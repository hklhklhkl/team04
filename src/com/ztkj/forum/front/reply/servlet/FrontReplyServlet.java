package com.ztkj.forum.front.reply.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.forum.block.service.dao.IBlockService;
import com.ztkj.forum.block.service.dao.impl.BlockServiceImpl;
import com.ztkj.forum.entity.Block;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class FrontReplyServlet
 */
@WebServlet("/front/forum/reply/frontReplyServlet")
public class FrontReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IBlockService blockService = new BlockServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	
    		count(request,response);
    	
    		
    	
	}
	
	 protected void count(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 /*String currentPage = request.getParameter("currentPage");
		 String pageSize = request.getParameter("pageSize");
		 int ps = 2;
		    if (pageSize != null && !"".equals(pageSize)) {
				ps = Integer.parseInt(pageSize);
		 	}
			// ���û�û�д���currentPage��Ĭ�ϵ�һҳ����
			int cp = 1;
			if (currentPage != null && !"".equals(currentPage)) {
				cp = Integer.parseInt(currentPage);
			}
			Page<Block> page = new Page<Block>();
			page.setPageSize(ps);
			// ����ǰҳ��ֵ
			page.setCurrentPage(cp);
			blockService.findAllBlock(page);*/
	 	 List<Block> countBlock = blockService.findTotalConut();
    	 request.setAttribute("countBlock", countBlock);
    	 /*request.setAttribute("page", page);*/
    	 request.getRequestDispatcher("/front/front_luntan.jsp").forward(request, response);
	 }
	 
	
	
	
	 
	
	
}
