package com.ztkj.resourcetype.service;


import java.util.List;

import com.ztkj.resource.entity.Resource;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.utils.Page;

public interface IResourceTypeService {
	boolean addResourceType(ResourceType resourceType);
	void  findResourceTypeByPageLike(Page<ResourceType>  page);
	ResourceType findResourceTypeById(int exploreTypeId);
	boolean updateResourceType(ResourceType resourceType);
	boolean deleteResourceTypeById(int  exploreTypeId);
	boolean updateResourceState(ResourceType resourceType);
	boolean updateResourceStateTwo(ResourceType resourceType);
}
