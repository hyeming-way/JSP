<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
<%--

    JSP 스크립트 요소들이란?
    - JSP페이지에서 HTML태그에 자바코드를 넣을수 있는 태그들을 말함

    JSP 스크립트 요소들 종류
    1. 선언문 태그  <%!  %>   :   JSP페이지에서 변수나 메소드를 선언해 놓을 영역의 태그
    2. 스크립 틀릿 태그  <%   %>   :   JSP페이지에서 자바코드를 작성할 영역의 태그 
    3. 표현식 태그  <%=  %>      : JSP페이지에 웹브라우저로 응답할 데이터를 넣는 태그 
    						     값 또는 변수명을 넣어서 웹브라우저에 출력할수 있는 태그 
--%>  
  
<%-- 선언문 태그 영역에 전역변수 name과 메소드 getName()을 만들어 놓자. --%>    

<%! 
	//전역변수
	String name = "듀크";

	//전역메소드
	public String getName(){
		return name;
	}
%>    

<%-- 표현식 태그영역을 이용해 선언문 태그영역에 만들어 놓은 name 변수의 값을 얻어 출력(브라우저로 응답) --%>
안녕하세요. <%=name%>님!
    
    
  