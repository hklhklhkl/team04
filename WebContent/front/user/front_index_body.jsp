<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<link href="${pageContext.request.contextPath }/front_css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/front_css/index.css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/My97DatePicker/WdatePicker.js"></script>
<script >

window.onload=function(){
	setCalendar();//显示日历
}
function setCalendar() {
	// 调用显示日历的方法."calendar"
	// calendar为要显示的位置的ID
	// 这里对应是div的ID
	WdatePicker({eCont:"body_three_left1"});
}

//图片轮播

	var index = 0;
	function showPic(a){
		clearInterval(time);
		var arr = document.getElementsByName("pic");
		var arr1 = document.getElementsByName("dian");
		for(var i = 0; i < arr1.length; i++){
			if(i == a){
				arr1[i].style.backgroundColor="#0d8fdd";
				arr[i].style.display="block";
			}else{
				arr[i].style.display = "none";
				arr1[i].style.backgroundColor = "";
			}
		}
		time = setInterval("picShow()", 1500);
	}
	function btnImg(i) {
		// 点击的时候,保存上次的数
		savePoint = index;
		// 先停止
		clearTimeout(mytime);
		// 切换图片
		index = i;
		loopImg();
	}
	function picShow(){
		var arr = document.getElementsByName("pic");
		var arr1 = document.getElementsByName("dian");
		for(var i = 0;i < arr1.length;i++){
			if(index == i){
				arr1[i].style.backgroundColor = "#0d8fdd";
				arr[i].style.display = "block";
			}else{
				arr1[i].style.backgroundColor = "";
				arr[i].style.display = "none";
			}
		}
		index++;
		if(index == 4){
			index = 0;	
		}
	}
	var time = setInterval("picShow()", 1500);
	function on() {
		var arr = document.getElementsByName("pic");
		var arr1 = document.getElementsByName("dian");
		for(var i = 0;i < arr1.length;i++){
			if(index == i){
				arr1[i].style.backgroundColor = "#0d8fdd";
				arr[i].style.display = "block";
			}else{
				arr1[i].style.backgroundColor = "";
				arr[i].style.display = "none";
			}
		}
	}
	window.onload=function(){
		myMarquee();//无缝滚动
		setCalendar();//显示日历
	}
	
	//无缝滚动1
	var mytimer2;

	function myMarquee(){
		var a = document.getElementById("body_three_right2_6");
	    a.scrollLeft+=1;
		if(a.scrollLeft > 851){
			a.scrollLeft = 0;
		}else{
			a.scrollLeft++;
		}
		mytimer2=setTimeout("myMarquee()",20);
	}
	
</script>
<style type="text/css">
.dian{
	list-style: none;
	
}

