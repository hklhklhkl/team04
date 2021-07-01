package com.ztkj.exam.examRuleDetail.service;

import java.util.List;

import com.ztkj.exam.entity.ExamRuleDetail;

public interface IExamRuleDetailService {
	public List<ExamRuleDetail> findAllExamRuleDetailById(int examId);
	public List<ExamRuleDetail> findAllExamRuleDetail();
	public boolean addExamRuleDetailById(ExamRuleDetail examRuleDetail);
	public boolean deleteExamRuleDetail(int examId);
	
}
