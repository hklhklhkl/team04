<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script>
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/front_css/index.css" />
<script language="javascript" src="${pageContext.request.contextPath }/front_js/index.js"></script>
<title></title>
</head>
	<body>
		<div id ="msg">
		<div id="msg1">
				<div id="msg1_1" ><a class="msg" href="javascript:;">我的信息</a></div>
				<div id="msg1_2" ><a class="msg"href="javascript:;"></a></div>
		</div>
		<div id="message2">
			<div id="lef">
			<div id="portrait1">
			<img style="width:150px;height:150px;" src="${pageContext.request.contextPath }/front/downServlet?fileName=${user.userUri }">
			</div>
			<div id="operation">
				修改头像:
				<form enctype="multipart/form-data" action="${pageContext.request.contextPath }/front/user/updateImg?username=${user.userName }&userId=${user.userId }&face=${user.userUri }" id="myform" method="post">
				<input name="face" type="file">
				<input type="submit" value="上传">
				</form>
			</div>
			</div>
			<div class="m1" name="userName">姓名：${user.userName }</div>
			<div class="m1" name="userForumIntegral">发帖积分:${user.userForumIntegral }</div>
			<div class="m1" name="userExamIntegral">考试学分:${user.userExamIntegral }</div>
			<div class="m1" >发帖数:${post }</div>
			<div class="m1" name="userCreateDate">注册时间:${user.userCreateDate }</div>
			<div id="tbl7">
				<table id="tbl8" border="1" cellspacing="0">
					<tr cellspacing="10">
						<td colspan="3" width="247"height="30" >考试信息详情<a href="" style="text-decoration:none;color:red;font-size:20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>more</a></td>
					</tr>
					<tr>
						<td>序号</td>
						<td>试卷</td>
						<td>分数</td>
					</tr>
					
				</table>
			</div>
		</div>
		</div>
		
	</body>
</html>