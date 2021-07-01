package com.ztkj.exam.subjects.dao;

import java.util.List;


import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.utils.Page;

public interface ISubjectDao {
	public boolean addSubject(Subject subject);
	public Subject findSubjectById(int subjectId);
	public boolean updateSubject(Subject subject); //¶³½á
	public boolean updateSubjectName(Subject subject);//ÐÞ¸Ä
	public boolean deleteSubject(int subjectId);
	public List<Subject> findAllSubject(Page page);
	int findTotalCount();
	public List<Subject> findSubjectByPage(Page page,String subjectName);
	int findTotalCount(String subjectName);
	public List<Subject> findAllSubject();
}
