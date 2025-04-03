<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="result.jsp" method="post">
		아이디 : <input type="text" name="userID"><br>
		비밀번호 : <input type="password" name="userPW"><br>
		
		<!-- 전송 요청 버튼으로 만들 수 있는 경우 -->
		<input type="submit" value="로그인">
<!-- 		<button>로그인</button> -->
<!-- 		<input type="image" src="s.png"> -->

		<input type="reset" value="다시입력">
	</form>
</body>
</html>