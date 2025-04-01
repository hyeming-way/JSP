<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//테스트
	//http://localhost:8090/pro12/test02/num.jsp 잘못 입력하여 
	//존재하지않는 num.jsp를 요청하면 404 예외가 발생합니다.
	
	//테스트2
	//http://localhost:8090/pro12/test02/number.jsp를 요청하면
	//500 예외가 발생합니다.
	int num = Integer.parseInt(request.getParameter("num")); 

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>쇼핑몰 중심 JSP 입니다!!!</h1>

</body>
</html>