package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	/*
		파일업로드 기능을 처리하는 서블릿인 FileUpload클래스입니다.
		
		라이브러리 파일에서 제공하는 DiskFileItemFactory클래스를 이용해
		파일이 업로드 되는 경로 위치와 한번에 업로드 가능한 최대 파일의 크기를 설정합니다.
		
		그리고 ServletFileUpload클래스를 이용해 파일 업로드창에서 업로드된 파일과
		요청한 파라미터에 대한 정보를 가져와 파일업로드하고
		요청한 파라미터 값을 출력합니다.
	*/
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String encoding = "UTF-8"; //한글 문자 인코딩 설정 값 저장
		
		//1. 요청한 파라미터 문자 한글 처리
		request.setCharacterEncoding(encoding);
		
		//2. 업로드 파일 경로와 연결된 File 클래스의 객체메모리 생성
		File currentDirPath = new File("C:\\file_repo"); //업로드할 폴더 경로
		
		//2-1. 업로드할 파일 데이터를 임시로 저장할 객체 메모리 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//2-1-1. 파일업로드시 사용할 임시로 저장할 DiskFileItemFactory 객체 메모리 최대 크기를 1MB로 설정
		factory.setSizeThreshold(1024*1024*1); //10MB로 하려면 10을 곱하면 됨
		
		//2-1-2. 임시로 저장할 DiskFileItemFactory 객체에 파일 업로드시 설정한 1MB 크기를 넘을 경우!
		//       업로드될 file_repo폴더에 접근하기 위한 File 객체를 설정
		factory.setRepository(currentDirPath);
		
		//참고. DiskFileItemFactory클래스는 업로드할 파일의 크기가 설정한 임시메모리의 크기(1MB)를 넘기전까지는
		//     업로드한 파일 데이터를 임시메모리에 저장하고 설정한 크기를 넘길 경우
		//     업로드할 C:\\file_repo 폴더로 업로드해서 저장시키는 역할을 함
		
		//3. 파일 업로드할 임시메모리 객체의 주소를 생성자쪽으로 전달해 저장한 
		//   파일업로드를 실제 처리할 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			
			/*
			uploadForm.jsp 파일업로드 요청하는 디자인 페이지에서  
			첨부한 파일 2개와  입력한 파라미터3개의 정보들
			request객체에서 꺼내와서  각각의 DiskFileItem객체들에 저장한 후 
			각각의 DiskFileItem객체들을  ArrayList배열에 추가 하게 됩니다. 
			그후 ~ ArrayList배열 자체를 반환 해 줍니다.
			*/	
			
			List items = upload.parseRequest(request);
			
			//ArrayList배열에 저장된 DiskFileItem객체(요청한 아이템)의 갯수만큼 반복
			for(int i=0; i<items.size(); i++) {
				
				//ArrayList배열에 저장된 DiskFileItem객체를 하나씩 반복해서 얻는다
				FileItem fileitem = (FileItem)items.get(i);
				
				//얻은 DiskFileItem객체의 정보가 첨부한 파일 요청이 아니고
				//입력한 텍스트 요청 정보가 저장된 DiskFileItem객체일 경우?
				if(fileitem.isFormField()) {
					
					System.out.println(fileitem.getFieldName() + "=" + fileitem.getString(encoding));
					
				}else { //얻은 DiskFileItem객체의 정보가 첨부한 파일일 경우
					
					System.out.println("<input type='file'>의 name속성값 : " + fileitem.getFieldName());
					
					System.out.println("업로드 요청시 첨부한 파일명 : " + fileitem.getName());
					
					System.out.println("업로드 요청시 첨부한 파일크기 : " + fileitem.getSize() + "bytes");
					
					//업로드시 첨부한 파일의 크기가 0bytes보다 크다면?
					//(파일 업로드 요청했다면?)
					if(fileitem.getSize() > 0) {
						
						//업로드할 파일명을 얻어 파일명 뒤에서부터 \\문자열이 들어있는지 index 위치를 알려주는데
						//없으면 -1을 반환
						int idx = fileitem.getName().lastIndexOf("\\");
						System.out.println(idx);
						
						if(idx == -1) { //업로드할 파일명에 \\가 포함되어 있지 않다면?
							
							//    /문자열이 업로드할 파일명에 포함되어 있지 않으면? -1을 반환
							idx = fileitem.getName().lastIndexOf("/");
							System.out.println("업로드할 파일명에 /기호는 없다.");
							
						}
						
						//업로드할 파일명 얻기
						String fileName = fileitem.getName().substring(idx+1);
														//idx에 -1이 저장되어 있으니 이를 0으로 만들어줌(index가 0부터 시작하니까)
						
						//업로드할 파일 경로 + 파일명 전체 주소를 문자열로 만들어 접근한 File 객체 생성
						File uploadFile = new File(currentDirPath + "\\" + fileName);
												//"C:\\file_repo" + "\\" + "업로드할파일명"
						
						//실제 파일 업로드
						fileitem.write(uploadFile);
						
					}//바깥 if
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
