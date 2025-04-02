<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//1. 인코딩 (컴퓨터가 한글문자를 처리할 수 있는 방식) 설정
	request.setCharacterEncoding("UTF-8");
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
			<td width="20%">주소</td>
		</tr>

		<tr align="center">
<%--
	EL에서 제공해주는 param 내장객체명을 이용해 request 내장객체메모리에 접근하여
	getParameter메소드를 이용하지않고 바로 회원정보를 얻어 출력
	
	참고. ${ param.<input>태그의name속성값 작성 }
--%>	
			<td>${param.id}</td>		
			<td>${param.pwd}</td>		
			<td>${param.name}</td>		
			<td>${param.email}</td>
			
<%--
	forward.jsp에서 request.setAttribute("address", "서울시 강남구");
	수동으로 바인딩해놓은 request 내장객체메모리에 접근할때
	EL에서 제공해주는 requestScope 내장객체 이름으로 접근하여 얻는다
--%>					
			<td>${requestScope.address}</td>
			
<%--			
	자바코드로 작성할 경우 아래와 같음
	
			<td>
			<% 
				Object obj = request.getAttribute("address");
				String data = (String)obj;
				out.print(data);
			%>
			</td>
 --%>			
		</tr>
				
		<tr height="1" bgcolor="green">
			<td colspan="5"></td>
		</tr>	
	</table>

</body>
</html>