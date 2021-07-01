package com.ztkj.study.dao;

import java.util.List;
import com.ztkj.entity.Study;
import com.ztkj.utils.Page;


public interface IStudyDao {
	boolean addStudy(Study study);
	List<Study> findAllStudy();
	Study findStudyById(int noteId);
	boolean updateStudy(Study study);
	boolean deleteStudyById(int noteId);
	//��ѯ����Ա��
		/*List<Study>findEmpByPage(Page page);*/
		//��ѯ
	List<Study>findEmpByPageLike(Page<Study> page);
	int findTotalCountByLike(Page<Study> page);

}
