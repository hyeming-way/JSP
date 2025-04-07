package sec01.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//파일 다운로드 서비스를 제공할 서블릿 클래스

@WebServlet("/download.do")
public class fileDownload extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청한 파라미터 문자 한글처리
		request.setCharacterEncoding("utf-8");
		
		//2. 클라이언트의 웹브라우저로 다운로드할 파일의 정보를 내보내기 위해
		//	 응답할 데이터 종류(MIME-TYPE)를 설정하고 문자 한글처리
		response.setContentType("text/html; charset=utf-8");
		
		//3. 다운로드할 파일이 저장되어 있는 경로
		String file_repo = "C:\\file_repo";
		
		//4.result.jsp에서 <a>를 클릭해 요청한 다운로드할 파일명을 request 객체에서 얻기
		String fileName = request.getParameter("fileName");
		
		//5. 3. 4.에서 얻은 다운로드할 파일 경로 + 다운로드할 파일명 연결해서 하나의 전체 경로를 만들어 줍니다.
		String downFile = file_repo + "\\" + fileName;
		
		//6. 파일 다운로드를 요청한 클라이언트의 웹브라우저 창과 연결된
		//   파일의 정보를 읽어들여 내보낼 출력스트림 통로 역할을 하는
		//   org.apache.catalina.connertor.CoyoeOutputStream@32d8fcdd객체를 반환해줍니다.
		OutputStream out = response.getOutputStream();
		
		//7. 실제 다운로드할 파일에 접근하기 위해 경로를 File객체에 저장
		File f = new File(downFile);
		
		/*		
			웹브라우저 캐시에 대해 설명하기 위한 내용
				웹개발을 하다보면 게시글 등의 데이터를  DB에 등록 했는데도 브라우저에서 새로고침 시 
				해당 데이터에 대한 내용이 반영되지 않는 경우가 있습니다. 
				혹은 데이터 뿐만 아니라 javascript나, HTML, CSS 등의 정적자원을 서버에서 수정했는데도
				새로고침시 적용되지 않는 경우도 있습니다.
			
			웹브라우저의 캐시 공간이란?
				브라우저에는 캐시 스토리지 공간이있는데, 
				이것은 서버페이지와 불필요한 통신을 하지 않기 위해 마련된 공간입니다.
				최초 서버로부터 요청한 자원들(javascript,HTML,CSS,이미지 등)을 내려 받고 
				같은 자원을 새로 고침등을 통해서 다시 요청하는 경우
				브라우저는 실제로는 서버로 HTTP요청을 하지 않고 
				브라우저 자신의 캐시 스토리지에 저장해 두었던
				읽어 들였던 자원들 재사용하게 됩니다.
			
			예를 들어 test.jsp를 최초 요청한 경우 서버로부터 응답된 자원들을 
			웹브라우저 캐시 스토리지에 저장하고  F5나 
			주소표시줄에 주소를 다시 입력해 다시 test.jsp를 재요청한경우
			불필요하게 다시 HTTP요청을 하는 것이 아니라 캐시 스토리지에서 저장된 데이터를 꺼내서 
			웹브라우저 화면에 보여줍니다.
			이러한 브라우저 캐시 기능이 성능상 이점을 가져다 줄수 있겠으나
			게시판이나 네이버의 실시간 검색어 처럼 자주 변하는 동적인 데이터 부분까지 
			브라우저의 캐시 스토리를 사용한다면 사용자는 변환된 데이터의 결과를 웹브라우저로 볼수 없고
			계속 같은 화면만 보게 될것입니다.
			
	  		응답 헤더를 통한 캐시 스토리지 제어 설명
		  		HTTP 응답 메세지의 몇가지 헤더 속성을 통해서 
		  		웹브라우저가 현재 페이지 내용을 캐시 스토리지 저장하는 것을 사용하지 않도록 할수 있습니다.
		  		response객체의 해당 속성들에 값을 설정해 웹브라우저가 캐시 스토리지를 사용하지 않고
		  		매번 새로운 요청을 통해 응답결과를 얻어오도록 할수 있습니다.		
		*/	
		
		//8. HTTP1.1 버전에서 지원하는 헤더로 no-cache값을 설정하면 브라우저는 응답받은 결과를
		//   캐시 스토리지에 저장하지않습니다.
		//   또한 뒤로가기 등을 통해서 전페이지로 이동하는 경우 페이지를 캐싱할 수 있으므로 no-store 값 또한
		//   추가해주어야합니다.
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		
		//9. 웹브라우저에서 다운로드할 <a> 링크를 클릭시
		//9-1.Content-Disposition속성에 attachment; 값을 지정하여 
		//    다운로드시 무조건 "파일 다운로드 다른이름으로 저장?" 대화상자가 뜨도록 하는 헤더 속성의 설정 
		
		//9-2. 다운로드할 파일명이 깨져 내려 받아지지않도록 하기 위해 
		//     Content-Disposition속성에 다운로드할 파일명을 인코딩 후 설정
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
		
		/*
			Content-Disposition : attachment
			브라우저 인식 파일 확장자를 포함하여 모든 확장자의 파일들에 대해....
			다운로드시 무조건 "파일 다운로드" 대화 상자가 뜨도록 하는 헤더 속성의 값 입니다.
			
			Content-Disposition : inline
			브라우저 인식 파일 확장자를 가진 파일들에 대해서는 웹브라우저 상에서 바로 파일을 열고
			그외의 파일들에 대해서는 "파일 다운로드" 대화 상자가 뜨도록 하는 헤더 속성의 값 입니다. 
			
		*/
		
		//10. 실제 파일 다운로드를 구현
		// - 다운로드할 파일에 작성된 데이터를 바이트 단위로 읽어들일 입력스트림(버퍼)통로 생성
		FileInputStream in = new FileInputStream(f);
		
		//다운로드할 파일에서 데이터를 8KB씩 읽어와 저장할 배열 생성
		byte[] buffer = new byte[1024*8];
		
		while(true) {
			
			//다운로드할 파일의 내용을 약 8kb 단위로 읽어와 위 buffer 배열에 저장 후
			//지정한 바이트수를 리턴
			//만약 읽어들일 바이트수가 없으면 -1을 리턴
			int count = in.read(buffer);
			
			if(count == -1) { //더이상 다운로드할 파일에서 읽어들일 데이터가 없으면?
				break; //while 반복문을 빠져나가 더이상 in.read()메소드를 호출 못하게 함
			}
			
			//출력스트림 OutputStram통로를 통해 위 한번 읽어들인 (in.read()) 파일의 정보가 저장된
			//buffer배열 전체 byte데이터들의 배열의 0index 위치부터 count 변수의 끝까지
			//웹브라우저로 출력(응답, 내보냄)
			out.write(buffer, 0, count);

		}

	}

}
