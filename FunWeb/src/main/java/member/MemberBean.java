package member;

import java.sql.Timestamp;

//VO역할을 하는 클래스
//즉! 가입하는 한사람의 회원정보를 임시로 변수에 저장시킬수도 있고,
//DB의 member 테이블에 저장된 한사람의 정보를 조회 후 저장할 변수를 가지고 있는 클래스
public class MemberBean {
	
	//변수
	private String id, passwd, name, gender, email, address, tel, mtel;
	private Timestamp reg_date;
	private int age;
	
	//생성자
	public MemberBean() {}

	//가입날짜를 제외한 모든 인스턴스변수값 초기화할 생성자
	public MemberBean(String id, String passwd, String name, 
					  String gender, String email, String address, String tel,
					  String mtel, int age) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.tel = tel;
		this.mtel = mtel;
		this.age = age;
	}

	//getter, setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMtel() {
		return mtel;
	}

	public void setMtel(String mtel) {
		this.mtel = mtel;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
