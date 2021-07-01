package com.ztkj.exam.vo;

import java.util.Date;

public class ExamRz {
	private int studentExamiId;
	private String studentName;
	private String subjectName;
	private String examName;
	private int kgFen;
	private int zgFen;
	private int credit;
	private Date createTime;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStudentExamiId() {
		return studentExamiId;
	}
	public void setStudentExamiId(int studentExamiId) {
		this.studentExamiId = studentExamiId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public int getKgFen() {
		return kgFen;
	}
	public void setKgFen(int kgFen) {
		this.kgFen = kgFen;
	}
	public int getZgFen() {
		return zgFen;
	}
	public void setZgFen(int zgFen) {
		this.zgFen = zgFen;
	}
	
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "ExamRz [studentExamiId=" + studentExamiId + ", studentName=" + studentName + ", subjectName="
				+ subjectName + ", examName=" + examName + ", kgFen=" + kgFen + ", zgFen=" + zgFen + ", credit="
				+ credit + ", createTime=" + createTime + ", status=" + status + "]";
	}
	
	  
}
