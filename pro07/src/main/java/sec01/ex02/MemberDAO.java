package sec01.ex02;

import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

// 주제 : Statement를 PreparedStatement로 업그레이드

/*
	오라클 DBMS의 t_member테이블과 연결하여
	데이터베이스 작업(SELECT, INSERT, DELETE, UPDATE 등)
	하는 클래스
*/

public class MemberDAO {

	//순서1. import	java.sql.*;
	//		그리고 오라클 DBMS의 XE데이터베이스 내부에 만들어 놓은 t_member테이블과 연결할
	//		4가지 연결정보를 변수 또는 상수에 저장
	
	//ojdbc6.jar(JDBC드라이버)파일 내부에 만들어져있는 드라이버프로그램(OracleDriver.class)의 경로를
	//문자열 형태로 상수에 저장
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//오라클 DBMS서버의 IP주소, 데이터베이스명, 포트번호를 상수에 저장
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	//오라클 DBMS서버의 XE데이터베이스에 접속할 아이디를 상수에 저장
	private static final String USER = "scott";
	
	//오라클 DBMS서버의 XE데이터베이스에 scott 아이디로 접속할 비밀번호를 상수에 저장
	private static final String PWD = "tiger";
	
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
	
	//---------------------------------------------------------------------------------------
	
	private void connDB() {
		try {
			//순서2. OracleDriver.class(중간 프로그램)을
			//		톰캣서버에서 제공하는 JVM메모리에 로딩해줍니다.
			//		Class.forName("oracle.jdbc.driver.OracleDriver");를 이용하여 
			//		OracleDriver.class 클래스 자체를 JVM이 차지하고 있는 메모리 중에서
			//		DriverManager 클래스의 정적변수에 저장시킨다.
			//요약 : 드라이버 로딩
		
	//참고.	
	//Class.forName() 메서드의 역할
	// - Class.forName("클래스 이름")은 해당 클래스(문자열로 주어진 클래스)를 메모리에 로드하는 역할을 함.
	// - 즉, 자바 가상 머신(JVM)이 "DRIVER" 변수에 저장된 클래스(예: "oracle.jdbc.driver.OracleDriver")를 찾아서 로드함.
	// - 이렇게 하면, 해당 클래스 내부의 static 블록이 실행되면서 JDBC 드라이버가 등록됨.

	//JDBC 드라이버가 등록되는 과정
	// - "oracle.jdbc.driver.OracleDriver" 클래스 내부에는 static 블록이 있음.
	// - static 블록이 실행되면서 `DriverManager.registerDriver(new OracleDriver());` 코드가 실행됨.
	// - 이 과정에서 JDBC 드라이버가 DriverManager에 자동 등록됨.
	// - 이후, DriverManager.getConnection(URL, USER, PWD) 메서드를 사용하여 DB에 연결할 수 있게 됨.
			
			Class.forName(DRIVER);
			//순서3. JVM에 로딩된 (DriverManager클래스의 정적변수에 저장된)
			//		OracleDriver객체를 통하여 DBMS의 XE데이터베이스 내부의 t_member테이블과
			//		연결을 맺은 연결정보를 가지고 있는 T4CConnerction객체 얻기
			//요약 : DB와의 연결을 맺은 정보를 가지고 있는 T4CConection객체 얻기
			con = DriverManager.getConnection(URL, USER, PWD);
									
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}		
		
	} //connDB 메소드
	
	//오라클 DBMS서버 내부의 XE데이터베이스 내부에 만들어 놓은 t_member테이블의 모든 회원레코드들을
	//한번에 조회해와서 반환하는 메소드
	public ArrayList listMembers() {
		
		//t_mamber테이블에 저장된 모든 회원레코드 정보들을 조회해 와서
		//가변길이 배열의 index 위치에 임시로 저장할 배열공간인 ArrayList배열 생성
		ArrayList list = new ArrayList();
		
		try {
			//순서2. 오라클 JDBC드라이버 OracleDriver.class의 객체를 DriverManager클래스의 변수에 등록
			//요약 : 오라클 드라이버 로딩
			//순서3. 데이터베이스와 연결은 정보를 관리하는 T4CConnection객체 얻기
			//요약 : DB와의 연결
			connDB();
			
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
	}
	
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
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} //ResourceClose 메소드
	
} //class MemberDAO
