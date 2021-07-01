package com.ztkj.sys.role.service.impl;

import java.util.List;

import com.ztkj.sys.role.dao.impl.RoleDaoImpl;
import com.ztkj.sys.role.service.IRoleService;
import com.ztkj.utils.Page;
import com.ztkj.sys.entity.Role;
import com.ztkj.sys.role.dao.IRoleDao;

public class RoleServiceImpl implements IRoleService {

	IRoleDao roleDao = new RoleDaoImpl();
	@Override
	public List<Role> findAllRole() {		
		return roleDao.findAllRole();
	}
	@Override
	public void findRoleByPageLike(Page<Role> page) {
		int totalCount = roleDao.findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		List<Role> list = roleDao.findRoleByPageLike(page);
		page.setList(list);		
	}
	@Override
	public boolean addRole(Role role) {
		return roleDao.addRole(role);
	}
	@Override
	public Role findRoleById(int roleId) {		
		return roleDao.findRoleById(roleId);
	}
	@Override
	public boolean updateRoleInfo(Role role) {
		return roleDao.updateRoleInfo(role);
	}
	@Override
	public boolean deleteRoleById(Role role) {
		return roleDao.deleteRoleById(role);
	}

}
