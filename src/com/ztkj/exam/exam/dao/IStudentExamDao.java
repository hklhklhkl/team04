package com.ztkj.exam.exam.dao;

import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.entity.StudentExam;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.utils.Page;


public interface IStudentExamDao {
	public boolean addStudentExam(StudentExam studentExam);
	public boolean deleteStudentExam(int  studentExam);
	public int findSeq();
	public boolean jiaFen(int id,int score);
	public boolean jiaFen2(int id,int score);
	public boolean jiaCredit(int id,int credit);
	public int findTotalCount(String userName,String examrName,int subjectId,String dateOne,String dateTwo);
	public List<StudentExam> findAll(Page page,String userName,String examrName,int subjectId,String dateOne,String dateTwo);
	public Subject findSubjectName(int id);
	public ExamRule findExamName(int id);
	public int findTotalCount(String userName,String examrName);
	public List<StudentExam> findAll(Page page,String userName,String examName);
	public StudentExam findStudentExamById(int id);
	public int findQuestionScore(int id);
	public StudentExam findStudentCredit(int userId,int examRuleId);
	public boolean addUserCredit(int userId);
}	
