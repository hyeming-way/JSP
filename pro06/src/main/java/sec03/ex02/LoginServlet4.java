package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {

	//클라이언트가 GET전송방식
	//(웹브라우저 주소창에 주소 입력 요청 또는 <form>태그의 method속성을 get으로 설정해놓고 요청)
	//으로 요청하면 호출되는 메소드 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	//클라이언트가 POST전송방식
	//(<form>태그의 method속성을 post로 설정해 놓고 요청)
	//으로 요청하면 호출되는 메소드 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	//get방식, post방식 요청을 모두 처리하는 일반 메소드
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청한 데이터들 HttpServletRequest객체에서 얻어오기 전체 인코딩 방식 UTF-8 설정
		request.setCharacterEncoding("UTF-8");
		
		//2. 요청한 데이터들 HttpServletRequest객체에서 얻어오기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//3-1. 요청한 웹브라우저에 응답할 데이터 종류 설정 및 인코딩 방식 UTF-8 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//3-2. 응답할 웹브라우저와 연결된 출력스트림 얻기
		PrintWriter out = response.getWriter();
		
		//3-3. 응답할 응답값 생성
		//하나의 문자열로 생성
		//"<body>아이디:admin<br> 비밀번호:1234</body>"
		String data = "<body>아이디:" + user_id + "<br> 비밀번호:" + user_pw + "</body>";
		
		//4. 응답할 데이터 웹브라우저로 응답
		out.print(data);
		
	}
	
	

}
