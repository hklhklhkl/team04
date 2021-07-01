<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><script type="text/javascript" src="jquery-2.js"></script>
<script type="text/javascript" src="WdatePicker.js"></script><link href="WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="core.js"></script>



	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style>
			*{
				margin:auto;
			}
			a{
				text-decoration:none;
				color:black;
			}
			
			#top{
				height:40px;
				background-color:#454545;
			}
			
						
			/*主体*/
			#tpaper{
				width:1300px;
				position:relative;
			}
			
			/*Subject选择*/
			#sub{
				width:1000px;
				height:200px;
				margin-top:10px;
				border:1px solid #CCCCCC;
				border-radius:10px 10px 0 0;
			}
			
			#sub_ul{
				padding:0;
				list-style:none;
			}
			#sub_ul>li{
				width:150px;
				height:50px;
				line-height:50px;
				text-align:center;
				border:1px solid #CCCCCC;
				float:left;
				margin-left:40px;
				margin-top:30px;
				border-radius:10px;
				color:#666;
				cursor:pointer;
			}
			/*搜索*/
			#search{
				width:1000px;
				height:35px;
				line-height:35px;
				margin-top:10px;
				border:1px solid #CCCCCC;
				background-color:#454545;
				font-size:12px;
				font-family:"微软雅黑";
				text-align:center;
			}
			
			#searchl{
				float:left;
				width:100px;
				background-color:#454545;
				color:white;
				
			}
			
			#searchr{
				float:left;
				width:240px;
				background-color:#454545;
			}
			
			#paper{
				width:1000px;
				height:500px;
				margin-top:10px;
				border:1px solid #CCCCCC;
				border-radius:0 0 10px 10px;
				font-size:12px;
				font-family:"微软雅黑";
				position:relative;
				margin-bottom:20px;
			}
			
			#papertab{
				width:1000px;
			}
			
			#papertab td{
				border-bottom:1px solid #CCCCCC;
				height:40px;
			}
			
			#papertab td:first-child{
				text-align:center;
			}
			
			#papertab td:nth-child(2){
				text-align:center;
			}
			
			#pagediv{
				height:25px;
				text-align:right;
				position:absolute;
				bottom:0;
				right:0;
				z-index:5;
			}
			
			
		</style>
		<script>
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
		
			function changeColor(v){
				var subul = document.getElementById("sub_ul");
				var lis = subul.getElementsByTagName("li");
				for(var i = 0; i < lis.length; i++){
					lis[i].style.backgroundColor = "white";
					lis[i].style.color = "#666";
					lis[i].style.fontWeight = "500";
				}
				
				v.style.backgroundColor = "#C21A12";
				v.style.color = "white";
				v.style.fontWeight = "700";
				
				document.getElementById("subject1").value = v.value;
				document.getElementById("pageForm").submit();
				
			}
			
			onload = function (){
				if("0" != "" ){
					var subul = document.getElementById("sub_ul");
					var lis = subul.getElementsByTagName("li");
					for(var i = 0; i < lis.length; i++){
						if(lis[i].value == "0"){
							lis[i].style.backgroundColor = "#C21A12";
							lis[i].style.color = "white";
							lis[i].style.fontWeight = "700";
						}else{
							lis[i].style.backgroundColor = "white";
							lis[i].style.color = "#666";
							lis[i].style.fontWeight = "500";
						}
					}
				}
				
			}
		
			function goPage(page){
				document.getElementById("currentPage").value = page;
				document.getElementById("pageForm").submit();
			}
			
			function getdataNum(data){
				document.getElementById("counts").value = data.value;
				document.getElementById("pageForm").submit();
			}
			
			function pagego(){
				var v = document.getElementById("pagego").value;
				if(v == "" || isNaN(v)){
					alert("请输入正确的数值");
				}else{
					v = parseInt(v);
					if(v < 1 || v > "2"){
						alert("请输入正确的数值");
					}else{
						goPage(v);
					}
				}
			}
			
			function pageenter(){
				if(event.keyCode == 13){
					pagego();
				}
			}
			
		</script>
	</head>
	<body>
		<div id="tpaper">
			<div id="search">
					
					<form action="${pageContext.request.contextPath }/front/exam/userExamServlet" method="post" id="fm">
	
					<div id="searchl">科目名称：</div>
					<div id="searchl" style="margin-top: 7px">
					<select name="subjectId">
					<option value="0">全部</option>
					<c:forEach items="${subjectList }" var="subject">
							<option <c:if test="${subjectId==subject.subjectId }">selected</c:if> value="${subject.subjectId }">${subject.subjectName }</option>
						</c:forEach>
					</select>
					</div>
					<div id="searchl">试卷名称：</div>
					<div id="searchr">
					<input type="hidden" name="pageSize"  id="newPageSize"/>
					<input type="hidden" name="currentPage" id="currentPage"/>
						<input type="text" name="examName" id="name" value="${examName }">
						<input type="submit" value="搜索" style="background-color:#C21A12;
						border:1px solid black;outline:none;color:white;">
					</div>
					
				</form>
			</div>
			
			<div id="paper">
				<table id="papertab" cellspacing="0">
					<tbody><tr>
						<td width="50">序号</td>
						<td width="100">科目</td>
						<td width="500">试卷名称</td>
						<td width="100">操作</td>
					</tr>
					
						<c:forEach items="${examList }" var="exam">
						<tr>
						<td>${exam.examId }</td>
						<td>
						<c:forEach items="${subjectList }" var="subject">
						<c:if test="${exam.subjectId ==subject.subjectId }">${subject.subjectName }</c:if>
						</c:forEach>
						</td>
						<td>${exam.examName }</td>
						<td><a href="${pageContext.request.contextPath }/front/exam/userExamServlet?method=test&examId=${exam.examId}">考试</a></td>
						</tr>
						</c:forEach>
					
						
					
				</tbody></table>
				
				<div id="pagediv">
					共${page.totalCount }条记录 每页<select name="pageSize" onchange="go(1)" id="pageSize">
				<option <c:if test="${page.pageSize==2 }">selected="selected"</c:if> value="2">2</option>
				<option <c:if test="${page.pageSize==5 }">selected="selected"</c:if> value="5">5</option>
				<option <c:if test="${page.pageSize==10 }">selected="selected"</c:if> value="10">10</option>
				<option <c:if test="${page.pageSize==20 }">selected="selected"</c:if> value="20">20</option>
				</select>条
						第${page.currentPage }页/共${page.totalPage }页
						转到<input style="width:15px;" type="text" id="inp" ><a href="javascript:void(0)" onclick="go(document.getElementById('inp').value)">go</a>
						<button class="common_button" onclick="go(1)">首页</button>
						<button class="common_button" onclick="go(${page.forwardPage})">上一页</button>
						<button class="common_button" onclick="go(${page.nextPage })">下一页</button>
						<button class="common_button" onclick="go(${page.totalPage})">尾页</button>
					
						
					
				</div>
			</div>
		</div>
		
	
</body></html>