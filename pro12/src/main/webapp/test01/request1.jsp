<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	//순서1. 클라이언트가 웹브라우저 주소창에 
	//http://localhost:8090/pro12/test01/request1.jsp 요청주소입력후
	//톰캣서버에 request1.jsp서버페이지를 요청하면
	//톰캣서버는 요청한 주소 하나당 하나의 request객체메모리를 생성해서 제공해 줍니다
	
	//순서2. 제공받은request객체 메모리에 개발자가 데이터를 바인딩 합니다
	request.setAttribute("name", "이순신");
	request.setAttribute("address", "서울시 강남구");
	
	
	//참고. 다른 서버페이지 (jsp 또는 서블릿)를 다시 요청하면
    //     요청주소 하나당 하나의 request객체 메모리를 톰캣서버가 새롭게 생성해서
    //     제공해 주기때문에  request1.jsp서버페이지에서 사용한  request객체메모리를
    //     request2.jsp다른 서버페이지에 공유해서 사용하기 위해서는
    //     디스패처 방식(톰캣서버가 서버페이지를 곧바로 재요청)으로 포워딩 해야 합니다
	RequestDispatcher dispatch = request.getRequestDispatcher("request2.jsp");
	dispatch.forward(request, response);
%>    
