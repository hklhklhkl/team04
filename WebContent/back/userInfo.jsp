<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
	</head>
	<script type="text/javascript">
		function fm(){document.getElementById("fm1").submit();}
	</script>	
<body>
	<div class="page_title">用户信息</div>
	<div class="button_bar">
		<button class="common_button" onclick="fm()">保存</button>
	</div>
	<form id="fm1" action="${pageContext.request.contextPath}/back/sys/user/userServlet?method=updateSelfInfo" enctype="multipart/form-data" method="post">	
	<table class="query_form_table">
		<tr>
			<th>姓名</th>
			<td><input type="text" name="userNickname" value="${user.userNickname }" /><span class="red_star">*</span></td>
			<th>账号</th>
			<td><input type="text" name="userName" value="${user.userName }"  /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>性别</th>
			<td>
				<select name="userSex">
					<option <c:if test="${user.userSex == '男' }">selected="selected"</c:if> value="男">男</option>
					<option <c:if test="${user.userSex == '女' }">selected="selected"</c:if> value="女">女</option>
				</select>
				<span class="red_star">*</span>
			</td>
			<th>出生日期</th>
			<td><input type="date" name="userBirth" value="${user.userBirth }" value="${user.userBirth }" /><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>头像</th>
			<td><input type="file" name="userUri"  value="${user.userUri }" /><span class="red_star">*</span></td>
			<th>创建人</th>
			<td>
				<c:forEach items="${userList }" var = "user2">
					<c:if test="${user2.userId==user.userCreatorId }">
					<input type="text" name="userName" value="${user2.userName }" readonly>
					</c:if>
				</c:forEach>		
			</td>
		</tr>
		<tr>
			<th>创建时间</th>
			<td><input type="text" value="${user.userCreateDate }" readonly /></td>			
			<th></th>
			<td></td>
		</tr>
	</table>
	</form>
</body>
</html>