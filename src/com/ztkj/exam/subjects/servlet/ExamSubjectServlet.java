package com.ztkj.exam.subjects.servlet;

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
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.subjects.service.ISubjectService;
import com.ztkj.exam.subjects.service.impl.SubjectServiceImpl;
import com.ztkj.exam.utils.Page;

/**
 * Servlet implementation class ExamSubjectServlet
 */
@WebServlet("/back/exam/examSubjectServlet")
public class ExamSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ISubjectService subjectService = new SubjectServiceImpl();
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String currentPage=request.getParameter("currentPage");
    	String pageSize = request.getParameter("pageSize");
    	String subjectName = request.getParameter("subjectName");
    	request.setAttribute("subjectName", subjectName);
    	
    	String method = request.getParameter("method");
    	if ("updateSubject".equals(method)) {
    		updateSubject(request,response);
		}else if ("deleteSubject".equals(method)) {
    		deleteSubject(request,response);
		}else if ("addSubject".equals(method)) {
			addSubject(request,response);
		}else if ("updateSubjectName".equals(method)) {
			updateSubjectName(request,response);
		}else if ("updateSubjectName2".equals(method)) {
			updateSubjectName2(request,response);
		}else {
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
    	List<Subject> subjectList = subjectService.findAllSubjectByPage(page, subjectName);
    	request.setAttribute("subjectList", subjectList);
    	request.setAttribute("page", page);
    	request.getRequestDispatcher("/back/exam/subject/subjectList.jsp").forward(request, response);
		}
    }
    public void updateSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int subjectId = Integer.parseInt(request.getParameter("subjectId"));
    	String subjectStatus = request.getParameter("subjectStatus");
    	Subject subject = new Subject();
    	subject.setSubjectId(subjectId);
    	subject.setSubjectStatus(subjectStatus);
    	boolean flag =subjectService.updateSubject(subject);
    	PrintWriter out = response.getWriter();
    	if (flag == true) {
			out.print("<script>alert('操作成功')</script>");
		}else{
			out.print("<script>alert('操作失败')</script>");
		}
    	out.print("<script>location='/team04/exam/examSubjectServlet'</script>");
    }
    public void deleteSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int subjectId = Integer.parseInt(request.getParameter("subjectId"));
    	boolean flag =subjectService.dropSubject(subjectId);
    	PrintWriter out = response.getWriter();
    	if (flag == true) {
			out.print("<script>alert('删除成功')</script>");
		}else{
			out.print("<script>alert('删除失败')</script>");
		}
    	out.print("<script>location='/team04/exam/examSubjectServlet'</script>");
    }
    public void addSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String subjectName=request.getParameter("subjectName");
    	String userName = request.getParameter("subjectAuthor");
    	PrintWriter out = response.getWriter();
    	if (!"".equals(subjectName)&&subjectName!=null) {
    		Subject subject = new Subject();
        	subject.setSubjectName(subjectName);
        	subject.setSubjectAuthor(userName);
        	boolean flag =subjectService.addSubject(subject);
        	if (flag == true) {
    			out.print("<script>alert('添加成功')</script>");
    		}else{
    			out.print("<script>alert('添加失败')</script>");
    		}
		}else {out.print("<script>alert('添加失败')</script>");
		out.print("<script>location='/team04/back/exam/subject/subjectAdd.jsp'</script>");
		}
    	out.print("<script>location='/team04/exam/examSubjectServlet'</script>");
    
    }
    public void updateSubjectName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		Subject subject = subjectService.findSubjectById(subjectId);
		request.setAttribute("subject", subject);
		request.getRequestDispatcher("/back/exam/subject/subjectUpdate.jsp").forward(request, response);
	}
    public void updateSubjectName2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String subjectName = request.getParameter("subjectName");
    	int subjectId = Integer.parseInt(request.getParameter("subjectId"));
    	PrintWriter out = response.getWriter();
    	if (!"".equals(subjectName)&&subjectName!=null) {
    		Subject subject = new Subject();
    		subject.setSubjectId(subjectId);
        	subject.setSubjectName(subjectName);
        	boolean flag =subjectService.updateSubjectName(subject);
        	if (flag == true) {
    			out.print("<script>alert('修改成功')</script>");
    		}else{
    			out.print("<script>alert('修改失败')</script>");
    		}
		}else {out.print("<script>alert('修改失败')</script>");
		out.print("<script>location='/team04/back/exam/subject/subjectUpdate.jsp'</script>");
		}
    	out.print("<script>location='/team04/back/exam/examSubjectServlet'</script>");
    
    }

}
