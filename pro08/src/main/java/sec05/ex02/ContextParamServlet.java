package sec05.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	ServletContext 객체메모리 주소를 얻어 접근하고 
	getInitParameter()메소드의 매개변수로 초기화 파라미터이름을 전달한 후
	메뉴 텍스트 항목을 가져와 웹브라우저로 출력합니다.
*/

@WebServlet("/initMenu")
public class ContextParamServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//톰캣서버가 웹프로젝트를 실행하면 하나의 ServletContext 객체메모리가 생성됩니다.
		//ContextParamServlet 서블릿 객체메모리에서 접근하기 위한 ServletContext 객체메모리 얻기
		ServletContext context = this.getServletContext();
		
		//web.xml에 설정된 초기화파라미터의 값을 ServletContext객체 메모리 내부에서 얻기
		//"menu_member",  "menu_order",  "menu_goods" 는 
		//web.xml에 설정된 초기화파라미터값이 저장된 변수명들 입니다
		//이 값들은 웹 애플리케이션 전체에 만들어 놓은 모든 서블릿 또는 jsp페이지에서 사용할수 있는
		//공통 파라미터(요청값)입니다. 	
		String menu_member = context.getInitParameter("menu_member");//"회원등록 회원조회 회원수정"
		String menu_order = context.getInitParameter("menu_order");//"주문조회 주문등록 주문수정 주문취소"
		String menu_goods = context.getInitParameter("menu_goods");//"상품조회 상품등록 상품수정 상품삭제"
		
		out.print("<html><body>");
		out.print("<table border='1' cellspacing='0'>");
		out.print("<tr><td>메뉴이름들</td></tr>");
		out.print("<tr><td>" + menu_member + "</td></tr>");
		out.print("<tr><td>" + menu_order + "</td></tr>");
		out.print("<tr><td>" + menu_goods + "</td></tr>");
		out.print("</table>");
		out.print("</body></html>");

	}

}
