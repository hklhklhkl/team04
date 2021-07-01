package com.ztkj.exam.question.service.impl;

import java.util.List;

import com.ztkj.exam.entity.Question;
import com.ztkj.exam.question.dao.IQuestionDao;
import com.ztkj.exam.question.dao.impl.QuestionDaoImpl;
import com.ztkj.exam.question.service.IQuestionService;
import com.ztkj.exam.utils.Page;
import com.ztkj.exam.vo.SubjectQuestionNum;

public class QuestionServiceImpl implements IQuestionService{
	IQuestionDao questionDao = new QuestionDaoImpl();
	@Override
	public boolean addQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionDao.addQuestion(question);
	}

	@Override
	public boolean updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionDao.updateQuestion(question);
	}

	@Override
	public boolean updateQuestionStatus(Question question) {
		// TODO Auto-generated method stub
		return questionDao.updateQuestionStatus(question);
	}

	@Override
	public boolean deleteQuestion(int questionId) {
		// TODO Auto-generated method stub
		return questionDao.deleteQuestion(questionId);
	}

	@Override
	public Question findQuestionById(int questionId) {
		// TODO Auto-generated method stub
		return questionDao.findQuestionById(questionId);
	}

	@Override
	public List<Question> findAllQuestion(Page page, String questionName, int questionTypeId, int subjectId) {
		int total = questionDao.findTotalCount(questionName,questionTypeId,subjectId);
		page.setPageProperties(total);
		return questionDao.findAllQuestion(page,questionName,questionTypeId,subjectId);
	}

	@Override
	public int findQuestionId() {
		return questionDao.findQuestionId();
		
	}

	@Override
	public int findCountBySubjectId(int subjectId, int questionTypeId) {
		
		return questionDao.findCountBySubjectId(subjectId, questionTypeId);
	}

	@Override
	public List<Question> findQuestionBySubjectIdAndType(int subjectId, int questionTypeId) {
		// TODO Auto-generated method stub
		return questionDao.findQuestionBySubjectIdAndType(subjectId, questionTypeId);
	}

	

}
