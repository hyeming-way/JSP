<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	//request1.jsp에서 request에 바인딩(저장)한 데이터를 
	//공유받아 사용할 request2.jsp 서버페이지 입니다
	
	//"이순신"
	String name = (String)request.getAttribute("name");
	//"서울시 강남구"
	String address = (String)request.getAttribute("address");
%>    

<h1>이름은 <%=name%>입니다.</h1>
<h1>주소는 <%=address%>입니다.</h1>

