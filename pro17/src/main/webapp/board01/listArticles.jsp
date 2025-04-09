<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<% request.setCharacterEncoding("UTF-8"); %>

<%-- 컨텍스트 주소 얻어 저장 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록 창</title>
	<style type="text/css">
		.cls1 {text-dacoration: none;}
		.cls2 {text-align: center; font-size: 30px;}
	</style>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>글번호</td>		
			<td>글작성자</td>		
			<td>글제목</td>		
			<td>글작성일</td>		
		</tr>
<c:choose>
	<%-- BoardController 서블릿으로부터 전달받은 request 내장객체 영역에서 꺼내온
	     ArrayList배열안에 ArticleVO객체들이 저장되어 있지 않으면? --%>
	<c:when test="${empty requestScope.articlesList}">
		<tr height="10">
			<td colspan="4">
				<p align="center"><b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b></p>
			</td>
		<tr>
	</c:when>
	<%-- DB에서 글이 조회 되면? --%>
	<c:when test="${not empty requestScope.articlesList}">
		<%-- BoardController.java 서블릿으로부터 재요청해서 전달받은 request 영역에
		     ArrayList배열 공간에 저장된 ArticleVO객체들의 갯수만큼 반복하여
		     조회한 글정보(ArticleVO)들을 ArrayList배열에서 차례로 얻어와 글목록을 행단위로 표시
		     varStatus="articleNum" <--- 반복 상태를 저장할 변수
		--%>
		<c:forEach var="articlevo" items="${requestScope.articlesList}">
		
			<tr align="center">
				<td width="5%">${articlevo.articleNO}</td> <%-- 검색한 글의 글번호 출력 --%> 
				<td width="10%">${articlevo.id}</td> <%-- 검색한 글 하나 정보의 id 변수값 (작성자 id) 출력 --%>		
				<td align="left" width="35%">
					<span style="padding-left: 30px;"></span> <%-- 왼쪽으로 30px만큼 여백을 준 후 글제목을 표시할 목적으로 여백을 줌 --%>
					<c:choose>
						<%-- 조건 : <forEach>태그 반복시 각글의 level값이 1보다 크면? 답글(자식글)이면? --%>
						<c:when test="${articlevo.level > 1}">
							<%-- 다시 내부 <forEach>태그를 이용해 1부터 level값까지 반복하면서
								 부모 글 밑에 공백으로 들여쓰기하여 답글임을 표시합니다. --%>
							<c:forEach begin="1" end="${articlevo.level}" step="1">
								<span style="padding-left: 20px"></span>							
							</c:forEach>
							<%-- 앞에 작성한 공백 다음에 자식글의 제목을 표시함 --%>	
							<span style="font-size: 12px;">[답변]</span> 
							
						</c:when>
					</c:choose>		
					<%-- 조건 : 이 때 level 값이 1보다 크지 않으면? 부모글(원글)이면? 글제목 앞에 공백없이 표시 --%>	
					<%-- 원본글 제목을 눌렀을때 글 내용을 조회해서 보기 위한 화면 요청 --%>
					<a class="cls1" href="${contextPath}/board/viewArticle.do?articleNO=${articlevo.articleNO}">${articlevo.title}</a>	
				
				</td>		
				<td width="10%"><fmt:formatDate value="${articlevo.writeDate}"/></td>		
			</tr>	
		</c:forEach>
	</c:when>
</c:choose>	
	</table>
	
	<a class="cls1" href="${contextPath}/board/articleForm.do"><p class="cls2">글쓰기</p></a>

</body>
</html>