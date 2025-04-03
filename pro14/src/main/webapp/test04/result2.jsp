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
		
		<%-- 입력한 아이디가 관리자 아이디(admin)를 입력했을 경우? --%>
		<c:if test="${param.userID eq 'admin'}">		
			<h1>관리자 계정 admin으로 로그인했습니다.</h1>		
			<form>
				<button type="button">회원정보 삭제</button>
				<button type="button">회원정보 수정</button>
			</form>	
		</c:if>	
		
		<%-- 입력한 아이디가 admin이 아닌 일반 사용자 계정으로 입력했다면? --%>		
		<c:if test="${param.userID ne 'admin'}">
			<h1>일반계정 ${param.userID}님 로그인했습니다!</h1>
		</c:if>
		
	</c:if>

</body>
</html>