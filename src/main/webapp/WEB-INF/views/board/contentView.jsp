<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#modal_wrap {
	display: none;
	position: fixed;
	z-index: 9;
	margin: 0 auto;
	top: 0;
	left: 0;
	right: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

#first {
	position: fixed;
	z-index: 10;
	margin: 0 auto;
	top: 30px;
	left: 0;
	right: 0;
	display: none;
	background-color: rgba(212, 244, 250, 0.9);
	height: 350px;
	width: 300px;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function replyData(){
	$.ajax({
		url:"replyData/"+${personalData.writeNo}, type:"GET", 
		dataType:"json",
		success: function(rep){
			let html = ""
			rep.forEach(function(data){
				let date = new Date(data.write_date)
				let writeDate = date.getFullYear()+"년"+(date.getMonth()+1)+"월"
				writeDate += date.getDate()+"일"+date.getHours()+"시"
				writeDate += date.getMinutes()+"분"+date.getSeconds()+"초"
				html += "<div align='left'><b>아이디 : </b>"+data.id+"님 / ";
				html += "<b>작성일</b> : "+writeDate+"<br>"
				html += "<b>제목</b> : "+data.title+"<br>"
				html += "<b>내용</b> : "+data.content+"<hr></div>"
			})
			$("#reply").html(html)
		},error:function(){
			alert('데이터를 가져올 수 없습니다')
		}
	})
}


	function slideClick() {
		$("#first").slideDown('slow');
		$("#modal_wrap").show();
	}
	function slide_hide() {
		$("#first").slideUp('fast');
		$("#modal_wrap").hide();
	}
	function rep() {
		let form = {};
		let arr = $("#frm").serializeArray()
		console.log(arr)
		for (i = 0; i < arr.length; i++) {
			form[arr[i].name] = arr[i].value;
		}
		$.ajax({
			url : "addReply",
			type : "POST",
			dataType : "json",
			data : JSON.stringify(form),
			contentType : "application/json;charset=utf-8",
			success : function(list) {
				$("#title").val("")
				$("#content").val("")
				
				alert("성공적으로 답글이 달렸습니다.");
				slide_hide();
				replyData();
			},
			error : function() {
				alert("문제 발생!")
			}
		});
	}
</script>
</head>
<body onload="replyData()">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id="modal_wrap">
		<div id="first">
			<div style="width: 250px; margin: 0 auto; padding-top: 20px;">
				<form id="frm">
					<input type="hidden" name="write_no" value="${personalData.writeNo}"> 
					<b>답글 작성 페이지</b>
					<hr>
					<b>작성자 : ${loginUser}</b>
					<hr>
					<b>제목</b><br>
					<input type="text" id="title" size="30" name="title">
					<hr>
					<b>내용</b><br>
					<textarea name="content" id="content" rows="5" cols="30"></textarea>
					<hr>
					<button type="button" onclick="rep()">답글</button>
					<button type="button" onclick="slide_hide()">취소</button>
				</form>
			</div>
		</div>
	</div>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<c:import url="../default/header.jsp" />
	<table border="1" align="center">
		<caption>
			<font size="5"><b>개인 정보</b></font>
		</caption>
		<tr>
			<th width="100">글 번호</th>
			<td width="200">${personalData.writeNo}</td>
			<th width="100">작성자</th>
			<td width="200">${personalData.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${personalData.title}</td>
			<th>등록일자</th>
			<td>${personalData.saveDate}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${personalData.content}</td>
			<td colspan="2"><c:if
					test="${ personalData.imageFileName != 'nan' }">
					<img width="200px" height="100px"
						src="${contextPath}/board/download?imageFileName=${personalData.imageFileName}">
				</c:if></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><c:if
					test="${ loginUser == personalData.id }">
					<input type="button" value="수정하기"
						onclick="location.href='${contextPath}/board/modify_form?writeNo=${personalData.writeNo}'">
					<input type="button" value="삭제하기"
						onclick="location.href='${contextPath}/board/delete?writeNo=${personalData.writeNo}&imageFileName=${personalData.imageFileName}'">
				</c:if> 
				<input type="button" onclick="slideClick()" value="답글달기">
				<input	type="button" onclick="" value="리스트로 돌아가기">
				
				<hr>
				<div id="reply"></div>
				
				</td>
				
		</tr>
	</table>
	<c:import url="../default/footer.jsp" />

</body>
</html>



