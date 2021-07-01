<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>编辑版块</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
	<div class="page_title">版块管理&nbsp; &gt; 编辑版块</div>
	<form action="${pageContext.request.contextPath }/back/forum/block/blockServlet?method=updateBlock" enctype="multipart/form-data" method="post">
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<input type="submit" value="保存" class="common_button">
	</div>
	<table class="query_form_table">
		<tr>
			<th>版块名称</th>
			<td><input type="text" value="${block.blockName }" name="blockName"/><span class="red_star">*</span></td>
			<th>版块简介</th>
			<td><input type="text" value="${block.blockBiref }" name="blockBiref"/></td>
		</tr>
		<tr>
			<th>版块图片</th>
			<td><input type="file" value="${block.blockPhoto }" name="blockPhoto"/><span class="red_star">*</span></td>
			<th>版主</th>
			<td><input type="text" value="${block.userId }" name="userId"/></td>
			
		</tr>
		
	</table>
	<input type="hidden" value="${block.blockId }" name="blockId" readonly/>
	</form>
</body>
</html>