package com.ztkj.exam.examRuleDetail.service.impl;

import java.util.List;

import com.ztkj.exam.entity.ExamRuleDetail;
import com.ztkj.exam.examRuleDetail.dao.IExamRuleDetailDao;
import com.ztkj.exam.examRuleDetail.dao.impl.ExamRuleDetailDaoImpl;
import com.ztkj.exam.examRuleDetail.service.IExamRuleDetailService;

public class ExamRuleDetailServiceImpl implements IExamRuleDetailService {
	IExamRuleDetailDao  erdDao = new ExamRuleDetailDaoImpl();
	@Override
	public List<ExamRuleDetail> findAllExamRuleDetailById(int examId) {
		// TODO Auto-generated method stub
		return erdDao.findAllExamRuleDetailById(examId);
	}
	@Override
	public List<ExamRuleDetail> findAllExamRuleDetail() {
		// TODO Auto-generated method stub
		return erdDao.findAllExamRuleDetail();
	}
	@Override
	public boolean addExamRuleDetailById(ExamRuleDetail examRuleDetail) {
		// TODO Auto-generated method stub
		return erdDao.addExamRuleDetail(examRuleDetail);
	}
	@Override
	public boolean deleteExamRuleDetail(int examId) {
		// TODO Auto-generated method stub
		return erdDao.deleteExamRuleDetail(examId);
	}

}
