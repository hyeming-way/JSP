<%@page import="file.FileDAO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 처리 서버페이지 fileUploadAction.jsp 화면</title>
</head>
<body>
	<%
		//application내장객체는 전체 프로젝트(웹애플리케이션)에 대한 자원을 관리하는 객체입니다.
		//서버(톰캣)의 실제 프로젝트 경로에서 자원을 찾을때 가장 많이 사용됩니다.
			//String path = application.getRealPath("/");
			//C:\workspace_ServerPage\FileUploadDownload\src\main\webapp\
	
		//업로드할 실제 톰캣 서버 내부의 upload 폴더의 경로 얻기
		String directory = application.getRealPath("/upload/");
		//C:\workspace_ServerPage\FileUploadDownload\src\main\webapp\ upload\
		
		//2. 한번에 업로드할 수 있는 파일의 최대 크기 지정 1G = 1024MB
		int maxSize = 1024 * 1024 * 1024;
		
		//참고 : MultiPartRequest클래스에 대한 객체 생성시 생성자로 업로드할 정보를 전달해 저장시키면 
		//      자동으로 우리가 지정한 경로에 파일 업로드가 되게 된다!
		
		//실제 파일 업로드 담당을 하는 클래스는? cos.jar 압축파일에 존재하는 MultipartRequest클래스이다.
		MultipartRequest multipartRequest;
				
		//3. 파일 업로드
		// - MultipartRequest클래스의 객체 생성시 생성자로 전달한 데이터들
		//3-1. fileUpload.jsp의 form태그에 의해서 전달받은 업로드할 파일의 정보를 가지고 있는 request 내장객체 전달
		//3-2. 업로드할 파일의 실제 서버 폴더 경로 (/upload/폴더) 전달
		//3-3. 한번에 업로드할 수 있는 파일의 최대 사이즈 전달
		//3-4. 업로드하는 파일이름이 한글일 경우 깨져서 올라가게 되므로 인코딩 방식을 UTF-8로 설정하기 위해 전달
		//3-5. 업로드되는 실제 서버 폴더 경로에 똑같은 파일 업로드시
		//     파일명끝에 1을 자동으로 붙여서 만들어주는 역할을 하는
		//     new DefaultFileRenamePolicy()객체를 생성해서 전달
		multipartRequest = new MultipartRequest(request,
												directory,
												maxSize,
												"UTF-8",
												new DefaultFileRenamePolicy() );
		
		//4. 톰캣 서버 경로(/upload/폴더)에 업로드할 파일을 업로드 요청하기 전의 원본파일명 얻기
		String fileName = multipartRequest.getOriginalFileName("file"); //input태그의 name값 넣어줌
		//5. 톰캣 서버 경로(/upload/폴더)에 업로드된 실제파일명 얻기
		String fileRealName = multipartRequest.getFilesystemName("file");
		
		//6. 업로드한 파일의 원이름과 업로드한 파일의 실제 
		new FileDAO().upload(fileName, fileRealName);

		out.write("업로드한 원본 파일명 : " + fileName + "<br>");
		out.write("업로드한 실제 파일명 : " + fileRealName + "<br>");
		
	%>
	


</html>