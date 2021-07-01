<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>交友 - 聊天记录</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <script type="text/javascript">
        var totalPage;

        function go(currentPage) {
            //在js中用EL表达式获取域对象中的数据，先定义全局变量，再使用双引号包裹EL表达式
            totalPage = "${requestScope.chatMsgListPage.totalPage}";
            if (currentPage > totalPage || currentPage < 1) {
                alert("数据不合法!!!");
            } else {
                document.getElementById("newPageSize").value = document.getElementById("pageSize").value;
                document.getElementById("currentPage").value = currentPage;
                //通过js提交form表单
                document.getElementById("fm").submit();
            }
        }
    </script>
</head>
<body>
<div style="float: right; width: 150px; margin-right: 30px;">
    <form id="fm" align="center"
          action="${pageContext.request.contextPath}/front/friend/chatServlet?method=findAllChatRecordListLike&userId=${sessionScope.user.userId}&friendId=${applicationScope.friendInfo.friendId}"
          method="post">
        <input id="newPageSize" type="hidden" name="pageSize">
        <input id="currentPage" type="hidden" name="currentPage">
    </form>
    <a
            href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendListLike&userId=${sessionScope.user.userId}"
            style="margin-left: 100px;">关闭</a>
</div>
<div id="info">
    <p align="center" style="margin-top: 20px;">TA的信息</p>
    <img src="${pageContext.request.contextPath}/front/friend/downServlet?fileName=${applicationScope.friendInfo.userUri}" alt="用户头像" width="100px"
         height="100px" style="margin: 10px 53px">
    <p class="info_p">ID：${applicationScope.friendInfo.friendId}</p>
    <p class="info_p">姓名：${applicationScope.friendInfo.userNickName}</p>
    <p class="info_p">用户名：${applicationScope.friendInfo.userName}</p>
    <p class="info_p">注册日期：<fmt:formatDate
            value="${applicationScope.friendInfo.userCreatDate}" pattern="yyyy-MM-dd"/></p>
    <p class="info_p">论坛积分：${applicationScope.friendInfo.userForumIntegral}</p>
    <p>考试学分：${applicationScope.friendInfo.userExamIntegral}</p>
</div>
<div id="content" style="overflow: hidden;">
    <table width="723px" cellpadding="0" cellspacing="0">
        <c:forEach items="${requestScope.chatMsgListPage.list}" var="chatMsgListPage">
            <c:if test="${chatMsgListPage.friendId == sessionScope.user.userId}">
                <tr>
                    <td colspan="4" align="center"><fmt:formatDate
                            value="${chatMsgListPage.when}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td bgcolor="#cdcdc1"
                        style="min-width: 65px; text-align: right; vertical-align: top;">
                            ${applicationScope.friendInfo.userNickName}：
                    </td>
                    <td width="40.7%" bgcolor="#cdcdc1">${chatMsgListPage.content}</td>
                    <td
                            style="min-width: 65px; text-align: right; vertical-align: top;">
                    </td>
                    <td width="40.7%"></td>
                </tr>
            </c:if>
            <c:if test="${chatMsgListPage.userId == sessionScope.user.userId}">
                <tr>
                    <td colspan="4" align="center"><fmt:formatDate
                            value="${chatMsgListPage.when}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td
                            style="min-width: 65px; text-align: right; vertical-align: top;">
                    </td>
                    <td width="40.7%"></td>
                    <td bgcolor="#87cefa"
                        style="min-width: 65px; text-align: right; vertical-align: top;">
                        <c:if test="${chatMsgListPage.userId == sessionScope.user.userId}">我：</c:if>
                    </td>
                    <td width="40.7%" bgcolor="#87cefa"><c:if
                            test="${chatMsgListPage.friendId != sessionScope.user.userId}">${chatMsgListPage.content}</c:if></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
<div style="width: 740px; float: right; text-align: center;">
    <ul>
        <li>当前第<font color="blue">${requestScope.chatMsgListPage.currentPage}</font>页,共<font
                color="blue">${requestScope.chatMsgListPage.totalCount}</font>条记录,共<font
                color="blue">${requestScope.chatMsgListPage.totalPage}</font>页
            <select id="pageSize" name="pageSize" onchange="go(1)">
                <option
                        <c:if test="${requestScope.chatMsgListPage.pageSize == 2 }">selected="selected"</c:if>
                        value="2">2
                </option>
                <option
                        <c:if test="${requestScope.chatMsgListPage.pageSize == 4 }">selected="selected"</c:if>
                        value="4">4
                </option>
                <option
                        <c:if test="${requestScope.chatMsgListPage.pageSize == 6 }">selected="selected"</c:if>
                        value="6">6
                </option>
                <option
                        <c:if test="${requestScope.chatMsgListPage.pageSize == 8 }">selected="selected"</c:if>
                        value="8">8
                </option>
                <option
                        <c:if test="${requestScope.chatMsgListPage.pageSize == 10 }">selected="selected"</c:if>
                        value="10">10
                </option>
            </select><a
                    href="javascript:go(${requestScope.chatMsgListPage.firstPage})">首页</a> <a
                    href="javascript:go(${requestScope.chatMsgListPage.lastPage})">尾页</a> <a
                    href="javascript:go(${requestScope.chatMsgListPage.forwardPage})">上一页</a> <a
                    href="javascript:go(${requestScope.chatMsgListPage.nextPage})">下一页</a>
            跳转至<input id="inp" type="text" style="width: 50px;margin-right: 2px;">
            <button style="width: 30px;" onclick="go(document.getElementById('inp').value)">GO</button>
        </li>
    </ul>
</div>
</body>
</html>