package sec01.ex01;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/*
	오라클 DBMS의 t_member테이블과 연결하여
	데이터베이스 작업(SELECT, INSERT, DELETE, UPDATE 등)
	하는 클래스
*/

public class MemberDAO {

	//위 4가지 연결 값을 이용해서 오라클 XE데이터베이스의 t_member테이블과 연결을 맺은 정보를 지니고 있는
	//T4CConnection객체를 저장할 참조변수 선언
	private Connection con;

	//오라클 XE데이터베이스의 t_member테이블과 연결 후 
	//우리 개발자가 만든 SQL문장을 오라클 DB의 t_member테이블에 전송하여 실행할 역할을 하는
	//OraclePreparedStatementWrapper 실행 객체를 얻어 저장할 참조변수 선언
	private PreparedStatement pstmt;
	
	//우리 개발자가 MemberDAO.java에 작성한 SQL문장 중 SELECT문으로 t_member테이블에 저장된 회원레코드를
	//조회해서 가져와 임시로 저장할 ResultSet객체메모리의 주소를 저장시킬 참조변수 선언
	private ResultSet rs;
	
	//DataSource 커넥션풀 객체의 주소를 저장할 참조변수 선언
	private DataSource dataSource;
	
	//기본생성자 : new MemberDAO(); 객체 생성시 호출되는 생성자로
	//생성자 내부에서 커넥션풀 역할을 하는 DataSource 객체를 얻어 위 dataSource참조변수에 저장
	//참고. context.xml에 작성한 <Resource name="jdbc/oracle" ........./> 불러오기
	public MemberDAO() {
		
		try {
			//1. JNDI 네이밍 서비스를 시작하고,
			//톰캣 서버에 등록된 <Resource/>태그의 자원에 접근할 수 있는 환경을 설정하는 객체 생성(DataSource자원을 얻을 환경준비)
			//InitialContext 객체를 생성하면 java 애플리케이션은 해당 객체를 통해 
			//톰캣서버 내부에 등록된 <Resource>들을 탐색할 수 있습니다.
			Context ctx = new InitialContext();
			
			//2. lookup 메소드는 특정 이름에 대응하는 객체를 찾는 역할을 합니다.
			//즉, ctx.lookup("java:/comp/env")는 
			//"java:/comp/env"경로는 JNDI에서 표준적으로 사용되는 경로로
			//주로 애플리케이션 환경설정(환경변수, 데이터베이스 연결정보 등)에 접근하는 기본경로입니다.
			//이 기본경로 아래에 커넥션풀(DataSource)등의 리소스 이름이 설정됩니다.
			Context envCtx = (Context)ctx.lookup("java:/comp/env");
			
			//3. 그런 후 다시 톰캣서버는 context.xml에 설정한
			//<Resource name="jdbc/oracle"..type"javax.sql.CataSource".../> 태그의
			//name속성값 "jdbc/oracle"(JNDI 기법을 사용하기위한 Key)를 이용해
			//톰캣서버가 DB와 미리 연결을 맺은 Connection 객체들을 보관하고 있는
			// DataSource커넥션풀 객체를 찾아 value로 반환해 줍니다.
			dataSource =  (DataSource)envCtx.lookup("jdbc/oracle");
					
		} catch (Exception e) {
			System.out.println("DataSource 커넥션풀 객체 얻기 실패 : " + e);
		}
			
	}
	
	
	//---------------------------------------------------------------------------------------
	
