package com.ztkj.sys.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztkj.entity.User;
import com.ztkj.sys.entity.Module;

public class AuthFilter implements Filter {

	private String code;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		request.setCharacterEncoding(code);
		response.setContentType("text/html;charset="+code);		
		//登陆拦截 	   请求资源为与登陆相关的直接放行,否则要验证是否登陆
		String uri = request.getRequestURI();		
		if(uri.endsWith("/downServlet")||uri.endsWith("user/loginServlet")
				|| uri.endsWith("noAuth.jsp")|| uri.endsWith("login.jsp")
				|| uri.endsWith("index.jsp")|| uri.endsWith("head.jsp")
				|| uri.endsWith("title.jsp")|| uri.endsWith("tail.jsp")
				|| uri.endsWith("front_index_body.jsp")|| uri.endsWith("frontUserServlet") 
				|| uri.endsWith("css")|| uri.endsWith("js")|| uri.contains("images") 
				|| uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".jpeg") 
				|| uri.endsWith(".gif")|| uri.endsWith("register.jsp")){
			chain.doFilter(request, response);
		}else{
			String contextPath = request.getContextPath();
			String path = uri.replace(contextPath, "");
			System.out.println(path);
			int secondNum = path.indexOf("/",2);
			System.out.println(secondNum);
			int lastNum = path.lastIndexOf("/");
			System.out.println(lastNum);
			String newPath = path.substring(secondNum, lastNum);
			System.out.println(newPath);
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if(user != null){
				if(uri.contains("/front")){
					chain.doFilter(request, response);
				}else{	
					List<Module> moduleList = (List<Module>) session.getAttribute("moduleList1");
					boolean flag = false;
					for(Module module:moduleList){
						if(module.getModuleUrl().contains(newPath)||path.endsWith("main.jsp")
							|| path.endsWith("top.jsp")|| path.endsWith("left.jsp")
							|| path.endsWith("footer.jsp")|| path.endsWith("welcome.jsp")
							|| path.endsWith("login.jsp")|| path.endsWith("userInfo.jsp")|| path.endsWith("password.jsp")|| path.contains("Add.jsp")){
							flag = true;
							break;
						}
					}
					if(flag){
						chain.doFilter(request, response);
					}else{
						response.sendRedirect("/team04/back/noAuth.jsp");
					}
				}
			}else{
				if(uri.contains("/front")){
					response.sendRedirect("/team04/front/user/frontUserServlet");
				}else{
					response.sendRedirect("/team04/back/sys/user/loginServlet");			
				}
				
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		code = config.getInitParameter("code");		
	}

}
