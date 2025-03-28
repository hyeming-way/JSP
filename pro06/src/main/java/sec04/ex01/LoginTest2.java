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
//<form>action속성의 요청 받은 주소  -> http://localhost:8090/pro06/loginTest2
@WebServlet("/loginTest2")
public class LoginTest2 extends HttpServlet {
	
	public void init() {
		System.out.println("init 메소드 호출");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("아이디 : " + id);
		System.out.println("패스워드 : " + pw);
		
		if(id != null && (id.length()!=0)) {
			
			//입력한 아이디가 admin(관리자아이디)로 입력했는지?
			if(id.equals("admin")) {
				//관리자가 로그인한 화면으로 보이게 응답
				out.print("<html>");
				out.print("<body>");
				out.print("<font size='12'>admin 관리자로 로그인하셨습니다!</font>");
				out.print("<input type='button' value='회원정보 수정하기'>");
				out.print("<input type='button' value='회원정보 삭제하기'>");		
				out.print("</body>");
				out.print("</html>");	
				
			} else { //입력한 아이디가 일반아이디로 입력했는지?
				out.print("<html>");
				out.print("<body>");
				out.print(id + " 님!! 로그인 하셨습니다." );
				out.print("</body>");
				out.print("</html>");	
			}

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
