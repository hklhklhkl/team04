package com.ztkj.exam.entity;

import java.util.Date;

public class Subject {
	private int subjectId;
	private String subjectName;
	private String subjectAuthor;
	private Date subjectDate;
	private String subjectStatus;
	
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", subjectAuthor=" + subjectAuthor
				+ ", subjectDate=" + subjectDate + ", subjectStatus=" + subjectStatus + "]";
	}
	public String getSubjectStatus() {
		return subjectStatus;
	}
	public void setSubjectStatus(String subjectStatus) {
		this.subjectStatus = subjectStatus;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectAuthor() {
		return subjectAuthor;
	}
	public void setSubjectAuthor(String subjectAuthor) {
		this.subjectAuthor = subjectAuthor;
	}
	public Date getSubjectDate() {
		return subjectDate;
	}
	public void setSubjectDate(Date subjectDate) {
		this.subjectDate = subjectDate;
	}
	
	
}
