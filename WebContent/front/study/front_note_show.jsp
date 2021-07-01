<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML >
<html>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script>
<link href="css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript"
	src="${pageContext.request.contextPath }/front_js/core.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<TITLE>Insert title here</TITLE>
<STYLE type="text/css">
* {
	margin: auto;
}

#learn {
	width: 1300px;
	height: 550px;
	margin-top: 30px;
}

#top {
	width: 1200px;
	height: 38px;
	border-radius: 5px;
	background: url("/slsft/images/titlebg.png");
}

#tab1 {
	width: 120px;
	height: 38px;
	background-color: #F01400;
	line-height: 38px;
	color: white;
	float: left;
	padding-left: 30px;
	margin-left: 50px;
	cursor: pointer;
}

#tab2 {
	width: 120px;
	height: 38px;
	background-color: black;
	color: white;
	float: left;
	line-height: 38px;
	padding-left: 30px;
	cursor: pointer;
}

.tab {
	color: white;
	text-decoration: none;
}

#modual1 {
	width: 1200px;
	height: 500px;
	margin-top: 30px;
	display: block;
}

#modual2 {
	display: none;
	width: 1200px;
	height: 500px;
	margin-top: 30px;
}

.left {
	
}

.img1 {
	border: 1px solid #F3F5F7;
	width: 774px;
	height: 60px;
	float: left;
	background: url("/slsft/images/creatbg.png") no-repeat 0px 0px;
	padding-left: 60px;
	padding-top: 30px;
	border-radius: 5px;
}

.create {
	width: 100px;
	height: 40px;
	background-color: #B60E06;
	margin-right: 180px;
	margin-top: -35px;
	text-indent: 18px;
	line-height: 40px;
}

.create2 {
	color: white;
	text-decoration: none;
}

.content {
	border: 2px solid #EFEFEF;
	width: 830px;
	height: 346px;
	margin-top: 20px;
	float: left;
	border-radius: 5px;
}

.right {
	width: 300px;
	height: 465px;
	float: right;
	margin-top: -100px;
}

.menu {
	list-style: none;
	padding: 0px;
	height: 310px;
}

.li1 {
	width: 300px;
	height: 30px;
	line-height: 30px;
	margin-bottom: 6px;
	margin-top: 10px;
	float: left;
}

.li1>a {
	text-decoration: none;
	color: black;
}

.noteName {
	font-size: 24px;
}

.noteName:hover {
	font-weight: 800;
}

.delete {
	width: 40px;
	height: 15px;
	float: right;
	font-size: 10px;
}

.update {
	width: 40px;
	height: 15px;
	float: right;
	font-size: 10px;
}

.update:hover {
	color: red;
}

.delete:hover {
	color: red;
}

.li2 {
	width: 100px;
	height: 10px;
	float: left;
	line-height: 10px;
}

.li3 {
	width: 100px;
	height: 15px;
	line-height: 15px;
	float: right;
}

.skip {
	width: 40px;
	height: 15px;
	font-size: 10px;
	float: left;
	margin-bottom: 0px;
}

.skip>a {
	text-decoration: none;
	color: black;
}

.skip>a:hover {
	color: blue;
}

#img2 {
	width: 320px;
	height: 475px;
	float: right;
	margin-top: -100px;
}
</STYLE>

<SCRIPT type="text/javascript">
	function check1() {
		document.getElementById("tab1").style.backgroundColor = "#F01400";
		document.getElementById("tab2").style.backgroundColor = "black";
		document.getElementById("modual1").style.display = "block";
		document.getElementById("modual2").style.display = "none";
	}
	function check2() {
		document.getElementById("tab1").style.backgroundColor = "black";
		document.getElementById("tab2").style.backgroundColor = "#F01400";
		document.getElementById("modual1").style.display = "none";
		document.getElementById("modual2").style.display = "block";
		var fm = document.getElementById("tab22");
		fm.action = "/slsft/learn/resource";
		fm.submit();
	}
	
	function deleter() {
		var i = window.confirm("确定删除吗");
		if (i == true) {
			return true;
		} else {
			return false;
		}
	}
</SCRIPT>


