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
    </script>
    <script type="text/javascript">
        function close() {
            location
        }
    </script>
</head>
<body>
<div style="float: right; width: 160px; margin-right: 30px">
</div>
<div id="content" style="margin-right: 125px;">
    <table width="723px" cellpadding="0" cellspacing="0">
        <c:forEach items="${requestScope.chatList}" var="chatList">
            <c:if test="${chatList.friendId == 1}">
                <tr>
                    <td colspan="4" align="center"><fmt:formatDate
                            value="${chatList.when}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td bgcolor="#cdcdc1"
                        style="min-width: 65px; text-align: right; vertical-align: top;">
                            ${requestScope.userNickName}：
                    </td>
                    <td width="40.7%" bgcolor="#cdcdc1">${chatList.content}</td>
                    <td
                            style="min-width: 65px; text-align: right; vertical-align: top;">
                    </td>
                    <td width="40.7%"></td>
                </tr>
            </c:if>
            <c:if test="${chatList.userId == 1}">
                <tr>
                    <td colspan="4" align="center"><fmt:formatDate
                            value="${chatList.when}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td
                            style="min-width: 65px; text-align: right; vertical-align: top;">
                    </td>
                    <td width="40.7%"></td>
                    <td bgcolor="#87cefa"
                        style="min-width: 65px; text-align: right; vertical-align: top;">
                        <c:if test="${chatList.userId == 1}">我：</c:if>
                    </td>
                    <td width="40.7%" bgcolor="#87cefa"><c:if
                            test="${chatList.friendId != 1}">${chatList.content}</c:if></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
<div style="width: 740px; float: right;margin-right: 130px">
    <form id="form"
          action="${pageContext.request.contextPath}/front/friend/chatServlet?method=findChatNoReadMsgList&type=reply&userId=${sessionScope.user.userId}"
          method="post">
        <a href="${pageContext.request.contextPath}/front/friend/chatServlet?method=findChatNoReadMsgList&type=ignoreMsg&userId=${sessionScope.user.userId}&friendId=${requestScope.friendId}">忽略</a>
        <input type="text" name="chatContent" id="chatContent"
               placeholder="在此输入内容"
               style="width: 86%; height: 30px; border: none; outline: none; margin-left: 5px;">
        <input type="button" value="回 复" style="height: 30px; width: 50px;"
               onclick="check()"> <input type="hidden" name="friendId"
                                        value="${requestScope.friendId}"><input
            type="hidden" name="userNickName"
            value="${requestScope.userNickName}">
    </form>
</div>
</body>
</html>