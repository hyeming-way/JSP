package sec04.ex01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
오라클 DBMS의 t_board테이블과 연결하여 
데이터베이스 작업(SELECT, INSERT, DELETE, UPDATE등)
하는 사원 클래스 
*/
public class BoardDAO {
//DB작업할 삼총사 객체
	//위 4가지 연결 값을 이용해서 오라클 XE데이터베이스의 t_board테이블과 연결을 맺은 정보를 지니고 있는
	//T4CConnection객체를 저장할 참조변수 선언
	private Connection con;
	//오라클 XE데이터베이스의 t_board테이블과 연결후 
	//우리 개발자가 만든 SQL문장을 오라클 DB의 t_board테이블에 전송하여 실행할 역할을 하는 
	//OraclePreparedStatementWrapper실행 객체를 얻어 저장할 참조변수 선언
	private PreparedStatement pstmt;
	//우리 개발자가 BoardDAO.java에 작성한 SQL문장중 SELECT문으로 t_board테이블에 저장된 회원레코드를
	//조회해서 가져와 임시로 저장할 ResultSet객체 메모리의 주소를 저장시킬 참조변수 선언
	private ResultSet rs;
	
	//DataSource커넥션풀 객체의 주소를 저장할 참조변수 선언
	private DataSource dataSource;
	
