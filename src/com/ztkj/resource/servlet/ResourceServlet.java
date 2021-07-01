package com.ztkj.resource.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.entity.Study;
import com.ztkj.resource.entity.Resource;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.resource.service.IResourceService;
import com.ztkj.resource.service.impl.ResourceServiceImpl;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class ResourceServlet
 */
@WebServlet("/front/study/resourceServlet")
public class ResourceServlet extends Upload {
	private static final long serialVersionUID = 1L;
	IResourceService resourceService = new ResourceServiceImpl();
       @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  request.setCharacterEncoding("UTF-8");
   		response.setContentType("text/html;charset=utf-8");
   		String method = request.getParameter("method");
   		if("addResource".equals(method)){
   			addResource(request,response);
   		}else if("findResourceByPageLike".equals(method)){
   			findResourceByPageLike(request,response);
   		}else if("deleteResourceById".equals(method)){
   			deleteResourceById(request,response);
   		}else if("findsourceByPageLike".equals(method)){
   			findsourceByPageLike(request,response);
   		}else if("findResourceById".equals(method)){
   			findResourceById(request,response);
   		}else if("updateResource".equals(method)){
   			updateResource(request,response);
   		}else{
   			findResourceByPageLike(request,response);
   		}
   	
   			
    }
     protected void addResource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Map<String, String> map = upload(request, response);
 		Resource resource = new Resource();
 		resource.setExploreDes(map.get("exploreDes"));
 		resource.setExploreName(map.get("exploreName"));
 		resource.setExploreUri(map.get("exploreUri"));
 		resource.setExploreDate(new Date());
 		resource.setExploreTypeId(Integer.parseInt(map.get("exploreTypeId")));
 		resource.setExploreState(Integer.parseInt(map.get("exploreState")));
 		boolean flag =resourceService.addResource(resource);
 		PrintWriter out =response.getWriter();
 		if(flag){
     		out.print("<script>alert('添加成功')</script>");
     		out.print("<script>location='/team04/front/img.jsp'</script>");
     	}else{
     		
     		out.print("<script>alert('添加失败')</script>");
     		out.print("<script>location='/team04/front/img.jsp'</script>");
     	}
     }
     protected void findResourceByPageLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String currentPage = request.getParameter("currentPage");
			String pageSize = request.getParameter("pageSize");
			String exploreName=request.getParameter("exploreName");
			String startTime2=request.getParameter("startTime2");
			String endTime2=request.getParameter("endTime2");
			Resource resource = new Resource();
			resource.setExploreName(exploreName);
			resource.setStartTime(startTime2);
			resource.setStartTime(endTime2);
			int ps = 5;
			if(pageSize != null && !"".equals(pageSize)){
				ps = Integer.parseInt(pageSize);
			}
			int cp = 1;
			if(currentPage != null && !"".equals(currentPage)){
				cp = Integer.parseInt(currentPage);
			}
			Page<Resource> page = new Page<Resource>();
			page.setCurrentPage(cp);
			page.setPageSize(ps);
			page.setEntity(resource);
			resourceService.findResourceByPageLike(page);
			request.setAttribute("page",page);
			
			//把模糊查询的条件带回到JSP页面
			request.getRequestDispatcher("/front/study/resource.jsp").forward(request,response);
    	
      }
     protected void deleteResourceById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 	String exploreId = request.getParameter("exploreId");
	    	boolean flag =resourceService.deleteResourceById(Integer.parseInt(exploreId));
	    	PrintWriter out = response.getWriter();
	    	if(flag){
	    		out.print("<script>alert('删除成功')</script>");
	    		out.print("<script>location='/team04/front/study/resourceServlet'</script>");
	    	}else{
	    		out.print("<script>alert('删除失败')</script>");
	    		out.print("<script>location='/team04/front/study/resourceServlet'</script>");
	    	}
     }
     protected void findsourceByPageLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String currentPage = request.getParameter("currentPage");
			String pageSize = request.getParameter("pageSize");
			String exploreName=request.getParameter("exploreName");
			System.out.println(exploreName);
			String startTimes=request.getParameter("startTimes");
			String endTimes=request.getParameter("endTimes");
			String exploreState = request.getParameter("exploreState");
			String exploreTypeId = request.getParameter("exploreTypeId");
			//String exploreDate=request.getParameter("exploreDate");
			Resource resource = new Resource();
			resource.setExploreName(exploreName);
			resource.setStartTime(startTimes);
			resource.setStartTime(endTimes);
			int et = 4;
			if(exploreTypeId == null && "".equals(exploreTypeId)){
				
				 et = Integer.parseInt(exploreState);
				 
			}
			resource.setExploreTypeId(et);
			int es =4;
			if(exploreState == null && "".equals(exploreState)){
				
				 es = Integer.parseInt(exploreState);
			}
			resource.setExploreState(es);
			//resource.getExploreDate(exploreDate);
			int ps = 5;
			if(pageSize != null && !"".equals(pageSize)){
				ps = Integer.parseInt(pageSize);
			}
			int cp = 1;
			if(currentPage != null && !"".equals(currentPage)){
				cp = Integer.parseInt(currentPage);
			}
			Page<Resource> page = new Page<Resource>();
			page.setCurrentPage(cp);
			page.setPageSize(ps);
			page.setEntity(resource);
			resourceService.findsourceByPageLike(page);;
			request.setAttribute("page",page);
			//把模糊查询的条件带回到JSP页面
			request.getRequestDispatcher("/back/study/source/sourceList.jsp").forward(request,response);
    	
      }
     protected void findResourceById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String exploreId = request.getParameter("exploreId");
    	 Resource resource = resourceService.findResourceById(Integer.parseInt(exploreId));
	    	request.setAttribute("resource",resource);
	    	request.getRequestDispatcher("/back/study/source/sourceAudit.jsp").forward(request, response);
     }
    protected void updateResource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String exploreName =request.getParameter("exploreName");
			String exploreDes =request.getParameter("exploreDes");
			String exploreState =request.getParameter("exploreState");
			String exploreTypeId =request.getParameter("exploreTypeId");
			String exploreId =request.getParameter("exploreId");
			String exploreUri =request.getParameter("exploreUri");
			Resource resource  = new Resource();
			resource.setExploreName(exploreName);;
			resource.setExploreDes(exploreDes);
			resource.setExploreState(Integer.parseInt(exploreState));
			resource.setExploreTypeId(Integer.parseInt(exploreTypeId));
			resource.setExploreDate(new Date());
			resource.setExploreId(Integer.parseInt(exploreId));
			resource.setExploreUri(exploreUri);
			boolean flag = resourceService.updateResource(resource);
			PrintWriter out =response.getWriter();
			if(flag){
	    		out.print("<script>alert('修改成功')</script>");
	    		out.print("<script>location='/team04/front/study/resourceServlet'</script>");
	    	}else{
	    		
	    		out.print("<script>alert('修改失败')</script>");
	    		out.print("<script>location='/team04/front/study/resourceServlet'</script>");
	    	}
	 }
  
	
	

}
