package com.ztkj.sys.role.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ztkj.sys.entity.Auth;
import com.ztkj.sys.entity.Module;
import com.ztkj.sys.entity.Role;
import com.ztkj.sys.module.service.IModuleService;
import com.ztkj.sys.module.service.impl.ModuleServiceImpl;
import com.ztkj.sys.role.service.IRoleService;
import com.ztkj.sys.role.service.impl.RoleServiceImpl;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/back/sys/role/roleServlet")
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IRoleService roleService = new RoleServiceImpl();
	IModuleService moduleService = new ModuleServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  	
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		if("findRoleByPageLike".equals(method)){			
			findRoleByPageLike(request, response);
		}else if("addRole".equals(method)){
			addRole(request, response);
		}else if("findRoleById".equals(method)){
			findRoleById(request, response);
		}else if("updateRoleInfo".equals(method)){
			updateRoleInfo(request, response);
		}else if("deleteRoleById".equals(method)){
			deleteRoleById(request, response);
		}else if("findAllRole".equals(method)){
			findAllRole(request, response);
		}else if("updateAuth".equals(method)){
			updateAuth(request, response);
		}else{
			findRoleByPageLike(request, response);
		}
    }
	protected void updateAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		String roleId = request.getParameter("roleId");
		int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		moduleService.deleteAuthByRoleId(rid);
		PrintWriter out = response.getWriter();		
    	String[] item = request.getParameterValues("chkItem");
    	/*System.out.println(Arrays.toString(item));*/
    	boolean flag = false;
    	for(int i=0;i<item.length;i++){
    		Auth auth = new Auth();
    		auth.setRoleId(rid);
    		if(item[i] != null && !"".equals(item[i])){
    			int moduleId = Integer.parseInt(item[i]);
    			auth.setModuleId(moduleId);
    		}
    		flag = moduleService.addAuth(auth);
    	}
    	if(flag){
			out.print("<script>alert('赋权成功!!')</script>");
		}else{
			out.print("<script>alert('赋权失败!!')</script>");
		}
    	out.print("<script>location='/team04/back/sys/role/roleServlet?method=findRoleByPageLike';</script>");
	}
	
	protected void findAllRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		String roleId = request.getParameter("roleId");
		int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		Role role = roleService.findRoleById(rid);
		request.setAttribute("role",role );

		List<Module> ModuleList = moduleService.findAllModule();
		request.setAttribute("ModuleList",ModuleList);
		List<Auth> authList = moduleService.findAuthByRoleId(rid);
		request.setAttribute("authList",authList);		
		request.getRequestDispatcher("/back/system/role/roleGrant.jsp").forward(request, response);
	}
	protected void deleteRoleById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		PrintWriter out = response.getWriter();
		String roleId = request.getParameter("roleId");
		int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		Role role = roleService.findRoleById(rid);
		boolean flag = roleService.deleteRoleById(role);
		if(flag){
			if(role.getRoleState()==1){
				out.print("<script>alert('注销成功!!')</script>");
			}else{
				out.print("<script>alert('恢复成功!!')</script>");
			}
		}else{
			if(role.getRoleState()==1){
				out.print("<script>alert('注销失败!!')</script>");
			}else{
				out.print("<script>alert('恢复失败!!')</script>");
			}
		}
		out.print("<script>location='/team04/back/sys/role/roleServlet?method=findRoleByPageLike';</script>");
	}
    protected void updateRoleInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	String roleId = request.getParameter("roleId");
    	int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		String roleName = request.getParameter("roleName");
		Role role = new Role();
		role.setRoleId(rid);
		role.setRoleName(roleName);
		boolean flag = roleService.updateRoleInfo(role);
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("<script>alert('保存成功!!')</script>");
		}else{
			out.print("<script>alert('保存失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/role/roleServlet?method=findRoleByPageLike';</script>");
    }
    protected void findRoleById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	String roleId = request.getParameter("roleId");
    	int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		Role role = roleService.findRoleById(rid);
		request.setAttribute("role", role);
		List<Auth> authList = moduleService.findAuthByRoleId(rid);
		request.setAttribute("authList", authList);
		request.getRequestDispatcher("/back/system/role/roleUpdate.jsp").forward(request, response);
    }
    protected void addRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		   	
    	String roleName = request.getParameter("roleName");
    	String userCreatorId = request.getParameter("userCreatorId");
    	int ucid = 0;
		if(userCreatorId != null && !"".equals(userCreatorId) ){
			ucid = Integer.parseInt(userCreatorId);
		}
		Role role = new Role();
		role.setRoleName(roleName);
		role.setRoleCreatorId(ucid);
		boolean flag = roleService.addRole(role);
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("<script>alert('添加成功!!')</script>");
		}else{
			out.print("<script>alert('添加失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/role/roleServlet?method=findRoleByPageLike';</script>");
    }
    
    protected void findRoleByPageLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
    	String currentPage = request.getParameter("currentPage");
		String roleName = request.getParameter("roleName");		
		String roleState = request.getParameter("roleState");
		int rState = -1;
		if(roleState != null && !"".equals(roleState) ){
			rState = Integer.parseInt(roleState);
		}
		int cp = 1;
		if(currentPage != null && !"".equals(currentPage)){
			cp = Integer.parseInt(currentPage);
		}
		Role role = new Role();
		role.setRoleName(roleName);
		role.setRoleState(rState);
		
		Page<Role> page = new Page<Role>();
		page.setCurrentPage(cp);
		int ps = 5;	
		page.setPageSize(ps);
		page.setEntity(role);
		roleService.findRoleByPageLike(page);
		request.setAttribute("page", page);
		request.setAttribute("roleName", roleName);
		request.setAttribute("roleState", rState);
		request.getRequestDispatcher("/back/system/role/roleList.jsp").forward(request, response);
	}
}
