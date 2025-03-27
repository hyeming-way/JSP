package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//주제 : 웹페이지를 연동(연결)하는 방법 중 <input type="hidden">(<hidden>태그)를 이용해
//		웹페이지들(login.html -> LoginServlet.class) 사이의 정보를 공유합니다.

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청한 데이터들 인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		
		//2. 요청받은 데이터들을 HttpServletRequest 객체메모리에서 꺼내 각 변수에 저장
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		String user_email = request.getParameter("user_email");
		String user_hp = request.getParameter("user_hp");
		
		//3. 요청한 값들 이용해 요청한 클라이언트의 웹브라우저로 응답할 데이터 생성
		String data;
		data = "안녕하세요!<br>"
				+ "로그인하셨습니다.<br><br>"
				+ "입력한 아이디 : " + user_id + "<br>"
				+ "입력한 비밀번호 : " + user_pw + "<br>"
				+ "주소 : " + user_address + "<br>"
				+ "이메일 : " + user_email + "<br>"
				+ "휴대전화 : " + user_hp;
		
		//4. MIME-TYPE 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//5. 출력스트림 객체 얻기
		PrintWriter out = response.getWriter();
		
		//6. 웹브라우저로 출력
		out.print(data);
		
		/*		
			안녕하세요!
			로그인하셨습니다.
			
			입력한 아이디 : admin
			입력한 비밀번호 : 1234
			주소 : 서울시 성북구
			이메일 : test@gmail.com
			휴대전화 : 010-111-2222	
		 */	
		
	}
	
}
