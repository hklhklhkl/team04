package com.ztkj.resource.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RDownServlet
 */
@WebServlet("/front/study/rDownServlet")
public class RDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init() throws ServletException {
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		down(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public void destroy() {
	}
	
	private void down(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
		
		//获取图片名
		String exploreUri = req.getParameter("exploreUri");
		
		exploreUri = new String(exploreUri.getBytes("iso-8859-1"),"utf-8");
		
		//得到向客服端输出的输出流
		OutputStream outputStream = resp.getOutputStream();
		//输出文件用的字节数组，每次向输出流发送600个字节
		byte[] b = new byte[600];
		//要下载的文件
		String path = req.getSession().getServletContext().getRealPath("");
		System.out.println(path);
		int index = path.indexOf("wtpwebapps");
		path = path.substring(0, index);
		path += File.separator + "upload";
		File fileload = new File(path + File.separator, exploreUri);        
		//客服端使用保存文件的对话框
		resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(exploreUri, "utf-8"));
		//通知客服文件的MIME类型
		resp.setContentType("application/x-msdownload");
		//通知客服文件的长度
		long fileLength = fileload.length();
		String length = String.valueOf(fileLength);
		resp.setHeader("Content_length", length);
		//读取文件，并发送给客服端下载
		FileInputStream inputStream = new FileInputStream(fileload);
		int n = 0;
		while((n=inputStream.read(b))!=-1){
		    outputStream.write(b,0,n);
		}
		outputStream.flush();
		outputStream.close();
	}
}
