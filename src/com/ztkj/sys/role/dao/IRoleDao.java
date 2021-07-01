package com.ztkj.sys.role.dao;

import java.util.List;
import com.ztkj.sys.entity.Role;
import com.ztkj.utils.Page;

public interface IRoleDao {

	List<Role> findAllRole();
	
	List<Role> findRoleByPageLike(Page<Role> page);;
	
	//查询总记录数
	int findTotalCountByLike(Page<Role> page);
	
	boolean addRole(Role role);
	
	Role findRoleById(int roleId);
	
	boolean updateRoleInfo(Role role);
	
	boolean deleteRoleById(Role role);
}
