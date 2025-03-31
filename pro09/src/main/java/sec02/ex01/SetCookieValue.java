package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	쿠키? 클라이언트(웹브라우저)에 저장되는 데이터(파일)로 로그인 상태 유지, 사용자 설정 저장 등에 사용됨
	
	쿠키 동작 방식
	
		(1) 클라이언트 -> 서버(최초요청)
		  - 사용자가 웹사이트에 처음 방문하면, 브라우저는 서버에 요청을 보냄
		  - 이때 클라이언트에 해당 사이트의 쿠키 정보가 없거나, 새로운 요청이므로 쿠키를 보내지 않음
		  
		  	요청예시(HTTP GET 메세지)
		    -------------------------------------------
			요청 헤더
			GET /login HTTP/1.1
			Host:www.naver.com?key&value
			-------------------------------------------
		  
		  
		(2) 서버 -> 클라이언트 (쿠키 생성 및 전송)
		  - 서버는 사용자의 정보를 저장하기위해 쿠키데이터를 생성하고, 응답(Response)헤더에 포함하여
		    클라이언트(브라우저)로 전송함
		    
		    서버 응답 메세지 예시(HTTP 응답 헤더)
	    	-------------------------------------------
	    	HTTP/1.1 200 OK
	    	Set-Cookie : session_id=abc123; Path=/; HttpOnly; Max-Age=3600
	    	-------------------------------------------
		    Set-Cookie : 서버가 클라이언트의 웹브라우저에 쿠키를 저장하도록 지시하는 속성
		    session_id=abc123 : 쿠키이름=쿠키값
		    Path=/ : 쿠키가 적용될 URL경로(사이트 전체에서 사용가능)
		    HttpOnly : javascript에서 접근 불가능(보안강화)
		    Max-Age=3600 : 쿠키의 만료 시간(1시간, 초로 표현)
		    
		    	
		(3) 클라이언트에 쿠키 저장
		  - 브라우저는 서버에서 받은 쿠키를 저장하고, 지정된 유효기간 동안 유지함
		  - 영구쿠키라면 브라우저를 닫아도 유지되고, 세션쿠키라면 브라우저가 종료되면 삭제됨
		  
		  
		(4) 클라이언트 -> 서버(쿠키 포함 요청)
		  - 사용자가 동일한 웹사이트를 다시 방문하면, 브라우저는 저장된 쿠키를 요청 헤더에 포함하여
		    서버로 보냄
		    
		    클라이언트 요청 예시(HTTP GET 요청 헤더)
		    -------------------------------------------
		    GET /login HTTP/1.1
		    Host : www.naver.com
		    Cookie : session_id=abc123 <-- 쿠키도 포함
		    -------------------------------------------
		    
		    
		(5) 서버 -> 클라이언트(쿠키데이터를 확인 후 응답)
		  - 서버는 요청에 포함된 쿠키를 확인하고, 사용자의 상태를 유지하거나 적잘한 응답을 반환함
		    예를 들어 session_id=abc123을 확인하고, 사용자가 로그인 상태임을 유지할 수 있음
		    
		    서버 응답 메세지 예시
		    -------------------------------------------
		    HTTP/1.1 200 OK
		    ContentType : text/html
		    HTML태그들을 작성하면 태그들로 응답을 보냄
		    -------------------------------------------		  

*/

/*
클라이언트가 웹브라우저 주소창에 http://localhost:8090/pro09/set 주소를 입력하여
SetCookieValue서블릿을 요청합니다.
SetCookieValue서블릿(톰캣 서버가 실행하는 서버페이지)내부에서 
Cookie클래스의 객체를 생성한후 쿠키이름을 cookieTest로 쿠키값을 저장합니다.
그리고 setMaxAge()메소드를 사용하여 쿠키 유효 시간을 24시간으로 설정합니다.
그런 다음 response객체의 addCookie()메소드를 이용해 생성된 쿠키를 
웹브라우저로 전송합니다. 
*/

//서버 측의 서버페이지(SetCookieValue서블릿)

@WebServlet("/set")
public class SetCookieValue extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//응답할 데이터 생성
		//- 현재 날짜 정보
		Date date = new Date();
		
		//쿠키 생성 
		//- Cookie클래스로 쿠키 객체 생성 후 쿠키명, 쿠키값 한 쌍의 형태로 쿠키 객체에 저장
		Cookie cookie = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍입니다.", "UTF-8"));
									//쿠키명 						//쿠키값
		
		//- 쿠키 객체의 메모리 유효 기간 설정
		//cookie.setMaxAge(60*60*24); //하루 24시간(초*분*시)
		
		//유효기간을 음수로 지정하면 쿠키파일에 저장하는 것이 아니라
		//클라이언트의 웹브라우저가 사용하는 Cookie저장소에 session쿠키를 생성할 수 있다.
		cookie.setMaxAge(-1);
		
		//- 생성된 쿠키 객체를 요청시 사용된 클라이언트의 웹브라우저로 전달(응답)
		//  응답하기위해 HttpServletResponse객체에 쿠키 객체를 저장한 후 내보내자
		response.addCookie(cookie);
		
		//클라이언트의 웹브라우저로 응답할 데이터 + 쿠키객체 모두 전달(응답)
		out.println("현재 시간 : " + date);
		out.println("쿠키명-쿠키값을 쿠키객체에 저장 후 웹브라우저로 내보냈습니다.");
				
	}

}
