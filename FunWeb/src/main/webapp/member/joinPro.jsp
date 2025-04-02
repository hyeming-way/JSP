<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//1. join.jsp 회원가입 입력페이지에서 입력한 요청한 값들을 얻기 전에 문자 인코딩 설정
request.setCharacterEncoding("UTF-8");
%>    

<%-- 
2. join.jsp에서 입력한 가입할 정보들을 request 내장객체영역에서 얻어
3. MemberBean 객체를 생성하여 각 변수에 저장시킴
   property="*" <-- MemberBean.java의 변수명과 join.jsp의 <input>name명이 동일해야 사용 가능
--%>
<jsp:useBean id="memberbean" class="member.MemberBean"/>
<jsp:setProperty name="memberbean" property="*"/>

<%
//4. jspbeginner데이터베이스 내부의 member테이블에 가입을 위해 입력한 한 줄의 정보를 추가(회원가입)
int result = new MemberDAO().insertMember(memberbean);

//5. 회원가입에 성공했다면 login.jsp로 포워딩(리다이렉트방법으로)
response.sendRedirect("login.jsp");

%>    
    
    
    
    