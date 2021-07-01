<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script><link href="css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title></title>
	<style type="text/css">
		#add{
			outline:2px solid #EFEFEF;
			border-radius:5px;
			width:310px;
			height:445px;
		}
		#img22{
			
		}
		#close{
			width:40px;
			float:right;
			height:50px;
			margin-right: 10px;
			margin-right: 30px;
		}
		#close>a{
			text-decoration: none;
			background-color: #EDEDED;
		}
	</style>
	<!-- <script type="text/javascript">
		function subForm() {
			var fm = document.getElementById("myform");
			fm.action = "/slsft/learn/upload";
			fm.submit();
		}
	</script> -->
</head>
	<body>
		<form enctype="multipart/form-data" id="myform" action="${pageContext.request.contextPath}/front/study/resourceServlet?method=addResource " method="post">
			<div id="add">
				<div id="img22"></div>
				<div id="close">
					<br/>
					<a href="${pageContext.request.contextPath }/front/study/img.jsp"><input type="button" value="关闭"/></a>
				</div>
				<br/><br/>
				&nbsp;类型：
				<select name="exploreTypeId">

						<option  value="10040"> servlrt</option>
					
						<option value="1"> html</option>
					
						<option value="2"> css</option>
					
						<option value="3"> js</option>
					
						<option value="10">   jsp</option>
					
						<option value="10021"> sql</option>
				</select><br/><br/>
				<input type="hidden" name="exploreState" value="0" />
				&nbsp;名称：<input type="text" name="exploreName" value="" /><br/><br/>
				&nbsp;描述：<textarea rows="15" name="exploreDes" cols="28"></textarea><br/><br/>
				&nbsp;资源：<input name="exploreUri" type="file"/><br/><br/>
				<input  value="上传" type="submit" style="float:right;margin-right: 30px;"/><!-- onclick="subForm();" -->
			</div>
			
		</form>
	
	</body>
</html>