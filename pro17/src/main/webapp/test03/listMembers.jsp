<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    
    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
		   <%-- /pro17 --%>   
   
<%-- 회원정보 추가, 회원정보 수정, 회원정보 삭제에 성공하면  
     request내장객체에 바인딩된 msg에 관한 성공 메세지를 조건에 따라 결과를 응답하는 코드 --%>   
<c:choose>
	<c:when test="${requestScope.msg == 'addMember'}">
	   <script>
	   		//웹브라우저가 모든 HTML을 로딩했을때
	   		window.onload = function(){
	   			alert('회원정보 추가에 성공했습니다');
	   		}
	   </script>
	</c:when>	
		
	<c:when test="${requestScope.msg == 'modified'}">
	   <script>
	   		//웹브라우저가 모든 HTML을 로딩했을때
	   		window.onload = function(){
	   			alert('회원정보 수정 완료');
	   		}
	   </script>
	</c:when>
	
	<c:when test="${requestScope.msg == 'deleted'}">
	   <script>
	   		//웹브라우저가 모든 HTML을 로딩했을때
	   		window.onload = function(){
	   			alert('회원정보 삭제 완료');
	   		}
	   </script>
	</c:when>	

</c:choose>   
   
   
   
   
    
    
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
			<td width="7%"><b>수정</b></td>
			<td width="7%"><b>삭제</b></td>
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
	 			<td><a href="${contextPath}/member/modMemberForm.do?id=${mem.id}">수정</a></td>
	 			<td><a href="${contextPath}/member/delMember.do?id=${mem.id}">삭제</a></td>
	 		</tr>
	 	</c:forEach>
	 </c:when>
</c:choose>		
	</table>
	
	<a href="${contextPath}/member/memberForm.do">
		<p class="cls2">회원가입하러가기</p>
	</a>
</body>
</html>









