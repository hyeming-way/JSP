package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.r;


@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {

	//다운로드 시킬 이미지 파일이 저장된 글번호 폴더 위치의 상위 폴더 
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// viewArticle.jsp 페이지에서 사용자가 <img src="이미지다운로드 요청주소">를 입력해  다운로드 요청할 때,
		// 해당 이미지가 어떤 글에 속해 있는지 (글 번호와 이미지 파일 이름)를 함께 서버로 전송하게 됩니다.
		// 이 정보를 기반으로 서버는 실제 이미지가 저장된 위치(경로)를 찾아야 하며,
		// 찾은 후에 웹 브라우저로 그 이미지를 전송하여 다운로드할 수 있도록 합니다.

		// 사용자가 입력한 값(파라미터)을 처리하기 전에 요청(request)의 문자 인코딩을 UTF-8로 설정합니다.
		// 이는 사용자가 한글 등의 문자를 입력했을 때, 깨지지 않고 정확히 서버에서 처리되도록 도와줍니다.
		req.setCharacterEncoding("UTF-8");

		// 응답(response)의 MIME 타입을 설정합니다.
		// 여기서는 text/html로 설정하고, 문자 인코딩도 UTF-8로 지정합니다.
		// 이것은 웹 브라우저에게 "이 응답은 HTML 형식이며 UTF-8로 인코딩되어 있다"고 알려주는 역할을 합니다.
		resp.setContentType("text/html;charset=utf-8");

		// 실제 웹 브라우저에 보내는 데이터의 문자 인코딩도 UTF-8로 설정합니다.
		// 이렇게 하면 서버에서 출력되는 문자들이 한글 등 다양한 언어에서도 깨지지 않고 올바르게 출력됩니다.
		resp.setCharacterEncoding("utf-8");

		
		//요청한 값 얻기
		String imageFileName = req.getParameter("imageFileName"); //다운로드해서 <img>태그에 보여질 이미지 파일 이름
		String articleNO = req.getParameter("articleNO"); //다운로드할 이미지 파일이 저장된 글 번호 폴더의 글번호  
		
		//웹브라우저로 데이터를 바이트 단위로 내보낼 출력스트림 통로 생성
		OutputStream out = resp.getOutputStream();
		
		//글번호 폴더 내부에 다운로드할 이미지파일에 대한 파일 경로 설정
		String path = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + imageFileName;
		File imageFile = new File(path);
		
		// 브라우저에게 캐시하지 말라고 지시합니다.
		// Cache-Control: no-cache는 브라우저가 이 응답을 저장하거나 재사용하지 않도록 합니다.
		// 즉, 항상 서버에서 새로운 응답을 받도록 합니다.
		resp.setHeader("Cache-Control", "no-cache");

		// 응답 헤더에 Content-disposition을 추가하여
		// 브라우저가 이미지를 직접 표시하는 대신 다운로드하도록 설정합니다.
		// attachment는 다운로드를 의미하고,
		// fileName=...은 다운로드할 파일의 이름을 지정합니다.
		resp.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);

		// 이미지 파일을 자바에서 읽어오기 위해 FileInputStream 객체를 생성합니다.
		// 이 스트림은 이미지 파일을 바이트(byte, 컴퓨터가 이해하는 가장 작은 데이터 단위) 형태로 읽어옵니다.
		FileInputStream in = new FileInputStream(imageFile);

		// 파일을 한 번에 모두 읽는 것이 아니라, 조금씩 나누어 읽기 위해 버퍼를 준비합니다.
		// 여기서는 8KB(= 1024 * 8 바이트) 크기의 배열을 사용해서 파일을 나눠서 읽습니다.
		byte[] buffer = new byte[1024 * 8];  // 8KB 크기의 바이트 배열 (임시 저장소)

		// 무한 반복문을 사용해서 파일을 끝까지 읽을 때까지 계속 반복합니다.
		while (true) {

		    // 파일에서 8KB만큼 데이터를 읽어서 buffer 배열에 저장합니다.
		    // 'count'는 실제로 읽은 바이트 수를 나타냅니다.
		    // 파일의 끝에 도달하면 -1이 반환됩니다.
		    int count = in.read(buffer);

		    // 더 이상 읽을 데이터가 없으면 반복을 멈춥니다.
		    // 즉, 파일을 모두 읽었으면 while문을 종료합니다.
		    if (count == -1) {
		        break;
		    }

		    // 읽어온 데이터를 웹 브라우저(사용자)에게 전송합니다.
		    // buffer 배열에서 0부터 count만큼의 바이트를 실제로 보냅니다.
		    // 예를 들어 이미지의 일부분을 전송하는 것이라고 생각하면 됩니다.
		    out.write(buffer, 0, count);
		}

		
		// 파일을 읽기 위해 열었던 입력 스트림(FileInputStream)을 닫아줍니다.
		// 파일이나 네트워크와 연결된 자원(자바에서는 "스트림"이라 부릅니다)은 사용이 끝나면 꼭 닫아야 합니다.
		// 그렇지 않으면 메모리 낭비가 생기거나, 다른 작업에서 해당 파일을 사용할 수 없게 될 수도 있습니다.
		in.close();

		// 웹 브라우저(사용자)에게 데이터를 보내기 위해 사용했던 출력 스트림(OutputStream)도 닫아줍니다.
		// 출력 스트림 역시 사용이 끝나면 자바 시스템에 반환(해제)해줘야 시스템 자원이 낭비되지 않습니다.
		out.close();

	}//doHandle메소드 닫는 기호 
	
}









