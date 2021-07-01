<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>查看题目</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
	</head>
<body>
	<div class="page_title">题目管理&nbsp; &gt; 查看题目</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${pageContext.request.contextPath }/back/exam/examQuestionServlet'">返回</button>
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td>
				<select>
					<c:forEach items="${subjectList }" var="subject">
					<c:if test="${question.subjectId ==subject.subjectId }"><option>${subject.subjectName }</option></c:if>
					</c:forEach>
				</select>
				<span class="red_star">*</span>
			</td>
			<th>题型</th>
			<td>
				<select>
					<c:forEach items="${questionTypeList }" var="questionType">
				<c:if test="${question.questionTypeId ==questionType.questionTypeId }"><option>${questionType.questionTypeName }</option></c:if>
			</c:forEach>
				</select>
				<span class="red_star">*</span>
			</td>
		</tr>
		<tr>
			<th>题目</th>
			<td colspan="3"><input type="text" readonly="readonly" value="${question.questionName }" size="80"><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>答案</th>
			<td colspan="3"><textarea rows="6" cols="50" readonly="readonly">${question.questionAnswer }</textarea><span class="red_star">*</span></td>
		</tr>
		<tr class="optionTr_show" id="optionTr">
			<th>选项</th>
			<td colspan="3">
				<ol type="A" id="options">
					<c:forEach items="${questionOptionList }" var="questionOption">
						<li><input readonly="readonly" type="text" value="${questionOption.questionOptionContent }"></li>
					</c:forEach>
				</ol>
			</td>
		</tr>
	</table>
</body>
</html>