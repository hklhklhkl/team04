package com.ztkj.forum.block.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.forum.block.service.dao.IBlockService;
import com.ztkj.forum.block.service.dao.impl.BlockServiceImpl;
import com.ztkj.forum.entity.Block;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class BlockServlet
 */
@WebServlet("/back/forum/block/blockServlet")
public class BlockServlet extends Upload {
	private static final long serialVersionUID = 1L;
	
	IBlockService blockService = new BlockServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String method = request.getParameter("method");
    	if("findAllBlock".equals(method)){
    		findAllBlock(request,response);
    	}else if("updateBlock".equals(method)){
    		updateBlock(request,response);
    	}else if("deleteBlock".equals(method)){
    		deleteBlock(request,response);
    	}else if("addBlock".equals(method)){
    		addBlock(request,response);
    	}else if("findBlockById".equals(method)){
    		findBlockById(request,response);
    	}else if("Buff".equals(method)){
    		Buff(request,response);
    	}else{
    		findAllBlock(request,response);
    	}
	}
	 protected void Buff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 int blockId = Integer.parseInt(request.getParameter("blockId"));
		 int blockBuff = Integer.parseInt(request.getParameter("blockBuff"));
		 boolean flag = false;
		 PrintWriter out = response.getWriter();
		 if(blockBuff == 2){
			 flag = blockService.buff(blockId, 1);
			 if(flag){
				 out.print("<script>alert('ͣ�óɹ�')</script>");
		     }else{
		    		out.print("<script>alert('ͣ��ʧ��')</script>");

		      }
		    		out.print("<script>location='/team04/back/forum/block/blockServlet?method=findAllBlock'</script>");
		 }else if(blockBuff == 1){
			 flag = blockService.buff(blockId, 2);
			 if(flag){
				 out.print("<script>alert('���óɹ�')</script>");
		     }else{
		    		out.print("<script>alert('����ʧ��')</script>");

		      }
		    		out.print("<script>location='/team04/back/forum/block/blockServlet?method=findAllBlock'</script>");
		 }
	 }
	 protected void findBlockById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 	String blockId = request.getParameter("blockId");
	    	Block block = blockService.findBlockById(Integer.parseInt(blockId));	
	    	request.setAttribute("block", block);
	    	request.getRequestDispatcher("/back/bbs/edition/editionUpdate.jsp").forward(request, response);
	 }
	 protected void findBlockPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		   String currentPage = request.getParameter("currentPage");
	 	   String pageSize = request.getParameter("pageSize");
		   String blockBiref = request.getParameter("blockBiref");
 	 	   String blockBuff = request.getParameter("blockBuff");
 	 	   System.out.println(blockBuff);
 	 	   String blockName = request.getParameter("blockName");
 	 	   String userId = request.getParameter("userId");
 	 	   Block block = new Block();
 	 	   if(blockBiref!=null){
 	 		 block.setBlockBiref(blockBiref);
 	 	   }
 	 	   
 	 	   if(blockBuff!=null){
 	 	   
 	 	   block.setBlockBuff(Integer.parseInt(blockBuff));
 	 	   
 	 	   }
 	 	   if(blockName!=null){
 	 		 block.setBlockName(blockName);
 	 	   }
 	 	   
 	 	   if(userId!=null){
 	 		 block.setUserId(Integer.parseInt(userId));  
 	 	   }
	 	   
 	 	  int ps = 2;
 	 	   if(pageSize != null && !"".equals(pageSize)){
 	 		   ps=Integer.parseInt(pageSize);
 	 		   System.out.println(ps);
 	 	   }
 	 	   //���û�û�д���currentPage��Ĭ�ϵ�һҳ����
 	 	   int cp = 1;
 	 	   if(currentPage != null && !"".equals(currentPage)){
 	 		   cp = Integer.parseInt(currentPage);
 	 	   }
 	 	   Page<Block> page = new Page<Block>(); 
 	 	   //��ÿ��ҳ��ʵ��������ֵ
 	 	   page.setPageSize(ps);
 	 	   //����ǰҳ��ֵ
 	 	   page.setCurrentPage(cp);
 	 	   //��ģ����ѯ�������ֶδ��뵽page�����entity������
 	 	   page.setEntity(block);
 	 	   blockService.findAllBlock(page);
 	 	   //��emplist��page����洢��request�����У�����ҳ��
 	 	   request.setAttribute("page", page);
 	 	   request.getRequestDispatcher("/back/bbs/edition/editionList.jsp").forward(request, response);
	 }
	
	 protected void updateBlock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String,String> map = upload(request,response);
		String blockName = map.get("blockName");
    	String blockPhoto = map.get("blockPhoto");
    	String userId = map.get("userId");
    	String blockBiref = map.get("blockBiref");
    	String blockId = map.get("blockId");
    	Block block = new Block();
    	block.setBlockBiref(blockBiref);
    	block.setBlockPhoto(blockPhoto);
    	block.setBlockName(blockName);
    	block.setUserId(Integer.parseInt(userId));
    	block.setBlockId(Integer.parseInt(blockId));
    	boolean flag = blockService.updateBlock(block);
    	PrintWriter out = response.getWriter();
    	
    	if(flag){
    		out.print("<script>alert('�޸ĳɹ�')</script>");
    	}else{
    		out.print("<script>alert('�޸�ʧ��')</script>");

    	}
    		out.print("<script>location='/team04/back/forum/block/blockServlet?method=findAllBlock'</script>");
    }
	protected void addBlock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blockName = request.getParameter("blockName");
    	String blockPhoto = request.getParameter("blockPhoto");
    	String userId = request.getParameter("userId");
    	String blockBiref = request.getParameter("blockBiref");
    	Block block = new Block();
    	block.setBlockBiref(blockBiref);
    	block.setBlockPhoto(blockPhoto);
    	block.setBlockName(blockName);
    	block.setUserId(Integer.parseInt(userId));
    	boolean flag = blockService.addBlock(block);
    	PrintWriter out = response.getWriter();
    	if(flag){
    		out.print("<script>alert('��ӳɹ�')</script>");
    	}else{
    		out.print("<script>alert('���ʧ��')</script>");

    	}
    	out.print("<script>location='/team04/back/forum/block/blockServlet?method=findAllBlock'</script>");
	}
	protected void deleteBlock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String blockId = request.getParameter("blockId");
    	boolean flag = blockService.deleteBlock(Integer.parseInt(blockId));
    	PrintWriter out = response.getWriter();
    	if(flag = true){
    		out.print("<script>alert('ɾ���ɹ�')</script>");
    		
    	}else{
    		out.print("<script>alert('ɾ��ʧ��')</script>");
    		
    	}
    	out.print("<script>location='/team04/back/forum/block/blockServlet?method=findAllBlock'</script>");
    }
	protected void findAllBlock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
	 	String pageSize = request.getParameter("pageSize");
		String blockBiref = request.getParameter("blockBiref");
	 	String blockBuff = request.getParameter("blockBuff");
	 	String blockName = request.getParameter("blockName");
	 	String userId = request.getParameter("userId");
	 	Block block = new Block();
		block.setBlockBiref(blockBiref);
		if (blockBuff != null && !"".equals(blockBuff)) {
			block.setBlockBuff(Integer.parseInt(blockBuff));
		}
		block.setBlockName(blockName);
		if (userId != null && !"".equals(userId)) {
			block.setUserId(Integer.parseInt(userId));
		}
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
		page.setEntity(block);
		blockService.findAllBlock(page);
		List<Block> user = blockService.findNameById();
		List<Block> count = blockService.findTotalConut();
		request.setAttribute("count", count);
		request.setAttribute("user", user);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/back/bbs/edition/editionList.jsp").forward(request, response);
	}

	
}
