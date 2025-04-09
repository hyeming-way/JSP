package sec04.ex01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import sec03.ex01.MemberDAO;

//MVC 디자인 패턴에서 C 역할을 하는 서블릿 클래스
// - 클라이언트로부터 처음 요청을 받는 곳

//1. 글 목록을 조회하는 요청 -> /board/listArticles.do
//2. 글쓰기를 위한 글작성 화면 요청 -> /board/articleForm.do
/*
	게시판 글쓰기 구현 과정
	순서1. 글목록창(listArticles.jsp)에서 글쓰기창을 요청
	순서2. 글쓰기창에서 글을 입력하고 컨트롤러에 /board/addArticle.do로 글쓰기를 요청
	순서3. 컨트롤러에서 Service 클래스로 글쓰기창에서 입력한 글 정보를 전달해 테이블에 글을 추가
	순서4. 새 글을 추가하고 컨트롤러에서 다시 /board/listArticles.do로 요청하여 전체글을 조회해서 표시
	
	글을 작성할때 업로드 파일 저장소
	- C:\board\article_image
*/
//2-1. 글쓰기 요청 -> /board/addArticle.do
//3. 글 하나의 정보를 조회하여 글상세보기 화면에 출력 요청 -> /board/viewArticle.do?articleNO=조회할글번호
//4. 글 하나의 정보를 수정하는 요청 -> /board/modArticle.do
//5. 글 하나의 정보를 삭제하는 요청 -> /board/removeArticle.do
//......

