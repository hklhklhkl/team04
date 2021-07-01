package com.ztkj.resourcetype.service.impl;

import java.util.List;

import com.ztkj.resource.entity.Resource;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.resourcetype.dao.IResourceTypeDao;
import com.ztkj.resourcetype.dao.impl.ResourceTypeDaoImpl;
import com.ztkj.resourcetype.service.IResourceTypeService;
import com.ztkj.utils.Page;


public class ResourceTypeServiceImpl implements IResourceTypeService {
	IResourceTypeDao  resourceTypeDao  = new ResourceTypeDaoImpl();
	@Override
	public boolean addResourceType(ResourceType resourceType) {
		// TODO Auto-generated method stub
		return resourceTypeDao.addResourceType(resourceType);
	}

	@Override
	public void findResourceTypeByPageLike(Page<ResourceType> page) {
		int totalCount = resourceTypeDao .findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		
		List<ResourceType> list = resourceTypeDao.findResourceTypeByPageLike(page);
		page.setList(list);
		
	}

	@Override
	public ResourceType findResourceTypeById(int exploreTypeId) {
		// TODO Auto-generated method stub
		return resourceTypeDao.findResourceTypeById(exploreTypeId);
	}

	@Override
	public boolean updateResourceType(ResourceType resourceType) {
		// TODO Auto-generated method stub
		return resourceTypeDao.updateResourceType(resourceType);
	}

	@Override
	public boolean deleteResourceTypeById(int exploreTypeId) {
		// TODO Auto-generated method stub
		return resourceTypeDao.deleteResourceTypeById(exploreTypeId);
	}

	@Override
	public boolean updateResourceState(ResourceType resourceType) {
		// TODO Auto-generated method stub
		return resourceTypeDao.updateResourceState(resourceType);
	}

	@Override
	public boolean updateResourceStateTwo(ResourceType resourceType) {
		// TODO Auto-generated method stub
		return resourceTypeDao.updateResourceStateTwo(resourceType);
	}

	

	


}
