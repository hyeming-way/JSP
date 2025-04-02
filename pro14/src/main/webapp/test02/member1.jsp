<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");

	//각 내장객체 메모리 영역에 바인딩된 속성(키)에 대한 값들을 getAttribute("키");메소드를 호출해서 얻기
	String id = (String)request.getAttribute("id");
	String pwd = (String)request.getAttribute("pwd");
	
	String name = (String)session.getAttribute("name");
	
	String email = (String)application.getAttribute("email");

%>    
    
<table width=100% align="center">
	<tr align="center" bgcolor="green">
		<td width="7%">아이디</td>
		<td width="7%">비밀번호</td>
		<td width="5%">이름</td>
		<td width="11%">이메일</td>
	</tr>
	
	<%-- 표현식으로 위 자바코드로 얻은 변수값들 출력 --%>
	<tr align="center">
		<td width="7%"><%=id%></td>
		<td width="7%"><%=pwd%></td>
		<td width="5%"><%=name%></td>
		<td width="11%"><%=email%></td>
	</tr>	
	
	<%-- 자바코드 없이 바로 각 내장객체 메모리에 바인딩된 키의 값들을 얻어 출력 --%>
	<tr align="center">
		<td width="7%">${requestScope.id}</td>
		<td width="7%">${requestScope.pwd}</td>
		<td width="5%">${sessionScope.name}</td>
		<td width="11%">${applicationScope.email}</td>
	</tr>	

</table> 