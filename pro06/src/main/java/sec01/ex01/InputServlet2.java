
package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
HttpServletRequest 클래스의  getParameterNames()메소드의 역할
- <form>태그 내부에서 전송되어 오는 요청 데이터들은 모두 <input>태그에 의해 입력 받았습니다
      서블릿으로 전송되는 데이터가 많은 경우 <input>태그들의 name속성값들을 일일이 기억할 필요없이
  getParameterNames()메소드를 호출하면
      요청한 값들에 대한 <input>태그들의 name속성값들을 모두~~~~~ 
  Enumeration배열에 담아 반환해줍니다.
*/

//input.html화면에서 아이디와 비밀번호를 입력하고, 과목명들을 체크하여 로그인 전송버튼을 눌렀을때
//요청한 값들을 얻어 처리하는 InputServlet 서블릿 서버페이지

@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {

	//클라이언트가 GET방식으로 요청하면 호출되는 콜백메소드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청한 데이터 한글처리(문자 처리 방식 UTF-8 설정)
		request.setCharacterEncoding("UTF-8");
		
		//2. 서블릿으로 요청한 데이터들 얻기
		//요약 : 요청한 데이터 얻기
		// input2.html에서 입력받은 <input>태그들의 name속성값들을 얻기 위해
		// HttpServletRequest객체의 getParameterNames()메소드를 호출합니다.
		// 이 메소드는 Enumeration인터페이스의 자식 배열 공간에  
		//<form>태그 내부에 있는 요청한  <input>들의 name속성값들 모두 저장한 후
		// Enumeration인터페이스를 구현한 자식 배열 자체를 반환해 줍니다.
		Enumeration<String> enu = request.getParameterNames();
		
		//위 Enumeration 자식배열에 <input>태그의 name 속성값들이 저장되어 있으면 반복해서
		while(enu.hasMoreElements()) {
			
			//<form>태그 내부의 모든 <input>태그들의 name 속성값들은 위 Enumeration 자식배열에 저장되어 있다
			//그러므로 차례대로 하나씩 반복해서 얻어 사용해야함
			String name = enu.nextElement();
			//name변수에는 "user_id", "user_pw", "subject" 가 차례대로 하나씩 저장됨
			
			//위 name변수에 저장되어 있는 <input>의  name속성값들을 
			//getParameterValues메소드 호출시 인자로 전달해
			//입력한 아이디, 비밀번호, 그리고 체크된 과목명들을 모두 String배열에 담아 반환받는다
			String[] values = request.getParameterValues(name);
			
			//향상된 for반복문을 이용해 String[] values배열에 저장된 데이터를 꺼내와 반복해서 출력
			for(String value : values ) {
				System.out.println("name속성값 : " + name + ", value = " + value);
				
			}
			
		}

	}
		
}
