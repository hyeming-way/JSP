package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
	세션?
	  - 클라이언트가 톰캣서버의 서버페이지와 연결을 유지하는 데이터를 저장하는 객체메모리
	  - 세션 객체 메모리는 톰캣서버 측에서 관리함
	  - 세션 메모리는 웹브라우저 창 닫으면 삭제되고, 세션 메모리 유지 시간을 넘기면 삭제되고, 로그아웃처리시 세션이 삭제됩니다.
	  
	  
	세션 흐름  	
	클라이언트 -> 요청 -> [톰캣서버] 세션메모리 생성(JSESSIONID) -> 응답(JSESSIONID 웹브라우저의 쿠키저장소 저장)
	-> 클라이언트 -> 재요청 -> [톰캣서버] JSESSIONID와 클라이언트가 전달한 JSESSIONID가 같은지 확인해서 클라이언트임을 확인하고 로그인 처리함
	
	세션동작흐름
	1. 클라이언트가 톰캣서버에 요청을 보냄
	2. 톰캣서버는 클라이언트에 대한 세션메모리를 생성하고, 고유한 JSESSIONID값을 생성함
	3. 톰캣서버는 JSESSIONID값을 쿠키에 담아 클라이언트에 전송함
	4. 클라이언트가 이후 요청을 보낼 때, JSESSIONID를 포함하여 톰캣서버로 요청을 보냅니다.
	5. 톰캣서버는 JSESSIONID를 확인하여 해당 클라이언트의 세션정보를 가져옴
	6. 클라이언트가 로그아웃하거나 세션메모리 유지시간을 넘기면 세션메모리가 삭제됨

*/

//서블릿
/*
	클라이언트가 웹브라우저 주소창에 http://localhost:8090/pro09/sess주소를 입력하여
	톰캣 서버가 실행해서 읽어들이는 SessionTest라는 이름의 서블릿페이지를 요청합니다.
	
	요청받으면 서블릿 서버페이지는 새로운 HttpServletRequest객체 메모리의 힘을 빌려서
	HttpSession객체 메모리를 하나 만듭니다.
	예) request.getSession(); -> HttpSession객체 메모리 반환함 
	
	만들어진 HttpSession객체 메모리의 정보를 웹브라우저로 응답합니다.
*/

@WebServlet("/sess")
public class SessionTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//1. HttpServletRequest객체의 getSession메소드의 반환값으로 HttpSession을 얻자
		//요약 : HttpSession 객체메모리 생성(얻기)
		HttpSession session = request.getSession();
		//참고. 기존에 만들었던 HttpSession 객체메모리가 없으면 새로운 HttpSession객체를 만들어 반환해줌
		
		//2. 생성된 HttpSession객체메모리를 식별할 JSESSIONID얻기
		//방법 : getId()메소드를 호출하면 반환받을 수 있다.
		out.print("HttpSession메모리의 아이디 : " + session.getId() + "<br>");
		
		//3. 최초 HttpSession 객체메모리를 생성한 시각 얻기
		//방법 : getCreationTime() 메소드를 호출하면 반환받을 수 있다.
		out.print("최초 HttpSession객체메모리 생성 시각 : " + new Date(session.getCreationTime()) + "<br>");
		
		//4. 생성된 HttpSession 객체메모리에 가장 최근에 접근한 시간을 가져올 수 있다.
		//방법 : getLastAccessedTime()메소드를 호출하면 반환받을 수 있다.
		out.print("최초 HttpSession객체메모리 생성 시각 : " + new Date(session.getLastAccessedTime()) + "<br>");
		
		//5. 생성된 HttpSession 객체메모리가 톰캣서버 메모리에 올라가 있는 유효시간 얻기
		out.print("세션 메모리 유지 시간 : " + new Date(session.getMaxInactiveInterval()) + "<br>");
		
		//6. 최초로 생성된 HttpSession 객체메모리이면? true 반환하는 isNew()메소드를 활용하여
		//최초로 생성된 HttpSession 객체메모리인지 판별
		if(session.isNew()) {
			out.print("새로 생성된 HttpSession 객체메모리입니다.");
		}
	
	}

}
