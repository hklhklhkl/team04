<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath }/front_js/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/front_css/index.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/index.js"></script>
<style type="text/css">
.code_right{
	width: 70px;
	height: 20px;
	float: right;
	background-repeat: no-repeat;
	line-height: 20px;
	text-align: center;
	letter-spacing: 6px;
	font-size: 14px;
	background-image:url("${pageContext.request.contextPath }/front_images/code.png");
	color: black;
	cursor: pointer;
}
</style>
<script>
	var a = document.getElementById("codeone").value;
	var b = document.getElementById("userCode").value;
	b=a;
</script>
</head>
	<body>	
		<div id="tbl3">
		<form action="${pageContext.request.contextPath }/front/user/frontUserServlet?method=login" method="post" onsubmit="return checkform()">
				<br><br><table id="tbl1">
					<tbody><tr>
						<th id="sign3" colspan="2">用户登录</th>
					</tr>
					<tr>
						<td class="sign2" colspan="2">登录名</td>
					</tr>
					<tr>
						<td colspan="3">
						<input type="text" name="userName" id="userName" onfocus="check6()" value="请输入用户名"></td>
					</tr>
					<tr>
						<td colspan="3" class="sign1_1" id="sign1_1">用户名不能为空！</td>
					</tr>
					<tr>
						<td colspan="3" class="sign2">密码</td>
					</tr>
					<tr>
						<td colspan="3"><input type="password" name="userPass" id="pwd" onfocus="check7()"></td>
					</tr>
					<tr>
						<td colspan="3" class="sign1_1" id="sign1_2">密码不能为空！</td>
					</tr>
					<tr>
						<td colspan="3" class="sign2">验证码</td>
					</tr>
					<tr>
						<td ><input type="text" id="code" name="code" value="请输入验证码" onfocus="check8()"></td>
						<td class="code_right" name="userCode"  colspan="2" id="codeone" onclick="createCode()" ></td>
					</tr>
					<tr>
						<td colspan="3" class="sign1_1" id="sign1_3">请校验验证码</td>
					</tr>
					<tr>
						<td style="height:40px;">
							<span id="ht" style="height:40px; width:100px;">
								<a href="${pageContext.request.contextPath }/back/login.jsp" target="blank" id="ht1">后台登陆</a>
							</span>
						</td>
						<td style="height:40px;" colspan="2">
							<span id="ht" style="height:40px; width:100px;">
								<a href="${pageContext.request.contextPath }/front/front_register.jsp" target="blank" id="ht1">注册</a>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="float: right;">
							<input id="sub" type="submit" value="登录" onclick="return checkform()">
						</td>
					</tr>
				</tbody></table>
			</form>
			</div>
			
	
</body>
</html>