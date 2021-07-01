<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>模块管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		<script type="text/javascript">			
			var moduleName;
			var moduleState;
			function go(currentPage){
				//在js中用EL表达式获取域对象中的数据  先定义全局变量  再使用双引号包裹EL表达式
				totalPage = "${page.totalPage}";
				if(currentPage > totalPage || currentPage < 1){
					alert("当前值数据不合法！！");
				}else{
					document.getElementById("currentPage").value=currentPage;
					moduleName = "${page.entity.moduleName}";
					moduleState = "${page.entity.moduleState}";
					document.getElementById("moduleName").value=moduleName
					document.getElementById("moduleState").value=moduleState
					//通过js提交表单
					document.getElementById("fm").submit();  
				}				
			}
			function tj(){
				document.getElementById("fm").submit();
			}			
		</script>
	</head>
<body>
	<div class="page_title">模块管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${pageContext.request.contextPath }/back/system/model/modelAdd.jsp'">新建</button>
		<button class="common_button" onclick="tj()">查询</button>
	</div>	
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/module/moduleServlet?method=findModuleByPageLike" method="post">
	<table class="query_form_table">
		<tr>
			<th>模块名称</th>
			<td><input type="text" id="moduleName" name="moduleName" value="${page.entity.moduleName}"></td>
			<th>状态</th>
			<td>
				<select name="moduleState" id="moduleState">
					<option value="">请选择</option>
					<option value="1" >
					正常</option>	
					<option value="0" 
						<c:if test="${page.entity.moduleState == 0 }">selected='selected'</c:if> >
					注销</option>					
				</select>
			</td>
		</tr>
	</table>
		<input id="currentPage" type="hidden" value="" name="currentPage">
	</form>
	
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>模块名称</th>
			<th>访问URL</th>
			<th>父模块</th>
			<th>状态</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list }" var="module">
		<tr>
			<td class="list_data_number">${module.moduleId }</td>
			<td class="list_data_text">${module.moduleName }</td>
			<td class="list_data_text">${module.moduleUrl }</td>
			<td class="list_data_text">${module.moduleParentId }</td>
			<td class="list_data_text">
			<c:choose>
				<c:when test="${module.moduleState == 1 }">正常</c:when>
				<c:otherwise>注销</c:otherwise>
			</c:choose>
			</td>
			<td class="list_data_text">
			<c:forEach items="${userList }" var = "user">
					<c:if test="${user.userId==module.moduleCreatorId }">
						${user.userName }
					</c:if>
			</c:forEach>
			</td>
			<td class="list_data_text">${module.moduleCreateDate }</td>
			<td class="list_data_text">
				<a href="/team04/back/sys/module/moduleServlet?method=findModuleById&moduleId=${module.moduleId }">编辑</a>
				<c:choose>
					<c:when test="${module.moduleState == 1}">
						<a href="${pageContext.request.contextPath}/back/sys/module/moduleServlet?method=deleteModuleById&moduleId=${module.moduleId }">注销</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/back/sys/module/moduleServlet?method==deleteModuleById&moduleId=${module.moduleId }">恢复</a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<th colspan="8">
				<div class="pager">
					<div class="pager_left">
						共${page.totalCount }条记录 每页${page.pageSize }条
						第${page.currentPage }页/共${page.totalPage }页
						转到<input value="1" id="inp" size="1" />页
						<button width="20" onclick="go(document.getElementById('inp').value);">GO</button>
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