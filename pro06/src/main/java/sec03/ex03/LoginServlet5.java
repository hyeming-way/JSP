package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {

	
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 요청한 데이터 문자셋 UTF-8로 설정
		req.setCharacterEncoding("UTF-8");
		
		//2. 요청한 데이터들을 HttpServletRequest객체메모리에서 꺼내오기
		String id = req.getParameter("user_id");
		String pw = req.getParameter("user_pw");
		String address = req.getParameter("user_address");
		
		//3-1. 요청한 웹브라우저에 응답할 데이터 종류(MIME-TYPE) 설정 및 문자셋 방식 UTF-8로 설정
		resp.setContentType("text/html; charset=UTF-8");
		
		//3-2. 요청한 웹브라우저와 연결된 출력스트림 PrintWriter객체 얻기
		PrintWriter out = resp.getWriter();
		
		//3-3. 웹브라우저에 응답할 데이터 생성
		String data = "<html><body>";
		data += "요청한 아이디:" + id + "<br>";
		data += "요청한 비밀번호:" + pw + "<br>";
		data += "요청한 주소:" + address + "<br>";
		data += "</body></html>";
		
		//4. 요청한 웹브라우저로 출력스트림 PrintWriter의 print 메소드를 통해 응답할 데이터 출력
		out.print(data);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
}
