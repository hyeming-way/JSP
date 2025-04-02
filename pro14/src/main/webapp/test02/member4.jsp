<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	
	//forward4.jsp에서 request 내장객체 영역에 address키(속성)으로 바인딩했었습니다.
	//그런데 현재 포워딩 당한 member4.jsp에서도 session 내장객체 영역에 "address"키(속성)으로 바인딩하자
	//그러면 아래에 EL태그로 address키(속성)으로 값을 얻어 출력하면 어떤 내장객체 메모리영역의 값을 얻어 출력하는지 보자
	session.setAttribute("address", "수원시 팔달구");
%>    
    
<table width=100% align="center">
	<tr align="center" bgcolor="green">
		<td width="7%">아이디</td>
		<td width="7%">비밀번호</td>
		<td width="5%">이름</td>
		<td width="11%">이메일</td>
		<td width="11%">주소</td>
	</tr>
	
	<tr align="center">
		<td width="7%">${id}</td>
		<td width="7%">${pwd}</td>
		<td width="5%">${name}</td>
		<td width="11%">${email}</td>
		<td width="11%">${address}</td>
	</tr>

	<%-- 
		EL태그의 속성 접근 우선순위
		
		page -> request -> session -> application
		
	--%>
	
	<%--   	 
		   결론 : 위의 EL태그로 xxxxxScope. 을 생략하여 ~~  address키(속성)의 값을 얻어 출력하면 
		   		 
		   		 같은 address키(속성)로 각각 request와 session내장객체에 바인딩 해 놓았기때문에!!
		   		 
		   		 session보다 request내장객체 메모리 영역에 EL태그로 접근하는 우선순위가 더 높으므로
		   		 
		   		 request내장객체 메모리영역에 바인딩된  "서울시 강남구" 값을 얻어 출력할것이다.
	--%>




</table> 