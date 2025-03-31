<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>안녕하세요. 쇼핑몰 상단 영역입니다.</h1>
	
	
<%--include 디렉티브 태그를 이용해 duke_imge.jsp에 작성한 코드 내용을 현재 위치로 불러와
현재 위치의 행 위치에 포함시킴 --%>	
	
<%-- 	<%@ include file="" %> 
		주석처리 단축키 Ctrl + Shift + c --%>
	<%@ include file="duke_imge.jsp" %>
	
	<h2>안녕하세요. 쇼핑몰 하단 영역입니다.</h2>


</body>
</html>