package com.ztkj.sys.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztkj.sys.role.service.IRoleService;
import com.ztkj.sys.entity.Module;
import com.ztkj.sys.entity.Role;
import com.ztkj.sys.module.service.IModuleService;
import com.ztkj.sys.module.service.impl.ModuleServiceImpl;
import com.ztkj.sys.role.service.impl.RoleServiceImpl;
import com.ztkj.entity.User;
import com.ztkj.sys.user.service.IUserService;
import com.ztkj.sys.user.service.impl.UserServiceImpl;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/back/sys/user/userServlet")
public class UserServlet extends Upload {
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
		if("findUserByPageLike".equals(method)){
			findUserByPageLike(request, response);
		}else if("addUser".equals(method)){
			addUser(request, response);
		}else if("updateSelfInfo".equals(method)){
			updateSelfInfo(request, response);
		}else if("findUserById".equals(method)){
			findUserById(request, response);
		}else if("updateUserInfo".equals(method)){
			updateUserInfo(request, response);
		}else if("deleteUserInfoById".equals(method)){
			deleteUserInfoById(request, response);
		}else if("updateSelfPass".equals(method)){
			updateSelfPass(request, response);
		}else if("updateUserPass".equals(method)){
			updateUserPass(request, response);
		}else if("cleanSession".equals(method)){
			cleanSession(request, response);
		}else{
			findUserByPageLike(request, response);
		}
	}
	protected void cleanSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		PrintWriter out = response.getWriter();
		out.print("<script>alert('清除成功!!')</script>");
		out.print("<script>location='/team04/back/login.jsp';</script>");		
	}
	protected void updateUserPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		int uid = 0;
		if(userId != null && !"".equals(userId) ){
			uid = Integer.parseInt(userId);
		}
		String userPass = request.getParameter("userPass");
		User user = new User();
		user.setUserId(uid);
		user.setUserPass(userPass);
		boolean flag = userService.updateSelfPass(user);
		if(flag){
			out.print("<script>alert('修改成功!!')</script>");
		}else{
			out.print("<script>alert('修改失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/user/userServlet?method=findUserByPageLike';</script>");
	}
	protected void updateSelfPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		int uid = 0;
		if(userId != null && !"".equals(userId) ){
			uid = Integer.parseInt(userId);
		}
		String userPass = request.getParameter("userPass");
		User user = new User();
		user.setUserId(uid);
		user.setUserPass(userPass);
		boolean flag = userService.updateSelfPass(user);
		if(flag){
			out.print("<script>alert('修改成功!!')</script>");
		}else{
			out.print("<script>alert('修改失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/user/userServlet?method=findUserByPageLike';</script>");
	}
	protected void findUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int uid = 0;
		if(userId != null && !"".equals(userId) ){
			uid = Integer.parseInt(userId);
		}
		User user = userService.findUserById(uid);
		request.setAttribute("user1", user);
		String cz = request.getParameter("cz");		 
		if("bj".equals(cz)){
			request.getRequestDispatcher("/back/system/user/userUpdate.jsp?userId="+uid).forward(request, response);
		}else if("czmm".equals(cz)){
			request.getRequestDispatcher("/back/system/user/passUpdate.jsp?userId="+uid).forward(request, response);
		}
	}

	protected void deleteUserInfoById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		int uid = 0;
		if(userId != null && !"".equals(userId) ){
			uid = Integer.parseInt(userId);
		}
		User user = userService.findUserById(uid);
		boolean flag = userService.deleteUserInfoById(user);
		if(flag){
			if(user.getUserState()==1){
				out.print("<script>alert('注销成功!!')</script>");
			}else{
				out.print("<script>alert('恢复成功!!')</script>");
			}
		}else{
			
		}
		out.print("<script>location='/team04/back/sys/user/userServlet?method=findUserByPageLike';</script>");
		
	}
	protected void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = upload(request, response);
		String userId = map.get("userId");
		int uid = 0;
		if(userId != null && !"".equals(userId) ){
			uid = Integer.parseInt(userId);
		}
		String userNickname = map.get("userNickname");		
		String userName = map.get("userName");
		String userSex = map.get("userSex");
		String userUri = map.get("userUri");
		String userBirth = map.get("userBirth");
		Date newUserBirth = null;
		if(userBirth != null && !"".equals(userBirth)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				newUserBirth = sdf.parse(userBirth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String roleId = map.get("roleId");
		int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		User user = new User();
		user.setUserId(uid);
		user.setUserNickname(userNickname);
		user.setUserName(userName);
		user.setUserSex(userSex);
		user.setUserBirth(newUserBirth);
		user.setUserUri(userUri);
		user.setRoleId(rid);
		request.setAttribute("user", user);
		boolean flag = userService.updateUserInfo(user);
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("<script>alert('修改成功!!')</script>");
		}else{
			out.print("<script>alert('修改失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/user/userServlet?method=findUserByPageLike';</script>");
	}
	protected void updateSelfInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = upload(request, response);
		String userNickname = map.get("userNickname");		
		String userName = map.get("userName");
		String userSex = map.get("userSex");
		String userUri = map.get("userUri");
		String userBirth = map.get("userBirth");
		Date newUserBirth = null;
		if(userBirth != null && !"".equals(userBirth)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				newUserBirth = sdf.parse(userBirth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("user");
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUserNickname(userNickname);
		user.setUserName(userName);
		user.setUserSex(userSex);
		user.setUserBirth(newUserBirth);
		user.setUserUri(userUri);
		user.setUserId(user1.getUserId());
		boolean flag = userService.updateSelfInfo(user);
		if(flag){
			out.print("<script>alert('修改成功!!')</script>");
		}else{
			out.print("<script>alert('修改失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/user/userServlet?method=findUserByPageLike';</script>");
	}
	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = upload(request, response);
		String userNickname = map.get("userNickname");		
		String userName = map.get("userName");
		String userPass = map.get("userPass");
		String roleId = map.get("roleId");
		int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		String userSex =map.get("userSex");
		String userBirth = map.get("userBirth");
		Date newUserBirth = null;
		if(userBirth != null && !"".equals(userBirth)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				newUserBirth = sdf.parse(userBirth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}			
		String userUri = map.get("userUri");
		String userCreatorId = map.get("userCreatorId");
		int ucid = 0;
		if(userCreatorId != null && !"".equals(userCreatorId) ){
			ucid = Integer.parseInt(userCreatorId);
		}
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUserNickname(userNickname);
		user.setUserName(userName);
		user.setUserPass(userPass);
		user.setRoleId(rid);
		user.setUserSex(userSex);
		user.setUserBirth(newUserBirth);
		user.setUserUri(userUri);
		user.setUserCreatorId(ucid);		
		boolean flag = userService.addUser(user);
		System.out.println(flag);
		if(flag){
			out.print("<script>alert('添加成功!!')</script>");
		}else{
			out.print("<script>alert('添加失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/user/userServlet?method=findUserByPageLike';</script>");
	}
	protected void findUserByPageLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String currentPage = request.getParameter("currentPage");
		String userNickname = request.getParameter("userNickname");		
		String userName = request.getParameter("userName");
		String userState = request.getParameter("userState");
		String roleId = request.getParameter("roleId");
		int rid = 0;
		if(roleId != null && !"".equals(roleId) ){
			rid = Integer.parseInt(roleId);
		}
		int uState = -1;
		if(userState != null && !"".equals(userState) ){
			uState = Integer.parseInt(userState);
		}
		
		int cp = 1;
		if(currentPage != null && !"".equals(currentPage)){
			cp = Integer.parseInt(currentPage);
		}
		User user = new User();
		user.setUserNickname(userNickname);
		user.setUserName(userName);
		user.setRoleId(rid);
		user.setUserState(uState);
		Page<User> page = new Page<User>();
		page.setCurrentPage(cp);
		int ps = 5;	
		page.setPageSize(ps);
		page.setEntity(user);
		userService.findUserByPageLike(page);
		request.setAttribute("page", page);
		request.setAttribute("userNickname", userNickname);
		request.setAttribute("userName", userName);
		request.setAttribute("userState", userState);
		request.setAttribute("roleId", roleId);
		request.getRequestDispatcher("/back/system/user/userList.jsp").forward(request, response);
	}
	/*protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			 out.print("<script>alert('鐧婚檰鎴愬姛!!')</script>");
			 out.print("<script>location='/team04/back/main.html';</script>");
		 }else{
			 out.print("<script>alert('鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒鎴栨棤鏉冮檺,鐧婚檰澶辫触!!')</script>");
			 out.print("<script>location='/team04/back/login.jsp';</script>");
		 }
	}*/
}
