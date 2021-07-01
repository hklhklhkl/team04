package com.ztkj.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ztkj.entity.Study;
import com.ztkj.study.service.IStudyService;
import com.ztkj.study.service.impl.StudyServiceImpl;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class StudyServlet
 */
@WebServlet("/front/study/studyServlet")
public class StudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IStudyService studyService = new StudyServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String method = request.getParameter("method");
		if("addStudy".equals(method)){
			addStudy(request,response);
		}else if("findStudyById".equals(method)){
			findStudyById(request,response);
		}else if("findSingleStudyById".equals(method)){
			findSingleStudyById(request,response);
		}else if("updateStudy".equals(method)){
			updateStudy(request,response);
		}else if("deleteStudyById".equals(method)){
			deleteStudyById(request,response);
		}else if("findStudyByPageLike".equals(method)){
			findStudyByPageLike(request,response);
		}else{
			findStudyByPageLike(request,response);
		}
	}
	private Date Date(String noteDate) {
		// TODO Auto-generated method stub
		return null;
	}
	protected void addStudy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noteTitle =request.getParameter("noteTitle");
		String noteType =request.getParameter("noteType");
		String noteContent =request.getParameter("noteContent");
		String userId =request.getParameter("userId");
		String noteId =request.getParameter("noteId");
		Study study= new Study();
		study.setNoteId(Integer.parseInt(noteId));
		study.setNoteTitle(noteTitle);
		study.setNoteType(noteType);
		study.setNoteContent(noteContent);
		study.setNoteDate(new Date());
		study.setUserId(Integer.parseInt(userId));
		boolean flag =studyService.addStudy(study);
		PrintWriter out =response.getWriter();
		if(flag){
    		out.print("<script>alert('添加成功')</script>");
    		out.print("<script>location='/team04/front/study/noteOne.jsp'</script>");
    	}else{
    		
    		out.print("<script>alert('添加失败')</script>");
    		out.print("<script>location='/team04/front/study/addNote.jsp'</script>");
    	}
	}
	 protected void findStudyByPageLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String currentPage = request.getParameter("currentPage");
			String pageSize = request.getParameter("pageSize");
			String noteTitle=request.getParameter("noteTitle");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			Study study = new Study();
			study.setNoteTitle(noteTitle);
			study.setStartTime(startTime);
			study.setEndTime(endTime);
			int ps = 5;
			if(pageSize != null && !"".equals(pageSize)){
				ps = Integer.parseInt(pageSize);
			}
			int cp = 1;
			if(currentPage != null && !"".equals(currentPage)){
				cp = Integer.parseInt(currentPage);
			}
			Page<Study> page = new Page<Study>();
			page.setCurrentPage(cp);
			page.setPageSize(ps);
			page.setEntity(study);
		
			studyService.findStudyByPageLike(page);
			request.setAttribute("page",page);
			
			//把模糊查询的条件带回到JSP页面
			request.getRequestDispatcher("/front/study/front_note_show.jsp").forward(request,response);
		}
	 protected void deleteStudyById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println(1);
		 String noteId = request.getParameter("noteId");
	    	int noteIds =Integer.parseInt(noteId);
	    	System.out.println(noteIds);
	    	boolean flag =studyService.deleteStudyById(noteIds);
	    	PrintWriter out = response.getWriter();
	    	if(flag){
	    		out.print("<script>alert('删除成功')</script>");
	    		out.print("<script>location='/team04/front/study/studyServlet'</script>");
	    	}else{
	    		out.print("<script>alert('删除失败')</script>");
	    		out.print("<script>location='/team04/front/study/studyServlet'</script>");
	    	}
	 }
	  protected void findStudyById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  	String noteId = request.getParameter("noteId");
	    	int newStudyId =Integer.parseInt(noteId);
	    	Study study = studyService.findStudyById(newStudyId);
	    	request.setAttribute("study",study);
	    	request.getRequestDispatcher("/front/study/noteOne.jsp").forward(request, response);
	    	    }
	    	    //修改时查询单条数据
	  protected void findSingleStudyById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	    	String noteId = request.getParameter("noteId");
	    	    	int newStudyId =Integer.parseInt(noteId);
	    	    	Study study = studyService.findStudyById(newStudyId);
	    	    	request.setAttribute("study", study);
	    	    	request.getRequestDispatcher("/front/study/updateNote.jsp").forward(request, response);
	    	    }
	 
	protected void updateStudy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("12");
			String noteTitle =request.getParameter("noteTitle");
			String noteType =request.getParameter("noteType");
			String noteContent =request.getParameter("noteContent");
			String userId =request.getParameter("userId");
			String noteId =request.getParameter("noteId");
			Study study = new Study();
			study.setNoteTitle(noteTitle);
			study.setNoteType(noteType);
			study.setNoteContent(noteContent);
			study.setNoteDate(new Date()); 
			study.setUserId(Integer.parseInt(userId));
			study.setNoteId(Integer.parseInt(noteId));
			boolean flag = studyService.updateStudy(study);
			PrintWriter out =response.getWriter();
			if(flag){
	    		out.print("<script>alert('修改成功')</script>");
	    		out.print("<script>location='/team04/front/noteOne.jsp'</script>");
	    	}else{
	    		
	    		out.print("<script>alert('修改失败')</script>");
	    		out.print("<script>location='/team04/front/noteOne.jsp'</script>");
	    	}
	 }
}

