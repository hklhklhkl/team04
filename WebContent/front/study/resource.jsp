<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD><META content="IE=5.0000" http-equiv="X-UA-Compatible">

<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script><link href="css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
	 
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	 <TITLE></TITLE> 
<STYLE type="text/css">
		#notec{
			
			width:815px;
			height:320px;
		}
		
		#content{
			width:680px;
			margin:auto;
		}
		table {
			background-color: #EFEFEF;
			border-radius:5px;
		}
		th,td {
			height:30px;
			background-color: white;
		}
		tbody>tr:nth-child(odd) td {
			background-color: #EFEFEF;
		}
		tbody>tr:nth-child(even) td {
			background-color: #88CCF1;
		}
		tbody>tr:hover td {
			background-color: #75B9E6;
			cursor: pointer;
		}
		tfoot a{
			font-size: 12px;
			color: black;
			text-decoration: none;
			display: inline-block;
			margin-left:10px;
		}
		#inputgo{
			width:30px;
		}
		#deleter{
			text-decoration: none;
			color:black;
			font-size: 12px;
			
		}
		#deleter:hover{
			color:red;
		}
		#dowload{
			text-decoration: none;
			color:black;
			font-size: 12px;
		}
		#dowload:hover{
			color:blue;
		}
	</STYLE>
 
<SCRIPT type="text/javascript">

	

	function setPage () {
		
		var v = document.getElementById("inputgo").value;
		if (v == "" || isNaN(v)) {
			alert("请输入正确的数值");
		}else  {
			v = parseInt(v);
			if (v < 1) {
				alert("请输入正确的数值");
			}else if (v > "2") {
				alert("请输入正确的数值");	
			}else {
				goPage2(v);
			}
		}
	}
	
	function keyEnter () {
		if (event.keyCode == 13){
			setPage();
		}
	}
	</SCRIPT>
 
<META name="GENERATOR" content="MSHTML 11.00.10570.1001"></HEAD>	 
<BODY>
<DIV id="notec"><BR>
<FORM name="myfm" id="myfm" action="${pageContext.request.contextPath}/front/study/resourceServlet?method=findResourceByPageLike " method="post">

						&nbsp; 文件名：<INPUT name="exploreName" id="exploreName" type="text">						 <SPAN 
style="margin-left: 300px;"></SPAN>						 <BR><BR>
						&nbsp;时&nbsp;间：<INPUT name="startTime2"  type="date">
						&nbsp; - &nbsp;	<INPUT name="endTime2" type="date">
						 <input id="newPageSize" type="hidden" name="pageSize">
						<input id="currentPage" type="hidden" name="currentPage">
						<INPUT style="margin-right: 50px; float: right;" type="submit" value="查询">
						 <INPUT style="margin-right: 10px; float: right;" type="reset" value="重置">
					 </FORM><BR>
	<script type="text/javascript">
	function go(currentPage) {
		
			var totalPage = "${page.totalPage}"
			if(currentPage > totalPage||currentPage < 1){
					alert("数据不合法！！！")
			}else{

				document.getElementById("currentPage").value = currentPage;
				var exploreName="${page.entity.exploreName }";
				document.getElementById("exploreName").value = exploreName; 
				 document.getElementById("myfm").submit(); 
			}
			
	}
	</script>	
<TABLE width="800" border="1" cellspacing="0">
  <THEAD>
  <TR height="30">
    <TD align="center">序号</TD>
    <TD align="center">文件名</TD>
    <TD align="center">类别</TD>
    <TD align="center">描述</TD>
    <TD align="center">上传日期</TD>
    <TD align="center">下载</TD>
    <TD align="center"> 删除</TD></TR></THEAD>
  <TBODY>
  <c:forEach items="${page.list}" var="resource">
  <TR>
    <TD>${resource.exploreId}</TD>
    <TD align="center">${resource.exploreName}</TD>
    <TD align="center">${resource.exploreTypeId} </TD>
    <TD align="center" style="width: 180px;">${resource.exploreDes}</TD>
    <TD>${resource.exploreDate}</TD>
    <TD align="center"><A id="dowload" href="${pageContext.request.contextPath}/front/study/rDownServlet?exploreUri=${resource.exploreUri}">下载</A>
      								 </TD>
    <TD align="center">
    <A id="deleter"  href="${pageContext.request.contextPath}/front/study/resourceServlet?method=deleteResourceById&exploreId=${resource.exploreId}">
      									删除</A>								 </TD></TR>
     </c:forEach> 									
</TBODY>
  <TFOOT>
  <TR>
    <TD colspan="3">当前第${page.currentPage }页，共${page.totalPage }页			共${page.totalCount}条数据							 </TD>
    <TD colspan="4"><input type="button" onclick="go(${page.firstPage})" value ="首页"> 
				<input type="button" onclick="go(${page.forwardPage})" value ="上一页"> 
				<input type="button" onclick="go(${page.nextPage})" value ="下一页"> 
				<input type="button" onclick="go(${page.lastPage})" value ="尾页"> 
				
      																		 
      <DIV style="float: right;">							
      									<input style="width:30px" type="text" id="inp"/>
      									<a href="javascript:void(0)" onclick="go(document.getElementById('inp').value)">go</a>
      									 </DIV></TD></TR></TFOOT></TABLE></DIV></BODY>
</html>