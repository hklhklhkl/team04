package com.ztkj.resourcetype.dao;

import java.util.List;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.utils.Page;

public interface IResourceTypeDao {
	boolean addResourceType(ResourceType resourceType);
	List<ResourceType>findResourceTypeByPageLike(Page<ResourceType> page);
	int findTotalCountByLike(Page<ResourceType> page);
	ResourceType findResourceTypeById(int exploreTypeId);
	boolean updateResourceType(ResourceType resourceType);
	boolean deleteResourceTypeById(int  exploreTypeId);
	boolean updateResourceState(ResourceType resourceType);
	boolean updateResourceStateTwo(ResourceType resourceType);
}
