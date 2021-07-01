<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath }/front_css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/front_css/index.css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/index.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.1.1.min.js"></script>
</head>
<body>

	<div id="all">
	<center>
		<div>
			<iframe id="ifr1" src="${pageContext.request.contextPath }/front/front_head.jsp" frameborder="0" scrolling="no"></iframe>
		</div>
		<div id="ifr2_1">
			<iframe id="ifr2" style="height: 55px; color: yellow"
				src="${pageContext.request.contextPath }/front/front_title.jsp" frameborder="0" scrolling="no"></iframe>
		</div>
		<div>
			<iframe onload="iFrameHeight('ifr3');" marginheight="0"
				scrolling="no" id="ifr3" name="ifr3" src="${pageContext.request.contextPath }/front/user/frontUserServlet" width="100%"
				height="1343" frameborder="0"> </iframe>
		</div>


		<div>
			<iframe id="ifr4" src="${pageContext.request.contextPath }/front/front_tail.jsp" frameborder="0"></iframe>
		</div>
		</center>
	</div>

</body>
</html>