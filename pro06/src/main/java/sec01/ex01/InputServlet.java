
package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//input.html화면에서 아이디와 비밀번호를 입력하고, 과목명들을 체크하여 로그인 전송버튼을 눌렀을때
//요청한 값들을 얻어 처리하는 InputServlet 서블릿 서버페이지

@WebServlet("/input1")
public class InputServlet extends HttpServlet {

	//클라이언트가 GET방식으로 요청하면 호출되는 콜백메소드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청한 데이터 한글처리(문자 처리 방식 UTF-8 설정)
		request.setCharacterEncoding("UTF-8");
		
		//2. 서블릿으로 요청한 데이터들 HttpServletRequest객체메모리에서 얻기
		//2-1. 입력한 아이디, 비밀번호 얻기
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//2-2. 체크박스들에 체크된 값들 얻기
		/*
			참고. <input>태그들은 하나의 공통된 name="subject"로 여러값을 한꺼번에 전송하여 받을 경우
			HttpServletRequest객체메모리의 getParameterValues("<input>태그의 name속성값 subject"); 메소드 호출
			여러개의 요청한 값들을 String[]배열에 담아 얻어와야할 경우 사용!
		*/
		//체크박스에 체크된 <input>의 value속성값(문자열)들만 String[]배열에 담아 배열을 반환해줌
		String[] subject = request.getParameterValues("subject");
		
		//3. InputServlet(서블릿 서버페이지)로 요청한 값을 확인하기위해 출력
		System.out.println("요청한 아이디 : " + user_id);
		System.out.println("요청한 비밀번호 : " + user_pw);
		
		//for반복문을 이용해 String[] subject배열에 저장된 체크된 모든 value속성값들을 얻어 출력
		for(String sub : subject) {
			System.out.println("체크된 과목명 : " + sub);
			
		}
		
	}
	
	
}
