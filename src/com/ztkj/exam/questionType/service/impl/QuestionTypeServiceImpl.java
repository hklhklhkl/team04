package com.ztkj.exam.questionType.service.impl;

import java.util.List;

import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.questionType.dao.IQuestionTypeDao;
import com.ztkj.exam.questionType.dao.impl.QuestionTypeDaoImpl;
import com.ztkj.exam.questionType.service.IQuestionTypeService;
import com.ztkj.exam.utils.Page;

public class QuestionTypeServiceImpl implements IQuestionTypeService{
	IQuestionTypeDao questionDao = new QuestionTypeDaoImpl();
	
	

	@Override
	public boolean deleteQuestionType(int questionTypeId) {
		// TODO Auto-generated method stub
		return questionDao.deleteQuestionType(questionTypeId);
	}

	@Override
	public List<QuestionType> findAllQuestionType(Page page) {
		int total = questionDao.findTotalCount();
		page.setPageProperties(total);
		return questionDao.findAllQuestionType(page);
	}

	@Override
	public boolean addQuestionType(QuestionType questionType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<QuestionType> findAllQuestionType() {
		// TODO Auto-generated method stub
		return questionDao.findAllQuestionType();
	}

}
