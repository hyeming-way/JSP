package member;

//회원 관련 기능들을 기능별로 추상메소드를 정의해놓고 제공하는 인터페이스
public interface MemberDAOImpl {
	
	//1. member 테이블에 새 회원을 추가하는 기능의 추상메소드 정의
	public int insertMember(MemberBean memberbean);
	
	//2. member 테이블에 새 회원을 추가하기 전 
	//   가입할 아이디가 member 테이블에 저장되어 있는지 확인(중복체크)하는 추상메소드 정의
	public int idCheck(String id);
	
	//3. 로그인 처리시 입력한 아이디, 비밀번호가 DB의 member 테이블에 있는지 체크하는 기능의 추상메소드 정의
	public int useCheck(String id, String passwd);
	
}
