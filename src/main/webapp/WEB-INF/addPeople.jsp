<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form>
		<table border="1" width="540px">
			<tr>
				<td>이름</td><td><input type="name" name="name"></td>
				<td>비밀번호</td><td><input type="pw" name="pw"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="72" rows="5" name = "comments"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit" name = "action" value = "register" >등록</button></td>
			</tr>
		</table>
	</form>
	<br>

	<c:forEach items = "${requestScope.guestList}" var = "guestVo" begon = "0" end = "3" step = "1">
	<table border="1" width="540px">
		<tr>
			<td>아이디: ${guestVo.personId}</td>
			<td>이름: ${guestVo.name} </td>
			<td>날짜: 2022-01-01</td>
			<td><a href="/GuestBook_ElJstl/guests?action=delete&num=${guestVo.personId}">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4">${guestVo.comments}</td>
		</tr>
	</table>
	<br>
	</c:forEach>
	

	
	<br>
</body>
</html>