	//기본생성자 
	//역할 : new BoardDAO(); 객체 생성시 호출되는 생성자로~
	//		생성자 내부에서 커넥션풀 역할을 하는 DataSource객체를 얻어 위 dataSource참조변수에 저장
	//참고. context.xml에 작성한  <Resouerce  name="jdbc/oracle" .. type="javax.sql.DataSource" ..  />
	public BoardDAO() {
		try {
			//1. JNDI 네이빙 서비스를 시작하고,
			//   톰캣 서버에 등록된 <Resource/>태그의 자원에 
			//   접근할 있는 환경을 설정하는 객체 생성(DataSoure자원을 얻을 환경준비)
			//   InitialContext객체를 생성하면, java애플리케이션은 해당 객체를 통해 
			//   톰캣서버 내부에 등록된 <Resource>들을 탐색할수 있습니다.
			Context ctx = new InitialContext();
			
			//2..lookup메소드는 특정 이름에 대응하는 객체를 찾는 역할을 합니다.
		    //  즉, ctx.lookup("java:/comp/env")는 
		    //   "java:/comp/env"경로는 JNDI에서 표준적으로 사용되는 경로로
		    //   주로 애플리케이션 환경 설정(환경변수, 데이터베이스 연결정보 등)에 접근하는 기본경로 입니다
		    //   이 기본경로 아래에 커넥션풀(DataSource) 등의 리소스 이름이 설정됩니다. 
			Context envCtx	= (Context)ctx.lookup("java:/comp/env");
			
			//3. 그런 후 다시 톰캣서버는 context.xml에 설정한
			// <Resouerce  name="jdbc/oracle" .. type="javax.sql.DataSource" ..  />태그의
			// name속성값 "jdbc/oracle"(JNDI 기법을 사용하기 위한 key)를 이용해
			// 톰캣서버가 DB와 미리연결을 맺은 Connection객체들을 보관하고 있는
			// DataSource커넥션풀 객체를 찾아 value로 반환해 줍니다.
			dataSource = (DataSource)envCtx.lookup("jdbc/oracle");
				
		} catch (Exception e) {
			System.out.println("DataSource커넥션풀 객체 얻기 실패 : " + e);
		}
	
	} //생성자 끝
	
	
	//BoardService 부장클래스에서 BoardDAO 사원클래스의 selectAllArticles()메소드를 호출하면
	//계층형 SELECT SQL문을 이용해 계층형 구조로 전체 글을 조회한 후 반환하는 메소드
	public List<ArticleVO> selectAllArticles(){
		
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>(); //조회된 글 정보들이 저장될 배열
		
		try {
			//1. 커넥션풀에서 커넥션 객체 얻어 DB와 BoardDAO.class 프로그램과 연결
			con = dataSource.getConnection();
			
			//2. 계층형 구조로 전체 글을 조회하는 오라클의 계층형 SELECT문 작성
			String query = "select level, articleNO, parentNO, title, content, id, writeDate"
					     + " from t_board"
					     + " start with parentNO=0"
					     + " connect by prior articleNO=parentNO"
					     + " order siblings by articleNO desc";
			/*
				위 select 구문 참고 설명
				
					1. 먼저 start with parentNO=0 parentNO 열 값이 0인 글이 최상위 계층의 글이다는 의미
					   parentNO가 0이면 그 글은 최상위 부모글부터 아래로 펼쳐지면서 트리구조로 조회하자
					
					2. connect by prior articleNO=paraentNO
					   각 글이 어떤 부모글과 연결되어 트리구조로 조회되는지 조건을 설정
					   
					3. order siblings by articleNO desc
					   계층 구조로 조회된 글을 articleNO열의 글번호 순으로 내림차순 정렬해서 조회
					
					4. select문의 level열은 계층형 SQL문 실행시,
					   connect by prior articleNO=parentNO로 글이 나열되면서 각 글의 깊이값을 계싼해서 나타냅니다.
					   오라클이 알아서 부모글에 대해서 깊이를 계산해서 Level값으로 반환합니다.
					   
					5. 따라서 계층형 SQL문을 실행하면 오라클이 전체 글에 대해서 내부적으로 모든 글의 articleNO에 대해,
					   다른 글들의 parentNO와 비교해서 같으면 그 글들을 parentNO의 글 아래 정렬한 후,
					   articleNO의 내림차순으로 정렬하는 과정을 거칩니다.      
			*/
			
			System.out.println(query);
			
			//3. PreparedStatement객체 얻기
			pstmt = con.prepareStatement(query);
			
			//4. select 구문 전체를 DB의 t_board 테이블에 전송해서 실행! 실행한 레코드들을 Resultset 객체에 담아 반환
			rs = pstmt.executeQuery();
			
			//5. while 반복문을 이용해 조회된 ResultSet 객체의 정보를 꺼내와 ArticleVO에 저장
			while(rs.next()) {
				
				ArticleVO articleVO = new ArticleVO(rs.getInt("level"), rs.getInt("articleNO"), rs.getInt("parentNO"),
													rs.getString("title"), rs.getString("content"), null, 
													rs.getString("id"), rs.getDate("writeDate"));
				
				//ArrayList배열에 ArticleVO객체 추가
				articlesList.add(articleVO);				
			}
			
		} catch (Exception e) {
			System.out.println("BoardDAO클래스의 selectAllArticles메소드 내부에서 SELECT문 실행 오류");
			e.printStackTrace();
		} finally {
			//자원해제
			ResourceClose();
		}
		
		//BoardService(부장)에게 보고(반환)
		return articlesList;
		
	} //selectAllArticles 메소드 끝
	
	
	//DB작업 관련 객체 메모리들 사용이 끝난 후  자원 해제하는 기능의 메소드
	public void ResourceClose() {		
		try {
			//ResultSet객체는 SQL문(SELECT)의 조회된 결과를 임시로 저장하는 객체 입니다.
			//이객체를 사용하고 있다면 제거 시키자.
			if(rs != null){
				rs.close();
			}
			//PrepardStatement객체는 SQL 쿼리를 실행하는 객체입니다.
			//이객체를 사용하고 있다면 제거 시키자.
			if(pstmt != null) {
				pstmt.close();
			}
			//Connection객체는 데이터베이스와의 연결을 관리하는 객체로, 더이상 데이터베이스 연결할필요가 없으면
			//이긱체를 사용하고 있다면 제거 시키자.
			if(con != null) {
				con.close();//DataSource커넥션풀에 사용이 끝난 Connection객체 반납 (자원해제)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	//DB에 새글을 추가하기전에 DB에 저장된 가장 최신 글번호를 검색해서 제공하는 메소드
	private int getNewArticleNo() {
		try {
			con = dataSource.getConnection();
			//DB의 t_board테이블에 저장된 가장 큰 글번호를 조회하는 SELECT문
			String query = "select max(articleNO) from t_board";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //조회된 최신글번호가 검색되었다면?
				return (rs.getInt(1) + 1); //새로운 글 추가시 가장 큰 글번호에 +1한 번호를 사용하기위해 리턴
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//자원해제
			ResourceClose();
		}
		
		return 0;		
	}

	//DB에 새글을 추가 시키는 메소드
	public int insertArticle(ArticleVO articleVO) {
		
		//DB에 insert시킬 새로운 글번호를 구해오기 위해 메소드 호출!
		int articleNO = getNewArticleNo();
		
		try {
			con = dataSource.getConnection();
			
			int parentNO = articleVO.getParentNO();
			String title = articleVO.getTitle(); 
			String content = articleVO.getContent();
			String id = articleVO.getId();
			String imageFileName = articleVO.getImageFileName();
			
			String query = "insert into t_board(articleNO, parentNO, title, content, imageFileName, id)"
						 + "values(?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ResourceClose();
		}		
		return articleNO; //추가한 새글 번호를 BoardService로 반환
	}

	
	//매개변수로 전달받은 글번호를 이용해 글 정보를 조회해서 ArticleVO객체의 변수에 저장후 반환하는 메소드
	public ArticleVO selectArticle(int articleNO) {
		
		//조회된 글 하나의 정보(레코드)를 저장할 ArticleVO객체 생성
		ArticleVO articleVO = new ArticleVO();
		
		try {
			//1. DB 연결
			con = dataSource.getConnection();
			
			//2. 글제목을 눌렀을때 그 글의 글번호를 이용해 하나의 글 정보 조회
			String query = "select articleNO, parentNO, title, content, imageFileName, id, writeDate"
						 + " from t_board where articleNO=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//ResultSet에 저장된 조회된 글 레코드 정보를 꺼내서 ArticleVO객체의 각 변수에 저장
				articleVO.setArticleNO(rs.getInt("articleNO"));
				articleVO.setParentNO(rs.getInt("parentNO"));
				articleVO.setTitle(rs.getString("title"));
				articleVO.setContent(rs.getString("content"));
				articleVO.setImageFileName(rs.getString("imageFileName"));
				articleVO.setId(rs.getString("id"));
				articleVO.setWriteDate(rs.getDate("writeDate"));				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose();
		}
			
		return articleVO; //BoardService로 리턴
	}

	//매개변수로 전달된 수정할 데이터에 대해 
	//이미지 파일을 수정하는 경우와 이미지 파일을 수정하지않는 경우를 구분해
	//동적으로 UPDATE문을 생성하여 수정 데이터를 DB의 t_board테이블에 UPDATE반영합니다.
	public void updateArticle(ArticleVO articleVO) {
		
		int articleNO = articleVO.getArticleNO();
		String title = articleVO.getTitle();
		String content = articleVO.getContent();
		String imageFileName = articleVO.getImageFileName(); //이미지 수정 안한 경우와 한 경우
		
		try {
			
			con = dataSource.getConnection();
			
			String query = "update t_board set title=?, content=?";
			
			//수정한 이미지 파일이 있을 경우
			if(imageFileName != null && imageFileName.length() != 0) {				
				query += ", imageFileName=?";				
			}
			
			query += "where articleNO=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			
			//수정한 이미지 파일이 있을 경우
			if(imageFileName != null && imageFileName.length() != 0) {				
				pstmt.setString(3, imageFileName);		
				pstmt.setInt(4, articleNO);
			}else { //이미지 파일 없을 경우
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose();
		}
		
	} //updateArticle 메소드


	//글을 삭제하기전에 부모글번호와 연결된 자식답변글의 글번호를 함께 계층형 구조로 조회
	public List<Integer> selectRemoveArticles(int articleNO) {
	
		List<Integer> articleNOList = new ArrayList<Integer>();
		
		try {
			
			con = dataSource.getConnection();
			
			//삭제할 부모(원글)의 글번호를 이용해 그 글에 달린 답글들 모두 포함해서
			//계층적(트리구조)으로 글번호를 조회하는 SELECT문 작성
			String query = "SELECT articleNO FROM t_board "
						 + "START WITH articleNO=? "
						 + "CONNECT BY PRIOR articleNO = parentNO";
			/*
				설명1. START WITH articleNO=?
				  - 삭제의 시작 지점을 지정합니다.
				  - ? 에는 삭제하려는 글 번호가 들어갑니다.
				    예를 들어 사용자가 글번호 1번에 대한 글을 삭제하고 싶다면
				    ? 자리에 1이 들어갑니다.
				    
				    결론 : 삭제할 글번호 1인 글을 기준으로, 여기에 달린 모든 그 자식답글들의 articleNO(글번호)를
				          찾아(트리구조로 내려가며 조회해) 보자는 뜻입니다.
				          
				설명2. CONNECT BY PRIOR articleNO = parentNO
				  
				    결론 : 삭제하려는 부모 글의 글번호(예:1번)가 어떤 자식(답변)글의 parentNO(부모글번호)와 같을때,
				          그 부모-자식 관계를 따라 트리구조로 아래로 계속 뻗어나가며 글번호들을 조회하는 조건식   
				          
				예시로 이해해보기:
			 		 
							articleNo	parentNo  
							1				0
							2				1
							3				1
							4				2
							
							t_board 테이블에 글번호와 부모글번호가 이런 구조로 저장되어  있다고 하면,	            
			 
			 
			 				START WITH articleNo = 1
							→ 1번 글부터 시작합니다.
			 
			 				CONNECT BY PRIOR articleNo = parentNo
							→ 1 → 2 (2의 parentNo가 1이므로 연결됨)
							→ 1 → 3 (3의 parentNo가 1이므로 연결됨)
							→ 2 → 4 (4의 parentNo가 2이므로 연결됨)
			 
			 
			 	  실제 글번호 조회 결과 (articleNo 기준)
							articleNo
								1
								2
								4
								3
								
								※ 위 결과는 일반적으로 트리 순회 순서에 따라 나옵니다.
								→ 오라클은 기본적으로 Depth-First 방식으로 탐색하기 때문에
								→ 1 → 2 → 4 → 그리고 나서 1의 다른 자식인 3을 찾게(조회) 됩니다.               
			
			*/
			pstmt = con.prepareStatement(query);
			
			// ? -> 삭제할 글번호를 이용해 글번호를 조회하기위해 삭제할 글번호 1을 넣는다.
			pstmt.setInt(1, articleNO);
			
			//위 SQL문을 실행해서 조회된 결과를 가져오자
			rs = pstmt.executeQuery();
			/*			  
		 	  실제 글번호 조회 결과 (articleNo 기준)
			
			  articleNo
				1
				2
				4
				3
			 */		
			
			while(rs.next()) {
				
				//조회된 삭제할 글번호 1과 그에 달린 자식 답글들 모두 반복해서 꺼내 
				//ArrayList배열에 오토박싱 new Integer(1)이런식으로 해서 추가
				articleNOList.add(rs.getInt("articleNO"));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ResourceClose();
		}
		return articleNOList;
	}

	public void deleteArticle(int articleNO) {

		try {
			
			//[1] DB연결
			con = dataSource.getConnection();
			
			//[2] 오라클 계층형 쿼리를 이용해
			//삭제 대상글과 그에 달린 모든 자식 답글을 함께 삭제하는 DELETE SQL문 작성
			//-> 서브쿼리(SELETE.....)로 먼저 삭제할 모든 글 번호를 조회해오고,
			//   그 조회된 삭제할 글번호를 DELETE문의 WHRER조건절의 IN()에 넣어 한번에 삭제합니다.
			String query = "DELETE FROM t_board "
					   	 + "WHERE articleNO IN (SELECT articleNO " 
										   	 + "FROM t_board " //삭제할 글 번호 목록 조회
										   	 + "START WITH articleNO=? " // 삭제 시작 지점 : 사용자가 삭제 요청한 글번호
										   	 + "CONNECT BY PRIOR articleNO = parentNO)"; //부모-자식 관계를 따라가며 자식 글들 모두 찾기			
			/*
				START WITH articleNO=?
				CONNECT BY PRIOR articleNO = parentNO)
				오라클에서 제공해주는 두 문법은 같이 써야함~!
			*/
			
			 /*
	         * *****************************이 쿼리는 다음과 같은 구조로 동작합니다:
	         *
	         * 예를 들어 글 번호 1번을 삭제한다고 하면,
	         * - START WITH articleNO = 1 → 1번 글부터 시작
	         * - CONNECT BY PRIOR articleNO = parentNO → 자식답변 글의 parentNO가 부모 글의 articleNO와 같으면 연결 해서 트리구조로 부모 글번호->자식 답변글 글번호 순으로 내려가며 조회
	         *
	         * 결국, 1번 글과 그 하위에 연결된 2번, 3번, 4번 등의 답글들의 글번호들도 함께 조회되고,
	         * 조회된 부모글 글번호 부터 자식글 글번호를 모두 IN(1,2,3,4) 이런식으로 넣어 DELETE 문을 통해 특정 글(articleNO)과 그 글에 달린 모든 답글(자식 글들)을 함께 삭제 됨
	         */
			
			//[3] 작성한 SQL 쿼리 실행 준비
			pstmt = con.prepareStatement(query);
			
			//[4] 쿼리 안의 ? 부분에 삭제하고자 하는 글 번호를 넣습니다.
			pstmt.setInt(1, articleNO);
			
			//[5] DELETE쿼리를 실행합니다.
			//위 쿼리로 조회된 모든 글번호에 해당하는 게시글이 t_board테이블에서 삭제됩니다.
			pstmt.executeQuery();
		} catch (Exception e) {
			//[6]만약 오류가 발생하면 오류내용을 콘솔에 출력합니다.
			//예 : DB 연결 오류, 쿼리 문법 오류 등
			e.printStackTrace();
		}finally {
			//[7] 사용한 DB 자원 해제
			ResourceClose();
		}
		
	}
	
}









