package com.ztkj.resource.service;

import java.util.List;


import com.ztkj.resource.entity.Resource;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.utils.Page;

public interface IResourceService {
	boolean addResource(Resource resource);
	boolean deleteResourceById(int exploreId);
	//��ѯ����Ա��
		/*List<Study>findEmpByPage(Page page);*/
		//��ѯ
	void  findResourceByPageLike(Page<Resource>  page);
	List<ResourceType> findAllResourceType();
	Resource findResourceById(int exploreId);
	boolean updateResource(Resource resource);
	void  findsourceByPageLike(Page<Resource>  page);
}
