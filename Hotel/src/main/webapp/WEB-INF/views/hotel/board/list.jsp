<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery/jquery.cookie.js"></script>





<!-- import css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/liststyle.css">
<meta charset="UTF-8">
<title>Hotel Bookers</title>
</head>
   <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <a href="main" id="logo"><img src="${pageContext.request.contextPath}/resources/img/logo.jpg" width="150"/></a>
      <form action="list" method="get">
         <div>
            <input type="text" name="keyword" id="keyword" placeholder="호텔 및 지역명" value="${c.urlKeyword }"></input> 
            체크인 : <input type="text" name="check_in" id="check_in" value="${c.check_in }"> -
            체크아웃 : <input type="text" name="check_out" id="check_out" value="${c.check_out }">
            인원수 : <select name = "person" id="person">
                  <option value="1" ${c.person =='1' ? 'selected="selected"' : '' }>1</option>
                  <option value="2" ${c.person =='2' ? 'selected="selected"' : '' }>2</option>
                  <option value="3" ${c.person =='3' ? 'selected="selected"' : '' }>3</option>
                  <option value="4" ${c.person =='4' ? 'selected="selected"' : '' }>4</option>
                  <option value="5" ${c.person =='5' ? 'selected="selected"' : '' }>5</option>
                  <option value="6" ${c.person =='6' ? 'selected="selected"' : '' }>6</option>
                   </select>
            <input type="submit" value="검색">
         </div>
      </form>
      
      <div id="sessionLog">
        <c:if test="${empty sessionScope.user_id }">
         <!-- sessionScopre.id가 없으면 -->
         <a href="../user/login"><button type="button" class="btn btn-lg btn-outline-success me-2">Login</button></a>
         <a href="../user/register"><button type="button" class="btn btn-lg btn-outline-success me-2">Sign-up</button></a>
      </c:if>
         
         <c:if test="${not empty sessionScope.user_id }">
         <!-- sessionScopre.id가 있으면 -->
         <%-- ${userid} 님 어서오세요<br/> --%>
         <a href="../reservation/reservation"><button type="button" class="btn btn-outline-success me-2">My Page</button></a>
         <a href="../user/logout"><button type="button" class="btn btn-outline-success me-2">Logout</button></a>
         <br/>
      </c:if>
      </div>
   </header>
<body>
  
   <form action="list" method="get" autocomplete="off">
    <input type="submit" value="평점순" name="sort">
    <input type="submit" value="리뷰순" name="sort">
    <input type="submit" value="최저가격순" name="sort">
    <input type="submit" value="최고가격순" name="sort">
    <input type="hidden" name="keyword" id="keyword" value=${c.urlKeyword }>
    <input type="hidden" name="check_in" id="check_in" value=${c.check_in }>
    <input type="hidden" name="check_out" id="check_out" value=${c.check_out }>
    <input type="hidden" name="person" id="person" value=${c.person }>
    
   </form>
   <br> 
   <input type="hidden" name="session_id" id="session_id" value=${user_id }>
    
<div class="container">
  <c:forEach var="hotel_vo" items="${list }" varStatus="status">
     <div class="well">
      <div class="media">
           <a class="pull-left" href="detail?hotel_no=${hotel_vo.hotel_no }&page=${pageMaker.criteria.page}&keyword=${c.urlKeyword }&check_in=${c.check_in}&check_out=${c.check_out}&person=${c.person}" onclick="window.open(this.href, '_blank'); return false;"><img class="media-object" src="<spring:url value='../../resources/
