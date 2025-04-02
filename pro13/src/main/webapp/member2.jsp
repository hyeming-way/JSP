<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/*
	member.jsp 설명
	- memberForm.html(회원가입을 위해 입력한 정보를 요청하는 전송하는 화면)에서
	  입력한 가입할 정보들을 request 내장객체 메모리에서 가져온 후 
	  MemberVO클래스의 객체 생성 후 각 인스턴스 변수에 저장시킵니다.
	  그런 다음 MemberDAO 객체를 생성해서 addMember()메소드 호출시
	  이 메소드의 매개변수로 memberVO 객체의 주소를 전달합니다.
	  addMember메소드 내부에서 회원가입정보를 DB의 t_member테이블에 INSERT시킨 후
	  INSERT에 성공하면 다시 MemberDAO객체의 listMembers()메소드를 호출하여
	  DB의 t_member테이블에 저장된 모든 회원정보들을 조회해와서
	  현재 member.jsp에ㅔ 목록으로 출력합니다.		  
*/

//1. 요청한 데이터 request에 UTF-8설정
request.setCharacterEncoding("UTF-8");

//2. 요청한 데이터 얻기 (가입을 위해 입력한 값들을 request 객체메모리에서 얻기)
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");
String email = request.getParameter("email");

/*
	<jsp:useBean>액션태그는 자바코드 객체 생성하는 구문을 대체해서 작성할 수 있는 태그입니다.
	
	id속성에는 생성한 객체의 참조변수명을 지정해서 객체를 식별할 유일한 고유ID값을 지정합니다.
	class속성에는 객체를 생성할 때 사용하는 설계도인 클래스경로를 패키지명을 포함해서 지정합니다.
	scope속성에는 자바빈 역할을 하는 VO또는 DTO객체를 생성 후 저장될 내장객체 메모리영역 종류명 하나 지정
	
	문법
		<jsp:useBean id="생성한 객체를 식별할 고유ID값(참조변수명 설정)"
					 class="객체 생성시 사용될 클래스파일이 저장된 경로"
					 scope="생성한 객체는 어떤 내장객체 메모리영역에 저장할지 종류작성"
					        page 또는 request 또는 session 또는 application /> 
*/


//3-1. MemberVO클래스의 객체하나를 생성해서 각 인스턴스변수에 요청한 데이터들을 각각 저장
//MemberVO vo = new MemberVO(id, pwd, name, email);
%>
	<%-- 기본생성자를 호출해서 MemberVO클래스의 객체 생성한 후 page 내장객체 영역에 바인딩 --%>			
	<jsp:useBean id="vo"
				 class="sec01.ex01.MemberVO"
				 scope="page"/> <%-- 기본값 page --%>
<% 
	//useBean 액션태그로 생성한 MemberVO객체 내부에 만들어져있는
	//setter인스턴스 메소드들을 호출해서 인스턴스변수값들을 각각 초기화
	vo.setId(id);
	vo.setPwd(pwd);
	vo.setName(name);
	vo.setEmail(email);

//3-2. MemberDAO클래스의 객체하나를 생성해서 addMember메소드를 호출시
//	   매개변수로 DB에 INSERT할 정보가 저장된 MemberVO객체의 주소를 전달!
MemberDAO dao = new MemberDAO();
dao.addMember(vo);

//3-3. 생성한 MemberDAO객체의 listMembers메소드를 호출해서
//	   회원가입에 성공한 회원레코드를 포함해서 t_member테이블에 저장된 모든 회원 조회
//arrayList반환
List membersList = dao.listMembers();

%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- t_member 테이블에서 조회된 모든 회원정보를 표의 목록형태로 출력 --%>
	<table width="100%" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%">아 이 디</td>
			<td width="7%">비밀번호</td>
			<td width="7%">이   름</td>
			<td width="7%">이 메 일</td>
			<td width="7%">가 입 일</td>
		</tr>
<%
		//t_member테이블에 조회한 회원정보가 없으면?(t_member테이블에 저장된 회원이 없으면?)
		if(membersList.size() == 0){
%>			
			<tr>
				<td colspan="5">등록된 회원이 없습니다.</td>
			</tr>
<%			
		}else{ //조회된 정보가 있으면? 
			
			for(int i=0; i<membersList.size(); i++){
				
				MemberVO memberVO = (MemberVO)membersList.get(i);
%>				
				<tr align="center">
					<td width="7%"><%=memberVO.getId()%></td>
					<td width="7%"><%=memberVO.getPwd()%></td>
					<td width="7%"><%=memberVO.getName()%></td>
					<td width="7%"><%=memberVO.getEmail()%></td>
					<td width="7%"><%=memberVO.getJoinDate()%></td>
				</tr>
<%					
			}			
		}
%>
		<tr height="1" bgcolor="#99ccff">
			<td colspan="5"></td>
		</tr>

	</table>

</body>
</html>