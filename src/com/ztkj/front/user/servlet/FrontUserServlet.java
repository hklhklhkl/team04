package com.ztkj.front.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztkj.entity.Pic;
import com.ztkj.entity.User;
import com.ztkj.front.user.service.IUserService;
import com.ztkj.front.user.service.impl.UserServiceImpl;
import com.ztkj.front.vo.GoodPost;
import com.ztkj.front.vo.HotPost;
import com.ztkj.front.vo.LunTan;
import com.ztkj.sys.entity.Module;
import com.ztkj.sys.entity.Role;


@WebServlet("/front/user/frontUserServlet")
public class FrontUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userservice = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		String method = request.getParameter("method");
		if("login".equals(method)){
			login(request,response);
		}else if("add".equals(method)){
			add(request,response);
		}else if("loginOut".equals(method)){
			loginOut(request,response);
		}else{
			List<User> userList = userservice.findUserName();
			List<LunTan> luntanList = userservice.findLunTanName();
			List<HotPost> hotpostList = userservice.findHotPost();
			List<Pic> picList =userservice.findPicture();
			List<GoodPost> goodList = userservice.findGoodPost();
			request.setAttribute("picList", picList);
			request.setAttribute("hotpostList",hotpostList);
			request.setAttribute("userList", userList);
			request.setAttribute("luntanList", luntanList);
			request.setAttribute("goodList", goodList);
			request.getRequestDispatcher("front_index_body.jsp").forward(request, response);		
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		User user =  userservice.front_login(userName, userPass);
		List<User> userList = userservice.findUserName();
		List<LunTan> luntanList = userservice.findLunTanName();
		List<HotPost> hotpostList = userservice.findHotPost();
		List<Pic> picList =userservice.findPicture();
		List<GoodPost> goodList = userservice.findGoodPost();
		String str = userservice.findRoleName(user.getUserId());
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if(user != null ){
			 session.setAttribute("user", user);
			 session.setAttribute("userList", userList);
			 session.setAttribute("luntanList", luntanList);
			 session.setAttribute("hotpostList",hotpostList);
			 session.setAttribute("str", str);
			 session.setAttribute("picList", picList);
			 request.setAttribute("goodList", goodList);
			 //把用户能够访问的权限存储在Session中	 
			 out.print("<script>alert('登陆成功!!')</script>");
			 out.print("<script>location='/team04/front/user/front_login_success.jsp';</script>");
		 }else{
			 out.print("<script>alert('用户名或密码错误,登陆失败!!')</script>");
			 out.print("<script>location='/team04/front/user/front_login.jsp';</script>");
		 }
	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =	response.getWriter();
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String userSex = request.getParameter("sex");
		String userNickName = request.getParameter("userNickName");
		User user  = new User();
		user.setUserName(userName);
		user.setUserPass(userPass);
		user.setUserNickname(userNickName);
		user.setUserSex(userSex);
		boolean flag  = userservice.add_user(user);
		if(flag){
			out.print("<script>alert('注册成功')</script>");
			out.print("<script>location='/team04/front/front_index.jsp';</script>");
		}else{
			out.print("<script>alert('注册失败')</script>");
			out.print("<script>location='/team04/front/front_register.jsp';</script>");
		}
	}
	protected void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//销毁session
		session.invalidate();
		PrintWriter out = response.getWriter();
		out.print("<script>alert('退出成功！！！')</script>");
		out.print("<script>location='/team04/front/user/front_login.jsp'</script>");
	}
}
