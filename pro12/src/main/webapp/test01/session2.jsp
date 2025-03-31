<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	/*
		session1.jsp에서 <a>태그를 클릭하여 요청받은 session2.jsp입니다.
		당연히 같은 브라우저를 이용했기때문에 session1.jsp에서 사용했던 HttpSession 객체메모리는
		session2.jsp페이지에서 공유받아 사용할 수 있습니다.
	*/
	
	//HttpSession 내장 객체 메모리에 접근해서 이름이 name인 "이순신"값을 꺼내오자
	String name = (String)session.getAttribute("name");
	
	//HttpSession 내장 객체 메모리에 접근해서 이름이 address인 "서울시 강남구" 값을 꺼내오자
	String address = (String)session.getAttribute("address");
%>

이름은 <%=name%>입니다. <br>
주소는 <%=address%>입니다. <br>

