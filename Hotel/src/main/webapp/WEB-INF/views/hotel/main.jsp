<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<!-- import css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mainstyle.css">

<meta charset="UTF-8">
<title>Hotel Bookers</title>
</head>
<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
         <a href="main" id="logo"><img src="../resources/img/logo.jpg" width="150"/></a>
         
  <div id="sessionLog">
	<c:if test="${empty sessionScope.user_id }">
		<!-- sessionScopre.id가 없으면 -->
		<a href="user/login"><button type="button" class="btn btn-outline-success me-2">Login</button></a>
		<a href="user/register"><button type="button" class="btn btn-outline-success me-2">Sign-up</button></a>
	</c:if>

	<c:if test="${not empty sessionScope.user_id }">
		<!-- sessionScopre.id가 있으면 -->
		<a href="reservation/reservation"><button type="button" class="btn btn-outline-success me-2">My Page</button></a>
 		<!-- <a href="user/mypagecheck"><button type="button">My Page</button></a> -->
		<a href="user/logout"><button type="button" class="btn btn-outline-success me-2">Logout</button></a>
		<br />
	</c:if>
  </div>
</header>
  <div id="centerWindow">
    <div id="search">
    <h1>Welcome, HotelBookers</h1>
	 <form action="board/list" method="get">
			<input type="text" name="keyword" id="keyword" placeholder="호텔 및 지역명">
			<input type="text" name="check_in" id="check_in"> - 
			<input type="text" name="check_out" id="check_out">
			인원수 : <select name="person">
						<option value=1>1</option>
						<option value=2 selected>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5>5</option>
						<option value=6>6</option>
			        </select>
			<button type="submit" class="btn-3">검색</button>
  	 </form>
    </div>
  </div>

  
<footer>
   <div id="footerDiv">
      Hotel Bookers(주) | 대표자 : 봄(spring)이 좋냐! | 사업자 등록번호 : 123-12-123456<br>
      전화 : 02-123-1234 | 서울시 구로구 abc동 123번지<br>
      <br>
      Copyright by Hotel Bookers Company All Rights Reserved
   </div>
</footer>
	
	<input id="insertAlert" type="hidden" value="${insert_result }">
	<input id="deleteAlert" type="hidden" value="${delete_result }">
	<input id="loginAlert" type="hidden" value="${login_result }">
	
	<script type="text/javascript">
		$(document).ready(function() {
			confirmDeleteResult();
			function confirmDeleteResult() {
				var result = $('#deleteAlert').val();
				if (result == 'success') {
					alert('회원탈퇴에 성공했습니다.\n감사합니다.');
				} else if (result == 'fail') {
					alert('회원탈퇴에 성공했습니다.\n다시 시도해주세요.');
				}
			}
		});
	
		$(document).ready(function(){
			confirmInsertResult();
			function confirmInsertResult(){
				var result = $('#insertAlert').val();
				if(result == 'success'){
					alert('회원가입에 성공했습니다.\n로그인 해주세요!');
				}
			}
		});
	
		$(document).ready(function(){
			confirmLoginResult();
			function confirmLoginResult(){
				var result = $('#loginAlert').val();
				if(result == 'success'){
					alert('로그인에 성공했습니다.');
				}
			}
		});
		
		$(document).ready(function () {
		     $.datepicker.setDefaults($.datepicker.regional['ko']); 
		     $("#check_in").datepicker({
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
		     }).datepicker('setDate', new Date());
		            
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
		     }).datepicker('setDate', '+1D');    
		});
	</script>
	
</body>
</html>
