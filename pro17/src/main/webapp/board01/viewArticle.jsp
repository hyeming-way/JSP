<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<% request.setCharacterEncoding("UTF-8"); %>

<%-- 컨텍스트 주소 얻어 저장 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		/* tr 태그 중 id가 tr_btn_modify인 요소를 선택합니다.
	     해당 요소는 "수정반영하기"와 "취소" 버튼이 들어 있는 행(tr)입니다.
	     기본적으로 이 행은 화면에 보이지 않도록 숨깁니다.
	     사용자가 "수정하기" 버튼을 클릭하면 자바스크립트를 통해 이 tr의 display를 "block"으로 변경하여 보이게 합니다. */
		#tr_btn_modify {
			display: none; /*화면에 보이지않도록 숨김*/
		}
	 /*
	   - #tr_btn_modify는 수정 버튼이 들어 있는 행
		 처음에는 display: none으로 보이지 않음
		 fn_enable() 함수 호출 시 보이도록 전환됨
	  */		
	</style>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	
		//게시글 목록화면을 BoardController로 요청하는 함수
		//매개변수 obj는 <form>태그 객체이며, 예를 들어 <form name="frmArticle"> 등이 전달됩니다.
		function backToList(obj) {
			//아래의 <form action=""> action주소 설정
			obj.action = "${contextPath}/board/listArticles.do";
			
			//<from>을 이용하여 모든 글 조회 요청합니다.
			obj.submit();
		}
	
		//사용자가 '수정하기' 버튼을 클릭했을때 호출되는 함수
		//이 함수는 제목, 내용, 이미지 입력 요소를 활성하고
		//'수정반영하기 / 취소' 버튼들을 보여주며,
		//기존의 '수정 / 삭제 / 리스트로 돌아가기 / 답글쓰기' 버튼은 숨깁니다.
		function fn_enable(obj){
			//글제목 입력하는 <input>선택해서 활성화(글제목 수정을 위해 입력 가능하게 만듬)
			document.getElementById("i_title").disabled = false;
			
			//글내용 입력하는 <textarea>선택해서 활성화(글내용 수정을 위해 입력 가능하게 만듬)
			document.getElementById("i_content").disabled = false;
			
			//이미지 업로드 <input type="file">선택해서 활성화
			document.getElementById("i_imageFileName").disabled = false;
			
			//숨겨져있던 수정 버튼 영역을 표시
			//<tr id="tr_btn_modify">가 화면에 보이도록 설정
			document.getElementById("tr_btn_modify").style.display = "block";
						
			//원래 표시되던 버튼 영역을 숨김
			//<tr_id="tr_btn">이 화면에서 사라짐
			document.getElementById("tr_btn").style.display = "none";			
		}

		// 사용자가 파일 업로드(input type="file")에서 이미지를 선택했을 때 호출되는 함수입니다.
		// 선택한 이미지 파일을 읽어서 화면에 미리보기를 표시하는 기능을 합니다.		
		function readURL(input){
			
			// input.files 배열이 존재하고, 그 안에 최소한 하나의 파일이 있는지 확인합니다.
			if(input.files && input.files[0]){
				
				// 파일을 읽기 위한 FileReader 객체 생성
				var reader = new FileReader();
				
				// 파일이 읽혀지면 실행될 콜백 함수 정의
				reader.onload = function(e){
					
					// 읽어들인 파일 데이터를 <img id="preview"> 요소의 src 속성에 설정
		            // 즉, 선택한 이미지가 화면에 미리보기로 보이도록 함
					$('#preview').attr('src', e.target.result).show();
				}
				// 첫 번째 파일을 읽어서 base64 형식의 URL로 변환 (이미지 데이터를 문자열로 읽음)
				reader.readAsDataURL(input.files[0]);
			}		
		}
		
		
		//글 수정 요청을 서버로 전송하는 함수입니다.
		function fn_modify_article(obj){ //documnet.frmArticle
			
			//<form>의 action속성을 "게시글 수정 요청을 하는 요청주소로 설정"합니다.
			//BoardController서블릿으로 요청할 주소 action에 설정
			obj.action = "${contextPath}/board/modArticle.do";
			
			//설정된 action 경로로 form을 이용하여 수정할 글정보를 BoardController로 전송요청합니다.
			obj.submit();
		}
		
		//삭제하기 버튼을 클릭하면 호출되는 함수이며 BoardController서블릿으로 /board/removeArticle.do로 삭제 요청하는 함수
		//url 매개변수 : 삭제요청할 URL주소 전달 받음
		//articleNO 매개변수 : 삭제시킬 글 번호 전달 받음
		function fn_remove_article(url, articleNO){
			
			//1. 동적으로 새로운 <form> DOM을 생성해서 변수에 저장
			var form = document.createElement("form"); //<form></form>
			
			//form변수에 저장된 <form>태그에 method속성에 "post"값,  action속성에 삭제요청할 주소를 설정
			//예)
			//<form method="post" action="${contextPath}/board/removeArticle.do">
			//</form> 			
			form.setAttribute("method", "post");
			form.setAttribute("action", url);
			
			//2. 자바스크립트를 이용해 동적으로 <input>태그를 생성한 후 name속성과 value속성을 삭제할 글번호를 설정합니다.
			//예)
		 	//<input  type="hidden" name="articleNO" value="삭제할글번호">			
			var articleNOInput = document.createElement("input");
			articleNOInput.setAttribute("type", "hidden");
			articleNOInput.setAttribute("name", "articleNO");
			articleNOInput.setAttribute("value", articleNO);
			
			//3. 동적으로 생성된 <input>태그를 동적으로 <form>태그 내부에 보내어 설정합니다.
		    //<form method="post" action="${contextPath}/board/removeArticle.do">
			  //<input  type="hidden" name="articleNO" value="삭제할글번호">
		    //</form>			
			form.appendChild(articleNOInput);
			
			//4. <form>태그 한쌍을 <body>태그 내부에 추가
		    //<body>
		     //<form method="post" action="${contextPath}/board/removeArticle.do">
			  	//<input  type="hidden" name="articleNO" value="삭제할글번호">
		     //</form>	       
		    //</body>			
			document.body.appendChild(form);
			
			//5. 그리고 <form>을 이용해 삭제 요청을 합니다.
			form.submit();
	
		}// fn_remove_article 메소드
		
		//답글쓰기버튼을 클릭했을때 호출되는 함수로
		//답글을 작성할 수 있는 화면 요청 주소와 부모글(주글) 글번호를 매개변수로 전달 받아
		//컨트롤러에 답글을 작성하는 화면 요청하는 함수
		function fn_reply_form(url, parentNO){
		
			var form = document.createElement("form");
			    form.setAttribute("method", "post");
			    form.setAttribute("action", url);
			    /*	 
		 		<form method="post" action="답글작성화면요청주소"></form>
		 		 */
			    
			var parentNOInput = document.createElement("input");
				parentNOInput.setAttribute("type", "hidden");
				parentNOInput.setAttribute("name", "parentNO");
				parentNOInput.setAttribute("value", parentNO);
				 /*	 
			 	<input type="hidden" name="parentNO" value="작성할답글의 주글번호">
				*/
			 
				 //<form>태그 내부에 자식태그로 <input>추가 				  
			form.appendChild(parentNOInput);
			 /*	 
			
		 	<form method="post" action="/pro17/board/replyForm.do">
		 
		 		<input type="hidden" name="parentNO" value="작성할답글의 주글번호">
		 
		 	</form>
		 	*/	 
		 	
		 	document.body.appendChild(form);
		 	
			form.submit();
		}
	
	</script>
