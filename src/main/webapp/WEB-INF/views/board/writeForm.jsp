<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function readURL(input) {
	   var file = input.files[0] //파일에 대한 정보
	   console.log(file)
      if (file != '') {
	      var reader = new FileReader();
	      reader.readAsDataURL(file); //파일의 정보를 토대로 파일을 읽고 
	      reader.onload = function (e) { // 파일 로드한 값을 표현한다
	    	//e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
	    	console.log(e.target)
	    	console.log(e.target.result)
	        $('#preview').attr('src', e.target.result);
          }
      }
  }  
</script>

</head>
<body>
<c:import url="../default/header.jsp" />
<div id="wrap" style="width: 400px; margin: 0 auto; ">
	<h1 style="text-align: center">글쓰기</h1>
	<form method="post" action="${contextPath}/board/writeSave" 
											enctype="multipart/form-data">
		<b>작성자</b><br>	<!-- readonly : 읽기 전용 -->
		<input type="text" name="id" value="${loginUser }" readonly />
		<hr>
		<b>제목</b> <br> <input type="text" size="50" name="title" /><hr>
		 <b>내용</b> <br>
		<textarea name="content" rows="10" cols="50"></textarea><hr>
		<b>이미지파일 첨부</b><br>
		<input type="file" name="image_file_name" onchange="readURL(this);" /> 
		<img id="preview" src="#" width=100 height=100 alt="선택된 이미지가 없습니다" />
		<hr> 
		<input type="submit" value="글쓰기" />
		<input type=button value="목록보기" 
					onClick="location.href='${contextPath}/board/boardAllList'">
	</form>
</div>
<c:import url="../default/footer.jsp" />
</body>
</html>

