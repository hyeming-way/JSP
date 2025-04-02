<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//한글처리
	request.setCharacterEncoding("UTF-8");

	//request 내장객체 메모리 영역에 수동으로 바인딩
	request.setAttribute("id", "hong");
	request.setAttribute("pwd", "1234");
	
	//session 내장객체 메모리 영역에 수동으로 바인딩
	session.setAttribute("name", "홍길동");
	
	//application 내장객체 메모리 영역에 수동으로 바인딩
	application.setAttribute("email", "hong@test.com");
	
	//member1.jsp 디스패처 방식으로 포워딩
	RequestDispatcher dispatcher = request.getRequestDispatcher("member1.jsp");
	dispatcher.forward(request, response);
%>    
    