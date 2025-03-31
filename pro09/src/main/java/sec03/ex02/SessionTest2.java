package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//주제 : HttpSession클래스의 setMaxInactibeInterval()메소드를 이용해 세션메모리 유효시간을 5초로 재설정

@WebServlet("/sess2")
public class SessionTest2 extends HttpServlet {

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
		
		//5. 생성된 HttpSession 객체메모리가 톰캣서버 메모리에 올라가 있는 유효시간 30분(1800초) 얻기
		out.print("기본 세션 메모리 유지 시간 : " + session.getMaxInactiveInterval() + "<br>");
		
		//5-1. HttpSession 객체메모리가 톰캣서버 메모리에 올라가 유지하는 유효시간을 5초로 변경
		session.setMaxInactiveInterval(5);
		out.print("변경된 세션 메모리 유지 시간 : " + session.getMaxInactiveInterval() + "<br>");
		
		//6. 최초로 생성된 HttpSession 객체메모리이면? true 반환하는 isNew()메소드를 활용하여
		//최초로 생성된 HttpSession 객체메모리인지 판별
		if(session.isNew()) {
			out.print("새로 생성된 HttpSession 객체메모리입니다.");
		}
	
	}

}
