<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>여러가지 논리 연산자를 EL표현 언어 내부에 작성</h2>
	<%--
		EL표현언어 안에 작성하는 논리 연산자 문법
		
		1. && 연산자나 and 연산자는 논리곱 연산을 합니다.
		2. || 연산자나 or 연산자는 논리합 연산을 합니다.
		3. ! 연산자나 not 연산자는 반대의 결과를 출력하는 논리부정연산을 합니다.
		
	--%>
	 
	 
	 <h2>
	 ${ (10 == 10) && (20 == 20) } <br>
	 <!-- true	   &&   true        -->
	 <!--         true              -->
	 ${ (10 eq 10) and (20 eq 20) } <br><br>
	  
	  
	 ${ (10 == 10) || (20 != 30) } <br>
	 <!--   true   ||     true      -->
	 <!--         true              -->
	 ${ (10 eq 10) or (20 ne 30) } <br><br>
	 
	 
	 ${     !(20 != 10)    } <br>
	 <!--   !(  true  )       -->
	 <!--      false          -->
	 ${    not(20 ne 10)    } <br><br>
	 
	 
	 </h2>

</body>
</html>