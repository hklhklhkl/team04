package com.ztkj.sys.user.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ztkj.utils.StrUtils;


public class Upload extends HttpServlet{
	
	//1 把表单参数全部封装到map对象中并返回
	//2 把file文件上传到指定目录中
	protected Map<String, String> upload(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Map<String, String> map = new HashMap<String, String>();
		
		//将每个单独上传的文件封装到磁�?
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//F:\apache-tomcat-7.0.76\wtpwebapps   真实路径
		String path = req.getSession().getServletContext().getRealPath("");
		int index = path.indexOf("wtpwebapps");
		path = path.substring(0, index);
		//File.separator 代表分隔�?
		path += File.separator + "upload";       
		//F:\\apache-tomcat-7.0.76\\upload
		//设置是否将上传文件的形式保持在磁盘的临界�?,默认�?10kb。这里设�?
		//这里表示每次文件读写都是1M�?�?,即每次读�?1M
		factory.setSizeThreshold(1024 * 1024);
		
		//超过1M�?,则存放到临时文件夹中
		factory.setRepository(new File(path));
		
		//将磁盘文件加载到上传�?
		ServletFileUpload upload = new ServletFileUpload(factory);   
		//每一个上传的文件不能超过2M
		upload.setFileSizeMax(1024 * 1024 * 10);

		//�?有上传的文件总共加起来不能超�?2M
		//upload.setSizeMax(1024 * 1024 * 2);
		
		List<FileItem> list;
		try {
			//从请求中获取�?有上传文�?,即获取页面表单内�?有元素的value�?
			list = upload.parseRequest(req);
			
			//对所有请求中的上传文件进行上�?
			for (FileItem item : list) {
				//获取提交页面表单里的元素的name属�?�的�?
				String name = item.getFieldName();
				//判断是否为表单里的普通文本元素还是type为file的元�?
				if (item.isFormField()) {
					String value = item.getString();
					//去吃乱码问题  因为使用了插�?,即使是用post提交方法也会出现乱码
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					map.put(name, value);
				} else {
					//获取�?�?上传的文件名,如果没有上传文件,则value的�?�为""
					String value = item.getName();
					
					//如果用户选择了上传文�?,则对文件进行保存
					if (null != value && value.length() > 0) {
						//获取文件名中的最后一个�??.”的位置
						int start = value.lastIndexOf(".");
						
						//生成新的文件�?
						String fileName = StrUtils.getUuid() + value.substring(start);
						
						//在服务器上保存文件的目录下上传文件的名字
						//F:\\apache-tomcat-7.0.76\\upload\\文件�?
						File file = new File(path, fileName);
						//将上次的文件内容，写入到服务器上新建的文件中
						item.write(file);
						
						map.put(name, fileName);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
		