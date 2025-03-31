<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	//순서1. 클라이언트가 웹브라우저 주소창에
	//		http://localhost:8090/pro12/test01/appTest1.jsp 요청주소를 입력하여
	//		톰캣서버에 주소를 보내어 요청합니다.
	
	//순서2. 톰캣서버가 실행하는 appTest1.jsp 페이지에
	//		우리 개발자가 session 내장객체와 application 내장객체메모리 영역에 각각 바인딩한 후
	//		두번째 웹페이지 appTest2.jsp를 클라이언트가 요청할 수 있게 <a>태그를 만들어놓자.
	
	//session 내장객체 메모리 영역에 바인딩
	//- 요청한 웹브라우저 창을 닫으면 session 내장객체메모리 영역 또한 톰캣 메모리영역에서 소멸된다.
	session.setAttribute("name", "이순신");
	
	//application내장객체(ServletContext) 영역에 바인딩
	//- 요청한 웹브라우저 종류와 상관없이 톰캣서버를 중지하면 웹애플리케이션도 중지되니
	//	그 시점에 application 내장객체 메모리 영역 소멸된다.
	application.setAttribute("address", "서울시 성동구");
%>    

	<h1>session과 application 내장객체 메모리 영역에 각각 바인딩함</h1>
    <a href="appTest2.jsp">두번째 서버페이지 이동</a>
