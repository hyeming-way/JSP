<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//1.요청한 데이터 인코딩
		request.setCharacterEncoding("UTF-8");
	
		//2.요청한 데이터 얻기
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		//3. 조건에 따라 웹브라우저에 응답할 디자인 출력
		//이름을 입력하고 요청했다면?
		if(name != null || name.length() != 0){
	%>
			<h1><%=name%>, <%=age%><h1>
	<%			
		}else {
	%>
			<h1>이름을 입력하세요.</h1>
	<%		
		}	
	%>
	
	<%-- --------------------------------------------------- --%>
	
	<%
		if(name != null || name.length() != 0){
			
			out.println("<h1>" + name + ", " + age + "</h1>");
			
		} else {
			out.println("<h1>이름을 입력하세요.</h1>");
		}
	%>

</body>
</html>