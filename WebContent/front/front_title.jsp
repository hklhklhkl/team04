<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script><link href="css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath }/front_css/index.css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/index.js"></script>
<script type="text/javascript">
function check1() {
	parent.document.getElementById("ifr3").src="${pageContext.request.contextPath }/front/user/frontUserServlet";
}
function check2() {
	parent.document.getElementById("ifr3").src="${pageContext.request.contextPath }/front/study/front_note_show.jsp";
}
function check3() {
	parent.document.getElementById("ifr3").src="${pageContext.request.contextPath }/front/exam/userExamServlet";
}
function check4() {
	parent.document.getElementById("ifr3").src="${pageContext.request.contextPath }/front/forum/reply/frontReplyServlet";
}
function check5() {
	parent.document.getElementById("ifr3").src="${pageContext.request.contextPath }/front/user/myInfoServlet";
}
</script>
</head>
<body>
<div style="width:1302px; height:53px; text-align:center">
	<div id="navigation">
			<li id="navigation1" onclick="check1()"><a href="javascript:;">首页</a></li>
			<li id="navigation2" onclick="check2()"><a href="javascript:;">学习</a></li>
			<li id="navigation3" onclick="check3()"><a href="javascript:;">考试</a></li>
			<li id="navigation4" onclick="check4()"><a href="javascript:;">论坛</a></li>
			<li id="navigation5" onclick="check5()"><a href="javascript:;">我的信息</a></li>
		</div>
</div>
</body>
</html>