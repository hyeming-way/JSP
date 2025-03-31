package sec04.ex01;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


//이벤트 처리하는 이벤트 핸들러 역할의 클래스로
//추상메소드를 강제로 오버라이딩해서 HttpSession 객체메모리에 바인딩시 이벤트를 처리하는 클래스입니다.

//HttpSessionBindingListener를 제외한 Listener를 구현하는 모든 이벤트 핸들러는 반드시
//아래의 어노테이션 기호를 이용해 Listener로 등록해야합니다.
@WebListener // 이 어노테이션은 이 클래스가 ServletContext의 리스너로 동작한다는 것을 톰캣에 알려줌
			 //즉, 서블릿 컨텍스트가 시작되거나 종료될 때 특정 작업을 수행할 수 있게 톰캣에 알려줌
public class LoginImpl implements HttpSessionBindingListener {

	//로그인 요청시 입력한 아이디, 비밀번호 저장시킬 변수
	public String user_id;

	public String user_pw;
	
	//HttpSession 객체메모리에 한번씩 바인딩하면 1씩 증가되어 저장될 변수
	static int total_user = 0;
	
	//기본생성자
	public LoginImpl() {}

	//변수 초기화 할 생성자
	public LoginImpl(String user_id, String user_pw) {
		this.user_id = user_id;
		this.user_pw = user_pw;
	}

	//HttpSession 객체메모리에 저장시(바인딩) 접속자수를 증가시키는 기능의 메소드 오버라이딩
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("사용자 접속");
		++total_user;
	}
	
	//HttpSession 객체메모리에 세션사용자 소멸시 접속자수를 감소시키는 기능의 메소드 오버라이딩
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("사용자 접속 해제");
		total_user--;
	}
	
}
