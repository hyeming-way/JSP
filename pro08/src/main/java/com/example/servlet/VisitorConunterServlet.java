package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	사이트(서블릿)에 방문하는 방문자수를 카운트하는 서블릿
	- ServletContext 공유 객체메모리를 활용하여 웹 애플리케이션(pro08) 전역에서 방문자수를 관리함
	- 클라이언트가 브라우저 주소창에 "/visitorCounter"를 입력하여 요청을 보내면 방문자수를 증가시키고 출력함
	- 방문자수는 ServletContext 공유 객체메모리에 저장되므로, 톰캣서버가 종료되지않는 한 유지됨
*/

@WebServlet("/visitorCounter")
public class VisitorConunterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. ServletContext 객체 얻기
		// - ServletContext는 웹 애플리케이션 전반에서 공유되는 객체로,
		//	 데이터를 저장 및 가져오는데 사용됨
		ServletContext context = this.getServletContext();
		
		//2. 방문자 수 가져오기
		// - getAttribute("visitCount");를 사용하여 현재 저장된 방문자수를 가져옴
		// - 만약 방문자 수 데이터가 없다면(null), 기본값으로 0으로 설정하여 방문자수를 1부터 시작하도록 함
		Integer visitCount = (Integer)context.getAttribute("visitCount");
		
		if(visitCount == null) { //방문자 수가 없을 경우 (즉, 애플리케이션이 시작된 후 첫번째 요청일 경우)
			visitCount = 1; //방문자수를 1로 초기화
		}else {
			visitCount++; //기존 방문자수에 1을 추가하여 방문할 때 마다 증가
		}
		
		//3. 방문자 수를 ServletContext 공유 객체메모리에 저장
		// - setAttribute("visitCount", visitCount);를 사용하여 방문자 수를 업데이트 함
		// - 이 데이터는 모든 클라이언트가 공유하므로, 서블릿을 새로 호출할 때 마다 방문자 수가 증가함
		context.setAttribute("visitCount", visitCount);
	
		//4. 톰캣서버 메모리에 방문자수를 기록
		// - context.log()를 사용하면 톰캣서버 콘솔에 메세지가 기록됨
		// - 개발자가 방문자수를 확인하거나 디버깅(테스트)할때 유용하게 사용가능
		context.log("현재 방문자수 : " + visitCount);
		
		//5. 이 사이트(pro08프로젝트 내부의 서블릿)를 요청한 클라이언트의 웹브라우저로 응답
		//응답할 데이터 MIME-TYPE 설정 및 인코딩 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//클라이언트에게 응답 데이터를 출력하기 위한 출력스트림 생성
		PrintWriter out = response.getWriter();
		
		//6. HTML로 응답 출력(브라우저 화면에 방문자수 표시)
		out.println("<html>");
		out.println("<head><title>방문자 수 카운터</title></head>");
		out.println("<body>");
		out.println("<h2>애플리케이션 방문자 수</h2>");
		out.println("<p><strong>현재 방문자 수 : </strong>" + visitCount + "</p>");
		out.println("</body>");
		out.println("</html>");

	}
	
}
