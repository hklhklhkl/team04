package com.ztkj.exam.entity;

import java.util.Date;

public class ExamRule {
	private int examId;
	private int subjectId;
	private String examName;
	private String status;
	private int examScore;
	private String examAuthor;
	private Date examDate;
	private int examTime;
	private int examCredit;
	
	public int getExamCredit() {
		return examCredit;
	}
	public void setExamCredit(int examCredit) {
		this.examCredit = examCredit;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getExamScore() {
		return examScore;
	}
	public void setExamScore(int examScore) {
		this.examScore = examScore;
	}
	public String getExamAuthor() {
		return examAuthor;
	}
	public void setExamAuthor(String examAuthor) {
		this.examAuthor = examAuthor;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public int getExamTime() {
		return examTime;
	}
	public void setExamTime(int examTime) {
		this.examTime = examTime;
	}
	@Override
	public String toString() {
		return "ExamRule [examId=" + examId + ", subjectId=" + subjectId + ", examName=" + examName + ", status="
				+ status + ", examScore=" + examScore + ", examAuthor=" + examAuthor + ", examDate=" + examDate
				+ ", examTime=" + examTime + ", examCredit=" + examCredit + "]";
	}
	
	
}
