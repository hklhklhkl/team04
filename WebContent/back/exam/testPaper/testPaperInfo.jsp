<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>查看试卷</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
	<div class="page_title">试卷管理&nbsp; &gt; 查看试卷</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${pageContext.request.contextPath }/back/exam/examServlet'">返回</button>
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td>
				<select>
					<c:forEach items="${subjectList }" var="subject">
					<c:if test="${exam.subjectId ==subject.subjectId }"><option>${subject.subjectName }</option></c:if>
					</c:forEach>
				</select>
				<span class="red_star">*</span>
			</td>
			<th>试卷名称</th>
			<td>
				<input type="text" readonly="readonly" value="${exam.examName }"><span class="red_star">*</span>
			</td>
		</tr>
		<c:forEach items="${examRuleDetailList }" var="examRuleDetail">
		<c:forEach items="${questionTypeList }" var="questionType">
		<c:if test="${questionType.questionTypeId==examRuleDetail.questionTypeId&&examRuleDetail.questionNum!=0}">
				<tr>
				<th>${questionType.questionTypeName }</th>
				<td colspan="3">
				数量：<input type="text" value="${examRuleDetail.questionNum }" readonly="readonly">
				每题分值：<input type="text" value="${examRuleDetail.score }" readonly="readonly">
				</td>
				</tr>
			</c:if>
			</c:forEach>
		</c:forEach>
		<tr>
			<th>考试总时长</th>
			<td><input type="text" readonly="readonly" value="${exam.examTime }">分钟<span class="red_star">*</span></td>
			<th>考试总分</th>
			<td><input type="text" readonly="readonly" value="${exam.examScore }"><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>学分</th>
			<td colspan="3">
				<input type="text" readonly="readonly" value="${exam.examCredit }">
			</td>
		</tr>
	</table>
</body>
</html>