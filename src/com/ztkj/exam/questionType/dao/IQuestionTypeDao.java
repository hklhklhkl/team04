package com.ztkj.exam.questionType.dao;

import java.util.List;

import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.utils.Page;

public interface IQuestionTypeDao {
	public boolean addQuestionType(QuestionType questionType);
	public boolean deleteQuestionType(int questionTypeId);
	public boolean updatedeleteQuestionType(Subject subject);
	public int findTotalCount();
	public List<QuestionType> findAllQuestionType(Page page);
	public List<QuestionType> findAllQuestionType();
}
