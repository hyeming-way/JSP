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


//두번째 서블릿인 GetAttribute클래스
// - 각 저장소 역할을 하는 객체메모리 영역에 바인딩된 데이터를 getAttribute("key"); 메소드를 호출해
//   꺼내와서 클라이언트의 웹브라우저로 출력(응답)

@WebServlet("/get")
public class GetAttribute extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//1. ServletContext 객체 메모리 얻기
		ServletContext servletContext = this.getServletContext();
		//getAttribute("key"); 메소드를 호출해서 바인딩된 값을 얻어 출력
		String ctxMesg = (String)servletContext.getAttribute("context");
		out.print("ServletContext객체영역에서 꺼내온 값 : " + ctxMesg + "<br>");
		
		//2. HttpSession 객체 메모리 얻기
		HttpSession httpSession = request.getSession();
		//getAttribute("key"); 메소드를 호출해서 바인딩된 값을 얻어 출력
		String sesMesg = (String)httpSession.getAttribute("session");
		out.print("HttpSession객체영역에서 꺼내온 값 : " + sesMesg + "<br>");
		
		//3. HttpServletRequest 객체메모리는 doGet메소드의 매개변수 request로 받기때문에 얻지않는다.
		String reqMesg = (String)request.getAttribute("request");
		out.print("HttpServletRequest객체영역에서 꺼내온 값 : " + reqMesg + "<br>");
		
	}

}
