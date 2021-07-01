package com.ztkj.resource.entity;

import java.util.Date;

public class Resource {
	private int exploreId;
	private String exploreDes;
	private String exploreName;
	private Date exploreDate;
	private String startTime;
	private String endTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getExploreState() {
		return exploreState;
	}
	public void setExploreState(int exploreState) {
		this.exploreState = exploreState;
	}
	private int exploreTypeId;
	private String exploreUri;
	private int exploreState;
	public int getExploreId() {
		return exploreId;
	}
	public void setExploreId(int exploreId) {
		this.exploreId = exploreId;
	}
	public String getExploreDes() {
		return exploreDes;
	}
	public void setExploreDes(String exploreDes) {
		this.exploreDes = exploreDes;
	}
	public String getExploreName() {
		return exploreName;
	}
	public void setExploreName(String exploreName) {
		this.exploreName = exploreName;
	}
	public Date getExploreDate() {
		return exploreDate;
	}
	public void setExploreDate(Date exploreDate) {
		this.exploreDate = exploreDate;
	}
	
	public int getExploreTypeId() {
		return exploreTypeId;
	}
	public void setExploreTypeId(int exploreTypeId) {
		this.exploreTypeId = exploreTypeId;
	}
	public String getExploreUri() {
		return exploreUri;
	}
	public void setExploreUri(String exploreUri) {
		this.exploreUri = exploreUri;
	}
	
}
