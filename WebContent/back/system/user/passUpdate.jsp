<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>修改密码</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="${pageContext.request.contextPath}/back/text/javascript" src="js/common.js"></script>
		<script type="text/javascript">
			function save(){
				var p1 = document.getElementById("userPass").value;
				var p2 = document.getElementById("confirmPass").value;
				if(p1 != p2){
					alert("两次输入的密码不一致！！")
					confirmNewPassword.focus();
					return false;
				}
				document.getElementById("fm").submit();
				return true;
			}		
		</script>
	</head>
<body>
	<div class="page_title">修改密码</div>
	<div class="button_bar">
		<button class="common_button" onclick="save();">保存</button>
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/user/userServlet?method=updateUserPass" method="post">
	<table class="query_form_table">
		<tr>
			<input type="hidden" name="userId" value = "${user1.userId }">
			<th>新密码</th>
			<td><input name="userPass" id="userPass" type="text" /><span class="red_star">*</span></td>
			<th>确认新密码</th>
			<td><input name="confirmPass" id="confirmPass" type="text" /><span class="red_star">*</span></td>
		</tr>
	</table>
	</form>
</body>
</html>