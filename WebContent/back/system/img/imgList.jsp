<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>首页图片管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		
		<script type="text/javascript">			
			var imgPic;
			var imgState;
			function go(currentPage){
				//在js中用EL表达式获取域对象中的数据  先定义全局变量  再使用双引号包裹EL表达式
				totalPage = "${page.totalPage}";
				if(currentPage > totalPage || currentPage < 1){
					alert("当前值数据不合法！！");
				}else{
					document.getElementById("currentPage").value=currentPage;
					imgPic = "${page.entity.imgPic}";
					imgState = "${page.entity.imgState}";
					document.getElementById("imgPic").value=imgPic
					document.getElementById("imgState").value=imgState
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
	<div class="page_title">首页图片管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${pageContext.request.contextPath }/back/system/img/imgAdd.jsp'">新建</button>
		<button class="common_button" onclick="tj()">查询</button>
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/img/imgServlet?method=findImgByPage" method="post">
	<table class="query_form_table">
		<tr>
			<th>图片名称</th>
			<td><input type="text" id="imgPic" name="imgPic" value="${page.entity.imgPic}"></td>
			<th>状态</th>
			<td>
				<select name="imgState" id="imgState">
					<option value="">请选择</option>
					<option value="1">正常</option>	
					<option value="0">注销</option>					
				</select>
			</td>
		</tr>
	</table>
		<input id="currentPage" type="hidden" name="currentPage">
	</form>
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>图片</th>
			<th>位置</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list }" var="img">
		<tr>
			<td class="list_data_number">${img.imgId }</td>
			<td class="list_data_text"><img width="80px" height="55px" src="${pageContext.request.contextPath }/img/downServlet?fileName=${img.imgPic}"/></td>
			<td class="list_data_text">${img.imgPlace }</td>
			<td class="list_data_text">
				<c:forEach items="${userList }" var = "user">
					<c:if test="${user.userId==img.imgCreatorId }">
						${user.userName }
					</c:if>
				</c:forEach>
			</td>
			<td class="list_data_text">${img.imgCreateDate }</td>
			<td class="list_data_text">
				<a href="/team04/back/sys/img/imgServlet?method=findImgById&imgId=${img.imgId }">编辑</a>
				<c:choose>
					<c:when test="${img.imgState == 1}">
						<a href="${pageContext.request.contextPath}/back/sys/img/imgServlet?method=deleteImgById&imgId=${img.imgId }">删除</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/back/sys/img/imgServlet?method=deleteImgById&imgId=${img.imgId }">恢复</a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		
		<tr>
			<th colspan="7">
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