package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8090/pro08/first 요청 -> 톰캣서버는 HttpservletRequest객체메모리 생성함

//주제 : 첫번째 서블릿에서 HttpservletRequest객체 메모리에 바인딩후
//두번째 서블릿을 리다이렉트 방법으로 포워딩(재요청)하면
//첫번째 서블릿에서 HttpservletRequest객체 메모리를 공유할수 없다!

//@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1. 서블릿간의 재요청의 경우! 문자처리방식 UTF-8로 설정해야 
		//   재요청 받는 두번째 서블릿에서 요청해온 데이터를 공유받을때 
		//   한글이깨지지 않고 공유받아 웹브라우저로 출력할수 있음
		request.setCharacterEncoding("UTF-8");
		
		//참고. 바인딩? 키와 값을 한 쌍으로 묶어서 저장하는 기술
		//	   setAttribute("키", "값"); <-- HttpServletRequest 객체메모리에 키-값으로 바인딩(저장)
		//	   getAttribute("키"); <-- HttpServletRequest 객체메모리에 저장된 키의 값을 얻을 수 있음
		//	   removeAttribute("키"); <-- HttpServletRequest 객체메모리에 저장된 키-값 한 쌍을 제거
		
		//2. 웹브라우저 주소창으로 서블릿 요청하면
		//	 톰캣서버는 요청 하나당 HttpServletRequest 객체메모리 생성 후
		//   doGet메소드의 매개변수 request로 전달하므로
		//   HttpServletRequest객체메모리에
		//	 address 이름(키)과 함께 "서울시 성북구"(값)을 함께 묶어서 저장(바인딩)할 수 있다.
		request.setAttribute("address", "서울시 성북구");
							// "키"	  ,   "값";
		
		//3. 두번째 서블릿을 재요청시 리다이렉트 방법으로 포워딩(재요청)하면?
		//   첫번째 서블릿에서 바인딩 해놓은 HttpServletRequest 객체메모리를
		//   두번째 서블릿으로 공유해서 전달할 수 있을까? -> 안됨~~~~~~~~~~~~~~~~~~~~
		
		//   이유 : 리다이렉트 방법의 포워딩 기술은 웹브라우저를 거쳐서 두번째 서블릿을 재요청시 새로운 요청주소가 주소창에 적히면서
		//   	   재요청하게 되므로 톰캣서버는 새로운 요청임을 인식하고 새로운 HttpServletRequest 객체메모리를 생성해서 두번째 서블릿의
		//  	   doGet메소드의 매개변수로 전달함
		response.sendRedirect("second");
		
	}

}
