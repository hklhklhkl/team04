package com.ztkj.exam.exam.service.impl;

import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.exam.dao.IExamRuleDao;
import com.ztkj.exam.exam.dao.impl.ExamRuleDaoImpl;
import com.ztkj.exam.exam.service.IExamService;
import com.ztkj.exam.examRuleDetail.dao.IExamRuleDetailDao;
import com.ztkj.exam.examRuleDetail.dao.impl.ExamRuleDetailDaoImpl;
import com.ztkj.exam.utils.Page;

public class ExamServiceImpl implements IExamService {
	IExamRuleDao examDao = new ExamRuleDaoImpl();
	IExamRuleDetailDao examRuleDetail = new ExamRuleDetailDaoImpl();
	@Override
	public List<ExamRule> findAllExam(Page page, String examName, int subjectId) {
		int total = examDao.findTotalCount(examName, subjectId);
		page.setPageProperties(total);
		return examDao.findAllExam(page, examName, subjectId);
	}
	@Override
	public boolean deleteExam(int examId) {
		boolean flag2 = examDao.deleteExam(examId);
		if (flag2==true) {
			examRuleDetail.deleteExamRuleDetail(examId);
		}
		return flag2;
	}
	@Override
	public boolean updateExamStatus(ExamRule exam) {
		return examDao.updateExamStatus(exam);
	}
	@Override
	public ExamRule findExamById(int examId) {
		// TODO Auto-generated method stub
		return examDao.findExamById(examId);
	}
	@Override
	public boolean addExam(ExamRule exam) {
		// TODO Auto-generated method stub
		return examDao.addExam(exam);
	}
	@Override
	public int findSeq() {
		// TODO Auto-generated method stub
		return examDao.findSeq();
	}
	@Override
	public boolean updateExam(ExamRule exam) {
		// TODO Auto-generated method stub
		return examDao.updateExam(exam);
	}

}
