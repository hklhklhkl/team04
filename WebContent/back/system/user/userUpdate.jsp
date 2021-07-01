<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑用户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back//css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		<script type="text/javascript">
		function tj(){
			document.getElementById("fm").submit();
		}
		</script>
	</head>
<body>
	<div class="page_title">用户管理&nbsp; &gt; 编辑用户</div>
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<button class="common_button" onclick="tj();">保存</button>
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/user/userServlet?method=updateUserInfo" enctype="multipart/form-data" method="post"> 		
	<table class="query_form_table">
		<input type="hidden" name="userId" value="${user1.userId }">
		<tr>
			<th>姓名</th>
			<td><input type="text" name="userNickname" value="${user1.userNickname }" /><span class="red_star">*</span></td>
			<th>账号</th>
			<td><input type="text" name="userName" value="${user1.userName }"  /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>性别</th>
			<td>
				<select name="userSex">
					<option <c:if test="${user1.userSex == '男' }">selected="selected"</c:if> value="男">男</option>
					<option <c:if test="${user1.userSex == '女' }">selected="selected"</c:if> value="女">女</option>
				</select>
				<span class="red_star">*</span>
			</td>
			<th>出生日期</th>
			<td>
			<input type="date" name="userBirth" value="${user1.userBirth }"  /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>头像</th>
			<td><input type="file" name="userUri"  value="${user1.userUri }" /><span class="red_star">*</span></td>
			<th>角色</th>
			<c:forEach items="${roleList }" var="role">
				<c:if test="${role.roleId == user.roleId }">${role.roleName }</c:if>
			</c:forEach>
			<td>
				<select name="roleId" id="roleId">
					<option value="">选择</option>
					<c:forEach items="${roleList }" var="role">
						<option value="${role.roleId }" 
						<c:if test="${role.roleId == user.roleId } ">
							selected='selected'
						</c:if> >
						${role.roleName }
						</option>						
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>论坛积分</th>
			<td><input type="text" value="${user1.userForumIntegral }" readonly /><span class="red_star">*</span></td>
			<th>考试学分</th>
			<td><input type="text" value="${user1.userExamIntegral }" readonly /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>创建时间</th>
			<td><input type="text" value="${user1.userCreateDate }" readonly /></td>
			<th>创建人</th>
			<c:forEach items="${userList }" var = "user2">
					<c:if test="${user2.userId==user1.userCreatorId }">
						<td><input type="text" value="${user2.userName }" readonly /></td>
					</c:if>
			</c:forEach>						
		</tr>
	</table>
	</form>
</body>
</html>