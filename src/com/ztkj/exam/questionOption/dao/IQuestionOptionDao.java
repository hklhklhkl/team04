package com.ztkj.exam.questionOption.dao;

import java.util.List;

import com.ztkj.exam.entity.QuestionOption;

public interface IQuestionOptionDao {
	public  boolean addQuestionOption(QuestionOption questionOption);
	public  boolean deleteQuestionOption(int questionId);
	public boolean updateQuestionOption(QuestionOption questionOption);
	public List<QuestionOption> findQuestionOption(int questionId);
}
