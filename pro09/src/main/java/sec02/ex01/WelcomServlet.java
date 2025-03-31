package sec02.ex01;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
response.setContentType("text/html; charset=UTF-8") 과
response.setCharacterEncoding("UTF-8") 차이점

 - 두 코드는 서블릿에서 응답(HTTP Response)의 인코딩을 설정하는 역할을 하지만, 작동 방식이 다릅니다.

1️. response.setContentType("text/html; charset=UTF-8")
✅ 역할
 - HTTP 응답의 Content-Type 헤더를 설정합니다.
 - 브라우저가 이 응답이 어떤 형식의 데이터인지(text/html) 
   그리고 어떤 문자 인코딩(UTF-8)으로 해석해야 하는지 알도록 합니다.

2️. response.setCharacterEncoding("UTF-8")
✅ 역할
 - 서블릿이 응답을 생성할 때 사용하는 문자 인코딩을 설정합니다.
 - 서블릿이 response.getWriter()를 호출하여 문자열을 출력할 때 해당 문자셋을 사용하여 변환합니다.
 - 즉, 서버에서 HTML을 생성하는 과정에서 UTF-8을 사용하도록 지정하는 역할.
 
 참고.
   - 브라우저가 charset=UTF-8 정보 없이 기본 인코딩(예: ISO-8859-1)로 해석할 수도 있음!
     그래서 setContentType()과 함께 설정하는 것이 중요함.

*/



//인코딩(Encoding)과 디코딩(Decoding)의 의미
//1. 인코딩(Encoding)
//"사람이 이해할 수 있는 문자 → 컴퓨터가 이해할 수 있는 형식으로 변환"
// 또는 
//"한 형식 → 다른 형식으로 변환"

//✅ 대표적인 인코딩 예시
//	- 문자 인코딩: UTF-8, ISO-8859-1, EUC-KR 등의 문자셋으로 변환
//  - URL 인코딩: 웹 주소에서 특수문자(한글, 공백 등)를 %XX 형태로 변환
//  - Base64 인코딩: 바이너리 데이터를 텍스트로 변환 (이메일 첨부파일, JWT 토큰 등)

//2. 디코딩(Decoding)
//"컴퓨터가 이해하는 형식 → 사람이 읽을 수 있는 형식으로 변환"
//또는 
//"인코딩된 데이터를 원래의 형태로 복원"  


//정리 표.
	 //구분								설명							예제
	//인코딩 			(Encoding)	데이터를 특정 형식으로 변환		안녕하세요 → %EC%95%88... (URL 인코딩)
	//디코딩 			(Decoding)	인코딩된 데이터를 원래대로 복원	%EC%95%88... → 안녕하세요 (URL 디코딩)


//사용자가 로그인 했는지 쿠키를 확인하고, 로그인 상태를 유지하는 서블릿
@WebServlet("/welcome")
public class WelcomServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//브라우저가 응답을 해석할 문자셋을 결정하며 클라이언트(브라우저)가 문서를 해석할 때 사용됨
		response.setContentType("text/html; charset=UTF-8");
		
		//서블릿이 응답데이터를 UTF-8로 변환하며 톰캣서버가 데이터를 생성할 때 사용됨
		response.setCharacterEncoding("UTF-8");
		
		//사용자의 브라우저에서 쿠키 객체 얻기
		Cookie[] cookies = request.getCookies();
		
		//사용자 아이디를 저장할 변수
		String username = null;
		
		//쿠키가 존재하는지 확인
		if(cookies != null) {
			
			//Cookie[] 배열에 저장된 Cookie객체 반복해서 얻기
			for( Cookie cookie : cookies ) {
				
				//"user_id"라는 쿠키명이 저장된 Cookie객체가 있는지 확인
				if("user_id".equals(cookie.getName())) {
					//Cookie 객체에 저장된 쿠키값(로그인 요청시 입력한 아이디) 가져오기 
					username = URLDecoder.decode(cookie.getValue(), "UTF-8");
				}
			}//for
		}//if
		
		//사용자가 로그인한 상태라면 환영 메세지 출력
		if( username != null ) {
			
			response.getWriter().println("<h1>환영합니다, " + username + "님!</h1>");
			response.getWriter().println("<a href='logout'>로그아웃</a>"); //로그아웃 링크 제공

		} else {
			//쿠키가 없다면 로그인 요청 페이지를 재요청해서 보여주자
			response.sendRedirect("login3.html");
		}

	}
	
}
