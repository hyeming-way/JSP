<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//첫번째 appTest1.jsp에서 session과 application 내장객에체 바인딩한 값을
	//현재 appTest2.jsp에서 얻어 사용합시다.
	
	//session에 바인딩된 값 얻기
	String name = (String)session.getAttribute("name");

	//application에 바인딩된 값 얻기
	String address = (String)application.getAttribute("address");
%>    

<h1>이름은 <%=name%>입니다.</h1> 
<h1>주소는 <%=address%>입니다.</h1>
    