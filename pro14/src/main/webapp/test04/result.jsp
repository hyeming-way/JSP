<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- 입력한 아이디를 request 내장객체영역에서 얻어 그 얻은 아이디가 존재하지 않으면? --%>
	<c:if test="${empty param.userID}">
		아이디를 입력하세요. <br>
		<a href="login.jsp">로그인 하러가기</a>
	</c:if>

	
	<%-- 아이디를 입력했을 경우? --%>
	<c:if test="${!empty param.userID}">
		<h1>환영합니다. <c:out value="${param.userID}"/>님!!</h1>
	</c:if>

</body>
</html>