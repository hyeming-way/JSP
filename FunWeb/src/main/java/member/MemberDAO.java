package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DAO(데이터베이스와 연결하여 DB작업할 클래스)
public class MemberDAO implements MemberDAOImpl {
	
	//데이터베이스 작업관련 객체들을 저장할 변수들 선언
	DataSource ds; //커넥션풀 역할을 하는 DataSource객체의 주소를 저장할 변수
	
	Connection con; //커넥션풀에 미리 만들어놓고 DB와의 접속이 필요하면 빌려와 
					//사용할 DB접속정보를 지니고 있는 Connection 객체주소를 저장할 변수
	
	PreparedStatement pstmt; //개발자가 작성한 SQL문을 DB의 테이블에 전송해서 실행할 역할을 하는
							 //PreparedStatement 실행객체의 주소를 저장할 변수
	
	ResultSet rs; //DB의 테이블에 저장된 행들을 조회한 전체 데이터들을 임시로 얻어 저장할
				  //ResultSet객체의 주소를 저장할 변수
	
	//커넥션풀(DataSource) 및 커넥션(Connection)객체를 얻어
	//커넥션(Connection)객체 자체의 주소를 반환하는 메소드
	private Connection getConnection() throws Exception {
		
		//1. InitialContext객체 생성
		//생성하는 이유는 자바의 네이밍 서비스(JNDI)에서 이름과 실제 객체를 연결해주는 개념이
		//Context이며, InitialContext객체는 네이밍 서비스를 이용하기위한 시작점입니다.
		Context initCtx = new InitialContext();
		
		//2. "java:comp/env"라는 주소를 전달하여  Context객체를 얻었습니다.
		//"java:comp/env" 주소는 현재 웹 애플리케이션의 루트 디렉터리 라고 생각 하면됩니다.
		//즉! 현재 웹애플리케이션이 사용할수 있는 모든 자원은 
		//"java:comp/env"아래에 위치합니다.(<Context></Context/>이위치를 말합니다.)
		Context ctx = (Context)initCtx.lookup("java:comp/env");
		
		//3. "java:comp/env 경로 아래에 위치한  
		//   "jdbc/jspbeginner" Recource태그의  DataSource커넥션풀을 얻는다
		ds = (DataSource)ctx.lookup("jdbc/jspbeginner");
		
		//4. 마지막으로 커넥션풀(DataSouce)객체 메모리 에 저장된 
		//   Connection객체를 반환받아 사용
		con = ds.getConnection();
		
		return con;

	} //getConnection 메소드
	
	/*
	 AutoCloseable 객체란?
	- AutoCloseable은 자동으로 닫을 수 있는 객체를 의미하는 인터페이스입니다.
	 try-with-resources 구문에서 사용되며, 사용이 끝나면 자동으로 close() 메소드를 호출하여 자원을 해제합니다.


	✅ AutoCloseable을 구현한 대표적인 클래스
	 - 자바에서 AutoCloseable을 구현한 대표적인 클래스들은 외부 리소스를 사용하는 객체들입니다.
	   이런 객체들은 사용이 끝나면 반드시 close()로 자원을 해제해야 합니다.

		클래스									설명
		Connection							DB 연결 객체 (JDBC)
		PreparedStatement					SQL 실행 객체 (JDBC)
		ResultSet							SQL 조회 결과 객체 (JDBC)
		BufferedReader						파일을 읽는 객체 (I/O)
		FileInputStream						파일을 읽는 객체 (I/O)
		FileOutputStream					파일을 쓰는 객체 (I/O)
		
		즉, 파일, 네트워크, DB와 같이 외부 리소스를 사용하는 객체들은 AutoCloseable을 구현하고 있으며, 사용이 끝난 후 반드시 닫아줘야 합니다.
	*/	
	
