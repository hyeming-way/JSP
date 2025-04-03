<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
		<c:out>태그는 out.println()과 <%= %> 표현식 태그 대신 사용!
		그리고 EL을 이용하여 계산신도 넣을 수 있음
	--%>
	<c:out value="안녕하세요"/> <br>
	<c:out value="${2*3}"/> <br>
	
	<c:out value="${requestScope.memberbean.id}" default="빈공백 대신 출력할 기본값 설정"/>
	<%--
		requestScope.memberbean.id 는
		null을 가져와 예외가 발생하게 되어 출력이 안되지만
		실제 위 구문은 예외처리를 자동으로 해주어 빈공백을 출력해버립니다.
		빈공백을 default 속성에 적은 기본값으로 출력할수도 있습니다.
	 --%>
	 
	 <hr><hr>
	 
	 <%-- <abc> html태그로 인식하여 그대로 화면에 출력되지않습니다. --%>
	 <abc>는 abc입니다.<br>
	 
	 <%-- EL언어의 문법 중에서 &lt; 엔티티를 사용해야 태그를 그대로 화면에 출력할 수 있습니다. --%>
	 &lt;abc>는 abc입니다. <br>
	 
	 <%-- 위 코드들의 단점을 해결하기위해
	 	  JSTL의 c:out 태그를 이용하면 쉽게
	 	  이스케이프 문자들을 화면에 출력할 수 있습니다. --%>
	 <c:out value="<abc>는 abc입니다."/>
	 
</body>
</html>