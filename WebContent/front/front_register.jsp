<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#eyes {
	width: 18px;
	position: absolute;
	top: 63px;
	left: 110px;
}

a {
	position: relative;
	width: 300px;
	height: 50px;
	margin: 100px auto;
}

body {
	background-image: url("/team04/front_images/blue-snow.png");
}

#userDate {
	width: 200px;
	height: 200px;
	line-height: 200px;
	text-align: center;
}
</style>
<script>
	window.onload = function() {
		setCalendar();//显示日历
	}
	function setCalendar() {
		// 调用显示日历的方法."calendar"
		// calendar为要显示的位置的ID
		// 这里对应是div的ID
		WdatePicker({eCont:"body_three_left1"});
	}
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/front_css/index.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/front_js/index.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/front_js/createCode.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/front_js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/front_js/jquery-2.1.1.min.js"></script>
</head>
<body>
	<center>
		<form
			action="${pageContext.request.contextPath }/front/user/frontUserServlet?method=add"
			method="post">
			<table border="1">
				<tr>
					<td colspan="3">欢迎来到注册界面！！！</td>
				</tr>
				<tr>
					<td>账号：</td>
					<td colspan="2"><input type="text" id="userName"
						name="userName" placeholder="请输入用户名" onfocus="check6()"></td>
				</tr>
				<tr>
					<td colspan="3" class="sign1_1" id="sign1_1">用户名不能为空</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input id="pwd" type="password" name="userPass"
						placeholder="请输入密码" onfocus="check7()"></td>
					<a href="javascript:;" onclick="tick()"> <img
						src="${pageContext.request.contextPath }/front_images/closeEyes.png"
						id="eyes">
					</a>
				</tr>
				<tr>
					<td colspan="3" class="sign1_1" id="sign1_2">密码不能为空</td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td><input type="text" id="code" name="code" value="请输入验证码"
						onfocus="check8()"></td>
					<td class="code_right" id="codeone" onclick="createCode()"></td>
				</tr>
				<tr>
					<td colspan="3" class="sign1_1" id="sign1_3">验证码不能为空</td>
				</tr>
				<tr>
					<td>性别：</td>
					<td colspan="2"><select name="sex">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td colspan="2"><input type="text" name="userNickName">
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;"><input
						type="submit" value="注册" onclick="return checkform()"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
<script>
	//获取元素（两种方式都可以）
	var input = document.getElementById('pwd');
	var imgs = document.getElementById('eyes');
	//下面是一个判断每次点击的效果
	var flag = 0;
	function tick() {
		if (flag == 0) {
			input.type = 'text';
			imgs.src = '/team04/front_images/openEyes.png';//睁眼图
			flag = 1;
		} else {
			input.type = 'password';
			imgs.src = '/team04/front_images/closeEyes.png';//闭眼图
			flag = 0;
		}

	}
</script>
</html>