package com.ztkj.study.service.impl;

import java.util.List;
import com.ztkj.entity.Study;
import com.ztkj.study.dao.IStudyDao;
import com.ztkj.study.dao.impl.StudyDaoImpl;
import com.ztkj.study.service.IStudyService;
import com.ztkj.utils.Page;

public class StudyServiceImpl implements IStudyService {
	IStudyDao studyDao = new StudyDaoImpl();
	@Override
	public boolean addStudy(Study study){
		// TODO Auto-generated method stub
		return studyDao.addStudy(study);
	}

	@Override
	public List<Study> findAllStudy(){
		// TODO Auto-generated method stub
		return studyDao.findAllStudy();
	}

	@Override
	public Study findStudyById(int noteId) {
		// TODO Auto-generated method stub
		return studyDao.findStudyById(noteId);
	}

	@Override
	public boolean updateStudy(Study study) {
		// TODO Auto-generated method stub
		return studyDao.updateStudy(study);
	}

	@Override
	public boolean deleteStudyById(int noteId) {
		// TODO Auto-generated method stub
		return studyDao.deleteStudyById(noteId);
	}

	@Override
	public void findStudyByPageLike(Page<Study> page) {
		int totalCount = studyDao.findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		
		List<Study> list = studyDao.findEmpByPageLike(page);
		page.setList(list);
		
	}

}
