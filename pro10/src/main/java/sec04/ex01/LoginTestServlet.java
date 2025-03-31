package sec04.ex01;

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

@WebServlet("/loginTest")
public class LoginTestServlet extends HttpServlet {

	ServletContext context = null;
	List user_list = new ArrayList();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//1. 이벤트 핸들러(리스너) 역할을 하는 LoginImpl클래스의 객체 생성시 입력받은 요청한 값을 생성자로 전달해 저장시킴
		LoginImpl loginUser = new LoginImpl(user_id, user_pw);
		
		//HttpSession객체 메모리 생성
		HttpSession session = request.getSession();
		
		if(session.isNew()) { //사용자 로그인 접속시 요청한 새로만든 HttpSession이면?
			//HttpSession 객체메모리 영역에 이벤트 핸들러 역할을 하는 LoginImpl클래스의 객체주소 바인딩
			session.setAttribute("loginUser", loginUser); //-> LoginImpl 객체 내부의 valueBound 콜백메소드 호출			
		}
		
		out.print("<html>");
		
			out.print("<head>");
				out.print("<script type='text/javascript'>");
					//자바스크립트의 Window객체의 setTimeout()메소드를 이용해
					//5초마다 서블릿에 재요청하여 현재 접속자 수를 표시하여 웹브라우저에 출력
					out.print("setTimeout('history.go(0);', 5000)"); //history.go 현재 페이지 재요청
				out.print("</script>");
			out.print("</head>");
			
			out.print("<body>");
				//접속자수를 웹브라우저에 출력
				out.print("접속한 아이디 : " + loginUser.user_id + "<br>");
				out.print("총 접속자 수 : " + LoginImpl.total_user + "<br>");
			/*	
				out.print("접속 아이디 : <br>");
				
				List list = (ArrayList)context.getAttribute("user_list");
				
				for(int i=0; i<list.size(); i++) {
					out.println(list.get(i) + "<br>"); 
				}
				
				out.println("<a href='logout?user_id="+ user_id +"'>로그아웃</a>");
			*/	
				
			out.print("</body>");
			
		out.print("</html>");
		
	}

}
