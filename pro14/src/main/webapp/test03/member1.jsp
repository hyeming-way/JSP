<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL의 core라이브러리의 태그들을 사용하기위해 외부에서 불러옴 
	 prefix="c" <---- 접두어 설정
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<%
	//클라이언트가 member1.jsp요청주소를 입력하여 요청하면 현재 화면을 웹브라우저에 보여줍니다.
	
	//한글처리
	request.setCharacterEncoding("UTF-8");
%>    

<%--	
	변수선언시 사용되는 태그
	
		<c:set var="선언할변수명작성" value="변수에저장할값" scope="선언한변수를바인딩할내장객체"/>
--%>
<c:set var="id" value="hong" scope="page" />
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'홍길동'}" scope="page"/>
<c:set var="age" value="${22}" scope="page"/>
<c:set var="height" value="${177}" scope="page"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>	
			<td width="7%"><b>비밀번호</b></td>	
			<td width="7%"><b>이름</b></td>	
			<td width="7%"><b>나이</b></td>	
			<td width="7%"><b>키</b></td>	
		</tr>
		
		<%-- EL 표현언어로 변수에 저장된 값을 얻어 출력합니다. --%>
		<tr align="center">
			<td>${id}</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${age}</td>
			<td>${height}</td>
		</tr>
	</table>
</body>
</html>