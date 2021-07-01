<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>左边菜单</title>
<link href="css/style-1.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>在线学习管理系统</div>
    <dl class="leftmenu">
    	<c:forEach items="${moduleList1 }" var="parentModule"> 
    	<c:if test="${parentModule.moduleParentId == 0 }">
			<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>
				${parentModule.moduleName }				
			</div>
				<ul class="menuson">
				<c:forEach items="${moduleList1 }" var="sonModule">
					<c:if test="${sonModule.moduleParentId == parentModule.moduleId }">
					<li><cite></cite>
						<a href="${pageContext.request.contextPath }${sonModule.moduleUrl}" target="rightFrame">${sonModule.moduleName }</a>
					</li>
					</c:if>	
				</c:forEach>
				</ul>
			</dd>
		</c:if>	
		</c:forEach>		
    </dl>
</body>
</html>