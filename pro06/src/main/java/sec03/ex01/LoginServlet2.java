package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//login2.html에서 요청하는 서블릿

@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트가 요청한 데이터들을 HttpServletRequest객체메모리 내부에서
		//   꺼내와 얻기 전에 한글데이터 문자처리방식 UTF-8방식으로 설정
		//   요약 : 한글처리
		request.setCharacterEncoding("UTF-8"); //ISO-8859-1 방식 : 유럽문자 인코딩
											   //EUC-KR 방식 : 한글만 인코딩
											   //UTF-8 방식 : 전세계 문자 인코딩
		
		
		//2. 요청한 데이터 HttpServletRequest객체메모리 내부에서 꺼내어 얻기
		//   요약 : 요청한 값들 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		
		//3-1. 요청한 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)을
		//     HttpServletResponse객체메모리에 설정
		//     설정할 값 : 응답할 데이터는 text기반 HTML데이터 모두 한글처리해서 응답하겠다고 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//3-2. 요청한 클라이언트의 HttpServletResponse객체의 getWriter()메소드를 호출하면
		//     반환값으로 클라이언트의 웹브라우저와 연결되어 데이터를 출력할 출력스트림 통로역할을 하는
		//     PrinWriter객체를 반환받을 수 있다.
		//     요약 : 요청한 클라이언트의 웹브라우저와 연결된 출력스트림 통로 PrintWriter객체 얻기
		PrintWriter out = response.getWriter();
		
		//3-3. 요청한 데이터를 이용해 클라이언트의 웹브라우저로 응답할(출력할) 데이터 생성(마련)
		String data = "<html>";
			   data += "<body>";
			   		data += "입력한 아이디 : " + user_id + "<br>";
			   		data += "입력한 비밀번호 : " + user_pw + "<br>";
			   data += "</body>";
			   data += "</html>";
			   
		//4. 클라이언트의 웹브라우저로 응답할 데이터 출력
	    //PrintWriter객체의 print 또는 println메소드를 이용하여 클라이언트의 웹브라우저로 응답
		out.print(data);
		
	}

}
