<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加角色</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		<script type="text/javascript">
			function save(){document.getElementById("fm").submit();}		
		</script>
	</head>
<body>
	<div class="page_title">角色管理&nbsp; &gt; 添加角色</div>
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<button class="common_button" onclick="save();">保存</button>
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/role/roleServlet?method=addRole" method="post">
	<table class="query_form_table">
		<tr>
			<th>角色名称</th>
			<td><input type="text" name="roleName" /><span class="red_star">*</span></td>
			<th>创建人</th>
			<td><input type="text" value="${user.userName }" readonly /></td>
		</tr>
		<tr>
			<th>创建时间</th>
			<td><input type="text" value="${newDate }" readonly /></td>
			<input type="hidden" name="userCreatorId" value = "${user.userId }"/>
			<th></th>
			<td></td>
		</tr>
	</table>
	</form>
</body>
</html>