package sec04.ex01;

import java.util.List;

//단위 기능을 처리하는 메소드들을 가지고 있는 부장클래스
public class BoardService {

	private BoardDAO boardDAO; //사원
	
	public BoardService() { //생성자
		boardDAO = new BoardDAO();
	}
	
	//단위기능1. 모든 글 정보를 조회 명령하는 메소드
	public List<ArticleVO> listArticles(){
		
		//BoardDAO객체(사원)의 selectAllArticles()메소드를 호출해 전체 글을 조회명령 합니다.
		//반환받은 List를 BoardController(사장)에게 반환(보고)
		return boardDAO.selectAllArticles();
		
	}
	
	//단위기능2. 글쓰기 창에서 입력한 글정보를 DB의 t_member테이블에 추가시키고,
	// 추가시킨 글의 글번호를 조회해서 반환하는 메소드
	public int addArticle(ArticleVO articleVO) {

		return boardDAO.insertArticle(articleVO);
	}

	//단위기능3. BoardController서블릿에서 호출하는 메소드로서
	//글목록창(listArticles.jsp)에서 글제목을 눌렀을때
	//조회할 글번호를 매개변수로 받아서
	//조회할 글번호를 BoardDAO객체의 selectArticle메소드 호출시 매개변수로 전달하여
	//글 하나 조회 명령하는 메소드
	public ArticleVO viewArticle(int articleNO) { //조회할 글번호를 매개변수로 받음
		
		return boardDAO.selectArticle(articleNO); //BoardController로 반환
	}

	//단위기능4. BoardController서블릿에서 modArticle메소드를 호출하면
	//다시 BoardDAO객체의 updateArticle메소드를 호출하면서 UPDATE 명령
	public void modArticle(ArticleVO articleVO) {
		
		boardDAO.updateArticle(articleVO);
		
	}

	//단위기능5. 글번호를 매개변수로 받아서 받은 글번호의 글과 답변자식글까지 모두 삭제한 후 
	//부모글과 자식답변글의 글번호를 조회해서 반환을 DAO에 명령하는 메소드
	public List<Integer> removeArticle(int articleNO) {
		
		//글을 삭제하기 전 글번호들을 조회해서 ArrayList배열에 담아 반환받습니다.
		List<Integer> articleNOList = boardDAO.selectRemoveArticles(articleNO);
		
		//글을 삭제하는 메소드 호출
		boardDAO.deleteArticle(articleNO);
		
		return articleNOList; //삭제한 글번호 목록을 컨트롤러로 반환
	}

	//단위기능6. 답글쓰기는 새글쓰기 기능과 동일하게 BoardDAO의 insetNewArticle()메소드를 호출하여 명력
	public int addReply(ArticleVO articleVO) {

		//새글 추가시 사용한 insertArticle메소드를 이용해 답글 또한 DB에 추가시킵니다.
		return boardDAO.insertArticle(articleVO);
	}

}
