<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/back/css/main.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
		
		<script type="text/javascript">			
			var userNickname;
			var userName;
			var userState;
			var roleId;
			function go(currentPage){
				//在js中用EL表达式获取域对象中的数据  先定义全局变量  再使用双引号包裹EL表达式
				totalPage = "${page.totalPage}";
				if(currentPage > totalPage || currentPage < 1){
					alert("当前值数据不合法！！");
				}else{
					document.getElementById("currentPage").value=currentPage;
					userNickname = "${page.entity.userNickname}";
					userName = "${page.entity.userName}";
					userState = "${page.entity.userState}";
					roleId = "${roleId}";
					document.getElementById("userNickname").value=userNickname;
					document.getElementById("userName").value=userName;
					document.getElementById("userState").value=userState;
					document.getElementById("roleId").value=roleId;
					//通过js提交表单
					document.getElementById("fm").submit();  
				}				
			}
			function tj(){
				document.getElementById("fm").submit();
			}
			function bj(){
				document.getElementById("fm1").submit();
			}
		</script>
	</head>
<body>
	<div class="page_title">用户管理</div>
	<div class="button_bar">		
		<button class="common_button" onclick="window.location.href='${pageContext.request.contextPath }/back/system/user/userAdd.jsp'">新建</button>
		<button class="common_button" onclick="tj()">查询</button>		
	</div>
	<form id="fm" action="${pageContext.request.contextPath}/back/sys/user/userServlet?method=findUserByPageLike" method="post">
	<table class="query_form_table">
		<tr>
			<th>姓名</th>
			<td><input type="text" id="userNickname" name="userNickname" value="${page.entity.userNickname}"></td>
			<th>角色</th>
			<td>
				<select name="roleId" id="roleId">
					<option value="">选择</option>
					<c:forEach items="${roleList }" var="role">
						<option value="${role.roleId }" 
						<c:if test="${page.entity.roleId == role.roleId }">
							selected='selected'
						</c:if> >
						${role.roleName }
						</option>						
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>账号</th>
			<td><input type="text" id="userName" name="userName" value="${page.entity.userName}"></td>
			<th>状态</th>
			<td>
				<select name="userState" id="userState">
					<option value="">请选择</option>
					<option value="1" 
						<c:if test="${page.entity.userState == 1 }">selected='selected'</c:if> >
					正常</option>	
					<option value="0" 
						<c:if test="${page.entity.userState == 0 }">selected='selected'</c:if> >
					注销</option>					
				</select>
			</td>
		</tr>
	</table>
		<input id="currentPage" type="hidden" value="" name="currentPage">
	</form>	
	<br />
	<table class="data_list_table">
		<tr>
			<th>编号</th>
			<th>头像</th>
			<th>姓名</th>
			<th>账号</th>
			<th>角色</th>
			<th>性别</th>
			<th>出生日期</th>
			<th>论坛积分</th>
			<th>考试学分</th>
			<th>状态</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list }" var="user">
		<tr>
			<td class="list_data_number">${user.userId }</td>
			<td class="list_data_number"><img width="80px" height="55px" src="${pageContext.request.contextPath }/downServlet?fileName=${user.userUri}"/></td>		
			<td class="list_data_text">${user.userNickname }</td> 
			<td class="list_data_text">${user.userName }</td>
			<td class="list_data_text">
			<c:forEach items="${roleList }" var="role">
				<c:if test="${user.roleId == role.roleId }">${role.roleName }</c:if>
			</c:forEach>
			</td>
			<td class="list_data_text">${user.userSex }</td>
			<td class="list_data_text">${user.userBirth }</td>
			<td class="list_data_text">${user.userForumIntegral }</td>
			<td class="list_data_text">${user.userExamIntegral }</td>
			<td class="list_data_text">
			<c:choose>
				<c:when test="${user.userState == 1 }">正常</c:when>
				<c:otherwise>注销</c:otherwise>
			</c:choose>
			</td>
			<td class="list_data_text">
				<c:forEach items="${userList }" var = "user2">
					<c:if test="${user2.userId==user.userCreatorId }">
						${user2.userName }
					</c:if>
				</c:forEach>		
			</td>
			<td class="list_data_text">${user.userCreateDate }</td>
			<td class="list_data_text">
				<a href="/team04/back/sys/user/userServlet?method=findUserById&userId=${user.userId }&cz=bj">编辑</a>
				<c:choose>
					<c:when test="${user.userState == 1}">
						<a href="${pageContext.request.contextPath}/back/sys/user/userServlet?method=deleteUserInfoById&userId=${user.userId }">注销</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/back/sys/user/userServlet?method=deleteUserInfoById&userId=${user.userId }">恢复</a>
					</c:otherwise>
				</c:choose>
				<a href="/team04/back/sys/user/userServlet?method=findUserById&userId=${user.userId }&cz=czmm">重置密码</a>	
			</td>
		</tr>
		</c:forEach>
		<tr>
			<th colspan="13">
				<div class="pager">
					<div class="pager_left">
						共${page.totalCount }条记录 每页${page.pageSize }条
						第${page.currentPage }页/共${page.totalPage }页
						转到<input value="1" id="inp" size="1" />页
						<button width="20" onclick="go(document.getElementById('inp').value);">GO</button>
					</div>
					<div class="pager_right">
						<button class="common_button" onclick="go(${page.firstPage})">首页</button>
						<button class="common_button" onclick="go(${page.forwardPage})">上一页</button>
						<button class="common_button" onclick="go(${page.nextPage})">下一页</button>
						<button class="common_button" onclick="go(${page.lastPage})">尾页</button>
					</div>
				</div>
			</th>
		</tr>
	</table>
</body>
</html>