package com.ztkj.exam.exam.service;

import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.entity.StudentExam;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.utils.Page;

public interface IStudentExamService {
	public boolean addStudentExam(StudentExam studentExam);
	public boolean deleteStudentExam(int i);
	public int findSeq();
	public boolean jiaFen(int id,int score);
	public boolean jiaFen2(int id,int score);
	public boolean jiaCredit(int id,int credit);
	public List<StudentExam> findAll(Page page,String userName,String examrName,int subjectId,String dateOne,String dateTwo);
	public Subject findSubjectName(int id);
	public ExamRule findExamName(int id);
	public StudentExam findStudentExamById(int id);
	public List<StudentExam> findAll(Page page,String userName,String examName);
	public int findQuestionScore(int id);
	public StudentExam findStudentCredit(int userId,int examRuleId);//判断是否有获得学分的记录
	public boolean addUserCredit(int userId);//给user加学分

}
