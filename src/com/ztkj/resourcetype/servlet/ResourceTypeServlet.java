package com.ztkj.resourcetype.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.entity.Study;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.resourcetype.service.IResourceTypeService;
import com.ztkj.resourcetype.service.impl.ResourceTypeServiceImpl;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class ResourceTypeServlet
 */
@WebServlet("/back/study/resourceType/resourceTypeServlet")
public class ResourceTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IResourceTypeService  resourceTypeService =  new  ResourceTypeServiceImpl();
       @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   request.setCharacterEncoding("UTF-8");
      		response.setContentType("text/html;charset=utf-8");
      		String method = request.getParameter("method");
      		if("addResourceType".equals(method)){
       			addResourceType(request,response);
       		}else if("findResourceTypeByPageLike".equals(method)){
       			findResourceTypeByPageLike(request,response);
       		}else if("updateResourceType".equals(method)){
       			updateResourceType(request,response);
       		}else if("findResourceTypeById".equals(method)){
       			findResourceTypeById(request,response);
       		}else if("deleteResourceTypeById".equals(method)){
       			deleteResourceTypeById(request,response);
       		}else if("updateResourceState".equals(method)){
       			updateResourceState(request,response);
       		}else{
       			findResourceTypeByPageLike(request,response);}
    }
       protected void addResourceType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  // String exploreTypeId =request.getParameter("exploreTypeId");
       	 String exploreTypeName =request.getParameter("exploreTypeName");
    		String exploreTypeMan =request.getParameter("exploreTypeMan");
    		String exploreTypeDate =request.getParameter("exploreTypeDate");
    		String exploreTypeState =request.getParameter("exploreTypeState");
    		System.out.println(exploreTypeState);
    		ResourceType resourceType = new ResourceType();
    		//resourceType.setExploreTypeId(Integer.parseInt(exploreTypeId));
    		resourceType.setExploreTypeName(exploreTypeName);
    		resourceType.setExploreTypeMan(exploreTypeMan);
  		try {
  			resourceType.setExploreTypeDate(simpleDateFormat.parse(exploreTypeDate));
  		} catch (ParseException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
    		resourceType.setExploreTypeState(Integer.parseInt(exploreTypeState));
    		boolean flag =resourceTypeService.addResourceType(resourceType);
    		PrintWriter out =response.getWriter();
    		if(flag){
        		out.print("<script>alert('保存成功')</script>");
        		out.print("<script>location='/team04/back/study/resourceType/resourceTypeServlet'</script>");
        	}else{
        		
        		out.print("<script>alert('保存失败')</script>");
        		out.print("<script>location='/team04/back/study/resourceType/resourceTypeServlet'</script>");
        	}
    }
       protected void findResourceTypeByPageLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String currentPage = request.getParameter("currentPage");
			String pageSize = request.getParameter("pageSize");
			String exploreTypeName =request.getParameter("exploreTypeName");
			String exploreTypeState =request.getParameter("exploreTypeState");
			int ets = 4;
			if(exploreTypeState != null && !"".equals(exploreTypeState)){
				ets = Integer.parseInt(exploreTypeState);
			}
			
			System.out.println(exploreTypeState);
			ResourceType resourceType = new ResourceType();
			resourceType.setExploreTypeName(exploreTypeName);
			
			resourceType.setExploreTypeState(ets);
			int ps = 5;
			if(pageSize != null && !"".equals(pageSize)){
				ps = Integer.parseInt(pageSize);
			}
			int cp = 1;
			if(currentPage != null && !"".equals(currentPage)){
				cp = Integer.parseInt(currentPage);
			}
			Page<ResourceType> page = new Page<ResourceType>();
			page.setCurrentPage(cp);
			page.setPageSize(ps);
			page.setEntity(resourceType);
			resourceTypeService.findResourceTypeByPageLike(page);;
			request.setAttribute("page",page);
			
			//把模糊查询的条件带回到JSP页面
			request.getRequestDispatcher("/back/study/sourceType/sourceTypeList.jsp").forward(request,response);
		}
       protected void findResourceTypeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String exploreTypeId = request.getParameter("exploreTypeId");
	    	ResourceType resourceType = resourceTypeService.findResourceTypeById( Integer.parseInt(exploreTypeId));
	    	request.setAttribute("resourceType", resourceType);
	    	request.getRequestDispatcher("/back/study/sourceType/sourceTypeUpdate.jsp").forward(request, response);
	    }
       protected void updateResourceType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	 String exploreTypeName =request.getParameter("exploreTypeName");
         	 System.out.println(exploreTypeName);
      		String exploreTypeMan =request.getParameter("exploreTypeMan");
      		String exploreTypeDate =request.getParameter("exploreTypeDate");
      		String exploreTypeId =request.getParameter("exploreTypeId");
      		String exploreTypeState =request.getParameter("exploreTypeState");
      		System.out.println(exploreTypeState);
      		ResourceType resourceType = new ResourceType();
      		resourceType.setExploreTypeId(Integer.parseInt(exploreTypeId));
      		resourceType.setExploreTypeName(exploreTypeName);
      		resourceType.setExploreTypeMan(exploreTypeMan);
    		try {
    			resourceType.setExploreTypeDate(simpleDateFormat.parse(exploreTypeDate));
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
      		resourceType.setExploreTypeState(Integer.parseInt(exploreTypeState));
			boolean flag = resourceTypeService.updateResourceType(resourceType);
			PrintWriter out =response.getWriter();
			if(flag){
	    		out.print("<script>alert('修改成功')</script>");
	    	}else{	    		
	    		out.print("<script>alert('修改失败')</script>");
	    	}
			out.print("<script>location='/team04/back/study/resourceType/resourceTypeServlet'</script>");
	    }
       protected void deleteResourceTypeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		 String exploreTypeId = request.getParameter("exploreTypeId");
  	    	boolean flag =resourceTypeService.deleteResourceTypeById(Integer.parseInt(exploreTypeId));
  	    	PrintWriter out = response.getWriter();
  	    	if(flag){
  	    		out.print("<script>alert('删除成功')</script>");
  	    		out.print("<script>location='/team04/back/study/resourceType/resourceTypeServlet'</script>");
  	    	}else{
  	    		out.print("<script>alert('删除失败')</script>");
  	    		out.print("<script>location='/team04/back/study/resourceType/resourceTypeServlet'</script>");
  	    	}
  	 }
       protected void updateResourceState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   String exploreTypeId =request.getParameter("exploreTypeId");
     		String exploreTypeState =request.getParameter("exploreTypeState");
     		int ets = 1;
			if(exploreTypeState != null && !"".equals(exploreTypeState)){
				ets = Integer.parseInt(exploreTypeState);
			}
     		System.out.println(exploreTypeState);
     		ResourceType resourceType = new ResourceType();
     		resourceType.setExploreTypeId(Integer.parseInt(exploreTypeId));
     		resourceType.setExploreTypeState(ets);
     		boolean flag = false;
     		if( ets == 1){
     		 flag = resourceTypeService.updateResourceState(resourceType);
     		}else if(ets == 2){
     			flag = resourceTypeService.updateResourceStateTwo(resourceType);
     		}
			PrintWriter out =response.getWriter();
			if(flag){
	    		out.print("<script>alert('修改成功')</script>");
	    		out.print("<script>location='/team04/back/study/resourceType/resourceTypeServlet'</script>");
	    	}else{
	    		
	    		out.print("<script>alert('修改失败')</script>");
	    		out.print("<script>location='/team04/back/study/resourceType/resourceTypeServlet'</script>");
	    	}
	    }
    
}
