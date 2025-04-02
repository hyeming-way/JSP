<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//1. 인코딩 (컴퓨터가 한글문자를 처리할 수 있는 방식) 설정
	request.setCharacterEncoding("UTF-8");
%>    
     
<%-- Address클래스의 객체를 생성해서 인스턴스변수 city와 zipcode에 값을 저장하자 --%>
<jsp:useBean id="address" class="sec01.ex02.Address"/>
<jsp:setProperty property="city" name="address" value="서울"/>
<jsp:setProperty property="zipcode" name="address" value="07654"/>

<%-- 
2,3. memberForm.html에 입력한 요청한 데이터들은 request 내장객체 메모리에서 꺼내어
MemberVO객체 생성 후 각 인스턴스변수에 저장 
--%>
<jsp:useBean id="vo" class="sec01.ex02.MemberVO"/>
<jsp:setProperty property="*" name="vo"/>
<jsp:setProperty property="address" value="<%=address%>" name="vo"/>


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
			<td width="11%">도시이름</td>
			<td width="11%">우편번호</td>
		</tr>
	
		<%-- MemberVO객체의 각 변수값 얻어 출력 --%>	
		<tr align="center">
			<td width="7%">${pageScope.vo.id}</td>
			<td width="7%">${pageScope.vo.pwd}</td>
			<td width="7%">${pageScope.vo.name}</td>
			<td width="7%">${pageScope.vo.email}</td>
		<%-- MemberVO객체의 address 인스턴스변수에 저장된 Address객체 주소 얻고,
		Address 객체의 메소드를 호출해 도시이름을 얻어 출력 (자바코드)--%>		
<%-- 			<td width="7%"><%=vo.getAddress().getCity()%></td>	 --%>
<%-- 			<td width="7%"><%=vo.getAddress().getZipcode()%></td>	 --%>
			
			<td width="7%">${pageScope.vo.address.city}</td>
			<td width="7%">${pageScope.vo.address.zipcode}</td>
		</tr>
	
		<tr height="1" bgcolor="green">
			<td colspan="6"></td>
		</tr>	
	</table>

</body>
</html>