package com.ztkj.forum.reply.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.forum.entity.Reply;
import com.ztkj.forum.reply.service.dao.IReplyServiceDao;
import com.ztkj.forum.reply.service.impl.ReplyServiceImpl;

/**
 * Servlet implementation class ReplyServlet
 */
@WebServlet("/front/reply/replyServlet")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    IReplyServiceDao rs = new ReplyServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String method = request.getParameter("method");
    	if("addReply".equals(method)){
    		addReply(request,response);
    	}
	}
	protected void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyContent = request.getParameter("replyContent");
		String commentId = request.getParameter("commentId");
		Reply reply = new Reply();
		reply.setReplyContent(replyContent);
		reply.setCommentId(Integer.parseInt(commentId));
		boolean flag = rs.addReply(reply);
    	PrintWriter out = response.getWriter();
    	if(flag){
    		out.print("<script>alert('回复成功')</script>");
    	}else{
    		out.print("<script>alert('回复失败')</script>");
    	}
    	out.print("<script>location='/team04/front/forum/reply/frontReplyServlet'</script>");
	}
	
}