	//전달 받은 여러개의 AutoCloseable객체(ResultSet, PreparedStetement, Connection 등)을 안전하게 닫는 메소드
	//resources 매개변수 - 닫아야 할 AutoCloseable 객체들(가변인자)
	private void close(AutoCloseable... resources) {
		//resources배열에 들어있는 모든 객체를 순회하며 하나씩 처리
		for(AutoCloseable r : resources) {
			if(r != null) { //객체가 null이 아닐때만(사용하고 있으면?) close()호출
				try {
					r.close(); //자원해제
				} catch (Exception e) {
					e.printStackTrace(); //닫는 과정에서 오류 발생시 콘솔에 오류메세지 출력
				}
			}
		}
	} //close 메소드

	
	//새 회원 한사람의 정보를 member 테이블에 insert 추가시키는 메소드
	//insert 추가에 성공하면 1반환, 실패하면 0반환
	@Override
	public int insertMember(MemberBean mb) {
		
		int result = 0; //1 또는 0을 저장해서 반환할 수 있도록 변수 선언
		
		try {
			//1. DB의 테이블과 연결
			//(커넥션풀에서 커넥션객체 빌려오기)
			con = getConnection();
			
			//2. SQL(INSERT문)만들기
			//-> 가입하기위해 입력한 값들을 member테이블에 추가할 INSERT문장
			//3. preparedStament 실행 객체에 만든 INSERT문장을 미리 저장해 놓고 얻기
			pstmt = con.prepareStatement("insert into member(id, passwd, name, reg_date, email, address, tel, mtel)"
										+ "values(? ,? ,? ,now() ,? ,? ,? ,?)");
			//4. ?에 대응되는 열에 추가할 값을 차례대로 PreparedStatement객체에 설정 
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getPasswd());
			pstmt.setString(3, mb.getName());
			pstmt.setString(4, mb.getEmail());
			pstmt.setString(5, mb.getAddress());
			pstmt.setString(6, mb.getTel());
			pstmt.setString(7, mb.getMtel());
			
			//5. 완성된 insert 전체 문장을 DB의 member 테이블에 전송해서 실행
			result = pstmt.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			//6. 자원해제 (ResultSet, PreparedStatement, Connection)
			close(rs, pstmt, con);
		}
		
		return result; //7. 회원가입에 성공하면 1을 반환 또는 실패하면 0을 joinPro.jsp로 반환
	}

	//회원가입을 위해 입력한 아이디를 매개변수로 받아
	//DB에 member테이블에 저장된 아이디 와 비교해서  
	//있으면 1을 반환
	//없으면 0을 반환 
	@Override
	public int idCheck(String id) {
		
		int check = 0; //1 또는 0 저장할 체크 변수
		String sql = "";
		
		try {
			//1. DB의 테이블과 연결
			//(커넥션풀에서 커넥션객체 빌려오기)
			con = getConnection();
			
			//2. SQL(SELECT)만들기
			sql = "SELECT * FROM MEMBER WHERE id=?";
			
			//3. preparedStatement실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			
			//3-1. ? 대신 들어갈 값 설정
			pstmt.setString(1, id); //가입시 입력한 아이디
			
			//4. SELECT문장을 조회한 결과를 하나의 표형식으로 ResultSet 객체에 담아 얻기
			rs = pstmt.executeQuery();
			
			//5. ResultSet 객체에 조회된 행 데이터가 있으면?
			if(rs.next()) {
				
				check = 1; //아이디 중복	
				
			}else { //ResultSet 객체에 조회된 행 데이터가 없으면?
				
				check = 0; //아이디 중복 아님(입력한 아이디로 가입 가능)
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//6. 자원해제
			if(rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace();}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace();}
			if(con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace();}
		}
		
		return check; //7. join_IDCheck.jsp로 1(아이디 중복) 또는 0(사용가능한 아이디) 반환
	}

	
	//로그인 처리시 사용하는 메소드
	//입력받은 아이디, 비밀번호와 DB에 있는 아이디, 비밀번호값 확인하여 로그인 처리
	@Override
	public int useCheck(String id, String passwd) { //<-- 입력받은 아이디, 비밀번호를 전달받은
		
		int check = -1;
		//check = 1 -> 아이디 맞음, 비밀번호 맞음
		//check = 0 -> 아이디 맞음, 비밀번호 틀림
		//check = -1 -> 아이디 틀림
		
		String sql = "";
		
		try {
			//1. DB의 테이블과 연결
			//(커넥션풀에서 커넥션객체 빌려오기)
			con = getConnection();
			
			//2. SQL(SELECT문)만들기
			//-> member 테이블의 id열에 저장된 값이 우리가 입력한 아이디와 일치한 행의 pwd열의 값 조회
			sql = "select passwd from member where id=?";
			
			//3. SQL문을 미리 저장해놓고 실행할 preparedStetement 실행 객체 얻기
			pstmt = con.prepareStatement(sql);
			
			//3-1. ? 설정
			pstmt.setString(1, id); //입력한 아이디로 설정
			
			//4. 위 완성된 select 전체 문장 DB의 테이블에 전송해서 실행한 조회 결과값을 ResultSet 임시메모리에 담아 반환
			rs = pstmt.executeQuery();
			
			//5. ResultSet 객체메모리 내부에서 커서의 위치를 한줄 내려 조회된 행이 존재하면?
			if(rs.next()) { //아이디가 존재하면?
				
				if(passwd.equals(rs.getString("passwd"))) { //비밀번호도 같으면?
					check = 1;
				} else { //비밀번호가 틀리면?
					check = 0;					
				}
				
			} else { //아이디가 존재하지않으면?
				check = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con); //자원해제
		}
		return check; //loginPro.jsp로 반환
		
	} //useCheck 메소드

}//MemberDAO 클래스

