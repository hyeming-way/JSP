package file;

//업로드(다운로드)한 파일의 원본이름, 실제이름, 다운로드횟수를 저장할 클래스
public class FileDTO { //VO역할을 하는 클래스

	//변수
	String fileName; //업로드(다운로드)한 파일의 원본이름 저장할 용도의 변수
	String fileRealName; //업로드(다운로드)한 파일의 실제 이름을 저장할 용도의 변수
	int downloadCount; //다운로드한 횟수 저장할 용도의 변수
		
	//생성자
	public FileDTO() { }

	public FileDTO(String fileName, String fileRealName, int downloadCount) {
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.downloadCount = downloadCount;
	}

	
	//getter,setter 메소드
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	
}
