package com.ztkj.exam.exam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
import com.ztkj.exam.entity.Question;
import com.ztkj.exam.entity.QuestionOption;
import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.entity.StudentExam;
import com.ztkj.exam.entity.StudentExamDetail;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.exam.service.IExamService;
import com.ztkj.exam.exam.service.IStudentExamService;
import com.ztkj.exam.exam.service.impl.ExamServiceImpl;
import com.ztkj.exam.exam.service.impl.StudentExamServiceImpl;
import com.ztkj.exam.examRuleDetail.service.IExamRuleDetailService;
import com.ztkj.exam.examRuleDetail.service.IStudentExamDetailService;
import com.ztkj.exam.examRuleDetail.service.impl.ExamRuleDetailServiceImpl;
import com.ztkj.exam.examRuleDetail.service.impl.StudentExamDetailServiceImpl;
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
 * Servlet implementation class UserExamServlet
 */
@WebServlet("/front/exam/userExamServlet")
public class UserExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IStudentExamDetailService studentExamDetailService = new StudentExamDetailServiceImpl();
	IStudentExamService studentExamService = new StudentExamServiceImpl();
	 IQuestionService questionService = new QuestionServiceImpl();
	IExamService examService = new ExamServiceImpl();
	ISubjectService subjectService = new SubjectServiceImpl();
	IExamRuleDetailService examRuleDetailService = new ExamRuleDetailServiceImpl();
    IQuestionOptionService questionOptionService = new QuestionOptionServiceImpl();
    IQuestionTypeService questionTypeService = new QuestionTypeServiceImpl();
    IExamService examRuleService = new ExamServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");
   		String method = request.getParameter("method");
   		if ("test".equals(method)) {
			testExam(request,response);
		}else if ("qx".equals(method)) {
			qx(request,response);
		}else if ("tj".equals(method)) {
			tj(request,response);
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
   		List<ExamRule> examList = examService.findAllExam(page, examName, subjectId);
   		request.setAttribute("examList",examList);
   		List<Subject> subjectList = subjectService.findAllSubject();
    	request.setAttribute("subjectList", subjectList);
    	request.setAttribute("page",page);
   		request.getRequestDispatcher("/front/testPaper.jsp").forward(request, response);
		}
    }
   
    protected void testExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int examId = Integer.parseInt(request.getParameter("examId"));
    	ExamRule examRule = examService.findExamById(examId);
    	List<Question> userQuestionList = new ArrayList<Question>();
    	List<Question> questionList = new ArrayList<Question>();
    	List<QuestionOption> questionOptionList = new ArrayList<QuestionOption>();
    	List<ExamRuleDetail> erdList = examRuleDetailService.findAllExamRuleDetailById(examId);
    	List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
    	for (int i = 0; i < erdList.size(); i++) {
    		questionList=questionService.findQuestionBySubjectIdAndType(examRule.getSubjectId(), erdList.get(i).getQuestionTypeId());
    		Collections.shuffle(questionList);
    		for (int j = 0; j < erdList.get(i).getQuestionNum(); j++) {
    			userQuestionList.add(questionList.get(j));
			}
    	}
    	for (int i = 0; i < userQuestionList.size(); i++) {
    		List<QuestionOption> a = new ArrayList<QuestionOption>();
    		if (userQuestionList.get(i).getQuestionTypeId()<=2) {
    			a =  questionOptionService.findQuestionOption(userQuestionList.get(i).getQuestionId());
    			for (int j = 0; j < a.size(); j++) {
    				questionOptionList.add(a.get(j));
    				}
    		}
    	}
    	
    	request.setAttribute("exam", examRule);
    	request.setAttribute("erdList",erdList);
    	request.setAttribute("questionTypeList", questionTypeList);
    	request.setAttribute("userQuestionList", userQuestionList);
    	request.setAttribute("questionOptionList", questionOptionList);
    	request.getRequestDispatcher("/front/examOnline.jsp").forward(request, response);
    }
    protected void qx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	out.print("<script>location='/team04/front/exam/userExamServlet'</script>");
    }
    protected void tj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int  userId = Integer.parseInt(request.getParameter("userId"));
    	List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType();
    	List<String> answer = new ArrayList<String> ();
    	String questionId[] = request.getParameterValues("questionId");
    	String questionTypeId[] = request.getParameterValues("questionTypeId");
    	String questionAnswer[] = request.getParameterValues("questionAnswer");
    	String questionScore[] = request.getParameterValues("questionFen");
    	List<String> userAnswer= new ArrayList<String>();
    	for (int i = 0; i < questionScore.length; i++) {
			
		}
    	int examId = Integer.parseInt(request.getParameter("examId"));
    	for (int i = 0; i < questionTypeId.length; i++) {
			if ("1".equals(questionTypeId[i])) {
				int num = Integer.parseInt(request.getParameter(questionId[i]));
				char a = (char)(num+64);
    			String b=a+"";
    			userAnswer.add(b);
			}else if ("2".equals(questionTypeId[i])) {
				String dx="";
				String arr[] = request.getParameterValues(questionId[i]);
				for (int j = 0; j < arr.length; j++) {
        			int num =Integer.parseInt(arr[j]);
        				char a = (char)(num+64);
    					dx+=a;
        			}userAnswer.add(dx);
			}else userAnswer.add(request.getParameter(questionId[i]));
			
		}
    	
    	ExamRule examRule = examService.findExamById(examId);
    	StudentExam studentExam = new StudentExam();
    	studentExam.setExamRuleId(examId);
    	studentExam.setUserId(userId);
    	studentExam.setExamScore(examRule.getExamScore());
    	studentExamService.addStudentExam(studentExam);
    	int seq=studentExamService.findSeq();
    	int count = 0;
    	for (int i = 0; i < questionId.length; i++) {
    		StudentExamDetail sed = new StudentExamDetail();
    		sed.setQuestionAnswer(questionAnswer[i]);
    		sed.setQuestionId(Integer.parseInt(questionId[i]));
    		sed.setStudentExamId(seq);
    		sed.setUserAnswer(userAnswer.get(i));
    		if (questionAnswer[i].equals(userAnswer.get(i))&&!"5".equals(questionTypeId[i])) {
    			sed.setUserScore(Integer.parseInt(questionScore[i]));
    			count+=Integer.parseInt(questionScore[i]);
			}studentExamDetailService.addStudentExamDetail(sed);
		}
    	studentExamService.jiaFen2(seq, count);
    	//修改科目题目状态
    	int subjectId=examRule.getSubjectId();
    	Subject subject = new Subject();
    	subject.setSubjectId(subjectId);
    	subject.setSubjectStatus("0");
    	subjectService.updateSubject(subject);
    	for (int i = 0; i < questionId.length; i++) {
			Question question = new Question();
			question.setQuestionId(Integer.parseInt(questionId[i]));
			question.setQuestionStatus("0");
			questionService.updateQuestionStatus(question);
		}ExamRule er = new ExamRule();
		er.setExamId(examId);
		er.setStatus("0");
		examService.updateExamStatus(er);
    	 //跳转页面显示正确答案
    	List<Question> userQuestionList = new ArrayList<Question>();
    	for (int i = 0; i < questionId.length; i++) {
    		userQuestionList.add(questionService.findQuestionById(Integer.parseInt(questionId[i])));
		}
    	List<QuestionOption> questionOptionList = new ArrayList<QuestionOption>();
    	for (int i = 0; i < userQuestionList.size(); i++) {
    		List<QuestionOption> a = new ArrayList<QuestionOption>();
    		if (userQuestionList.get(i).getQuestionTypeId()<=2) {
    			a =  questionOptionService.findQuestionOption(userQuestionList.get(i).getQuestionId());
    			for (int j = 0; j < a.size(); j++) {
    				questionOptionList.add(a.get(j));
    				}
    		}
    	}
    	List<StudentExamDetail> sedList = studentExamDetailService.findQuestion(seq);
    	request.setAttribute("sedList", sedList);
    	List<ExamRuleDetail> erdList = examRuleDetailService.findAllExamRuleDetailById(examId);
    	request.setAttribute("count", count);
    	request.setAttribute("exam", examRule);
    	request.setAttribute("erdList",erdList);
    	request.setAttribute("questionTypeList", questionTypeList);
    	request.setAttribute("userQuestionList", userQuestionList);
    	request.setAttribute("questionOptionList", questionOptionList);
    	request.getRequestDispatcher("/front/examOnline2.jsp").forward(request, response);
        
    }  
   
}
