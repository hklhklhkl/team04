package com.ztkj.exam.exam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.entity.Question;
import com.ztkj.exam.entity.StudentExam;
import com.ztkj.exam.entity.StudentExamDetail;
import com.ztkj.exam.exam.service.IExamService;
import com.ztkj.exam.exam.service.IStudentExamService;
import com.ztkj.exam.exam.service.impl.ExamServiceImpl;
import com.ztkj.exam.exam.service.impl.StudentExamServiceImpl;
import com.ztkj.exam.examRuleDetail.service.IStudentExamDetailService;
import com.ztkj.exam.examRuleDetail.service.impl.StudentExamDetailServiceImpl;
import com.ztkj.exam.question.service.IQuestionService;
import com.ztkj.exam.question.service.impl.QuestionServiceImpl;

import com.ztkj.exam.utils.Page;
import com.ztkj.exam.vo.ExamPg;
import com.ztkj.exam.vo.QuestionPg;
import com.ztkj.sys.user.service.IUserService;
import com.ztkj.sys.user.service.impl.UserServiceImpl;

/**
 * Servlet implementation class PgExamServlet
 */
@WebServlet("/back/exam/pgExamServlet")
public class PgExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       IStudentExamService studentExamService = new StudentExamServiceImpl();
       IUserService userService = new UserServiceImpl();
       IStudentExamDetailService studentExamDetailService = new StudentExamDetailServiceImpl();
       IQuestionService questionService = new QuestionServiceImpl();
       IExamService examRuleService = new ExamServiceImpl();
       
       @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");
   		String method = request.getParameter("method");
   		if ("pg".equals(method)) {
			pg(request,response);
		}else if ("jiaFen".equals(method)) {
			jiaFen(request,response);
		}else{
   		String currentPage=request.getParameter("currentPage");
   		String pageSize = request.getParameter("pageSize");
   		String userName = request.getParameter("userName");
   		String examName = request.getParameter("examName");
   		request.setAttribute("examName", examName);
   		request.setAttribute("userName", userName);
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
   		List<StudentExam> studentExamList = studentExamService.findAll(page, userName, examName);
   		List<ExamPg> examPgList = new ArrayList<ExamPg>();
   		for (int i = 0; i < studentExamList.size(); i++) {
   			ExamPg examPg = new ExamPg();
   			examPg.setId(studentExamList.get(i).getStudentExamId());
   			examPg.setName(userService.findNameById(studentExamList.get(i).getUserId()).getUserName());
   			examPg.setSubject(studentExamService.findSubjectName(studentExamList.get(i).getStudentExamId()).getSubjectName());
   			examPg.setExam(studentExamService.findExamName(studentExamList.get(i).getExamRuleId()).getExamName());
   			examPg.setDate(studentExamList.get(i).getCreateDate());
   			examPgList.add(examPg);
   		}
   		request.setAttribute("page",page);
   		request.setAttribute("examPgList", examPgList);
   		request.getRequestDispatcher("/back/exam/correct/correctList.jsp").forward(request, response);
		}
    }
    protected void pg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("studentExamId"));
    	StudentExam studentExam = studentExamService.findStudentExamById(id);
    	ExamPg examPg = new ExamPg();
			examPg.setName(userService.findNameById(studentExam.getUserId()).getUserName());
			examPg.setSubject(studentExamService.findSubjectName(studentExam.getStudentExamId()).getSubjectName());
			examPg.setExam(studentExamService.findExamName(studentExam.getExamRuleId()).getExamName());
			request.setAttribute("examPg",examPg);
			List<StudentExamDetail> studentExamDetailList = studentExamDetailService.findQuestion(id);
			int score = studentExamService.findQuestionScore(id);
			List<QuestionPg> questionPg = new ArrayList<QuestionPg>();
			for (int i = 0; i < studentExamDetailList.size(); i++) {
				Question question = questionService.findQuestionById(studentExamDetailList.get(i).getQuestionId());
				if (question.getQuestionTypeId()==5) {
					QuestionPg q = new QuestionPg();
					q.setQuestionId(studentExamDetailList.get(i).getQuestionId());
					q.setQuestionName(question.getQuestionName());
					q.setQuestionAnswer(studentExamDetailList.get(i).getQuestionAnswer());
					q.setUserAnswer(studentExamDetailList.get(i).getUserAnswer());
					questionPg.add(q);
				}
			}
			request.setAttribute("id", id);
			request.setAttribute("questionPg", questionPg);
			request.setAttribute("score", score);
			
			request.getRequestDispatcher("/back/exam/correct/correct.jsp").forward(request, response);
    	
    }
    protected void jiaFen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean flag2=true;
    	boolean flag = false;
    	PrintWriter out = response.getWriter();
    	int id = Integer.parseInt(request.getParameter("id"));
    	String arr[] = request.getParameterValues("kgFen");
    	int fen=0;
    	if(arr == null ){
    		fen=0;
    	}else{
    	for (int i = 0; i < arr.length; i++) {
    		fen+=Integer.parseInt(arr[i]);
			}
    	}
    	StudentExam studentExam = studentExamService.findStudentExamById(id);
    	
    	StudentExam studentExam2 = studentExamService.findStudentCredit(studentExam.getUserId(), studentExam.getExamRuleId());
    	if (studentExam2!=null&&studentExam.getStudentExamId()!=studentExam2.getStudentExamId()) {
    		int fen2 = studentExam2.getUserScore()+studentExam2.getUserScore2();
        	int fen3 = studentExam.getUserScore()+fen;
        	System.out.println(fen2);
        	System.out.println(fen3);
        	if (fen2<=fen3) {
        		studentExamService.deleteStudentExam(studentExam2.getStudentExamId());
        		studentExamDetailService.deleteStudentExamDetail(studentExam2.getStudentExamId());
			}else {
				flag2=false;
				boolean fla = studentExamService.deleteStudentExam(studentExam.getStudentExamId());
				if (fla==true) {
					studentExamDetailService.deleteStudentExamDetail(studentExam.getStudentExamId());
				}
			
			}flag = true;
        }
    	if (flag2==true) {
    	flag = studentExamService.jiaFen(id, fen);
    	StudentExam se = studentExamService.findStudentExamById(id);
    	ExamRule examRule = examRuleService.findExamById(se.getExamRuleId());
    	Double examScore = (double) se.getExamScore();
    	Double userAllScore = (double)(se.getUserScore()+se.getUserScore2());
    	if (examScore*0.6<=userAllScore) {
    		studentExamService.jiaCredit(id, examRule.getExamCredit());
    	}
    	}
    	if (flag == true) {
			out.print("<script>alert('批改成功')</script>");
		}else{
			out.print("<script>alert('批改失败')</script>");
		}
  		out.print("<script>location='/team04/back/exam/pgExamServlet'</script>");
  		studentExamService.addUserCredit(studentExam.getUserId());
  		} 
}
