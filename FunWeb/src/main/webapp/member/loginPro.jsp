<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//1. 요청한 데이터 인코딩 설정
	request.setCharacterEncoding("UTF-8");
	
	//2. 요청한 데이터 얻기
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	//3. 비즈니스로직(DB작업 또는 로직)처리
	//-> 입력받은 아이디, 비밀번호 값이 DB에 member테이블에 저장되어 있는 아이디, 비밀번호가 있는지 확인
	MemberDAO memberDAO = new MemberDAO();
	
	//check = 1 -> 아이디 맞음, 비밀번호 맞음
	//check = 0 -> 아이디 맞음, 비밀번호 틀림
	//check = -1 -> 아이디 틀림
	int check = memberDAO.useCheck(id, passwd);
	
	//4. DB의 테이블에 저장된 아이디, 비밀번호와
	//   로그인 화면에서 입력한 아이디, 비밀번호가 일치할 때 
	if(check == 1){
		//로그인 화면에서 입력한 아이디를 HttpSession 객체에 바인딩
		session.setAttribute("id", id);
		//메인페이지(index.jsp)를 포워딩
		response.sendRedirect("../index.jsp");
	} else if (check == 0){ //비밀번호가 틀리면?
%>
		<script>
			alert("비밀번호가 틀렸습니다.");
			histoty.back(); //login.jsp전 페이지 보여줌
		</script>
<%	
	} else { //아이디가 틀리면?
%>
		<script>
			alert("아이디가 없습니다.");
			history.go(-1); //login.jsp전 페이지 보여줌
		</script>
<%	
	}	
%>    
    
 
    