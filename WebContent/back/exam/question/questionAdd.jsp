<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加题目</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript">

			//新增选项
			function addOption(){
				var options = document.getElementById("options");
				options.innerHTML += "<li><input type='text' name='num'></li>";
				
			}
			//删除选项
			function delOption() {
				var options = document.getElementById("options");
				var lis = options.getElementsByTagName("li");
				lis[lis.length-1].remove();
				
			}
			function back(){
				window.location.href="${pageContext.request.contextPath }/back/exam/examQuestionServlet"
			}
			//选择题
			function selectQuestion(value) {
				var optionTr = document.getElementById("optionTr");
				if(value == 1 || value == 2) {
					optionTr.className = "optionTr_show"
				}else {
					optionTr.className = "optionTr_hidden"
				}
			}
		</script>
	</head>
<body>
	<div class="page_title">题目管理&nbsp; &gt; 添加题目</div>
	<form action="${pageContext.request.contextPath }/back/exam/examQuestionServlet?method=addQuestion2&userName=${user.userName}" method="post" >
	<div class="button_bar">
		<input type="button" onclick="back();" value="返回">
		<input type="submit" value="保存">
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td>
				<select name="subjectId">
					<option value="0">请选择</option>
					<c:forEach items="${subjectList }" var="subject">
					
							<option value="${subject.subjectId }">${subject.subjectName }</option>
						
						</c:forEach>
				</select>
				<span class="red_star">*</span>
			</td>
			<th>题型</th>
			<td>
				<select name="questionTypeId" onchange="selectQuestion(this.value)">
					<option value="0">请选择</option>
					<c:forEach items="${questionTypeList }" var="questionType">
					<option value="${questionType.questionTypeId }">${questionType.questionTypeName }</option>
					</c:forEach>
				</select>
				<span class="red_star">*</span>
			</td>
		</tr>
		<tr>
			<th>题目</th>
			<td colspan="3"><input type="text" size="80" name="questionName"><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>答案</th>
			<td colspan="3"><textarea rows="6" cols="50" name="questionAnswer"></textarea><span class="red_star">*</span></td>
		</tr>
		<tr class="optionTr_hidden" id="optionTr">
			<th>选项</th>
			<td colspan="3">
				<button class="common_button" onclick="addOption();" type="button">新增选项</button>
				<button class="common_button" onclick="delOption();" type="button">删除选项</button>
				<ol type="A" id="options">
					<li><input type="text" name="num"></li>
				</ol>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>