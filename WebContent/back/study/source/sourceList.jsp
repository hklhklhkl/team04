<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>资源审核</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
	</head>
<body>
	<FORM name="sForm" id="sForm" action="${pageContext.request.contextPath}/back/study/source/sourceServlet?method=findsourceByPageLike" method="post">
	<div class="">资源审核</div>
	<div class="button_bar">
	<input id="newPageSize" type="hidden" name="pageSize">
		<input id="currentPage" type="hidden" name="currentPage">
		<input class="common_button" type="submit" value="查询">
	</div>
	<table class="query_form_table">
		<tr>
			<th>资源名称</th>
			<td><input name="exploreName" id="exploreName" type="text"/></td>
			<th>审核状态</th> 
			<td>
				<select name="exploreState">
					<option value="4">请选择</option>
					<option value="0">待审核</option>
					<option value="1">通过</option>
					<option value="2">驳回</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>资源类别</th>
			<td>
				<select name="exploreTypeId">
					<option value="4">请选择</option>
					<option value="2">java</option>
					<option value="0">html</option>
					<option value="10400">htmls</option>
				</select>
			</td>
			<th>上传时间</th>
			<td><input name="startTimes" type="date">-<input name="endTimes" type="date"></td>
		</tr>
	</table>
	</Form>
	<script type="text/javascript">
	function go(currentPage) {
		
			var totalPage = "${page.totalPage}"
			if(currentPage > totalPage||currentPage < 1){
					alert("数据不合法！！！")
			}else{
				
				document.getElementById("currentPage").value = currentPage;
				var exploreName="${page.entity.exploreName }";
				document.getElementById("exploreName").value = exploreName; 
				document.getElementById("sForm").submit();
			}
	}
	</script>	
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>资源名称</th>
			<th>资源类别</th>
			<th>描述</th>
			<th>上传人</th>
			<th>上传时间</th>
			<th>审核状态</th>
			<th>审核时间</th>
			<th>审核人</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="resource">
		<tr>
			<td class="list_data_number">${resource.exploreId}</td>
			<td class="list_data_text">${resource.exploreName}</td>
			<td class="list_data_text">${resource.exploreTypeId}</td>
			<td class="list_data_text">${resource.exploreDes}</td>
			<td class="list_data_text">钢铁侠</td>
			<td class="list_data_text">${resource.exploreDate}</td>
			<td class="list_data_text">${resource.exploreState}</td>
			<td class="list_data_text">2017-09-10</td>
			<td class="list_data_text">关羽</td>
			<td class="list_data_text">
				<a href="${pageContext.request.contextPath}/back/study/source/sourceServlet?method=findResourceById&exploreId=${resource.exploreId}">审核</a>
				<a href="${pageContext.request.contextPath}/rDownServlet?exploreUri=${resource.exploreUri}">下载</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<th colspan="11">
				<div class="pager">
					<div class="pager_left">
						共${page.totalCount}条记录 每页${page.pageSize}条
						第${page.currentPage }页/共${page.totalPage }页
						转到<input style="width:30px" type="text" id="inp"/>
						<a href="javascript:void(0)" onclick="go(document.getElementById('inp').value)">go</a>
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