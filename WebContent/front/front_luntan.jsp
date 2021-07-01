<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath }/front_css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
<title>luntan</title>
<style type="text/css">
	*{
		margin: auto;
	}
	#main{
		width:1200px;
		height:auto;
		outline:0px solid red;
	}
	#up{
		margin-top:10px;
		width:1000px;
		height: auto;
		border:1px solid #ccc;
		border-top-left-radius: 5px;
		border-top-right-radius: 5px;
		border-bottom-left-radius: 5px;
		border-bottom-right-radius: 5px;
	}
	.up_head{
		width:1000px;
		height:40px;
		background: url("/slsft/images/titlebg.png") repeat-x;
	}
	.up_title1{
		width:130px;
		height:40px;
		float:left;
		margin-left:20px;
		background: url('/slsft/images/comiis_bm_h.gif') 0 2px;
		font-weight: 600;
		line-height: 40px;
		text-align: center;
		color: white;
	}
	.up_title2{
		width:50px;
		height:36px;
		float:left;
		margin-top:2px;
		background: url('/slsft/images/comiis_bm_h.gif') repeat-x 22px -34px;
	}
	#up_body{
		width:1000px;
		height:auto;
		outline: 0px solid black;
	}
	
	#up_body li{
		width:300px;
		height:100px;
		outline:0px solid red;
		float: left;
		margin-right: 25px;
		list-style: none;
	}
	#up_body img{
		display:inline-block;
		position:relative;
		margin-top: 15px;
		outline:0px solid black;
		float: left;
		left:10px;
		transition:all .2s;
	}
	#up_body img:hover{
		left:0px;
	}
	#up_body li a{
		display: inline-block;
		width: 150px;
		height: 23px;
		float: left;
		margin-top: 20px;
		margin-left: 25px;
		font-weight: bold;
		color: black;
		text-decoration: none;
		font-size: 14px;
	}
	
	.down_main{
		margin-top:10px;
		width:1000px;
		height:200px;
		border:1px solid #ccc;
		border-top-left-radius: 5px;
		border-top-right-radius: 5px;
		border-bottom-left-radius: 5px;
		border-bottom-right-radius: 5px;
		margin-bottom: 20px;
	}
	.down_left{
		width:200px;
		height:160px;
		float: left;
		border-right:1px solid #ccc;
	}
	
	.down_left img{
		display:inline-block;
		position:relative;
		margin-top: 15px;
		outline:0px solid black;
		float: left;
		left:50px;
		cursor:pointer;
		transition:all .2s;
	}
	.down_left img:hover{
		transform:rotate3d(0,0,1,360deg);
	}
	
	.down_left div{
		width:180px;
		height:50px;
		margin-top: 80px;
		font-size: 13px;
		color: gray;
		margin-left: 10px;
	}
	
	.down_right{
		width:790px;
		height:160px;
		outline:0px solid red;
		float: right;
	}
	
	.down_right a{
		display: inline-block;
		width: 750px;
		height: 35px;
		text-indent: 10px;
		line-height: 30px;
		margin-left: 15px;
		border-bottom:1px dashed #ccc;
		color: gray;
		font-size: 14px;
		text-decoration: none;
	}
	
	.down_right a:hover{
		color:black;
		font-size: 15px;
	}
	
	#head233{
		width:1300px;
		height:40px;
		background: #454545;
	}
	#head_left233{
		width:300px;
		height:40px;
		float: left;
		margin-left:200px;
	}
	#head_left233 a {
		color:white;
		line-height: 40px;
		text-align: center;
		text-decoration: none;
		font-size: 12px;
	}
	#head_left233 a:hover{
		color:yellow;
	}
	
	#head_right233{
		width:300px;
		height:35px;
		float: right;
		margin-right:200px;
		margin-top: 7px;
	}
	#search233{
		background: #666;
		border:1px solid #1d1d1d;
		color: #d6d6d6;
		height:22px;
	}
	#submit233{
		background: #b10800;
		color:#fff;
		height:22px;
		width:52px;
		line-height: 18px;
		border:1px solid #1d1d1d;
	}
