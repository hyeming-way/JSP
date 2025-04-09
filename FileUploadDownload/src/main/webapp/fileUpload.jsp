<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 파일 업로드 요청하는 디자인 -->
	<form action="fileUploadAction.jsp" method="post" enctype="multipart/form-data">
		파일 : <input type="file" name="file"><br>
		<input type="submit" value="업로드">
	</form>
	
	<br>
	
	<a href="fileDownLoadList.jsp">파일 다운로드</a>

</body>
</html>