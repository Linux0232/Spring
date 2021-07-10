<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 성공 페이지</title>
</head>
<body>
	<h1>${reservation_id }님 결제가 완료되었습니다.</h1>
	<h2>아래의 예약정보를 확인해주세요.</h2>
	<hr>
	예약 번호 : ${reservation_no }<br>
 	호텔 : ${reservation_vo.hotel_vo.hotel_name }<br>
	호텔 위치 : ${reservation_vo.hotel_vo.hotel_addr }<br>
	룸타입 : ${reservation_vo.room_vo.room_name }<br>
	여행 일정 : ${reservation_vo.reservation_check_in } ~ ${reservation_vo.reservation_check_out }<br>
	결제 금액 : ${reservation_vo.reservation_total_price }<br>
	
	<a href="../reservation/reservation"><input type="button" value="마이페이지"></a>
	<a href="../main"><input type="button" value="홈으로"></a>
	
</body>
</html>