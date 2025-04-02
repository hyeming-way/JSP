<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL의 core라이브러리의 태그들을 사용하기위해 외부에서 불러옴 
	 prefix="c" <---- 접두어 설정
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<%
	//클라이언트가 member1.jsp요청주소를 입력하여 요청하면 현재 화면을 웹브라우저에 보여줍니다.
	
	//한글처리
	request.setCharacterEncoding("UTF-8");
%>    

<%--	
	변수선언시 사용되는 태그
	
		<c:set var="선언할변수명작성" value="변수에저장할값" scope="선언한변수를바인딩할내장객체"/>
--%>
<c:set var="id" value="hong" scope="page" />
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'홍길동'}" scope="page"/>
<c:set var="age" value="${22}" scope="page"/>
<c:set var="height" value="${177}" scope="page"/>

<c:if test="${true}"> <%-- 조건식 작성하는 자리에 true를 넣어 참을 만들 수 있습니다. --%>	
	<h1>항상 참입니다.</h1>
</c:if>

<c:if test="${11 eq 11}"> 
	<h1>두 값은 같습니다.</h1>
</c:if>

<c:if test="${11 != 31}"> 
	<h1>두 값은 다릅니다.</h1>
</c:if>

<c:if test="${ (id == 'hong') && (name == '홍길동') }"> 
	<h1>아이디는 ${id}이고, 이름은 ${name}입니다.</h1>
</c:if>

<c:if test="${age == 22}" var="result" scope="page"> 
	<h1>${name}의 나이는 ${age}살 입니다.</h1>
	${pageScope.result}
	
	<c:if test="${result}">	<%-- 중첩 if --%>
		<h1>그래 맞다.</h1>
	</c:if>	
</c:if>

<c:if test="${height > 160}">
	<h1>${name}의 키는 160보다 큽니다.</h1>
</c:if>


<%--
	<c:if>태그
	
	- 자바의 if문과 돌일하게 제어구문을 작성할때 사용합니다.
	  하지만 else가 별도로 없기때문에 일련의 조건을 
	  나열하는 형태로 작성하기에는 어려움이 있습니다.
	  
	- 문법
	
		<c:if test="${조건식}" var="조건식의 결과를 저장할 변수" scope="변수가 저장될 내장객체영역" 
							  var에는 true, false를 저장 
							  
			test 속성의 조건식이 참이면 출력될 코드 문장	
			
		</c:if>			  

 --%>

