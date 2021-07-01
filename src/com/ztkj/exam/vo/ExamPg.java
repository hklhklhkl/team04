package com.ztkj.exam.vo;

import java.util.Date;

public class ExamPg {
	private int id;
	private String name;
	private String subject;
	private String exam;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ExamPg [id=" + id + ", name=" + name + ", subject=" + subject + ", exam=" + exam + ", date=" + date
				+ "]";
	}
	
}
