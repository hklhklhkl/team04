package com.ztkj.sys.img.dao;

import java.util.List;

import com.ztkj.sys.entity.Img;
import com.ztkj.utils.Page;

public interface IImgDao {

	List<Img> findImgByPage(Page<Img> page);	
	//查询总记录数
	int findTotalCount(Page<Img> page);
	
	boolean addImg(Img img);
	
	boolean updateImg(Img img);
	
	boolean deleteImg(Img img);
	
	Img findImgById(int imgId);
}
