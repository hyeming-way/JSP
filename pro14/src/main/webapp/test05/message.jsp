<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL의 core, fmt 라이브러리 태그들 사용하기위해 요청 주소 작성 --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% request.setCharacterEncoding("UTF-8"); %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 다국어 기능</title>
</head>
<body>

<%--
	실습 내용
	- <fmt:setLocale>태그를 이용해 표시할 언어를 지정한 후
	  <fmt:bundle>태그를 이용해 resource 패키지의 프로퍼티 파일을 읽어옵니다.
	  그리고 <fmt:message>태그를 이용해 프로퍼티 파일의 키(key)에 대한 값을 각각 출력합니다.
--%>

	<%-- 언어(locale)을 영어로 지정합니다. --%>
<%-- 	<fmt:setLocale value="en_US"/> --%>
	
	<%-- 언어(locale)을 한국어로 지정합니다. --%>
	<fmt:setLocale value="ko_KR"/>
	
	<h1>
		회원정보<br><br>
		<%-- <fmt:message>태그의 key 속성의 프로퍼티 파일의 key를 지정하여 값(value) --%>
		<fmt:bundle basename="resource.member">
		
			<%-- resource 패키지 아래 member 프로퍼티 파일을 읽어옵니다. --%>
			이름 : <fmt:message key="mem.name"/> <br>
			주소 : <fmt:message key="mem.address"/> <br>
			직업 : <fmt:message key="mem.job"/> <br>
		
		</fmt:bundle>
	
	
	</h1>
	
	




</body>
</html>