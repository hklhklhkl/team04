package com.ztkj.sys.role.service;

import java.util.List;

import com.ztkj.sys.entity.Role;
import com.ztkj.utils.Page;

public interface IRoleService {

	List<Role> findAllRole();
	
	void findRoleByPageLike(Page<Role> page);
	
	boolean addRole(Role role);
	
	Role findRoleById(int roleId);
	
	boolean updateRoleInfo(Role role);
	
	boolean deleteRoleById(Role role);
}
