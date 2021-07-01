/**
 * 鍥剧墖涓嬭浇鐨剆ervlet
 * @author shiming
 * @date 2015-6-6
 * @version 1.0
 */
package com.ztkj.front.user.servlet;

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
@WebServlet("/front/downServlet")
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
		
		//鑾峰彇鍥剧墖鍚�
		String filename = req.getParameter("fileName");
		
		filename = new String(filename.getBytes("iso-8859-1"),"utf-8");
		resp.reset();
		//寰楀埌鍚戝鏈嶇杈撳嚭鐨勮緭鍑烘祦
		OutputStream outputStream = resp.getOutputStream();
		//杈撳嚭鏂囦欢鐢ㄧ殑瀛楄妭鏁扮粍锛屾瘡娆″悜杈撳嚭娴佸彂閫�600涓瓧鑺�
		byte[] b = new byte[600];
		//瑕佷笅杞界殑鏂囦欢
		String path = req.getSession().getServletContext().getRealPath("");
		System.out.println(path);
		int index = path.indexOf("wtpwebapps");
		path = path.substring(0, index);
		path += File.separator + "upload";
		File fileload = new File(path + File.separator, filename);        
		//瀹㈡湇绔娇鐢ㄤ繚瀛樻枃浠剁殑瀵硅瘽妗�
		resp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
		//閫氱煡瀹㈡湇鏂囦欢鐨凪IME绫诲瀷
		resp.setContentType("application/x-msdownload");
		//閫氱煡瀹㈡湇鏂囦欢鐨勯暱搴�
		long fileLength = fileload.length();
		String length = String.valueOf(fileLength);
		resp.setHeader("Content_length", length);
		//璇诲彇鏂囦欢锛屽苟鍙戦�佺粰瀹㈡湇绔笅杞�
		FileInputStream inputStream = new FileInputStream(fileload);
		
		int n = 0;
		while((n=inputStream.read(b))!=-1){
		    outputStream.write(b,0,n);
		}
		outputStream.flush();
		outputStream.close();
	}
}
