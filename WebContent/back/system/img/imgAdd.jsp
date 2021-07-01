<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加首页图片</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		<script type="text/javascript">
			function tj(){
				document.getElementById("fm").submit();
			}
		</script>
	</head>
<body>
	<div class="page_title">首页图片管理&nbsp; &gt; 添加首页图片</div>
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<button class="common_button" onclick="tj();">保存</button>
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/img/imgServlet?method=addImg" enctype="multipart/form-data" method="post">
	<table class="query_form_table">
		<tr>
			<th>图片</th>
			<td><input type="file" name="imgPic" /><span class="red_star">*</span></td>
			<th>位置</th>
			<td><input type="text" name="imgPlace" /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>创建人</th>
			<td><input type="text" value="${user.userName }" readonly /></td>
			<th>创建时间</th>
			<td><input type="text"  value="${newDate }" readonly /></td>
			<input type="hidden" name="imgCreatorId" value = "${user.userId }"/>
		</tr>
	</table>
	</form>
</body>
</html>