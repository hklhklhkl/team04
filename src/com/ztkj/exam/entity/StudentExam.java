package com.ztkj.exam.entity;

import java.util.Date;

public class StudentExam {
	private int studentExamId;
	private int userId;
	private int examRuleId;
	private int examScore;
	private int userScore;
	private Date createDate;
	private String status;
	private int userScore2;
	private int credit;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserScore2() {
		return userScore2;
	}
	public void setUserScore2(int userScore2) {
		this.userScore2 = userScore2;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getStudentExamId() {
		return studentExamId;
	}
	public void setStudentExamId(int studentExamId) {
		this.studentExamId = studentExamId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getExamRuleId() {
		return examRuleId;
	}
	public void setExamRuleId(int examRuleId) {
		this.examRuleId = examRuleId;
	}
	public int getExamScore() {
		return examScore;
	}
	public void setExamScore(int examScore) {
		this.examScore = examScore;
	}
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "StudentExam [studentExamId=" + studentExamId + ", userId=" + userId + ", examRuleId=" + examRuleId
				+ ", examScore=" + examScore + ", userScore=" + userScore + ", createDate=" + createDate + ", status="
				+ status + ", userScore2=" + userScore2 + ", credit=" + credit + "]";
	}
	
	
}
