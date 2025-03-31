package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
		
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//MemberVO 객체의 id, pwd 변수에 입력한 아이디와 비밀번호 저장
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(user_id);
		memberVO.setPwd(user_pw);
		
		//DB작업할 MemberDAO객체 생성
		MemberDAO dao = new MemberDAO();
		
		//입력한 아이디와 비밀번호가 DB의 t_member테이블에 저장되어있으면 true, 없으면 false를 반환할
		//MemberDAO의 isExisted(MemberVO)메소드 호출!
		boolean result = dao.isExisted(memberVO);
		
		if(result) { //입력한 아이디와 비밀번호로 조회가 되면? (DB의 테이블에 저장되어 있다는 의미)
			
			HttpSession session = request.getSession();
			
			session.setAttribute("isLogon", true); //로그인 인증 값 저장
			session.setAttribute("login.id", user_id); //입력한 아이디 저장
			session.setAttribute("login.pw", user_pw); //입력한 비밀번호 저장

			out.print("<html><body>");
			out.print("이 LoginServlet5 서버페이지는 메인화면을 웹브라우저로 응답한 내용입니다.<hr>");
			out.print("안녕하세요..." + user_id + "님! 로그인 되셨습니다.");
			out.print("<a href='show'>회원 정보 보기</a>");
			out.print("</body></html>");
					
		} else { //입력한 아이디와 비밀번호가 조회가 안 되면? (DB의 테이블에 저장되어 있지 않다면?)
			
			out.print("<html><body>");
			out.print("<center>회원 아이디 또는 비밀번호가 틀립니다.</center>");
			out.print("<a href='login5.html'>다시 로그인 요청하러 가기</a>");
			out.print("</body></html>");
			
		}
		
	}//doHandle

}
