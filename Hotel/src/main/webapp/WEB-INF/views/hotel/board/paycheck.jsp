<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- iamport.payment js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<meta charset="UTF-8">
<title>최종 결제 확인 페이지</title>
</head>
<body>
	<a href="../main">main</a>
	<h1>${user_vo.user_name }님 결제정보를 확인해주십시오.</h1>
	<hr>
	<h2>호텔 : ${hotel_info.hotel_name }</h2>
	<h2>룸 타입 : ${room_name }</h2>
	<h2>체크인 : ${check_in }</h2>
	<h2>체크아웃 : ${check_out }</h2>
	<hr>
	<h1>최종 결제 금액 : ${total_price} 원</h1>
	<button onclick="requestPay()">결제하기</button>
	
	<script type="text/javascript">
	var IMP = window.IMP;
	IMP.init("imp20100687"); // 가맹점 식별코드
	
	// IMP.request_pay(param, callback) 호출
	function requestPay(){
 		var room_no = ${room_no};
		var merchant_uid = 'merchant_' + new Date().getTime(); // 예약번호
		var reservation_hotel_no = ${hotel_info.hotel_no};
		var reservation_user_id = '${user_id}';
		var check_in = '${check_in}';
		var check_out = '${check_out}';
		var total_price = ${total_price};
		
		IMP.request_pay({
			pg : 'html5_inicis', // 결제사
			pay_method : 'card', // 결제방식 : 카드결제
			merchant_uid : 'merchant_' + new Date().getTime(), // 예약번호
			name : '주문명 : ${hotel_info.hotel_name} ${room_name}',
			amount : ${total_price}, // 결제가격
			buyer_email : '${user_vo.user_email}',
			buyer_name : '${user_vo.user_name}',
			buyer_tel : '${user_vo.user_phone}',
		}, function(rsp) {
			if(rsp.success) {
				$.ajax({
					type : "post",
					url : "/spring/hotel/rest/reservation/payment", // 보내고자하는 서버
					headers : {"Content-Type": "application/json","X-HTTP-Method-Override" : "POST"},
					data : JSON.stringify({
						'reservation_no' : merchant_uid, // 예약번호
						'reservation_hotel_no' : reservation_hotel_no,
						'reservation_user_id' : reservation_user_id,
						'reservation_check_in' : check_in,
						'reservation_check_out' : check_out,
						'reservation_room_no' : room_no,
						'reservation_total_price' : total_price,
 					}),
 					success : function(result, status){
 						var msg = '결제가 완료되었습니다.\n';
 						msg += '${user_vo.user_name}님 해당 카드 관련 정보는\n';
 						msg += '고유ID : ' + rsp.imp_uid;
						msg += '\n상점 거래ID : ' + rsp.merchant_uid;
						msg += '\n결제 금액 : ' + rsp.paid_amount;
						msg += '\n카드 승인번호 : ' + rsp.apply_num;
						msg += '입니다';
						alert(msg);
						console.log(result + ', ' + status);
						location.href='<%=request.getContextPath()%>/hotel/reservation/paysuccess?reservation_no=' + rsp.merchant_uid;
 					} // end success
				}); // end ajax
			} else {
				var msg = '${room_name} 결제에 실패하였습니다.\n';
				msg += '에러내용 : ' + rsp.error_msg;
				msg += ' ${user_vo.user_name}님';
				alert(msg);
			}			
		}); // end IMP.request_pay()
	} // end requestPay
	</script>
</body>
</html>