<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
   
<%
	//클라이언트(웹브라우저)에서 result.jsp로 전송된 다운로드할 파일명 데이터의
	//문자 인코딩을 UTF-8로 설정
	request.setCharacterEncoding("UTF-8");
	
	//컨텍스트 주소 얻기 (/pro15)
	String contextPath = request.getContextPath();
			
	//result.jsp에서 요청한 다운로드할 파일명을 request 객체에서 가져옵니다.
	//다운할 파일명에 한글이 포함될때 특수문자나 한글이 깨질 수 있기때문에, URLEncoder를 사용해
	//UTF-8방식으로 인코딩한 문자열을 반환받아 저장
	String file1 = URLEncoder.encode(request.getParameter("param1"));
	
	//두번째 다운로드할 파일명도 위와 동일한 방식으로 request에서 가져와 인코딩해서 얻자
	String file2 = URLEncoder.encode(request.getParameter("param2"));
%>   

	<!-- 첫번째 파일을 다운로드 요청할 수 있는 <a>링크를 생성 -->
	파일 내려 받기 1 : <br>
	<a href="<%=contextPath%>/download.do?fileName=<%=file1%>">파일 다운로드 요청</a><br>
   
   	<!-- 두번째 파일을 다운로드 요청할 수 있는 <a>링크를 생성 -->
	파일 내려 받기 2 : <br>
	<a href="<%=contextPath%>/download.do?fileName=<%=file2%>">파일 다운로드 요청</a><br>
   
   
   