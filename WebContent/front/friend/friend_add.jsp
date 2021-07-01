<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>交友 - 添加好友</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <style type="text/css">
        #friend li {
            width: 206px;
            margin-bottom: 20px;
            height: 250px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/front/friend/js/layui/layui.js"></script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.flag}) {
                layer.msg("好友请求发送成功", {time: 1000});
            } else {
                layer.msg("好友请求发送失败", {time: 1000});
            }
        });
    </script>
</head>
<body>
<form id="fm" action="${pageContext.request.contextPath}/front/friend/friendServlet?method=findUser&userId=${sessionScope.user.userId}" method="post">
    用户名：<input name="userName" id="userName" type="text" placeholder="请输入您要查找的用户名！"
               class="add-text" value="${requestScope.userName}"/> 姓名：
    <input name="userNickName" id="userNickName" type="text" placeholder="请输入您要查找的姓名！"
           class="add-text" value="${requestScope.userNickName}">
    <input type="hidden" name="userName">
    <input type="hidden" name="userNickName">
    <input type="submit" value="搜索" class="add-submit">
</form>
<ul id="friend" style="padding: 0;">
    <c:forEach items="${requestScope.userList}" var="userList">
        <li>
            <img src="${pageContext.request.contextPath}/front/friend/downServlet?fileName=${userList.userUri}" alt="用户头像" width="100px" height="100px"
                 style="margin: 10px 53px">
            <div style="text-align: center;width: 208px;">
                <p class="mtb10">用户名：${userList.userName}</p>
                <p class="mtb10">姓&nbsp;&nbsp; 名：${userList.userNickname}</p>
                <p class="mtb10">
                    <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findUser&type=add&userId=${sessionScope.user.userId}&friendId=${userList.userId}">加为好友</a>
                </p>
            </div>
        </li>
    </c:forEach>
</ul>
</body>
</html>
