package sec01.ex02;

import java.io.IOException;

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
           
       예) response.addHeader("Refresh","휴식시간(초); url=재요청할 두번째 서블릿 매핑주소");

*/

//@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//포워드  : 다른 서버페이지를 재요청 하는 기술
		
		//포워드 방법 중에서 HttpServletResponse객체의 addHeader메소드를 호출하는 방법은?
		//-> 첫번쨰 서블릿을 처음 요청했지만 첫번째 서블릿 내부 코드에서 두번쨰 서블릿을 
		//	 웹브라우저 주소창을 거쳐서 재요청(포워딩)한다는 의미 입니다.
		//   단! 재요청하기 전에 휴식 시간을 설정해서 휴식후 재요청 하게 됩니다.
		
		//-> 문법
		//		response.addHeader("Refresh", "휴식시간(초); url=재요청할 두번쨰 서블릿 요청 매핑주소");	
		
		//1. 서블릿간의 재요청의 경우! 문자처리방식 UTF-8로 설정해야 
		//   재요청 받는 두번째 서블릿에서 요청해온 데이터를 공유받을때 
		//   한글이깨지지 않고 공유받아 웹브라우저로 출력할수 있음
		request.setCharacterEncoding("UTF-8");
		
		//2. 두번쨰 SecondServlet클래스로 재요청(포워딩) - 리플러쉬 방법으로
		//해석 : 1초 휴식 후 url=second 에 지정한 매핑이름 second로 두번쨰 SecondServlet을
		//      재요청을위해  웹브라우저 주소창을 자동으로 이동했다가 second주소가 자동 입력된 후 재요청하게됨	
		response.addHeader("Refresh", "3;url=second"); //1초 휴식 후 이동
		
	}

}
