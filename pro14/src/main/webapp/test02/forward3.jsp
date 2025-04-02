<%@page import="sec01.ex01.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");

	List membersList = new ArrayList();
	
	membersList.add( new MemberVO("lee","1234","이순신","lee@test.com") );
	membersList.add( new MemberVO("son","1234","손흥민","son@test.com") );

	//request 내장객체 메모리 영역에 ArrayList배열 바인딩
	request.setAttribute("list", membersList);
	
%>    
<%-- member3.jsp 포워딩 --%>
<jsp:forward page="member3.jsp"/>
    