</style>
<script type="text/javascript">
	function Fsubmit(id,name,master){
		$("#sectionId").val(id);
		document.getElementById("myform").submit();
	}
	
	function submit(tid,sid){
		$("#tId").val(tid);
		$("#myf").submit();
	}
	
	function myTopic(){
		$("#myTopic").submit();
	}
</script>
</head> 
<!-- 板块全部的显示 -->
<body>
	<form action="/slsftsShowMyTopic" method="get" id="myTopic">
		<input type="hidden" id="userId" name="userId" value="1">
	</form>
	<div id="head233">
		<div id="head_left233">
			<img alt="" src="bbsSectionList.html">
			<a>${user.userName }</a>
			<a href="javascript:;" onclick="myTopic()">我的帖子</a>
		</div>

		<div id="head_right233">
			<form action="/slsftarchTopic" method="get" id="searchform">
				<table>
					<tbody><tr style="height:24px;">
						<td><input type="search" name="searTitle" id="search233"></td>
						<td><input type="submit" value="查询" id="submit233"></td>
					</tr>
				</tbody></table>
			</form>
		</div>
	</div>
	
	<form action="/slsftsTopicList?sectionId=" method="get" id="myform">
		<input type="hidden" value="" id="sectionId" name="sectionId">
		<div id="main">
			<div id="up">
				<div class="up_head">
					<div class="up_title1">快速入口</div>
					<div class="up_title2"></div>
				</div>
				<div id="up_body">
					<ul style="padding-left: 20px;height: auto;display: inline-block;">
						    <c:forEach items="${countBlock}" var="countBlock">
							<li><img alt="" src="${pageContext.request.contextPath }/front_images/common_394_icon.png">
								<a href="${pageContext.request.contextPath }/front/forum/comment/frontServlet?method=postBlock&blockId=${countBlock.blockId}" name="blockName">${countBlock.blockName}</a>
								<a href="javascript:;" style="margin-top: 5px;font-size: 12px;font-weight: normal;">帖子：${countBlock.count}&nbsp;&nbsp;版主：${countBlock.userName}</a>
							
								<>
							</c:forEach>
							
						
					</ul>
				</div>
			</div>
			<c:forEach items="${countBlock}" var="countBlock">
			<div id="down">
				<input type="hidden" value="${countBlock.blockId }">
					    <div class="down_main">
							<div class="up_head">
								<span class="up_title1" onclick="Fsubmit(10060,'辜志伟','mxl');" style="cursor: pointer;">辜志伟</span>
								<span class="up_title2"></span>
								<div onclick="Fsubmit(10060,'辜志伟','mxl');" style="float: right;width:80px;height:30px;font-size:14px;cursor:pointer;margin-top: 5px;text-align: center;line-height: 30px;">&gt;&gt; <a href="${pageContext.request.contextPath }/front/forum/comment/frontServlet?method=findPostList&blockId=${countBlock.blockId }">更多</a></div>
							</div>
							
							<div class="down_left">
								<img alt="" onclick="Fsubmit(10060,'辜志伟','mxl');" src="${pageContext.request.contextPath }/front_images/common_394_icon.png">
								<div>
									简介：${countBlock.blockBiref}
								</div>
							</div>
							
							
				
							<div class="down_right">
								
									
										<a href="#" onclick="submit(10006);">
										
										<c:choose>
								<c:when test="${countBlock.postState==1}"><a href="${pageContext.request.contextPath }/front/forum/comment/frontServlet?method=findpostById&postId=${countBlock.postId}">${countBlock.postContent}</a><input type="hidden" name="postId" value="${countBlock.postId }"></c:when>
								
							</c:choose></a>
										<br>
									
								
									
								
								
							</div>
						</div>
				
					    
				
			</div></c:forEach>
			
		</div>
	</form>
	<form action="/slsftsReplyList" method="get" id="myf">
		<input type="hidden" value="" id="tId" name="tId">
	</form>
				

</body>
</html>