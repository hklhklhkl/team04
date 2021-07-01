<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑角色</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/common.js"></script>
		<script type="text/javascript">
		function tj(){
			document.getElementById("fm").submit();
		}
		</script>
	</head>
<body>
	<div class="page_title">角色管理&nbsp; &gt; 编辑角色</div>
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<button class="common_button" onclick="tj();">保存</button>
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/role/roleServlet?method=updateRoleInfo" method="post">
	<table class="query_form_table">
		<input type="hidden" name="roleId" value="${role.roleId }">
		<tr>
			<th>角色名称</th>
			<td><input type="text" name="roleName" value="${role.roleName }" /><span class="red_star">*</span></td>
			<th>创建人</th>			
			<c:forEach items="${userList }" var="user">
				<c:if test="${role.roleCreatorId == user.userId }">
					<td><input type="text" value="${user.userName }" readonly /></td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<th>创建时间</th>
			<td><input type="text" value="${role.roleCreateDate }" readonly /></td>
			<th></th>
			<td></td>
		</tr>
	</table>
	</form>
</body>
</html>