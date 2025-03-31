package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	실행 테스트 흐름
	1. 클라이언트가 login3.html에서 아이디, 비밀번호 입력 후 로그인 버튼 클릭
	2. LoginServlet에서 아이디와 비밀번호 확인 후 로그인 성공시 쿠키 생성
	3. 클라이언트가 WelcomServlet에 접근하면 쿠키를 확인하여 로그인 상태 유지
	4. 클라이언트가 logout을 클릭하면 LogoutServlet에서 쿠키 삭제 후 로그아웃 처리
*/

//로그인 처리 및 쿠키 생성
@WebServlet(name = "login3", urlPatterns = { "/login3" })
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//DB의 테이블에 아이디:"admin", 비밀번호:"1234"가 저장되어 있다고 가정하고 조회해 왔다고 생각하자 
		
		//간단한 로그인 검증(실제 서비스에서는 DB에서 확인해야함
		if( username.equals("admin") && password.equals("1234") ) {
			
			//로그인 성공시 쿠키 객체 생성 (쿠키이름 : user_id, 쿠키값 : username 변수 값)
			Cookie userCookie = new Cookie("user_id", username );
			
			//쿠키객체의 유효기간을 7일로 설정 (60초*60분*24시간*7일)
			userCookie.setMaxAge(60*60*24*7);
			
			//쿠키를 전체 웹프로젝트 경로에서 유효하도록 설정
			userCookie.setPath("/"); //<-- 보안에 좋음
			
			//JavaScript에서 쿠키 접근 차단 (XSS 공격 방지)
			userCookie.setHttpOnly(true);
			
			//브라우저(클라이언트)에게 쿠키를 저장하도록 HttpServletResponse 객체에 쿠키 저장
			response.addCookie(userCookie);
			
			//로그인 성공 후 환영 페이지를 재요청(포워딩)
			response.sendRedirect("welcome");

		} else {
			//로그인 실패시 오류메세지 출력
			response.getWriter().println("로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
		}
	
	}

}
