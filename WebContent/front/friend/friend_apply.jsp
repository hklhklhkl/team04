<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>交友 - 好友请求</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <script type="text/javascript">
        function go(currentPage) {
            location = "${pageContext.request.contextPath}/front/friend/friendServlet?method=findApplyList&userId=${sessionScope.user.userId}&currentPage="
                + currentPage;
        }
    </script>
    <script src="${pageContext.request.contextPath}/front/friend/js/layui/layui.js"></script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.agreeFlag}) {
                layer.msg("已成功好友", {time: 1000});
            } else {
                layer.msg("操作失败", {time: 1000});
            }
        });
    </script>
    <script type="text/javascript">
        var layer;
        layui.use('layer', function () {
            if (${requestScope.refuseFlag}) {
                layer.msg("已拒绝", {time: 1000});
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
            <c:when test="${empty requestScope.applyListPage.list}">
                <p style="font-size: 18px; margin-left: 450px;">暂时没有好友申请</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.applyListPage.list}" var="friendApplyList">
                    <li>
                        <img src="${pageContext.request.contextPath}/front/friend/downServlet?fileName=${friendApplyList.userUri}" alt="用户头像" width="100px" height="100px"
                             style="margin: 10px 53px">
                        <div style="text-align: center;width: 208px;">
                            <p class="mtb10">用户名：${friendApplyList.userName}</p>
                            <p class="mtb10">姓&nbsp;&nbsp;名：${friendApplyList.userNickName}</p>
                            <p  class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findApplyList&action=yes&type=agree&userId=${sessionScope.user.userId}&friendId=${friendApplyList.userId}">同意请求</a>
                            </p>
                            <p  class="mtb10">
                                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findApplyList&action=yes&type=refuse&userId=${sessionScope.user.userId}&friendId=${friendApplyList.userId}">拒绝请求</a>
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
        <li>当前第<font color="blue">${requestScope.applyListPage.currentPage}</font>页,共<font
                color="blue">${requestScope.applyListPage.totalCount}</font>个好友请求,共<font
                color="blue">${requestScope.applyListPage.totalPage}</font>页 <a
                href="javascript:go(${requestScope.applyListPage.firstPage})">首页</a> <a
                href="javascript:go(${requestScope.applyListPage.lastPage})">尾页</a> <a
                href="javascript:go(${requestScope.applyListPage.forwardPage})">上一页</a> <a
                href="javascript:go(${requestScope.applyListPage.nextPage})">下一页</a>
        </li>
    </ul>
</div>
</body>
</html>
