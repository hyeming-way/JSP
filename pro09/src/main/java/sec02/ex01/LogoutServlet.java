package sec02.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그아웃 요청이 들어오면 웹브라우저에 저장된 쿠키 객체를 삭제하는 서블릿
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. "user_id"쿠키명을 가진 쿠키객체를 웹브라우저에서 삭제하기 위해
		//   같은 쿠키명을 가진 쿠키객체 생성 (이 때 쿠키값은 "" 빈공백문자열 넣기)
		
		Cookie cookie = new Cookie("user_id", "");
		
		//2. 쿠키객체의 만료시간을 0으로 설정하여 쿠키객체를 웹브라우저에서 즉시 완전히 삭제되도록 함
		cookie.setMaxAge(0);
		
		//3. 쿠키객체가 적용될 경로를 기존과 동일하게 설정해야 정상적으로 삭제됨
		cookie.setPath("/"); //<-- / 는 절대경로 
		
		//4. 브라우저(클라이언트)에게 삭제된 쿠키객체를 적용하도록 응답 메세지에 추가
		response.addCookie(cookie);
		
		//5. 로그아웃 후 로그인 페이지를 재요청(포워딩)해서 이동
		response.sendRedirect("login3.html");
		
	}
	
}
