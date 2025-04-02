<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 중에서 core라이브러리에 속한 태그를 불러와서 사용하기 위해 taglib 디렉티브 태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

<%-- HashMap 배열 생성 한쌍의 형태로 묶어서 저장시키는 배열 메모리 --%>    
<jsp:useBean id = "membersMap" class="java.util.HashMap"/>

<%-- ArrayList 배열 생성 --%>    
<jsp:useBean id="membersList" class="java.util.ArrayList" />
    
<%
	membersList.add( new MemberVO("ki","4321","기성용","ki@test.com") );
	membersList.add( new MemberVO("son","1234","손흥민","son@test.com") );

	//HashMap에 key, value를 한쌍의 형태로 묶어 박지성에 대한 정보 저장
	membersMap.put("id", "park2");
	membersMap.put("pwd", "4321");
	membersMap.put("name", "박지성");
	membersMap.put("email", "park2@test.com");

	//HashMap에 key,value 한쌍의 형태로 묶어 위 VO객체 2개를 저장하고 있는 ArrayList배열 자체를 저장
	membersMap.put("List", membersList);

%>   
<%-- HashMap에 저장된 ArrayList배열을 꺼내 변수에 저장 --%>
<c:set var="memberslist" value="${membersMap.List}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="green">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
		</tr>
		
<%-- HashMap에 저장된 박지성에 대한 정보를 얻어 출력 --%>		
		<tr align="center">
			<td>${membersMap.id}</td>
			<td>${membersMap.pwd}</td>
			<td>${membersMap.name}</td>
			<td>${membersMap.email}</td>
		</tr>
		
<%-- ArrayList를 저장한 변수를 불러와 값 출력 --%>		
		<tr align="center">
			<td>${memberslist[0].id}</td>
			<td>${memberslist[0].pwd}</td>
			<td>${memberslist[0].name}</td>
			<td>${memberslist[0].email}</td>
		</tr>
		
		<tr align="center">
			<td>${memberslist[1].id}</td>
			<td>${memberslist[1].pwd}</td>
			<td>${memberslist[1].name}</td>
			<td>${memberslist[1].email}</td>
		</tr>
		
		
		
	</table>
</body>
</html>