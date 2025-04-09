package file;

import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//DB작업할 클래스
public class FileDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//DB연결을 맺은 Connection 객체를 얻어 저장하는 생성자
	public FileDAO() {
		
		try {
			
			//1. Driver.class 드라이버에 대한 경로를 문자열로 전달해
			//   동적으로 new Driver() 객체 생성 후 DriverManager클래스에 저장(등록)
			//   요약 : 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. DriverManager클래스의 정적메소드인 getConnection메소드 호출시
			//   접속할 DB 연결 정보를 전달해 DB와 연결을 맺어 관리하는 Connection 객체 얻기
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/file", "root", "1234");
		
		} catch (Exception e) {		
			
			e.printStackTrace();
			
		} 					
		
	}//생성자 끝
	
	//톰캣 서버에(upload 폴더) 실제로 업로드된 실제파일명과 원본 파일명을 FILE DB내부의 file 테이블에 INSERT
	public int upload(String fileName, String fileRealName) {
		
		String sql = "INSERT INTO FILE VALUES(?,?,0);";
		
		try {
			//PreparedStatement얻기
			pstmt = conn.prepareStatement(sql);
			
			// ? 대신 테이블에 추가할 원본파일명, 실제업로드한파일명으로 설정
			pstmt.setString(1, fileName); //서버에 업로드할 파일의 원본파일명
			pstmt.setString(2, fileRealName); //실제 서버경로(upload)에 업로드된 실제파일명
			
			return pstmt.executeUpdate(); //성공하면 1을 반환
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//DB작업한 자원해제
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} //finally

		return -1; //insert 실패시 -1 반환
	} //upload 메소드 끝
	
	
	//다운로드할 실제 파일명들을 DB의 테이블로부터 검색해서 가져와 각각 FileDTO객체에 
	//최종적으로 
	public ArrayList<FileDTO> AllSelect(){
		
		ArrayList<FileDTO> list = new ArrayList<FileDTO>();
		
		String sql = "SELECT * FROM FILE";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//ResultSet객체에 저장된 검색한 파일 정보 레코드 한쌍씩 꺼내어 FileDTO객체의 변수에 저장
				FileDTO filedto = new FileDTO(rs.getString("FILENAME"), 
											  rs.getString("FILEREALNAME"), 
											  rs.getInt("downloadCount"));	
				//ArrayList배열에 FileDTO객체 추가
				list.add(filedto);
				
			} //while 반복문 끝
			
		} catch (Exception e) {
			e.printStackTrace();		
		}finally {
			//DB작업한 자원해제
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return list; //fileDownLoadList.jsp 요청한 페이지로 리턴
	} //AllSelect 메소드
	
	
	//사용자가 파일을 다운로드 할떄 마다 다운로드할 횟수를 DB에 업데이트 하기 위한메소드
		public int hit(String fileRealName){//다운로드한 파일의 실제이름을 매개변수로 전달받기
			
			//사용자가 파일을 다운로드할때 마다 다운로드한 파일의 다운로드 횟수 1증가 시키는 SQL문
			String sql = "UPDATE FILE SET downloadCount = downloadCount + 1 "
					   + " WHERE fileRealName = ?";		
			//UPDATE문법
			//  UPDATE 테이블명 SET 수정할값이저장된_열명 = 수정할값, ...
			//	WHERE  조건열 = 조건값;
			try {
				pstmt = conn.prepareStatement(sql);
				//? 설정
				pstmt.setString(1, fileRealName);//실제 서버에 업로드되어 올라가있는 다운로드할 실제 파일명
				
				return pstmt.executeUpdate(); //파일 다운로드 횟수 1증가에 성공하면 1을 반반환			
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return -1; //파일 다운로드 횟수 1증가에 실패하면 -1을 반환
		}

}
