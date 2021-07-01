<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<title>编辑科目</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
<form action="${pageContext.request.contextPath }/back/exam/examSubjectServlet?method=updateSubjectName2&subjectId=${subject.subjectId}" method="post" >
	
	<div class="page_title">科目管理&nbsp; &gt; 编辑科目</div>
	<div class="button_bar">
		<input type="button" onclick='window.location.href="${pageContext.request.contextPath }/back/exam/examSubjectServlet"' value="返回">
		<input type="submit" value="保存">
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td><input type="text" value="${subject.subjectName }"  name="subjectName"/><span class="red_star">*</span></td>
			<th>创建人</th>
			<td><input type="text" value="${subject.subjectAuthor }" readonly /></td>
		</tr>
	</table>
	</form>
</body>
</html>