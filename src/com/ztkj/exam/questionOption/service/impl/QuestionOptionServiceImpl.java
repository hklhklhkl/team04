package com.ztkj.exam.questionOption.service.impl;

import java.util.List;

import com.ztkj.exam.entity.QuestionOption;
import com.ztkj.exam.questionOption.dao.IQuestionOptionDao;
import com.ztkj.exam.questionOption.dao.impl.QuestionOptionDaoImpl;
import com.ztkj.exam.questionOption.service.IQuestionOptionService;


public class QuestionOptionServiceImpl implements IQuestionOptionService{
	IQuestionOptionDao  questionOptionDao = new QuestionOptionDaoImpl();

	@Override
	public boolean addQuestionOption(QuestionOption questionOption) {
		// TODO Auto-generated method stub
		return questionOptionDao.addQuestionOption(questionOption);
	}

	@Override
	public boolean deleteQuestionOption(int questionId) {
		// TODO Auto-generated method stub
		return questionOptionDao.deleteQuestionOption(questionId);
	}

	@Override
	public boolean updateQuestionOption(QuestionOption questionOption) {
		// TODO Auto-generated method stub
		return questionOptionDao.updateQuestionOption(questionOption);
	}

	@Override
	public List<QuestionOption> findQuestionOption(int questionId) {
		// TODO Auto-generated method stub
		return questionOptionDao.findQuestionOption(questionId);
	}
}
