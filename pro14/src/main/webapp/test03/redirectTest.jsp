<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<%--
	 <c:redirect>태그
	 - 이태그는 response내장객체의 sendRedirect()메소드를 호출한 
	      포워딩을 통한 페이지 이동을 처리하는 태그입니다.
	      
	 - 문법
	 	<c:redirect url="재요청할 주소 경로"    />
	 
	 	또는
	 	
	 	<c:redirect url="재요청할 서버페이지 주소">
	 	
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 		<c:param name="전달할값을식별할속성명" value="전달할값" >
	 	
	 	</c:redirect>
	 
	 - 재요청시  다른 서버페이지로  전달할 값이 있다면  
	   <c:param>태그를 내부에 사용하면 됩니다.
	 
 --%>        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- requestVar변수를 만들고 "홍길동" 저장
	     그리고 request 내장객체 영역에 바인딩 --%>
	<c:set var="requestVar" value="홍길동" scope="request"/>
	<%--
		   위 set태그로 선언한 requestVar변수는 request메모리 영역에 저장하게 되는데
		   클라이언트가 http://locahost:8090/pro14/test03/redirectTest.jsp 주소를 입력해서
		   요청했을떄  톰캣이 이 URL을 받아 새롭게 만든 request객체 메모리에 저장하게 됩니다.
		   그러므로 아래의 c:reirect 태그를 이용해 /test03/OtherPage.jsp를 리다이렉트방법으로 포워딩하면
		   톰캣서버는 또~ 새로운 요청 URL에 관한 새로운 request객체 메모리를 생성하게 되어 재요청이 이루어집니다 
	--%>
	
	<%-- OtherPage.jsp를 포워딩 - 리다이렉트방법으로 --%>
	<c:redirect url="/test03/OtherPage.jsp">
		
		<c:param name="user_param1" value="출판사"/>
		<c:param name="user_param2" value="한빛출판사"/>
	
	</c:redirect>
	
</body>
</html>