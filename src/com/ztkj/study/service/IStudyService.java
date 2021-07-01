package com.ztkj.study.service;

import java.util.List;
import com.ztkj.entity.Study;
import com.ztkj.utils.Page;

public interface IStudyService {
	boolean addStudy(Study study);
	List<Study> findAllStudy();
	Study findStudyById(int noteId);
	boolean updateStudy(Study study);
	boolean deleteStudyById(int noteId);
	//��ѯ����Ա��
		/*List<Study>findEmpByPage(Page page);*/
		//��ѯ
	void  findStudyByPageLike(Page<Study>  page);

}
