package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
	클라이언트가 최초 웹브라우저로 요청하는 서블릿
	
	하는 일 : HttpSession 내장객체메모리 영역을 만들어 특정 데이터를 바인딩(묶어서 저장)시킴
			 그리고 작성한 <a>태그를 클릭하면 session1.jsp 서버페이지를 요청해서
			 HttpSession 내장객체메모리를 공유시킵니다.
*/

@WebServlet("/sess")
public class SessionTest extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. HttpSession 내장객체메모리 생성
		HttpSession session = request.getSession();
		
		//2. HttpSession 내장객체메모리에 이름, 값 한쌍의 형태로 묶어서 바인딩(저장)
		session.setAttribute("name", "이순신");
		
		//3. <a>태그를 만들어 웹브라우저에 출력해 보여주고 클라이언트에게 클릭을 유도
	
		//3-1. 클라이언트의 웹브라우저로 응답할 데이터 종류 설정 및 문자처리방식(인코딩) 설정
		response.setContentType("text/html; charset=utf-8");
		//3-2. 클라이언트의 웹브라우저로 출력할 출력스트림 통로 생성
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h1>HttpSession 내장객체메모리 내부에 데이터를 바인딩함</h1>");
		out.println("<a href='/pro12/test01/session1.jsp'>첫번째 session1.jsp 요청해서 보여줘</a>");
		out.println("</body></html>");

	}

}
