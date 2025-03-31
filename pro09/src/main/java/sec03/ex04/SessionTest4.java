package sec03.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//서블릿

/*
순서1. login4.html에서 아이디, 비밀번호 입력후 로그인 버튼을 클릭해 아래의 서블릿으로 로그인 요청합니다.

순서2. doPost메소드가 호출되면서 매개변수로 request를 받습니다. 
   request에는 입력한 아이디, 비번이 저장되어 있음
   doPost메소드에서 doHandle메소드를 호출시 request를 매개변수로 전달해서 사용합니다.
   
순서3. doHandle메소드 내부에서 로직을 작성 합니다.

*/

@WebServlet("/login4")
public class SessionTest4 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//요청한 클라이언트 고유 HttpSession 객체메모리 새로 생성
		HttpSession session = request.getSession();
		
		//요청한 데이터 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//HttpSession 객체메모리가 새로 생성된 메모리이면? (최초로 로그인 요청 했을 경우)
		if( session.isNew() ) {
			
			//만약 로그인 요청시 아이디를 입력했다면?
			if( user_id != null && !user_id.trim().isEmpty() ) {
				
				//HttpSession 객체메모리에 user_id 변수의 값을 저장(바인딩)하고 로그인 상태를 확인하는 <a>링크를 표시
				session.setAttribute("user_id", user_id);
				out.println("<a href='login4'>로그인 상태 확인 요청</a>");
				
			} else { //아이디를 입력하지않았다면?
				
				out.println("<a href='login4.html'>다시 로그인 하세요</a>");
				session.invalidate(); //톰캣 메모리에 만약 올라가있으면 HttpSession 객체메모리 제거
				
			}
			
		} else { //최초로그인이 아니면?
			//HttpSession이 이미 존재하는 경우 (사용자가 이미 로그인을 했다거나 "login4" 페이지를 방문한 경우)
			//HttpSession에 저장된 uset_id 속성을 가져와 사용자가 로그인 되었는지 확인합니다.
			user_id = (String)session.getAttribute("user_id");
			
			if(user_id != null && user_id.length() != 0 ) {
				
				out.print("안녕하세요 " + user_id + "님!! 로그인 중....");
				
			}else {
				
				out.print("<a href='login4.html'>다시 로그인 하세요</a>");
				session.invalidate(); 
				
			}
			
		}
		
	}//doHandle

}
