package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
서블릿(Servlet)이란?
- 서블릿(Servlet)은 Java를 사용하여 동적인 웹 페이지를 생성하는 서버 측 프로그램입니다.
- 웹 서버(예: Tomcat)가 클라이언트(브라우저)로부터 요청을 받으면, 
  서블릿이 이를 처리하고 응답을 생성하여 브라우저에 전달합니다.
- 역할: 사용자의 입력을 받아 데이터베이스와 연동하고, 결과를 HTML 형식으로 반환.




   동작 흐름
   
   순서1. 클라이언트(웹브라우저)가  웹브라우저 주소창에 FirstServlet클래스의 객체를 요청할 주소를 입력
         http://localhost:8090/pro05/first
         		톰캣서버IP:포트번호/컨텍스트주소/FirstServlet을 요청할 가짜주소
   순서2. http://localhost:8090/pro05/first요청한 주소를 톰캣서버(웹애플리케이션서버)가 받아
         server.xml파일의 <Context>태그의 path속성의 정보(/pro05)를 찾아 pro05프로젝트에 접근
         그리고 web.xml파일에 작성한 <servlet></servlet>  <servlet-mapping></servlet-mapping>
         태그에 설정한  /first 가짜주소와 연결된 실제 sec01.ex01.FirstServlet클래스의 객체에 요청함
          		
   순서3. FirstServlet이 최초로 요청된것이면 FistServlet클래스의 객체메모리를 생성해서
         Tomcat서버 메모리 내부에 로딩합니다. 이떄~~ 단한번만 init()메소드가 호출됩니다.
         이후 ~ service메소드를 거쳐 doGet()메소드가 자동으로 호출될때
         Tomcat서버는 HttpServletRequest객체와 HttpServletResponse객체를 만들어
         doGet메소드의 매개변수로 전달해준다.
         
   순서4. doGet메소드 내부에 개발자가 작성된 웹브라우저로 응답할 코드를 톰캣서버가 실행해~
         웹브라우저로 내보내어 응답 하게 된다.
         
   순서5. 만약~ 톰캣서버가 중지되면? destory메소드가 자동으로 호출되어 톰캣서버에 올라가 있던
         FirstServlet객체가 제거되면서 프로그램은 종료됩니다.
         
동작순서 요약
   순서1. 클라이언트가 웹브라우저를 통해 톰캣서버에 FirstServlet을 요청함
   
   순서2. 톰캣은 요청주소를 전달받아 FirstServlet을 찾아 실행합니다.
   
   순서3. FirstServlet실행결과를 웹브라우저로 전송해서 클라이언트에게 응답함.
   
   순서4. 클라이언트는 웹브라우저 화면에 출력된 응답결과를 보게 됩니다.
   
   
   
   
