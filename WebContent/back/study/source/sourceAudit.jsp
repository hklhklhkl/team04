<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源审核</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
</head>
<body>
<FORM id="dfForm" action="${pageContext.request.contextPath}/back/study/source/sourceServlet?method=updateResource&exploreId=${resource.exploreId}" method="post">
<div class="page_title">资源审核&nbsp; &gt; 资源审核</div>
	<div class="button_bar">
		<a class="common_button" href="${pageContext.request.contextPath}/back/study/source/sourceList.jsp">返回</a>
		<input class="common_button" type="submit"  value="提交">
	</div>
	<table class="query_form_table">
		<tr>
			<th>资源名称</th>
			<td><input name="exploreName" type="text" value="${resource.exploreName}" /></td>
			<th>资源类别</th>
			<td><input name="exploreTypeId" type="text" value="${resource.exploreTypeId}" /></td>
		</tr>
		<tr>
			<th>描述</th>
			<td colspan="3">
				<textarea name="exploreDes" rows="6" cols="60" >
				${resource.exploreDes }
				</textarea>
			</td>
		</tr>
		<tr>
			<th>上传人</th>
			<td><input type="text" value="钢铁侠" readonly /></td>
			<th>上传时间</th>
			<td><input name="exploreDate" type="text" value="${resource.exploreDate }" /></td>
		</tr>
		<tr>
			<th>审核状态</th>
			<td>
				<select name="exploreState">
					<option value="1">通过</option>
					<option value="2">驳回</option>
				</select>
				<span class="red_star">*</span></td>
			<th>审核意见</th>
			<td><input type="text" value="好" readonly/><span class="red_star">*</span>
			<input name="exploreId " type="hidden" value="${resource.exploreId }">
			<input name="exploreUri " type="hidden" ">
			</td>
		</tr>

	</table>
	</FORM>

</body>
</html>