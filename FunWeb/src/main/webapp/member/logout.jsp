<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//HttpSession 메모리에 접근해서 id 삭제
	session.invalidate();
	//session.removeAttribute("id");
	
	//index.jsp(메인화면)을 포워딩
%>    
	<script>
		alert("로그아웃되었습니다.");
		location.href = "../index.jsp";
	</script>
    
    
    