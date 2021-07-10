<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

#map {
	width:100%;
	height: 400px;
	background-color: grey;
}
</style>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<!-- iamport -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<!-- google map api -->
<script async defer  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkl0sSFUjZRDg6dlPuVL46SrvtHnGeSUc&callback=initMap&sensor=true"></script>
<meta charset="UTF-8">
<title>호텔 상세 페이지</title>
</head>
<body>

<!-- ------------------------------------- header : 검색 기능 ------------------------------------- -->
<!-- ------------- 검색을 누르면 list로 가는데 아래 날짜선택 조건을 버리고 여기서 detail로 가면 좋을것 같음 ----------- -->
   <header>
   	 <a href="../main">main</a>
     <h1>Welcome, Hotel Bookers</h1>
     <%-- <form action="list" method="get">
         <div>
            <input type="text" name="keyword" id="keyword" placeholder="키워드값 입력" value="${keyword }"></input> 
            <input type="submit" value="검색">
         </div>
      </form> --%>
   </header>
   <hr>
   <!-- ----------------------------------------------------------------------------------------------- -->
   
   <!-- ---------------------------------------------- 호텔 정보 ---------------------------------------- -->
   <p>호텔사진 : ${hotel_info.hotel_no }
   <p>호텔명 : ${hotel_info.hotel_name }</p>
   <p>주소 : ${hotel_info.hotel_addr }</p>
   <!-- GoogleMap API연동 -->
   <div id="map"></div> <!-- 지도가 붙을 위치 -->
   <p>전화번호 : ${hotel_info.hotel_phone }</p>
   <p>호텔 정보 : ${hotel_info.hotel_comment }</p>
   <p>가격 : ${hotel_info.hotel_low_price }</p>
   <p>평점 : ${hotel_info.hotel_star }</p>
   <p>리뷰수 : ${hotel_info.hotel_review_count }</p>
   <!-- ---------------------------------------------------------------------------------------------- -->

	<hr>
	<form action="detail" method="get">
		<input type="hidden" name="hotel_no" value="${hotel_info.hotel_no }">
		체크인 : <input type="text" name="check_in" id="check_in" value="${check_in }"> -
		체크아웃 : <input type="text" name="check_out" id="check_out" value="${check_out }">
		인원 : <select name="person">
				<option value=1 ${person ==1 ? 'selected="selected"' : '' }>1</option>
                <option value=2 ${person ==2 ? 'selected="selected"' : '' }>2</option>
                <option value=3 ${person ==3 ? 'selected="selected"' : '' }>3</option>
                <option value=4 ${person ==4 ? 'selected="selected"' : '' }>4</option>
                <option value=5 ${person ==5 ? 'selected="selected"' : '' }>5</option>
                <option value=6 ${person ==6 ? 'selected="selected"' : '' }>6</option>
			  </select> 
		<input type="submit" value="객실 확인">
	</form>
	<em>원하시는 방이 없으시다면? 일정을 변경해보세요!</em>
	
   <!-- ----------------------------------------- 방정보 list up ---------------------------------------------- -->
   <table>
      <thead>
         <tr>
            <th style="width: 80px;">방 사진</th>
            <th style="width: 200px;">방 이름</th>
            <th style="width: 80px;">수용 가능 인원</th>
            <th style="width: 80px;">${cal_date_days }박 가격</th>
            <th style="width: 60px;"></th>
         </tr>      
      </thead>
      <tbody>
      <c:forEach var="room_vo" items="${list }" varStatus="status">
        <tr>
        	<form action="paycheck" method="post">
        		<td><%-- <img src="<spring:url value='/resources/img/${hotel_vo.hotel_no }.jpeg'/>"width="80"/> --%> ${room_vo.room_no } </td>
          		<td>${room_vo.room_name }</td>
          		<td>${room_vo.room_person }</td>
          		<td><div id="room_per_price${status.count }"></div></td>
          		<td><input type="submit" value="예약하기"></td>
          		<input type="hidden" name="hotel_no" id="hotel_no" value=${hotel_info.hotel_no }>
                <input type="hidden" name="check_in" id="check_in" value=${check_in }>
                <input type="hidden" name="check_out" id="check_out" value=${check_out }>
                <input type="hidden" name="room_no" id="room_no" value=${room_vo.room_no }>
                <input type="hidden" name="room_name" id="room_name" value=${room_vo.room_name } >
                <input type="hidden" name="total_price" id="total_price${status.count }" value="">
        	</form>
        </tr>
	  
	  <script type="text/javascript">
		var cal_date_days = ${cal_date_days}; 
		var room_per_price = ${room_vo.room_per_price };    
			console.log(room_per_price)
			
			function cal_room_price(cal_date_days, room_per_price){
				return cal_date_days * room_per_price;
			}
			
			var room_price = cal_room_price(cal_date_days,room_per_price).toString();
			console.log(room_price)
			document.getElementById('room_per_price${status.count }').innerHTML = room_price;
			document.getElementById('total_price${status.count }').value = cal_room_price(cal_date_days, room_per_price);
	  </script>
      </c:forEach>
      </tbody>
   </table>
  <!-- --------------------------------------------------------------------------------------------------------- -->
	
  <!-- ----------------------------------------- 리뷰 목록 불러오기 ---------------------------------------------------- -->
	<hr>
	<div style="text-align: center;"><div id="review_list"></div></div>
	<input type="hidden" name="page" id="page" value=${page }>
	<input type="hidden" name="user_id" id="user_id" value=${user_id }>
  <!-- --------------------------------------------------------------------------------------------------------- -->
	
  <!-- -------------------------------------------------- 대댓글 ------------------------------------------------- -->
   <!-- 답글 등록하는 textarea -->
   <div id="create_form_wrapper">
    <textarea name="content" id="reply_content"></textarea>
    <button class="btn_reply_insert" type="button" >등록</button>
   </div>
   
   <!-- 답글 수정하는 textarea -->
   <div id="update_form_wrapper">
    <textarea name="content" id="reply_update_content"></textarea>
    <button class="btn_reply_update" type="button" >등록</button>
   </div>
  <!-- --------------------------------------------------------------------------------------------------------- -->