	//오라클 DBMS서버 내부의 XE데이터베이스 내부에 만들어 놓은 t_member테이블의 모든 회원레코드들을
	//한번에 조회해와서 반환하는 메소드
	public ArrayList listMembers() {
		
		//t_mamber테이블에 저장된 모든 회원레코드 정보들을 조회해 와서
		//가변길이 배열의 index 위치에 임시로 저장할 배열공간인 ArrayList배열 생성
		ArrayList list = new ArrayList();
		
		try {
			
			//DataSource(커넥션풀)공간에서
			//미리 DB의 테이블과 연결을 맺어 놓은 Connection 객체 하나 빌려오기
			con = dataSource.getConnection(); //DB와 MemberDAO.java와의 연결
			
			
			//순서5. SQL문장(SELECT문장) 작성
			//-> t_member테이블에 저장된 모든 회원레코드를 조회하는 SELECT문 작성
			String query = "SELECT * FROM t_member";
			
			//순서6. T4CConnection객체의 prepareStatement메소드 호출시
			//		매개변수로 미리 준비한 SELECT * FROM T_MEMBER SQL문장을 문자열로 전달하면
			//		OraclePreparedStatementWrapper 실행 객체메모리에 미리 담아
			//		OraclePreparedStatementWrapper 실행 객체메모리 자체를 반환
			pstmt = con.prepareStatement(query);
			
			//순서6-1. OraclePreparedStatementWrapper 실행 객체메모리에 
			//		  설정된 완성된 "SELECT * FROM t_member"을 t_member테이블에 전송하여 실행
			//		  실행 후 조회된 모든 회원레코드 정보를 테이블표 형식으로 OracleResultSetImpl 객체메모리에 담아 반환
			rs = pstmt.executeQuery();
			
			//순서7. 조회된 회원 레코드들이 ResultSet 임시 객체메모리에 표형태로 저장되어 있으면 계속 반복해서
			//회원레코드(행)단위의 조회된 열(컬럼)값들을 차례대로 얻어
			//MemberVO객체를 행단위로 생성하여 각 인스턴스 변수에 저장시킵니다.
			//마지막으로 생성된 MemberVO객체들을 ArrayList배열에 반복해서 추가시킵니다.
			while( rs.next() ) {
				
				//회원 레코드(행)단위의 조회된 열 값들을 차례대로 얻어 변수에 저장
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				Date joinDate = rs.getDate("JOINDATE");
				
				//MemberVO객체를 행단위로 생성하여 각 인스턴스 변수에 저장시킵니다.
				MemberVO vo = new MemberVO();		
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				//마지막으로 생성된 MemberVO객체들을 ArrayList배열에 반복해서 추가시킵니다.
				list.add(vo);
				
			}//while 끝
			
			//ArrayList가변길이 배열 모습
			//[ MemberVO,  MemberVO,  MemberVO ]
			//    0          1            2       index		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//순서9. DB작업 관련 객체 메모리를 다 사용 후 필요없으면 메모리 낭비이므로 자원해제
			ResourceClose();	
		}
		
		return list; //ArrayList배열 자체를 MemberServlet으로 반환
		
	} //listMembers 메소드
	
	
	/*
	  memberForm.html에서 입력한 가입할 회원내용들을
	  MemberVO객체를 생성해서 각 인스턴스변수에 저장한뒤~~~~~~~~
	  addMember메소드 호출시~~ 매개변수 vo로 전달받아 INSERT문장을 만들고
	   만든 INSERT문장을 DB의 t_member테이블에 전송해서 새회원을 추가시키는 기능의 메소드
	*/	
	public int addMember(MemberVO vo) {
		
		//회원 추가(insert)에 성공하면 1을 저장하고 실패하면 0을 저장시킬 result 변수 선언
		int result = 0;
		
		try {
			//1. 커넥션풀(DataSource)에서 미리 DB와 연결해놓은 Connection 객체 빌려오기
			//요약 : MemberDAO와 DBMS와의 연결
			con = dataSource.getConnection();
			
			//2. t_member테이블에 INSERT문장으로 추가할 값들이 저장될 값들은
			//매개변수 vo로 전달받는 MemberVO객체의 각 변수에 저장되어있으므로
			//MemberVO의 getter메소드들을 호출하여 얻자
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String email = vo.getEmail();
			
			//3. insert (SQL문) 만들기
			String query = "insert into t_member(id, pwd, name, email)" + 
										  "values(?,  ?,    ?,    ?)";
			
			//4. query 변수에 저장된 ? 들을 포함한 전체 insert 문장 하나를 미리 로드한
			//OraclePreparedStatement 실행 객체 얻기
			pstmt = con.prepareStatement(query);
			
			//4-1. OraclePreparedStatementWrapper 실행 객체에 위 입력된 가입할 값들을
			//? 기호 대신 설정합니다.
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			/*
	     	참고. 회원가입(회원추가)날짜 정보가 저장되는 joinDate열의 값은 t_member테이블 만들때
	     	     default설정을 통해 sysdate로 값을 적어 놓았기때문에
	     	     따로 insert구문에 작성하지 않으면 현재 컴퓨터의 날짜정보가 sysdate예약어에 의해
	     	     자동으로 같이 insert됩니다.
	     	*/
			//5. OraclePreparedStatementWrapper실행객체에 설정된 전체 insert문장을
	     	//   DB의 t_member테이블에 전송해 실행!
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO의 addMember 메소드 내부에서 insert문 실행 오류 : " + e);
			e.printStackTrace();
		} finally { //자원해제
			ResourceClose();
		}	
		
		return result;
		
	} //addMember 메소드
	
