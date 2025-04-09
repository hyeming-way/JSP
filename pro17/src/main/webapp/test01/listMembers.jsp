<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
    
<%--
		 MVC 디자인 패턴 중에서 V역할(VIEW역할)을 하는 JSP페이지
		 - 클라이언트의 웹브라우저로 응답할 MODEL을 보여주는 디자인 페이지 
 --%>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
 
 ${requestScope.membersList.size()}   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style type="text/css">
		.cls1{
			font-size:40px;
			text-align:center;
		}
		.cls2{
			font-size: 20px;
			text-align: center;
		}
	</style>
</head>
<body>
	<p class="cls1">회원 정보</p>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%"><b>가입일</b></td>			
		</tr>
<c:choose>
	<%-- 조회된 정보가 없으면? 
	(request객체 메모리에 바인딩된 ArrayList배열에 MemberVO객체가 저장되어 있지 않으면?) --%>
	<c:when test="${ empty requestScope.membersList}">
		<tr align="center">
			<td colspan="5"><b>등록된 회원이 없습니다.</b></td>
		</tr>
	</c:when>
	
	<%--조회된 정보가 있으면? 
	   (request객체 메모리에 바인딩된 ArrayList배열에 MemberVO객체가 저장되어 있으면?)--%>
	<c:when test="${ not empty requestScope.membersList }">
		
		<%-- request에 바인딩된 ArrayList배열을 꺼내오고 
			 MemberVO객체의 갯수 만큼 반복해서 하나씩 얻어 행 단위로 출력--%>
		<c:forEach   var="membervo"     items="${requestScope.membersList}">		
			<tr align="center">
				<td>${membervo.id}</td>
				<td>${membervo.pwd}</td>
				<td>${membervo.name}</td>
				<td>${membervo.email}</td>
				<td>${membervo.joinDate}</td>			
			</tr>
		</c:forEach>
	</c:when>
</c:choose>		
	</table>
	
	<%-- MemberController서블릿(사장)님에게 회원가입 입력 창(VIEW)좀 보여줘~~ 라고 요청하는 주소  --%>
	<a href="${pageContext.request.contextPath}/member/memberForm.do">
		<p class="cls2">회원가입하러가기</p>
	</a>
	


</body>
</html>








