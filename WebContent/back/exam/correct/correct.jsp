<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>试卷批改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
<form action="${pageContext.request.contextPath }/back/exam/pgExamServlet?method=jiaFen&id=${id}" method="post" >
	
	<div class="page_title">试卷管理&nbsp; &gt; 添加试卷</div>
	<div class="button_bar">
		<button class="common_button" type="button" onclick="window.location.href='${pageContext.request.contextPath }/back/exam/pgExamServlet'">返回</button>
		<button class="common_button" type="submit">保存</button>
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td>${examPg.subject }</td>
			<th>试卷名称</th>
			<td>${examPg.exam }</td>
		</tr>
		<tr>
			<th>学生姓名</th>
			<td>${examPg.name }</td>
			<th>题目分值</th>
			<td>${score }分</td>
		</tr>
		<c:forEach items="${questionPg }" var="question">
		<tr>
			<th>题目</th>
			<td colspan="3">${question.questionName }</td>
		</tr>
		<tr>
			<th>学员答案</th>
			<td colspan="3">
				${question.userAnswer }
			</td>
		</tr>
		<tr>
			<th>题目答案</th>
			<td colspan="3">
				${question.questionAnswer}
			</td>
		</tr>
		
		<tr>
			<th>实际得分</th>
			<td colspan="3"><input oninput="if(value>${score })value=${score }" name="kgFen" type="text" value="0"><span class="red_star">*</span></td>
		</tr>
		</c:forEach>
	</table>
	</form>
</body>
</html>