	//삭제 <a>링크를 클릭했을 때 서블릿으로 전송할 삭제할 회원 아이디를
	//다시 delMember메소드의 매개변수로 전달받아서
	//DELETE문장을 완성 후 DB의 t_member테이블에 저장된 회원 한 사람의 정보를 삭제시키는 기능의 메소드
	public void delMember(String id) {
		
		try {
			//순서1. 커넥션풀에서 커넥션객체 하나 얻기
			//요약 : DB와 MemberDAO.class와 연결
			con = dataSource.getConnection();
			
			//순서2. 위 String id 매개변수로 전달받은 삭제할 
			//회원 아이디와 일치하는 회원 레코드(행) 삭제하는 DELETE 구문 작성
			String query = "DELETE FROM T_MEMBER WHERE ID=?";
			
			//순서3. query변수에 저장된 전체 DELETE문자열을 미리 로드한
			//OraclePreparedStatementWrapper 실행 객체 메모리 얻기
			pstmt = con.prepareStatement(query);
			
			
			//순서3-1. OraclePreparedStatementWrapper 실행 객체메모리에 미리 로드된
			//DELETE 문자열 중에서 ? 기호 대신 매개변수로 전달받은 삭제할 회원아이디로 설정
			pstmt.setString(1, id);
			
			//순서4. OraclePreparedStatementWrapper 실행 객체에 설정된
			//DELETE구문을 DB의 t_member테이블에 전송해 실행!
			pstmt.executeUpdate();
			//참고. executeUpdate(); <-- INSERT, UPDATE, DELETE 구문 실행시 사용
			//						<-- SQL문 실행시 성공하면 성공한 레코드 갯수 1반환, 실패하면 0반환
			//	   excuteQuery();   <-- SELECT 구문 실행시 사용
			//						<-- SQL문 실행시 조회된 결과 데이터들을 ResultSet임시메모리객체에 담아 반환
			
		} catch (Exception e) {
			System.out.println("MemberDAO의 delMember메소드 내부에서 DELETE문 실행 오류 : " + e);
			e.printStackTrace();
		} finally {
			//자원해제
			ResourceClose();
		}
		
	}//delMember 메소드
	
	
	//DB작업 관련 객체메모리를 사용이 끝난 후 자원 해제하는 기능의 메소드
	public void ResourceClose() {
		
		try {
			//ReseltSet객체는  SQL문(SELECT)의 조회된 결과를 임시로 저장하는 객체입니다.
			//이 객체를 사용하고 있다면 제거시키자
			if(rs != null) {
				rs.close();
			}			
			//PreparedStatement객체는 SQL 쿼리를 실행하는 객체입니다.
			//이 객체를 사용하고 있다면 제거시키자
			if( pstmt != null ) {
				pstmt.close();
			}			
			//Connection 객체는 데이터베이스와의 연결을 관리하는 객체로, 더이상 데이터베이스 연결할 필요가 없으면
			//이 객체를 사용하고 있다면 제거시키자
			if( con != null ) {
				con.close(); //DataSource커넥션풒ㄹ에 사용이 끝난 Connection 자원 해제
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} //ResourceClose 메소드

	//LoginServlet5 서블릿 페이지에서 호출하는 메소드로
	//입력한 아이디와 비밀번호가 저장된 MemberVO객체를 매개변수 memberVO로 받아서
	//t_member테이블에 있는지 없는지 판단을 위해 조회작업하는 메소드
	public boolean isExisted(MemberVO memberVO) {
		
		boolean result = false; //조회되면 true 저장, 조회되지않으면 false 저장
		
		//입력한 아이디, 비밀번호 얻기
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		
		try {
			//1. 커넥션풀에서 미리 DB와 연결해놓은 Connection 객체 빌려오기
			con = dataSource.getConnection(); //MemberDAO.class프로그램과 Oracle DBMS와 연결
			
			//2. 입력한 아이디 비밀번호를 갖는 회원정보를 조회하는데...
			//   조회된 회원 행의 갯수가 1이면 'true' 조회 결과가 나오게 하고
			//   조회된 회원 행의 갯수가 1이 아닌 0이면 'false'문자열로 조회 결과가 출력되게 하는 SQL문
			String query = "SELECT DECODE(COUNT(*), 1, 'true', 'false') AS result FROM T_MEMBER "
					+ "WHERE ID=? AND PWD=?";
			
			//3. query 변수에 저장된 전체 SELECT 문자열을 미리 로드한
			//OraclePreparedStatementWrapper 실행 객체 얻기
			pstmt = con.prepareStatement(query);
			
			//3-1. OraclePreparedStatementWrapper 실행객체에 위 입력된 가입할 값들을 ? 기호 대신 설정
			pstmt.setNString(1, id);
			pstmt.setNString(2, pwd);
			
			//4. OraclePreparedStatementWrapper 실행객체에 설정된 전체 SELECT문장을
			//DB의 t_member테이블에 전송해 실행 후 조회된 결과를 ResultSet에 담아
			//ResultSet 객체 자체를 반환
			rs = pstmt.executeQuery();
			
			//5. ResultSet의 커서(화살표)의 위치를 조회된 레코드 행으로 내려준다
			rs.next();
			
			//조회된 'true' 문자열을 true로 변환해서 result변수에 저장
			result = Boolean.parseBoolean(rs.getNString("result")); //'true' -> true
			
		} catch (Exception e) {
			System.out.println("MemberDAO의 isExisted메소드 내부에서 select문장 실행 오류 : " + e);
		} finally {
			//자원해제
			ResourceClose();
		}
		
		return result; //로그인 요청을 위해 입력한 아이디가 있으면 true, 없으면 false 반환
		
	}
	
} //class MemberDAO
