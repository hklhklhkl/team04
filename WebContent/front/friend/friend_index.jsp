<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>交友</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front/friend/css/default.css">
    <script type="text/javascript">
    	function showScrolling(){
    		document.getElementById('ifr').scrolling="auto";
    	}
    	function hideScrolling(){
    		document.getElementById('ifr').scrolling="no";
    	}
    	function show(){
    		document.getElementById('welcome').style.display="none";
    		document.getElementById('ifr').style.display="block";
    	}
    </script>
</head>
<body marginheight="0">
<div id="dabiankuang">
    <div id="biankuang">
        <ul>
            <li id="l1" onclick="hideScrolling()">
                <a href="${pageContext.request.contextPath}/front/friend/chatServlet?method=findChatNoReadMsgList&userId=${sessionScope.user.userId}" target="ifr" onclick="show()">未读信息</a>
            </li>
            <li id="l2" onclick="hideScrolling()">
                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findApplyList&userId=${sessionScope.user.userId}" target="ifr" onclick="show()">好友请求</a>
            </li>
            <li id="l3" onclick="hideScrolling()">
                <a href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendListLike&userId=${sessionScope.user.userId}" target="ifr" onclick="show()">我的好友</a>
            </li>
            <li id="l4" onclick="hideScrolling()">
                <a style="" href="${pageContext.request.contextPath}/front/friend/friendServlet?method=findAllFriendBlackList&userId=${sessionScope.user.userId}" target="ifr" onclick="show()">黑名单</a></li>
            <li id="l5" onclick="showScrolling()">
                <a href="${pageContext.request.contextPath}/front/friend/friend_add.jsp" target="ifr" onclick="show()">添加好友</a>
            </li>
        </ul>
    </div>
    <div id="biankuangyou">
    	<div id="welcome"></div>
        <iframe onload="" frameborder="0" scrolling="no" id="ifr" name="ifr" width="980" height="490" style="display: none;"></iframe>
    </div>
</div>
</body>
</html>
