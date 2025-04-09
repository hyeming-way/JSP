<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  

<% request.setCharacterEncoding("UTF-8"); %>  

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>새 글 쓰기 창</title>	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>	
	<script type="text/javascript">
	
		//아래 쪽 <input type="file">태그에서 이미지 파일 첨부시 이미지 미리보기 기능을 구현하는 함수
		function readURL(input) { //<- <input type="file">태그를 매개변수로 전달받는다.
			
			console.log(input);
			console.log(input.files);
			//참고
			//<input type="file">인 태그객체의 files메소드를 호출하면
			//FileList라는 배열이 생성되면서 FileList배열 내부의 0번째 인덱스 위치에
			//아래에서 선택한(업로드할) 파일 정보들을 key:value쌍으로 저장한 File객체가 저장되어 있음
			
			//FileList라는 배열이 존재하고, FileList라는 배열의 0번째 인덱스 위치에 아래에서 파일을 업로드하기 위해서
			//선택한 File객체가 저장되어 있다면?
			//요약 : 아래의 <input type="file">태그에서 업로드를 하기 위한 파일을 선택했다면?
			if(input.files && input.files[0]){
				
				//이미지 파일 데이터를 문자 단위로 읽어들일 스트림통로 생성
				var reader = new FileReader();
				
				//지정한 img태그에 첫번째 파일 input에 첨부한 파일에 대한 File객체를 읽어들입니다.
				reader.readAsDataURL(input.files[0]);
				
				//업로드하기위해 선택한 파일을 모두 읽어들였다면?
				reader.onload = function(ProgressEvent){
					
					//읽어들인 File객체의 정보는 매개변수로 넘어오는 ProgressEvent 객체내부의?
					//target속성에 대응되는 객체(JSON객체 데이터 형식)로 저장되어있다.
					console.log(ProgressEvent);
						
					//id속성값이 preview인 <img>태그를 선택해
					//attr메소드를 이용해 파일 첨부시 미리보기 이미지를 나타내기위해
					//<img>태그의 src속성값에 new FileReader()객체를 이용하여 읽어들인 File 객체정보를 지정하여
					//추가함으로써 이미지 파일의 미리보기 기능이 가능한 것 입니다.
					//-> 읽은 파일을 <img>태그의 src속성에 경로를 설정하고, display:none;으로 인한 숨겨져있는
					//	 <img>태그도 보여지게 show()메소드 호출!
					$('#preview').attr('src', ProgressEvent.target.result).show();
						
				} 
			}
				
		}
		
		//글목록을 눌렀을때 호출되는 함수로 BoardController에 모든글 목록 조회 요청하는 함수
		function backToList(formObj){
			
			//JSP에서 전달받은 contextPath를 자바스크립트에서도 사용할 수 있도록 설정
			formObj.action = contextPath + "/board/listArticles.do";
			
			//모든 글 조회 요청 <form action="/pro17/board/listArticles.do">전송
			formObj.submit();
			
		}
		
		//자바스크립트에서 사용할 contextPath 전역변수 선언 (var는 호이스팅(위에서 끌어다 쓸수 있음) 가능!)
		var contextPath = "${contextPath}";
	
	</script>
</head>
<body>
	<h1 style="text-align: center;">새 글 쓰기</h1>
	
	<form name="articleForm" method="post" action="${contextPath}/board/addArticle.do" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td align="right">글제목 : </td>
				<td colspan="2"><input type="text" size="67" maxlength="500" name="title"></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>글내용 : </td>
				<td colspan="2"><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea></td>
			</tr>
			<tr>
				<td align="right">이미지파일 첨부 : </td>
				<!-- 첨부할 다른 이미지 파일을 선택해서 변화가 일어나면 readURL함수 호출시 변화가 일어난 input 태그 전달 -->
				<td><input type="file" name="imageFileName" onchange="readURL(this);"></td>
				<!-- 미리보기 이미지 표시(초기상태를 img 태그 숨기기) -->
				<td><img id="preview" width="200" height="200" style="display: none;"></td>
			</tr>
			<!-- 글쓰기 및 글목록 보기 버튼 -->
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="submit" value="글쓰기" />
					<input type="button" value="글목록" onclick="backToList(this.form)"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>