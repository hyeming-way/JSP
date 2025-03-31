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


//@WebServlet("/logout")
public class LogoutTest extends HttpServlet {
	
	ServletContext context;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		context = this.getServletContext();
		
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		
		session.invalidate(); //세션 무효화
		
		List user_list = (ArrayList)context.getAttribute("user_list");
		
		user_list.remove(user_id);
		
		context.removeAttribute("user_list"); //현재 특정 key-value만 제거
		
		context.setAttribute("user_list", user_list);

		out.print("<br>로그아웃했습니다.");
		
	}

}
