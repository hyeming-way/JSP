<%@page import="java.util.List"%>
<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//1. 요청한 데이터 request에 UTF-8설정
request.setCharacterEncoding("UTF-8");

//2. 요청한 데이터 얻기 (가입을 위해 입력한 값들을 request 객체메모리에서 얻기)
// String id = request.getParameter("id");
// String pwd = request.getParameter("pwd");
// String name = request.getParameter("name");
// String email = request.getParameter("email");

//3-1. MemberVO클래스의 객체하나를 생성해서 각 인스턴스변수에 요청한 데이터들을 각각 저장
//MemberVO vo = new MemberVO(id, pwd, name, email);
%>
	<%-- 기본생성자를 호출해서 MemberVO클래스의 객체 생성한 후 page 내장객체 영역에 바인딩 --%>			
	<jsp:useBean id="vo"
				 class="sec01.ex01.MemberVO"
				 scope="page"/> <%-- 기본값 page --%>
	
	<%-- vo.setId(String id)메소드 호출시 매개변수 id로 value속성에 작성한 값을 전달해서
		 property 속성에 작성한 id변수에 저장시킴 --%>			 
	<jsp:setProperty property="id" name="vo" value='<%=request.getParameter("id")%>'/>		
	<jsp:setProperty property="pwd" name="vo" value='<%=request.getParameter("pwd")%>'/>		
	<jsp:setProperty property="name" name="vo" value='<%=request.getParameter("name")%>'/>		
	<jsp:setProperty property="email" name="vo" value='<%=request.getParameter("email")%>'/>		
		 
<% 
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