package com.ztkj.exam.entity;

import java.util.Date;

public class Question {
	private int questionId;
	private int subjectId;
	private int questionTypeId;
	private String questionName;
	private String questionAnswer;
	private String questionAuthor;
	private Date questionDate;
	private String questionStatus;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	public String getQuestionAuthor() {
		return questionAuthor;
	}
	public void setQuestionAuthor(String questionAuthor) {
		this.questionAuthor = questionAuthor;
	}
	public Date getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}
	public String getQuestionStatus() {
		return questionStatus;
	}
	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", subjectId=" + subjectId + ", questionTypeId=" + questionTypeId
				+ ", questionName=" + questionName + ", questionAnswer=" + questionAnswer + ", questionAuthor="
				+ questionAuthor + ", questionDate=" + questionDate + ", questionStatus=" + questionStatus + "]";
	}
	
	
	
	
	
	
}
