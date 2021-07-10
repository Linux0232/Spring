<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- iamport.payment js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>예약 취소 페이지</title>
</head>
<body>
	<h1>${reservation_id }님 예약취소를 요청하셨습니다.</h1>
	<h2>아래의 예약정보를 확인후 예약취소 버튼을 눌러주세요.</h2>
	<hr>
	예약 번호 : ${reservation_no }<br>
 	호텔 : ${reservation_vo.hotel_vo.hotel_name }<br>
	호텔 위치 : ${reservation_vo.hotel_vo.hotel_addr }<br>
	룸타입 : ${reservation_vo.room_vo.room_name }<br>
	여행 일정 : ${reservation_vo.reservation_check_in } ~ ${reservation_vo.reservation_check_out }<br>
	결제 금액 : ${reservation_vo.reservation_total_price }<br>
	
	<hr>
	<button onclick="canclePay()">예약취소</button>	
	<a href="../reservation/reservation"><input type="button" value="마이페이지"></a>
	<a href="../main"><input type="button" value="홈으로"></a>
	
	<script type="text/javascript">
 		function canclePay(){
			var merchant_uid = '${reservation_no}';
			var reservation_user_id = '${reservation_id }';
			console.log("예약번호 : " + merchant_uid);
			
			$.ajax({
				type : 'delete',
				url : '/spring/hotel/rest/reservation/canclepayment',	
				headers : {"Content-Type": "application/json","X-HTTP-Method-Override" : "DELETE"},
				data : JSON.stringify({
					reservation_no : merchant_uid, // 예약번호
				}),
				success : function(result, status){
					var msg = '예약이 취소되었습니다.\n';
						    + '결제 취소까지 카드사 영업일 기준 2~3일정도 소요됩니다.'
					alert(msg);
					location.href='<%=request.getContextPath()%>/hotel/reservation/reservation?reservation_user_id=' + reservation_user_id;
				}
			}); // end ajax
		} // end canclePay()

	</script>
</body>
</html>