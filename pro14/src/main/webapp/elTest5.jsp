<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- useBean 액션태그를 이용해 MemberVO클래스의 객체(빈) 생성 --%>    
<jsp:useBean id="m1" class="sec01.ex01.MemberVO" scope="page"/>

<%-- setProperty액션태그를 이용해 MemberVO객체의 setName메소드 호출시
	 매개변수로 "이순신" 문자열전달해 name 인스턴스 변수에 저장 --%>
<jsp:setProperty name="m1" property="name" value="이순신"/>

<%-- useBean액션태그를 이용해 java.util패키지에서 제공해주는
	 ArrayList클래스에 대한 배열 객체 생성 --%>
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page"/>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	empty 연산자 이용하여 EL표현언어 영역에 출력
	
	<h2>
		<%--
			MemberVO객체의 모든 인스턴스 변수에 값이 저장되어 있지 않느냐?라고 물어봅니다.
			값이 저장되어 있지 않으면 true
			하나라도 값이 저장되어있으면 false를 반환
			
			참고. m1참조변수가 가리키는 MemberVO 객체 내부의
			name 인스턴스변수에는 "이순신"값이 저장되어있으므로 false 반환
		--%>
		${empty m1} <br> <%-- false --%>
		
		<%-- MemberVO 객체의 모든 변수에 값이 하나라도 저장되어있느냐? 라고 물어볼수도 있다. --%>
		${not empty m1} <br> <%-- true --%>
		
		<%-- ArrayList배열에 값이 저장되어 있지 않느냐?(비어있느냐? 없느냐?)라고 물어볼 때 --%>
		${empty m2} <br> <%-- true --%>
		
		<%-- 위 ArrayList배열에 값이 하나라도 저장되어있느냐? --%>
		${not empty m2} <br> <%-- false --%>
		
		<br>
		
		<%-- 뒤 문자열 값이 없느냐? --%>
		${empty "hello"} <br> <%-- false --%>
		
		<%-- empty 연산자 뒤에 null을 넣으면 무조건 true를 반환 --%>
		${empty null} <br> <%-- true --%>
		
		<%-- empty 연산자 뒤에 빈공백문자열""을 넣으면 무조건 true를 반환 --%>
		${empty ""} <br> <%-- true --%>
		

	</h2>

</body>
</html>