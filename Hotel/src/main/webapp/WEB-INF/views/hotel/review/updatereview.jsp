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
<title>리뷰 수정 페이지</title>
</head>
<body>
	<form action="updatereview" method="post" enctype="multipart/form-data">
		<input type="hidden" name="review_hotel_no" value="${review_vo.review_hotel_no }">
		<input type="hidden" name="review_no" value="${review_vo.review_no }">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="check_in" value="${check_in }">
		<input type="hidden" name="check_out" value="${check_out }">
		<input type="hidden" name="review_user_id" value="${review_vo.review_user_id }">
		<br>
		
		<textarea rows="20" cols="120" name="review_content" id="review_content" placeholder="내용 입력" required="required">${review_vo.review_content }</textarea>
		<br>
		평점 : <select name="review_star" id="review_star">
					<option value="1" ${review_vo.review_star =='1' ? 'selected="selected"' : '' }>1</option>
					<option value="2" ${review_vo.review_star =='2' ? 'selected="selected"' : '' }>2</option>
					<option value="3" ${review_vo.review_star =='3' ? 'selected="selected"' : '' }>3</option>
					<option value="4" ${review_vo.review_star =='4' ? 'selected="selected"' : '' }>4</option>
					<option value="5" ${review_vo.review_star =='5' ? 'selected="selected"' : '' }>5</option>
			 </select><br>
		<!-- 이미지 업로드 -->
		<div id="attach_file"></div>
		<input type="submit" value="등록">
	</form>
	
	<script type="text/javascript">
	 	var review_hotel_no = ${review_vo.review_hotel_no};
		var review_img_file = '${review_vo.review_img_file}';
		var attach = 'none';
		
		var input_file = '첨부파일 : ' + attach
					   + '<input type="file" name="file" id="file"></div>'
					   + '<div id="preview"></div>';
		
		if(review_img_file != 'none'){
			attach = review_hotel_no + '_' + review_img_file;
		}
		var update_file = '첨부파일 : ' + attach
						+ '<input type="button" class="delete_btn" value="삭제"></br>'
						+ '@이미지를 변경하고 싶으시다면? '
						+ '<input type="file" name="file" id="file">'
						+ '<div id="preview"></div>';
		
		if(review_img_file == 'none'){
			$('#attach_file').html(input_file);
		} else {
			$('#attach_file').html(update_file);
		}
		
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
		}); // end preview.onclick(preview_delete)
		
		$('#attach_file').on('click', '.delete_btn', function(){
			console.log(review_img_file);
			var review_no = ${review_vo.review_no};
			var review_content = '${review_vo.review_content}';
			var review_star = ${review_vo.review_star};
			
			$.ajax({
				type : 'put',
				url : '../rest/review/imgdelete',
				headers : {'Content-Type' : 'application/json', 'X-HTTP-Method-Override' : 'PUT'},
				data : JSON.stringify({
					'review_no' : review_no,
					'review_hotel_no' : review_hotel_no,
					'review_img_file' : review_img_file,
					'review_content' : review_content,
					'review_star' : review_star,
				}),
				success : function(){
					location.reload();
				}
			}); // end ajax
		}); // end attach_file.onclick(delete_btn)
	</script>
</body>
</html>