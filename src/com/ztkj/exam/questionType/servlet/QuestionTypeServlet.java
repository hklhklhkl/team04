package com.ztkj.exam.questionType.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.questionType.service.IQuestionTypeService;
import com.ztkj.exam.questionType.service.impl.QuestionTypeServiceImpl;
import com.ztkj.exam.utils.Page;

/**
 * Servlet implementation class QuestionTypeServlet
 */
@WebServlet("/back/exam/examQuestionTypeServlet")
public class QuestionTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IQuestionTypeService questionTypeService = new QuestionTypeServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String currentPage=request.getParameter("currentPage");
    	String pageSize = request.getParameter("pageSize");
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
    	List<QuestionType> questionTypeList = questionTypeService.findAllQuestionType(page);
    	request.setAttribute("questionTypeList", questionTypeList);
    	request.setAttribute("page", page);
    	request.getRequestDispatcher("/back/exam/questionType/questionTypeList.jsp").forward(request,response);
    }

}
