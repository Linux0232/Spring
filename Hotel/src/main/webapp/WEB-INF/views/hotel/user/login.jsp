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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/loginstyle.css">
<title>Login</title>
</head>
<body>
   <div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
               <img src="https://colorlib.com/etc/lf/Login_v1/images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form" action="login" method="post">
               <span class="login100-form-title">
                  Member Login
               </span>

               <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                  <input class="input100" type="text" name="user_id" placeholder="User ID" required="required" autofocus="autofocus">
                  <span class="focus-input100"></span>
                  <span class="symbol-input100">
                     <i class="fa fa-envelope" aria-hidden="true"></i>
                  </span>
               </div>

               <div class="wrap-input100 validate-input" data-validate = "Password is required">
                  <input class="input100" type="password" name="user_pw" placeholder="Password" required="required">
                  <span class="focus-input100"></span>
                  <span class="symbol-input100">
                     <i class="fa fa-lock" aria-hidden="true"></i>
                  </span>
               </div>

               <div class="container-login100-form-btn">
                  <button class="login100-form-btn" type="submit">
                     Login
                  </button>
               </div>
                <div class="text-center p-t-12">
                  <span class="txt1">
                     Forgot
                  </span>
                  <a class="txt2" href="idfind">
                     Userid?
                  </a>
               </div>

               <div class="text-center p-t-12">
                  <span class="txt1">
                     Forgot
                  </span>
                  <a class="txt2" href="pwfind">
                     Password?
                  </a>
               </div>
               
               

               <div class="text-center p-t-136">
                  <a class="txt2" href="register">
                     Create your Account
                     <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                  </a>
               </div>
            </form>
         </div>
      </div>
   </div>

   <input id="pwAlert" type="hidden" value="${pw_result }">
   <input id="updateAlert" type="hidden" value="${update_result }">
   <input id="loginAlert" type="hidden" value="${login_result }">
   <input id="idAlert" type="hidden" value="${id_result}">
 <script type="text/javascript">
      $(document).ready(function() {
    	 confirmMailResultId();
    	 confirmMailResultPw();
         confirmUpdateResult();
         confirmLoginResult();
         
         function confirmMailResultId() {
            var result = $('#idAlert').val();
            if (result == 'success') {
               alert('?????? ???????????? ???????????? ?????????????????????.');
            } else if (result == 'fail') {
               alert('name ?????? email??? ???????????????. ?????? ??????????????????.');
            }
         }
   
         function confirmMailResultPw() {
            var result = $('#pwAlert').val();
            if (result == 'success') {
               alert('????????? ????????????????????? ????????? ????????????. ????????????????????? ?????????????????????');
            } else if (result == 'fail') {
               alert('id ?????? email??? ???????????????. ?????? ??????????????????.');
            }
         }
      
            
            function confirmUpdateResult() {
               var result = $('#updateAlert').val();
               if (result == 'success') {
                  alert('??????????????????. ????????? ?????????????????????.');
               } else if (result == 'fail') {
                  alert('??????????????????');
               }
            }
      
         function confirmLoginResult(){
            var result = $('#loginAlert').val();
            if(result == 'fail'){
               alert('???????????? ??????????????????.\n?????? ??????????????????!');
            }
         }
      });
   </script>

</body>
</html>