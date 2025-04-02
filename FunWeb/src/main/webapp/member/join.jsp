<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
 
 	<script type="text/javascript">
 		
 		//아이디 중복확인 버튼을 클릭하면 호출되는 함수로
 		//1. 아이디를 입력하지 않았으면 아이디를 입력할 수 있는 <input>선택해서
 		//   강제로 포커스 설정
 		//2. 아이디를 입력했다면, 입력한 아이디를 변수에 저장시키고
 		//	 새로운 윈도우창(팝업창)을 띄워 입력한 아이디를 팝업창으로 전달
 		function winopen(){
 			
 			if(document.fr.id.value == ""){ //아이디를 입력하지 않았으면? 					
 				
 				window.alert("아이디를 입력해주세요.");
 				document.fr.id.focus(); //아이디를 입력할 수 있는 <input>태그에 강제로 포커스 설정
 				return;
 
 			} 
 			//아이디를 입력했다면 입력한 아이디를 변수에 저장
 			let fid = document.fr.id.value;
 			
 			//새로운 팝업창을 띄우자
 			window.open("join_IDCheck.jsp?userid="+ fid,"팝업창제목","width=400, height=200");
 		
 		} //winopen(){}
 		
 	</script>
 
 
 
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문메인이미지 -->
		<div id="sub_img_member"></div>
		<!-- 본문메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Join us</a></li>
				<li><a href="#">Privacy policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 본문내용 -->
		<article>
			<h1>회원가입 작성</h1>
			<form action="joinPro.jsp" Method="POST" name="fr" id="join">
				<fieldset>
					<legend>Basic Info</legend>
					<label>User ID</label> <input type="text" name="id" class="id">
					<input type="button" value="아이디중복확인" class="dup" onclick="winopen();"><br>
					<label>Password</label> <input type="password" name="passwd"><br>
					<label>Retype Password</label> <input type="password" name="passwd2"><br>
					<label>Name</label> <input type="text" name="name"><br>
					<label>E-Mail</label> <input type="email" name="email"><br>
					<label>Retype E-Mail</label> <input type="email" name="email2"><br>
				</fieldset>

				<fieldset>
					<legend>Optional</legend>
					<label>Address</label> <input type="text" name="address"><br>
					<label>집전화번호</label> <input type="text" name="tel"><br>
					<label>휴대폰전화번호</label> <input type="text" name="mtel"><br>
				</fieldset>
				<div class="clear"></div>
				<div id="buttons">
					<input type="submit" value="가입하기" class="submit"> 
					<input type="reset" value="취소" class="cancel">
				</div>
			</form>
		</article>
		<!-- 본문내용 -->
		<!-- 본문들어가는 곳 -->

		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp"/>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>
    
    
    