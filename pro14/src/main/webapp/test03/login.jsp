<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL의 core라이브러리의 태그들 사용하기 위한 요청 --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<%-- 
	<c:set>태그를 이용해 pageContext내장객체의 컨텍스트 주소를 변수 contextPath에 미리 저장합니다.
--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<a href="${contextPath}/test03/memberForm.jsp">회원등록하러가기</a>
<br>

<%
	//자바문법으로 컨텍스트 주소 찾기
	String contextPath2 = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인창</title>
</head>
<body>
	<form action="result.jsp">
		아이디 : <input type="text" size=20/> <br>
		비밀번호 : <input type="password" size=20/> <br>
		<input type="submit" value="로그인">
		<input type="reset" value="다시입력">
	</form>
	
</body>
</html>