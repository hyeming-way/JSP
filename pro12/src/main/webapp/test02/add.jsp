<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    errorPage="addException.jsp"
    %>
    <%-- add.jsp에서 예외발생시 예외를 처리할 JSP페이지의 주소 경로를 설정하는 errorPage속성 --%>

<%
	//add.html에서 입력한 자연수(요청한값)를 request 객체메모리에서 얻어 변수에 저장
	int num = Integer.parseInt(request.getParameter("num"));

	int sum = 0; //1부터 입력받은 자연수까지 누적된 합을 저장할 변수
	
	for(int i=1; i<=num; i++){
		sum += i;
	}
%>   

<h2>합계 구하기</h2> 
<h1>1부터 <%=num%>까지의 합은 <%=sum%>입니다.</h1>    
    
    
    