package com.ztkj.sys.img.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztkj.entity.User;
import com.ztkj.sys.entity.Img;
import com.ztkj.sys.img.service.IImgService;
import com.ztkj.sys.img.service.impl.ImgServiceImpl;
import com.ztkj.sys.user.servlet.Upload;
import com.ztkj.utils.Page;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/back/sys/img/imgServlet")
public class ImgServlet  extends Upload {
	private static final long serialVersionUID = 1L;
    
	IImgService imgService = new ImgServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		if("findImgByPage".equals(method)){			
			findImgByPage(request, response);
		}else if("addImg".equals(method)){
			addImg(request, response);
		}else if("findImgById".equals(method)){
			findImgById(request, response);
		}else if("updateImg".equals(method)){
			updateImg(request, response);
		}else if("deleteImgById".equals(method)){
			deleteImgById(request, response);
		}else{
			findImgByPage(request, response);
		}
	}
	protected void deleteImgById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String imgId = request.getParameter("imgId");
		int iid = 0;
		if(imgId != null && !"".equals(imgId) ){
			iid = Integer.parseInt(imgId);
		}
		Img img = imgService.findImgById(iid);
		boolean flag = imgService.deleteImg(img);
		if(flag){
			if(img.getImgState()==1){
				out.print("<script>alert('注销成功!!')</script>");
			}else{
				out.print("<script>alert('恢复成功!!')</script>");
			}
		}else{
			
		}
		out.print("<script>location='/team04/back/sys/img/imgServlet?method=findImgByPage';</script>");
	}
	protected void updateImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = upload(request, response);
		String imgId = map.get("imgId");
		int iid = 0;
		if(imgId != null && !"".equals(imgId) ){
			iid = Integer.parseInt(imgId);
		}
		String imgPic = map.get("imgPic");
		String imgPlace = map.get("imgPlace");
		int ipc = 0;
		if(imgPlace != null && !"".equals(imgPlace) ){
			ipc = Integer.parseInt(imgPlace);
		}
		Img img = new Img();
		img.setImgId(iid);
		img.setImgPic(imgPic);
		img.setImgPlace(ipc);
		boolean flag = imgService.updateImg(img);
		PrintWriter out = response.getWriter();
		if(flag){
			out.print("<script>alert('保存成功!!')</script>");
		}else{
			out.print("<script>alert('保存失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/img/imgServlet?method=findImgByPage';</script>");
	}
	protected void findImgById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imgId = request.getParameter("imgId");
		int iid = 0;
		if(imgId != null && !"".equals(imgId) ){
			iid = Integer.parseInt(imgId);
		}
		Img img = imgService.findImgById(iid);
		request.setAttribute("img", img);
		request.getRequestDispatcher("/back/system/img/imgUpdate.jsp").forward(request, response);
	}
	protected void addImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map = upload(request, response);
		String imgPic = map.get("imgPic");
		String imgPlace = map.get("imgPlace");
		int ipc = 0;
		if(imgPlace != null && !"".equals(imgPlace) ){
			ipc = Integer.parseInt(imgPlace);
		}
		String imgCreatorId = map.get("imgCreatorId");
		int icd = 0;
		if(imgCreatorId != null && !"".equals(imgCreatorId) ){
			icd = Integer.parseInt(imgCreatorId);
		}
		Img img = new Img();
		img.setImgPic(imgPic);
		img.setImgPlace(ipc);
		img.setImgCreatorId(icd);
		PrintWriter out = response.getWriter();
		boolean flag = imgService.addImg(img);
		if(flag){
			out.print("<script>alert('添加成功!!')</script>");
		}else{
			out.print("<script>alert('添加失败!!')</script>");
		}
		out.print("<script>location='/team04/back/sys/img/imgServlet?method=findImgByPage';</script>");
	}
	protected void findImgByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		int cp = 1;
		if(currentPage != null && !"".equals(currentPage)){
			cp = Integer.parseInt(currentPage);
		}
		String imgPic = request.getParameter("imgPic");		
		String imgState = request.getParameter("imgState");
		int iState = -1;
		if(imgState != null && !"".equals(imgState) ){
			iState = Integer.parseInt(imgState);
		}
		Img img = new Img();
		img.setImgPic(imgPic);
		img.setImgState(iState);
		Page<Img> page = new Page<Img>();
		page.setCurrentPage(cp);
		int ps = 5;	
		page.setPageSize(ps);
		page.setEntity(img);
		imgService.findImgByPage(page);
		request.setAttribute("page", page);
		request.setAttribute("imgPic", imgPic);
		request.setAttribute("imgState", iState);
		request.getRequestDispatcher("/back/system/img/imgList.jsp").forward(request, response);
	}
}
