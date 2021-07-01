package com.ztkj.sys.entity;

import java.util.Date;

public class Img {

	private int imgId;
	private String imgPic;
	private int imgPlace;
	private int imgCreatorId;
	private Date imgCreateDate;
	private int imgState;
	public int getImgState() {
		return imgState;
	}
	public void setImgState(int imgState) {
		this.imgState = imgState;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getImgPic() {
		return imgPic;
	}
	public void setImgPic(String imgPic) {
		this.imgPic = imgPic;
	}

	public int getImgPlace() {
		return imgPlace;
	}
	public void setImgPlace(int imgPlace) {
		this.imgPlace = imgPlace;
	}
	public int getImgCreatorId() {
		return imgCreatorId;
	}
	public void setImgCreatorId(int imgCreatorId) {
		this.imgCreatorId = imgCreatorId;
	}
	public Date getImgCreateDate() {
		return imgCreateDate;
	}
	public void setImgCreateDate(Date imgCreateDate) {
		this.imgCreateDate = imgCreateDate;
	}
	
}
