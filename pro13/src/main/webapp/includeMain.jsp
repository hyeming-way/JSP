<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//두 외부 파일을 include디렉티브태그(지시어태그)와 include액션태그로 
	//각각 인클루드하여 어떤 차이가 있는지 알아보겠습니다.
	
	//1. 포함할 파일의 경로를 변수에 저장
	String outerPath1 = "./inc/OuterPage1.jsp"; //첫번째 외부파일 경로
	String outerPath2 = "./inc/OuterPage2.jsp"; //두번째 외부파일 경로
	
	//2. page 내장객체 영역과 request 내장객체 영역에 바인딩(묶어서 저장)
	//여기서 바인딩한 속성은 두 파일에서 읽어올 수 있는지 확인하기 위한 용도입니다.
	pageContext.setAttribute("pAttr", "동명왕");
	request.setAttribute("rAttr", "온조왕");
	
%>    
 
<h2>include 디렉티브(지시어)태그와 include 액션태그 동작 방식 비교</h2> 

	<%-- include 디렉티브 태그 방식 --%>
 	<h3>include 디렉티브 태그로 외부페이지 소스코드 포함시키기</h3>
 	<%@ include file="./inc/OuterPage1.jsp" %>
 	
 	<%-- 표현식을 사용한다면 에러가 발생합니다. --%>
	<!-- 주석처리 단축키 : Ctrl + Shift + C -->
	<%-- <%@ include file="<%=outerPath1%>" %> --%>
	
	<p>외부 파일1에 선언한 변수 : <%=newVar1%></p>
	
<%----------------------------------------------------------------------- --%>

	<%-- include 액션 태그 방식 --%>
 	<h3>include 액션 태그로 외부페이지2의 실행한 결과 데이터 포함시키기</h3>
 	
 	<%-- 모든 include 액션태그로 외부페이지의 실행결과를 웹브라우저에 보내어 포함시키기 --%>
 	<jsp:include page="<%=outerPath2%>"/>
 	
 	<%-- 
 		에러발생이유
 		액션태그 방식에서는 실행의 흐름만 이동(재요청)시켜
 		외부파일에서 실행된 결과값만 가져와 현재페이지에 포함시키기 때문입니다.
 	--%>
	<%-- <p>외부 파일2에 선언한 변수 : <%=newVar2%></p> --%>
 	
 	
 	
 	
 	
 	