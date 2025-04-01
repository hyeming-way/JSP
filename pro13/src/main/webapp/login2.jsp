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
	//상황1. 최초로 login2.jsp를 요청하면 request 객체 메모리영역은 톰캣서버에 의해 하나 생성된다.
	//      request 객체영역에는 아무런 값도 저장되어있지않기때문에 null이 저장되어 있으므로
	//		판단해서 경고메세지를 보여주지말자
	
	//상황2. login2.jsp 화면에서 아이디를 입력하지않고 로그인 버튼을 눌러
	//		result2.jsp로 요청해서 갔다가 다시 login2.jsp를 재요청해서 오는 상황
	//		이 상황에서는<jsp:param value="<%=msg>"name="msg"/>
	//		액션태그에 설정한 경고메세지값을 request에 꺼내서 사용
	
	String msg = request.getParameter("msg");
				//"아이디를 입력하지 않았습니다. 아이디를 입력해 주세요" <- 상황2.
			    //또는
			    //null  <- 상황1.

	if(msg != null){ 
		out.print("<h1>" + msg + "</h1>");
	}

%>

	<form action="result2.jsp" method="post">
		아이디 : <input type="text" name="userID"><br>
		비밀번호 : <input type="password" name="userPW"><br>
		
		<!-- 전송 요청 버튼으로 만들 수 있는 경우 -->
		<input type="submit" value="로그인">
<!-- 		<button>로그인</button> -->
<!-- 		<input type="image" src="s.png"> -->

		<input type="reset" value="다시입력">
	</form>
</body>
</html>