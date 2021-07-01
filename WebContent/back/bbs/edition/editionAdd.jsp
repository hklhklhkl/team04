<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加版块</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
	<div class="page_title">版块管理&nbsp; &gt; 添加版块</div>
	<form action="${pageContext.request.contextPath }/back/forum/block/blockServlet?method=addBlock" method="post">
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<input type="submit" value="保存" class="common_button">
	</div>
	<table class="query_form_table">
		<tr>
			<th>版块名称</th>
			<td><input type="text" name="blockName"/><span class="red_star">*</span></td>
			<th>版块简介</th>
			<td><input type="text" name="blockBiref"/></td>
		</tr>
		<tr>
			<th>版块图片</th>
			<td><input type="file" name="blockPhoto"/><span class="red_star">*</span></td>
			<th>版主</th>
			<td><input type="text" value="${block.userId }"  name="userId"/></td>
		</tr>
	</table>
	</form>
</body>
</html>