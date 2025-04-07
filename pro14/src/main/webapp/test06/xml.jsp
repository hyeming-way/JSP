<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>    

<% request.setCharacterEncoding("UTF-8"); %>

<%--
 JSTL(XML 태그 사용법)
 - XML 데이터를 파싱하고 가공하여 출력하는 기능을 제공
 - 주요 태그 설명:
   - <x:out>: XPath 표현식을 사용하여 특정 데이터를 출력하는 태그
   - <x:parse>: XML 데이터를 파싱하여 변수에 저장하는 태그
   - <x:forEach>: 특정 XPath의 반복되는 요소를 처리할 때 사용
   - <x:if>: 조건을 만족하는 경우 특정 내용을 출력하는 태그
   - <x:choose>: 다중 조건을 처리할 때 사용하며, <x:when>과 <x:otherwise>와 함께 사용
   
 - XPath란?
   - XML 문서 내에서 특정 노드나 값에 접근할 수 있도록 해주는 경로 표현식
   - 예시: /booklist/book[1]/name → booklist 내부의 첫 번째 book 요소의 name 값
--%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- XML파일(BookList.xml) 내용을 현재 JSP페이지에서 사용하기 위해 불러옴 --%>
	<c:import url="BookList.xml" var="booklist" charEncoding="UTF-8"/>
	
	<%-- booklist 변수에 저장된 XML 데이터를 파씽하여 blist 변수에 저장 --%>
	<x:parse xml="${booklist}" var="blist"/>
	
	<h4>파싱 1 : 개별 데이터 출력</h4>
	<%-- 첫번째 책의 제목, 저자, 가격을 Xpath(/)를 이용해 파싱(추출)하여 출력 --%>
	제목 : <x:out select="$blist/booklist/book[1]/name"/> <br>
	저자 : <x:out select="$blist/booklist/book[1]/author"/><br>
	가격 : <x:out select="$blist/booklist/book[1]/price"/><br>
	
	<h2>파싱 2 : 테이블 형태로 출력</h2>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>저자</th>
			<th>가격</th>
		</tr>
		<%-- XML 데이터에서 모든 <book>요소(노드)를 반복하여 출력 --%>
		<x:forEach select="$blist/booklist/book" var="item">
			<tr>
				<td><x:out select="$item/name"/></td>
				<td><x:out select="$item/author"/></td>
				<td>
					<%-- 가격이 2만원 이상이면? '2만원 이상', 미만이면 '2만원 미만'출력 --%>
					<x:choose>
						<x:when select="$item/price >= 20000">2만원 이상</x:when>
						<x:otherwise>2만원 미만</x:otherwise>
					</x:choose>			
				</td>
			</tr>
		</x:forEach>	
	</table>
	
	<h4>파싱 3 : 특정 조건을 만족하는 경우 추가 출력</h4>
	<table border="1">
		<%-- XML데이터에서 모든 <book>요소를 반복해서 출력 --%>
		<x:forEach select="$blist/booklist/book" var="item">
			<tr>
				<td><x:out select="$item/name"/></td>
				<td><x:out select="$item/author"/></td>
				<td><x:out select="$item/price"/></td>
				<td>
					<%-- 책 제목이 '총,균,쇠'이면 '구매함' 출력 --%>
					<x:if select="$item/name = '총,균,쇠'">구매함</x:if>
				</td>
			</tr>
		</x:forEach>
	</table>
	
	
	


</body>
</html>