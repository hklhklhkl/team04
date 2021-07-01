<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>交友 - 未读消息</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <script type="text/javascript">
        function go(currentPage) {
            location = "${pageContext.request.contextPath}/front/friend/chatServlet?method=findChatNoReadMsgList&userId=${sessionScope.user.userId}&currentPage="
                + currentPage;
        }
    </script>
    <script src="${pageContext.request.contextPath}/front/friend/js/layui/layui.js"></script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.ignoreFlag}) {
                layer.msg("已忽略", {time: 1000});
            } else {
                layer.msg("操作失败", {time: 1000});
            }
        });
    </script>
</head>
<body>
<div id="dabiankuang">
    <form id="fm" method="post">
        <input type="hidden" id="currentPage" name="currentPage">

    </form>
    <ul id="friend" style="padding: 0; margin-top: 40px;">
        <c:choose>
            <c:when test="${empty requestScope.msgNoReadList.list}">
                <p style="font-size: 18px; margin-left: 450px;">暂时没有未读消息</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.msgNoReadList.list}" var="msgNoReadList">
                    <li><img src="${pageContext.request.contextPath}/front/friend/downServlet?fileName=${msgNoReadList.userUri}" alt="用户头像"
                             width="100px" height="100px" style="margin: 10px 53px">
                        <div style="text-align: center;width: 208px;">
                            <p class="mtb10">用户名：${msgNoReadList.userName}</p>
                            <p class="mtb10">姓&nbsp;&nbsp;
                                名：${msgNoReadList.userNickName}</p>
                            <p class="mtb10"><span style="color: red">${msgNoReadList.count}</span>条未读消息</p>
                            <p class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/chatServlet?method=findChatNoReadMsgByUser&userId=${msgNoReadList.userId}&friendId=${sessionScope.user.userId}&userNickName=${msgNoReadList.userNickName}">查看该消息</a>
                            </p>
                            <p class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/chatServlet?method=findChatNoReadMsgList&type=ignoreMsg&userId=${sessionScope.user.userId}&friendId=${msgNoReadList.userId}">忽略该消息</a>
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
        <li>当前第<font color="blue">${requestScope.msgNoReadList.currentPage}</font>页,共<font
                color="blue">${requestScope.msgNoReadList.totalCount}</font>个好友的信息未读,共<font
                color="blue">${requestScope.msgNoReadList.totalPage}</font>页 <a
                href="javascript:go(${requestScope.msgNoReadList.firstPage})">首页</a> <a
                href="javascript:go(${requestScope.msgNoReadList.lastPage})">尾页</a> <a
                href="javascript:go(${requestScope.msgNoReadList.forwardPage})">上一页</a> <a
                href="javascript:go(${requestScope.msgNoReadList.nextPage})">下一页</a>
        </li>
    </ul>
</div>
</body>
</html>
