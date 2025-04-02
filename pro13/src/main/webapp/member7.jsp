<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 요청한 데이터 request에 UTF-8설정
request.setCharacterEncoding("UTF-8");
%>

	<%-- 기본생성자를 호출해서 MemberVO클래스의 객체 생성한 후 page 내장객체 영역에 바인딩 --%>			
	<jsp:useBean id="vo"
				 class="sec01.ex01.MemberVO"
				 scope="page"/> <%-- 기본값 page --%>
			 
	<jsp:setProperty property="*" name="vo" />	
 <%--
     해석 : MemberVO객체의 모든 setter메소드 호출해서 
                모든 인스턴스변수에 각각 요청한 값들을 저장 시킨다.
                
     조건 : MemberVO클래스의 변수명과  
           MemberForm.html에 작성한 <input>의 name속성값이 같아야함
  --%>
  		
		 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- memberForm.html 입력한 정보를 MemberVO에 저장한 후
		 MemberVO의 인스턴스변수값들을 각각 얻어 getProperty 액션태그를 이용해 반환받아 출력 --%>
	<table width="100%" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아 이 디</td>
			<td width="7%">비밀번호</td>
			<td width="7%">이   름</td>
			<td width="7%">이 메 일</td>
		</tr>
	
<%-- getProperty액션태그 사용
	 - 자바빈 역할을 하는 VO객체의 private으로 은닉화된 인스턴스변수의 값을 반환시킬 getter메소드들을 
	   자바코드 대신 호출하는 태그.
	 - 자바코드의 for반복문이랑 같이 사용할수 없습니다. 
	   그러나 JSTL태그의 반복문문법과는 같이 사용할수 있다.
 	
 	 - 문법
 	 	<jsp:getProperty  name="useBean액션태그의 id속성에 작성한값"  
 	 	                  property="반환받을 값이 저장된 변수명"/>
 --%>
	
		<tr align="center">
				<td width="7%"><jsp:getProperty name="vo" property="id"/></td>
				<td width="7%"><jsp:getProperty name="vo" property="pwd"/></td>
				<td width="7%"><jsp:getProperty name="vo" property="name"/></td>
				<td width="7%"><jsp:getProperty name="vo" property="email"/></td>
		</tr>

		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr>

	</table>

</body>
</html>