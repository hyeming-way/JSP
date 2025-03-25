package sec04.ex01;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//login.html에서 아이디,비밀번호를 입력하고 로그인 버튼을 눌러 로그인 요청을하면
//요청을 받는 서블릿LoginTest 클래스 
//<form>action속성의 요청 받은 주소  -> http://localhost:8090/pro06/loginTest
@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	
	public void init() {
		System.out.println("init 메소드 호출");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.login.html에서 입력한 아이디 , 비밀번호(서블릿으로 요청한 값들)을 
		//  HttpServletRequest객체 메모리에서 꺼내와 얻기전에
		//  한글일 경우 꺠져서 꺼내와지기 떄문에 , 문자를 처리할수 있는 방식(UTF-8)로 설정
		//  요약  :   HttpServletReuquest객체 메모리에 저장된 요청데이터 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2.login.html에서 입력한 아이디, 비밀번호를 HttpServletRequest객체 메모리에서 꺼내와 얻기
		//  요약 : 서블릿으로 요청한 값들 얻기 
		//아이디 : <input type="text" name="user_id"> <br>
		//비밀번호 : <input type="password" name="user_pw"> <br>
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("아이디 : " + id);
		System.out.println("패스워드 : " + pw);
		
		//3. login.html화면을 띄워 요청했던 클라이언트의 웹브라우저로 응답(출력)할 데이터 종류(MIMETYPE)설정
		//   그리고 응답할 데이터 문자처리방식(인코딩방식)을 UTF-8로 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//4. 클라이언트의 웹브라우저로 데이터를 내보내어 출력할 출력스트림 통로 (PrintWriter)생성(얻기)
		PrintWriter out = response.getWriter();
			
		if(id != null && (id.length()!=0)) {
			out.print("<html>");
			out.print("<body>");
			out.print(id + " 님!! 로그인 하셨습니다." );
			out.print("</body>");
			out.print("</html>");	
		} else { //입력한 ID와 비밀번호가 없으면 다시 로그인창으로 이동합니다.
			out.print("<html>");
			out.print("<body>");
			out.print("아이디를 입력하세요!!!");
			out.print("<br>");
			out.print("<a href='http://localhost:8090/pro06/test01/login.html'>로그인창으로 이동</a>");
			out.print("</body>");
			out.print("</html>");		
		}	
		
	}

	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}

}
