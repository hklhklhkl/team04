package com.ztkj.front.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.entity.User;
import com.ztkj.front.user.service.IUserService;
import com.ztkj.front.user.service.impl.UserServiceImpl;


@WebServlet("/front/user/myInfoServlet")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userservice = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	
		String method = request.getParameter("method");
		User user = (User) request.getSession().getAttribute("user");
		int post = userservice.findPsostTotal(user.getUserId());
		request.setAttribute("post", post);
		request.getRequestDispatcher("front_my_info_body.jsp").forward(request, response);	
	}	
}
