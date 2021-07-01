<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>交友 - 黑名单</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <script type="text/javascript">
        function go(currentPage) {
            location = "${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendBlackList&userId=${sessionScope.user.userId}&currentPage="
                + currentPage;
        }
    </script>
    <script src="${pageContext.request.contextPath}/front/friend/js/layui/layui.js"></script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.removeFlag}) {
                layer.msg("已解除黑名单", {time: 1000});
            } else {
                layer.msg("操作失败", {time: 1000});
            }
        });
    </script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.deleteFromBlackFlag}) {
                layer.msg("删除成功", {time: 1000});
            } else {
                layer.msg("操作失败", {time: 1000});
            }
        });
    </script>
</head>
<body>
<div id="dabiankuang">
    <ul id="friend" style="padding: 0; margin-top: 40px;">
        <c:choose>
            <c:when test="${empty requestScope.friendBlackListPage.list}">
                <p style="font-size: 18px;margin-left:450px;">没有黑名单用户</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.friendBlackListPage.list}" var="friendBlackListPage">
                    <li><img src="${pageContext.request.contextPath}/front/friend/downServlet?fileName=${friendBlackListPage.userUri}" alt="用户头像" width="100px"
                             height="100px" style="margin: 10px 53px">
                        <div style="text-align: center;width: 208px;">
                            <p class="mtb10">用户名：${friendBlackListPage.userName}</p>
                            <p class="mtb10">姓&nbsp;&nbsp; 名：${friendBlackListPage.userNickName}</p>

                            <p class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendBlackList&action=yes&type=remove&userId=${sessionScope.user.userId}&friendId=${friendBlackListPage.friendId}">解除黑名单</a>
                            </p>
                            <p class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendBlackList&action=yes&type=delete&userId=${sessionScope.user.userId}&friendId=${friendBlackListPage.friendId}" onclick="return confirm('确定删除该好友吗？')">删除该用户</a>
                            </p>
                        </div>
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<div>
    <ul id="yeshu">
        <li>当前第<font color="blue">${requestScope.friendBlackListPage.currentPage}</font>页,共<font
                color="blue">${requestScope.friendBlackListPage.totalCount}</font>个黑名单用户,共<font
                color="blue">${requestScope.friendBlackListPage.totalPage}</font>页 <a
                href="javascript:go(${requestScope.friendBlackListPage.firstPage})">首页</a> <a
                href="javascript:go(${requestScope.friendBlackListPage.lastPage})">尾页</a> <a
                href="javascript:go(${requestScope.friendBlackListPage.forwardPage})">上一页</a> <a
                href="javascript:go(${requestScope.friendBlackListPage.nextPage})">下一页</a>
        </li>
    </ul>
</div>
</body>
</html>
