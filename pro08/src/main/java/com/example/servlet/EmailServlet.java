package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	이메일 발송 서블릿
	- ServletConfig 객체메모리 영역을 사용하여 초기화 파라미터로 이메일 발송 서버 주소와 포트번호를 받아옴
	- 클라이언트가 이 서블릿을 요청하면 설정된 서버로 이메일을 발송하는 기능을 구현
*/

public class EmailServlet extends HttpServlet {

	//서버 설정을 저장할 변수들
	private String serverAddress; //이메일 서버의 주소(예: smtp.example.com)
	private int serverPort; //이메일 서버의 포트번호 (예: 587)
	
	//init, doGet메소드 오버라이딩
	
	/*
		서블릿 초기화(EmailServlet클래스의 변수값 초기화) 메소드
		- 서블릿이 처음 실행될 때 호출됩니다.
		- 주로 서블릿이 실행되기 전에 필요한 변수값 설정을 초기화하는데 사용됩니다.
		- ServletConfig 객체메모리는 이 서블릿에 대한 설정을 가져오는 객체 메모리입니다.
	*/
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config); //부모 클래스(HttpServlet)의 init()메소드를 호출하여 서블릿을 초기화합니다.
		
		//서버주소와 포트번호를 초기화 파라미터에서 읽어옵니다. web.xml에 설정된 변수들의 값을 얻자
		serverAddress = config.getInitParameter("serverAddress"); //"smtp.example.com" 이메일서버주소 얻기
		String port = config.getInitParameter("serverPort");
		
		
		try {
			//문자열로 받은 포트번호를 숫자로 변환해야하므로 Integer.parseInt("587");호출
			serverPort = Integer.parseInt(port);
		} catch (NumberFormatException e) {
			//만약 숫자가 아닌값을 포트번호로 받으면 예외가 발생함
			//이 경우, 예외처리를 해서 적절한 오류메세지를 표시함
			//-> 예외가 발생하면 서블릿을 실행할 수 없으므로 예외를 던집니다.
			throw new ServletException("Invalid port number:" + port);
		}
		
		//디버깅(테스트)을 위해 서버 설정을 콘솔에 출력합니다.
		System.out.println("이메일 서버 주소 : " + serverAddress);
		System.out.println("이메일 서버 포트 : " + serverPort);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h2>이메일 발송 서버 정보</h2>");
		out.println("<p><strong>서버 주소:</strong>" + serverAddress + "</p>");
		out.println("<p><strong>서버 포트:</strong>" + serverPort + "</p>");
		out.println("<p>이메일을 발송하는 기능이 구현되었습니다.</p>");
		out.println("</body></html>");
		
	}
	
}
