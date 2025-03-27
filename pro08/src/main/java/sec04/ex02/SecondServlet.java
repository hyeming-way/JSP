package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//두번째 서블릿
//-> FirstServlet 첫번쨰 서블릿으로 부터 포워드(재요청)되어 요청받은
// SecondServlet 두번째 서블릿 클래스.
//재요청 받는 주소 :  http://localhost:8090/pro08/second

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 매개변수 request로 받은 HttpServletRequest 객체메모리의 데이터 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2. 첫번째 서블릿에서 작성한 request.setAttribute("address", "서울시 성북구");에 의해 바인딩한 정보를
		//   한번 꺼내와서 사용해보자
		String address = (String)request.getAttribute("address");
		
		//3. 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)와 인코딩 방식 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//3-1. 출력스트림 통로 만들기
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("재요청받아 공유해서 출력하는 값 : " + address ); //null
		out.print("</body></html>");
		
		
	}
	
}
