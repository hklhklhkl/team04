package com.ztkj.sys.img.service.impl;

import java.util.List;

import com.ztkj.sys.entity.Img;
import com.ztkj.sys.entity.Role;
import com.ztkj.sys.img.dao.IImgDao;
import com.ztkj.sys.img.dao.impl.ImgDaoImpl;
import com.ztkj.sys.img.service.IImgService;
import com.ztkj.utils.Page;

public class ImgServiceImpl implements IImgService {

	IImgDao imgDao = new ImgDaoImpl();
	@Override
	public void findImgByPage(Page<Img> page) {
		int totalCount = imgDao.findTotalCount(page);
		page.setPageProperties(totalCount);
		List<Img> list = imgDao.findImgByPage(page);
		page.setList(list);
	}

	@Override
	public boolean addImg(Img img) {
		return imgDao.addImg(img);
	}

	@Override
	public boolean updateImg(Img img) {
		return imgDao.updateImg(img);
	}

	@Override
	public boolean deleteImg(Img img) {
		return imgDao.deleteImg(img);
	}

	@Override
	public Img findImgById(int imgId) {
		return imgDao.findImgById(imgId);
	}

}
