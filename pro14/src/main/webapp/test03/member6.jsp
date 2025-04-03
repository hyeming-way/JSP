<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
  
<%
	//자바코드로 ArrayList배열 생성 후 문자열객체 저장
	List dataList = new ArrayList();
	dataList.add("hello");
	dataList.add("world");
	dataList.add("안녕하세요!!");
%>  

<%-- JSTL c:forEach에서 EL ${}을 사용할 수 있도록 
list변수에 위 dataList참조변수에 저장된 ArrayList배열의 주소 저장 --%>
<c:set var="list" value="<%=dataList%>"/>

<%-- 
<c:forEach var="현재 반복중인 값을 저장할 변수 이름" begin="반복 시작할 값(최초값)" 
		   end="반복 끝 값(최대값)" step="반복할때 증가하는 값(기본값:1)" varStatus="반복상태정보를 저장하는 변수명">
	
		반복할 코드 작성

</c:forEach>
--%>

<%-- 변수 i가 1부터 10이 될때까지 1씩 증가시키면서 10번 반복해서 i변수를 출력
	 그리고 현재 몇 번 반복하고 있는지 상태값도 출력  --%>
<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
	현재 ${loop.count} 반복한 상태의 i변수에 저장된 값은 ${i} 입니다. <br>
</c:forEach>

<hr>

<c:forEach var="i" begin="1" end="9" step="2" varStatus="loop">
	현재 ${loop.count}번 반복한 상태 -> 5 * ${i} = ${5*i} <br>
</c:forEach>

<hr>

<%--
<c:forEach var="순회할배열의_인덱스의_데이터를_차례대로_얻어_저장할_변수" items="순회할배열">
	배열에서 차례로 얻는 데이터를 사용해서 반복할 코드 작성
</c:forEach>
 --%>

<c:forEach var="data" items="${list}" varStatus="loop">
	${loop.count}번째 반복하고 있는 상태에서 얻는 문자열객체는?
	${data} <br>
</c:forEach>

<hr>

<c:forEach var="data" begin="0" end="${list.size()-1}" step="1" items="${list}">
	${data} <br>
</c:forEach>

<%--
	varStatus="loop"에서 loop는 c:forEach 태그의 반복 상태를 저장하는 변수입니다. 
	varStatus 속성을 사용하면 반복에 대한 다양한 정보를 얻을 수 있는데, 
	그 중 loop는 이 정보를 담고 있는 객체가 됩니다. 이 loop 객체를 통해 반복의 상태 정보를 참조할 수 있습니다.

	loop 객체가 제공하는 주요 속성들은 다음과 같습니다:	
		loop.index: 0부터 시작하는 현재 반복의 인덱스.
		loop.count: 1부터 시작하는 현재 반복 횟수.
		loop.first: 첫 번째 반복인지 여부 (Boolean).
		loop.last: 마지막 반복인지 여부 (Boolean).
		loop.even: 현재 반복이 짝수인지 여부.
		loop.odd: 현재 반복이 홀수인지 여부.
		
	위코드를 예를 들면, ${loop.count}는 현재 몇 번째 반복인지 나타내고, 
	               ${i}는 var="i"에 정의된 반복 변수 값을 참조합니다.
--%>

<hr>

<c:set var="fruits" value="사과, 파인애플, 바나나, 망고, 귤"/>

<%--
<c:forTokens var="전체문자열에서 구분자기호를 기준으로 잘라 하나씩 저장할 변수명" items="자를전체문자열" delims="구분자기호">
	출력 구문
</c:forTokens>
--%>

<c:forTokens var="value" items="${fruits}" delims=", ">
	${value} <br>
</c:forTokens>




