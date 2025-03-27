package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		request.setCharacterEncoding("UTF-8");
		
		//2. RequestDispatcher객체의 forward메소드를 이용해서 포워딩(재요청)하는 방법
		//   -> 디스패처 방법을 이용한 포워딩
		//	    참고.  이 디스패처 방법을 사용하면  두번째 서블릿을 재요청할때
		//      웹브라우저를 거치지 않고 톰캣서버에 의해 자동으로 두번째을 재요청하게 되어
		//      클라이언트가 처음 요청했던 주소 /first가 웹브라우저 주소창에 그대로 남아있게 되지만
		//      재요청 당한 두번쨰 서블릿의 응답결과 를 웹브라우저로 볼수 있습니다.
				
		/*
		사용 문법	
		 	
		RequestDispatcher dispatcher  = request.getRequestDispatcher("재요청할 두번째 서블릿의 매핑 주소 또는 jsp주소");

		//두번쨰 (다른 서블릿 또는 JSP)를 재요청
		 dispatcher.forward(request, response);
		*/
		
		//해설1. getRequestDispatcher메소드는 재요청 할 두번쨰 서블릿의 매핑주소를 
		//		RequestDispatcher객체 메모리에 저장시킵니다.
		//      그리고 RequestDispatcher객체 메모리 주소 자체를 반환해 줍니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("second?name=lee");
		
		//해설2. 첫번째 서블릿에서 얻은 HttpServletRequest객체 주소와
		//                       HttpServletResponse객체 주소를
		//      forward메소드 호출시 매개변수로 전달해서  두번째 서블릿 재요청시 공유해서 사용하게 됩니다.
		dispatcher.forward(request, response);
		
	}

}
