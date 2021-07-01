package com.ztkj.exam.entity;

public class QuestionOption {
	private int questionOptionId;
	private int questionId;
	private String questionOptionContent;
	public int getQuestionOptionId() {
		return questionOptionId;
	}
	public void setQuestionOptionId(int questionOptionId) {
		this.questionOptionId = questionOptionId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionOptionContent() {
		return questionOptionContent;
	}
	public void setQuestionOptionContent(String questionOptionContent) {
		this.questionOptionContent = questionOptionContent;
	}
	@Override
	public String toString() {
		return "QuestionOption [questionOptionId=" + questionOptionId + ", questionId=" + questionId
				+ ", questionOptionContent=" + questionOptionContent + "]";
	}
	
}
