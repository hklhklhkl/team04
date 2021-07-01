package com.ztkj.exam.entity;

public class StudentExamDetail {
	private int studentExamDetailId;
	private int studentExamId;
	private int	questionId;
	private String questionAnswer;
	private String userAnswer;
	private int userScore;
	
	
	public int getStudentExamDetailId() {
		return studentExamDetailId;
	}
	public void setStudentExamDetailId(int studentExamDetailId) {
		this.studentExamDetailId = studentExamDetailId;
	}
	public int getStudentExamId() {
		return studentExamId;
	}
	public void setStudentExamId(int studentExamId) {
		this.studentExamId = studentExamId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public int getUserScore() {
		return userScore;
	}
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}
	@Override
	public String toString() {
		return "StudentExamDetail [studentExamDetailId=" + studentExamDetailId + ", studentExamId=" + studentExamId
				+ ", questionId=" + questionId + ", questionAnswer=" + questionAnswer + ", userAnswer=" + userAnswer
				+ ", userScore=" + userScore + "]";
	}
	
	
	
	
}
