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
	
	<%-- 디스패처 방식으로 포워딩 당한 member2.jsp에서는 request 내장객체를 공유받아 사용합니다. 
	request 내장객체에 바인딩된 MemberVO객체의 각 변수값을 얻어 EL로 출력 
	
	참고. requestScope. 생략 가능 
	--%>
	<tr align="center">
		<td width="7%">${requestScope.member.id}</td>
		<td width="7%">${member.pwd}</td>
		<td width="5%">${member.name}</td>
		<td width="11%">${member.email}</td>
	</tr>
	

</table> 