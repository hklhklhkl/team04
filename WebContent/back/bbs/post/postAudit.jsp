<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>帖子审核</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/back/js/common.js"></script>
	</head>
<body>
	<div class="page_title">帖子管理&nbsp; &gt; 帖子审核</div>
	<form action="${pageContext.request.contextPath }/back/forum/post/postServlet?method=updatePost" method="post">
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<input type="submit" value="保存" class="common_button">
	</div>
	<table class="query_form_table">
		<tr>
			<th>帖子名称</th>
			<td><input type="text" value="${post.postContent }" name="postContent" readonly/></td>
			<th>版块名称</th>
			<td><input type="text" value="${post.blockId }" name="blockId" readonly/></td>
		</tr>
		<tr>
			<th>帖子内容</th>
			<td colspan="3">
				<input type="text" value="${post.postComment }" name="postComment" readonly/>
			</td>
		</tr>
		<tr>
			<th>发帖人</th>
			<td><input type="text" value="${post.userId }" name="userId" readonly/></td>
			<th>发布时间</th>
			<td><input type="text" value="${post.postDate }" name="postDate" readonly/></td>
		</tr>
		<tr>
			<th>审核状态</th>
			<td>
				<c:choose>
				 <c:when test="${post.postState==2 }"><a>已驳回</a></c:when>
				 <c:when test="${post.postState==1}"><a>已通过</a></c:when>
				 <c:when test="${post.postState==0 }"><a>未审核</a></c:when>
				</c:choose>
				
				<span class="red_star">*</span>&nbsp;&nbsp;
				<a>请选择操作:</a>
				<select id="postState" name="postState">
					<option value="0">请选择</option>  
	                <option value="1">通过</option>   
	                <option value="2">驳回</option>
                </select>
				</td>
			<th>审核意见</th>
			<td><input type="text" value="${post.postSaw }" name="postSaw"/><span class="red_star">*</span></td>
		</tr>
	
	</table>
	<input type="hidden" value="${post.postId }" name="postId" readonly/>
	</form>
</body>
</html>