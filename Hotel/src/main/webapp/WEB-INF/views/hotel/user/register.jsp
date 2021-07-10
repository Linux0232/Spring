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
<title>회원가입 페이지</title>
</head>
<body>
<div class="limiter">
      <div class="container-login100">
         <div class="wrap-login100">
            <div class="login100-pic js-tilt p-t-126" data-tilt>
               <img src="https://colorlib.com/etc/lf/Login_v1/images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form" action="register" method="post">
               <span class="login100-form-title">
                  Make Account
               </span>

               <div class="wrap-input100 validate-input" >
                  <input class="input100" type="text" id="user_id" name="user_id" placeholder="User ID" required="required" autofocus="autofocus"> 
               </div>
              <div id="msg_id_check"></div>
              
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="password" name="user_pw" id="user_pw" placeholder="Password" required="required">
               </div>
    
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="password" name="user_pw_check" id="user_pw_check" placeholder="PasswordCheck" required="required">
               </div>
               <div id="msg_pw_check"></div>
               
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="text" name="user_name" placeholder="Name" required="required">
               </div>
               
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="text" name="user_phone" placeholder="Phone Number" required="required">
               </div>
               
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="email" name="user_email" id="user_email" placeholder="Email" required="required">
               </div>
                  <div id="msg_email_check"></div>
               
               <div class="wrap-input100 validate-input">
                  <input class="input100" type="number" name="user_birth" placeholder="Birth" required="required">
               </div>
               
               <div class="container-login100-form-btn">
                  <button class="login100-form-btn" type="submit">
                     Sign UP
                  </button>
               </div>
               </form>

               <div class="text-center p-t-12">
                  <span class="txt1">
                     Already have an account?
                  </span>
                  <a class="txt2" href="login">
                     Login here.
                  </a>
               </div>
               <div class="text-center p-t-8">
                  <span class="txt1">
                     Want go home?
                  </span>
                  <a class="txt2" href="../main">
                     click here.
                  </a>
               </div>
            
         </div>
      </div>
   </div>
   
     <input id="insert_alert" type="hidden" value="${insert_result }">
     <input id="overlap_alert" type="hidden" value="${overlap_check }">
   

     <script type="text/javascript">
     
     $(document).ready(function(){
        confirmOverlapCheckResult();
      function confirmOverlapCheckResult(){
         var result = $('#overlap_alert').val();
         if(result == 'fail'){
             alert('입력하신 정보가 조건에 맞지 않습니다.\n다시 시도해주세요!');
         } 
      }
   });
     
     $(document).ready(()=>{
       $('#user_id').blur(()=>{
           var user_id = $('#user_id').val();
            console.log(user_id);
            
            $.ajax({
               type : 'post',
                url : '/spring/hotel/user/useridcheck',
                data : {
                   "user_id" : user_id 
                },
                success : (result)=>{
                   var list = '';
                   
                   if (result == '1') {
                       list += '<p style="color : red;">' + user_id + '는 중복된 아이디입니다.</p>';
                  } else if (result == '0'){
                     list += '<p style="color : blue;">' + user_id + '는 사용할 수 있는 아이디입니다.</p>';
                  } else if(result == '-1') {
                     list += '<p style="color : red;">아이디를 5글자 이상 입력해 주세요.</p>';
                  }
                   $('#msg_id_check').html(list);
                }
             });
          }); // end of userId.blur
       
          $('#user_pw_check').blur(()=>{
             var user_pw = $('#user_pw').val();
             var user_pw_check = $('#user_pw_check').val();
             console.log(user_pw);
             console.log(user_pw_check);
             
             $.ajax({
                type : 'post',
                url : '/spring/hotel/user/userpwcheck',
                data : {
                   "user_pw" : user_pw,
                   "user_pw_check" : user_pw_check
                },
                success : (result) =>{
                   var list='';
                   if(result == '1') {
                      list += '<p style="color : blue;">비밀번호가 일치합니다.</p>';
                   } else if(result == '0'){
                      list += '<p style="color : red;">비밀번호가 일치하지 않습니다.</p>';
                   }
                   $('#msg_pw_check').html(list);
                }
             })
          }); // end of user_pw_check.blur()
       
       
          $('#user_email').blur(()=>{
             var user_email = $('#user_email').val();
             console.log(user_email);
             
             $.ajax({
                type : 'post',
                url : '/spring/hotel/user/useremailcheck',
                data :{
                   "user_email" : user_email
                },
                success : (result) =>{
                   var list="";
                   if(result== 1){
                      list+= '<p style="color : red;">' + user_email + '은 중복됩니다. 다른 이메일을 입력해 주세요.</p>'
                   } else if(result=='0'){
                      list+= '<p style="color : blue;">' + user_email + '은 사용가능한 이메일입니다.</p>'
                   }
                   $('#msg_email_check').html(list);
                }
             })
          }); // end of user_email.blur()
       }); // end of document()
    </script>
 
</body>
</html>