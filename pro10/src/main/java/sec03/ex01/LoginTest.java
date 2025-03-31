package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//login.html에서 로그인 요청시 <form>태그의 action속성에 작성된 login 서블릿 매핑 주소로 요청받는 서블릿 클래스
@WebServlet("/login")
public class LoginTest extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		
		모든 인코딩 작성을 각 서블릿에서 하지말고 Filter 관련 클래스에서 공통작업으로 처리하자
		  
		request.setCharacterEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		*/
		
		PrintWriter out = response.getWriter();
		
		//입력받은 요청한 값 얻기
		String user_name = request.getParameter("user_name");
		String user_pw = request.getParameter("user_pw");
		
		//입력받은 이름과 비밀번호를 클라이언트의 웹브라우저로 출력
		out.print("<html><body>이름은 " + user_name + "<br> 비밀번호는 " + user_pw + "<br></body></html>");
		
		
	}

}
