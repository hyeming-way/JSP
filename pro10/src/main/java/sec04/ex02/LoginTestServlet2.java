package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sec04.ex01.LoginImpl;

@WebServlet("/loginTest2")
public class LoginTestServlet2 extends HttpServlet {
	
	//웹 애플리케이션의 컨텍스트 정보를 저장하는 공유객체(서블릿 페이지간의 값을 공유할 수 있음)
	ServletContext context = null;

	//로그인한 사용자들의 ID를 저장하는 리스트(모든 사용자의 로그인 정보를 저장)
	List<String> user_list = new ArrayList<String>();
	
	//login2.html에서 로그인 요청하면 실행되는 메소드 (POST 방식 요청 처리)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//현재 실행중인 웹 애플리케이션의 ServletContext객체를 가져옴 (톰캣이 미리 생성해놓음)
		context = this.getServletContext();
		
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//사용자의 로그인 정보를 저장하는 LoginImpl 객체 생성
		LoginImpl2 loginUser = new LoginImpl2(user_id, user_pw);
		
		//사용자의 HttpSession 객체 생성
		HttpSession session = request.getSession();
		
		//사용자가 로그인 접속시 요청한 새로 만든 HttpSession이면?
		if(session.isNew()) {
			//HttpSession영역에 이벤트 핸들러 역할을 하는 LoginImpl 클래스의 객체주소를 바인딩
			session.setAttribute("loginUser", loginUser);
			
			//로그인한 사용자의 ID(문자열객체)를 리스트에 추가(로그인 한 사용자 관리하기 위해)
			user_list.add(user_id);
			
			//웹 애플리케이션의 컨텍스트객체에 사용자의 ID가 저장된 ArrayList(리스트)를 바인딩 (모든 사블릿에서 공유 가능)
			context.setAttribute("user_list", user_list); //ArrayList(리스트)를 바인딩

		}
		
		out.print("<html><body>");
		
		out.println("아이디는 " + LoginImpl2.user_id + "<br>");
		
		out.println("총 접속자 수는 " + LoginImpl2.total_user + "명<br><br>");
		
		out.println("접속 아이디 목록:<br>");
		
		List<String> list = (ArrayList<String>)context.getAttribute("user_list");
		
		for(String id : list  ) {
			out.println(id + "<br>");
		}
		
		out.println("<a href='logout?user_id=" + user_id + "'>로그아웃</a>");
		
		out.print("</body></html>");
	
	}

}
