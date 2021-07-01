<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>试卷批改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		
	<script type="text/javascript" src="../../js/common.js"></script>
	<script type="text/javascript">
	function go(num){
		totaoPage = "${page.totalPage}";
		if(totaoPage<num || num<1){
			alert("数据不合法!!!")
		}else{
			var pageSize=document.getElementById("pageSize").value;
			document.getElementById("newPageSize").value = pageSize;
			document.getElementById("currentPage").value = num;
			document.getElementById("fm").submit();
		}
	}
	
</script>
	</head>
<body>

	<div class="page_title">试卷批改</div>
	<form action="${pageContext.request.contextPath }/back/exam/pgExamServlet" method="post" id="fm">
	
	<div class="button_bar">
		<button class="common_button" type="submit">查询</button>
		<input type="hidden" name="pageSize"  id="newPageSize"/>
		<input type="hidden" name="currentPage" id="currentPage"/>
	</div>
	<table class="query_form_table">
		<tr>
			<th>学生姓名</th>
			<td>
				<input type="text" name="userName" value="${userName }">
			</td>
			<th>试卷名称</th>
			<td><input type="text" name="examName" value="${examName }"></td>
		</tr>
	</table>
	</form>
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>学生姓名</th>
			<th>科目名称</th>
			<th>试卷名称</th>
			<th>考试时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${examPgList }" var="exam">
		<tr>
			<td class="list_data_number">${exam.id}</td>
			<td class="list_data_text">${exam.name }</td>
			<td class="list_data_text">${exam.subject }</td>
			<td class="list_data_text">${exam.exam }</td>
			<td class="list_data_text">${exam.date }</td>
			<td class="list_data_text">
				<a href="${pageContext.request.contextPath }/back/exam/pgExamServlet?method=pg&studentExamId=${exam.id}">批改</a>
			</td>
		</tr>	
			</c:forEach>		
		<tr>
			<th colspan="8">
				<div class="pager">
					<div class="pager_left">
						共${page.totalCount }条记录 每页
						<select name="pageSize" onchange="go(1)" id="pageSize">
				<option <c:if test="${page.pageSize==2 }">selected="selected"</c:if> value="2">2</option>
				<option <c:if test="${page.pageSize==5 }">selected="selected"</c:if> value="5">5</option>
				<option <c:if test="${page.pageSize==10 }">selected="selected"</c:if> value="10">10</option>
				<option <c:if test="${page.pageSize==20 }">selected="selected"</c:if> value="20">20</option>
				</select>
						条
						第${page.currentPage }页/共${page.totalPage }页
						转到
						<input style="width:15px;" type="text" id="inp" ><a href="javascript:void(0)" onclick="go(document.getElementById('inp').value)">go</a>
					
					</div>
					<div class="pager_right">
						<button class="common_button" onclick="go(1)">首页</button>
						<button class="common_button" onclick="go(${page.forwardPage})">上一页</button>
						<button class="common_button" onclick="go(${page.nextPage })">下一页</button>
						<button class="common_button" onclick="go(${page.totalPage})">尾页</button>
					
					</div>
				</div>
			</th>
		</tr>
	</table>
</body>
</html>