[클라이언트 (웹브라우저)]
   |
   | 1. HTTP GET 요청 (http://localhost:8090/pro05/first)
   v
[웹 서버 (Tomcat)]
   |
   | 2. 요청 URL을 분석하여 서블릿 매핑 확인 (web.xml 또는 @WebServlet)
   v
[서블릿 컨테이너]
   |
   | 3. FirstServlet 객체가 없는 경우:
   |    - new FirstServlet() → init() 호출 (1회만 실행)
   |
   | 4. doGet() 메소드 실행
   v
[응답 생성]
   |
   | 5. HTML 응답 데이터를 클라이언트로 전송
   v
[웹 브라우저 화면 출력]
   |
   | 6. "서블릿 요청 성공!" 메시지를 화면에 표시
   v
[추가 요청 발생 시]
   |
   | - init() 생략 (이미 메모리에 로딩됨)
   | - doGet()만 실행하여 빠르게 응답
   
         
*/


//1.실제 웹 프로그래밍에서 사용되는 사용자 정의 서블릿 클래스는
//ServletApi.jar라이브러리에서 제공되는 HttpServlet클래스를 상속받아 만듭니다.

//2. 서블릿 3개의 생명주기 메소드( init(), doGet(), destory() )오버라이딩해서 기능을 구현합니다.
/*
	서블릿의 생명주기 메소드 설명
	1. init() : 서블릿이 최초로 실행될때 한번만 호출되는 초기화 메소드 
	2. service() : 클라이언트의 요청을 처리하는 메소드(HttpServle에서는 doGet(), doPost()등의 메소드로 세분화됨)
	3. destory() : 서블릿이 종료될떄 한번만 호출되는 메소드 
*/
public class FirstServlet extends HttpServlet{
	/*
		init() 메서드는 FirstServlet이 처음 생성될때(클래스가 객체생성되면서 톰캣메모리에 올라가는시점) 
		한번만 호출되며, 변수의 초기화 작업을 수행하는 역할을 합니다.
		예를 들어, 데이터베이스 연결코드를 설정하거나, 설정 파일을 톰캣서버메모리에 로드하는 등의 작업을 수행할수 있음
	*/
	@Override
	public void init() throws ServletException {
		System.out.println("init() 메소드 호출 - FirstServlet클래스의 객체 톰캣서버메모리에 올림");
	}
	/*
		doGet()메소드는 클라이언트가 GET방식으로  FirstServlet서블릿 페이지로 요청을 낼때 호출되는 메소드.
	    이 메소드 내부에서는 요청을 처리하고, 응답할 데이터를 생성하여 클라이언트의 웹브라우저로 반환(응답)할수 있습니다.
	
		req 매개변수 : 클라이언트의 요청 정보를 포함하는 HttpServletRequest객체를 전달받음
		resp 매개변수 : 클라이언트의 웹브라우저로 응답을 보낼 데이터를 저장하는 HttpServletResponse객체를 전달받음
		
		ServletException : 서블릿 관련 예외처리
		IOException : 입 출력 관련 예외처리 
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("doGet() 메소드 호출 - GET 요청 처리");
		
		//클라이언트의 웹브라우저로 응답할 데이터 종류(MIME-TYPE)을 text기반의 html문서로 내보내고
	    //한글 깨짐을 방지 하기 위한 응답할 문자 처리방식을 UTF-8로 설정
		resp.setContentType("text/html; charset=UTF-8");
		
		//웹브라우저창과 연결된 출력스트림 통로를 통해 HTML형태의 응답데이터를 만든후 내보낸다
		//요약 : 클라이언트의 웹브라우저로 응답
		resp.getWriter().println("<html><body>");
		resp.getWriter().println("<h1>서블릿 요청 성공!</h1>");
		resp.getWriter().println("</body></html>");	
	}
	/*
		FirstServlet객체메모리가(서블릿이) 톰캣메모리에서 제거될때 호출되는 메소드 입니다. 
		예를 들어, 데이터베이스 연결을 닫거나, 파일을 정리하는 등의 작업을 수행할수 있습니다.
	*/
	@Override
	public void destroy() {
		System.out.println("destory() 메소드 호출 - 서블릿 종료");
	}
	
}
/*
결론
 - 톰캣을 실행한 후 브라우저로 요청하여 출력결과를 보면
   맨 처음 브라우저에서 /first로 요청하면 최초 요청이므로 FirstServlet클래스의 init()메소드를 호출해 초기화한 후 톰캣 메모리에 로드되어 doGet()메소드를 호출하여 서비스를 실행합니다
   그러나 다른 브라우저에서 동일한 서블릿 매핑이름인 /first로 요청하면 톰캣 메모리에 로드된 FirstServlet클래스 서블릿이 재사용되므로 init()메소드는 호출하지 않고 doGet()메소드만 호출하여 서비스를 합니다
   이처럼 동일한 요청 작업을 하는 경우 서블릿은 톰캣메모리에 존재하는 서블릿을 재사용함으로써 월씬 빠르고 효츌적으로 동작합니다.



*/





