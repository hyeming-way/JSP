<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<% request.setCharacterEncoding("UTF-8"); %>   

<%-- urlTest.jsp를 요청했을때의 전체주소(URL) 중에서 컨텍스트주소만 얻어 변수에 저장 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
		<%-- /pro14 --%>

<%-- 현재 페이지에서 <a>태그를 나중에 클릭해서 요청할 주소(URL)을 만들때 사용되는 c:url태그 --%>
<c:url var="url1" value="/member1.jsp?id=hong&pwd=1234&n">
	<c:param name="id" value="hong"/>
	<c:param name="pwd" value="1234"/>
	<c:param name="name" value="홍길동"/>
	<c:param name="email" value="hong@test.com"/>
</c:url>

<!-- <a href="http://localhost:8090/pro14/test01/member1.jsp?id=hong&pwd=1234&name=홍길동&email=hong@test.com">회원정보 출력</a> -->
<a href="${contextPath}/member1.jsp?id=hong&pwd=1234&name=홍길동&email=hong@test.com">회원정보 출력</a>
<hr>
<a href="${url1}">회원정보 출력</a>

    