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

//사용자가 로그아웃 요청을 하면 HttpSession객체를 제거하고, ArrayList에서 로그아웃요청한 사람의 ID를 삭제하는 서블릿
//- 로그아웃처리 서블릿
@WebServlet("/logout")
public class LogoutTest2 extends HttpServlet {
	
	//웹 애플리케이션 전체 공유 메모리 영역인 ServletContext객체 메모리 영역 저장할 변수 
	ServletContext context;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");	
		response.setContentType("text/html; charset=utf-8");	
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		context = this.getServletContext();
		
		HttpSession session = request.getSession(); //없으면 null 반환
		
		String user_id = request.getParameter("user_id");
		
		session.invalidate();
		
		List<String> user_list = (ArrayList<String>)context.getAttribute("user_list");
		
		//현재 로그아웃을 요청한 사용자의 ID를 ArrayList배열에서 삭제
		user_list.remove(user_id);
		
		//기존의 사용자ID가 저장된 ArrayList배열을 ServletContext영역에서 제거하고,
		//변경된 ArrayList배열을 다시 ServletContext영역에 저장해서 업데이트 함
		context.removeAttribute("user_list");
		context.setAttribute("user_list", user_list);
		
		out.println("<br>로그아웃했습니다.");
		
	}

}
