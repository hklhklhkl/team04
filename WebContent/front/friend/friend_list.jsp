<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>交友 - 我的好友</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <script type="text/javascript">
        function go(currentPage) {
            document.getElementById('currentPage').value = currentPage;
            document.getElementById('userName').value = "${requestScope.friendListPage.entity.userName}";
            document.getElementById('userNickName').value = "${requestScope.friendListPage.entity.userNickName}";
            document.getElementById('fm').action = "${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendListLike&userId=${sessionScope.user.userId}&currentPage="
                + currentPage;
            document.getElementById('fm').submit();
        }
    </script>
    <script src="${pageContext.request.contextPath}/front/friend/js/layui/layui.js"></script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.addBlackFlag}) {
                layer.msg("已加入黑名单", {time: 1000});
            } else {
                layer.msg("操作失败", {time: 1000});
            }
        });
    </script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.deleteFromListFlag}) {
                layer.msg("删除成功", {time: 1000});
            } else {
                layer.msg("操作失败", {time: 1000});
            }
        });
    </script>
</head>
<body>
<div id="dabiankuang">
    <form id="fm" method="post">
        用户名：<input name="userName" id="userName" type="text"
                   placeholder="请输入您要查找的用户名！" class="add-text"
                   value="${requestScope.friendListPage.entity.userName}"/> 姓名： <input
            name="userNickName" id="userNickName" type="text"
            placeholder="请输入您要查找的姓名！" class="add-text"
            value="${requestScope.friendListPage.entity.userNickName}"> <input
            type="hidden" name="userNickName" id="currentPage"> <input
            type="submit" value="搜索" class="add-submit">
    </form>
    <ul id="friend" style="padding: 0;">
        <c:choose>
            <c:when test="${empty requestScope.friendListPage.list}">
                <p style="font-size: 18px; margin-left: 360px;">暂时没有好友，快去添加好友吧</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.friendListPage.list}" var="friendList">
                    <li><img src="${pageContext.request.contextPath}/front/friend/downServlet?fileName=${friendList.userUri}" alt="用户头像" width="100px"
                             height="100px" style="margin: 10px 53px">
                        <div style="text-align: center;width: 208px;">
                            <p class="mtb10">用户名：${friendList.userName}</p>
                            <p class="mtb10">姓&nbsp;&nbsp; 名：${friendList.userNickName}</p>
                            <p class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/chatServlet?userId=${sessionScope.user.userId}&friendId=${friendList.friendId}&userName=${friendList.userName}&userNickName=${friendList.userNickName}&userUri=${friendList.userUri}
                                    &friendCreatDate=${friendList.userCreatDate}&friendForumIntegral=${friendList.userForumIntegral}&friendExamIntegral=${friendList.userExamIntegral}"
                                   target="ifr">发起聊天</a>
                            </p>
                            <p class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendListLike&action=yes&type=addBlack&userId=${sessionScope.user.userId}&friendId=${friendList.friendId}">加入黑名单</a>
                            </p>
                            <p class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendListLike&action=yes&type=delete&userId=${sessionScope.user.userId}&friendId=${friendList.friendId}" onclick="return confirm('确定删除该好友吗？')">删除好友</a>
                            </p></div>
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<div>
    <ul id="yeshu" style="margin-top: 25px;">
        <li>当前第<font color="blue">${requestScope.friendListPage.currentPage}</font>页,共<font
                color="blue">${requestScope.friendListPage.totalCount}</font>个好友,共<font
                color="blue">${requestScope.friendListPage.totalPage}</font>页 <a
                href="javascript:go(${requestScope.friendListPage.firstPage})">首页</a> <a
                href="javascript:go(${requestScope.friendListPage.lastPage})">尾页</a> <a
                href="javascript:go(${requestScope.friendListPage.forwardPage})">上一页</a> <a
                href="javascript:go(${requestScope.friendListPage.nextPage})">下一页</a>
        </li>
    </ul>
</div>
</body>
</html>
