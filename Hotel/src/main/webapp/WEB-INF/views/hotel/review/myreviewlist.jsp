<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<style type="text/css">
table, th, td {
   border-style: solid;
   border-width : 1px;
   text-align : center;
}

ul {
   list-style-type: none;
}

li {
   display: inline-block;
}
</style>
<title>나의 리뷰 목록</title>
</head>
<body>
	<a href="../main">main</a>
	<a href="../reservation/reservation">my page</a>
	<h1>${user_id }님의 작성된 리뷰 정보입니다.</h1>
   	<hr>
   	
   	<table>
      <thead>
         <tr>
            <th style="width: 150px;">리뷰 사진</th>
            <th style="width: 300px;">호텔이름</th>
            <th style="width: 300px;">리뷰내용</th>
            <th style="width: 200px;">작성날짜</th>
            <th style="width: 200px;">평점</th>
            <th style="width: 300px;">체크인/체크아웃</th>
            <th style="width: 200px;">룸타입</th>
            <th style="width: 130px;">리뷰수정</th>
            <th style="width: 130px;">리뷰삭제</th>
         </tr>      
      </thead>
      
      <tbody>
      <c:forEach var="review_vo" items="${list }">
         <tr>
          <td><img src="../rest/review/display?file_name=${review_vo.hotel_vo.hotel_no }_${review_vo.review_img_file}" onerror="this.style.display='none';" width="200"/></td>
          <td>${review_vo.hotel_vo.hotel_name }</td>
          <td>${review_vo.review_content }</td>
          <td>${review_vo.review_date }</td>
          <td>${review_vo.review_star }</td>
          <td>${review_vo.reservation_vo.reservation_check_in } ~ ${review_vo.reservation_vo.reservation_check_out }</td>
          <td>${review_vo.room_vo.room_name }</td>
          <td><a href="updatereview?review_no=${review_vo.review_no }&review_hotel_no=${review_vo.hotel_vo.hotel_no}"><input type="button" value="수정"></a></td>
          <td><a href="myreviewdelete?review_no=${review_vo.review_no }&review_hotel_no=${review_vo.hotel_vo.hotel_no }&user_id=${user_id }"><input type="button" value="삭제"></a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    
	<input type="hidden" name="msg" id="msg" value=${msg }>

	<!-- 삭제 alert -->
	<script type="text/javascript">
		var msg = $('#msg').val(); 
		if(msg != ''){
		   alert(msg);
		}
	</script>
</body>
</html>