</style>
<title>body</title>
</head>
<body style="margin-left: -6px">

	<form action="#" method="post" id="myform">
		<input type="hidden" value="" id="sectionId" name="sectionId">
	</form>
	
	<form action="#" method="get" id="myf">
		<input type="hidden" value="" id="tId" name="tId">
	</form>
	
	
	
	<div id="body">
		<div id="sign">
			<c:if test="${user.userId == null }">
			 	<iframe src="${pageContext.request.contextPath }/front/user/front_login.jsp" width="200" height="300" frameborder="0" scrolling="no"></iframe>
			</c:if>
			<c:if test="${user.userId != null }">
				<iframe src="${pageContext.request.contextPath }/front/user/front_login_success.jsp?userId=${user.userId}" width="200" height="300" frameborder="0" scrolling="no"></iframe>
			</c:if>
		</div>

			<!-- 轮播图片当背景插进DIV,链接写在DIV里面 -->
			<div id="carousel" style="overflow: hidden" >
				<c:forEach items="${picList }" var="pic" varStatus="p">
				<c:if test="${p.count<=4 }">
				<div name="pic">
				<a href="javascript:;">
				<img style="width: 100%;height: 50%;" src="${pageContext.request.contextPath }/downServlet?fileName=${pic.imgPic}">
				</a>
				</div>
				</c:if>
				</c:forEach>
				<div class="cc" style="height:100px;width:20px; position: absolute; right: 230px; top: 30px;" >
						<a name="dian" onclick = "showPic(0);" style="background-color:#0d8fdd; display: block; height: 35px; width: 35px; border-radius: 15px; text-align: center; text-decoration: none" href="javascript:;">1</a>
						<a name="dian" onclick = "showPic(1);" style=" display: block; height: 35px; width: 35px; border-radius: 15px; text-align: center; text-decoration: none" href="javascript:;">2</a>
						<a name="dian" onclick = "showPic(2);" style=" display: block; height: 35px; width: 35px; border-radius: 15px; text-align: center; text-decoration: none" href="javascript:;">3</a>
						<a name="dian" onclick = "showPic(3);" style=" display: block; height: 35px; width: 35px; border-radius: 15px; text-align: center; text-decoration: none" href="javascript:;">4</a>
        		</div>
		</div>



		
		<div id="ranking">
			<li id="right1"><a href="">学分排行榜 </a></li>
			<li style="list-style:none;">
			<span style="color:red; font-weight:bold;">&nbsp;姓名</span>
			 <span style="color:red;float:right;margin-rigth:0px;font-weight:bold;">学分</span>
			</li>
			<c:forEach items="${userList }" var="list" end="5">
				<li id="oo">
				<a href="javascript:;">${list.userName }
				<span style="height:20px;float:right;">${list.userExamIntegral }</span>
				</a>
				</li>
			</c:forEach>	
		</div>
	</div>
	<div id="body_two">
		<div id="body_two_left">
			<li id="body_two_left1" class="btl"><a href="#">在线考试&gt;&gt;</a></li>
			<li id="body_two_left2" class="btl"><a href="#">成绩查询&gt;&gt;</a></li>
			<li id="body_two_left3" class="btl"><a href="${pageContext.request.contextPath }/front/friend/friend_index.jsp">在线交友</a></li>
			<li id="body_two_left4" class="btl"><a href="#">我的好友</a></li>
			<li id="body_two_left5" class="btl"><a href="#">代码上传</a></li>
		</div>
		<div id="body_two_center">
			<li><a href="">Z<br>T<br>K<br>J<br>2<br>0<br>0<br>6<br>学<br>风<br>无<br>敌
			</a></li>
		</div>
		<div id="body_two_right">

			<c:forEach items="${luntanList }" var="luntan" end="5">
				<div id="body_two_right_1">
					<div style="margin-top:25px;">
						<a href="javascript:;" onclick="Fsubmit(${luntan.blockId});">${luntan.blockName }</a>
					</div>
					<div id="xz" style="margin-top:10px;">
						<a href="javascript:;" onclick="Fsubmit(${luntan.blockId});">
						<img src="${pageContext.request.contextPath }/front_images/common_231_icon.png"></a>
					</div>
					<div style="margin-top:10px;">
						<a href="javascript:;" onclick="Fsubmit(${luntan.blockId});">帖子数:${luntan.count }</a>
					</div>
				</div>
			</c:forEach>
				
			

		</div>
	</div>
	<div id="body_three">
		<div id="body_three_left">
			<div id="body_three_left1"></div>
			<li id="body_three_left2_1">优秀学员</li>
			<div id="body_three_left2">
				<div id="body_three_left2_2">
					<c:forEach items="${userList }" var="list" end="4">
						<li id="uu"><a href="javascript:;">${list.userName }</a></li>
					</c:forEach>
						
				</div>
			</div>
		</div>
		<div id="body_three_rig">
			<div id="body_three_right">
				<li id="body_three_right_2"><a href="javascript:;">热门话题</a></li>

					<c:forEach items="${hotpostList }" var="hot">
					<li id="body_three_right_1">
					<a href="javascript:;" onclick="submit(10181);">
					${hot.block_name }		
					</a>
					<span style="float: right">
					${hot.time }
					</span>
					</li>
					</c:forEach>
					
				
			</div>
			<div id="body_three_right1">
				<li id="body_three_right_3"><a href="javascript:;">加精好帖</a></li>
					<c:forEach items="${goodList }" var="good">
					<li id="body_three_right_3_1">
					<a href="javascript:;" onclick="submit(10182);">	
							${good.postContent }
							<span style="float:right;">
							${good.postDate }
							</span>
					</a>
					</li>
					</c:forEach>
				
			</div>
			<div id="body_three_right2">
				<div id="body_three_right2_1">
					<a href="javascript:;">&nbsp;心灵鸡汤</a>
				</div>
				<div id="body_three_right2_6">
					<div id="body_three_right2_2">
						<div id="body_three_right2_2_1">
							<img width="100%" height="100%" alt="1" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_2">
							<img width="100%" height="100%" alt="2" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_3">
							<img width="100%" height="100%" alt="3" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_4">
							<img width="100%" height="100%" alt="4" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_5">
							<img width="100%" height="100%" alt="5" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_6">
							<img width="100%" height="100%" alt="6" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_7">
							<img width="100%" height="100%" alt="7" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_8">
							<img width="100%" height="100%" alt="8" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_9">
							<img width="100%" height="100%" alt="9" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
						<div id="body_three_right2_2_10">
							<img width="100%" height="100%" alt="10" src="${pageContext.request.contextPath }/front_images/common_231_icon.png">
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>


</body>
</html>