package com.ztkj.exam.examRuleDetail.service;

import java.util.List;

import com.ztkj.exam.entity.StudentExamDetail;

public interface IStudentExamDetailService {
	public boolean addStudentExamDetail(StudentExamDetail sed);
	public boolean deleteStudentExamDetail(int studentExamId);
	public List<StudentExamDetail> findQuestion(int studentExamId);
	public boolean updateAnswer(int id,String answer,int score);
	public int chaFen(int id);
}
