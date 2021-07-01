<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>添加试卷</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath }/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript">
		function comp(){ 
			var num1 = parseInt(document.getElementById("num1").value);    
	        var score1 = parseInt(document.getElementById("score1").value);           
	        var ns1 = num1 * score1 ;
	        var num2 = parseInt(document.getElementById("num2").value);    
	        var score2 = parseInt(document.getElementById("score2").value);           
	        var ns2 = num2 * score2 ;
	        var num3 = parseInt(document.getElementById("num3").value);    
	        var score3 = parseInt(document.getElementById("score3").value);           
	        var ns3 = num3 * score3 ;
	        var num4 = parseInt(document.getElementById("num4").value);    
	        var score4 = parseInt(document.getElementById("score4").value);           
	        var ns4 = num4 * score4 ;
	        var num5 = parseInt(document.getElementById("num5").value);    
	        var score5 = parseInt(document.getElementById("score5").value);           
	        var ns5 = num5 * score5 ;
	        var examScore = ns1+ns2+ns3+ns4+ns5;
	 		document.getElementById("examScore").value=examScore;
	 	}
		function back(){
			window.location.href="${pageContext.request.contextPath }/back/exam/examServlet"
		}
</script>
	</head>
<body>
	<div class="page_title">试卷管理&nbsp; &gt; 修改试卷</div>
	<form action="${pageContext.request.contextPath }/back/exam/examServlet?method=updateExam2&examId=${exam.examId}" method="post" >
	
	<div class="button_bar">
		<button class="common_button" type="button" onclick="back();">返回</button>
		<button class="common_button" type="submit"  >保存</button>
	</div>
	<table class="query_form_table">
		<tr>
			<th>科目名称</th>
			<td>
				<select name="subjectId">
					
					<c:forEach items="${subjectList }" var="subject">
						<option <c:if test="${exam.subjectId==subject.subjectId }">selected</c:if>  value="${subject.subjectId }">${subject.subjectName }</option>
					</c:forEach>
				</select>
				<span class="red_star">*</span>
			</td>
			<th>试卷名称</th>
			<td>
				<input type="text" name="examName" value="${exam.examName }"><span class="red_star">*</span>
			</td>
		</tr>
		<tr>
			<th>单选题</th>
			<td colspan="3">
				数量：<input  <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==1 }">value="${examRuleDetail.questionNum }"</c:if> </c:forEach> value="0" type="text" name="questionNum" id="num1" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
				分值：<input  <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==1 }">value="${examRuleDetail.score }"</c:if>   </c:forEach> value="0"  type="text" name="score" id="score1" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
			</td>
		</tr>
		<tr>
			<th>多选题</th>
			<td colspan="3">
				数量：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==2 }">value="${examRuleDetail.questionNum }"</c:if> </c:forEach> value="0" type="text" name="questionNum" id="num2" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
				分值：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==2 }">value="${examRuleDetail.score }"</c:if>   </c:forEach> value="0"  type="text" name="score" id="score2" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
			</td>
		</tr>
		<tr>
			<th>判断题</th>
			<td colspan="3">
				数量：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==3 }">value="${examRuleDetail.questionNum }"</c:if> </c:forEach> value="0" type="text" name="questionNum" id="num3" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
				分值：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==3 }">value="${examRuleDetail.score }"</c:if>   </c:forEach> value="0"  type="text" name="score" id="score3" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
			</td>
		</tr>
		<tr>
			<th>填空题</th>
			<td colspan="3">
				数量：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==4 }">value="${examRuleDetail.questionNum }"</c:if> </c:forEach> value="0" type="text" name="questionNum" id="num4" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
				分值：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==4 }">value="${examRuleDetail.score }"</c:if>   </c:forEach> value="0"  type="text" name="score" id="score4" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
			</td>
		</tr>
		<tr>
			<th>简答题</th>
			<td colspan="3">
				数量：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==5 }">value="${examRuleDetail.questionNum }"</c:if> </c:forEach> value="0" type="text" name="questionNum" id="num5" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
				分值：<input <c:forEach items="${examRuleDetailList }" var="examRuleDetail"> <c:if test="${examRuleDetail.questionTypeId==5 }">value="${examRuleDetail.score }"</c:if>   </c:forEach> value="0"  type="text" name="score" id="score5" onchange="comp();" onkeyup="this.value=this.value.replace(/\D/g,'')">
			</td>
		</tr>
		<tr>
			<th>考试总时长</th>
			<td><input type="text" value="${exam.examTime }" name="examTime">分钟<span class="red_star">*</span></td>
			<th>考试总分</th>
			<td><input value="${exam.examScore }" readonly="readonly"  type="text" id="examScore" name="examScore"><span class="red_star">*</span></td>
		</tr>
		<tr>
			<th>学分</th>
			<td colspan="3">
				<input type="text" value="${exam.examCredit }" name="examCredit">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>