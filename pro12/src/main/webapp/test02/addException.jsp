<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    isErrorPage="true"
    %>
    <%-- 다른 JSP(add.jsp)에서 예외 발생시 예외를 처리하는 예외처리 서버페이지로 설정하기위한 속성 --%>
    
    ===================== exception 내장객체의 toString() 호출 내용 ===================== <br>
    
    <h1><%=exception.toString()%></h1> <%-- 발생한 예외 메세지 출력 --%>
    
    ===================== exception 내장객체의 getMessage() 호출 내용 ===================== <br>
    
    <h1><%=exception.getMessage()%></h1> 
    
    ===================== exception 내장객체의 printStackTrace() 호출 내용 ===================== <br>
	
	<h1><% exception.printStackTrace(); %></h1> <%-- 콘솔창에 오류메세지 출력 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>
		자연수 숫자만 입력가능합니다. 다시 입력해 주세요.
		<a href="add.html">다시 입력하러가기</a>
	</h3>
</body>
</html>