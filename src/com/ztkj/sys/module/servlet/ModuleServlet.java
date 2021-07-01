package com.ztkj.sys.module.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.sys.entity.Module;
import com.ztkj.sys.module.service.IModuleService;
import com.ztkj.sys.module.service.impl.ModuleServiceImpl;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class ModuleServlet
 */
@WebServlet("/back/sys/module/moduleServlet")
public class ModuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      

	IModuleService moduleService = new ModuleServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		PrintWriter out = response.getWriter();
		if("findModuleByPageLike".equals(method)){			
			findModuleByPageLike(request, response);
		}else if("addModule".equals(method)){
			addModule(request, response);
		}else if("findModuleById".equals(method)){
			findModuleById(request, response);
		}else if("updateModule".equals(method)){
			updateModule(request, response);
		}else if("deleteModuleById".equals(method)){
			deleteModuleById(request, response);
		}else{
			findModuleByPageLike(request, response);
		}
	}
	protected void deleteModuleById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moduleId = request.getParameter("moduleId");
    	int mid = 0;
		if(moduleId != null && !"".equals(moduleId) ){
			mid = Integer.parseInt(moduleId);
		}
		Module module = moduleService.findModuleById(mid);
		boolean flag = moduleService.deleteModule(module);
		PrintWriter out = response.getWriter();
		if(flag){
			if(module.getModuleState()==1){
				out.print("<script>alert('注销成功!!')</script>");
			}else{
				out.print("<script>alert('恢复成功!!')</script>");
			}
		}else{
			if(module.getModuleState()==1){
				out.print("<script>alert('注销失败!!')</script>");
			}else{
				out.print("<script>alert('恢复失败!!')</script>");
			}
		}
		out.print("<script>location='/team04/back/sys/module/moduleServlet?method=findModuleByPageLike';</script>");
	}	
	protected void updateModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moduleId = request.getParameter("moduleId");
    	int mid = 0;
		if(moduleId != null && !"".equals(moduleId) ){
			mid = Integer.parseInt(moduleId);
		}
		String moduleName = request.getParameter("moduleName");
		String moduleUrl = request.getParameter("moduleUrl");
		String moduleParentId = request.getParameter("moduleParentId");
		int mpd = 0;
		if(moduleParentId != null && !"".equals(moduleParentId) ){
			mpd = Integer.parseInt(moduleParentId);
		}
		Module module = new Module();
		module.setModuleId(mid);
		module.setModuleName(moduleName);
		module.setModuleUrl(moduleUrl);
		module.setModuleParentId(mpd);
		boolean flag = moduleService.updateModule(module);
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("<script>alert('修改成功!!')</script>");
		}else{
			out.print("<script>alert('修改失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/module/moduleServlet?method=findModuleByPageLike';</script>");
	}
	protected void findModuleById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moduleId = request.getParameter("moduleId");
    	int mid = 0;
		if(moduleId != null && !"".equals(moduleId) ){
			mid = Integer.parseInt(moduleId);
		}
		Module module = moduleService.findModuleById(mid);
		request.setAttribute("module", module);
		request.getRequestDispatcher("/back/system/model/modelUpdate.jsp").forward(request, response);
	}
	protected void addModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moduleName = request.getParameter("moduleName");	
		String moduleUrl = request.getParameter("moduleUrl");
		String moduleParentId = request.getParameter("moduleParentId");
		int mpd = 0;
		if(moduleParentId != null && !"".equals(moduleParentId) ){
			mpd = Integer.parseInt(moduleParentId);
		}
		String moduleCreatorId = request.getParameter("moduleCreatorId");
		int mcid = 0;
		if(moduleCreatorId != null && !"".equals(moduleCreatorId) ){
			mcid = Integer.parseInt(moduleCreatorId);
		}
		Module module = new Module();
		module.setModuleName(moduleName);
		module.setModuleUrl(moduleUrl);
		module.setModuleParentId(mpd);
		module.setModuleCreatorId(mcid);
		boolean flag = moduleService.addModule(module);
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("<script>alert('添加成功!!')</script>");
		}else{
			out.print("<script>alert('添加失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/module/moduleServlet?method=findModuleByPageLike';</script>");
    }	
	
	protected void findModuleByPageLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String moduleName = request.getParameter("moduleName");		
		String moduleState = request.getParameter("moduleState");
		int mState = -1;
		if(moduleState != null && !"".equals(moduleState) ){
			mState = Integer.parseInt(moduleState);
		}
		int cp = 1;
		if(currentPage != null && !"".equals(currentPage)){
			cp = Integer.parseInt(currentPage);
		}
		Module module = new Module();
		module.setModuleName(moduleName);
		module.setModuleState(mState);
		Page<Module> page = new Page<Module>();
		page.setCurrentPage(cp);
		int ps = 10;	
		page.setPageSize(ps);
		page.setEntity(module);
		moduleService.findModuleByPageLike(page);
		request.setAttribute("page", page);
		request.setAttribute("moduleName", moduleName);
		request.setAttribute("moduleState", mState);
		request.getRequestDispatcher("/back/system/model/modelList.jsp").forward(request, response);
	}
}
