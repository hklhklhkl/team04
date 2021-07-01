<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加用户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		<script type="text/javascript">
			function save(){
				var p1 = document.getElementById("userPass").value;
				var p2 = document.getElementById("confirmPass").value;
				if(p1 != p2){
					alert("两次输入的密码不一致！！")
					confirmNewPassword.focus();
					return false;
				}
				document.getElementById("fm2").submit();
				return true;
			}		
		</script>
	</head>
<body>
	<div class="page_title">用户管理&nbsp; &gt; 添加用户</div>
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<button class="common_button" onclick="save()">保存</button>
	</div>
	<form id="fm2" action="${pageContext.request.contextPath}/back/sys/user/userServlet?method=addUser" enctype="multipart/form-data" method="post">
	<table class="query_form_table">
		<tr>
			<th>姓名</th>
			<td><input type="text" name="userNickname" /><span class="red_star">*</span></td>
			<th>账号</th>
			<td><input type="text" name="userName" /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>密码</th>
			<td><input type="password" name="userPass" id="userPass" /><span class="red_star">*</span></td>
			<th>确认密码</th>
			<td><input type="password" name="confirmPass" id="confirmPass" /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>性别</th>
			<td>
				<select name="userSex">
					<option value="0">请选择</option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
				<span class="red_star">*</span>
			</td>
			<th>出生日期</th>
			<td><input type="date" name="userBirth" /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>头像</th>
			<td><input type="file" name="userUri" /><span class="red_star">*</span></td>
			<th>角色</th>
			<td>
				<select name="roleId" id="roleId">
					<option value="">选择</option>
					<c:forEach items="${roleList }" var="role">
						<option value="${role.roleId }" 
						<c:if test="${page.entity.roleId == role.roleId }">
							selected='selected'
						</c:if> >
						${role.roleName }
						</option>						
					</c:forEach>
				</select>
				<span class="red_star">*</span>
			</td>
		</tr>
		<tr>
			<th>论坛积分</th>
			<td><input type="text" value="0" readonly /><span class="red_star">*</span></td>
			<th>考试学分</th>
			<td><input type="text" value="0" readonly /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>创建时间</th>
			<td><input type="text" value="${newDate }" readonly /></td>
			<th>创建人</th>
			<td><input type="text" value="${user.userName }" readonly /></td>
			<input type="hidden" name="userCreatorId" value = "${user.userId }"/>
			</tr>
	</table>
	</form>
</body>
</html>