<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style type="text/css">
		.cls1{
			font-size: 40px;
			text-align: center;
		}
		.cls2{
			font-size: 20px;
			text-align: center;
		}
	</style>

</head>
<body>

	${requestScope.membersList.size()}


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
     <%-- request에 바인딩된  ArrayList배열이 없으면?(조회된 정보가 없으면?)  --%>		  
	 <c:when test="${empty requestScope.membersList}">
	 	<tr align="center">
	 		<td colspan="5">
	 			<b>등록된 회원이 없습니다.</b>
	 		</td>
	 	</tr>
	 </c:when>                  
	  <%--request에 바인딩된 ArrayList배열이 있으면?(조회된 정보가 있으면?) --%>
	 <c:when test="${not empty requestScope.membersList}">	 
	 	<%-- request에 바인딩된 ArrayList배열을 꺼내오고 
	 	     MemberVO객체의 갯수만큼 반복해서 얻어 출력 --%>
	 	<c:forEach  var="mem"   items="${requestScope.membersList}"  >
	 		<tr align="center">
	 			<td>${mem.id}</td>
	 			<td>${mem.pwd}</td>
	 			<td>${mem.name}</td>
	 			<td>${mem.email}</td>
	 			<td>${mem.joinDate}</td>
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









