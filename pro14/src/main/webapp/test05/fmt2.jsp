<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>       
    
<% request.setCharacterEncoding("UTF-8"); %> 
 
 <%-- 
	 날짜 포맷 및 타임존
	 
	 <fmt:formatDate /> 태그    (날짜 포맷)
	 
	 	- 이태그는 날짜와 시간 포맷을 지정하는 태그입니다.
	 	
	 	- 문법
	 		 <fmt:formatDate   
	 		 	  
	 		 	  value="변환하여출력할날짜"
	 		 	  
	 		 	  type="변환하여 출력할 양식 세가지중 하나   (출력양식 종류 : 날짜 date,  시간  time,  날짜 및 시간모두  both  )"
	 		 	  
	 		 	  var="변환하여 출력할 날짜 또는 시간을 저장할 변수"
	 		 	
	 		 	  dataStyle="날짜 스타일 종류 지정  (default, short,  medium, long, full 중 하나 )"
	 		 	  
	 		 	  timeStyle="시간 스타일 종류 지정 (default, short,  medium, long, full 중 하나 ) "
	 		 	
	 		 	  pattern = "출력할 날짜 및 시간의 양식을 패턴으로 직접 지정합니다."
	 		 	
	 		 	  scope = "변환한 날짜가 저장된 var의 변수를 저장할 내장객체 영역중 하나"

	 		 />
	 	
	 
	  타임존
	 	<fmt:timeZone> </fmt:timeZone> 태그
	 	- 출력할 시간의 시간대를 설정할수 있는 태그 
	 	- 위 <fmt:formatDate>태그를 <fmt:timeZone>여는 부분과 닫는 부분 사이에 작성하면 ,
	 	  설정한 시간대에 따라 다른 시간을 출력할수 있습니다.
	 	  
	 	  <fmt:timeZone  value="America/Chicago" >
	 	  	
	 	  		<fmt:formatDate value="날짜및시간" ....  />
	 	  
	 	  </fmt:timeZone>


--%>      
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- 
	java.util패키지에서 제공해주는 Date클래스의 객체를 생성해서 변수에 저장합니다. 
	Date클래스의 기본생성자로 객체를 생성하면 오늘 날짜와 시간값을 가지는 Date 객체가 만들어집니다.
	--%>
	<c:set var="today" value="<%= new java.util.Date()%>" />
	
	<c:out value="${today}"/> <br>
	
	<h4>날짜 포맷</h4>
	<%-- 날짜를 포맷(변환)하기위해서는 type="date"로 설정합니다.
	     날짜스타일은 dateStyle속성에 각각 지정합니다. --%>
	
	full : <fmt:formatDate value="${today}" type="date" dateStyle="full"/> <br> 
	<%-- full : 2025년 4월 3일 목요일 --%>
	
	long : <fmt:formatDate value="${today}" type="date" dateStyle="long"/> <br> 
	<%-- long : 2025년 4월 3일 --%>
	
	default : <fmt:formatDate value="${today}" type="date" dateStyle="default"/> <br> 
	<%-- default : 2025. 4. 3. --%>
	
	short : <fmt:formatDate value="${today}" type="date" dateStyle="short"/> <br> 
	<%-- short : 25. 4. 3. --%>

	<hr><hr>

	<h4>시간 포맷</h4>
	<%-- 시간 포맷(변환)하기위해서는 type="time"로 설정합니다.
	     시간스타일은 timeStyle속성에 각각 지정합니다. --%>
	 
	full : <fmt:formatDate value="${today}" type="time" timeStyle="full"/> <br> 
	<%-- full : 오후 1시 11분 31초 대한민국 표준시 --%>
	
	long : <fmt:formatDate value="${today}" type="time" timeStyle="long"/> <br>
	<%-- long : 오후 1시 12분 33초 KST --%> 
	
	default : <fmt:formatDate value="${today}" type="time" timeStyle="default"/> <br> 
	<%-- default : 오후 1:12:33 --%>
	
	short : <fmt:formatDate value="${today}" type="time" timeStyle="short"/> <br> 
	<%-- short : 오후 1:12 --%>
	
	<hr><hr>
	
	<h4>날짜/시간 모두 (포맷)변환</h4>
	<%-- 날짜와 시간을 포맷해서 동시에 출력하기위해서는 type="both"로 설정합니다. --%>
	
	<fmt:formatDate value="${today}" type="both" dateStyle="full" timeStyle="full"/> <br>
	<%-- 2025년 4월 3일 목요일 오후 1시 17분 4초 대한민국 표준시 --%>
	
	<%-- dataStyle속성과 timeStyle속성 대신
	     pattern 속성을 설정하여 직접 날짜와 시간의 포맷형식을 설정해서 변경할 수 있다. --%>
	<fmt:formatDate value="${today}" type="both" pattern="yyyy/MM/dd hh:mm:ss"/>
	<%-- 2025/04/03 01:18:52 --%>
	
	
	<hr><hr>
	
	<h4>타임존 설정</h4>
	
	<%-- 시간대를 세계 협정시(GMT, 대한민국 시간보다 9시간 빠름) --%>
	세계 협정 시간대 : 
	<fmt:timeZone value="GMT">
		<fmt:formatDate value="${today}" type="both" dateStyle="full" timeStyle="full"/>
	</fmt:timeZone>
	<%-- 세계 협정 시간대 : 2025년 4월 3일 목요일 오전 5시 35분 4초 그리니치 표준시 --%>
	<br>
	
	시카고 지역 시간대 : 
	<fmt:timeZone value="America/Chicago">
		<fmt:formatDate value="${today}" type="both" dateStyle="full" timeStyle="full"/>
	</fmt:timeZone>
	<%-- 시카고 지역 시간대 : 2025년 4월 3일 목요일 오전 12시 36분 34초 미 중부 하계 표준시 --%>
	<br>
	
	유럽 런던 시간대 : 
	<fmt:timeZone value="Europe/London">
		<fmt:formatDate value="${today}" type="both" dateStyle="full" timeStyle="full"/>
	</fmt:timeZone>
	<%-- 유럽 런던 시간대 : 2025년 4월 3일 목요일 오전 6시 37분 53초 영국 하계 표준시 --%>
	<br>
	
	<%--
		지역기반 ID
		
		대륙/도시
		
		Asia/Seoul 			(한국)
		Asia/Tokyo 			(일본)
		America/New_York    (미국 뉴욕)
		Europe/London       (영국 런던)
		Australia/Sydeny    (호주 시드니)
			
	 --%>
	



</body>
</html>