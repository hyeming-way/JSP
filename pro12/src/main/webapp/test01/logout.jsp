<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//로그아웃된 화면을 보여주기 위해
	//공유받은 HttpSession 객체메모리 내부에 바인딩된 모든 데이터 제거
	session.invalidate();

	//참고. 
	//session.removeAttribute("id"); //("id", "master")만 제거
	
	//HttpSession 내장객체메모리의 모든 데이터를 제거 후
	//index.jsp(메인화면) 포워딩(재요청)
//	response.sendRedirect("index.jsp");
%>
	<script>
		window.alert("로그아웃 처리 되었습니다.");
		window.alert("메인페이지로 이동합니다.");
		location.href = "index.jsp";	
	</script>
    
    