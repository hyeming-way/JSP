package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//주제 : 웹페이지를 연동(연결)하는 방법 중
//URL Rewriting 방법을 이용해
//웹페이지들(login.html -> LoginServlet.class -> SecondServlet.class) 사이의 정보를 공유합니다.

/*
URL Rewriting이란?
URL Rewriting은 클라이언트가 쿠키를 지원하지 않는 경우에도 세션을 유지하기 위해 URL에 세션 ID를 추가하는 방식입니다.
즉, 서버에서 클라이언트에게 응답할 때, URL에 세션 ID를 포함하여 클라이언트가 이를 다시 서버로 전송할 수 있도록 합니다.

1. URL Rewriting의 필요성
 1.1.쿠키를 사용할 수 없는 경우
	 - 클라이언트가 쿠키를 차단한 경우
	 - 특정 환경(예: 보안이 중요한 환경)에서 쿠키가 비활성화된 경우
 1.2. 세션을 유지해야 하는 경우
 	 - 사용자가 로그인 상태를 유지해야 하는 웹 애플리케이션

2.URL Rewriting 방식
	- 일반적으로 Java의 Servlet에서 response.encodeURL(String url)을 사용하여 URL을 재작성합니다.


response.encodeURL(String url) 상세 설명
	- response.encodeURL(String url)은 세션 유지를 위해 URL을 자동으로 변환해 주는 메서드입니다.
	- 쿠키를 사용할 수 없을 때 jsessionid를 URL에 추가하여 세션을 유지하는 역할을 합니다.

*/


@WebServlet("/login2")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청한 데이터들 인코딩 방식 설정
		request.setCharacterEncoding("UTF-8");
		
		//2. 요청받은 데이터들을 HttpServletRequest 객체메모리에서 꺼내 각 변수에 저장
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		String user_email = request.getParameter("user_email");
		String user_hp = request.getParameter("user_hp");
		
		//3. 요청한 값들 이용해 요청한 클라이언트의 웹브라우저로 응답할 데이터 생성
		String data;
		data = "안녕하세요!<br>"
				+ "로그인하셨습니다.<br><br>"
				+ "입력한 아이디 : " + user_id + "<br>"
				+ "입력한 비밀번호 : " + user_pw + "<br>"
				+ "주소 : " + user_address + "<br>"
				+ "이메일 : " + user_email + "<br>"
				+ "휴대전화 : " + user_hp + "<br><br>";
		
								// URL ? 요청할 값 파라미터
		data += "<a href='/pro09/second?user_id=" + user_id 
									+ "&user_pw=" + user_pw 
									+ "&user_address=" + URLEncoder.encode(user_address, "UTF-8") //user_address 값은 한글이기때문에 인코딩 해주어야 함!!
									+ "'>두번째 서블릿으로 보내기</a>";
								
		
		//4. MIME-TYPE 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//5. 출력스트림 객체 얻기
		PrintWriter out = response.getWriter();
		
		//6. 웹브라우저로 출력
		out.print(data);

	}
	
}
