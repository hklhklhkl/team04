package com.ztkj.sys.module.service;

import java.util.List;

import com.ztkj.sys.entity.Auth;
import com.ztkj.sys.entity.Module;
import com.ztkj.utils.Page;

public interface IModuleService {

	List<Module> findModuleByUserId(int userId);
	
	List<Module> findAllModule();
	
	List<Auth> findAuthByRoleId(int roleId);
	
	boolean deleteAuthByRoleId(int roleId);
	
	boolean addAuth(Auth auth);

	void findModuleByPageLike(Page<Module> page);
	
	boolean addModule(Module module);
	
	boolean updateModule(Module module);
	
	boolean deleteModule(Module module);
	
	Module findModuleById(int moduleId);
}
