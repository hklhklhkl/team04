package com.ztkj.forum.front.servlet;

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
import com.ztkj.forum.entity.Post;
import com.ztkj.forum.entity.Reply;
import com.ztkj.forum.post.service.dao.IPostServiceDao;
import com.ztkj.forum.post.service.dao.impl.PostService;
import com.ztkj.forum.reply.service.dao.IReplyServiceDao;
import com.ztkj.forum.reply.service.impl.ReplyServiceImpl;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet("/front/forum/comment/frontServlet")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IPostServiceDao postService = new PostService();
	ICommentService cs = new CommentServiceImpl();
	IReplyServiceDao rs = new ReplyServiceImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String method = request.getParameter("method");
    	if("findAllPost".equals(method)){
    		findAllPost(request,response);
    	}else if("updatePost".equals(method)){
    		updatePost(request,response);
    	}else if("findPostById".equals(method)){
    		findPostById(request,response);
    	}else if("updateGood".equals(method)){
    		updateGood(request,response);
    	}else if("updateUp".equals(method)){
    		updateUp(request,response);
    	}else if("findById".equals(method)){
    		findById(request,response);
    	}else if("findpostById".equals(method)){
    		findpostById(request,response);
    	}else if("findPostList".equals(method)){
    		findPostList(request,response);
    	}else if("addPost".equals(method)){
    		addPost(request,response);
    	}else if("deletePost".equals(method)){
    		deletePost(request,response);
    	}else if("postBlock".equals(method)){
    		postBlock(request,response);
    	}else{
    		findAllPost(request,response);
    	}
	}
	
	protected void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");
    	boolean flag = postService.deletePost(Integer.parseInt(postId));
    	PrintWriter out = response.getWriter();
    	if(flag = true){
    		out.print("<script>alert('删除成功')</script>");
    		
    	}else{
    		out.print("<script>alert('删除失败')</script>");
    		
    	}
    	out.print("<script>location='/team04/front/forum/reply/frontReplyServlet'</script>");
	}
	protected void addPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blockId = request.getParameter("blockId");
    	String userId = request.getParameter("userId");
    	String postContent = request.getParameter("postContent");
    	String postComment = request.getParameter("postComment");
    	Post post = new Post();
    	post.setBlockId(Integer.parseInt(blockId));
    	post.setUserId(Integer.parseInt(userId));
    	post.setPostComment(postComment);
    	post.setPostContent(postContent);
    	boolean flag = postService.addPost(post);
    	PrintWriter out = response.getWriter();
    	if(flag){
    		out.print("<script>alert('添加成功')</script>");
    	}else{
    		out.print("<script>alert('添加失败')</script>");
    	}
    	out.print("<script>location='/team04/front/forum/reply/frontReplyServlet'</script>");
	}
	protected void postBlock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blockId = request.getParameter("blockId");
		
		List<Post> postList = postService.findPostList(Integer.parseInt(blockId));
		
		request.setAttribute("postList", postList);
		request.getRequestDispatcher("/back/bbs/post/postBlock.jsp").forward(request, response);
	}
	protected void findPostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String blockId = request.getParameter("blockId");
		
		List<Post> postList = postService.findPostList(Integer.parseInt(blockId));
		
		request.setAttribute("postList", postList);
		request.getRequestDispatcher("/back/bbs/post/postAll.jsp").forward(request, response);
	}
	protected void updateUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		int postUp = Integer.parseInt(request.getParameter("postUp"));
		boolean flag = false;
		if(postUp==0){
			flag = postService.updateUp(postId, 1);
			
			PrintWriter out = response.getWriter();
			if(flag){
	    		out.print("<script>alert('置顶成功')</script>");
	    	}else{
	    		out.print("<script>alert('置顶失败')</script>");

	    	}
	    		out.print("<script>location='/team04/front/forum/comment/frontServlet?method=findAllPost'</script>");
		}
		if(postUp==1){
			flag = true;
			postService.updateUp(postId, 0);
			PrintWriter out = response.getWriter();
			if(flag){
	    		out.print("<script>alert('取消置顶成功')</script>");
	    	}else{
	    		out.print("<script>alert('取消置顶失败')</script>");

	    	}
	    		out.print("<script>location='/team04/front/forum/comment/frontServlet?method=findAllPost'</script>");
		}
		
	}
	protected void updateGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		int postGood = Integer.parseInt(request.getParameter("postGood"));
		boolean flag = false;
		if(postGood==0){
			flag = postService.updateGood(postId, 1);
			
			PrintWriter out = response.getWriter();
			if(flag){
	    		out.print("<script>alert('加精成功')</script>");
	    	}else{
	    		out.print("<script>alert('加精失败')</script>");

	    	}
	    		out.print("<script>location='/team04/front/forum/comment/frontServlet?method=findAllPost'</script>");
		}
		if(postGood==1){
			flag = true;
			postService.updateGood(postId, 0);
			PrintWriter out = response.getWriter();
			if(flag){
	    		out.print("<script>alert('取消加精成功')</script>");
	    	}else{
	    		out.print("<script>alert('取消加精失败')</script>");

	    	}
	    		out.print("<script>location='/team04/front/forum/comment/frontServlet?method=findAllPost'</script>");
		}
	}
	protected void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");
		Post post = postService.findPostById(Integer.parseInt(postId));
		request.setAttribute("post", post);
		request.getRequestDispatcher("/back/bbs/post/postInfo.jsp").forward(request, response);
	}
	protected void findpostById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");
		Post post = postService.findPostById(Integer.parseInt(postId));
		request.setAttribute("post", post);
		
		List<Comment> comment = cs.findAllComment();
		request.setAttribute("comment", comment);
		
		
		List<Reply> reply = rs.findAllReply();
		request.setAttribute("reply", reply);
		request.getRequestDispatcher("/back/bbs/post/Comment.jsp").forward(request, response);
	}
	protected void findPostById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");
		Post post = postService.findPostById(Integer.parseInt(postId));
		request.setAttribute("post", post);
		request.getRequestDispatcher("/back/bbs/post/postAudit.jsp").forward(request, response);
		
	}
	protected void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postContent = request.getParameter("postContent");
		String blockId = request.getParameter("blockId");
		String postComment = request.getParameter("postComment");
		String userId = request.getParameter("userId");
		String postState = request.getParameter("postState");
		String postSaw = request.getParameter("postSaw");
		String postId = request.getParameter("postId");
		Post post = new Post();
		post.setPostState(Integer.parseInt(postState));
		post.setPostContent(postContent);
		post.setBlockId(Integer.parseInt(blockId));
		post.setPostComment(postComment);
		post.setUserId(Integer.parseInt(userId));
		post.setPostId(Integer.parseInt(postId));
		post.setPostSaw(postSaw);
		boolean flag = postService.updatePost(post);
		PrintWriter out = response.getWriter();
		if(flag){
    		out.print("<script>alert('审核成功')</script>");
    	}else{
    		out.print("<script>alert('审核失败')</script>");

    	}
    		out.print("<script>location='/team04/front/forum/comment/frontServlet?method=findAllPost'</script>");
	}
	protected void findAllPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
	 	String pageSize = request.getParameter("pageSize");
	 	String postContent = request.getParameter("postContent");
	 	String postState = request.getParameter("postState");
	 	String blockId = request.getParameter("blockId");
	 	String userId = request.getParameter("userId");
	 	Post post = new Post();
	 	if (blockId != null && !"".equals(blockId)) {
			post.setUserId(Integer.parseInt(blockId));
		}
	 	post.setPostContent(postContent);
	 	if(postState != null && !"".equals(postState)){
	 		post.setPostState(Integer.parseInt(postState));
	 	}
	 	
	 	if (userId != null && !"".equals(userId)) {
			post.setUserId(Integer.parseInt(userId));
		}
	 	int ps = 2;
	    if (pageSize != null && !"".equals(pageSize)) {
			ps = Integer.parseInt(pageSize);
	 	}
		// 若用户没有传入currentPage，默认第一页数据
		int cp = 1;
		if (currentPage != null && !"".equals(currentPage)) {
			cp = Integer.parseInt(currentPage);
		}
		Page<Post> page = new Page<Post>();
		page.setPageSize(ps);
		// 给当前页赋值
		page.setCurrentPage(cp);
		page.setEntity(post);
		postService.findAllPost(page);
		List<Post> user = postService.findNameByUserId();
		List<Post> block = postService.findNameByBlockId();
		List<Post> state = postService.findNameByState();
		request.setAttribute("state", state);
		request.setAttribute("block", block);
		request.setAttribute("user", user);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/back/bbs/post/postList.jsp").forward(request, response);
	}
}