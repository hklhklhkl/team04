/**
 * 图片下载的servlet
 * @author shiming
 * @date 2015-6-6
 * @version 1.0
 */
package com.ztkj.sys.user.servlet;

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
@WebServlet("/downServlet")
public class Down extends HttpServlet{
	
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
		
		//��ȡͼƬ��
		String filename = req.getParameter("fileName");
		
		filename = new String(filename.getBytes("iso-8859-1"),"utf-8");
		
		//�õ���ͷ�������������?
		resp.reset();
		OutputStream outputStream = resp.getOutputStream();
		//����ļ��õ��ֽ����飬ÿ�������������600���ֽ�
		byte[] b = new byte[600];
		//Ҫ���ص��ļ�
		String path = req.getSession().getServletContext().getRealPath("");
		System.out.println(path);
		int index = path.indexOf("wtpwebapps");
		path = path.substring(0, index);
		path += File.separator + "upload";
		File fileload = new File(path + File.separator, filename);        
		//�ͷ���ʹ�ñ����ļ��ĶԻ���
		resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
		//֪ͨ�ͷ��ļ���MIME����
		resp.setContentType("application/x-msdownload");
		//֪ͨ�ͷ��ļ��ĳ���
		long fileLength = fileload.length();
		String length = String.valueOf(fileLength);
		resp.setHeader("Content_length", length);
		//��ȡ�ļ��������͸��ͷ�������
		FileInputStream inputStream = new FileInputStream(fileload);
		
		int n = 0;
		while((n=inputStream.read(b))!=-1){
		    outputStream.write(b,0,n);
		}
		outputStream.flush();
		outputStream.close();
	}
}
