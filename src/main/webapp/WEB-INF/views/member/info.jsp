<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../default/header.jsp"/>
<div align="center">
    <table border="0" style="width:300px;">
	<caption><font size="5">${info.id } 정 보</font></caption>
	<tr><th>아이디</th><th>${info.id }</th></tr>
	<tr><th>비밀번호</th><th>${info.pw }</th></tr>
	<tr><th>주소</th><th>${info.addr }</th></tr>
    </table>
</div>
<c:import url="../default/footer.jsp"/>

</body>
</html>