<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>在线学习管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	body{
		border-bottom:solid 1px #666;
	}
	a{
		text-decoration: none;
		color: #000000;
	}
</style>
</head>

<body>

<table style="width:100%;">
<tr>
	<td ><img src="images/logo.gif" height="60px"></td>
	<td style="font-family:黑体;font-size:33px;font-weight:bold;">在线学习管理系统</td>	
	<td width="25%" align="right" style="font-size:12px;" valign="bottom">
	当前用户：
	<a href="userInfo.jsp" target="rightFrame">${user.userName }
	（<c:forEach items="${roleList }" var="role">
		<c:if test="${role.roleId == user.roleId }">${role.roleName }</c:if>
	</c:forEach>）
	</a>&nbsp;&nbsp; 	
	<a href="password.jsp" target="rightFrame">修改密码</a>&nbsp;&nbsp;&nbsp;
	<a href="login.jsp" target="_top">退出系统</a> </td>
</tr>
</table>
</body>
</html>
