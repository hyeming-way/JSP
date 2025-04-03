<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 중에서 core 라이브러리에 속한 태그를 불러와서 사용하기 위해 taglib 디렉티브 태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<%
	request.setCharacterEncoding("UTF-8");

	List membersList = new ArrayList();

	membersList.add( new MemberVO("ki","4321","기성용","ki@test.com") );
	membersList.add( new MemberVO("son","1234","손흥민","son@test.com") );
	membersList.add( new MemberVO("park","1212","박지성","park@test.com") );	
%>   

<c:set var="list" value="<%=membersList%>"/>


<table align="center" width="100%">
	<tr align="center" bgcolor="green">
		<td width="7%">아이디</td>
		<td width="7%">비밀번호</td>
		<td width="5%">이름</td>
		<td width="11%">이메일</td>
	</tr>

<c:forEach var="membervo" items="${list}" >
		<tr align="center" bgcolor="lightgreen">
		<td>${membervo.id}</td>
		<td>${membervo.pwd}</td>
		<td>${membervo.name}</td>
		<td>${membervo.email}</td>
	</tr>
</c:forEach>

<c:forEach var="i" begin="0" end="${list.size()-1}" step="1">
	<tr align="center">
		<td>${list[i].id}</td>
		<td>${list[i].pwd}</td>
		<td>${list[i].name}</td>
		<td>${list[i].email}</td>
	</tr>
</c:forEach>

</table>
