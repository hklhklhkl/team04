package com.ztkj.exam.examRuleDetail.service.impl;

import java.util.List;

import com.ztkj.exam.entity.StudentExamDetail;
import com.ztkj.exam.examRuleDetail.dao.IStudentExamDetailDao;
import com.ztkj.exam.examRuleDetail.dao.impl.StudentExamDetailDaoImpl;
import com.ztkj.exam.examRuleDetail.service.IStudentExamDetailService;

public class StudentExamDetailServiceImpl implements IStudentExamDetailService {
	IStudentExamDetailDao studentExamDetail = new StudentExamDetailDaoImpl();
	@Override
	public boolean addStudentExamDetail(StudentExamDetail sed) {
		// TODO Auto-generated method stub
		return studentExamDetail.addStudentExamDetail(sed);
	}

	@Override
	public boolean deleteStudentExamDetail(int studentExamId) {
		// TODO Auto-generated method stub
		return studentExamDetail.deleteStudentExamDetail(studentExamId);
	}

	@Override
	public List<StudentExamDetail> findQuestion(int studentExamId) {
		// TODO Auto-generated method stub
		return studentExamDetail.findQuestion(studentExamId);
	}

	@Override
	public boolean updateAnswer(int id, String answer, int score) {
		// TODO Auto-generated method stub
		return studentExamDetail.updateAnswer(id, answer, score);
	}

	@Override
	public int chaFen(int id) {
		// TODO Auto-generated method stub
		return studentExamDetail.chaFen(id);
	}

}
