package com.ztkj.resource.service.impl;

import java.util.List;

import com.ztkj.entity.Study;
import com.ztkj.resource.dao.IResourseDao;
import com.ztkj.resource.dao.impl.ResourceDaoImpl;
import com.ztkj.resource.entity.Resource;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.resource.service.IResourceService;
import com.ztkj.utils.Page;

public class ResourceServiceImpl implements IResourceService {
	IResourseDao resourceDao = new ResourceDaoImpl();
	@Override
	public boolean addResource(Resource resource) {
		// TODO Auto-generated method stub
		return resourceDao.addResource(resource);
	}

	@Override
	public boolean deleteResourceById(int exploreId) {
		// TODO Auto-generated method stub
		return resourceDao.deleteResourceById(exploreId);
	}

	@Override
	public void findResourceByPageLike(Page<Resource> page) {
		int totalCount = resourceDao.findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		
		List<Resource> list = resourceDao.findResourceByPageLike(page);
		page.setList(list);
		
	}
	@Override
	public List<ResourceType> findAllResourceType() {
		// TODO Auto-generated method stub
		return resourceDao.findAllResourceType() ;
	}

	@Override
	public Resource findResourceById(int exploreId) {
		// TODO Auto-generated method stub
		return resourceDao.findResourceById(exploreId);
	}

	@Override
	public boolean updateResource(Resource resource) {
		// TODO Auto-generated method stub
		return resourceDao.updateResource(resource);
	}

	@Override
	public void findsourceByPageLike(Page<Resource> page) {
		int totalCount = resourceDao.findTotalCountsByLike(page);
		page.setPageProperties(totalCount);
		
		List<Resource> list = resourceDao.findsourceByPageLike(page);
		page.setList(list);
		
	}


}
