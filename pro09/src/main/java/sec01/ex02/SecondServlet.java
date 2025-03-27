package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(name = "second", urlPatterns = { "/second" })
@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 첫번째 서블릿 LoginServlet에서 <a>를 클릭해 요청받은 데이터는 모두 HttpServletRequest 객체메모리에 저장되어있음
		//   서울시 성북구 주소는 한글이므로 인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		
		//1-1. 웹브라우저로 응답할 데이터유형(MIME-TYPE)설정
		response.setContentType("text/html; charset=UTF-8");
		
		//1-2. 출력스트림 생성
		PrintWriter out = response.getWriter();
		
		//2. 첫번째 서블릿 LoginServelet에서 <a>를 클릭해 요청한 데이터들을
		//   HttpServletRequest 객체메모리(저장소 역할의 메모리)에서 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");

		//3. 첫번째 서블릿 LoginServelet에서 전달한 입력한 아이디정보를 이용해 로그인 상태를 유지시키는 모습을 보여주자
		
		//입력한 아이디가 존재하면?
		if( user_id != null && user_id.length() != 0 ) {
			out.print("이미 로그인 된 상태입니다. <br><br>");
			out.println("첫번째 서블릿으로부터 넘겨받은 아이디 : " + user_id + "<br>");
			out.println("첫번째 서블릿으로부터 넘겨받은 비밀번호 : " + user_pw + "<br>");
			out.println("첫번째 서블릿으로부터 넘겨받은 주소 : " + user_address + "<br>");
		} else { //첫번째 서블릿에서 전달한 아이디를 받을 수 없으면?
			out.println("로그인을 하지않으셨습니다.<br><br>");
			out.println("다시 로그인 하고 오세요. <br><br>");
			out.println("<a href='/pro09/login2.html'>로그인창으로 이동하기</a>");
			
		}
		
		
		
		
		
		
		
		
		
	}

}
