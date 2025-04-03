<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  

<% request.setCharacterEncoding("UTF-8"); %>  

<%--
	<fmt:setLocale> 태그
	- 국나별로 다른 통화나 기호나 날짜를 표현할때 사용하는 태그
 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>로케일 설정</h4>
	
	<c:set var="today" value="<%=new Date()%>" scope="page" />
	
	한글로 설정 : <fmt:setLocale value="ko_KR"/>
	<fmt:formatNumber value="10000" type="currency"/><br>
	<%-- 한글로 설정 : ₩10,000 --%>
	<fmt:formatDate value="${today}"/>
	<%-- 2025. 4. 3. --%>
	
	<hr>
	
	일어로 설정 : <fmt:setLocale value="ja_JP"/>
	<fmt:formatNumber value="10000" type="currency"/><br>
	<%-- 일어로 설정 : ￥10,000 --%>
	<fmt:formatDate value="${today}"/>
	<%-- 2025/04/03 --%>
	
	<hr>
	
	영어로 설정 : <fmt:setLocale value="en_US"/>
	<fmt:formatNumber value="10000" type="currency"/><br>
	<%-- 영어로 설정 : $10,000.00 --%>
	<fmt:formatDate value="${today}"/>
	<%-- Apr 3, 2025 --%>
	

</body>
</html>