img/${hotel_vo.hotel_no }.jpg'/>"width="150" height="150"/></a>
           <div class="media-body">

             <h4 class="media-heading">
             <a href="detail?hotel_no=${hotel_vo.hotel_no }&page=${pageMaker.criteria.page}&keyword=${c.urlKeyword }&check_in=${c.check_in}&check_out=${c.check_out}&person=${c.person}" onclick="window.open(this.href, '_blank'); return false;">${hotel_vo.hotel_name }</a></h4>                
              <p class="text-right">${hotel_vo.hotel_addr }</p>
              

                <p>${hotel_vo.hotel_addr }</p>
             <ul class="list-inline list-unstyled">
              <li>${hotel_vo.hotel_low_price } 원</li>
               <li>|</li>
               <span><i class="glyphicon glyphicon-comment"></i> ${hotel_vo.hotel_review_count } comments</span>
               <li>|</li>
               <li>
                  <c:if test="${hotel_vo.hotel_star == 1 }">
                     <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                  </c:if>
                  <c:if test="${hotel_vo.hotel_star == 2 }">
                     <span class="glyphicon glyphicon-star"></span>
                     <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                  </c:if>
                  <c:if test="${hotel_vo.hotel_star == 3 }">
                     <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                  </c:if>
                  <c:if test="${hotel_vo.hotel_star == 4 }">
                     <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                  </c:if>
                  <c:if test="${hotel_vo.hotel_star == 5 }">
                     <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                  </c:if>
               </li>
               <li>|</li>
               <li class="content">
               
               <!-- Use Font Awesome http://fortawesome.github.io/Font-Awesome/ -->
                 <span><i class="fa fa-facebook-square"></i></span>
                 <span><i class="fa fa-twitter-square"></i></span>
                 <span><i class="fa fa-google-plus-square"></i></span>
                 </li>
                 <li>
                 <div id="like${hotel_vo.hotel_no }">   
                   <input type="hidden" id="like_hotel_no"  value="${hotel_vo.hotel_no }"/>
                   <input type="hidden" id="like_user_id"  value="${user_id }"/>
                   <input type="hidden" id="like_name"  value="${hotel_vo.hotel_name }"/>
                   <input type="hidden" id="like_addr"  value="${hotel_vo.hotel_addr }"/>
                   <input type="hidden" id="like_star"  value="${hotel_vo.hotel_star }"/>
                   <input type="hidden" id="like_comment"  value="${hotel_vo.hotel_comment }"/>
                   
                   <button type="button" id="btn${hotel_vo.hotel_no }" ><img id="imgsrc${hotel_vo.hotel_no }"src="/spring/resources/img/empty_heart.jpg" width="20"/></button>
               <span class="badge">${hotel_vo.hotel_like_count }</span>
                </div>
            <div id="likely">
            </div>


                 <!-- 위 아래 vo.hno, button은 그냥 넣어놓은값 이걸 like 숫자로 변경해야함///////// 
                 아래 badge는 ajax 처리 ?????
                 c:if써서 해당 쿠키가 있으면 좋아요 누른 이미지 해당 아이디, hno 값에대한 쿠키가 없으면 좋아요 안누른 이미지
                 
                 -->
                 
               </li>
            </ul>
          </div>
       </div>
     </div>
  </c:forEach>
</div>

     <ul class="page" >
         <c:if test="${pageMaker.hasPrev }">
           <li><a href="list?page=${pageMaker.startPageNo - 1 }&keyword=${c.urlKeyword }&sort=${sort}&check_in=${c.check_in}&check_out=${c.check_out}&person=${c.person}">이전</a></li>
         </c:if>
         <c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num">
           <li><a href="list?page=${num }&keyword=${c.urlKeyword }&sort=${sort}&check_in=${c.check_in}&check_out=${c.check_out}&person=${c.person}">${num }</a></li>
         </c:forEach>
         <c:if test="${pageMaker.hasNext }">
           <li><a href="list?page=${pageMaker.endPageNo + 1 }&keyword=${c.urlKeyword }&sort=${sort}&check_in=${c.check_in}&check_out=${c.check_out}&person=${c.person}">다음</a></li>
         </c:if> 
     </ul>
  <hr><br>      
<div style="text-align: center;">
      <div id="like_list">
      </div>
