<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<%
//1. 요청한 데이터 request에 UTF-8설정
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- t_member 테이블에서 조회된 모든 회원정보를 표의 목록형태로 출력 --%>
	<table width="100%" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아 이 디</td>
			<td width="7%">비밀번호</td>
			<td width="7%">이   름</td>
			<td width="7%">이 메 일</td>
			<td width="7%">가 입 일</td>
		</tr>
<c:choose>
	<%-- 조회된 정보들이 ArrayList배열에 저장되어 있지 않으면? --%>
	<c:when test="${empty requestScope.list}">
		<tr align="center">
			<td colspan="5">등록된 회원이 없습니다.</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach var="membervo" items="${list}">
			<tr align="center">
				<td width="7%">${membervo.id}</td>
				<td width="7%">${membervo.pwd}</td>
				<td width="7%">${membervo.name}</td>
				<td width="7%">${membervo.email}</td>
				<td width="7%">${membervo.joinDate}</td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>

		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr>
	</table>
</body>
</html>