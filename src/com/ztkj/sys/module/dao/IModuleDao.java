package com.ztkj.sys.module.dao;

import java.util.List;

import com.ztkj.sys.entity.Auth;
import com.ztkj.sys.entity.Module;
import com.ztkj.utils.Page;

public interface IModuleDao {

	List<Module> findModuleByUserId(int userId);
	
	List<Module> findAllModule();
	
	List<Auth> findAuthByRoleId(int roleId);
	
	boolean deleteAuthByRoleId(int roleId);
	
	boolean addAuth(Auth auth);
	
	List<Module> findModuleByPageLike(Page<Module> page);	
	//查询总记录数
	int findTotalCountByLike(Page<Module> page);
	
	boolean addModule(Module module);
	
	boolean updateModule(Module module);
	
	boolean deleteModule(Module module);
	
	Module findModuleById(int moduleId);
}
