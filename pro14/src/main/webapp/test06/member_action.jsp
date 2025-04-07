<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
    
<%-- 1. 요청한 문자 데이터 인코딩 설정 --%>    
<% request.setCharacterEncoding("UTF-8"); %>

<%--
	2. memberForm.html에서 요청한 가입정보들을 request 객체 영역에서 얻어
	3. MemberVO 객체를 생성해서 각 인스턴스변수에 저장
--%>
<jsp:useBean id="membervo" class="sec01.ex01.MemberVO" />
<jsp:setProperty property="*" name="membervo"/>
<%

	//4. 입력한 회원정보를 DB의 t_member테이블에 추가(insert)하기 위해
	//   DB관련 작업하는 MemberDAO객체를 생성해서 addMember메소드 호출
	MemberDAO memberdao = new MemberDAO();
	memberdao.addMember(membervo);
	
	//5. 회원 추가 성공했다면 추가한 회원 포함한 전체 회원 조회를 위해 
	//   DB관련 작업하는 MemberDAO객체의 listMembers메소드 호출하여 조회된 정보(ArrayList배열)을 반환받자
	List membersList = memberdao.listMembers();
	
	//5-1. 조회된 회원정보(ArrayList배열)을 membersList.jsp에 보여주기위해
	//     request 내장객체 메모리에 ArrayList배열을 바인딩
	request.setAttribute("list", membersList);
%>

<%-- 6.디스패처 방식으로 membersList.jsp 포워딩시 request와 response 객체 공유 --%>
<jsp:forward page="membersList.jsp"/>


