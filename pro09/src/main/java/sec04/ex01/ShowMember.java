package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//두번쨰 서블릿(로그인 한 회원의 회원정보를 보여주면서 로그인 된 화면을 보여주자) 
/*
먼저 로그인 된 상태를 나타내기 위해  기존 LoginServlet5내부에서 만들었었던 HttpSession메모리를 얻은다음
내부에 바인딩된  isLogOn key에 해당하는 true값을 가져와 로그인 상태의 화면을 보여줍니다.
그리고 isLogon의 값이 true이면 같이 저장된 회원정보(입력한 아이디, 비번)을 불러와 같이 화면에 출력합니다
마지막으로 HttpSession객체 메모리가 존재하지 않거나  isLogOn key에 해당하는 값이 false이면 
다시 로그인 요청할수 있도록 포워딩(재요청)해서 이동한 login4.html화면을 보여 줍니다.

*/

@WebServlet("/show")
public class ShowMember extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
		
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String id = "", pwd = "";
		
		Boolean isLogon = false;
		
		//이미 LoginServlet5에서 HttpSession을 생성해놓고 바인딩했으므로
		//기존에 만들어 놓았던 HttpSession 객체 메모리의 주소를 얻는다.
		HttpSession session = request.getSession(false);
		
		if(session != null) { //먼저 기존 HttpSession 객체메모리가 톰캣서버메모리에 유지되고 있고
			//로그인 되었다라는 로그인 인증값 true 받아와서 저장
			isLogon = (Boolean)session.getAttribute("isLogon");
			
			if( isLogon ) { //로그인된 상태에서 ShowMember 서블릿 페이지로 온 상태이므로 회원 정보를 화면에 응답
				
				id = (String)session.getAttribute("login.id");
				pwd = (String)session.getAttribute("login.pw");
				
				out.print("이 ShowMember서버페이지는 서브화면을 웹브라우저로 응답한 내용입니다. <hr>");
				out.print("안녕하세요." + id + "님!! 로그인되셨어요.");
				out.print("<hr><hr>");
				out.print("아이디 : " + id + "<br>");
				out.print("비밀번호 : " + pwd + "<br>");
						
			} else { //isLogon 변수의 값이 false이면 미로그인이라는 의미이므로
				//포워딩(재요청)
				response.sendRedirect("login5.html");
			}
			
		} else {
			
			//포워딩(재요청)
			response.sendRedirect("login5.html");
		}	
		
	}//doHandle

}
