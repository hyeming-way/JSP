package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//주제 : 각 서블릿 API에 바인딩된 속성의 스코프(영역)에 대해 알아보자

//ServletContext 객체 메모리 영역 - 전체 프로젝트 pro10 내부에서 모든 서블릿에서 값을 공유하고자 할 때 
//값을 바인딩 해놓고 공유하는 객체메모리 영역

//HttpSession 객체 메모리 영역 - 하나의 웹브라우저 창이 닫히기 전까지는 모든 서블릿에서 값을 공유하고자 할 때
//값을 바인딩해놓고 공유하는 객체 메모리 영역

//HttpServletRequest 객체 메모리 영역 - 클라이언트가 웹브라우저로 서블릿을 요청하면 톰캣서버에 의해 생성되는
//클라이언트의 요청 데이터들을 바인딩 해놓고 공유하는 객체 메모리 영역


@WebServlet("/set")
public class SetAttribute extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//각 객체메모리 영역(스코프)에 바인딩할 문자열 데이터들 변수에 저장
		String ctxMesg = "ServletContext객체 메모리에 바인딩할 문자열";
		String sesMesg = "HttpSession객체 메모리에 바인딩할 문자열";
		String reqMesg = "HttpServletRequest객체 메모리에 바인딩할 문자열";
		
		//1. ServletContext 객체 메모리 얻기
		ServletContext servletContext = this.getServletContext();
		
		//2. HttpSession 객체 메모리 얻기
		HttpSession httpSession = request.getSession();
		
		//3. HttpServletRequest 객체메모리는 doGet메소드의 매개변수 request로 받기때문에 얻지않는다.
		
		//각 저장소 역할을 하는 객체 메모리 영역들에 바인딩(key-value 한쌍으로 묶어서 저장)
		//방법 : setAttribute("key", "value");
		servletContext.setAttribute("context", ctxMesg);
		httpSession.setAttribute("session", sesMesg);
		request.setAttribute("request", reqMesg);
		
		out.print("각 저장소 역할을 하는 객체 메모리 영역들에 바인딩 했습니다.");

	}

}
