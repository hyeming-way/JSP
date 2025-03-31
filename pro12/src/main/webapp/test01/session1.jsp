<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
/*
	SessionTest라는 이름의 서블릿에서 <a>태그를 클릭하여 요청받은 서버페이지 session1.jsp입니다.
	이때 같은 웹브라우저를 사용하므로 HttpSession내장객체 메모리를 공유 받아 사용할 수 있다.
	
	확장자가 .jsp인 서버페이지에서 HttpSession 내장객체메모리에 접근할때는
	서블릿 페이지에서는 request.getSession()메소드를 호출하여 얻었지만
	jsp페이지에서는 참조변수 session라는 이름으로 HttpSession 객체메모리에 바로 접근해서 사용할 수 있다.
*/
	//웹브라우저 창이 닫히기 전에 SessionTest서블릿으로부터 공유받은 HttpSession 내장객체메모리에 접근해서
	//바인딩한 값을 꺼내오자
	String name = (String)session.getAttribute("name"); //"이순신"을 얻음
	
	//추가로 기존 공유받은 HttpSession내장객체메모리에 바인딩
	session.setAttribute("address", "서울시 강남구");
%>

	이름은 <%=name%> 입니다. <br>
	<a href="session2.jsp">두번째 session2.jsp 요청해서 보여줘</a>
    
    