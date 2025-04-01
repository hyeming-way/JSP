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
		//1. 한글처리
		request.setCharacterEncoding("UTF-8");
	
		//2. 요청한 값 얻기
		String userID = request.getParameter("userID");
		
		//3. 요청한 값을 조건식에 작성하여 판단 후 웹브라우저로 응답
		//조건 : 아이디를 입력하지 않았다면?
		if(userID == null || userID.length() == 0){
			//디스패치 방식으로 login.jsp 서버페이지를 포워딩(재요청)시
			//현재 result.jsp 서버페이지가 사용하고 있는 request 객체메모리 공유합시다.
			/*
			자바코드 작성 예
			RequestDispatcher dispatcher = request.getRequestDispatcher("재요청할 서버페이지 경로");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			*/
	%>
		<!-- 아이디를 입력하지 않았으면 다시 login.jsp 딛스패처 방식으로 포워딩(재요청) forward 액션태그 사용 -->
		<jsp:forward page="login2.jsp">
			<jsp:param value="<%=msg%>" name="msg"/>
		</jsp:forward>
		<!-- 액션코드를 쓰면 간결하게 코드 작성이 가능함! -->
	<%			
		}	
	%>
	<h1>환영합니다. <%=userID%>님!!!</h1>
	
	<%!
		//선언문 태그 (전역변수!)
		//login2.jsp 화면에서 아이디를 입력받지않고 로그인버튼을 누르면 재요청에 의해 login2.jsp에 보여질 경고메세지를 변수에 저장
		String msg = "아이디를 입력하지 않았습니다. 아이디를 입력해주세요.";
	%>


</body>
</html>