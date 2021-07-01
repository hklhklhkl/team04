<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<title>添加科目</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
	<div class="page_title">科目管理&nbsp; &gt; 添加科目</div>
	<form action="${pageContext.request.contextPath }/back/exam/examSubjectServlet?method=addSubject" method="post" >
	
	<div class="button_bar">
		 <input type="button" onclick='window.location.href="${pageContext.request.contextPath }/back/exam/examSubjectServlet"' value="返回">
		<input type="submit" value="保存">
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td><input type="text" name="subjectName" /><span class="red_star">*</span></td>
			<th>创建人</th>
			<td><input type="text" value="${user.userName }" readonly name="subjectAuthor"/></td>
		</tr>
	</table>
	</form>
</body>
</html>