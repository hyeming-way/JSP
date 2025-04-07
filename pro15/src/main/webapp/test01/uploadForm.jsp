<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<% request.setCharacterEncoding("UTF-8"); %>    

<%-- 톰캣 서버가 프로젝트까지 찾아갈 수 있는 컨텍스트주소 경로를 얻어 변수에 저장 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		  <%-- /pro15 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		서블릿(서버페이지)에 파일업로드를 요청해 파일을 업로드하기위해
		action속성에 서블릿을 요청할 매핑주소 /upload.do를 작성하고
		파일 업로드 요청시
		반드시 <form>태그에는 enctype속성의 값을 multipart/form-data로 설정해서 요청해야 합니다.
	 --%>
	<form action="${contextPath}/upload.do" method="POST" enctype="multipart/form-data">
		
		첨부 파일1 : <input type="file" name="file1"><br> <!-- DiskFileItem 객체 -->
		첨부 파일2 : <input type="file" name="file2"><br> <!-- DiskFileItem 객체 -->
		
		파라미터1 : <input type="text" name="param1"><br> <!-- DiskFileItem 객체 -->
		파라미터2 : <input type="text" name="param2"><br> <!-- DiskFileItem 객체 -->
		파라미터3 : <input type="text" name="param3"><br> <!-- DiskFileItem 객체 -->
	
		<input type="submit" value="업로드">
		
	</form>

</body>
</html>