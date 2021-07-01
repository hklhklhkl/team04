<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>考试试卷-考试结束</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/front/exam/CSS/exam_online.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/front/exam/js/exam_online.js"></script>
<body>
<c:set var="num" value="${num=1 }" ></c:set>
    <!-- 考试中的中间部分 -->
    <div class="examing">
        <p class="exam_position">当前位置：考试结束 &gt; ${exam.examName }</p>
       <span style="color : red;">客观题分数:${count }</span>
        <div class="exam_title">
            <span class="exam_title1">${exam.examName }</span>
            
        </div>
		 <ul class="exam_problems">
	        	<c:forEach items="${erdList }" var="erd">
	        		<c:forEach items="${questionTypeList }" var="questionType">
	        		<c:if test="${questionType.questionTypeId==erd.questionTypeId }">
	        			<li>
	        			<span class="es1">${questionType.questionTypeName }</span>
	               		<span class="es2">（每题${erd.score }分，共${erd.questionNum }题）</span>
	               		
	        			<ul>
	        			<c:forEach	items="${userQuestionList }" var="userQuestion">
	        				
	        				<c:set var="num1" value="${num1=1 }"></c:set>
	                        
	                        <c:if test="${erd.questionTypeId==userQuestion.questionTypeId }">
	                           	<li>
	        						<span class="radio_1">
	                           	 	${num }.${userQuestion.questionName }
	                           	 	<input type="hidden" name="questionId" value="${userQuestion.questionId }" >
	                           	 	<input type="hidden" name="questionTypeId" value="${userQuestion.questionTypeId }" >
	                           	 	<c:forEach items="${sedList }" var="sed">
	                           	 	<c:if test="${userQuestion.questionId==sed.questionId }">
	                           	 	<span style="color : purple;">你的答案:${sed.userAnswer }</span>
	                           	 	</c:if>
	                           	 	</c:forEach>
	                           	 	&nbsp;	&nbsp;	&nbsp;	&nbsp;
	                           	 	<span style="color : red;">正确答案:</span>
	                           	 	<input type="text"  name="questionAnswer" value="${userQuestion.questionAnswer }" >
	                           	 	<input type="hidden" name="questionFen" value="${erd.score }" >
	                           	 	<c:set var="num" value="${num+1 }"></c:set>
	                           	 	</span>
	                           	 	<ul id="ss1" class="radio_1_ul">
	                           	 	<c:set var="numtwo" value="${numtwo=1 }" ></c:set>
	                           	 	<c:forEach items="${questionOptionList }" var="questionOption">
	                           	 		<c:if test="${questionOption.questionId==userQuestion.questionId&&userQuestion.questionTypeId==1}">
	                           	 			<li onclick="click_answer(0)">
	                           	 			<input type="radio" name="${userQuestion.questionId}" value="${num1}" /><label>${questionOption.questionOptionContent }</label>
	                            			
	                            			</li>
	                            			</c:if>
	                           	 		<c:if test="${questionOption.questionId==userQuestion.questionId&&userQuestion.questionTypeId==2}">
	                           	 			<li onclick="click_answer(0)">
	                           	 			<input type="checkBox" name="${userQuestion.questionId}" value="${num1}"  /><label>${questionOption.questionOptionContent }</label>
	                            			</li>
	                            			<c:set var="num1" value="${num1+1 }"></c:set>
	                           	 		</c:if>
	                           	 	</c:forEach>
	                           	 	<c:if test="${userQuestion.questionTypeId==3 }">
	                           	 	<input type="radio"  name="${userQuestion.questionId}" value="对" /> <label for="male1">对</label>
						    		<input type="radio"  name="${userQuestion.questionId}" value="错"  /> <label for="male2">错</label>
	                   				</c:if>
	                   				<c:if test="${userQuestion.questionTypeId==4 }">
	                   					<input type="text" name="${userQuestion.questionId}" width="100" >
	                   				</c:if>
	                   				<c:if test="${userQuestion.questionTypeId==5 }">
	                   					<input type="text" name="${userQuestion.questionId}" style="width: 50%;height: 200%" >
	                   				</c:if>
	                           	 	</ul>
	        					</li>
	        				</c:if>
	        				
	                    </c:forEach>
	        			</ul>
	        			</li>
	        		</c:if>
	        		</c:forEach>
	        		</c:forEach>
			</ul>
	        <div class="exam_button">
	        	<!--提交到服务器需更改form的action值以及启用以下代码-->
	        	<!--
	        		<input type="submit" class="exam_button1" value="提交试卷" />
	        	-->
	        	<a class="exam_button1" href="${pageContext.request.contextPath }/front/exam/userExamServlet?method=qx" ><span>关闭</span></a>
	        	</div>
	   
    </div>
</body>
</html>