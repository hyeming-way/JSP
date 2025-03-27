package sec06.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//http://localhost:8090/pro08/sInit 주소요청 또는
//http://localhost:8090/pro08/sInit2 주요요청을 하면 호출되는 서블릿 

@WebServlet(
		
		//urlPatterns속성을 이용해 매핑주소를 여러개 설정할수 있다.
		//코드에서는 /sInit와 /sInit2 두 가지 URL을 지정하였습니다. 
		//즉, 사용자가 이 두 URL로 요청을 보내면 이 서블릿이 실행됩니다.
		urlPatterns = { 
				"/sInit", 
				"/sInit2"
		}, 
		//서블릿이 사용할 초기 변수들을 만들어 놓고 사용할 수 있다. (ServletConfig객체에 저장됨)
		initParams = { 
				@WebInitParam(name = "email", value = "admin@jweb.com"), 
				@WebInitParam(name = "tel", value = "010-1111-2222")
		})

public class InitParamServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//email 초기변수에 저장된 값을 얻어 변수에 저장
		String email = this.getInitParameter("email");
		
		//tel 초기변수에 저장된 값을 얻어 변수에 저장
		String tel = this.getInitParameter("tel");
		
		out.print("email : " + email + "<br>");
		out.print("tel : " + tel + "<br>");
		
	}

}
