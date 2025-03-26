package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	웹브라우저(클라이언트)로 부터 t_member테이블에 저장된 모든 회원레코드를 조회하여 보여줘~!
	라고 http://localhost:8090/pro07/member 조회 주소 요청을 받아
	응답하는 서블릿
*/
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청한 데이터 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2. 요청한 값 얻기
		//(요청한 주소 http://localhost:8090/pro07/member를 얻었기때문에 따로 요청한 값이 없음)
		
		//3. 요청한 주소에 관한 DB의 t_member테이블의 회원레코드 정보를 조회하기 위해
		//데이터베이스와 연결하여 SQL작업을 위해 MemberDAO클래스의 객체를 생성해서 listMembers()메소드 호출!
		MemberDAO dao = new MemberDAO();
		//3-1. MemberDAO객체의 listMembers()메소드를 호출하여
		//     t_member테이블에 저장된 모든 회원레코드를 조회하는 명령을 내립니다.
		//클라이언트의 웹브라우저로 응답(출력)할 조회된 회원레코드 전체(ArrayList배열)반환받습니다.
		List list = dao.listMembers(); 
		
		//ArrayList가변길이 배열 모습
		//[ MemberVO,  MemberVO,  MemberVO ]
		//    0          1            2       index
		
		//3-2-1. 요청한 클라이언트의 웹브라우저로 응답할 데이터유형(MIME-TYPE)설정 및 인코딩 방식 설정
		response.setContentType("text/html; charset=UTF-8");
		
		//3-2-2. 요청한 클라이언트 웹브라우저와 연결된 출력스트림 PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		
		//3-3. (순서8). 웹브라우저로 출력(결과값 출력)
		//t_member테이블에 저장되고 조회한 정보를 출력
		out.print("<html>");
		out.print("<body>");
			out.print("<table border=1>");
				out.print("<tr align='center' bgcolor='lightgreen'>");
					out.print("<th>아이디</th>");
					out.print("<th>비밀번호</th>");
					out.print("<th>이름</th>");
					out.print("<th>이메일</th>");
					out.print("<th>가입일</th>");
				out.print("</tr>");
				
				for(int i=0; i<list.size(); i++) {
					//ArrayList배열에 저장되어 있는 MemberVO객체를 하나씩 반복해서 얻기
					MemberVO memberVO = (MemberVO)list.get(i);
					
					out.print("<tr align='center'>");
					out.print("<td>" + memberVO.getId() + "</td>");
					out.print("<td>" + memberVO.getPwd() + "</td>");
					out.print("<td>" + memberVO.getName() + "</td>");
					out.print("<td>" + memberVO.getEmail() + "</td>");
					out.print("<td>" + memberVO.getJoinDate() + "</td>");
					out.print("</tr>");
				} //for	
			out.print("</table>");
		out.print("</body>");
		out.print("</html>");
		
	}//doGet 메소드
	
}//class MemberServlet
