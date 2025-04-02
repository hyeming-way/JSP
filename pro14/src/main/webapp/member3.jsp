<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//1. 인코딩 (컴퓨터가 한글문자를 처리할 수 있는 방식) 설정
	request.setCharacterEncoding("UTF-8");
%>    
     
<%-- 
2,3. memberForm.html에 입력한 요청데이터들을 request 메모리에서 꺼내어
memberVO객체의 각 변수에 저장 
--%>     
<jsp:useBean id="vo" class="sec01.ex01.MemberVO"/>
<jsp:setProperty name="vo" property="*"/>
<%-- MemberVO의 변수명과 member3의 <input> name값과 동일해야 * 사용 가능 --%>
  
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
		
<%--
	EL태그로 MemberVO객체의 각 변수에 저장된 입력한 값들을 얻어 출력
	문법
		${MemberVO객체.가져와_출력할_값이_저장된_변수명}
 --%>		
 		<tr>
 			<td>${vo.id}</td>
 			<td>${vo.pwd}</td>
 			<td>${vo.name}</td>
 			<td>${vo.email}</td>
 		</tr>
 		
 <%--
 	표현식과 자바코드로 MemberVO객체의 각 변수에 저장된 입력한 값들을 얻어 출력
 	문법
 		<% MemberVO객체.getter메소드 호출 %>
  --%>	
  		<tr>
  			<td><%=vo.getId()%></td>
  			<td><%=vo.getPwd()%></td>
  			<td><%=vo.getName()%></td>
  			<td><%=vo.getEmail()%></td>
  		</tr>
	
		<tr height="1" bgcolor="green">
			<td colspan="5"></td>
		</tr>	
	</table>

</body>
</html>