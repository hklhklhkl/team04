<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
	
		<title>版块管理</title>
		<script type="text/javascript">
	var totalPage;
	var blockName;
	var blockBiref;
	var blockBuff;
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
			blockName="${page.entity.blockName}";
			blockBiref="${page.entity.blockBiref}";
			blockBuff="${page.entity.blockBuff}";
			userId="${page.entity.userId}";
			document.getElementById("blockName").value = blockName;
			document.getElementById("blockBiref").value = blockBiref;
			document.getElementById("blockBuff").value = blockBuff;
			document.getElementById("userId").value = userId;
			//通过js提交form表单
			document.getElementById("fm").submit();
		}	
		
	}

</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../back/js/common.js"></script>
		
	</head>
<body>
	
	<div class="page_title">版块管理</div>
	<div class="button_bar">
	
		<button class="common_button"><a href="${pageContext.request.contextPath }/back/bbs/edition/editionAdd.jsp">新建</a></button>
		
	</div>
	<form action="${pageContext.request.contextPath }/back/forum/block/blockServlet" method="post" id="fm">
	<input type="submit" value="查询">
	<table class="query_form_table">
		<tr>
			<th>版块名称</th>
			<td><input type="text" id="blockName" value="${page.entity.blockName}" name="blockName"/><span class="red_star"></span></td>
			<th>版块简介</th>
			<td><input type="text" id="blockBiref" value="${page.entity.blockBiref}" name="blockBiref"/></td>
		</tr>
		<tr>
			<th>版块状态</th>
			<td><input type="text" id="blockBuff" value="" name="blockBuff"/><span class="red_star"></span></td>
			<th>版主</th>
			<td>
			<select id="userId" name="userId">
			<option></option>   
			<c:forEach items="${user}" var="user">
                <option  value="${user.userId}" >${user.userName}</option>   
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
			<th>版块图片</th>
			<th>版块名称</th>
			<th>版块简介</th>
			<th>版主</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="block" >
		<tr>
			<td class="list_data_number">${block.blockId}</td>
			<td class="list_data_text"><img width="50" height="30" src="${pageContext.request.contextPath }/block/downServlet?fileName=${block.blockPhoto}"/></td>
			<td class="list_data_text">${block.blockName}</td>
			<td class="list_data_text">${block.blockBiref}</td>
			<td class="list_data_text">${block.userName}</td>
			<td class="list_data_text">
				<c:choose>
				 <c:when test="${block.blockBuff==2}"><a name="blockBuff">已启用</a></c:when>
				 <c:when test="${block.blockBuff==1}"><a name="blockBuff">已停用</a></c:when>
				 
				</c:choose>
			</td>
			<td class="list_data_text">
				<a href="${pageContext.request.contextPath}/back/forum/block/blockServlet?method=findBlockById&blockId=${block.blockId}">编辑</a>
				<c:choose>
				
				 <c:when test="${block.blockBuff==2}"><a href="${pageContext.request.contextPath}/back/forum/block/blockServlet?method=Buff&blockId=${block.blockId}&blockBuff=${block.blockBuff}">停用</a></c:when>
				 <c:when test="${block.blockBuff==1}"><a href="${pageContext.request.contextPath}/back/forum/block/blockServlet?method=Buff&blockId=${block.blockId}&blockBuff=${block.blockBuff}">启用</a></c:when>
				</c:choose>
				<a href="${pageContext.request.contextPath}/back/forum/block/blockServlet?method=deleteBlock&blockId=${block.blockId}">删除</a>
				
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
	</table>
</body>
</html>