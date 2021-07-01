<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
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
		<title>题型管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
	<div class="page_title">题型管理</div>
	<br />
	<table class="data_list_table">
	<form action="${pageContext.request.contextPath }/back/exam/examQuestionTypeServlet" method="post" id="fm">
	<input type="hidden" name="pageSize"  id="newPageSize"/>
		<input type="hidden" name="currentPage" id="currentPage"/>
		
		<tr>
			<th>编号</th>
			<th>题型</th>
		</tr>
		<c:forEach items="${questionTypeList }" var="questionType">
		<tr>
			<td class="list_data_number">${questionType.questionTypeId }</td>
			<td class="list_data_text">${questionType.questionTypeName }</td>
		</tr>
		</c:forEach>
		<tr>
			<th colspan="7">
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
						转到<input style="width:15px;" type="text" id="inp" ><a href="javascript:void(0)" onclick="go(document.getElementById('inp').value)">go</a>
					
						
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
		</form>
	</table>
</body>
</html>