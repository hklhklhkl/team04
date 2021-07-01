<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线学习管理系统后台登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/back/css/login-style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/back/css/login-body.css"/> 
</head>
<body>
<div class="container">
	<section id="content">
		<form action="${pageContext.request.contextPath }/back/sys/user/loginServlet?method=login" method="post" id="loginForm">
			<h1>后台登录</h1>
			<div>
				<input type="text" placeholder="账号" id="username" name="userName"/>
			</div>
			<div>
				<input type="password" placeholder="密码" id="password" name="userPass"/>
			</div>
			<div>
				<input type="text" placeholder="验证码" id="checkcode"
					name="checkcode" />
				<div class="block_1" onclick="showData()"
					style="width: 89px; float: right; height: 43px; background-color: #f5ff9b; cursor: pointer; -moz-user-select: none; -webkit-user-select: none; margin: 0px; margin-right: 10px"></div>
			</div>
			<div>
				<input type="button" value="登录" class="btn btn-primary"
					id="js-btn-login" onclick="loginSubmit()" /> <!-- <input type="button"
					value="返回" class="btn btn-primary" id="js-btn-login" /> -->
					<a href="${pageContext.request.contextPath }/front/front_index.jsp">返回前台</a>
			</div>
		</form>
		 <div class="button">
			<h3>在线学习管理系统后台登录</h3>	
		</div> 
	</section>
</div>
</body>
<script type="text/javascript">
	var block_1 = document.getElementsByClassName("block_1")[0];
	var res;
	showData();
	function showData() {
		res = "";
		var ele = "";
		for (var i = 0; i < 4; i++) {
			if (Math.random() < 0.4) {
				var num = Math.floor(Math.random() * 10);
				ele += "<span class='num' style='transform:rotate("
						+ Math.floor((Math.random() - 0.5) * 70)
						+ "deg);font-size:"
						+ (Math.floor(Math.random() * 10) + 20)
						+ "px; color:rgb(" + Math.floor(Math.random() * 256)
						+ "," + Math.floor(Math.random() * 256) + ","
						+ Math.floor(Math.random() * 256) + ");'>" + num
						+ "</span>";
				res += num;
			} else if (Math.random() < 0.8 && Math.random() > 0.4) {
				var number = (97 + Math.floor(26 * Math.random()));
				var str = "";
				if (Math.random() < 0.5) {
					str = String.fromCharCode(number).toUpperCase();
				} else {
					str = String.fromCharCode(number).toLowerCase()
				}
				ele += "<span class='num' style='transform:rotate("
						+ Math.floor((Math.random() - 0.5) * 70)
						+ "deg);font-size:"
						+ (Math.floor(Math.random() * 10) + 20)
						+ "px; color:rgb(" + Math.floor(Math.random() * 256)
						+ "," + Math.floor(Math.random() * 256) + ","
						+ Math.floor(Math.random() * 256) + ");'>" + str
						+ "</span>";
				res += str;

			} else {
				var strT = "qwertyuiopasdfghjklzxcvbnm";
				var index = Math.floor(Math.random() * strT.length);
				ele += "<span class='num' style='transform: rotate("
						+ Math.floor((Math.random() - 0.5) * 80)
						+ "deg); font-size:"
						+ (Math.floor(Math.random() * 8) + 20)
						+ "px; color:rgb(" + Math.floor(Math.random() * 256)
						+ "," + Math.floor(Math.random() * 256) + ","
						+ Math.floor(Math.random() * 256) + "); '>"
						+ strT.charAt(index) + "</span>";
				res += strT.charAt(index);
			}
		}
		block_1.innerHTML = ele;
	}

	function loginSubmit() {
		var username = document.getElementById("username");
		var password = document.getElementById("password");
		var login = document.getElementById("loginForm");
		var checkcode = document.getElementById("checkcode");
		if (username.value.trim() == "") {
			window.alert("用户名不能为空");
		} else if (password.value.trim() == "") {
			window.alert("密码不能为空");
		} else if (checkcode.value.toLowerCase() != res.toLowerCase()) {
			window.alert("验证失败！");
			showData();
			checkcode.value = "";
		} else {
			login.submit();
			
		}
	}
</script>
</html>