<META name="GENERATOR" content="MSHTML 11.00.10570.1001">
</HEAD>
<BODY>
	<!-- 学习模块 -->
	<DIV id="learn">
		<DIV id="top">
			<DIV id="tab1" onclick="check1()">学习手记</DIV>
			<FORM name="tab22" method="post">
				<DIV id="tab2" onclick="check2()">共享资源</DIV>
			</FORM>
		</DIV>
		<!-- 笔记模块 -->
		<DIV id="modual1">
			<DIV class="left">
				<DIV class="img1">
					<H2>用手记的方式，与日俱进！</H2>
					<DIV class="create">
						<A class="create2"
							href="${pageContext.request.contextPath }/front/study/addNote.jsp"
							target="noteIf"> 创建手记 </A>
					</DIV>
				</DIV>
				<DIV class="content">
					<IFRAME name="noteIf" width="830" height="350" id="noteIf"
						src="${pageContext.request.contextPath }/front/study/noteOne.jsp"
						border="0" frameborder="no"></IFRAME>
				</DIV>
			</DIV>
			<DIV class="right">
				<FORM name="npForm" id="npForm"
					action="${pageContext.request.contextPath}/front/study/studyServlet?method=findStudyByPageLike" method="post">
					 笔记名:<INPUT name="noteTitle" type="text" value="${page.entity.noteTitle }" id="noteTitle"><BR>
					<BR>时&nbsp;间:<INPUT name="startTime" type="date" value="${study.startTime }"
						><BR>
					<BR> &nbsp;&nbsp;-&nbsp;<INPUT name="endTime" type="date" value="${study.endTime }"><BR>
						<input id="currentPage" type="hidden" name="currentPage">
						<input id="newPageSize" type="hidden" name="pageSize">
					<INPUT style="margin-right: 20px; float: right;" type="submit"
						value="查询"><BR>
			  </FORM>	
	 <script type="text/javascript">
	function go(currentPage) {
		
			var totalPage = "${page.totalPage}"
			if(currentPage > totalPage||currentPage < 1){
					alert("数据不合法！！！")
			}else{

				
				document.getElementById("currentPage").value = currentPage;
				var noteTitle="${page.entity.noteTitle }";
				document.getElementById("noteTitle").value = noteTitle; 
				document.getElementById("npForm").submit();
			}
	}
	</script>	
					<UL class="menu">
						<c:forEach items="${page.list}" var="study">
							<LI class="li1"><A class="noteTitle"
								href="${pageContext.request.contextPath}/front/study/studyServlet?method=findStudyById&noteId=${study.noteId}"
								target="noteIf">${study.noteTitle}</A> 
								<A class="delete" href="${pageContext.request.contextPath}/front/study/studyServlet?method=deleteStudyById&noteId=${study.noteId}">删除</A>
								<A class="update"
								href="${pageContext.request.contextPath}/front/study/studyServlet?method=findSingleStudyById&noteId=${study.noteId}"
								target="noteIf">修改</A></LI>
							<LI class="li2"></LI>
							<LI class="li3">${study.noteDate}</LI>
						</c:forEach>
					</UL>
					<DIV class="skip" style="width: 90px;">当前第${page.currentPage }页,共${page.totalPage }页</DIV>
					<DIV class="skip" style="margin-left: 20px;">
						<input type="button" onclick="go(${page.firstPage})" value ="首页"> 
					</DIV>
					<DIV class="skip">
						<input type="button" onclick="go(${page.lastPage})" value ="尾页"> 
					</DIV>
					<DIV class="skip" style="width: 48px;">
						<input type="button" onclick="go(${page.forwardPage})" value ="上一页"> 
					</DIV>
					<DIV class="skip" style="width: 48px;">
						<input type="button" onclick="go(${page.nextPage})" value ="下一页">
					</DIV>

			</DIV>
		</DIV>
		<!-- 资源板块 -->
		<DIV id="modual2">
			<DIV class="left">
				<DIV class="img1">
					<H2>资料共享，互帮互助！</H2>
					<DIV class="create">
						<A class="create2"
							href="${pageContext.request.contextPath }/front/study/resource_upload.jsp"
							target="resourceIf"> 分享资料 </A>
					</DIV>
				</DIV>
				<DIV class="content">
					<IFRAME width="830" height="400"
						src="${pageContext.request.contextPath }/front/study/resourceServlet"
						border="0" frameborder="no"></IFRAME>
				</DIV>
			</DIV>
			<DIV id="img2">
				<IFRAME name="resourceIf" width="320" height="475" id="resourceIf"
					src="${pageContext.request.contextPath }/front/study/img.jsp" border="1"
					frameborder="no"></IFRAME>
			</DIV>
		</DIV>
	</DIV>
</BODY>
</html>