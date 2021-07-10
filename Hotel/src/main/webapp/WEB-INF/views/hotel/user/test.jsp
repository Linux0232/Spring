<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test </title>
</head>
<body>
<div id="demo"></div> 
<input type="text" id="test" required="required">
<button type="button" onclick="ajaxdemo()">버튼</button>

<script type="text/javascript">


function ajaxdemo() {
	  var userid = document.getElementById('test').value;
	  console.log(userid);
	  const xhttp = new XMLHttpRequest();
	  xhttp.onload = function() {
		console.log("aaa");  
		console.log(this.responseText); //String 
		console.log(this);
		console.log("Ccccc");
	    var list = JSON.parse(this.responseText); //JSON
	    console.log(list);
	    console.log(list[0].hotel_no);
	    console.log("bbbb");
	    for(var i in list){
	    	console.log(i);
	        console.log(list[i].hotel_no);
	       var a = list[i].hotel_no +'\n';
	    document.getElementById("demo").innerHTML += a;
	    }
    }
	  xhttp.open("GET", "../rest/like/test/" + userid, true); //JSON
	  // 1. JSON 데이터를 url로 보내는게 아니라 body controller 보낸다. 
	  // 2. 컨트롤러에서 JSON데이터를 꺼내서 DB에
	  // 0. 먼저 javascript -> ajax 데이터를 보내는 함수 (JSON)
	  xhttp.send();
}


</script>
</body>
</html>