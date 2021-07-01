package com.ztkj.resource.dao;

import java.util.List;
import com.ztkj.resource.entity.Resource;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.utils.Page;


public interface IResourseDao {
	boolean addResource(Resource resource);
	boolean deleteResourceById(int exploreId);
	//��ѯ����Ա��
		/*List<Study>findEmpByPage(Page page);*/
		//��ѯ
	List<Resource>findResourceByPageLike(Page<Resource> page);
	int findTotalCountByLike(Page<Resource> page);
	List<ResourceType> findAllResourceType();
	boolean addResourceType(ResourceType resourceType);
	Resource findResourceById(int exploreId);
	boolean updateResource(Resource resource);
	List<Resource>findsourceByPageLike(Page<Resource> page);
	int findTotalCountsByLike(Page<Resource> page);
	
}
