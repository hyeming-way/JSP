<%@page import="file.FileDAO"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일 다운로드 처리하는 서버페이지</title>
</head>
<body>
	<%
		//1. 요청한 데이터 한글 처리
		//클라이언트가 보낸 데이터 중 한글이 있을 경우 깨지지않도록 UTF-8로 설정
		request.setCharacterEncoding("UTF-8"); 
	
		//2. 다운로드 대상 파일 이름과 경로 얻기
		String fileName = request.getParameter("fileRealName"); 
		String directory = application.getRealPath(request.getParameter("directory"));
		
	
		//3.물리적으로 존재 하는 다운로드(업로드)할 파일에 접근 하기 위해  File객체 생성
		File file = new File(directory+"/"+ fileName);
		
		//MIME 타입(Multipurpose Internet Mail Extensions Type)은 브라우저에게 파일의 종류를 알려주는 문자열입니다. 	 
		//4. 클라이언트의 웹브라우저에 응답할 데이터를 파일데이터로 설정 하기 위해 작성
		String mimeType = getServletContext().getMimeType(file.toString());
		/*
		 file.toString()
		file은 java.io.File 객체입니다.
		
		toString()을 쓰면 해당 파일의 전체 경로(문자열) 로 변환됩니다.
		예: D:/workspace_jsp/WebContent/upload/hello.jpg

		
		
		getMimeType(String file)
		이 메서드는 파일의 확장자를 기준으로 MIME 타입을 추측해 반환합니다.

		예: "hello.jpg" → "image/jpeg"

		내부적으로는 웹 서버가 등록한 MIME 매핑 정보를 참조합니다. (Tomcat의 경우 web.xml 내 mime-mapping 태그 참고)
		
		
		
		즉, getMimeType("D:/workspace_jsp/WebContent/upload/hello.jpg")
		→ .jpg 확장자를 보고 "image/jpeg" 반환
		
		
		JSP는 결국 서블릿(servlet)으로 변환되어 실행됩니다.
		ServletContext는 웹 애플리케이션 전체에 대한 정보를 담고 있는 객체입니다.
		application 객체는 ServletContext의 내장 객체입니다.
		
		*/
				
				
				
		if(mimeType == null){
			//이진 데이터형식의 파일 관련 데이터를 웹브라우저에 전달(응답)하기 위해  octet-stream 유형 설정
			response.setContentType("application/octet-stream");
		}
		//5. 한글 파일 다운로드시에 한글 깨짐 현상을 피하기 위해서 웹브라우저별로 서로 다른 대응이 필요하다.
		//   먼저 브라우저를 분류하기 위해서는 다음과 같은 로직을 필요로 한다.
		
		//웹브라우저 종류 별로 다운로드시킬 파일명에 대한 인코딩 설정 후 다시 저장할 파일명 변수
		String downloadName = null;
		
		//사용자가 어떤 웹브라우저를 사용해서 다운로드 요청하는지 웹브라우저 정보 얻기
		String header = request.getHeader("User-Agent");
		
		//인터넷 익스플로러(MSIE, Trident) 웹브라우저를 사용하는 사용자이면?
		if(header.contains("MSIE") || header.contains("Trident")){
			
			//다운로드시킬 파일명을 UTF-8로 변경하여 웹브라우저로 전달 하게 되면
			//다운로드시킬 한글 파일명이 깨지지 않고 웹브라우저로 전달되어 다운로드되게 된다.
			//브라우저가 IE일 경우 다운로드될 파일 이름에 공백이 '+'로 인코딩된것을  다시 공백 "%20"으로 바꿔준다
			downloadName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
				
		//인터넷 익스프롤러(MSIE, Trident 11버전이상)웹브라우저를 제외한 다른 웹브라우저를 사용하는 사용자라면?	
		}else{
			//브라우저가 IE가 아닐 경우 다운로드될 파일 이름 설정 하는 부분
		//fileName.getBytes("UTF-8") 부분 설명
			//다운로드할 파일이름! 즉! 문자열이름을  전세계 거의 모든 문자를 포함한 체계(UTF-8)의 데이터로 사용하기 위해
			//문자열들을 바이트크기의 데이터들로 모두 변환 하여 새로운 byte[]배열에 저장해 byte[]배열 자체를 반환 해 온다.
			//요약 :다운로드시킬 파일명을 인코딩방식 UTF-8 체계로 설정 
		//new String( fileName.getBytes("UTF-8") , "8859_1"); 부분 설명 
			//fileName.getByte("UTF-8")코드 부분에서 다운로드할 파일명 문자열은 바이트 데이터들로 변환되어 byte[]배열에
			//저장되어 있기 때문에 byte[]배열에 저장된 다운로드할 파일명을 다시 웹브라우저로 전달 하여 다운로드 시킬때..
			//웹브라우저가 인식할수 있도록 다시 8859_1체계로 변형 후 다운로드할 파일명을 변수에 저장 시킨다. 
			downloadName =  new String( fileName.getBytes("UTF-8") , "8859_1");
			
		
		//참고
		//컴퓨터는 문자를 사람이 보듯이 컴퓨터에 저장 하는게 아니라
		//01010101 이런식으로 이진수로 변환후  비트단위로 저장 합니다.
		//이것을 사람이 읽을수 있도록 문자로 표현 및 저장 하는 규칙이 필요한데 이러한 것을 캐릭터 셋이라고 부릅니다.
		//한국인끼리 쓰는 시스템은 EUC-KR 캐릭터셋으로 문자를 사용하면 되지만
		//외국인에게도 서비스를 하려면 더많은 문자를 지원 해야할때 UTF-8 캐릭터셋을 사용해 줍니다.	
		}
				
		//6. 웹브라우저로 응답할 데이터(다운로드시킬 파일)을 설정하는 response객체의 헤더영역에
		//   1.다운로드시킬 파일명 설정
		//   2.content-dispostion을 명시하여 브라우저로 전송(응답)된 파일을 디스크에 직접 다운로드 받을 것인지
		//     혹은 브라우저로 바로 보여줄 것인지 설정 할 수 있다.
		//     Content-Disposition: inline <-- 브라우저에서 응답을 받으면 브라우저에서 바로 보여 준다.
		//     Content-Disposition: attachment <-- 다운로드 창이 뜨게 된다.
		response.setHeader("Content-Disposition", 
						   "attachment;filename=\"" + downloadName + "\";");
		
		//7.다운로드할 파일에서 데이터를 읽어와 버퍼공간에 저장하고 
		//  버퍼공간에 저장된 데이터를 브라우저로 출력할 스트림 통로 객체들 생성
		
		//다운로드할 파일을 바이트 단위로 읽어들일  new FileInputStream(file) 버퍼공간 스트림 통로 생성
		FileInputStream fileInputStream  = new FileInputStream(file);
		
		//읽어 들인 다운로드할 파일의 데이터들을 new FileInputStream(file) 버퍼 공간에서 꺼내어서
		//바이트 단위로  웹브라우저에 출력(응답,다운로드)할 출력 버퍼공간 통로 생성
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		/*	
			일반적으로 화면은 JPS에 구현하고 기능은 JAVA파일 구현한다.
			하지만 종종 JSP에 자바코드를 입력하여 구현하는 경우가 있다.
			만약 이때 다운로드 로직을 구현한다면 주의 할점.
			
			out.clear();
			out = pageConte.pushBody();
					
		클라이언트의 웹브라우저에 wirte 전에  로직을 추가 해줘야 하는데
		만약 위와 같은 로직이 추가 되지 않으면 다운로드에 영향을 미칠수도 있습니다.
		그리고  Servlet.service() for servlet JSP threw exception
		java.lang.IllegalStateException: getOutputStream() has already beean called for thos reponse
		위와 같은 에러메세지를 받게 될것입니다.

		이유는 jsp페이지에서 다른 jsp페이지를 호출해서 다운로드 로직을 실행하는 경우
		이미 스트림통로가 열려 있는 상태입니다. 따라서 추가적으로 스트림을 열려고 하면 위와 같은 에러 메세지를 생성 하는 것입니다.
		그렇다면 사용하기 전에 스트림을 한번 비우고 깔끔하게 웹브라우저로 전송 해야 겠죠ㅕ
		out.clear(); -> 실행하면 스트림통로에 데이터들을 깔끔하게 비운다.
		그럼 통로는 비웠으니 그통로를 지나갈 탈것이 필요 하겠죠!
		ouy = pageContext.pushBody(); -> jsp페이지에 대한 정보를 저장하는 기능을 한다.
		여기서 말하는 jsp페이지에 대한 정보라면 다운로드할 파일의 정보를 말하는 거겠죠?
		따라서 저 두굴로 페이지의 정보를 보내는 부분을 초기화 하는 작업을 한다고 생각하면 됩니다.	
		*/
		out.clear();
		out = pageContext.pushBody();
		
		//8. 파일 입출력 부분
		//다운로드할 파일에서 데이터를 1048576byte(1MB)단위로 읽어와 저장할 byte배열 생성
		byte[] b = new byte[1048576];
		
		while(true){
			//다운로드할 파일의 내용을 약 1MB단위로 한번~ 읽어와 변수에 저장
			int count = fileInputStream.read(b);
			
			if(count == -1){//다운로드할 파일에서 더이상 읽어 들일 데이터가 없으면(-1일경우)
				break;//더이상 읽어들이지 않고 while반복문 빠져나감
			}
			
			//다운로할 파일에서 읽어 들인 데이터가 있으면
			//한번씩 읽어들인 1048576byte(1MB) 전체를 출력스트림 통로를 통해 웹브라우저로 응답()
			servletOutputStream.write(b, 0, count);
			
		}
		
		//웹브라우저로 다운로드된 파일에 관하여  DB 내부의 파일 다운로드 횟수 1증가 시키기
		new FileDAO().hit(fileName); 
		
		//출력 스트림통로에 다운로드 시킬 파일에서 읽어 들인 데이터가 남아 있다면 완전히 웹브라우저로 내보내기
		servletOutputStream.flush();
		
		//자원해제(스트림 통로들 자원해제)
		servletOutputStream.close();
		fileInputStream.close();
	
	%>


</body>
</html>