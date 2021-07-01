package com.ztkj.exam.exam.servlet;

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
import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.entity.ExamRuleDetail;
import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.exam.service.IExamService;
import com.ztkj.exam.exam.service.impl.ExamServiceImpl;
import com.ztkj.exam.examRuleDetail.service.IExamRuleDetailService;
import com.ztkj.exam.examRuleDetail.service.impl.ExamRuleDetailServiceImpl;
import com.ztkj.exam.question.service.IQuestionService;
import com.ztkj.exam.question.service.impl.QuestionServiceImpl;
import com.ztkj.exam.questionType.service.IQuestionTypeService;
import com.ztkj.exam.questionType.service.impl.QuestionTypeServiceImpl;
import com.ztkj.exam.subjects.service.ISubjectService;
import com.ztkj.exam.subjects.service.impl.SubjectServiceImpl;
import com.ztkj.exam.utils.Page;

/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/back/exam/examServlet")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		IQuestionTypeService questionTypeService = new QuestionTypeServiceImpl();
       IExamService examService = new ExamServiceImpl();
       ISubjectService subjectService = new SubjectServiceImpl();
       IExamRuleDetailService examRuleDetailService = new ExamRuleDetailServiceImpl();
       IQuestionService questionService = new QuestionServiceImpl();
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");
   		String method = request.getParameter("method");
   		if ("deleteExam".equals(method)) {
			deleteExam(request,response);
		}else if ("updateExamStatus".equals(method)) {
			updateExamStatus(request,response);
		}else if ("findExamById".equals(method)) {
			findExamById(request,response);
		}else if ("addExam".equals(method)) {
			addExam(request,response);
		}else if ("addExam2".equals(method)) {
			addExam2(request,response);
		}else if ("updateExam".equals(method)) {
			updateExam(request,response);
		}else if ("updateExam2".equals(method)) {
			updateExam2(request,response);
		}else{
   		String currentPage=request.getParameter("currentPage");
   		String pageSize = request.getParameter("pageSize");
   		String examName = request.getParameter("examName");
   		request.setAttribute("examName", examName);
   		int subjectId;
   		if (request.getParameter("subjectId")==null) {
   			subjectId=0;
		}else {
			subjectId = Integer.parseInt(request.getParameter("subjectId"));
		}
   		request.setAttribute("subjectId", subjectId);
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
   		request.setAttribute("questionTypeList",questionTypeList);
   		List<ExamRule> examList = examService.findAllExam(page, examName, subjectId);
   		request.setAttribute("examList",examList);
   		List<ExamRuleDetail> examRuleDetailList = examRuleDetailService.findAllExamRuleDetail();
   		request.setAttribute("examRuleDetailList", examRuleDetailList);
   		List<Subject> subjectList = subjectService.findAllSubject();
    	request.setAttribute("subjectList", subjectList);
    	request.setAttribute("page",page);
   		request.getRequestDispatcher("/back/exam/testPaper/testPaperList.jsp").forward(request, response);
		}
    }

	protected void deleteExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = false;
		PrintWriter out = response.getWriter();
		int examId=Integer.parseInt(request.getParameter("examId"));
		flag = examService.deleteExam(examId);
		if (flag == true) {
			out.print("<script>alert('删除成功')</script>");
		}else{
			out.print("<script>alert('删除失败')</script>");
		}
  		out.print("<script>location='/team04/back/exam/examServlet'</script>");
		
	}
	protected void updateExamStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int examId=Integer.parseInt(request.getParameter("examId"));
		String examStatus=request.getParameter("examStatus");
		ExamRule exam = new ExamRule();
		exam.setExamId(examId);
		exam.setStatus(examStatus);
		PrintWriter out = response.getWriter();
		boolean flag = false;
		flag = examService.updateExamStatus(exam);
		if (flag == true) {
			out.print("<script>alert('操作成功')</script>");
		}else{
			out.print("<script>alert('操作失败')</script>");
		}
  		out.print("<script>location='/team04/back/exam/examServlet'</script>");
	}	
	protected void findExamById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int examId=Integer.parseInt(request.getParameter("examId"));
		ExamRule exam = examService.findExamById(examId);
		request.setAttribute("exam", exam);
   		List<Subject> subjectList = subjectService.findAllSubject();
    	request.setAttribute("subjectList", subjectList);
    	List<ExamRuleDetail> examRuleDetailList = examRuleDetailService.findAllExamRuleDetailById(examId);
   		request.setAttribute("examRuleDetailList", examRuleDetailList);
   		List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
   		request.setAttribute("questionTypeList",questionTypeList);
   		request.getRequestDispatcher("/back/exam/testPaper/testPaperInfo.jsp").forward(request, response);
	}
	protected void addExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Subject> subjectList = subjectService.findAllSubject();
    	request.setAttribute("subjectList", subjectList);
    	request.getRequestDispatcher("/back/exam/testPaper/testPaperAdd.jsp").forward(request, response);
   	}
	protected void addExam2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		boolean flag = false;
		int count = 0;
		if (!"0".equals(request.getParameter("subjectId"))&&request.getParameter("examTime")!=null&&!"".equals(request.getParameter("examTime"))&&!"".equals(request.getParameter("examCredit"))&&request.getParameter("examScore")!=null&&!"".equals(request.getParameter("examScore"))&&request.getParameter("examCredit")!=null&&request.getParameter("examName")!=null&&!"".equals(request.getParameter("examName"))) {
			int subjectId = Integer.parseInt(request.getParameter("subjectId"));
			String examName = request.getParameter("examName");
			int examTime = Integer.parseInt(request.getParameter("examTime"));
			int examScore=Integer.parseInt(request.getParameter("examScore"));
			int examCredit = Integer.parseInt(request.getParameter("examCredit"));
			if (examTime>0||examScore>0||examCredit>0) {
				ExamRule exam = new ExamRule();
				exam.setExamAuthor(userName);
				exam.setExamCredit(examCredit);
				exam.setExamName(examName);
				exam.setExamTime(examTime);
				exam.setSubjectId(subjectId);
				exam.setExamScore(examScore);
				String questionNum[] = request.getParameterValues("questionNum");
				String score[] = request.getParameterValues("score");
				for (int i = 0; i < questionNum.length; i++) {
					int num = questionService.findCountBySubjectId(subjectId, i+1);
					if (Integer.parseInt(questionNum[i])<=num||Integer.parseInt(score[i])==0) {
						count++;
					}else{count=0;break;}
				}
				if (count==0) {
					out.print("<script>alert('题目不够')</script>");
					out.print("<script>location='/team04/back/exam/examServlet?method=addExam'</script>");
				}else{
				flag = examService.addExam(exam);
				int id= examService.findSeq();
				for (int i = 0; i < score.length; i++) {
					if (!"0".equals(questionNum[i])&&!"0".equals(score[i])&&!"".equals(questionNum[i])&&!"".equals(score[i])) {
						int questionNum1 = Integer.parseInt(questionNum[i]);
						int score1 = Integer.parseInt(score[i]);
						ExamRuleDetail erd = new ExamRuleDetail();
						erd.setExamRuleId(id);
						erd.setQuestionTypeId(i+1);
						erd.setQuestionNum(questionNum1);
						erd.setScore(score1);
						flag = examRuleDetailService.addExamRuleDetailById(erd);
					}
				}
				}
				if (flag==true) {
					out.print("<script>alert('添加成功')</script>");
					out.print("<script>location='/team04/back/exam/examServlet'</script>");
				
				}
			}else{
				out.print("<script>alert('添加失败')</script>");
				out.print("<script>location='/team04/back/exam/examServlet?method=addExam'</script>");
			}
		}else{
			out.print("<script>alert('添加失败')</script>");
			out.print("<script>location='/team04/back/exam/examServlet?method=addExam'</script>");
		}
		
		
	}
	protected void updateExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int examId=Integer.parseInt(request.getParameter("examId"));
		int status=Integer.parseInt(request.getParameter("status"));
		if (status==1) {
			out.print("<script>alert('请先禁用')</script>");
			out.print("<script>location='/team04/back/exam/examServlet'</script>");
		
		}else{
		ExamRule exam = examService.findExamById(examId);
		request.setAttribute("exam", exam);
   		List<Subject> subjectList = subjectService.findAllSubject();
    	request.setAttribute("subjectList", subjectList);
    	List<ExamRuleDetail> examRuleDetailList = examRuleDetailService.findAllExamRuleDetailById(examId);
   		request.setAttribute("examRuleDetailList", examRuleDetailList);
   		List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
   		request.setAttribute("questionTypeList",questionTypeList);
   		request.getRequestDispatcher("/back/exam/testPaper/testPaperUpdate.jsp").forward(request, response);
		}
	}
	protected void updateExam2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int examId=Integer.parseInt(request.getParameter("examId"));
		boolean flag = false;
		int count = 0;
		if (!"0".equals(request.getParameter("subjectId"))&&request.getParameter("examTime")!=null&&!"".equals(request.getParameter("examTime"))&&!"".equals(request.getParameter("examCredit"))&&request.getParameter("examScore")!=null&&!"".equals(request.getParameter("examScore"))&&request.getParameter("examCredit")!=null&&request.getParameter("examName")!=null&&!"".equals(request.getParameter("examName"))) {
			int subjectId = Integer.parseInt(request.getParameter("subjectId"));
			String examName = request.getParameter("examName");
			int examTime = Integer.parseInt(request.getParameter("examTime"));
			int examScore=Integer.parseInt(request.getParameter("examScore"));
			int examCredit = Integer.parseInt(request.getParameter("examCredit"));
			if (examTime>0||examScore>0||examCredit>0) {
				ExamRule exam = new ExamRule();
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("user");
				exam.setExamAuthor("admin");
				exam.setExamCredit(examCredit);
				exam.setExamName(examName);
				exam.setExamTime(examTime);
				exam.setSubjectId(subjectId);
				exam.setExamScore(examScore);
				exam.setExamId(examId);
				String questionNum[] = request.getParameterValues("questionNum");
				String score[] = request.getParameterValues("score");
				for (int i = 0; i < questionNum.length; i++) {
					int num = questionService.findCountBySubjectId(subjectId, i+1);
					if (Integer.parseInt(questionNum[i])<=num||Integer.parseInt(score[i])==0) {
						count++;
						System.out.println(Integer.parseInt(questionNum[i]));
					}else {count=0;break;}
				}
				if (count==0) {
					out.print("<script>alert('题目不够')</script>");
					out.print("<script>location='/team04/back/exam/examServlet?method=addExam'</script>");
				}else{
				flag = examService.updateExam(exam);
				examRuleDetailService.deleteExamRuleDetail(examId);
				for (int i = 0; i < score.length; i++) {
					if (!"0".equals(questionNum[i])&&!"0".equals(score[i])&&!"".equals(questionNum[i])&&!"".equals(score[i])) {
						int questionNum1 = Integer.parseInt(questionNum[i]);
						int score1 = Integer.parseInt(score[i]);
						ExamRuleDetail erd = new ExamRuleDetail();
						erd.setExamRuleId(examId);
						erd.setQuestionTypeId(i+1);
						erd.setQuestionNum(questionNum1);
						erd.setScore(score1);
						flag = examRuleDetailService.addExamRuleDetailById(erd);
					}
					}
				}
				if (flag==true) {
					out.print("<script>alert('修改成功')</script>");
					out.print("<script>location='/team04/back/exam/examServlet'</script>");
				
				}
			}else{
				out.print("<script>alert('修改失败')</script>");
				out.print("<script>location='/team04/back/exam/examServlet</script>");
			}
		}else{
			out.print("<script>alert('修改失败')</script>");
			out.print("<script>location='/team04/back/exam/examServlet</script>");
		}
	}

}
