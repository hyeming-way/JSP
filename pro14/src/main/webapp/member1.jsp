<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//1. 인코딩 (컴퓨터가 한글문자를 처리할 수 있는 방식) 설정
	request.setCharacterEncoding("UTF-8");
	
	//2. 요청한 값 얻기
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	//참고. request.getParameter는 무조건 String으로 반환함. int형도 String으로 반환
%>    
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width=100% align="center">
		<tr align="center" bgcolor="green">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
		</tr>
		
<%-- 자바코드를 작성하면 request.getParameter메소드를 호출해서 가져온 요청값들을 표현식으로 출력 해야합니다. --%>		
		<tr align="center">
			<td><%=id%></td>
			<td><%=pwd%></td>
			<td><%=name%></td>
			<td><%=email%></td>
		</tr>
<%--
	EL에서 제공해주는 param 내장객체명을 이용해 request 내장객체메모리에 접근하여
	getParameter메소드를 이용하지않고 바로 회원정보를 얻어 출력
	
	참고. ${ param.<input>태그의name속성값 작성 }
--%>		
		<tr align="center">
			<td>${param.id}</td>		
			<td>${param.pwd}</td>		
			<td>${param.name}</td>		
			<td>${param.email}</td>		
		</tr>
				
		<tr height="1" bgcolor="green">
			<td colspan="5"></td>
		</tr>	
	</table>

</body>
</html>