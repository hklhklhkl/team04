package com.ztkj.exam.question.service;

import java.util.List;

import com.ztkj.exam.entity.Question;
import com.ztkj.exam.utils.Page;
import com.ztkj.exam.vo.SubjectQuestionNum;

public interface IQuestionService {
	public boolean addQuestion(Question question);
	public boolean updateQuestion(Question question);
	public boolean updateQuestionStatus(Question question);
	public boolean deleteQuestion(int questionId);
	public Question findQuestionById(int questionId);
	public List<Question> findAllQuestion(Page page,String questionName,int questionTypeId,int subjectId);
	public int findQuestionId();
	public int findCountBySubjectId(int subjectId,int questionTypeId);
	public List<Question> findQuestionBySubjectIdAndType(int subjectId,int questionTypeId);//查询某个科目各个题型的所有题目

}
