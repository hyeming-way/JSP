<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

	 <%-- /pro17 --%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"   />
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%-- 가입할 회원정보를 입력하고 가입하기 버튼을 누르면  
	     MVC중에서 C역할을 하는 MemberController서블릿에 가입요청합니다. --%>
	<form  action="${contextPath}/member/addMember.do"  method="post">
		<h1 style="text-align:center" >회원 등록창</h1>
		<table align="center">
			<tr> 
				<td width="200"> <p align="center">아이디</p> </td> 
				<td width="400"><input type="text" name="id"></td> 
			</tr>  
			<tr> 
				<td width="200"><p align="center">비밀번호</p></td> 
				<td width="400"><input type="password" name="pwd"></td> 
			</tr> 
			<tr> 
				<td width="200"><p align="center">이름</p></td> 
				<td width="400"><input type="text" name="name"></td> 
			</tr> 
			<tr> 
				<td width="200"><p align="center">이메일</p></td> 
				<td width="400"><input type="email" name="email"></td> 
			</tr>  
			<tr> 
				<td width="200"><p align="center">&nbsp;</p></td> 
				<td width="400">
					<input type="submit" value="회원가입">
					<input type="reset" value="다시입력">
				</td> 
			</tr>					
		</table>	
	</form>


</body>
</html>




    