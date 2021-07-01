<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>帖子管理</title>
		<script type="text/javascript">
		var totalPage;
		var postContent;
		var postState;
		var blockId;
		var userId;
		function go(cunrrentPage) {
			//在js中用EL表达式获取域对象中的数据,先定义全局变量,再使用双引号包裹EL表达式
			totalPage = "${page.totalPage}";
			if(cunrrentPage > totalPage || cunrrentPage < 1){
				alert("当前值数据不合法！！");
			}else{
				var pageSize = document.getElementById("pageSize").value;
				document.getElementById("newPageSize").value = pageSize;
				document.getElementById("currentPage").value = cunrrentPage;
				postContent="${page.entity.postContent}";
				postState="${page.entity.postState}";
				blockId="${page.entity.blockId}";
				userId="${page.entity.userId}";
				document.getElementById("postContent").value = postContent;
				document.getElementById("postState").value = postState;
				document.getElementById("blockId").value = blockId;
				document.getElementById("userId").value = userId;
				//通过js提交form表单
				document.getElementById("fm").submit();
			}	
			
		}
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/back/js/common.js"></script>
	</head>
<body>
	<div class="page_title">帖子管理</div>
	<form action="${pageContext.request.contextPath }/back/forum/post/postServlet" method="post" id="fm">
	<div class="button_bar">
	<input type="submit" value="查询">
	</div>
	<table class="query_form_table">
		<tr>
			<th>帖子名称</th>
			<td><input name="postContent" value="${post.postContent}" id="postContent"/></td>
			<th>审核状态</th>
			<td>
				<select id="postState" name="postState">
					<option></option>   
					<c:forEach items="${state}" var="state">
	                	<option value="${state.postState}">${state.postState}</option>   
	            	</c:forEach>
           		</select>
			</td>
		</tr>
		<tr>
			<th>版块</th>
			<td>
				<select id="blockId" name="blockId">
					<option></option>   
					<c:forEach items="${block}" var="block">
	                	<option value="${block.blockId}">${block.blockName}</option>   
	            	</c:forEach>
           		</select>
			</td>
			<th>用户</th>
			<td><select id="userId" name="userId">
					<option></option>   
					<c:forEach items="${user}" var="user">
	                	<option value="${user.userId}">${user.userName}</option>   
	            	</c:forEach>
           		</select></td>
		</tr>
	</table>
	<input id="newPageSize" type="hidden"  name="pageSize">
	<input id="currentPage" type="hidden"  name="currentPage">
	</form>
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>帖子名称</th>
			<th>版块名称</th>
			<th>发帖人</th>
			<th>发布时间</th>
			<th>审核状态</th>
			<th>审核时间</th>
			<th>审核人</th>
			<th>是否加精</th>
			<th>是否置顶</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="post" >
		<tr>
			<td class="list_data_number"><a name="postId">${post.postId}</a></td>
			<td class="list_data_text"><a name="postContent">${post.postContent}</a></td>
			<td class="list_data_text"><a name="postId">${post.blockId}</a></td>
			<td class="list_data_text"><a name="userId">${post.userId}</a></td>
			<td class="list_data_text"><a name="postDate">${post.postDate}</a></td>
			<td>
			    <c:choose>
				 <c:when test="${post.postState==1 }"><a name="postState">已通过</a></c:when>
				 <c:when test="${post.postState==0}"><a name="postState">未审核</a></c:when>
				 <c:when test="${post.postState==2 }"><a name="postState">已驳回</a></c:when>
				</c:choose>
			</td>
			<td class="list_data_text"><a name="postDate">${post.postDate}</a></td>
			<td class="list_data_text"><a name="userId">${post.userId}</a></td>
			<td class="list_data_text"><c:choose>
				 <c:when test="${post.postGood==1 }"><a name="postGood">已加精</a></c:when>
				 <c:when test="${post.postGood==0 }"><a name="postGood">未加精</a></c:when>
				</c:choose></td>
			<td class="list_data_text"><c:choose>
				 <c:when test="${post.postUp==1 }"><a name="postUp">已置顶</a></c:when>
				 <c:when test="${post.postUp==0 }"><a name="postUp">未置顶</a></c:when>
				</c:choose> </td>
			<td class="list_data_text">
				<c:choose>
				 <c:when test="${post.postGood==1 }"><a href="${pageContext.request.contextPath}/back/forum/post/postServlet?method=updateGood&postId=${post.postId}&postGood=${post.postGood}">取消加精</a></c:when>
				 <c:when test="${post.postGood==0 }"><a href="${pageContext.request.contextPath}/back/forum/post/postServlet?method=updateGood&postId=${post.postId}&postGood=${post.postGood}">加精</a></c:when>
				</c:choose>
				<c:choose>
				 <c:when test="${post.postUp==1 }"><a href="${pageContext.request.contextPath}/back/forum/post/postServlet?method=updateUp&postId=${post.postId}&postUp=${post.postUp}">取消置顶</a></c:when>
				 <c:when test="${post.postUp==0 }"><a href="${pageContext.request.contextPath}/back/forum/post/postServlet?method=updateUp&postId=${post.postId}&postUp=${post.postUp}">置顶</a></c:when>
				</c:choose> 
				<a href="${pageContext.request.contextPath}/back/forum/post/postServlet?method=findPostById&postId=${post.postId}">审核</a>
				<a href="${pageContext.request.contextPath}/back/forum/post/postServlet?method=findById&postId=${post.postId}">查看详情</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="9">
				总记录数${page.totalCount}
				当前页${page.currentPage}
				总页数${page.totalPage}
				每页显示条数
				<select id="pageSize" name="pageSize" onchange="go(1)">
	                <option <c:if test="${page.pageSize==2}">selected="selected"</c:if> value="2">2</option>   
	                <option <c:if test="${page.pageSize==5}">selected="selected"</c:if> value="5">5</option>
	                <option <c:if test="${page.pageSize==10}">selected="selected"</c:if> value="10">10</option>
                </select>
				<input type="button" onclick="go(${page.firstPage})" value="首页">
				<input type="button" onclick="go(${page.forwardPage})"  value="上一页">
				<input type="button" onclick="go(${page.nextPage})"  value="下一页">
				<input type="button" onclick="go(${page.lastPage})"  value="尾页">
				<input type="text" style="width:20px;height:20px" id="inp"><a href="javascript:void(0)" onclick="go(document.getElementById('inp').value)">go</a>
			</td>
		</tr>
	</table>
</body>
</html>