</head>
<body>
	<form name="frmArticle" method="post" action="${contextPath}" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글번호</td>
				<td>
					<input type="text" value="${article.articleNO}" disabled> <!-- disabled : 수정못하게(회색) 값만 보여줌 -->
					<input type="hidden" name="articleNO" value="${article.articleNO}">
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">작성자아이디</td>
				<td>
					<input type="text" value="${article.id}" name="writer" disabled> 
				</td>
			</tr>			
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글제목</td>
				<td>
					<input type="text" value="${article.title}" name="title" id="i_title" disabled> 
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글내용</td>
				<td>
					<textarea rows="20" cols="60" name="content" id="i_content" disabled>${article.content}</textarea>
				</td>
			</tr>
		<%-- 글쓰기에서 글을 작성할때 첨부한 이미지 파일이 존재할 경우 이미지명을 조회한 경우 --%>			
		<c:if test="${not empty article.imageFileName && article.imageFileName != 'null'}">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">이미지</td>
				<td>
					<%-- 수정하기 전 조회해 온 원본이미지명을 수정 요청할때 BoardController로 전달하기 위해 작성함 --%>
					<input type="hidden" name="originalFileName" value="${article.imageFileName}">
				
					<%-- 조회된 이미지 파일이름이 존재하면 글번호와 이미지파일이름을 
						 FileDownloadController서블릿으로 요청 전송한 후 <img>태그 자체에 다운로드시켜 표시 --%>
					<img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" id="preview"><br>
				</td>
			</tr>
			<tr>
				<td><input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);"></td>
			</tr>
		</c:if>	
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933">등록일자</td>
				<td><input type="text" value="<fmt:formatDate value="${article.writeDate}"/>" disabled></td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center">
					<input type="button" value="수정반영하기" onclick="fn_modify_article(frmArticle)">	
					<input type="button" value="취소" onclick="backToList(frmArticle)">	
				</td>
			</tr>					
			<tr id="tr_btn">
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="fn_enable(this.form)">
					
					<%-- 삭제하기 버튼을 클릭하면 fn_remove_article함수 호출하면 삭제요청할 주소와 삭제할 글번호를 매개변수로 전달함 --%>
					<input type="button" value="삭제하기" onclick="fn_remove_article('${contextPath}/board/removeArticle.do',${article.articleNO});">
					
					<input type="button" value="리스트로 돌아가기" onclick="backToList(this.form)">
					
					<%--현재 보이고 있는 부모글의 상세화면에서 답글쓰기를 누르면 
					    답글을 작성할 수 있는 화면 요청 주소 /board/replyForm.do와
					    부모글번호 ${article.articleNO}로 전달해서 사용 --%>
					<input type="button" value="답글쓰기" onclick="fn_reply_form('${contextPath}/board/replyForm.do',${article.articleNO})">
				</td>
			</tr>
						
		</table>
	
	
	</form>
</body>
</html>