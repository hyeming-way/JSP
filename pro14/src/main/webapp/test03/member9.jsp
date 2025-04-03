<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 중에서 core 라이브러리에 속한 태그를 불러와서 사용하기 위해 taglib 디렉티브 태그 작성 --%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>   

<table align="center" width="100%">
	<tr align="center" bgcolor="green">
		<td width="7%">아이디</td>
		<td width="7%">비밀번호</td>
		<td width="5%">이름</td>
		<td width="11%">이메일</td>
	</tr>

<c:choose>
	<%--아이디를 입력하지 않고 요청했다면? --%>
	<c:when test="${empty param.id}"> <%-- request.getParameter("id") --%>
		<tr align="center">
			<td colspan="4">아이디를 입력하세요!</td>
		</tr>
	</c:when>
	<c:otherwise> <%-- 아이디를 입력하고 요청했다면? --%>
		<tr align="center">
		<td width="7%"><c:out value="${param.id}"/></td>
		<td width="7%"><c:out value="${param.pwd}"/></td>
		<td width="5%"><c:out value="${param.name}"/></td>
		<td width="11%"><c:out value="${param.email}"/></td>
	</tr>
	</c:otherwise>

</c:choose>

</table>
