<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>角色赋权</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.js"></script>
		<style type="text/css">
			ul {
				list-style: none;
			}
			#treeMenu ul{
				margin-left: 50px;
			}
			.hidden{
				display: none;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				$("#treeMenu").children("li").children(":checkbox").click(function(){
					var flag = $(this).attr("checked");
					$(this).siblings("ul").show();
					$(this).siblings("ul").children("li").children(":checkbox").attr("checked", flag);
				}).siblings("a").click(function(){
					$(this).siblings("ul").toggle();
				});
			});
			function tj(){
				document.getElementById("fm").submit();
			}
		</script>
	</head>
<body>
	<div class="page_title">角色管理&nbsp; &gt; 角色赋权</div>
	<div class="button_bar">
		<button class="common_button" onclick="back();">返回</button>
		<button class="common_button" onclick="tj();">保存</button>
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/role/roleServlet?method=updateAuth" method="post">
	<table class="query_form_table">
		<input type="hidden" name="roleId" value="${role.roleId }"/></td>
		<tr>
			<th>角色名称</th>
			<td><input type="text" value="${role.roleName }" readonly /></td>
			<th></th>
			<td></td>
		</tr>

		<tr>
			<th>赋权</th>
			<td colspan="3">			
			<ul id="treeMenu" class="forminfo">				
				<c:forEach items="${ModuleList }" var="parentModule">
				<c:if test="${parentModule.moduleParentId == 0 }">	
					<li>
						<input type="checkbox" name = "chkItem" value="${parentModule.moduleId }" 
							<c:forEach items="${authList }" var="auth">
								<c:if test="${auth.moduleId == parentModule.moduleId }">checked="checked"</c:if>
							</c:forEach>							
						/>
						<a href="javascript:void(0);">${parentModule.moduleName }</a>						
						<c:forEach items="${ModuleList }" var="sonModule">
							<c:if test="${sonModule.moduleParentId == parentModule.moduleId }">					
							<ul class="hidden">						
							<li>
								<input type="checkbox" name = "chkItem" value="${sonModule.moduleId }" 
									<c:forEach items="${authList }" var="auth">
									<c:if test="${auth.moduleId == sonModule.moduleId }">checked="checked"</c:if>
									</c:forEach>
								/>
								<a href="javascript:void(0);">${sonModule.moduleName }</a>
							</li>							
							</ul>
							</c:if>
						</c:forEach>												
					</li>	
				</c:if>		
				</c:forEach>							
			</ul>								
			</td>
		</tr>
	</table>
	</form>
</body>
</html>