<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//1. 인코딩 (컴퓨터가 한글문자를 처리할 수 있는 방식) 설정
	request.setCharacterEncoding("UTF-8");
%>    
     
<%-- HashMap 클래스의 객체 생성 --%>     
<jsp:useBean id="membersMap" class="java.util.HashMap"/>

<%
	//HashMap 객체에 Key, Value 한쌍의 형태로 묶어서 데이터 저장
	membersMap.put("id","park2");
	membersMap.put("pwd","4321");
	membersMap.put("name","박지성");
	membersMap.put("email","park2@test.com");
	
/*
	-----------------------------------HashMap----------------------------------
    key들   value들 
	("id", "park2")
	("pwd", "4321")
	("name", "박지성")
	("email", "park2@test.com")
	-----------------------------------------------------------
*/
	
%>    

<%-- ArrayList 객체 생성 --%>
<jsp:useBean id="membersList" class="java.util.ArrayList"/> 
      
<%-- 
2,3. memberForm.html에 입력한 요청데이터들을 request 메모리에서 꺼내어
memberVO객체의 각 변수에 저장 
--%>     
<jsp:useBean id="vo1" class="sec01.ex01.MemberVO"/>
<jsp:setProperty name="vo1" property="*"/>
<%-- MemberVO의 변수명과 member3의 <input> name값과 동일해야 * 사용 가능 --%>

<%
	//2번째 MemberVO 객체 생성
	MemberVO vo2 = new MemberVO("son","1234","손흥민","son@test.com");
	
	//위 두 개의 MemberVO객체들을 ArrayList배열에 추가
	membersList.add(vo1); membersList.add(vo2);
	
	//추가로 HashMap 내부에 ArrayList 배열 추가
	membersMap.put("membersList",membersList);
/*

	-----------------------------------HashMap----------------------------------
	     key들   value들 
		("id", "park2")
		("pwd", "4321")
		("name", "박지성")
		("email", "park2@test.com")
		("membersList", ArrayList배열)
							|              입력한정보          손흥민
							 ->  [  new MemberVO(),     new MemberVO()  ] 
									 	 0                 1
	------------------------------------------------------------------------------


						${membersMap.membersList[0].변수명}
						${membersMap.membersList[1].변수명}

*/	
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
		
		<tr align="center">
			<td>${membersMap.id}</td>
			<td>${membersMap.pwd}</td>
			<td>${membersMap.name}</td>
			<td>${membersMap.email}</td>
		</tr>
	
<%-- HashMap에 저장된 ArrayList배열얻고 배열에 저장된 VO객체 각 변수값 얻어 EL로 출력 --%>	
		<tr align="center">
			<td>${membersMap.membersList[0].id}</td>
			<td>${membersMap.membersList[0].pwd}</td>
			<td>${membersMap.membersList[0].name}</td>
			<td>${membersMap.membersList[0].email}</td>		
		</tr>
		
		<tr align="center">
			<td>${membersMap.membersList[1].id}</td>
			<td>${membersMap.membersList[1].pwd}</td>
			<td>${membersMap.membersList[1].name}</td>
			<td>${membersMap.membersList[1].email}</td>		
		</tr>

		<tr height="1" bgcolor="green">
			<td colspan="5"></td>
		</tr>	
	</table>

</body>
</html>