package sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//MVC 중에서 C(Controller)역할을 하는 서블릿 클래스 (사장)

//request객체의 getPathInfo() 메소드를 호출하면
//두 단계로 이루어진 클라이언트의 요청한 주소(URL)  /member/addMember.do 중에서
//  /addMember.do 2단계 요청주소를 문자열로 반환받을수 있다.

//모든 회원 정보 조회 요청주소 만들기
//http://localhost:8090/pro17/member/listMembers.do

//회원가입 입력한 디자인 페이지( /test02/MemberForm.jsp ) 요청 주소 만들기
//http://localhost:8090/pro17/member/memberForm.do

//회원가입 요청(DB의 t_member테이블에 가입할 정보 추가) 요청 주소 만들기
//http://localhost:8090/pro17/member/addMember.do

//@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	private MemberDAO memberDAO;
		
//메소드 오버라이딩  init,  doGet, doPost
	//서블릿이 요청을 받았을때. 가장 처음에 MemberController클래스의 객체가 톰캣서버메모리에 로드되는 시점에
	//개발자가 변수의 값을 초기화 해놓을때 사용되는 init메소드 
	@Override
	public void init(ServletConfig config) throws ServletException {
		//MemberDAO객체를 생성해서 주소번지 memberDAO전역변수에 초기화
		memberDAO = new MemberDAO();
	}
	//클라이언트가 GET요청방식으로 요청하면 호출되어 요청을 처리하는 콜백메소드 
	@Override
	protected void doGet(HttpServletRequest request, 
					     HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	//클라이언트가 POST요청방식으로 요청하면 호출되어 요청을 처리하는 콜백메소드 
	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	//클라이언트가 GET 또는 POST 요청시 모두 한번에 웹브라우저로 응답할 Model을 전달하는 일반 메소드 
	protected void doHandle(HttpServletRequest request, 
							HttpServletResponse response) throws ServletException, IOException {
		
		//1. 문자셋 (인코딩)방식 UTF-8로 설정
		request.setCharacterEncoding("UTF-8");
		
		//2. request객체의 getPathInfo()메소드를 호출하여
		//   클라이언트가 요청한 전체 URL주소중에서
		//   2단계 요청한 주소만 문자열로 얻습니다
		String action = request.getPathInfo();
		//클라이언트가 회원가입을 위해 입력하는 VIEW(/test02/MemberForm.jsp) 2단계 요청 주소 얻기
		//   "/memberForm.do" 가  action변수에 저장됨
		
		//클라이언트가 MemberForm.jsp에서 입력한 가입할 정보를 DB의 t_member테이블에 추가 해줘~~ 요청주소 중에서
		//   2단계 요청주소 "/addMember.do" 가 action변수에 저장됨
		
		//회원가입에 성공한 후 /member/listMembers.do 모든회원조회 URL중에서
		//  2단계 요청주소  "/listMembers.do" 가 action변수에 저장됨
		
		
		
		//서블릿에서 포워딩(재요청) 할 VIEW주소 경로를 저장할 변수 선언
		String nextPage = null;
		
		//action변수에 저장된 2단계 요청한 주소값에 따라 if문을 분기해서 요청한 작업을 수행하는데
		
		//만약 action변수에 저장된 값이 null이거나
		// /listMembers.do 2단계 요청주소를 받으면(모든 회원을 조회 하는 요청주소를 받으면)
		if(action == null  ||  action.equals("/listMembers.do")) {
			
			//요청주소 /listMembers.do를 받은 MemberController서블릿(사장)은 
			//   모든 회원정보를 t_member테이블에서 조회해 오기 위해
			//   MemberDAO객체(사원)의  listMembers메소드를 호출하는 조회 명령을 합니다
			List list = memberDAO.listMembers();
			
			//   /test02폴더에 만들어 놓은 listMembers.jsp(VIEW)로 
			//	  조회한 회원정보들을 보여줘서 웹브라우저에 출력 하기위해
			//    request내장객체 메모리 영역에 조회된 MemberVO객체들이 저장된 ArrayList배열 자체를 바인딩(묶어서 저장)
			request.setAttribute("membersList", list);
								// 속성명      , 속성값 
			
			//조회한 회원 정보를 보여줄 VIEW경로를 nextPage변수에 저장
			nextPage = "/test02/listMembers.jsp";
			
			
		//listMembers.jsp화면에서 
		//가장 아래에 작성된 <a>회원가입하러가기</a>를 클릭하여
		//회원가입을 하기위해 입력하는 화면 VIEW(/test02/MemberForm.jsp)요청을 받았을때
		}else if(action.equals("/memberForm.do")) {
			
			//클라이언트의 웹브라우저로 보여줄 VIEW주소를 저장 
			nextPage = "/test02/MemberForm.jsp";
			
			
		//MemberForm.jsp에서 입력한 가입할 정보를
		//DB의 t_member테이블에 INSERT 2단계 요청 주소 "/addMember.do"를 받았을때
		}else if(action.equals("/addMember.do")) {
			//요청한 값들을 request에서 얻기
			String id  = request.getParameter("id"); 
			String pwd  = request.getParameter("pwd"); 
			String name  = request.getParameter("name"); 
			String email  = request.getParameter("email"); 
					
			//MemberVO객체 생성후 각변수에 요청한 값들 저장
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			
			//입력한 회원정보들을 DB의 t_member테이블에 INSERT명령 하기 위해
			//MemberDAO객체의 addMember메소드 호출시~ 매개변수로 MemberVO객체를 전달함
			memberDAO.addMember(memberVO);
			
			//새 회원 정보를 DB의 t_member테이블에 INSERT후 ~ `
			//다시 ~ 모든 회원을 조회 해줘~~ 라는 재요청 주소를 
			//nextPage변수에 저장하면  MemberController가 웹브라우저 주소창으로 인한 재요청을 받아
			//다시 조회 요청주소를 받아 처리할것이다.
			nextPage = "/member/listMembers.do";
			
			
		}
		
		
		
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
		
	}	
	
}








