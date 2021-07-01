<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>资源类型管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
	</head>
<body>
	<FORM name="rForm" id="rForm" action="${pageContext.request.contextPath}/back/study/resourceType/resourceTypeServlet?method=findResourceTypeByPageLike" method="post">
	<div class="page_title">资源类型管理</div>
	<div class="button_bar">
		<a class="common_button" href="${pageContext.request.contextPath}/back/study/sourceType/sourceTypeAdd.jsp">新建</a>
		<input id="newPageSize" type="hidden" name="pageSize">
		<input id="currentPage" type="hidden" name="currentPage">
		<input class="common_button" type="submit" value="查询">
	</div>
	<table class="query_form_table">
		<tr>
			<th>资源类型名称</th>
			<td><input id="exploreTypeName" name="exploreTypeName" type="text"/></td>
			<th>状态</th>
			<td>
				<select name="exploreTypeState" id="exploreTypeState">
					<option value="4">请选择</option>
					<option value="1">启用</option>
					<option value="2">停用</option>
				</select>
			</td>
		</tr>
	
	</table>
	</FORM>
	<script type="text/javascript">
	function go(currentPage) {
		
			var totalPage = "${page.totalPage}"
			if(currentPage > totalPage||currentPage < 1){
					alert("数据不合法！！！")
			}else{
				
				document.getElementById("currentPage").value = currentPage;
				var exploreTypeName="${page.entity.exploreTypeName }";
				var exploreTypeState="${page.entity.exploreTypeState }";
				document.getElementById("exploreTypeState").value = exploreTypeState; 
				document.getElementById("exploreTypeName").value = exploreTypeName; 
				document.getElementById("rForm").submit();
			}
	}
	</script>	
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>资源类型名称</th>
			<th>状态</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="resourceType">
		<tr>
			<td class="list_data_number">${resourceType.exploreTypeId}</td>
			<td class="list_data_text">${resourceType.exploreTypeName}</td>
			<td class="list_data_text">${resourceType.exploreTypeState}</td>
			<td class="list_data_text">${resourceType.exploreTypeMan}</td>
			<td class="list_data_text">${resourceType.exploreTypeDate}</td>
			<td class="list_data_text">
				<a href="${pageContext.request.contextPath}/back/study/resourceType/resourceTypeServlet?method=findResourceTypeById&exploreTypeId=${resourceType.exploreTypeId}">编辑</a>
				<a href="${pageContext.request.contextPath}/back/study/resourceType/resourceTypeServlet?method=updateResourceState&exploreTypeId=${resourceType.exploreTypeId}&exploreTypeState=${resourceType.exploreTypeState}"><c:choose>
                 <c:when test="${resourceType.exploreTypeState == 2}">启用</c:when>
                 <c:when test="${resourceType.exploreTypeState == 1}">停用</c:when>    
               </c:choose></a>
				<a href="${pageContext.request.contextPath}/back/study/resourceType/resourceTypeServlet?method=deleteResourceTypeById&exploreTypeId=${resourceType.exploreTypeId}">删除</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<th colspan="7">
				<div class="pager">
					<div class="pager_left">
						共${page.totalCount}条记录 每页${page.pageSize}条
						第${page.currentPage }页/共${page.totalPage }页
						转到<input style="width:30px" type="text" id="inp"/><a href="javascript:void(0)" onclick="go(document.getElementById('inp').value)">go</a>页
						
					</div>
					<div class="pager_right">
						<button class="common_button" onclick="go(${page.firstPage})">首页</button>
						<button class="common_button" onclick="go(${page.forwardPage})">上一页</button>
						<button class="common_button" onclick="go(${page.nextPage})">下一页</button>
						<button class="common_button" onclick="go(${page.lastPage})">尾页</button>
					</div>



				</div>
			</th>
		</tr>
	</table>
</body>
</html>