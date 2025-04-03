<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<%-- JSTL의 functions 라이브러리 태그를 사용하기 위해 요청 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="title1" value="hello world!"/>
						   <!--0123456789   -->
	<c:set var="title2" value="쇼핑몰 중심 JSP입니다!"/>
	
	<c:set var="str1" value="중심"/>

	<h2>여러가지 문자열 관련 처리 함수들</h2>
	
	<%-- fn:length 함수 호출시 문자열을 전달하면 문자열의 전체문자갯수 반환 --%>
	fn:length(title1) -> ${ fn:length(title1) } <br>
	<%-- fn:length(title1) -> 12 --%>
	
	<%-- fn:toUpperCase 함수 호출시 문자열을 전달하면 소문자들을 모두 대문자로 변경한 전체문자열 반환 --%>
	fn:toUpperCase(title1) -> ${ fn:toUpperCase(title1) }<br>
	<%-- fn:toUpperCase(title1) -> HELLO WORLD! --%>
	
	<%-- fn:toLowerCase함수 호출시 문자열을 전달하면 대문자들을 모두 소문자로 변경한 전체문자열을 반환 --%>
	fn:toLowerCase(title1) -> ${ fn:toLowerCase(title1) } <br>
	<%-- fn:toLowerCase(title1) -> hello world! --%>
	
 	<%-- fn:substring함수 호출시  첫번쨰 전달 값으로 자를 전체 문자열을 전달하고,
		   두번째 전달값으로 자를 문자의 시작 index위치를 전달하고
		   세번째 전달값으로 자를 문자의 그다음 index위치를 전달하면
		   두번째로 전달한 자를 문자가 저장된 index위치의 문자부터
		   세번쨰 전달한 index이전위치의 문자까지를 잘라서 반환해줍니다.
		  
		  여기서는 3 index위치의 l부터  5 index ""빈공백 까지의 문자를 문자열로 잘라
		  "lo " 를 반환합니다.  그러므로 문자의 갯수는 총 3입니다.				  
	--%>
	fn:substring(title1, 3, 6) -> ${ fn:substring(title1, 3, 6) } <br>
	<%-- fn:substring(title1, 3, 6) -> lo --%>
	
	 <%--  trim함수 호출시  문자열을 전달하면 양쪽에 빈공백이 있으면 양쪽 공백만 제거후 
		   제거한 문자열을 반환해줌  --%>
	fn:trim('  안녕   하  세  요    ') -> ${fn:trim('  안녕   하  세  요    ')} <br>
	<%-- fn:trim(' 안녕 하 세 요 ') -> 안녕 하 세 요 --%>
	
	<%-- replace함수 호출시  첫번째 전달값으로 전체 문자열을 전달하고,
		 두번쨰 값으로 첫번째로 전달한 전체 문자열중에서 바뀌게될 문자를 전달합니다.
		 세번째 값으로 수정할 문자을 전달 하게 되면
		 바뀌게 될문자들이 모두~ 수정할 문자로 변경된  전체문자열을 반환해 줍니다. 
			
		 hello world! -> hello/world!
 	--%>	
	fn:replace(title1, " ", "/") -> ${fn:replace(title1, " ", "/")} <br>
	<%-- fn:replace(title1, " ", "/") -> hello/world! --%>
	
	fn:indexOf(title2, str1) -> ${fn:indexOf(title2, str1)} <br>
	<%-- fn:indexOf(title2, str1) -> 4 --%>
	
	<%--
	title1 = "hello world!" 
	          0123456

	title2 = "쇼핑몰 중심 JSP입니다!"


	str1 = "중심" <- 찾을 검사할 문자열 
	--%>
	<%-- contains함수 호출시  첫번째 매개변수로 전체 문자열을 전달하고,
						            두번째 매개변수로 찾을 문자열을 전달하면
						            전체 문자열에서 찾을 문자열이 있으면 true반환하고 
						            없으면 false를 반환
	 --%>
	fn:contains(title1, str1) -> ${fn:contains(title1, str1)} <br>
	<%-- fn:contains(title1, str1) -> false --%>
	fn:contains(title1, str1) -> ${fn:contains(title2, str1)} <br>
	<%-- fn:contains(title1, str1) -> true --%>
	
	
	
</body>
</html>