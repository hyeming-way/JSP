<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    

<% request.setCharacterEncoding("UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 텍스트 분석기</title>
	
	<style type="text/css">
		.highlight {color: red; font-weight: bold;}
	</style>
	
</head>
<body>
	<h2>JSP기반 텍스트 분석기</h2>
	
	<!-- 클라이언트가 텍스트를 입력하여 요청하는 폼 -->
	<form method="POST">
	
		<label>분석할 문장을 입력하세요:</label>
		
		<!-- 클라이언트가 입력한 값을 톰캣서버의 fnTest2.jsp로 request 내장객체에 담아 전송 -->
		<!-- 입력한 값이 유지되도록 value 속성에 request에서 꺼내온 값을 다시 넣었습니다. -->
		<input type="text" name="inputText" value="${param.inputText}">
		
		<button type="submit">분석요청하기</button>

	</form>
	
	<!-- 클라이언트가 입력한 텍스트가 있을 경우 실행! -->
	<c:if test="${not empty param.inputText}">
		
		<!-- 클라이언트가 입력한 텍스트를 얻어 변수 text에 저장! -->
		<c:set var="text" value="${param.inputText}" />
		
		<!-- 검색할 키워드 목록을 콤마(,)로 구분된 전체문자열로 설정해서 변수 keywors에 저장! -->
		<c:set var="keywords" value="JSP,Java,Spring"/>
		
		<h3>입력된 문장 : ${text}</h3>
		
		<ul>
			<!-- 전체 문자열 길이 출력 -->
			<li>
				<b>문장의 길이:</b>
				<!-- fn:length()함수로 문자열의 총 문자 길이를 반환 -->
				${fn:length(text)} 문자
			</li>
			
			<!-- 전체 문자열을 소문자로 변환해서 출력 -->
			<li>
				<b>소문자로 변환:</b>
				<!-- fn:toLowerCase() 함수 사용 -->
				${fn:toLowerCase(text)}
			</li>
			
			<!-- 공백을 밑줄(_)로 변경 -->
			<li>
				<b>공백을 밑줄(_)로 변경:</b>
				<!-- fn:replace() 함수 사용하여 공백(' ')을 밑줄('_')로 변경한 전체 문자열 반환 -->
				${fn:replace(text, ' ', '_')}
			</li>
			
			<!-- 특정 키워드가 포함되어 있는지 여부 확인 -->
			<li>
				<b>키워드 포함 여부:</b><br>
				<!-- 키워드 목록을 콤마(,)로 나누어 반복문 실행 -->
				<c:forEach var="keyword" items="${fn:split(keywords, ',')}">
					
					<!-- fn:contains()함수를 사용하여 키워드 포함여부 확인 -->
					"${keyword}" : ${ fn:contains(text, keyword) ? "포함됨" : "없음" }<br>
				</c:forEach>
			</li>
			
			<!-- 특정 키워드를 강조 처리 -->
			<li>
				<b>강조된 문장:</b>
				<!-- 강조할 HTML 태그를 변수로 저장 -->
				<c:set var="highlightStart" value="<span class='highlight'>" />
				<c:set var="highlightEnd" value="</span>" />
				
				<!-- 강조된 텍스트를 저장할 변수 초기화 -->
				<c:set var="highlightedText" value="${text}"/>
				
				<!-- 키워드 목록을 순회하면서 해당 키워드를 강조된 형식으로 변경 -->
				<c:forEach var="keyword" items="${fn:split(keywords,',')}">		
				
					<c:set var="replacement" value="${highlightStart}${keyword}${highlightEnd}"/>		
										
					<c:set var="highlightedText" value="${fn:replace(highlightedText, keyword, replacement)}"/>
		
				</c:forEach>
				
				<!-- HTML태그가 정상적으로 렌더링되도록 escapeXml="false"로 설정 -->		
				<c:out value="${highlightedText}" escapeXml="false"/>			
			</li>
			
			<!-- 문자열 뒤집기 -->
			<li>
				<b>문자열 뒤집기:</b>
				<c:set var="reversedText" value=""/>
				
				<!-- 입력된 문자열의 문자 갯수만큼 반복 -->
				
				<c:forEach var="i" begin="0" end="${fn:length(text)-1}">
					<!-- 현재 문자(fn:substring(text, i, i+1))를 기존 reversedText 앞에 추가하여 문자열 뒤집기 -->
					<c:set var="reversedText" value="${fn:substring(text, i, i+1)}" />
				</c:forEach>
				
				<!-- 뒤집힌 문자열 출력 -->
				${reversedText}
			</li>
			
			
		</ul>
	
	</c:if>
	
	
</body>
</html>