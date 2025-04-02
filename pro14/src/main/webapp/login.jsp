<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//한글처리
request.setCharacterEncoding("UTF-8");
%>
	<form action="result.jsp" method="post">
		아이디 : <input type="text" name="userID"><br>
		비밀번호 : <input type="password" name="userPW"><br>
		
		<input type="submit" value="로그인">
		<input type="reset" value="다시입력">
	</form>
	
	<!-- 작성하는 방법 1 -->
	<a href="http://localhost:8090/pro14/test01/memberForm.jsp">회원가입하러가기</a>
	<br><br>
	
	<%
	/*
		최초 웹브라우저 주소창에 http://localhost:8090/pro14/login.jsp URL 입력하여
		톰캣 서버에 login.jsp를 요청하게 됩니다.
		login.jsp 페이지 내부에서 클라이언트가 요청한 전체 URL주소 중에서
		/pro14 <- 컨텍스트주소 : 톰캣서버가 pro14 프로젝트 찾아갈 /pro14 주소를 얻을 수 있다.
	*/
		//자바코드를 작성하여 컨텍스트 주소 얻기
		String contextPath = request.getContextPath();	
	%>
	<!-- 작성하는 방법 2. 자바코드로 request객체의 getContextPath()메소드를 호출하여 
		 login.jsp를 요청한 전체 URL중에서 /pro14 <-- 컨텍스트 주소만 반환받아 사용
		 
		 pageContext내장객체 내부에 만들어져 있는 request속성(변수)을 호출하면
		 HttpServletRequest객체의 주소를 얻고 
		 얻은 HttpServletRequest객체 내부에 만들어져 있는 contextPath속성(변수)를 호출하면
		 반환값으로 "/pro14" 컨텍스트 주소를 얻을수 있다.
			   
	 -->
	<a href="<%=contextPath%>/test01/memberForm.jsp">회원가입하러가기</a>
	<br><br>
	
	<!-- 작성하는 방법3. EL의 pageContext 내장객체를 사용한 방법 -->
	<a href="${pageContext.request.contextPath}/test01/memberForm.jsp">회원가입하러가기</a>
	
	<%--
		EL 표현언어에서 제공해주는 pageContext내장객체 
		
		- pageContext내장객체는 javax.servlet.jsp.PageContext클래스를 상속해
		  톰캣 컨테이너가 JSP파일 실행시 자동으로 생성해서 제공해 주는 내장객체 입니다.
		  
		사용 예
			<a>태그를 이용해 다른 서블릿이나 JSP를 요청하는 방법은 2가지 입니다
				방법1. 컨텍스트 주소(pro14)를 직접 <a>의 href속성에 작성 해서 사용 하는 방법
				
					  <a href="/pro14/test01/memberForm.jsp"> 회원가입</a>  

				방법2. 자바의 request객체의 getContextPath메소드를 호출하여 
					  클라이언트가 요청한 전체 URL중에서 컨텍스트 주소 /pro14 만 얻어 
					  <a>태그의 href속성에 작성해서 사용 하는 방법
					  
					  <a href="<%=request.getContextPath()%>/test01/memberForm.jsp">회원가입</a>

				문제점
					 방법1은  컨텍스트 주소이름이 바뀌면 일일이 찾아서 수정해야한다는 단점이 있고
					 방법2는   자바코드가 HTML코드와 사용되므로 작업이 복잡해 진다는 단점 이 있다
					 
				해결법
					  EL문법에서 제공해주는 pageContext객체의 변수를 이용하면
					  컨텍스트 주소를 반환 받을수 있다.					  
	--%>
	
</body>
</html>