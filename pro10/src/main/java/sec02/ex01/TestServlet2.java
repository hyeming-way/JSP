package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨텍스트 주소 : 톰캣 서버가 요청 URL 전체를 받았을때 톰캣서버가 프로젝트에 접근할 수 있는 경로
//			   -> /pro10

// /first/*		URL패턴은 첫번째주소 /first는 무조건 일치하나
//				두번째 /뒤에 여러가지 주소를 작성하여 서블릿을 요청할 수 있다.


@WebServlet("/first/*") 
public class TestServlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//HttpServletRequest 객체의 메소드들!
		//1. 위 요청한 URL 주소중에서 컨텍스트주소만 반환하는 메소드
		String contextPath = request.getContextPath();
		System.out.println("getContextPath()메소드 호출 후 반환받은 컨텍스트 주소 : " + contextPath);
		//getContextPath()메소드 호출 후 반환받은 컨텍스트 주소 : /pro10
		
		//2. 위 요청한 URL주소 전체를 반환하는 메소드
		String url = request.getRequestURL().toString();
		System.out.println("getRequestURL()메소드 호출 후 반환받은 클라이언트가 요청한 전체 주소 : " + url);
		//getRequestURL()메소드 호출 후 반환받은 클라이언트가 요청한 전체 주소 : http://localhost:8090/pro10/first/test
		
		//3. 위 요청한 URL 주소중에서 서블릿 매핑이름(주소)만 반환하는 메소드
		String mapping = request.getServletPath();
		System.out.println("getServletPath()메소드 호출 후 반환받은 클라이언트가 요청한 서블릿 매핑 주소 : " + mapping);
		//getServletPath()메소드 호출 후 반환받은 클라이언트가 요청한 서블릿 매핑 주소 : /first/test
		
		//4. 위 요청한 URL 주소중에서 URI 주소만 반환하는 메소드
		String uri = request.getRequestURI();
		System.out.println("getRequestURI()메소드 호출 후 반환받은 클라이언트가 요청한 주소 중 URI 주소 : " + uri);
		//getRequestURI()메소드 호출 후 반환받은 클라이언트가 요청한 주소 중 URI 주소 : /pro10/first/test
		
		
	}

}
