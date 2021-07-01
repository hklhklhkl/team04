package com.ztkj.resource.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ztkj.utils.StrUtils;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/front/study/upload")
public class Upload extends HttpServlet {
	protected Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, String> map = new HashMap<String, String>();
		
		//��ÿ�������ϴ����ļ���װ������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//F:\apache-tomcat-7.0.76\wtpwebapps   ��ʵ·��
		String path = request.getSession().getServletContext().getRealPath("");
		int index = path.indexOf("wtpwebapps");
		path = path.substring(0, index);
		//File.separator����ָ���  
		path += File.separator + "upload";       
		//F:\\apache-tomcat-7.0.76\\upload
		//�����Ƿ��ϴ��ļ�����ʱ�ļ�����ʽ�����ڴ��̵��ٽ�ֵ��Ĭ��ֵ10KB����������Ϊ1M
		//�����ʾÿ���ļ���д����1Mһ�Σ���ÿ�ζ�д1M
		factory.setSizeThreshold(1024 * 1024);
		
		//����1Mʱ�����ŵ���ʱ�ļ�����
		factory.setRepository(new File(path));
		
		//�������ļ����ص��ϴ���
		ServletFileUpload upload = new ServletFileUpload(factory);   
		//ÿһ���ϴ����ļ����ܳ���2M
		upload.setFileSizeMax(1024 * 1024 * 10);

		//�����ϴ����ļ��ܹ����������ܳ���2M
		//upload.setSizeMax(1024 * 1024 * 2);
		
		List<FileItem> list;
		try {
			//�������л�ȡ�����ϴ��ļ�������ȡҳ��������е�Ԫ�ص�valueֵ
			list = upload.parseRequest(request);
			
			//�����������е��ϴ��ļ������ϴ�
			for (FileItem item : list) {
				//��ȡ�ύҳ������Ԫ�ص�name���Ե�ֵ
				String name = item.getFieldName();
				//�ж��Ƿ�Ϊ�������ͨ�ı�Ԫ�ػ���typeΪfile��Ԫ��
				if (item.isFormField()) {
					String value = item.getString();
					//ȥ���������⣬��Ϊʹ���˲������ʹ����post�ύ����Ҳ���������
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					map.put(name, value);
				} else {
					//��ȡ�����ϴ����ļ��������û��ѡ���ϴ��ļ�����value��ֵΪ""
					String value = item.getName();
					
					//����û�ѡ�����ϴ��ļ�������ļ����б���
					if (null != value && value.length() > 0) {
						//��ȡ�ļ����е����һ����.����λ��
						int start = value.lastIndexOf(".");
						
						//�����µ��ļ���
						String fileName = StrUtils.getUuid() + value.substring(start);
						
						//�ڷ������ϱ����ļ���Ŀ¼���ϴ��ļ�������
						//F:\\apache-tomcat-7.0.76\\upload\\�ļ���
						File file = new File(path, fileName);
						//���ϴε��ļ����ݣ�д�뵽���������½����ļ���
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
