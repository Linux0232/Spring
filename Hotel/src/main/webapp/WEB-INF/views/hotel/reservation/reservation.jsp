<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
   border-style: solid;
   border-width : 1px;
   text-align : center;
}

ul {
   list-style-type: none;
}

li {
   display: inline-block;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>마이페이지 - 내 예약정보</title>
</head>
<body>
	<a href="../main">main</a>
	<h1>${reservation_user_id }님의 예약정보입니다.</h1>
	<hr>
	
	<a href="../user/mypagecheck"><button type="button">My정보 수정/삭제</button></a>
	<a href="../review/myreviewlist?user_id=${user_id }"><button type="button">나의 리뷰 목록</button></a>
    <input type="button" id="btn_get_reservation" value="예정된 예약"/>
	<input type="button" id="btn_get_finish" value="완료된 예약"/>
	
	<div style="text-align: center;">
    	<div id="reservation"></div>
    </div>
    
    <script type="text/javascript">
    $(document).ready(function() {
    	getReservation();
		function getReservation(){
			var url = '../rest/reservation/reservationbefore';
			$.getJSON(
					url,
					function(jsonData){
						console.log("예정된 예약");
						console.log(jsonData);
						var list = '<h1>예정된 예약</h1>'
								 + '<table>'
								 + '<thead>'
								 + '<tr>'
								 + '<th style="width:150px;">예약번호</th>'
								 + '<th style="width:300px;">호텔이름</th>'
								 + '<th style="width:400px;">호텔주소</th>'
								 + '<th style="width:100px;">체크인</th>'
								 + '<th style="width:100px;">체크아웃</th>'
								 + '<th style="width:100px;">룸타입</th>'
								 + '<th style="width:100px;">가격</th>'
								 + '<th style="width:100px;">예약취소</th>'
								 + '</tr>'
								 + '</thead>';
								$(jsonData).each(function(){
									console.log("예정된 예약 목록");
									console.log(this)
									var check_in_arr = this.reservation_check_in.split("-");
									var check_in = new Date(check_in_arr[0], check_in_arr[1]-1, check_in_arr[2]);
									var today = new Date();
									
									var disabled="";
									if(today >= check_in){ // 체크인 날짜가 오늘포함 이전이면 예약취소가 불가능하다(버튼 비활성화)
										disabled = "disabled";
									}
									
									list += '<tbody>'
										  + '<tr>'
										  + '<td>' + this.reservation_no +'</td>'
										  + '<td>' + this.hotel_vo.hotel_name +'</td>'
										  + '<td>' + this.hotel_vo.hotel_addr +'</td>'
										  + '<td>' + this.reservation_check_in +'</td>'
										  + '<td>' + this.reservation_check_out +'</td>'
										  + '<td>' + this.room_vo.room_name +'</td>'
										  + '<td>' + this.reservation_total_price +'</td>'
										  + '<td><a href="../reservation/canclecheck?reservation_no=' + this.reservation_no + '"><input type="button" value="예약취소" '+ disabled +'></a></td>'
										  + '</tr>'
										  + '</tbody>';
								}); // end each
							list += '</table>';
						$('#reservation').html(list);
					} // end function(jsonData)
			); // end getJSON
		} // end getReservation()
		
		$('#btn_get_finish').click(function(){
			var url = '../rest/reservation/reservationafter';
			$.getJSON(
				url,
				function(jsonData) {
					console.log("완료된 예약 목록");
					console.log(jsonData);
					var list = '<h1>완료된 예약</h1>'
							 + '<table>'
							 + '<thead>'
							 + '<tr>'
							 + '<th style="width:150px;">예약번호</th>'
							 + '<th style="width:300px;">호텔이름</th>'
							 + '<th style="width:400px;">호텔주소</th>'
							 + '<th style="width:100px;">체크인</th>'
							 + '<th style="width:100px;">체크아웃</th>'
							 + '<th style="width:100px;">룸타입</th>'
							 + '<th style="width:100px;">가격</th>'
							 + '<th style="width:100px;">리뷰작성</th>'
							 + '</tr>'
							 + '</thead>';
					$(jsonData).each(function(){
						console.log(this);
						var disabled = ""; // 리뷰를 작성하지 않았으면 리뷰작성버튼 활성화
						console.log(this.review_vo);
						if(this.review_vo != null){
							disabled = "disabled";
						}
						list += '<tbody>'
							  + '<tr>'
							  + '<td>' + this.reservation_no +'</td>'
							  + '<td>' + this.hotel_vo.hotel_name +'</td>'
							  + '<td>' + this.hotel_vo.hotel_addr +'</td>'
							  + '<td>' + this.reservation_check_in +'</td>'
							  + '<td>' + this.reservation_check_out +'</td>'
							  + '<td>' + this.room_vo.room_name +'</td>'
							  + '<td>' + this.reservation_total_price +'</td>'
 							  + '<td><a href="../review/insertreview?reservation_no=' + this.reservation_no + '"><input type="button" value="리뷰 등록" '+disabled+'></a></td>'
							  + '</tr>'
							  + '</tbody>';
					}); // end each
					list += '</table>';
					$('#reservation').html(list);
				} // end function(jsonData)
			); // end getJSON()
		}); // end btn_get_finish()
		
		$('#btn_get_reservation').click(function(){
			getReservation();
		}); // end btn_get_reserve
    }); // end document
    </script>
</body>
</html>