<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- import css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/registerstyle.css">
<title>개인정보 수정/삭제</title>
</head>
<body>
<div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100">
            <div class="login100-pic js-tilt p-t-126" data-tilt>
               <img src="https://colorlib.com/etc/lf/Login_v1/images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form" action="mypageupdate" method="post">
               <span class="login100-form-title">
                  Update Account
               </span><hr>
               <span class="login100-form-title">
                  안녕하세요 hotelBookers입니다. 변경하실 계정 정보를 입력해주세요.
               </span>
               <input type="hidden" id="user_vo" value="${user_vo }">

               <div class="wrap-input100 validate-input" >
                  <input class="input100" type="text" id="user_id" name="user_id" value="${user_id }" readonly="readonly"> 
               </div>
    
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="password" name="user_pw" id="user_pw" value="${user_vo.user_pw }" required="required">
               </div>
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="text" name="user_name" value="${user_vo.user_name }"required="required">
               </div>
               
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="text" name="user_phone" value="${user_vo.user_phone }"required="required">
               </div>
               
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="email" name="user_email" id="user_email" value="${user_vo.user_email }" required="required">
               </div>
               
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="number" name="user_birth" value="${user_vo.user_birth }" required="required">
               </div>
               
               <div class="container-login100-form-btn">
                  <button class="login100-form-btn" type="submit">
                     변경하기
                  </button>
               </div>
               </form>
               <div class="text-center p-t-8">
                  <span class="txt1">
                     취소
                  </span>
                  <a class="txt2" href="../user/mypagecheck">
                     Click here.
                  </a>
               </div>
               <div class="text-center p-t-8">
                  <span class="txt1">
                     회원탈퇴
                  </span>
                  <a class="txt2" href="../user/deletecheck">
                     Click here.
                  </a>
               </div>
            
         </div>
      </div>
   </div>
   
   <script type="text/javascript">
   		var a = document.getElementById('user_vo').value;
   		console.log(a);
   </script>
</body>
</html>