package com.ztkj.exam.subjects.service.impl;

import java.util.List;

import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.subjects.dao.ISubjectDao;
import com.ztkj.exam.subjects.dao.impl.SubjectDaoImpl;
import com.ztkj.exam.subjects.service.ISubjectService;
import com.ztkj.exam.utils.Page;

public class SubjectServiceImpl implements ISubjectService{
	ISubjectDao subjectDao = new SubjectDaoImpl();
	public boolean updateSubjectName(Subject subject){
		return subjectDao.updateSubjectName(subject);
	}
	@Override
	public boolean addSubject(Subject subject) {
		// TODO Auto-generated method stub
		return subjectDao.addSubject(subject);
	}

	@Override
	public boolean updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		return subjectDao.updateSubject(subject);
	}

	@Override
	public boolean dropSubject(int subjectId) {
		// TODO Auto-generated method stub
		return subjectDao.deleteSubject(subjectId);
	}


	@Override
	public List<Subject> findAllSubject(Page page) {
		// TODO Auto-generated method stub
		int total = subjectDao.findTotalCount();
		page.setPageProperties(total);
		return subjectDao.findAllSubject(page);
	}

	@Override
	public List<Subject> findAllSubjectByPage(Page page, String subjectName) {
		// TODO Auto-generated method stub
		int total = subjectDao.findTotalCount(subjectName);
		page.setPageProperties(total);
		return subjectDao.findSubjectByPage(page,subjectName);
	}
	@Override
	public Subject findSubjectById(int subjectId) {
		// TODO Auto-generated method stub
		return subjectDao.findSubjectById(subjectId);
	}
	@Override
	public List<Subject> findAllSubject() {
		// TODO Auto-generated method stub
		return subjectDao.findAllSubject();
	}
	
}
