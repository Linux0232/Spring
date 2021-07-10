<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- import css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/mypagecheckstyle.css">
<title>회원정보 삭제 확인</title>
</head>
<body>
   <div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
               <img src="https://colorlib.com/etc/lf/Login_v1/images/img-01.png" alt="IMG">
            </div>
            

            <form class="login100-form validate-form" action="deletecheck" method="post">
               <span class="login100-form-title">
                  회원정보 확인
               </span><hr>
               <span class="login100-form-title">
                  ${user_id }님의 정보를 안전하게 삭제하기 위해 비밀번호, 이메일을 다시 한번 확인 합니다.
               </span>

               <div class="wrap-input100 validate-input" data-validate = "Password is required">
                  <input class="input100" type="password" name="user_pw" placeholder="Password" required="required">
                  <span class="focus-input100"></span>
                  <span class="symbol-input100">
                     <i class="fa fa-lock" aria-hidden="true"></i>
                  </span>
               </div>
               <div class="wrap-input100 validate-input" data-validate = "Email is required">
                  <input class="input100" type="email" name="user_email" placeholder="Email" required="required">
                  <span class="focus-input100"></span>
                  <span class="symbol-input100">
                     <i class="fa fa-lock" aria-hidden="true"></i>
                  </span>
               </div>

               <div class="container-login100-form-btn">
                  <button class="login100-form-btn" type="submit">
                     회원탈퇴
                  </button>
               </div>
            </form>
         </div>
      </div>
   </div>
</body>
</html>