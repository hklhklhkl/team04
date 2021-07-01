<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>编辑资源类型</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		<script type="text/javascript">
		window.onload = function(){
			var time = getNowFormatDate();
			document.getElementById("currentTime").value = time;
		}
		function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		    return currentdate;
		}
		</script>
	</head>
<body>
	<FORM id="addForm" action="${pageContext.request.contextPath}/back/study/resourceType/resourceTypeServlet?method=updateResourceType&exploreTypeId=${resourceType.exploreTypeId}" method="post">
	<div class="page_title">资源类型管理&nbsp; &gt; 编辑资源类型</div>
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<input type="submit" value="提交">
	</div>
	<table class="query_form_table">
		<tr>
			<th>资源名称</th>
			<td><input type="text" name="exploreTypeName" value="${resourceType.exploreTypeName}" /><span class="red_star">*</span></td>
			<th>创建人</th>
			<td><input type="text" name="exploreTypeMan" value="${resourceType.exploreTypeMan} "  /></td>
		</tr>
		<tr>
			<th>状态</th>
			<td>
				<select name="exploreTypeState">
					<option value="1">启用</option>
					<option value="2">停用</option>
				</select>
				<span class="red_star">*</span></td>
			<th>创建时间</th>
			<td><input type="text" name="exploreTypeDate" id="currentTime"  /></td>
		</tr>
	</table>
	</FORM>
</body>
</html>