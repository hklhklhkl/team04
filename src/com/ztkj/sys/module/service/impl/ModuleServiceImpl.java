package com.ztkj.sys.module.service.impl;

import java.util.List;

import com.ztkj.sys.entity.Auth;
import com.ztkj.sys.entity.Module;
import com.ztkj.sys.entity.Role;
import com.ztkj.sys.module.dao.IModuleDao;
import com.ztkj.sys.module.dao.impl.ModuleDaoImpl;
import com.ztkj.sys.module.service.IModuleService;
import com.ztkj.utils.Page;

public class ModuleServiceImpl implements IModuleService {

	IModuleDao moduleDao = new ModuleDaoImpl();

	@Override
	public List<Module> findModuleByUserId(int userId) {
		return moduleDao.findModuleByUserId(userId);
	}

	@Override
	public List<Module> findAllModule() {
		return moduleDao.findAllModule();
	}

	@Override
	public List<Auth> findAuthByRoleId(int roleId) {
		return moduleDao.findAuthByRoleId(roleId);
	}

	@Override
	public boolean deleteAuthByRoleId(int roleId) {
		return moduleDao.deleteAuthByRoleId(roleId);
	}

	@Override
	public boolean addAuth(Auth auth) {
		return moduleDao.addAuth(auth);
	}

	@Override
	public void findModuleByPageLike(Page<Module> page) {
		int totalCount = moduleDao.findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		List<Module> list = moduleDao.findModuleByPageLike(page);
		page.setList(list);
	}

	@Override
	public boolean addModule(Module module) {
		return moduleDao.addModule(module);
	}

	@Override
	public boolean updateModule(Module module) {
		return moduleDao.updateModule(module);
	}

	@Override
	public boolean deleteModule(Module module) {
		return moduleDao.deleteModule(module);
	}

	@Override
	public Module findModuleById(int moduleId) {
		return moduleDao.findModuleById(moduleId);
	}
	
}