<script type="text/javascript">
 	$(document).ready(function() {
 		// reload 해주는 이유 : 서버사이드에서 위도 경도를 불러오고 있어서 시점차이
   		if (self.name != 'reload') {
 	         self.name = 'reload';
 	         self.location.reload(true);
 	    }
 	    else self.name = '';
		
 		initMap();
		function initMap(){
			var latitude = ${hotel_info.hotel_latitude};
			var longitude = ${hotel_info.hotel_longitude};
			var hotel_name = '${hotel_info.hotel_name}';
			
			var location = {
				lat:latitude, 
				lng:longitude
			};
			var map = new google.maps.Map(document.getElementById('map'),{
				zoom:15, // 지도 확대정도
				center: location
			});
			var marker = new google.maps.Marker({
				position: location,
				title: hotel_name
			});
			marker.setMap(map);
		}
	}); // end documnet


	$(document).ready(function () {
	    $.datepicker.setDefaults($.datepicker.regional['ko']); 
	    $( "#check_in" ).datepicker({
	         changeMonth: true, 
	         changeYear: true,
	         nextText: '다음 달',
	         prevText: '이전 달', 
	         dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
	         dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
	         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         dateFormat: "yy-mm-dd",
	         minDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
	         onClose: function( selectedDate ) {    
	              //시작일(startDate) datepicker가 닫힐때
	              //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
	             $("#check_out").datepicker( "option", "minDate", selectedDate );
	         }    
	    });
	    
	    $( "#check_out" ).datepicker({
	         changeMonth: true, 
	         changeYear: true,
	         nextText: '다음 달',
	         prevText: '이전 달', 
	         dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
	         dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
	         monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	         dateFormat: "yy-mm-dd",
	         minDate: 0,                       
	         onClose: function( selectedDate ) {    
	             // 종료일(endDate) datepicker가 닫힐때
	             // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
	             $("#check_in").datepicker( "option", "maxDate", selectedDate );
	         }    
	    });    
	});
    
	$(document).ready(function(){
		var hotel_no = $('#hotel_no').val();
	    var user_id = $('#user_id').val();
	    var check_in = $('#check_in').val();
	    var check_out = $('#check_out').val();
	    var page = $('#page').val();

		// 전체 리뷰 가져오기
		getAllReviewList();
		function getAllReviewList(){
			var url = '../rest/review/all/' + hotel_no;
			// $.getJSON(url, data, callback) : 요청한 url의 JSON 데이터를 호출
	        // url : 서버의 요청 주소
	        // data : 요청과 함께 보낼 데이터(선택)
	        // callback : JSON 데이터가 리턴됐을때 처리할 함수
			
	        $.getJSON(
	        		url,
	        		function(jsonData){
	        			console.log(jsonData);
	        			var list=''; // JSON 데이터를 표현할 변수
	        			
	        			$(jsonData).each(function(){
	        				console.log(this); // this : 컬랙션에서 각 데이터를 꺼내서 저장
	        				var disabled=""; // 작성자만 리뷰 수정삭제가 가능하게 하기 위한 변수
	        				var none = "'none'"; // 이미지 오류(x박스) 안뜨게 처리하기 위해
	        				var reply = ""; // review일떄는 공란, reply일떄는 ----> 로 구분위해 사용
	        				var review_star = "평점 : " + this.review_star; // 댓글에는 평점이 보이고, 대댓글에는 평점이 안보이게 구분
	        				var reply_button = ""; // admin계정에서만 reply버튼을 활성화 및 보이게 하기 위해서
	        				var review_update_button = "btn_review_update"; // 리뷰의 버튼태그 클래스명, 대댓글의 버튼태그 클래스명은 btn_reply_update_textarea로 변경하여 두 버튼 구분
	        				
	        				if(user_id != this.review_user_id) { // 작성자만 리뷰 수정삭제가 가능하게 하기 위해
	        					disabled = "disabled";
	        				}
	        				if(user_id == "admin" && this.review_user_id != "admin") { 
	        					// admin만 답글 가능 && admin의 답글 vo에 답글 버튼 보이지 않게
	        					reply_button =  "<button class=\"btn_reply\" type=\"button\" >답글</button>";
	        				}
	        				if(this.review_user_id == "admin") {
	        					reply = "---> ";
	        					review_star = "";
	        					review_update_button = "btn_reply_update_textarea"; // 대댓글에서 수정 눌렀을때 대댓글이 수정되게 버튼태그 클래스명 수정
	        				}
	        				
	        				list += '<div class="review_item">'
	        					  + '<pre>'
	        					  + '<input type="hidden" id="review_no" value="' + this.review_no + '" />'
	        	                  + '<input type="hidden" id="review_user_id" value="' + this.review_user_id + '" />'
	        	                  + '<input type="hidden" id="review_group" value="' + this.review_group + '" />'
	        	                  + '<input type="hidden" id="review_reservation_no" value="' + this.review_reservation_no + '" />'
	        	                  + '<input type="hidden" id="review_room_no" value="' + this.review_room_no + '" />'
	        	                  + '<img src = "../rest/review/display?file_name='+ this.review_hotel_no +'_'+ this.review_img_file+'" onerror="this.style.display='+none+';" width="200"/>'
	        	                  + '&nbsp;&nbsp;'
	        	                  + reply // 대댓글 표시를 위해 "--->" && 일반 리뷰에는 "--->" 없음!
	        	                  + this.review_user_id
	        	                  + '&nbsp;&nbsp;' // 공백 
	        	                  + '<input type="hidden" id="review_content" value="' + this.review_content + '"/>'
	        	                  + this.review_content
	        	                  + '&nbsp;&nbsp;'
	        	                  + review_star // 리뷰에는 "평점 : " + this.review_star && 대댓글에는 평점xx 
	        	                  + '&nbsp;&nbsp;'
	        	                  + this.review_date
	        	                  + '&nbsp;&nbsp;'
	        	                  + '<button class="'+review_update_button+'" type="button" '+disabled+'>수정</button>'
	        	                  + '<button class="btn_delete" type="button" '+disabled+'>삭제</button>'
	        	                  + reply_button + '<br>'
	        	                  // "<button class="btn_reply" type="button">답글</button>"; 
	        	                  // >>> admin만 답글 가능 && admin의 답글 vo에 답글 버튼 보이지 않게
	        	                  + '<div class="reply_form"></div>' // 대댓글이 보여질 div태그
	        	                  + '</pre>'
	        	                  + '</div>';
	        			}); // end jsonData.each
	        			$('#review_list').html(list);
	        		} // end callback()
	        ); // end getJSON
		} // end getAllReviewList()
	    
		$('#review_list').on('click', '.review_item .btn_update', function(){
			console.log(this);
			
			// 선택된 review_no
			var review_no = $(this).prevAll('#review_no').val();
			console.log("선택된 리뷰 번호 : " + review_no);
			location.href="../review/updatereview?review_no="+review_no+"&review_hotel_no="+hotel_no
										+"&page="+page+"&check_in="+check_in+"&check_out="+check_out;
		}); // end review_list.onclick(btn_update)
		
		$('#review_list').on('click', '.review_item .btn_delete', function(){
			console.log(this);
			// 선택된 review_no
			var review_no = $(this).prevAll('#review_no').val();
			console.log("선택된 리뷰 번호 : " + review_no);
			
			//.ajax 요청
			$.ajax({
				type : 'delete',
				url : '../rest/review/' + review_no,
				headers : {'Content-Type' : 'application/json', 'X-HTTP-Method-Override' : 'DELETE'},
				data : JSON.stringify({
						'review_hotel_no' : hotel_no,
				}),
				success : function(result){
					if(result == 'success'){
						alert('리뷰 삭제 성공');
						getAllReviewList();
					} // end if
				} // end callback
			}); // end ajax
		}); // end review_list.onclick(btn_delete)
		
	 	// 입력하는 버튼 누르기 전에는 댓글/답글 입력창 감추기
	    $('#create_form_wrapper').hide();
	    $('#update_form_wrapper').hide();
	    var create_form = $("#create_form_wrapper").html(); // 댓글 생성 창
	    var update_form = $("#update_form_wrapper").html(); // 댓글 수정 창
		
	    // 댓글의 답글 버튼 클릭시
		$('#review_list').on('click', '.review_item .btn_reply', function(){
			var table = $(this).parent();
			var reply_form = table.children(":eq(11)"); // <div class="reply_form"></div> 내부 태그<>기준 11번째 위치
			
			// 답글버튼(btn_reply) 클릭시 창이열리고, 한번더 누르면 창이 닫힘이 가능하게
			if(create_form != ""){ 
				reply_form.html(create_form);
				reply_form.show();
				create_form="";
			} else {
				create_form = $("#create_form_wrapper").html();
				reply_form.hide();
			} // end if-else
		}); // end review_list.onclick(btn_reply)
		
		// 대댓글 등록버튼 클릭시
		$('#review_list').on('click', '.review_item .btn_reply_insert', function(){
			console.log(this);
			console.log($(this).parent().siblings('#review_no').val()); 
			
			// 답글을 달 review_no을 뽑아 대댓글의 review_group에 넣어준다.
			// 선택된 review_no
			var review_group = $(this).parent().siblings('#review_no').val();
			var review_content = $('#reply_content').val();
			var review_reservation_no = $(this).parent().siblings('#review_reservation_no').val();
			var review_room_no = $(this).parent().siblings('#review_room_no').val();
			
			$.ajax({
				type : 'post',
				url : '../rest/review/replies',
				headers : {'Content-Type' : 'application/json', 'X-HTTP-Method-Override' : 'POST'},
				data : JSON.stringify({
					'review_hotel_no' : hotel_no,
					'review_group' : review_group,
					'review_content' : review_content,
					'review_user_id' : user_id,
					'review_reservation_no' : review_reservation_no,
					'review_room_no' : review_room_no,
					'review_img_file' : "none",
				}),
				success : function(result, status) {
					console.log(result, status);
					if(result == 1){
						alert("대댓글 입력 성공");
						getAllReviewList();
					} // end if
				} // end success : function()
			}); // end ajax()
		}); // end review_list.onclick(btn_reply_insert)
		

		// 대댓글 : 수정 버튼을 클릭하면 입력창이 뜨게
		$('#review_list').on('click', '.review_item .btn_reply_update_textarea', function(){
			var table = $(this).parent();
			var reply_form = table.children(":eq(10)");
			
			if(update_form !=""){
				reply_form.html(update_form);
				reply_form.show();
				update_form = "";
			} else {
				update_form = $('#update_form_wrapper').html();
				reply_form.hide();
			}
		}); // end review_list.onclick(btn_reply_update_textarea)
		
		// 대댓글 : 수정할 내용 입력후 등록 버튼을 클릭하면 선택된 답글 수정
		$('#review_list').on('click', '.review_item .btn_reply_update', function(){
			// 선택된 리뷰no
			var review_no = $(this).parent().siblings('#review_no').val();
			var review_content = $('#reply_update_content').val();
			
			// ajax 요청
			$.ajax({
				type : 'put',
				url : '../rest/review/' + review_no,
				headers : {'Content-Type' : 'application/json', 'X-HTTP-Method-Override' : 'PUT'},
				data : JSON.stringify({
					'review_no' : review_no,
					'review_content' : review_content,
					'review_img_file' : 'none',
				}),
				success : function(result){
					if(result == 'success'){
						alert('답글 수정 성공');
						getAllReviewList();
					} // end if
				} // end success
			}); // ajax
		}); // end review_list.onclick(btn_reply_update)
	}); // end document
	
	
</script>

</body>
</html>