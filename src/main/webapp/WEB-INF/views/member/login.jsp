<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:import url="../default/header.jsp" />
	<div align="center">
		<h1>로그인 페이지 입니다</h1>
	</div>
	<div align="right">
		<form action="<%=request.getContextPath() %>/member/user_check" method="post">
			<table>
				<tr>
					<td><input type="text" name="id" placeholder="아이디"></td>
					<td rowspan="2"><input type="submit" value="로그인"
						style="width: 60px; height: 55px;"></td>
				</tr>
				<tr>
					<td><input type="text" name="pw" placeholder="비밀번호"></td>
				</tr>
				<tr>
					<td colspan="2" align="left">
						<a href="register_form">회원가입</a>
						<input type="checkbox" name="autoLogin">로그인 유지
					</td>
				</tr>
			</table>
		</form>
	</div>
	<c:import url="../default/footer.jsp" />

</body>
</html>