//사장
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	//글에 첨부한 이미지 저장위치를 상수로 선언
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
		
	private BoardService boardService; //부장
	private ArticleVO articleVO;		
	private List<ArticleVO> articlesList;
	

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//재료 준비
		request.setCharacterEncoding("UTF-8"); //요청한 문자 데이터 한글 처리
		response.setContentType("text/html; charset=UTF-8"); //응답할 문자 데이터 문자 처리 방식 UTF-8 설정 및
															 //응답할 데이터 유형(MIME-TYPE)을 html 기반으로 설정
		response.setCharacterEncoding("UTF-8"); //응답할 문자 데이터 한글 처리
		
		//답글에 대한 부모글번호를 저장하기위해 session 내장객체를 저장시킬 변수 선언
		HttpSession session = null;
		
		String nextPage = ""; //응답할 VIEW경로 저장 또는 포워딩 주소 저장 용도
		
		//1. 클라이언트가 요청한 전체 URL중에서 2단계 요청주소 얻기
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		//1. 글 목록을 조회하는 2단계 요청 주소 -> /listArticles.do 또는 없음
		//2. 글쓰기를 위한 글작성 화면 2단계 요청 주소 -> /articleForm.do
		//2-1. 글쓰기 2단계 요청 주소 -> /addArticle.do
		//3. 글 리스트 화면에서 글제목 하나를 클릭했을때 글번호를 전달해 글을 조회후 글 상세화면에 보여주는 2단계 요청주소 -> viewArticle.do
		//4. 글 하나의 정보를 수정하는 2단계 요청 주소 -> /modArticle.do
		//5. 글 하나 삭제하는 2단계 요청 주소 -> /removeArticle.do
		//6. 답변글을 작성하는 화면 2단계 요청 주소 -> /replyForm.do
		//7. 작성한 답변글 정보를 t_board테이블에 부모글번호와 함께 추가 2단계 요청주소 -> /addReply.do
		
		//2. 2단계 요청한 주소 판단해서 작업
		//   /board 까지만 입력해서 모든 글을 조회 요청하거나, 
		//   /board/listArticles.do로 입력해서 모든 글을 조회 요청하는 경우
		if(action == null || action.equals("/listArticles.do") || action.equals("/*")) {
			
			//BoardService객체(부장)의 listArticles()메소드를 호출해서 모든 글 정보 조회해오라고 명령
			articlesList = boardService.listArticles();
			
			//request 객체 메모리에 조회된 글 정보가 저장된 ArrayList배열(Model) 바인딩
			request.setAttribute("articlesList", articlesList);
			
			//VIEW페이지(listArticles.jsp)로 포워딩하기위해 주소 경로 저장
			nextPage = "/board01/listArticles.jsp";
			
		//2단계 요청 주소가 /articleForm.do이면 (글쓰기 화면을 요청했다면)
		}else if(action.equals("/articleForm.do")) {
			
			//새 글을 작성할 수 있는 화면을 재요청하기위해 VIEW주소 저장
			nextPage = "/board01/articleForm.jsp";
		
		//2단계 요청 주소가 /addArticle.do이면 (DB의 t_board테이블에 새 글 추가요청 했다면)
		}else if(action.equals("/addArticle.do")) {
			
			//추가한 새글 번호를 반환받아 저장할 변수
			//반환받는 이유는? 글번호 폴더를 생성하기 위함입니다.
			int articleNO = 0;
			
			//upload()메소드를 호출해 글쓰기 화면에서 첨부하여 전송된 글 관련 정보를
			//HashMap에 key/value 쌍으로 저장합니다.
			//그런 후 글 입력시 추가적으로 업로드할 파일을 선택하여 글쓰기 요청을 했다면
			//업로드할 파일명, 입력한 글제목, 입력한 글내용을 key/value형태의 값들로 저장되어 있는 HashMap을 리턴받는다.
			//그렇지 않을 경우에는??
			//업로드할 파일명을 제외한 입력한 글제목, 입력한 글내용을 key/value 형태의 값들로 저장되어 있는 HashMap을 리턴받는다.
			Map<String, String> articleMap = upload(request, response);
			
			//HashMap에 저장된 글정보(업로드한 파일명, 입력한 글제목, 입력한 글내용)을 다시 꺼내옵니다.
			String title = articleMap.get("title");
			String content = articleMap.get("content");
			String imageFileName = articleMap.get("imageFileName");
			
			//DB에 추가하기위해 사용자가 입력한 글정보+업로드할 파일명을 ArticleVO객체의 각 변수에 저장
			articleVO.setArticleNO(0); //추가할 새글의 부모글번호를 0으로 지정해서 부모글이 없다는것을 알림
			articleVO.setId("hong"); //추가할 새글 작성자 ID를 hong으로 저장 (참고. t_member테이블에 ID가 hong이 저장되어 있어야함)
			articleVO.setTitle(title); //추가하기위해 입력한 글제목 저장
			articleVO.setContent(content); //추가하기위해 입력한 글내용 저장
			articleVO.setImageFileName(imageFileName); //새글 입력시 첨부해서 업로드한 파일명 저장
			
			//그런 다음 BoardService 객체의 addArticle()메소드 호출시
			//매개변수로 추가할 새글 정보(ArticleVO객체의 정보)를 전달하면 새 글 정보가 DB의 t_board테이블에 등록됩니다.
			//-> 새글을 DB의 t_board테이블에 등록한 후 등록한 새글의 글번호를 조회해서 반환받습니다.
			articleNO = boardService.addArticle(articleVO); //부장에게 명령
			
			//파일을 첨부한 경우에만
			if(imageFileName != null && imageFileName.length() != 0) {
				//temp 폴더에 임시로 업로드된 파일에 접근하기위해 File 객체 생성
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\temp\\" + imageFileName);
				
				//글번호 폴더를 생성하기위해 경로를 지정한 File 객체 생성
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
				//위 destDir 참조변수에 지정된 경로("C:\board\article_image"폴더 안에)의 글번호 폴더 생성
				
				//글번호 폴더 생성
				destDir.mkdirs();
				
				//temp 폴더에 업로드된 파일을 DB에 추가한 새 글의 글번호 폴더로 이동시키자
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
				//클라이언트의 웹브라우저로 응답할 출력스트림 통로 객체 생성
				PrintWriter pw = response.getWriter();
				
				pw.print("<script>");
				pw.print("alert('새 글을 추가했습니다.');");
				pw.print("location.href='" + request.getContextPath() + "/board/listArticles.do';");
				pw.print("</script>");
				
				return; //doHandle 빠져나가기(디스패처를 안 타기 위해)
				
			}
			//DB에 새글을 추가하고 컨트롤러에서 /board01/listArticles.jsp로 이동하여
			//전체글을 DB에서 검색하여 보여주기 위해 다음과 같은 요청할 주소를 지정합니다.
			//nextPage = "/board/listArticles.do";		
		
		//listArticles.jsp페이지에서 조회된 글 중에서 하나의 글제목을 클릭하여
		//글번호에 해당되는 글을 조회하여 보여주는 글 상세화면 요청 2단계 주소를 받았을때
		}else if(action.equals("/viewArticle.do")) {
			
			//요청한 값 얻기(조회할 글번호 얻기)
			String articleNO = request.getParameter("articleNO");
			
			//글번호를 이용하여 조회한 글 하나의 정보를 ArticleVO 객체 변수에 저장후 반환받기
			articleVO = boardService.viewArticle( Integer.parseInt(articleNO) );
			
			//조회된 글의 정보를 VIEW페이지(viewArticle.jsp)에 출력해서 보여주기 위해
			//request 내장객체에 조회해서 반환받은 ArticleVO객체를 바인딩
			request.setAttribute("article", articleVO);
			
			//재요청(포워딩)할 VIEW페이지 경로 저장
			nextPage = "/board01/viewArticle.jsp";		
			
		//글 수정 요청 2단계 주소를 받았을때	
		}else if(action.equals("/modArticle.do")) {
			
			//글 수정을 위해 수정한 정보를 HashMap에 담아 반환받는다
			//또한 수정할 이미지 파일 같은 글번호 폴더에 업로드 시켜서 파일을 덮어씌워 수정시킨다.
			Map<String, String> articleMap = upload(request, response);
			
			//수정할 글번호를 얻는다.
			int articleNO = Integer.parseInt(articleMap.get("articleNO"));
			
			//수정시 입력한 글 제목을 얻는다.
			String title = articleMap.get("title");
			
			//수정시 입력한 글내용을 얻는다.
			String content = articleMap.get("content");
			
			//수정시 선택한 이미지파일명을 얻는다.
			String imageFileName = articleMap.get("imageFileName");
			
			//ArticleVO객체에 위에 작성한 수정시 입력한 값들을 각 변수에 저장
			articleVO.setArticleNO(articleNO);
			articleVO.setParentNO(0);
			articleVO.setId("hong");
			articleVO.setTitle(title);
			articleVO.setContent(content);
			articleVO.setImageFileName(imageFileName);
			
			//DB의 t_board테이블에 수정할 정보를 UPDATE하기위해
			//BoardService의 modArticle메소드 호출시 ArticleVO객체를 매개변수로 전달
			boardService.modArticle(articleVO);
			
			if(imageFileName != null & imageFileName.length() != 0) {
				
				String originalFileName = articleMap.get("originalFileName"); //업로드된 원본이미지명
				
				//수정하기위해 업로드한 이미지 파일을 temp 폴더에서 글번호 폴더로 이동시키기 위해 작성
				//글 수정시 첨부한 이미지가 temp폴더에 업로드된 이미지파일에 접근하기 위한 경로
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\temp\\" + imageFileName );						
				
				//글 수정시 첨부한 이미지가 temp폴더에서 글번호 폴더로 이동해야하기때문에 글번호 폴더에 접근하기 위한 경로
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
				
				destDir.mkdirs(); //없으면 만들어주고, 있으면 안 만들어줌.
				
				FileUtils.moveFileToDirectory(srcFile, destDir, true); //true -> 파일을 덮어씌움.
				
				//기존에 글 번호 폴더에 저장되어있던 이미지파일을 삭제시킵니다.
				File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
				oldFile.delete();
				
			} //if
			
			//클라이언트의 웹브라우저로 응답할 출력 스트림 통로 객체 생성
			PrintWriter pw = response.getWriter();
			
			pw.print("<script>");
			pw.print("alert('글을 수정했습니다.');");
			pw.print("location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO + "';");
			pw.print("</script>");
			
			return; // doHandle 빠져나가서 아래의 포워딩을 막음			
			
		//삭제 2단계 요청 주소를 받았을 때	
		}else if(action.equals("/removeArticle.do")) {
			
			//설명
			//삭제 요청을 하면 글번호에 대한 글과 그 자식글을 삭제하기전에 먼저 삭제할 글번호와 자식글번호를
			//목록으로 가져옵니다. 그리고 글을 삭제한 후 글번호로 이루어진 이미지 저장 폴더까지 모두 삭제시킵니다.
			
			//1. 삭제할 글번호(요청한 값) 얻기
			int articleNO = Integer.parseInt(request.getParameter("articleNO"));
			
			//2. 삭제할 글번호에 대한 글을 삭제한 후 부모글과 자식글의 글번호를 조회해서 목록으로 가져옵니다.
			List<Integer> articleNOList = boardService.removeArticle(articleNO);
			
			for(int _articleNO : articleNOList) {
				//삭제할 글번호 폴더 경로에 접근해서 삭제하기 위해 File클래스의 객체 생성
				File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
				
				//위 생성한 File객체의 경로 정보가 폴더라면?
				//exists() -> 폴더면 true, 아니면 false를 반환
				if(imgDir.exists()) {
					
					//글번호 폴더 삭제
					FileUtils.deleteDirectory(imgDir);					
				}
			} //for
			
			//클라이언트의 웹브라우저로 응답할 출력 스트림 통로 객체 생성
			PrintWriter pw = response.getWriter();
			
			pw.print("<script>");
			pw.print("alert('글을 삭제했습니다.');");
			pw.print("location.href='" + request.getContextPath() + "/board/listArticles.do';");
			pw.print("</script>");		
			
			return; // doHandle 빠져나가서 아래의 포워딩을 막음		
			
			
		//답변글을 작성하는 화면 2단계 요청 주소 -> /replyForm.do	
		}else if(action.equals("/replyForm.do")) {
			//답글창 요청시 미리 부모글번호를 parentNO 속성으로 세션영역에 저장해놓고,
			//답글을 작성한 후 등록을 요청(/addReply.do)하면 세션에서 parentNO부모글번호를 가져와
			//t_board테이블에 답글의 정보에 같이 추가시킵니다.
			
			int parentNO = Integer.parseInt(request.getParameter("parentNO"));
			
			session = request.getSession(); //session 내장객체 얻기
			session.setAttribute("parentNO", parentNO); //세션에 부모글번호 바인딩
			
			//답글 작성창(화면)VIEW 주소 저장
			nextPage = "/board01/replyForm.jsp";	
			
			
		//작성한 답변글 정보를 t_board테이블에 부모글번호와 함께 추가 2단계 요청주소 -> /addReply.do	
		}else if(action.equals("/addReply.do")) {
			
			//답글 전송시 session 내장객체 영역에 바인딩된 부모글(주글) 글번호를 꺼내옵니다.
			session = request.getSession(true); //기존에 사용한 세선메모리 영역 얻기
			int parentNO = (Integer)session.getAttribute("parentNO"); //세션에서 부모글 번호 얻기
			session.removeAttribute("parentNO"); //꺼내오고 나서 세션에 저장된 부모글 번호 삭제
			
			//upload()메소드를 호출해 답글쓰기 화면에서 첨부하여 전송된 답글 관련 정보를
			//HashMap에 key/value 쌍으로 저장합니다.
			//그런 후 답글 입력시 추가적으로 업로드할 파일을 선택하여 답글쓰기 요청을 했다면
			//업로드할 파일명, 입력한 글제목, 입력한 글내용을 key/value형태의 값들로 저장되어 있는 HashMap을 리턴받는다.
			//그렇지 않을 경우에는??
			//업로드할 파일명을 제외한 입력한 답글제목, 입력한 답글내용을 key/value 형태의 값들로 저장되어 있는 HashMap을 리턴받는다.
			Map<String, String> articleMap = upload(request, response);
			
			//HashMap에 저장된 답글정보(업로드한 파일명, 입력한 답글제목, 입력한 답글내용)을 다시 꺼내옵니다.
			String title = articleMap.get("title");
			String content = articleMap.get("content");
			String imageFileName = articleMap.get("imageFileName");
			
			//DB의 t_board 테이블에 추가하기위해 사용자가 입력한 답변글 정보 + 업로드할 파일명을 ArticleVO객체의 각 변수에 저장
			articleVO.setParentNO(parentNO); //추가할 답글의 부모 글번호를 위에서 만든 변수값으로 저장
			articleVO.setId("hong"); //추가할 답글 작성자 ID를 hong으로 저장
			articleVO.setTitle(title); //추가할 답글 제목 저장
			articleVO.setContent(content); //추가할 답글 내용
			articleVO.setImageFileName(imageFileName); //답글 입력시 업로드한 파일명 저장
			
			//작성한 답글 정보를 DB에 추가하기위해 부장의 addReply메소드 호출시
			//articleVO객체를 매개변수로 전달하여 INSERT명령합니다.
			//그리고 INSERT에 성공하면 성공한 글번호폴더를 만들기 위해 글번호를 리턴 받습니다.
			int articleNO = boardService.addReply(articleVO);
			
			//답글에 첨부한 이미지를 temp폴더에 업로드 해 놨기 때문에 답글 글번호폴더를 생성해서 이동!
			if(imageFileName != null && imageFileName.length() != 0) {		
				//temp 폴더에 임시로 업로드된 파일에 접근하기위해 File 객체 생성
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\temp\\" + imageFileName);
				
				//답글번호 폴더를 생성하기위해 경로를 지정한 File 객체 생성
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
				//위 destDir 참조변수에 지정된 경로("C:\board\article_image"폴더 안에)의 답글번호 폴더 생성
				
				//답글번호 폴더 생성
				destDir.mkdirs();
				
				//temp 폴더에 업로드된 파일을 DB에 추가한 새 답글의 답글번호 폴더로 이동시키자
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
			}
			
			//클라이언트의 웹브라우저로 응답할 출력스트림 통로 객체 생성
			PrintWriter pw = response.getWriter();
			
			pw.print("<script>");
			pw.print("alert('답글을 추가했습니다.');");
			pw.print("location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNO=" + articleNO + "';");
			pw.print("</script>");
			
			return; //doHandle 빠져나가기(디스패처를 안 타기 위해)
					
		}else {
			
			nextPage = "/board01/listAtricles.jsp";
			
		}
		
		//디스패처 방식으로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	
	} //doHandle 메소드
	
	
	//C:\\board\\article_image\\temp폴더에 글 작성시 첨부한 이미지파일을 업로드 시키고
	//입력한 글제목, 글내용, 첨부한 이미지명을 HashMap에 담아서 리턴하는 메소드
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Map<String, String> articleMap = new HashMap<String, String>();
		
		//글쓰기를 할 때 첨부한 이미지를 저장할 폴더경로에 접근하기위해 File 객체를 생성합니다.
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
								//"C:\\board\\article_image"
		
		//업로드할 파일 데이터를 임시로 저장시킬 저장소 역할의 객체메모리 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//파일 업로드시 사용할 임시메모리 최대 크기 1메가 바이트로 지정
		factory.setSizeThreshold(1024*1024*1);
		
		//임시메모리에 파일 업로드시 지정한 1메가 바이트 크기를 넘길 경우 업로드될 파일 경로를 지정함
		factory.setRepository(currentDirPath);
		
		//참고
		//DiskFileItemFactory 클래스는 업로드 파일의 크기가 지정한 크기를 넘기 전까지는
		//업로드한 파일 데이터를 임시 메모리에 저장하고 지정한 크기를 넘길 경우 디렉터리에 파일로 저장한다.
		
		//파일업로드할 메모리를 생성자쪽으로 전달받아 저장한 파일업로드를 처리하기 위한 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			/*
			uploadForm.jsp 파일업로드 요청하는 디자인 페이지에서  
			첨부한 파일 1개와  입력한 파라미터2개의 정보들
			request객체에서 꺼내와서  각각의 DiskFileItem객체들에 저장한 후 
			각각의 DiskFileItem객체들을  ArrayList배열에 추가 하게 됩니다. 
			그후 ~ ArrayList배열 자체를 반환 해 줍니다.
			*/	
			List items = upload.parseRequest(request);
			
			//ArrayList배열에 저장된 DiskFileItem객체(요청한 아이템)의 갯수만큼 반복
			for(int i=0; i<items.size(); i++) {
				
				//ArrayList배열에 저장된 DiskFileItem객체를 하나씩 반복해서 얻는다
				FileItem fileitem = (FileItem)items.get(i);
				
				//얻은 DiskFileItem객체의 정보가 첨부한 파일 요청이 아니고
				//입력한 텍스트 요청 정보가 저장된 DiskFileItem객체일 경우?
				//요약 : 작성한 글제목과 글내용이면?
				if(fileitem.isFormField()) {
					
					System.out.println(fileitem.getFieldName() + "=" + fileitem.getString("UTF-8"));
					
					//articleForm.jsp페이지에서 입력한 글제목, 글내용만 HashMap에 (key=value)형식으로 저장합니다.
					//HashMap에 저장된 데이터의 예 => {title=입력한글제목, content=입력한글내용}
					articleMap.put(fileitem.getFieldName(), fileitem.getString("UTF-8"));
					
				}else { //얻은 DiskFileItem객체의 정보가 첨부한 파일일 경우
					
					System.out.println("<input type='file'>의 name속성값 : " + fileitem.getFieldName());
					
					System.out.println("업로드 요청시 첨부한 파일명 : " + fileitem.getName());
					
					System.out.println("업로드 요청시 첨부한 파일크기 : " + fileitem.getSize() + "bytes");
					
					//articleForm.jsp페이지에서 입력한 글제목, 글내용, 요청한 업로드할 파일등의 모든 정보를
					//HashMap에 (key=value)형식으로 저장합니다.
					//HashMapdp 저장된 데이터의 예 => {title=입력한글제목, content=입력한글내용}
					
					articleMap.put(fileitem.getFieldName(), fileitem.getName());
									
					//업로드시 첨부한 파일의 크기가 0bytes보다 크다면?
					//(파일 업로드 요청했다면?)
					if(fileitem.getSize() > 0) {
						
						//업로드할 파일명을 얻어 파일명 뒤에서부터 \\문자열이 들어있는지 index 위치를 알려주는데
						//없으면 -1을 반환
						int idx = fileitem.getName().lastIndexOf("\\");
						System.out.println(idx);
						
						if(idx == -1) { //업로드할 파일명에 \\가 포함되어 있지 않다면?
							
							//    /문자열이 업로드할 파일명에 포함되어 있지 않으면? -1을 반환
							idx = fileitem.getName().lastIndexOf("/");
							System.out.println("업로드할 파일명에 /기호는 없다.");
							
						}
						
						//업로드할 파일명 얻기
						String fileName = fileitem.getName().substring(idx+1);
														//idx에 -1이 저장되어 있으니 이를 0으로 만들어줌(index가 0부터 시작하니까)
						//업로드할 파일 경로 + 파일명 전체 주소를 문자열로 만들어 접근한 File 객체 생성
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
												//"C:\\board\\article_image\\temp\\업로드할이미지파일명"
						
						//실제 파일 업로드
						fileitem.write(uploadFile);
						
					}//바깥 if
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return articleMap; //upload메소드를 호출한 코드줄 doHandle메소드 내부로 HashMap리턴
		
	}//upload 메소드 끝
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService(); //BoardService 객체 생성 후 초기화
		articleVO = new ArticleVO(); //Article
	}
	
	//클라이언트가 GET요청방식으로 요청하면 호출되어 요청을 처리하는 콜백메소드 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	//클라이언트가 POST요청방식으로 요청하면 호출되어 요청을 처리하는 콜백메소드 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
}
