package sec06.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//어노테이션 기호를 사용하여 이 클래스가 서블릿(서버페이지)임을 톰캣서버에게 알립니다.
// - name 속성 : 서블릿의 이름을 정의
// - urlPatterns 속성 : 서블릿 요청할 매핑 주소 정의
// - loadOnStartup 속성 : 톰캣서버가 시작될 때 이 서블릿이 즉시 로드되도록 설정하는 속성. 1은 가장 먼저 로드된다는 의미
@WebServlet(name = "loadConfig", urlPatterns = { "/loadConfig" }, loadOnStartup = 1 )
public class LoadAppConfig extends HttpServlet {

	private ServletContext context; //<-- 웹 프로젝트 내부의 모든 서블릿이 공유할 객체메모리
	
	//서블릿 초기화 메소드. 서블릿이 처음 실행될 때 호출됩니다.
	//init()메소드는 서블릿이 로드될때 호출되며, 변수 초기화 작업 담당
	public void init(ServletConfig config) throws ServletException {
		
		// ServletConfig 객체에서 서블릿의 환경 설정 정보를 가져옵니다.
        // getServletContext() 메서드는 ServletContext 객체를 반환합니다.
        // ServletContext는 서블릿 애플리케이션 전체에서 사용할 수 있는 설정 및 데이터를 저장합니다.
		context = config.getServletContext();
		
		// web.xml 또는 @WebServlet 애노테이션에 정의된 초기화 파라미터 값을 가져옵니다.
        // context.getInitParameter()를 사용하여, 해당 파라미터의 값을 읽습니다.
        // 초기화 파라미터는 서블릿이 시작될 때 외부 설정을 통해 전달된 값들입니다.
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		
		//가져온 초기화 파라미터 값들을 ServletContext 공유객체 메모리에 바인딩해서 다른 서블릿으로 공유가능하다.
		context.setAttribute("menu_member", menu_member);
		context.setAttribute("menu_order", menu_order);
		context.setAttribute("menu_goods", menu_goods);	
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		
		
		
		
		
		
	}
	

}
