package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 : 클라이언트의 요청을 받고 응답을 처리할 자바 클래스(서버페이지)

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트가 요청한 데이터들은 모두 매개변수로 전달받는 HttpServletRequest객체메모리에 저장되어 있으므로
		//	 요청한 데이터들 중에서 한글이 하나라도 존재하면 한글만 깨져서 꺼내와지므로
		//   문자를 처리할 수 있는 방식(인코딩 방식)을 UTF-8방식으로 설정해서 한글이 깨지는 것을 방지합니다.
		//	 요약 : 요청한 값들을 HttpServletRequest객체메모리에서 꺼내오기 전에 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		//2. login.html화면에서 입력한 값들을 HttpServletRequest객체메모리에서 얻기
		//   요약 : 요청한 값 얻기 -> 방법 : HttpServletRequest의 getParameter("<input>의 name속성값")메소드 호출!
		String user_id = request.getParameter("user_id"); //입력한 아이디가 admin이라면 "admin"반환
		String user_pw = request.getParameter("user_pw"); //입력한 비밀번호가 1234라면? "1234" 반환

		
		//3. 요청한 값을 서블릿(서버페이지)에서 얻는지 확인하기위해 이클립스의 console창에 출력
		System.out.println("입력하여 요청한 아이디 : " + user_id);
		System.out.println("입력하여 요청한 비밀번호 : " + user_pw);
	
		
	}
	
}
