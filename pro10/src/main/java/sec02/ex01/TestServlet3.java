package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/*") 
//컨텍스트 주소 pro10/ 뒤에 작성하는 모든 요청주소에 대해 현재 서블릿을 요청할 수 있게 URL 작성
//게시판 서비스를 하는 서블릿의 매핑 주소 설계
//http://localhost:8090/pro10/board/writer
//http://localhost:8090/pro10/board/read
//http://localhost:8090/pro10/board/update
//http://localhost:8090/pro10/board/delete

//회원관리 서비스를 하는 하나의 서블릿을 요청한 매핑 주소 설계
//http://localhost:8090/pro10/member/search
//http://localhost:8090/pro10/member/insert
//http://localhost:8090/pro10/member/update
//http://localhost:8090/pro10/member/delete


//@WebServlet("*.do")
//요청하는 주소전체에서 확장자가 .do로 끝나는 요청주소를 작성하면 현재 서블릿을 요청할 수 있게 작성
//http://localhost:8090/pro10/index.do
//http://localhost:8090/pro10/index2.do
//http://localhost:8090/pro10/index3.do

@WebServlet("*.do")
public class TestServlet3 extends HttpServlet {
	
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
