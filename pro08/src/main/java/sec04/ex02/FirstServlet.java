package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 첫번째 서블릿(서버페이지) */
//http://localhost:8090/pro08/first 요청 -> 톰캣서버는 HttpservletRequest객체메모리를 생성함

//주제 : 첫번째 서블릿에서 HttpservletRequest객체 메모리에 바인딩후
//   두번째 서블릿을 디스패처 방법으로 포워딩(재요청)하면
//		첫번째 서블릿에서 사용한 HttpservletRequest객체 메모리를 공유할수 있다!
@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		
		//3.  RequestDispatcher객체의 forward메소드를 이용해서 포워딩 하는 방법(디스패처) 사용
		
		//해설1. getRequestDispatcher메소드는 재요청 할 두번쨰 서블릿의 매핑주소를 
		//		RequestDispatcher객체 메모리에 저장시킵니다.
		//      그리고 RequestDispatcher객체 메모리 주소 자체를 반환해 줍니다.	
		RequestDispatcher dispatcher = request.getRequestDispatcher("second");
		
		//해설2. 첫번째 서블릿에서 얻은 HttpServletRequest객체 주소와
		//                       HttpServletResponse객체 주소를
		//      forward메소드 호출시 매개변수로 전달해서  두번째 서블릿 재요청시 공유해서 사용하게 됩니다.
		dispatcher.forward(request, response);
		
	}

}
