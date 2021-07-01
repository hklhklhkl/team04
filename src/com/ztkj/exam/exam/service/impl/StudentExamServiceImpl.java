package com.ztkj.exam.exam.service.impl;

import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.entity.StudentExam;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.exam.dao.IStudentExamDao;
import com.ztkj.exam.exam.dao.impl.StudentExamDaoImpl;
import com.ztkj.exam.exam.service.IStudentExamService;
import com.ztkj.exam.utils.Page;

public class StudentExamServiceImpl implements IStudentExamService {
		IStudentExamDao stdentExamDao =new StudentExamDaoImpl();
	@Override
	public boolean addStudentExam(StudentExam studentExam) {
		// TODO Auto-generated method stub
		return stdentExamDao.addStudentExam(studentExam);
	}

	@Override
	public boolean deleteStudentExam(int i) {
		// TODO Auto-generated method stub
		return stdentExamDao.deleteStudentExam(i);
	}

	@Override
	public int findSeq() {
		// TODO Auto-generated method stub
		return stdentExamDao.findSeq();
	}

	@Override
	public boolean jiaFen(int id, int score) {
		// TODO Auto-generated method stub
		return stdentExamDao.jiaFen(id, score);
	}

	@Override
	public List<StudentExam> findAll(Page page, String userName, String examrName, int subjectId, String dateOne,
			String dateTwo) {
		int total = stdentExamDao.findTotalCount(userName, examrName, subjectId, dateOne, dateTwo);
		page.setPageProperties(total);
		return stdentExamDao.findAll(page, userName, examrName, subjectId, dateOne, dateTwo);
	}

	@Override
	public Subject findSubjectName(int id) {
		// TODO Auto-generated method stub
		return stdentExamDao.findSubjectName(id);
	}

	@Override
	public ExamRule findExamName(int id) {
		// TODO Auto-generated method stub
		return stdentExamDao.findExamName(id);
	}

	@Override
	public List<StudentExam> findAll(Page page, String userName, String examName) {
		int total = stdentExamDao.findTotalCount(userName, examName);
		page.setPageProperties(total);
		return stdentExamDao.findAll(page, userName, examName);
	}

	@Override
	public StudentExam findStudentExamById(int id) {
		// TODO Auto-generated method stub
		return stdentExamDao.findStudentExamById(id);
	}

	@Override
	public int findQuestionScore(int id) {
		// TODO Auto-generated method stub
		return stdentExamDao.findQuestionScore(id);
	}

	@Override
	public boolean jiaFen2(int id, int score) {
		// TODO Auto-generated method stub
		return stdentExamDao.jiaFen2(id, score);
	}

	@Override
	public boolean jiaCredit(int id, int credit) {
		// TODO Auto-generated method stub
		return stdentExamDao.jiaCredit(id, credit);
	}

	@Override
	public StudentExam findStudentCredit(int userId, int examRuleId) {
		// TODO Auto-generated method stub
		return stdentExamDao.findStudentCredit(userId, examRuleId);
	}

	@Override
	public boolean addUserCredit(int userId) {
		// TODO Auto-generated method stub
		return stdentExamDao.addUserCredit( userId);
	}

}
