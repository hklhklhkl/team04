<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>bbs_post</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/css/post.css" />
<script>
	function $$(s){
		return document.getElementById(s);
	}
	function changMenu(j){
		var menu=$$("menu");
		var arr=menu.getElementsByTagName("li");
		for(var i=0;i<arr.length;i++){
			if(i==j){
				arr[i].style.border="solid 1px #65a2fd ";
				arr[i].style.fontSize="16px";
				arr[i].style.color="#65a2fd";
			}else{
				arr[i].style.border="solid 1px white ";
				arr[i].style.fontSize="14px";
				arr[i].style.color="#333";
			}
		}
	}
	function checkForm(){
		var title =$$("postTitle").value;
		if(title.length==0){
			alert("请输入标题")
			return false;	
		}else{
			return true;
		}
	}
</script>
</head>

<body>
	<div id="dv">
        <div id="top">
        	<!--左上角的话题和版主名-->
            <div id="top_left">
                <div id="top_left_font1">
                    <span class="top_left_span1">
                        话题：
                    </span>
                    <span class="top_left_span2">
                        朝腾科技
                    </span>
                </div>
                <div id="top_left_font2">
                    <span class="top_left_span1">
                        版主：
                    </span>
                    <span class="top_left_span2">
                        YY
                    </span>
                </div>
            </div>
            <!--右上角的提交话题-->
            <div id="top_right">
                <input type="button" value="发表话题"/>
            </div>
        </div>
        <!--帖子菜单-->
    	<div id="menu">
        	<ul>
            	<li style="margin-left:0px;font-size: 16px;border: solid 1px #65a2fd;color:#65a2fd" onclick="changMenu(0)">
                	全部
                </li>
                <li  onclick="changMenu(1)">
                	精华
                </li>
                <li  onclick="changMenu(2)">
                	最新  
                </li>
                <li  onclick="changMenu(3)">
                	最热
                </li>
            </ul>
        </div>
        <!--蓝框的帖子信息-->
        <div id="postMessage">
        	<span id="postMessage_span1">
            	帖子标题
            </span>
            <span id="postMessage_span2">
            	作者
            </span>
            <span id="postMessage_span3">
            	回复
            </span>
            <span id="postMessage_span4">
            	发帖时间
            </span>
        </div>
        <!--帖子列表-->
        <ul class="post_iframe">
	
		<c:forEach items="${postList}" var="postList">
    	<c:choose>
		<c:when test="${postList.postState==1 }">
        	<span class="post_span1"><a href="${pageContext.request.contextPath }/front/forum/comment/frontServlet?method=findpostById&postId=${postList.postId}">${postList.postContent}</a></span>
            <span class="post_span2">${postList.userName}</span>
            <span class="post_span3">${postList.userId}</span>
            <span class="post_span4">${postList.postDate}</span>
       </c:when>
		</c:choose>
    	 </li>
        </c:forEach>
       
    </ul>
        <!--快速发帖-->
        <form action="${pageContext.request.contextPath}/front/forum/comment/frontServlet?method=addPost" method="post" id="postFast" onsubmit="return checkForm()">
			<div id="postFast_title">
                快速发帖
            </div>
            <div id="postFast_content1">
                帖子标题
            	<input type="text" id="postTitle" placeholder="请输入帖子的标题..." name="postContent"/>
            </div>
            <div id="postFast_content2">
            	<span>帖子内容</span>
                <textarea placeholder="请输入帖子的内容..." name="postComment"></textarea>
            </div>
            版块ID:
            	<input type="text"  name="blockId"/>
            </div>
            <div>
            用户ID:
                <input type="text"  name="userId"/>
            </div>
            <div id="postFast_submit">
                <input type="submit" value="发表帖子"/>
            </div>                                
        </form>
        
    </div>
</body>
</html>