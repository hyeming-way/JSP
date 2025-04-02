<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//한글처리
	request.setCharacterEncoding("UTF-8");

	//sec01.ex01패키지의 MemberVO 클래스의 객체 생성
	MemberVO memberVO = new MemberVO("lee","1234","이순신","lee@test.com");
	
	//위 생성한 객체를 request 내장객체 메모리에 바인딩
	request.setAttribute("member", memberVO);
	
%>    

<%-- member2.jsp 디스패처 방식으로 포워딩 --%>
<jsp:forward page="member2.jsp"/>    
    
    
    