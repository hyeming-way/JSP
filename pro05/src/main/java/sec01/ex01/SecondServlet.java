package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 매핑하기?
//- 서블릿 매핑(Servlet Mapping)은 
//  웹 브라우저에서 입력한 URL(주소)과 실제 Java 서블릿 클래스를 연결하는 과정입니다.
//- 웹 브라우저는 서블릿 클래스를 직접 실행할 수 없기 때문에, 특정 URL과 서블릿을 연결해야 합니다.
//이렇게 하면 사용자가 특정 웹 주소를 입력했을 때 해당 서블릿이 실행되도록 설정할 수 있습니다.

/*
  서블릿 매핑을 설정하는 2가지 방법
  방법1. web.xml 파일에서 설정하는 방법(전통적인 방법)
  방법2. 어노테이션(@WebServlet)으로 설정하는 방법(요즘 많이 사용되는 방법)

주제 : 방법1. web.xml 파일에서 설정하는 방법 사용해보자.
*/


//SecondServlet 클래스는 HttpServlet클래스를 상속받아 웹 요청을 처리하는 서블릿으로 만든다
//HttpServlet을 상속하면 클라이언트 요청을 자동으로 처리하는 메소드를 제공해 줍니다.
public class SecondServlet extends HttpServlet {
	
	/*
     * 1. 서블릿이 최초로 실행될 때 (최초 요청이 들어올 때) 단 한 번만 호출되는 메서드입니다.
     *    - 서블릿이 처음 로드될 때 실행되며, 이후 클라이언트가 다시 요청해도 init()은 실행되지 않고, 
     *      기존 서블릿 객체가 사용됩니다.
     *    - 보통 **초기화 작업 (DB 연결, 설정 값 로드 등)** 을 수행할 때 사용됩니다.
     */
	@Override
	public void init() throws ServletException {
		System.out.println("init 메서드 호출>>>>");
	}
	
	/*
     * 2. 클라이언트가 GET 요청을 보냈을 때 실행되는 메서드입니다.
     *    - 클라이언트(웹 브라우저)가 이 서블릿을 요청하면 이 메서드가 자동으로 실행됩니다.
     *    - HTTP 요청(request)과 HTTP 응답(response)을 처리하는 역할을 합니다.
    
    ✅ doGet() 메서드는 클라이언트가 GET 방식으로 요청했을 때 실행됩니다.
	✅ 클라이언트(웹 브라우저)의 요청을 받아 처리하고, 응답을 보냅니다.
	✅ HttpServletRequest req → 클라이언트 요청 정보를 담는 객체
	✅ HttpServletResponse resp → 서버의 응답 정보를 담는 객체
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
						throws ServletException, IOException {
		System.out.println("doGet 메서드 호출>>>>");
	}
	
	/*
     * 3. 서블릿이 제거될 때 (톰캣 서버가 종료되거나, 서블릿이 메모리에서 내려갈 때) 실행되는 메서드입니다.
     *    - 서블릿이 더 이상 사용되지 않을 때 단 한 번만 실행됩니다.
     *    - 보통 **사용했던 자원을 정리하거나, DB 연결을 닫을 때** 사용됩니다.
     */
	@Override
	public void destroy() {
		System.out.println("destory 메서드 호출>>>>");
	}
}













