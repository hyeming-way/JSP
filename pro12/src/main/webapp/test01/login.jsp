<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
			login.jsp 화면에서 아이디, 비밀번호를 입력한 후 서버페이지인 login.jsp로 로그인 요청시
			입력한 아이디, 비밀번호를 request 내장객체메모리에 바인딩 후 전달
	-->
	<form action="login.jsp" method="post">
		ID : <input type="text" name="user_id"><br>
		PW : <input type="password" name="user_pw"><br>
		<input type="submit" value="로그인">
		<input type="reset" value="취소">
	</form>
	
	<%
		//<form>태그의 요청 전송방식이 "post"이면? 조건식
		if(request.getMethod().equals("POST")){ //getMethod() 전송방식이 무엇인지 확인하는 메소드. 대문자로 반환해줌
			
			//1. 한글처리
			request.setCharacterEncoding("UTF-8");
			
			//2. 로그인 요청시 입력한 아이디, 비밀번호 얻기
			String id = request.getParameter("user_id");
			String pw = request.getParameter("user_pw");
			
			//참고. DB에 테이블에 저장되어 있는 아이디 : "master"와 
			//비밀번호 : "1111"이 저장되어있다고 가정하고 실습
			
			//DB에 저장되어 있는 조회한 "master"아이디와 login.jsp화면에서 로그인 요청시 입력한 아이디가 같은지 비교
			if("master".equals(id)){
				
				if("1111".equals(pw)){ //아이디, 비밀번호 모두 같으면?
						
					//로그인한 화면을 보여주기 위해 HttpSessoin 내장객체메모리에 입력한 아이디 바인딩
					session.setAttribute("id", id);
				
					//메인 홈페이지(index.jsp)을 재요청(포워딩) - HttpSession 내장객체 메모리 유지 될 것 임
					//참고. 리다이렉트 방법으로 포워딩
					response.sendRedirect("index.jsp");
					
				} else { //아이디는 같으나 비밀번호가 다르면?
	%>
					<script>
						window.alert("비밀번호가 틀립니다.");
						location.href = "login.jsp";
					</script>
	<%							
				}
				
			} else { //아이디가 같지 않은 경우
				
				//아이디는 같지 않으나 DB "1111"비밀번호와 입력한 비밀번호가 같으면?
				if("1111".equals(pw)){
	%>
					<script>
						window.alert("아이디만 틀립니다.");
						location.href = "login.jsp";
					</script>
	<%				
				} else { //아이디, 비밀번호 모두 다르면?
	%>				
					<script>
						window.alert("아이디, 비밀번호 둘다 틀립니다.");
						location.href = "login.jsp";
					</script>					
	<%											
				}
	%>
			
	<%							
			}		
		}		
	%>
</body>
</html>