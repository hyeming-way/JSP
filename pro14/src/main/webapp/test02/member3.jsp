<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
%>    
    
<table width=100% align="center">
	<tr align="center" bgcolor="green">
		<td width="7%">아이디</td>
		<td width="7%">비밀번호</td>
		<td width="5%">이름</td>
		<td width="11%">이메일</td>
	</tr>

<%-- EL태그에서 request내장객체 영역에 바인딩된 ArrayList배열을 얻고 
배열의 0 index 위치에 추가된 VO객체를 얻어 각 변수값 출력 --%>
	<tr align="center">
		<td width="7%">${list[0].id}</td>
		<td width="7%">${list[0].pwd}</td>
		<td width="5%">${list[0].name}</td>
		<td width="11%">${list[0].email}</td>
	</tr>
	
	<tr align="center">
		<td width="7%">${list[1].id}</td>
		<td width="7%">${list[1].pwd}</td>
		<td width="5%">${list[1].name}</td>
		<td width="11%">${list[1].email}</td>
	</tr>

</table> 