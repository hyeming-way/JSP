package sec01.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
첫번째 서블릿(서버페이지)

순서1. 클라이언트가 웹브라우저 주소창에 첫번째 서블릿을 요청할 주소를 입력해서 요청한다
            요청주소 -> http://localhost:8090/pro08/first

순서2. FirstServlet클래스 내부의 doGet메소드 재구현 코드를 작성하는데
           두번째 서블릿인 SecondServlet서블릿을 포워딩(재요청)할 코드를 작성함
           
       예) location.href = "재요청할 두번쨰 서블릿 매핑주소 또는  재요청할 JSP페이지의 주소";

*/

//@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//포워드  : 다른 서버페이지를 재요청 하는 기술
		
		//포워드 방법중에서 자바스크립트 문법 location객체의 href속성을 이용한 재요청(포워딩) 방법
		//문법
		//	location.href = '재요청할 두번쨰 서블릿 매핑주소 또는 재요청할 JSP페이지의 주소";
		
		request.setCharacterEncoding("UTF-8");
		
		//2. 요청한 클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)설정 및
		//   문자 처리방식(인코딩방식)을 UTF-8로 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//3. 출력스트림 통로 얻기
		PrintWriter out = response.getWriter();
		
		//4. 자바스크립트의 location 객체를 이용한 포워딩
		
		out.print("<script type='text/javascript'>");
		
	    out.print(" window.alert('회원가입 성공! 메인화면으로 이동합니다.');");
		
		out.print(" location.href = 'second'; ");
		
		out.print("</script>");
	}

}
