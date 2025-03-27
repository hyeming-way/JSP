package sec05.ex01;

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

/*
getServletContext()메소드를 호출해서  반환 값으로  ServletContext객체메모리를 얻은다음
ArrayList배열에  이름과 나이를 바인딩(저장)한 후 ~~
ArrayList배열을 ServletContext객체메모리에 setAttribute메소드를 호출해 바인딩합니다.

클라이언트는 크롬브라우저를 이용해 주소창에 http://localhost:8090/pro08/cset요청주소를
입력하여 아래의 SetservletContext서블릿을 요청하면
톰캣 서버는 웹프로젝트(컨텍스트) pro08 하나에 관한 저장소인 ServletContext객체 메모리를 생성하여
톰캣 서버 메모리에 보관 해 둡니다.
*/

@WebServlet("/cset")
public class SetServletContext extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//톰캣서버가 생성해놓은 ServletContext 객체메모리의 주소를 얻어 저장
		//설명 : 현재 작성하는 SetServletContext서블릿이 속한 웹프로젝트 전체에서
		//		데이터를 공유할 공간 메모리인? ServletContext객체를 톰캣으로부터 얻습니다.
		//참고. ServletContext 객체메모리는 
		//	   웹프로젝트(pro08) 전체에서 공유되는 데이터들을 저장해놓고
		//	   모든 서버페이지(서블릿 또는 JSP들)에서 공유하는 메모리입니다.
		ServletContext context = this.getServletContext();
		
		//ArrayList배열을 생성해서 "이순신"과 30을 저장해놓자
		List list = new ArrayList();
		list.add("이순신");
		list.add(30);
		
		//ServletContext 공유 객체메모리 내부에 ArrayList배열 자체를 바인딩
		//이렇게 바인딩하면 pro08 웹 프로젝트 내부에 만들어져 있는 모든 서블릿에서 
		//ServletContext 공유 객체메모리를 공유받아 접근해서 ArrayList배열을 꺼내서 사용할 수 있다.
		context.setAttribute("member", list);
		
		out.print("이순신 과 30을 ArrayList배열에 저장하고,"
				+ " ArrayList배열 자체를 ServletContext 공유 객체메모리에 바인딩");
	
	}	
	
}
