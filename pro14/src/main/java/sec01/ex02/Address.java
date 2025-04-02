package sec01.ex02;

//회원의 거주 도시 이름과 우편번호를 저장해놓고 제공하는 클래스
public class Address {

	private String city;
	private String zipcode; 
	
	public Address() { }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
		
}
