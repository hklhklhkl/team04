package com.ztkj.exam.exam.dao;

import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.utils.Page;

public interface IExamRuleDao {
	public List<ExamRule> findAllExam(Page page,String examName,int subjectId);
	public int findTotalCount(String examName,int subjectId);
	public boolean deleteExam(int examId);
	public boolean updateExamStatus(ExamRule exam);
	public ExamRule findExamById(int examId);
	public boolean addExam(ExamRule exam);
	public int findSeq();
	public boolean updateExam(ExamRule exam);
	
}
