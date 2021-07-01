package com.ztkj.sys.img.service;

import com.ztkj.sys.entity.Img;

import com.ztkj.utils.Page;

public interface IImgService {

	void findImgByPage(Page<Img> page);
	
	boolean addImg(Img img);
	
	boolean updateImg(Img img);
	
	boolean deleteImg(Img img);
	
	Img findImgById(int imgId);
}
