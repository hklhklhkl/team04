package com.ztkj.exam.entity;

public class ExamRuleDetail {
	private int examRuleDetailId;
	private int examRuleId;
	private int questionTypeId;
	private int questionNum;
	private int score;
	public int getExamRuleDetailId() {
		return examRuleDetailId;
	}
	public void setExamRuleDetailId(int examRuleDetailId) {
		this.examRuleDetailId = examRuleDetailId;
	}
	public int getExamRuleId() {
		return examRuleId;
	}
	public void setExamRuleId(int examRuleId) {
		this.examRuleId = examRuleId;
	}
	public int getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public int getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "ExamRuleDetail [examRuleDetailId=" + examRuleDetailId + ", examRuleId=" + examRuleId
				+ ", questionTypeId=" + questionTypeId + ", questionNum=" + questionNum + ", score=" + score + "]";
	}
	
}
