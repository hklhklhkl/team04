<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>bbs_post_message</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/css/post.css" />
</head>

<body>
	<ul class="post_iframe">
		<c:forEach items="${postList}" var="postList">
    	<c:choose>
		<c:when test="${postList.postState==1 }"><li class="post_iframe_li">
        	<span class="post_span1">${postList.postContent}</span>
            <span class="post_span2">${postList.userName}</span>
            <span class="post_span3">${postList.userId}</span>
            <span class="post_span4">${postList.postDate}</span>
        </li></c:when>
		</c:choose>
    	
        </c:forEach>
       
    </ul>
</body>
</html>