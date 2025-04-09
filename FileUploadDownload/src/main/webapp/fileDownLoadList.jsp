<%@page import="java.net.URLEncoder"%>
<%@page import="file.FileDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="file.FileDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다운로드할 파일 목록을 보여주는 화면</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	
		//참고 : 클라이언트가 FileUploadDownload프로젝트 내부의 fileDownloadList.jsp서버페이지를 요청하면
		//요청한 주소중에서 톰캣이 웹프로젝트까지 찾아갈 수 있게 작성되는 컨텍스트 주소를 얻을 수 있다.
		String contextPath = request.getContentType();
		  // "/FileUploadDownload"
		  
		//File Database의 file테이블에 실제 저장된 다운로드할 파일명 검색해서 얻기
		ArrayList<FileDTO> fileList = new FileDAO().AllSelect();
		  
		//위 ArrayList배열에 저장된 조회된 레코드 정보 한줄씩 저장하고 있는 FileDTO 객체의 갯수만큼 반복해서
		//ArrayList배열에 각 index 위치에 저장된 FileDTO객체를 꺼내와서 정보를 얻어 출력
		for(FileDTO fileDTO : fileList){
			
			//다운로드할 파일의 원본이름(업로드 요청 당시 선택했던 파일의 원본이름) 얻기
			String fileName = fileDTO.getFileName();
			
			//다운로드할 파일(업로드한 실제파일)의 실제이름 얻기
			String fileRealName = fileDTO.getFileRealName();
			
			//다운로드한 횟수 얻기
			int downloadCount = fileDTO.getDownloadCount();
			
			//다운로드 요청 링크 만들기
			//                    /FileUploadDownload/fileDownloadAction.jsp?name=다운로드할폴더명&name=다운로드할실제파일명
			out.println("<a href='" + contextPath + "/fileDownloadAction.jsp?directory=upload&fileRealName=" + URLEncoder.encode(fileRealName,"UTF-8") + "'>"
						+ fileRealName + " (다운로드한 횟수:" + downloadCount + ")" + "</a><br>");			
		} //for
		
	
	
	%>




</body>
</html>