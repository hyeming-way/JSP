package sec04.ex01;

import java.sql.Date;

//조회한 하나의 글 정보를 저장할 용도의 클래스
public class ArticleVO {

	//변수
	private int level; //글제목 들여쓰기 계산할때 쓰이는 오라클의 가상 level 컬럼 값
	private int articleNO; //글번호
	private int parentNO; //부모 글번호
	private String title; //글제목
	private String content; //글내용
	private String imageFileName; //글 작성시 첨부된 파일명
	private String id; //글 작성한 사람의 아이디
	private Date writeDate; //글작성 날짜

	//기본생성자
	public ArticleVO() { }
	
	//모든 변수값 초기화할 생성자
	public ArticleVO(int level, int articleNO, int parentNO, String title, String content, String imageFileName,
					 String id, Date writeDate) {
		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
		this.writeDate = writeDate;
	}
	
	//글작성 날짜를 제외한 모든 변수값을 초기화할 생성자
	public ArticleVO(int level, int articleNO, int parentNO, String title, 
					 String content, String imageFileName, String id) {
		this.level = level;
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.imageFileName = imageFileName;
		this.id = id;
	}

	
	//getter, setter 메소드
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
}
