package com.ztkj.exam.subjects.service;

import java.util.List;

import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.utils.Page;

public interface ISubjectService {
	public boolean addSubject(Subject subject);
	public boolean updateSubject(Subject subject);
	public boolean dropSubject(int subjectId);
	public List<Subject> findAllSubject(Page page);
	public List<Subject> findAllSubjectByPage(Page page,String subjectName);
	public boolean updateSubjectName(Subject subject);
	public Subject findSubjectById(int subjectId);
	public List<Subject> findAllSubject();
}
