<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>试卷管理</title>
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
<form action="${pageContext.request.contextPath }/back/exam/examServlet" method="post" id="fm">
	
	<div class="page_title">试卷管理</div>
	<div class="button_bar">
		<button class="common_button" type="button" onclick="window.location.href='${pageContext.request.contextPath }/back/exam/examServlet?method=addExam'">新建</button>
		<button class="common_button" onclick="submit">查询</button>
	<input type="hidden" name="pageSize"  id="newPageSize"/>
		<input type="hidden" name="currentPage" id="currentPage"/>
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td>
				<select name="subjectId">
					<option value="0">请选择</option>
					<c:forEach items="${subjectList }" var="subject">
							<option <c:if test="${subjectId==subject.subjectId }">selected</c:if> value="${subject.subjectId }">${subject.subjectName }</option>
						</c:forEach>
				</select>
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
			<th>科目名称</th>
			<th>试卷名称</th>
			<th>试卷所含题型</th>
			<th>试卷状态</th>
			<th>学分</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${examList }" var="exam">
		<tr>
			<td class="list_data_number">${exam.examId }</td>
			<td class="list_data_text">
			<c:forEach items="${subjectList }" var="subject">
				<c:if test="${exam.subjectId ==subject.subjectId }">${subject.subjectName }</c:if>
			</c:forEach>
			</td>
			<td class="list_data_text">${exam.examName }</td>
			<td class="list_data_text">
				<c:forEach items="${examRuleDetailList }" var="examRuleDetail">
				<c:forEach items="${questionTypeList }" var="questionType">
				<c:if test="${questionType.questionTypeId==examRuleDetail.questionTypeId&&exam.examId==examRuleDetail.examRuleId&&examRuleDetail.questionNum!=0 }">
				题型：${questionType.questionTypeName }&nbsp;数量：${examRuleDetail.questionNum }&nbsp;
				每题分值：${examRuleDetail.score }
				<br/>
				</c:if>
				</c:forEach>
				</c:forEach>
			</td>
			<td class="list_data_text">
					<c:if test="${exam.status==1 }">启用</c:if>
					<c:if test="${exam.status==0 }">停用</c:if>
			</td>
			<td class="list_data_number">${exam.examCredit }</td>
			<td class="list_data_text">${exam.examAuthor }</td>
			<td class="list_data_text">${exam.examDate }</td>
			<td class="list_data_text">
				<a href="${pageContext.request.contextPath }/back/exam/examServlet?method=updateExam&examId=${exam.examId}&status=${exam.status}">编辑</a>
				<a href="${pageContext.request.contextPath }/back/exam/examServlet?method=updateExamStatus&examId=${exam.examId}&examStatus=${exam.status}">
				<c:if test="${exam.status==0 }">启用</c:if>
				<c:if test="${exam.status==1 }">停用</c:if>
				</a>
				<a href="${pageContext.request.contextPath }/back/exam/examServlet?method=deleteExam&examId=${exam.examId}">删除</a>
				<a href="${pageContext.request.contextPath }/back/exam/examServlet?method=findExamById&examId=${exam.examId}">查看详情</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<th colspan="10">
				<div class="pager">
					<div class="pager_left">
						共${page.totalCount }条记录 每页<select name="pageSize" onchange="go(1)" id="pageSize">
				<option <c:if test="${page.pageSize==2 }">selected="selected"</c:if> value="2">2</option>
				<option <c:if test="${page.pageSize==5 }">selected="selected"</c:if> value="5">5</option>
				<option <c:if test="${page.pageSize==10 }">selected="selected"</c:if> value="10">10</option>
				<option <c:if test="${page.pageSize==20 }">selected="selected"</c:if> value="20">20</option>
				</select>条
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
	</table>
</body>
</html>