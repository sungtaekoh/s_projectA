<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	        $('#preview').attr('src', e.target.result);
          }
      }
  }  
</script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:import url="../default/header.jsp" />
<div style="width:300px; margin: 0 auto;">
<form action="${contextPath}/board/modify" enctype="multipart/form-data" method="post" >
	<input type="hidden" name="writeNo" value="${personalData.writeNo}">
	<input type="hidden" name="originFileName" value="${personalData.imageFileName}">
	제목	<input type="text" size="30" name="title" value="${personalData.title}"><hr>
	내용	<textarea rows="5" cols="30" name="content">${personalData.content}</textarea>
	<hr>
	<c:if test="${ personalData.imageFileName != 'nan' }">
		<img width="200px" height="100px" id="preview"
			src="${contextPath}/board/download?imageFileName=${personalData.imageFileName}">
	</c:if>
	<input type="file" name="imageFileName" onchange="readURL(this)">
	<hr>
	<input type="submit" value="수정하기">
	<input type="button" onclick="history.back()" value="이전으로 돌아가기">
</form>
</div>
<c:import url="../default/footer.jsp" />
</body>
</html>





