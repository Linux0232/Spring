<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
.thumb {
    width: 100px;
    height: 100px;
}
</style>
<title>리뷰 작성 페이지</title>
</head>
<body>
	<form action="insertreview" method="post" enctype="multipart/form-data">
		<input type="hidden" name="review_hotel_no" value="${reservation_vo.hotel_vo.hotel_no }">
		<input type="hidden" name="review_room_no" value="${reservation_vo.room_vo.room_no }">
		<input type="hidden" name="review_reservation_no" value="${reservation_no }">
		<input type="hidden" name="review_user_id" value="${review_user_id }">
		<br>
		
		<textarea rows="20" cols="120" name="review_content" id="review_content" placeholder="내용 입력" required="required"></textarea>
		<br>
		평점 : <select name="review_star" id="review_star">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
			 </select>
		<!-- 이미지 업로드 -->
		<!-- <input type="file" name="file"> -->
		<input type="file" name="file" id="file">
		<div id="preview"></div>
		<input type="submit" value="등록">
	</form>
	
	<script type="text/javascript">
		console.log()
		function handleFileSelect(event) {
		    var input = this;
		    console.log(input.files)
		    if (input.files && input.files.length) {
		        var reader = new FileReader();
		        this.enabled = false
		        reader.onload = (function (e) {
		        	console.log(e)
		        	var preview = ['<img class="thumb" src="', e.target.result, '" title="', escape(e.name), '"/>'].join('')
		        					 + '<a href="#" class="preview_delete">삭제</a>';
		        	$("#preview").html(preview)
		        });
		        reader.readAsDataURL(input.files[0]);
		    }
		}
		$('#file').change(handleFileSelect);
		$('#preview').on('click', '.preview_delete', function(){
			$("#preview").empty();
		    $("#file").val("");
		});
	</script>
</body>
</html>