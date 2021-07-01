package com.ztkj.forum.Comment.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.forum.Comment.service.dao.ICommentService;
import com.ztkj.forum.Comment.service.impl.CommentServiceImpl;
import com.ztkj.forum.entity.Comment;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/front/comment/commentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ICommentService cs = new CommentServiceImpl();
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String method = request.getParameter("method");
    	if("addComment".equals(method)){
    		addComment(request,response);
    	}else{
    		findAllComment(request,response);
    	}
    }
    protected void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/*String userId = request.getParameter("userId");*/
    	String commentContent = request.getParameter("commentContent");
    	String postId = request.getParameter("postId");
    	Comment comment = new Comment();
    	comment.setCommentContent(commentContent);
    	comment.setPostId(Integer.parseInt(postId));
    	/*comment.setUserId(Integer.parseInt(userId));*/
    	boolean flag = cs.addComment(comment);
    	PrintWriter out = response.getWriter();
    	if(flag){
    		out.print("<script>alert('回复成功')</script>");
    	}else{
    		out.print("<script>alert('回复失败')</script>");
    	}
    	out.print("<script>location='/team04/front/forum/reply/frontReplyServlet'</script>");
    }
    protected void findAllComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comment> comment = cs.findAllComment();
		request.setAttribute("comment", comment);
		System.out.println(comment.size());
		request.getRequestDispatcher("/back/bbs/post/Comment.jsp").forward(request, response);
    }
}
