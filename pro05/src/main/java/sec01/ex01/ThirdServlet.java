package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
1. @WebServlet("/third")의 기본 개념
   - @WebServlet("/third")는 서블릿을 특정 URL에 연결하기 위한 어노테이션(annotation)기호 입니다.
     여기서 중요한 점은 서블릿을 URL(클라이언트가 서블릿을 요청할 전체 주소)에 매핑(mapping) 한다는 것입니다.

	 서블릿은 클라이언트의 요청을 받아 처리하고, 응답을 반환하는 Java 클래스입니다.
	 예를 들어, 사용자가 웹 브라우저에 http://localhost:8080/프로젝트명/third를 입력하면 이 서블릿이 호출되어 동작합니다.

2. URL 매핑의 필요성
  - 웹 애플리케이션에서 여러 서블릿을 사용한다고 가정할 때, 각 서블릿은 특정 URL 경로로 요청을 처리합니다.
  - 따라서 서블릿을 특정 URL에 연결해주는 매핑이 필요합니다.
    @WebServlet("/third")는 바로 이 역할을 하는 매핑 설정입니다.

  - @WebServlet("/third")는 클라이언트가 /third라는 URL을 요청하면 이 서블릿을 실행하겠다는 의미입니다.
  - 예를 들어, http://localhost:8080/프로젝트이름/third로 요청하면, ThirdServlet 클래스가 동작하게 됩니다.

3. 어노테이션(annotation)과 @WebServlet의 의미
	1.어노테이션(annotation) 의미
    - 어노테이션은 메타데이터라고 부르며, 코드에 추가적인 설명을 붙이는 방법입니다.
    - 어노테이션은 컴파일러나 런타임에서 참고해서 특정 동작을 하도록 할 수 있습니다.

    2.@WebServlet 어노테이션
     - @WebServlet은 서블릿을 등록하기 위한 어노테이션입니다.
     - 이 어노테이션을 사용하면 web.xml 파일을 수정할 필요 없이 서블릿을 직접 URL에 매핑할 수 있습니다.

4. @WebServlet("/third")가 실제로 하는 일 (동작 순서)

	순서1. 사용자가 요청을 보냄
	   - 사용자가 웹 브라우저 주소창에 **http://localhost:8080/프로젝트이름/third**를 입력합니다.
	   - 이 요청은 Tomcat 서버로 전달됩니다.
	
	순서2. Tomcat 서버가 @WebServlet("/third")를 확인
	  - Tomcat은 ThirdServlet 클래스에 **@WebServlet("/third")**가 붙어있는지 확인합니다.
	  - 이 어노테이션을 보면, /third라는 URL 경로와 이 서블릿을 연결해야 한다는 정보를 알 수 있습니다.
	
	순서3. ThirdServlet 서블릿 실행
	 - @WebServlet("/third")가 설정된 서블릿이 실행됩니다.
	 - ThirdServlet 클래스의 doGet() 또는 doPost() 메서드가 호출되어 요청을 처리하고 응답을 클라이언트에게 보냅니다.
	
	순서4. 웹 브라우저가 응답을 받음
	 - 서버는 처리한 데이터를 HTML로 만들어 클라이언트(웹 브라우저)로 응답을 보냅니다.
	 - 클라이언트는 그 응답을 받아 화면에 출력합니다.
	
 @WebServlet("/third")를 사용한 서블릿 등록 vs. web.xml 사용
	 - 예전에는 서블릿을 등록할 때 web.xml 파일을 수정하여 서블릿과 URL을 매핑했습니다.
	   하지만 @WebServlet("/third")를 사용하면 web.xml 파일을 수정하지 않고 서블릿을 바로 URL에 매핑할 수 있습니다.
	   따라서 코드가 더 간결해지고 관리가 쉬워집니다.

*/
//http://localhost:8090/pro05/third를 입력해서 웹브라우저로 요청하면 요청을 받아처리하는 ThirdServlet클래스
@WebServlet("/third")
public class ThirdServlet extends HttpServlet {
	
	/*
	serialVersionUID란?
			serialVersionUID는 **직렬화(serialization)**와 관련된 버전 관리에 사용되는 고유 식별자입니다.

			직렬화는 객체를 바이트 스트림으로 변환하는 과정이고, 이때 객체를 파일로 저장하거나 네트워크를 통해 전송할 수 있습니다.

			역직렬화(deserialization)는 직렬화된 데이터를 다시 원래 객체 형태로 복원하는 과정입니다.
	*/
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("ThirdServlet init 메서드 호출");
	}

	public void destroy() {
		System.out.println("ThirdServlet destory 메소드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ThirdServlet doGet메소드 호출");
	}

}







