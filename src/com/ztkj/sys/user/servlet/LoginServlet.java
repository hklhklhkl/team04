package com.ztkj.sys.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztkj.entity.User;
import com.ztkj.sys.entity.Module;
import com.ztkj.sys.entity.Role;
import com.ztkj.sys.module.service.IModuleService;
import com.ztkj.sys.module.service.impl.ModuleServiceImpl;
import com.ztkj.sys.role.service.IRoleService;
import com.ztkj.sys.role.service.impl.RoleServiceImpl;
import com.ztkj.sys.user.service.IUserService;
import com.ztkj.sys.user.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/back/sys/user/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		IUserService userService = new UserServiceImpl();
		IModuleService moduleService = new ModuleServiceImpl();
		IRoleService roleService = new RoleServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		String method = request.getParameter("method");
		PrintWriter out = response.getWriter();
		if("login".equals(method)) {
			login(request, response);
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);		
		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		User user = userService.login(userName,userPass);
		HttpSession session = request.getSession();
		Date date = new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String newDate = format.format(date);
		session.setAttribute("newDate", newDate);
		PrintWriter out = response.getWriter();
		if(user != null){
			 session.setAttribute("user", user);
			 List<Module> moduleList = moduleService.findModuleByUserId(user.getUserId());
			 List<Role> roleList = roleService.findAllRole();
			 List<User> userList = userService.findAllUser();
			 session.setAttribute("userList", userList);
			 session.setAttribute("roleList", roleList);
			 session.setAttribute("moduleList1", moduleList);			 
			 out.print("<script>alert('登陆成功!!')</script>");
			 out.print("<script>location='/team04/back/main.jsp';</script>");
		 }else{
			 out.print("<script>alert('用户名或密码错误或无权限,登陆失败!!')</script>");
			 out.print("<script>location='/team04/back/login.jsp';</script>");
		 }
	}
}
