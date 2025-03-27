package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//두번째 서블릿
//-> FirstServlet 첫번째 서블릿으로부터 포워드(재요청)되어 요청받은
//	 SecondServlet 두번째 서블릿 클래스

//@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//재요청한 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)를
		//HttpServletResponse 객체에 설정하고 응답할 문자데이터가 한글이 있으면 깨지므로 인코딩방식 UTF-8로 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//재요청한 클라이언트의 웹브라우저로 응답할 출력스트림 PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		
		//현재 두번째 서블릿의 응답
		out.print("<html><body>");
		
		out.print("RequestDispatcher객체의 forward()메소드를 사용해 재요청 당한 SecondServlet 두번째 서블릿이 응답한 화면 <br><br>");
		
		out.print("재요청 당한 두번째 서블릿이 첫번째 서블릿을 요청했을때의 톰캣서버에 의해 생성되는?<br>");
		
		out.print("HttpServletReuquest객체메모리와 HttpServletResponse객체메모리를 공유받아서");
		out.print("사용한 요청 값 얻기 : " + request.getParameter("name"));
		
		out.print("</body></html>");
		
		
	}
	
}
