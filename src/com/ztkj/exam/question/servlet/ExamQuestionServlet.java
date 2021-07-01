package com.ztkj.exam.question.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztkj.entity.User;
import com.ztkj.exam.entity.Question;
import com.ztkj.exam.entity.QuestionOption;
import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.question.service.IQuestionService;
import com.ztkj.exam.question.service.impl.QuestionServiceImpl;
import com.ztkj.exam.questionOption.service.IQuestionOptionService;
import com.ztkj.exam.questionOption.service.impl.QuestionOptionServiceImpl;
import com.ztkj.exam.questionType.service.IQuestionTypeService;
import com.ztkj.exam.questionType.service.impl.QuestionTypeServiceImpl;
import com.ztkj.exam.subjects.service.ISubjectService;
import com.ztkj.exam.subjects.service.impl.SubjectServiceImpl;
import com.ztkj.exam.utils.Page;

/**
 * Servlet implementation class ExamQuestionServlet
 */
@WebServlet("/back/exam/examQuestionServlet")
public class ExamQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       IQuestionService questionService = new QuestionServiceImpl();
       ISubjectService subjectService = new SubjectServiceImpl();
       IQuestionTypeService questionTypeService = new QuestionTypeServiceImpl();
       IQuestionOptionService questionOptionService = new QuestionOptionServiceImpl();
   @Override
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");
   		String currentPage=request.getParameter("currentPage");
   		String pageSize = request.getParameter("pageSize");
   		String questionName = request.getParameter("questionName");
   		request.setAttribute("questionName", questionName);
   		String method = request.getParameter("method");
   		if ("updateQuestionStatus".equals(method)){
   			updateQuestionStatus(request,response);
   		}else if ("deleteQuestion".equals(method)) {
   			deleteQuestion(request,response);
		}else if ("addQuestion".equals(method)) {
   			addQuestion(request,response);
		}else if ("addQuestion2".equals(method)) {
   			addQuestion2(request,response);
		}else if ("findQuestion".equals(method)) {
   			findQuestion(request,response);
		}else if ("updateQuestion".equals(method)) {
			updateQuestion(request,response);
		}else if ("updateQuestion2".equals(method)) {
			updateQuestion2(request,response);
		}else {
   			int questionTypeId ;
   			int subjectId;
   			if (request.getParameter("questionTypeId")==null) {
   			questionTypeId=0;
		}else {
			questionTypeId = Integer.parseInt(request.getParameter("questionTypeId"));
		}
   		if (request.getParameter("subjectId")==null) {
   			subjectId=0;
		}else {
			subjectId = Integer.parseInt(request.getParameter("subjectId"));
		}
   		request.setAttribute("subjectId", subjectId);
   		request.setAttribute("questionTypeId", questionTypeId);
   		int ps = 5;
   		if(pageSize != null && !"".equals(pageSize)){
		ps=Integer.parseInt(pageSize);
   		}
   		int cp = 1;
   		if(currentPage != null && !"".equals(currentPage)){
   			cp=Integer.parseInt(currentPage);
   		}
   		Page page = new Page();
   		page.setPageSize(ps);
   		page.setCurrentPage(cp);
   		List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
    	request.setAttribute("questionTypeList", questionTypeList);
   		List<Subject> subjectList = subjectService.findAllSubject();
    	request.setAttribute("subjectList", subjectList);
   		List<Question> questionList = questionService.findAllQuestion(page,questionName,questionTypeId,subjectId);
   		request.setAttribute("questionList", questionList);
   		request.setAttribute("page",page);
   		request.getRequestDispatcher("/back/exam/question/questionList.jsp").forward(request, response);
   		}
   }
   protected void updateQuestionStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int questionId=Integer.parseInt(request.getParameter("questionId"));
	   String questionStatus=request.getParameter("questionStatus");
	   Question q = new Question();
	   q.setQuestionId(questionId);
	   q.setQuestionStatus(questionStatus);
	   PrintWriter out = response.getWriter();
	   boolean flag = questionService.updateQuestionStatus(q);
	   if (flag == true) {
			out.print("<script>alert('操作成功')</script>");
		}else{
			out.print("<script>alert('操作失败')</script>");
		}
   		out.print("<script>location='/team04/back/exam/examQuestionServlet'</script>");
   }
   protected void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int questionId=Integer.parseInt(request.getParameter("questionId"));
	   PrintWriter out = response.getWriter();
	   boolean flag = questionService.deleteQuestion(questionId);
	   if (flag == true) {
		   questionOptionService.deleteQuestionOption(questionId);
			out.print("<script>alert('删除成功')</script>");
		}else{
			out.print("<script>alert('删除失败')</script>");
		}
  		out.print("<script>location='/team04/back/exam/examQuestionServlet'</script>");
   }
   protected void addQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	List<Subject> subjectList = subjectService.findAllSubject();
	   	request.setAttribute("subjectList", subjectList);
   		List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
    	request.setAttribute("questionTypeList", questionTypeList);
    	request.getRequestDispatcher("/back/exam/question/questionAdd.jsp").forward(request, response);
   }
   protected void addQuestion2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String questionName = request.getParameter("questionName");
	   String questionAnswer = request.getParameter("questionAnswer");
	   int subjectId = Integer.parseInt(request.getParameter("subjectId"));
	   int questionTypeId= Integer.parseInt(request.getParameter("questionTypeId"));
	   PrintWriter out = response.getWriter();
	   boolean flag = false;
	   //判断题目是否完整
		  if (!"".equals(questionName)&&questionName!=null&&!"".equals(questionAnswer)&&questionAnswer!=null&&subjectId!=0&questionTypeId!=0) {
			  Question q= new Question();
			String userName = request.getParameter("userName");
			  q.setQuestionAuthor(userName);
			  q.setQuestionAnswer(questionAnswer);
			  q.setQuestionName(questionName);
			  q.setQuestionTypeId(questionTypeId);
			  q.setSubjectId(subjectId);
			  flag=questionService.addQuestion(q);
			   if (questionTypeId<=2) {
				String num[]= request.getParameterValues("num");
				int id = questionService.findQuestionId();
				  for (int i = 0; i < num.length; i++) {
			   		QuestionOption qo = new QuestionOption();
				   		 qo.setQuestionId(id);
				   		 qo.setQuestionOptionContent(num[i]);
				   		 questionOptionService.addQuestionOption(qo);
						  if(num[i].equals("")||num[i]==null){
							  questionService.deleteQuestion(id);
							  questionOptionService.deleteQuestionOption(id);
							  flag = false;
							  break;
					 }
			   	  	}
			   	  }
			   	if (flag == true) {
					out.print("<script>alert('添加成功')</script>");
					out.print("<script>location='/team04/back/exam/examQuestionServlet'</script>");
				}else{
					out.print("<script>alert('添加失败')</script>");
					out.print("<script>location='/team04/back/exam/examQuestionServlet?method=addQuestion'</script>");
			 }
			}else {
				//题目不完整
			   out.print("<script>alert('添加失败')</script>");
			   out.print("<script>location='/team04/back/exam/examQuestionServlet?method=addQuestion'</script>");
		   }
		 }
   protected void findQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int questionId = Integer.parseInt(request.getParameter("questionId"));
	   int questionTypeId = Integer.parseInt(request.getParameter("questionTypeId"));
	   Question q = null;
	   List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
	   request.setAttribute("questionTypeList", questionTypeList);
   		List<Subject> subjectList = subjectService.findAllSubject();
   		request.setAttribute("subjectList", subjectList);
   		q=questionService.findQuestionById(questionId);
   		request.setAttribute("question", q);
   		List<QuestionOption> qo = null;
		qo = questionOptionService.findQuestionOption(questionId);
	   if (questionTypeId<=2) {
		   request.setAttribute("questionOptionList", qo);
		   request.getRequestDispatcher("/back/exam/question/questionInfo.jsp").forward(request, response);
	   }else{
		   request.getRequestDispatcher("/back/exam/question/questionInfo2.jsp").forward(request, response);
			  
	}
   }
   protected void updateQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int questionId = Integer.parseInt(request.getParameter("questionId"));
	   int questionTypeId = Integer.parseInt(request.getParameter("questionTypeId"));
	   
	   Question q = null;
	   List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
	   request.setAttribute("questionTypeList", questionTypeList);
   		List<Subject> subjectList = subjectService.findAllSubject();
   		request.setAttribute("subjectList", subjectList);
   		q=questionService.findQuestionById(questionId);
   		request.setAttribute("question", q);
   		List<QuestionOption> qo = null;
		qo = questionOptionService.findQuestionOption(questionId);
	   if (questionTypeId<=2) {
		   request.setAttribute("questionOptionList", qo);
		   request.getRequestDispatcher("/back/exam/question/questionUpdate.jsp").forward(request, response);
	   }else{
		   request.getRequestDispatcher("/back/exam/question/questionUpdate.jsp").forward(request, response);
	   }	  
	
   }
   protected void updateQuestion2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String questionName = request.getParameter("questionName");
	   String questionAnswer = request.getParameter("questionAnswer");
	   int subjectId = Integer.parseInt(request.getParameter("subjectId"));
	   int questionTypeId= Integer.parseInt(request.getParameter("questionTypeId"));
	   PrintWriter out = response.getWriter();
	   int questionId=Integer.parseInt(request.getParameter("questionId"));
	   boolean flag = false;
	   boolean flag2 =false;
	   if (questionTypeId<=2) {
	    //选择题
		   String num[]= request.getParameterValues("num");
		   for (int i = 0; i < num.length; i++) {
			   if(!num[i].equals("")&&num[i]!=null){
				   flag2=true;
			   }else{
				   flag2=false;
				   flag=false;
				   break;
			   }
		   }
		   if(flag2==true){
	    	Question q = new Question();
			   q.setQuestionId(questionId);
			   q.setSubjectId(subjectId);
			   q.setQuestionTypeId(questionTypeId);
			   q.setQuestionAnswer(questionAnswer);
			   q.setQuestionName(questionName);
			   flag = questionService.updateQuestion(q);
			   questionOptionService.deleteQuestionOption(questionId);
			   for (int i = 0; i < num.length; i++) {
			   		QuestionOption qo = new QuestionOption();
				   		 qo.setQuestionId(questionId);
				   		 qo.setQuestionOptionContent(num[i]);
				   		 questionOptionService.addQuestionOption(qo);
					}
			   }
			 }else{
		//非选择题
				if (questionName==null||"".equals(questionName)||questionAnswer==null||"".equals(questionAnswer)) {
					flag=false;
					}else{
					Question q = new Question();
					q.setQuestionId(questionId);
					q.setSubjectId(subjectId);
					q.setQuestionTypeId(questionTypeId);
					q.setQuestionAnswer(questionAnswer);
					q.setQuestionName(questionName);
					questionOptionService.deleteQuestionOption(questionId);
					flag = questionService.updateQuestion(q);
					}
	   }
	   if (flag==true) {
		   out.print("<script>alert('修改成功')</script>");
		   }else{
			 out.print("<script>alert('修改失败')</script>");
			}
	   out.print("<script>location='/team04/back/exam/examQuestionServlet'</script>");
	}
 }
