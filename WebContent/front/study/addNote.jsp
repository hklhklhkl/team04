<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD><META content="IE=5.0000" http-equiv="X-UA-Compatible">

<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/jquery-2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/front_js/WdatePicker.js"></script><link href="css/WdatePicker.css" rel="stylesheet" type="text/css">
<script language="javascript" src="${pageContext.request.contextPath }/front_js/core.js"></script>
	 
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	 <TITLE></TITLE>	 
<STYLE type="text/css">
		#add{
			
			width:800px;
			height:320px;
		}
		#close{
			
			width:40px;
			float:right;
			height:50px;
			
		}
		#close>a{
			text-decoration: none;
			background-color: #EDEDED;
		}
	</STYLE>
	 
<SCRIPT type="text/javascript">
		/* function chooseSubmit(){
			var v = document.getElementById("addForm");
			if("" != 0){
				v.action="/slsft/note/update2";
				v.submit();
			}else{
				v.action="/slsft/note/add";
				v.submit();
			}
		} */
		
	</SCRIPT>
 
<META name="GENERATOR" content="MSHTML 11.00.10570.1001"></HEAD>	 
<BODY>
<FORM id="addForm" action="${pageContext.request.contextPath}/front/study/studyServlet?method=addStudy" method="post">
<DIV id="add">
<DIV id="close"><BR><A 
href="${pageContext.request.contextPath }/front/study/noteOne.jsp"><INPUT type="button" value="关闭"></A>
				 </DIV><!-- 用到了用户  id -->								 <INPUT name="userId" type="hidden" value="12">
				 <INPUT name="noteId" type="hidden" value="1">				 <BR>名字：<INPUT name="noteTitle" type="text"><BR><BR>
				 类型：<INPUT name="noteType" type="text"><BR><BR>手记：<TEXTAREA name="noteContent" rows="11" cols="90"></TEXTAREA><BR>
				 <BR><INPUT type="submit" value="上传"> <!-- style="float: right;" onclick="chooseSubmit();" type="button" value="上传"> -->
			 </DIV></FORM></BODY>
</html>