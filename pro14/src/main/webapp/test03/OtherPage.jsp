<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<h4>OtherPage.jsp</h4>

<ul>
	<li>
		request내장객체에 바인딩되어있는 requestVar변수의 값:
		${requestVar}	
		<%-- 새로운 웹 페이지 요청에 의해 
		     request가 만들어지기때문에 출력 안 됨  
		     만약, 유지하고 싶다면 session영역에 requestVar변수를 저장해야합니다.--%>	
	</li>
	<li>재요청시 전달 받은 값1 : ${param.user_param1}</li>
	<li>재요청시 전달 받은 값2 : ${param.user_param2}</li>
	<%-- 새 request가 만들어지는데, 파라미터로 값을 전달했기때문에 출력됨 --%>
</ul>

	<%--
		위 set태그로 선언한 requestVar변수는 request메모리 영역에 저장하게 되는데
		클라이언트가 http://localhost:8090/pro14/test03/redirectTest.jsp 주소를 입력해서
		요청했을때 톰캣이 이 URL을 받아 새롭게 만든 request 객체메모리에 저장하게 됩니다.
		그러므로 아래의 c:redirect 태그를 이용해 /test03/OtherPage.jsp를 리다이렉트방법으로 포워딩하면
		톰캣서버는 또 새로운 요청 URL에 관한 새로운 request 객체메모리를 생성하게 되어 재요청이 이루어집니다.
	 --%>
    