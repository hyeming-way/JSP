<%@page import="sec01.ex01.MemberVO"%>
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
<jsp:useBean id="vo1" class="sec01.ex01.MemberVO"/>
<jsp:setProperty name="vo1" property="*"/>
<%-- MemberVO의 변수명과 member3의 <input> name값과 동일해야 * 사용 가능 --%>

<%-- ArrayList 객체 생성 --%>
<jsp:useBean id="membersList" class="java.util.ArrayList"/>

<%
	//2번째 MemberVO 객체 생성
	MemberVO vo2 = new MemberVO("son","1234","손흥민","son@test.com");
	
	//위 두 개의 MemberVO객체들을 ArrayList배열에 추가
	membersList.add(vo1); membersList.add(vo2);
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
		
<%-- 위 ArrayList배열에 0 index 위치에 저장되어 있는 MemberVO 객체의 각 병수값들을 얻어 EL로 출력 
	 문법
	 	${컬렉션배열참조변수명[index].꺼내온VO객체의 변수명}
--%>		
		<tr align="center">
			<td>${membersList[0].id}</td>
			<td>${membersList[0].pwd}</td>
			<td>${membersList[0].name}</td>
			<td>${membersList[0].email}</td>		
		</tr>
		<%-- 참고. EL태그는 for문을 사용할 수 없음!!! --%>

		<tr align="center">
			<td>${membersList[1].id}</td>
			<td>${membersList[1].pwd}</td>
			<td>${membersList[1].name}</td>
			<td>${membersList[1].email}</td>		
		</tr>
	
		<tr height="1" bgcolor="green">
			<td colspan="5"></td>
		</tr>	
	</table>

</body>
</html>