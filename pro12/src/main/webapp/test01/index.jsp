<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>welcome to my homepage Main</h1>
	
	<%
		//1. login.jsp페이지에서 포워딩해서 유지중인 HttpSession 내장객체메모리에 접근하여 
		//바인딩된 아이디 얻기
		String new_id = (String)session.getAttribute("id");
	
		//2. HttpSession 내장객체메모리 영역에 입력한 아이디가 저장되어 있으면?
		//	 -> 로그인 된 디자인 화면을 보여주고
		//	 HttpSession 내장객체메모리 영역에 입력한 아이디가 저장되어 있지 않으면?
		//	 -> 미로그인 된 디자인 화면을 보여주자
		
		//2-1. HttpSession 내장객체메모리 영역에 입력한 아이디가 저장되어있으면?
		if(new_id != null){
	%>
			<%=new_id%> 로그인 중.... &nbsp;&nbsp; <!-- &nbsp; 스페이스바 효과 -->
			<a href="logout.jsp">logout</a>
			<a href="shop.jsp">쇼핑몰</a> <!-- 로그인된 상태에서 서브페이지 요청 -->
	
	<%					
		} else { //2-2. HttpSession 내장객체메모리 영역에 입력한 아이디가 저장되어있지않으면?
	%>
			<a href="login.jsp">login</a> <!-- 로그인 요청하는 화면 요청 -->
			<a href="shop.jsp">쇼핑몰</a> <!-- 미로그인 상태에서 서브페이지 요청 -->
	<%		
		}		
	%>
	
</body>
</html>