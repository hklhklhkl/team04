package com.ztkj.entity;

import java.util.Date;

public class Explore {
	 private  int exploreId;
     private String exploreName ;
     private String exploreDesc ;
     private Date exploreDate;
     private String exploreUri;    
     private  int exploreTypeId;
	public int getExploreId() {
		return exploreId;
	}
	public void setExploreId(int exploreId) {
		this.exploreId = exploreId;
	}
	public String getExploreName() {
		return exploreName;
	}
	public void setExploreName(String exploreName) {
		this.exploreName = exploreName;
	}
	public String getExploreDesc() {
		return exploreDesc;
	}
	public void setExploreDesc(String exploreDesc) {
		this.exploreDesc = exploreDesc;
	}
	public Date getExploreDate() {
		return exploreDate;
	}
	public void setExploreDate(Date exploreDate) {
		this.exploreDate = exploreDate;
	}
	public String getExploreUri() {
		return exploreUri;
	}
	public void setExploreUri(String exploreUri) {
		this.exploreUri = exploreUri;
	}
	public int getExploreTypeId() {
		return exploreTypeId;
	}
	public void setExploreTypeId(int exploreTypeId) {
		this.exploreTypeId = exploreTypeId;
	}
     

}
