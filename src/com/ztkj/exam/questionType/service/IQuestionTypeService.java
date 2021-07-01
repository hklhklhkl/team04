package com.ztkj.exam.questionType.service;

import java.util.List;

import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.utils.Page;

public interface IQuestionTypeService {
	public boolean addQuestionType(QuestionType questionType);
	public boolean deleteQuestionType(int questionTypeId);
	public List<QuestionType> findAllQuestionType(Page page);
	public List<QuestionType> findAllQuestionType();
}
