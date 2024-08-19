<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>삭제 확인</title>
</head>
<body>
    <!-- 폼에 action 및 method 추가 -->
    <form method="post" action="/GuestBook_jstl/guests">
        <table>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="pw"  required></td>
                <!-- num 필드를 hidden으로 설정하고 guestbookVo 객체에서 값을 가져옴 -->
                <td><input type="hidden" name="num" value= ${param.num} ></td>
                <td><button type="submit" name="action" value="deleteInfo">삭제</button></td>
            </tr>
        </table>
    </form>
    
    <br><br>
    <!-- /WEB-INF/ 경로는 직접 접근할 수 없으므로 수정 -->
    <a href="/WEB-INF/addPeople.jsp">메인으로 돌아가기</a>
</body>
</html>
