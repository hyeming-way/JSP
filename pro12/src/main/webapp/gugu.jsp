<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//순서1. 요청한 데이터 인코딩 설정
	request.setCharacterEncoding("UTF-8");

	//순서2. 요청한 데이터 얻기
	//구구단 연산을 위해 숫자로 변환해서 변수에 저장
	int dan = Integer.parseInt(request.getParameter("dan"));	
%>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="800">
		<tr bgcolor="yellow" align="center">
			<td colspan="2"><%=dan%>단 출력</td>
		</tr>
<%
		for(int i=1; i<10; i++){
%>
			<tr align="center">
				<td width="400"><%=dan%>*<%=i%></td>
				<td width="400"><%=dan*i%></td>
			</tr>			
<%	
		}
%>
	</table>
</body>
</html>