<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 중에서 core, fomatting 태그들을 사용하기 위해 주소를 import --%>    
<%-- <%@ taglib %>는 외부파일을 불러오기 위한 코드 --%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
  
<% request.setCharacterEncoding("UTF-8"); %>
   
<%--

	 국제화(Formatting)태그
	 - 이태그들은  국제화 태그로, 국가별로 다양한 언어, 날짜, 시간, 숫자형식을 설정할때 사용하는 태그입니다.
	 - 종류
	 	분류			태그명				기능
	 	숫자포맷		formatNumber		숫자 포맷을 설정하는태그
	 			    parseNumber			문자열을 숫자 포맷으로 변환하는 태그
	 	
	 	날자포맷		formatDate			날짜나 시간의 포맷을 설정하는 태그
	 				parseDate			문자열을 날짜 포맷으로 변환하는 태그
	 				
	 	타임존설정		setTimeZone			시간대 설정정보를 변수에 저장하는 태그 
	 				timeZone			시간대를 설정하는 태그
	 				
	 	로케일설정		setlocale			통화 기호나 시간대를 설정한 지역에 맞게 표시 하는 태그 
	 				requestEncoding		요청 매개변수의 문자셋을 설정합니다.
	
	
	
	
	주제 : 숫자 포맷팅 및 파싱
	
		<fmt:formatNumber> 사용 형식
		
			<fmt:formatNumber  
				 
				 value="출력할 숫자"  
				 
				 type="출력 양식 percent(퍼센트), currency(통화), number(일반 숫자,기본값) 등을 설정합니다. "     
				 
				 var="출력할 숫자를 변수에 저장합니다. 해당속성 사용시 즉시 출력되지 않고 원하는 위치에 출력할수 있게 변수 선언"
				 
				 groupingUsed="세자리 마다 콤마를 출력할지 여부를 설정합니다. 기본값은 true입니다."
				 
				 pattern="출력할 숫자의 양식을 패턴으로 지정합니다."
				 
				 scope="var로 선언한 변수를 저장할 내장객체 종류중 하나 지정합니다."
				 
				 />
	
	
		 <fmt:parseNumber /> 태그 사용형식
	 						
	 			<fmt:parseNumber   
	 				 
	 				 var="변환된 숫자를 저장할변수선언"
	 				 
	 				 value="변환할(파싱할) 문자열을 설정합니다."
	 				 
	 				 type="문자열의 타입을 설정합니다. 기본값은 number(숫자)입니다."
	 				 
	 				 integerOnly="정수 부분만 표시할지 여부를 결정합니다. 기본값은 false입니다."
	 				 
	 				 pattern="문자열의 양식을 패턴으로 지정합니다."
	 				 
	 				 scope="var로 선언된 변수를 저장할 내장객체 영역중 하나를 설정합니다."
	 		      />								   
 --%>      
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>숫자 포맷 설정</h4>
	
	<c:set var="number1" value="12345"/>
	
	<%--
		groupingUse 속성 : 수를 세자리마다 콤마로 표시할지 지정하는 속성
		groupingUse:true - 수를 세자리마다 콤마로 표시
		groupingUse:false - 수를 세자리마다 콤마로 표시하지않음
		
		지정하지않으면 기본값은 true	
	 --%>
	
	<%-- formatNumber태그를 사용하면 기본적으로 큰 수는 세자리마다 콤마로 출력합니다. 12,345 --%>
	콤마 O : <fmt:formatNumber value="${number1}"/> <br>
	
	<%-- 반면 groupingUsed="false"로 설정하면 콤마로 구분하지않고 출력합니다. 12345 --%>
	콤마 X : <fmt:formatNumber value="${number1}" groupingUsed="false"/> <br>
	
	
	<%-- type속성을 사용하면 통화기호를 달거나 백분율 %로 포맷형식을 바꾸어 출력할 수 있습니다. ₩12,345 --%>
	<fmt:formatNumber value="${number1}" type="currency" var="printNum1"/>
	
	 	  <%-- ₩12,345 --%>
	통화기호 : ${printNum1} <br>
	
	<fmt:formatNumber value="0.03" type="percent" var="printNum2" />
			<%-- 3% --%>
	퍼센트 : ${printNum2} <br>
	
	<hr><hr>
	
	<h4>문자열을 숫자로 변경</h4>
	
	<c:set var="number2" value="${'6,789.01'}"/>
	
	<fmt:parseNumber value="${number2}" pattern="00,000.00" var="printNum3"/>
			 <%-- 6789.01 --%>
	소수점까지 : ${printNum3} <br>
	
	<%-- integerOnly="true"로 설정해 정수부만 파싱하여 출력 --%>
	<fmt:parseNumber value="${number2}" integerOnly="true" var="printNum4"/>
			   <%-- 6789 --%>
	정수 부분만 : ${printNum4}
	
</body>
</html>
