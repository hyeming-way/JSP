package sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
memberForm.html(회원가입을 위해 가입할 정보를 입력후 DB의 테이블에 INSERT 요청 하는 화면)
에서 가입할 정보를 입력하고 가입요청버튼을 클릭하면 입력된 정보들을 
모두 HttpServletRequest객체 메모리에  저장된후 공유받아 사용하는 서블릿으로 
회원 추가후 추가된 정보를 확인 하기 위해
다시~~~ 모든 회원정보들을 조회해서 클라이언트의 웹브라우저로 출력(응답) 해서 보여줍니다.

순서1. input type="hidden" name="command" value="addMember" 의 
      addMember값을  HttpServletRequest객체 메모리에서 얻습니다.
	  
	  얻는 코드 :   request.getParameter("command"); -> "addMember"
	  
	  얻는 이유 : 어떤 요청을 했는지 서블릿은 판단하기 위해서 입니다. 
	  			여기서는 addMember 값 자체가 회원가입 요청임을 판단하는 값이 됩니다. 
	  			
순서2. addMember <- 요청한 값은 t_member테이블에 회원추가 요청임을 판단해
	  가입시 입력한 회원정보들을 HttpServletRquest객체 메모리로부터 얻어서
	  MemeberVO객체 생성후 각인스턴스변수에 저장 

순서3. 실제 t_member테이블에 insert하기 위해 MemberDAO의 addMember메소드 호출시~
  	  매개변수로 MemberVO객체의 주소를 전달하여 MemberDAO의 addMember메소드 내부에서
  	  insert문장을 만들고 insert문을 실행할수 있도록 합니다.
  	  
순서4. insert에 성공하면 다시~모든 회원정보 조회 요청을 하기위해
  	  MemberDAO객체의 listMembers메소드를 호출하여 ArrayList배열을 받고
  	  웹브라우저 화면에 조회된 정보들을 응답합니다.
  	  	  			
*/

@WebServlet("/member4")
public class MemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB작업할 MemberDAO클래스의 객체 생성
		MemberDAO dao = new MemberDAO();
		
		//1. 요청한 데이터 한글처리 (회원가입 폼에서 입력한 가입 정보 한글처리)
		request.setCharacterEncoding("UTF-8");
		
		//2-1. MemberServlet이 어떤 요청(회원 조회/추가/삭제/수정 중 추가요청)을 받았는지 판단
		String command = request.getParameter("command"); //"addMember"
		
		//2-2. DB의 t_member테이블에 회원추가 요청을 받았다면?
		if(command != null && command.equals("addMember")) {
			
			//2-3. 회원가입(t_member 테이블에 입력한 회원 추가)을 위해 입력한 값들을 얻는다
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			//2-4. 회원가입(t_member테이블에 입력한 회원정보 추가)를 위해 
			//MemberVO객체를 생성해서 가입할 정보들을 각 변수에 저장
			MemberVO vo = new MemberVO(_id, _pwd, _name, _email);
			
			//2-5. MemberDAO에 만들어 놓은 addMember메소드 호출시 매개변수로
			//가입시 입력한 정보가 각 변수에 저장된 MemberVO객체 전달하여 INSERT명령
			//INSERT에 성공하면 1을 반환, 실패하면 0을 반환
			int result = dao.addMember(vo);
			
			System.out.println("회원가입 성공 1 실패 0 = " + result);
		
		//위 command변수에 저장된 값이 "delMember"라면?
		//(회원 한사람의 정보 삭제 요청을 받았다면?)	
		} else if ( command != null && command.equals("delMember") ) {
			
			//요청한 삭제할 회원의 id값 얻기
				//삭제할 회원아이디를 HttpServletRequest 객체메모리에서 얻기
				//얻는 이유 : DELETE 구문 작성시 어떤 아이디를 가진 회원을 삭제할 것인지 판단해서 삭제하기 위함
				//	예) DELETE FROM T_MEMBER WHERE ID=?;
			String id = request.getParameter("id");
			
			//삭제할 회원 아이디를 이용해 DB의 t_member테이블에 저장된 하나의 회원레코드를 삭제하기위해
			//MemberDAO에 만들어놓은 delMember메소드 호출시 매개변수로 삭제할 회원 아이디를 전달해서
			//DELETE삭제 명령함 
			dao.delMember(id);
			
		}//else if
		//흐름 : 위 회원등록 후  아래의 코드 dao.listMembers();에 의해 모든 회원정보를 조회 후 보여 줄것이다.
		//흐름 : 위 회원삭제 후  아래의 코드 dao.listMembers();에 의해 삭제후 모든 회원정보를 조회해서 보여 줄것이다.-			
				
		
		//3. 요청한 주소에 관한 DB의 t_member테이블의 회원레코드 정보를 조회하기 위해
		//데이터베이스 와 연결하여 SQL작업을 위해 MemberDAO클래스의 객체를 생성해서 listMembers()메소드 호출!
		//MemberDAO dao = new MemberDAO();
		
		//3-1. MemberDAO객체의 listMembers()메소드를 호출하여
		//     t_member테이블에 저장된 모든 회원레코드를 조회하는 명령을 내립니다.
		//클라이언트의 웹브라우저로 응답(출력)할 조회된 회원레코드 전체(ArrayList배열)반환받습니다.
		List list = dao.listMembers(); 
		
		//ArrayList가변길이 배열 모습
		//[ MemberVO,  MemberVO,  MemberVO ]
		//    0          1            2       index
		
		//조회된 ArrayList배열을 HttpServletRequest 객체메모리에 바인딩해서
		request.setAttribute("membersList", list);
		
		//디스패처 방법으로 두번째 서블릿(ViewServlet)을 재요청(포워딩)시  
		//HttpServletRequest 객체메모리 공유
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewMembers");
		
		//실제 포워딩시 공유
		dispatcher.forward(request, response);
		
		//---------------------------------------------------------------------------------------
		
	}//doPost 메소드
	
}//class MemberServlet
