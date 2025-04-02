<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//한글처리
	request.setCharacterEncoding("UTF-8");

	//회원가입요청창(memberForm.html)에서 입력한 요청정보들이 저장된 request객체를 공유받아
	//추가로 수동으로 개발자가 직접 특정 데이터를 바인딩해놓자
	request.setAttribute("address", "서울시 강남구");
	//수동으로 바인딩한 경우 ${requestScope.address}로 접근해야함.
%>    

<%-- member2.jsp로 디스패처방식으로 포워딩 --%>
<jsp:forward page="member2.jsp"/>   
    
    