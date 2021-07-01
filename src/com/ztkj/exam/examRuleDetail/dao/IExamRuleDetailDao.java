package com.ztkj.exam.examRuleDetail.dao;

import java.util.List;

import com.ztkj.exam.entity.ExamRuleDetail;

public interface IExamRuleDetailDao {
	public List<ExamRuleDetail> findAllExamRuleDetail();
	public List<ExamRuleDetail> findAllExamRuleDetailById(int examId);
	public boolean deleteExamRuleDetail(int examId);
	public boolean addExamRuleDetail(ExamRuleDetail examRuleDetail);
}
