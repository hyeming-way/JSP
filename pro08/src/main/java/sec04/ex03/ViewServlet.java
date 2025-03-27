package sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//첫번째 MemberServlet에서 포워딩한 두번째 ViewServlet
@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		//첫번째 서블릿에서 HttpServletRequest 객체메모리 바인딩한
		//조회된 MemberVO객체들이 저장된 ArrayList배열 꺼내오기
		List list = (List)request.getAttribute("membersList");
		
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
					out.print("<th>삭제</th>");
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
					out.print("<td><a href='/pro08/member4?command=delMember&id=" + memberVO.getId() + "'>삭제</a></td>");
					out.print("</tr>");
				} //for	
			out.print("</table>");
			
			out.print("<a href='/pro08/memberForm.html'>회원가입</a>");
				
		out.print("</body>");
		out.print("</html>");

	}
	
	
}