</div>

   
<script type="text/javascript">
  
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
    
    var like_user_id = $('#session_id').val();
    var hotel_count = ${hotel_count}; // 반복문을 위한 호텔 갯수
        
    if(like_user_id != ""){
          $('body').on('click', '[id^=btn]', function(event){
        	  console.log(this);
            getAllLike();
            var like_hotel_no = $(this).prevAll('#like_hotel_no').val();
        console.log(like_hotel_no);
    
        $.ajax({
          type : 'post',
          url : '../rest/like/insert',
          headers : {'Content-Type' : 'application/json', 'X-HTTP-Method-Override' : 'POST'},
          data : JSON.stringify({
            'like_hotel_no' : like_hotel_no,
            'like_user_id' : like_user_id
          }),
          success : function(result, status){
            if(result == 1){
              getAllLike();
              location.reload();
              
              $.ajax({
                type : 'post',
                url : '../rest/like/list/countuser/' + like_user_id,
                headers : {'Content-Type' : 'application/json', 'X-HTTP-Method-Override' : 'POST'},
                data : JSON.stringify({
                  'like_user_id' : like_user_id
                }),
                success : function(result, status){
                  var result2 = result;
                  $.cookie('like_info' + like_hotel_no + like_user_id, like_hotel_no, { expires: 7, path: '/' }); // 쿠키 생성, expires:7 -> 쿠키 유지기간
                } // end success
              }); // end ajax
              $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/not_empty_heart.jpg"); // 꽉찬하트
              // 성공했을때 (insert)가 됬을때 쿠키가 생성되야함
              location.reload();
            } else if(result == 2) {
              $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/empty_heart.jpg"); // 빈하트
              getAllLike();
              $.removeCookie('like_info' + like_hotel_no + like_user_id, {path:'/'});
              location.reload();
              // 삭제되었을때 쿠키 제거
            } // end if/else if
          } // end success
        }); // end ajax
          }); // end body.onclick(id^=btn)
        
        getAllLike();
          function getAllLike(){
            var url = '../rest/like/list/'+ like_user_id;
        $.getJSON(
          url,
          function(jsonData){
            var list = '';
                      $(jsonData).each(function() {
                         console.log(this);
                         list += '<div class="like_item">'
                               + '<pre>'
                               + '<input type="hidden" id = "like_hotel_no" value="' + this.hotel_no + '" />'
                               + '<img src="/spring/resources/img/'+ this.hotel_no +'.jpg" width="65"/>'
                               + '&nbsp;&nbsp;' // 공백
                               + this.hotel_name
                               + '&nbsp;&nbsp;&nbsp;&nbsp;' // 공백
                               + '<button class="like_delete" type="button">삭제</button> '
                               + '</pre>'
                               + '</div>';
                      }); // end jsonData.each()
                      $('#like_list').html(list);
          } // end jsonData
        ); // end getJSON 
          } // end getAllLike()
      
      // 삭제 버튼을 클릭하면 선택된 댓글 삭제
           $('#like_list').on('click', '.like_item .like_delete', function () {
             var like_hotel_no= $(this).prevAll('#like_hotel_no').val();
               console.log("선택된 좋아요 호텔 번호 : " + like_hotel_no);
               
               // ajax 요청
               $.ajax({
                 type : 'delete',
                 url : '../rest/like/' + like_hotel_no + '/'+ like_user_id,
                 headers : { 'Content-Type' : 'application/json', 'X-HTTP-Method-Override' : 'DELETE' }, 
                 data : JSON.stringify({
                   'like_hotel_no' : like_hotel_no,
                   'like_user_id' : like_user_id
                 }),
                 success : function (result) {
                   if(result == 'success'){
                     $.removeCookie('like_info' + like_hotel_no + like_user_id,{ path: '/' });
                     $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/empty_heart.jpg");
                     for(var i=0; i < hotel_count ; i++){
                       like_hotel_no = $.cookie('like_info' + i + like_user_id);
                       console.log(like_hotel_no);
                       if(like_hotel_no == i ){
                         $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/not_empty_heart.jpg");
                       }else{
                         $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/empty_heart.jpg");
                       }
                     }
                     getAllLike();
                   }
                   location.reload();
                 }
               }); // end ajax()
           }); // end #like_list.onclick.like_delete()
           
           var like_hotel_no;
           for(var i = 0; i <= hotel_count; i++){
             like_hotel_no = $.cookie('like_info' + i + like_user_id);
             console.log(like_hotel_no);
             if(like_hotel_no == i) {
               $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/not_empty_heart.jpg");
             } else {
               $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/empty_heart.jpg");
             }
           }
    } else {
      $("#imgsrc"+like_hotel_no).attr("src","/spring/resources/img/empty_heart.jpg");
    }
    }); // end documnet
</script>
   
</body>
</html>