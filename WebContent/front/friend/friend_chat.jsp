<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>交友 - 聊天</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <script type="text/javascript">
        function check() {
            var msg = document.getElementById("chatContent").value;
            if (msg == "") {
                alert("请输入内容");
                return false;
            } else {
                document.getElementById("form").submit();
            }
        }
        function showChatRecord() {
            location = "${pageContext.request.contextPath}/front/friend/chatServlet?method=findAllChatRecordListLike&userId=${sessionScope.user.userId}&friendId=${applicationScope.friendInfo.friendId}";
        }
    </script>
</head>
<body>
<div style="float: right; width: 160px; margin-right: 30px">
    <a
            href="javascript:void(0)"
            onclick="showChatRecord()">查看聊天记录</a> <a
        href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendListLike&userId=${sessionScope.user.userId}"
        style="margin-left: 25px;">关闭</a>
</div>
<div id="info">
    <p align="center" style="margin-top: 20px;">TA的信息</p>
    <c:if test="${empty applicationScope.friendInfo.userUri}">为空</c:if>
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
<div id="content">
    <table width="723px" cellpadding="0" cellspacing="0">
        <c:forEach items="${applicationScope.msgList}" var="msgList">
            <c:if test="${msgList.friendId == sessionScope.user.userId}">
                <tr>
                    <td colspan="4" align="center"><fmt:formatDate
                            value="${msgList.when}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td bgcolor="#cdcdc1"
                        style="min-width: 65px; text-align: right; vertical-align: top;">
                            ${applicationScope.friendInfo.userNickName}：
                    </td>
                    <td width="40.7%" bgcolor="#cdcdc1">${msgList.content}</td>
                    <td
                            style="min-width: 65px; text-align: right; vertical-align: top;">
                    </td>
                    <td width="40.7%"></td>
                </tr>
            </c:if>
            <c:if test="${msgList.userId == sessionScope.user.userId}">
                <tr>
                    <td colspan="4" align="center"><fmt:formatDate
                            value="${msgList.when}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td
                            style="min-width: 65px; text-align: right; vertical-align: top;">
                    </td>
                    <td width="40.7%"></td>
                    <td bgcolor="#87cefa"
                        style="min-width: 65px; text-align: right; vertical-align: top;">
                        <c:if test="${msgList.userId == sessionScope.user.userId}">我：</c:if>
                    </td>
                    <td width="40.7%" bgcolor="#87cefa"><c:if
                            test="${msgList.friendId != sessionScope.user.userId}">${msgList.content}</c:if></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
<div style="width: 740px; float: right;">
    <form id="form"
          action="${pageContext.request.contextPath}/front/friend/chatServlet?method=chat&userId=${sessionScope.user.userId}"
          method="post">
        <input type="text" name="chatContent" id="chatContent"
               placeholder="在此输入内容"
               style="width: 91%; height: 30px; border: none; outline: none; margin-left: 5px;">
        <input type="button" value="发 送" style="height: 30px; width: 50px;"
               onclick="check()"> <input type="hidden" name="friendId"
                                         value="${applicationScope.friendInfo.friendId}"><input
            type="hidden" name="userNickName"
            value="${applicationScope.friendInfo.userNickName}">
    </form>
</div>
</body>
</html>