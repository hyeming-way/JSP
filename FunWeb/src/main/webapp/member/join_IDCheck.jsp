<%@page import="member.MemberDAO"%>
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
		//1. 현재 join_IDCheck.jsp 서버페이지로 요청받은 아이디 문자 인코딩
		request.setCharacterEncoding("UTF-8");
	
		//2. join.jsp의 function winopen()함수에 의해서 전달받은 입력한 아이디 얻기
		//2-1. 밑의 아이디 다시 중복확인 버튼을 클릭하면 <form>태그에 의해서
		//     다시 join_IDCheck.jsp서버페이지로 요청시 입력된 아이디 얻기
		String id = request.getParameter("userid");
		
		//3. MemberDAO클래스의 객체를 생성해서
		//   idCheck메소드 호출시 매개변수로 입력한 아이디를 전달해 DB의 member테이블에 있는지 없는지 유무 확인
		MemberDAO memberDAO = new MemberDAO();
		
		//아이디 중복 체크 유무값 가져오기
		// -> 1(아이디 중복) 또는 0(사용가능한 아이디) 반환
		int check = memberDAO.idCheck(id);
	
		//4. check변수가 1이면 "아이디 중복" 웹브라우저로 응답
		//	 check변수가 0이면 "사용가능함" 웹브라우저로 응답
		if(check ==1) out.print("아이디 중복");
		else out.print("사용가능함");
	%>
	
	<!-- 5. 사용가능한 아이디이면 사용함 버튼을 눌러서 부모창(join.jsp)에 사용가능한 아이디 보여주기 -->
	<button onclick="result();">사용함</button>
	
	<form action="join_IDCheck.jsp" method="post" name="nfr">
		아이디 : <input type="text" name="userid" value="<%=id%>">
		<button type="submit">아이디 중복 다시 확인</button>	
	</form>
	
	<script>
		//6. 사용가능함 버튼을 클릭했을때 호출되는 함수
		//   바로 위 <input>에 다시 입력한 사용가능한 아이디를 얻어
		//   부모창(join.jsp)페이지에 입력되어 있는 아이디 입력<input>의 value속성의 값으로 설정해서 보여주자
		function result(){
		//join.jsp부모창의 아이디를 입력하는 <input value=""> = join.IDCheck.jsp자식팝업창의 <input>에 입력된 value값 얻자;
			opener.document.fr.id.value = document.nfr.userid.value;
			
			//자식팝업창 닫기
			window.close();			
		}	
		
	</script>
	
	
</body>
</html>