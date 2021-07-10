<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/pwfindstyle.css">
<title>비밀번호 찾기</title>
</head>
<body>

<div class="container forget-password">
   <div class="row">
      <div class="col-md-12 col-md-offset-4">
         <div class="panel panel-default">
            <div class="panel-body">
               <div class="text-center">
                  <img src="https://usa.afsglobal.org/SSO/SelfPasswordRecovery/images/send_reset_password.svg?v=3" alt="car-key" border="0">
                  <h2 class="text-center">Forgot Password?</h2>
                  <p>Send Email. You can reset your password.</p>
                  <form action="sendmail" method="post">
                     <div class="form-group">
                        <div class="input-group">
                           <input type="text" id="user_id" name="user_id" placeholder="Insert your ID" required="required" class="form-control">
                        </div>
                     </div>
                     <div class="form-group">
                        <div class="input-group">
                           <input type="text" id="user_email" name="user_email" placeholder="Insert Your Email" class="form-control">
                        </div>
                     </div>
                     <div class="form-group">
                        <input type="submit" value="Send Email" class="btn btn-lg btn-block btnForget">
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
  
</body>
</html>