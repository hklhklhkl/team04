package com.ztkj.front.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztkj.entity.User;
import com.ztkj.front.user.service.IUserService;
import com.ztkj.front.user.service.impl.UserServiceImpl;

@WebServlet("/front/user/updateImg")
public class UpdateImg extends Upload {
	private static final long serialVersionUID = 1L;
       IUserService userservice = new UserServiceImpl();
       @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 鑾峰彇椤甸潰琛ㄥ崟浼犺繃鏉ョ殑鎵�鏈夋暟鎹�
    	request.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");	
   		Map<String, String> map = upload(request, response);
   		int id = Integer.parseInt(request.getParameter("userId"));
   		PrintWriter out = response.getWriter();
   		HttpSession session = request.getSession();
   		User user = (User)session.getAttribute("user");
   		user.setUserId(id);
   		user.setUserUri(map.get("face"));
   		
   		boolean flag = userservice.updatePic(user);
   		if(flag){
   			session.setAttribute("user", user);
   			out.print("<script>alert('修改成功！！')</script>");
   			out.print("<script>location='/team04/front/user/front_my_info_body.jsp'</script>");
   		}else{
   			out.print("<script>alert('修改失败！！')</script>");
   			out.print("<script>location='/team04/front/user/front_my_info_body.jsp'</script>");
   		}
   		
    }
   
}
