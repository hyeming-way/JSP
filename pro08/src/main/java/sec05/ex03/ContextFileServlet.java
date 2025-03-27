package sec05.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//주제 : 
//getServletContext()메소드를 호출하여 ServletContext객체 메모리를 얻어 접근합니다.
//getResourceAsStream()메소드를 이용해 읽어들일 데이터가 저장된 파일 위치 경로를 지정한후 
//InputStream입력스트림 통로를 반환받아 파일에서 데이터를 바이트 단위로 읽어들여 
//웹브라우저로 응답 합니다.

//http://localhost:8090/pro08/cfile 입력하여 요청하면 요청을 받아 처리하는 서블릿 
@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//웹프로젝트의 모든 서버페이지(서블릿)이 공유할 자원이 저장된 ServletContext 공유 객체 얻기
		ServletContext context = this.getServletContext();
		
		//웹프로젝트 내부에 만들어 놓은 "/WEB-INF/bin/init.txt"파일의 데이터를
		//바이트 단위로 읽어들이기 위한 init.txt파일과 연결된 InputStream 입력스트림 통로 얻기
		InputStream is = context.getResourceAsStream("/WEB-INF/bin/init.txt");
		
		//파일의 내용을 버퍼메모리에 저장해두었다가 2바이트 단위로 읽어들이기위해
		//InputStream통로를 InputStreamReader로 업그레이드 시키고
		//BufferedReader통로로 업그레이드 시키자
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
		
		//init.txt파일에서 읽어온 메뉴 데이터 정보들을 저장할 변수선언
		String menu = null; //회원등록 회원조회 회원수정, 주문조회 주문수정 주문취소, 상품조회 상품등록 상품수정 상품삭제
		String menu_member = null; //회원등록 회원조회 회원수정
		String menu_order = null; //주문조회 주문수정 주문취소
		String menu_goods = null; //상품조회 상품등록 상품수정 상품삭제
		
		//파일의 전체 데이터를 끝까지 읽어들이면서 각 메뉴 정보를 반복해서 분리합니다
		//init.txt파일은 줄에 "회원등록 회원조회 회원수정, 주문조회 주문수정 주문취소, 상품조회 상품등록 상품수정 상품삭제"
		//같은 형식으로 저장되어 있습니다.
		while( (menu = bufferedReader.readLine()) != null) {
			
			//콤마 , 를 구분자 기호로 정하여 메뉴 항목전체를 문자열로 분리시킬 수 있도록 객체 생성
			StringTokenizer tokenizer = new StringTokenizer(menu, ",");
			
			menu_member = tokenizer.nextToken();
			menu_order = tokenizer.nextToken();
			menu_goods = tokenizer.nextToken();
		
		}
		
		out.print(menu_member + "<br>");
		out.print(menu_order + "<br>");
		out.print(menu_goods + "<br>");

	}

}
