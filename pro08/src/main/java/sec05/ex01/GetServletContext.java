package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//엣지 웹브라우저 주소창에 
//http://localhost:8090/pro08/cget요청주소를 입력하여 아래의 서블릿 요청

//설명 : 
//getServletContext메소드를 호출해 ServletContext객체 메모리주소를 얻어와 접근합니다.
//그리고 getAttribute(key); 메소드를 호출하여 ServletContext객체 메모리 안에 바인딩되었던
//ArrayList배열을 가져와 사용 합니다.

@WebServlet("/cget")
public class GetServletContext extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//톰캣서버가 생성해놓은 ServletContext 공유 객체의 주소를 얻어 저장
		ServletContext context = this.getServletContext(); //this 생략 가능
		
		//ServletContext 서블릿에서 ServletContext 공유 객체메모리에 바인딩한
		//ArrayList배열을 꺼내옵니다.
		List list = (List)context.getAttribute("member");
		
		//ArrayList배열에 저장된 이름과 나이 정보를 얻어 출력
		String name = (String)list.get(0); //"이순신" 문자열 객체 얻기
		int age = (Integer)list.get(1); //new Integer(30);
		
		out.print(name + "<br>" +age);
		
		//결론 : 이번 예제는 ServletContext 공유 객체메모리 영역을 사용해서
		//		하나의 웹애플리케이션(pro08) 내부에 만들어 놓은 모든 서블릿(서버페이지)들이
		//		공유해서 사용할 데이터가 있을 때 저장(바인딩)해서 얻어 사용하는 예제입니다.
		
	}

}
