package com.ztkj.exam.entity;

public class QuestionType {
	private int questionTypeId;
	private String questionTypeName;
	
	public int getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public String getQuestionTypeName() {
		return questionTypeName;
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}

	@Override
	public String toString() {
		return "QuestionType [questionTypeId=" + questionTypeId + ", questionTypeName=" + questionTypeName + "]";
	}

	
	
	
}
