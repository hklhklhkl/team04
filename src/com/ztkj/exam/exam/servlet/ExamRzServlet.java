package com.ztkj.exam.exam.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.exam.entity.StudentExam;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.exam.service.IStudentExamService;
import com.ztkj.exam.exam.service.impl.StudentExamServiceImpl;
import com.ztkj.exam.examRuleDetail.service.IStudentExamDetailService;
import com.ztkj.exam.examRuleDetail.service.impl.StudentExamDetailServiceImpl;
import com.ztkj.exam.subjects.service.ISubjectService;
import com.ztkj.exam.subjects.service.impl.SubjectServiceImpl;
import com.ztkj.exam.utils.Page;
import com.ztkj.exam.vo.ExamRz;
import com.ztkj.sys.user.service.IUserService;
import com.ztkj.sys.user.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ExamRzServlet
 */
@WebServlet("/back/exam/examRzServlet")
public class ExamRzServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ISubjectService subjectService = new SubjectServiceImpl();
	IStudentExamService studentExamService = new StudentExamServiceImpl();
	IUserService userService = new UserServiceImpl();
    IStudentExamDetailService studentExamDetailService = new StudentExamDetailServiceImpl();
    
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");
   		
   		String currentPage=request.getParameter("currentPage");
   		String pageSize = request.getParameter("pageSize");
   		String userName = request.getParameter("userName");
   		String examName = request.getParameter("examName");
   		String dateOne = request.getParameter("dateOne");
   		String dateTwo = request.getParameter("dateTwo");
   		int subjectId;
   		if (request.getParameter("subjectId")==null) {
   			subjectId=0;
		}else {
			subjectId = Integer.parseInt(request.getParameter("subjectId"));
		}
   		
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
   		List<StudentExam> studentExamList = studentExamService.findAll(page, userName, examName, subjectId, dateOne, dateTwo);
   		
   		List<ExamRz> examRzList = new ArrayList<ExamRz>();
   		for (int i = 0; i < studentExamList.size(); i++) {
			ExamRz er = new ExamRz();
			er.setStudentExamiId(studentExamList.get(i).getStudentExamId());
			er.setStudentName(userService.findNameById(studentExamList.get(i).getUserId()).getUserName());
			
			er.setSubjectName(studentExamService.findSubjectName(studentExamList.get(i).getStudentExamId()).getSubjectName());
			er.setExamName(studentExamService.findExamName(studentExamList.get(i).getExamRuleId()).getExamName());
			er.setKgFen(studentExamList.get(i).getUserScore());
			er.setZgFen(studentExamList.get(i).getUserScore2());
			er.setCreateTime(studentExamList.get(i).getCreateDate());
			er.setCredit(studentExamList.get(i).getCredit());
			examRzList.add(er);
   		}
   		request.setAttribute("examRzList", examRzList);
   		request.setAttribute("userName", userName);
   		request.setAttribute("examName", examName);
   		List<Subject> subjectList = subjectService.findAllSubject();
    	request.setAttribute("subjectList", subjectList);
    	request.setAttribute("subjectId", subjectId);
    	request.setAttribute("dateOne", dateOne);
    	request.setAttribute("dateTwo", dateTwo);
    	request.setAttribute("page", page);
   		request.getRequestDispatcher("/back/exam/examHistory/examHistoryList.jsp").forward(request, response);
		
    }

}
