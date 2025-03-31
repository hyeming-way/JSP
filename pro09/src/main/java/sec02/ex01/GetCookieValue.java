package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	두번째 서블릿 요청시에는 request의 getCookies()메소드를 호출해
	웹브라우저로부터 쿠키를 전달받습니다.
	그리고 전달된 쿠키에서 저장할때 사용한 이름인 "cookieTest"로 검색해 쿠키값을 가져옵니다.
*/

//서버 측의 서버페이지(GetCookieValue서블릿)

@WebServlet("/get")
public class GetCookieValue extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//HttpServletRequest객체의 getCookies()메소드를 호출해
		//웹브라우저에게 new Cookie(...)객체들의 정보를 요청한 후 Cookie[]배열에 담아 반환받습니다.
		Cookie[] allValue = request.getCookies();
		
		//Cookie[]배열에 저장할 때 사용한 쿠키명인 cookieTest로 검색해서 쿠키값을 가져와 출력
		for(int i=0; i<allValue.length; i++) {
			
			//조건 : 쿠키명이 cookieTest인 new Cookie()객체이면?
			if( allValue[i].getName().equals("cookieTest")) {
				out.print("웹브라우저에서 가져온 Cookie객체 내부의 쿠키명 cookieTest와 함께 저장된?");
				out.print("쿠키값 : " + URLDecoder.decode(allValue[i].getValue(), "UTF-8")); //인코딩한 값 디코딩하여 출력해야함!
			}
			
		}
		
				
	}

}
