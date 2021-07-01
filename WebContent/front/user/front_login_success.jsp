<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/front/user/frontUserServlet?method=loginOut" method="post">
<h2>登录成功</h2>

<img style="width:70px;height:70px;" src="${pageContext.request.contextPath }/front/downServlet?fileName=${user.userUri }">
<h2>欢迎${user.userName }</h2>
<h5>您的职位是 ${str }</h5>
<input type="submit" value="注销" >
</form>
</body>
</html>