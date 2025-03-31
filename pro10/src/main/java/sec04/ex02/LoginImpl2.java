package sec04.ex02;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//웹 애플리케이션에서 사용자의 로그인 상태를 관리하는 클래스입니다.
//HttpSessionListener인터페이스를 구현하여 HttpSession객체가 생성되거나 소멸될때 동작합니다.

@WebListener
public class LoginImpl2 implements HttpSessionListener {
	
	static String user_id;
	String user_pw;
	static int total_user = 0; //현재 로그인한 사용자 수를 저장하는 정적변수
	
	public LoginImpl2() {}
	
	public LoginImpl2(String user_id, String user_pw) {
		this.user_id = user_id;
		this.user_pw = user_pw;
	}

	//사용자가 로그인하여 HttpSession 객체메모리가 생성될때 실행되는 메소드입니다.
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		++total_user;
	}
	
	//사용자가 로그아웃하거나 HttpSession 객체가 톰캣 메모리에서 만료되어 소멸될때 실행되는 메소드입니다.
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("HttpSession 객체메모리 소멸");
		total_user--;		
	}

}
