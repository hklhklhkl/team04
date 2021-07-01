package com.ztkj.sys.user.service.impl;

import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.sys.user.dao.IUserDao;
import com.ztkj.sys.user.dao.impl.UserDaoImpl;
import com.ztkj.sys.user.service.IUserService;
import com.ztkj.utils.Page;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();
	@Override
	public User login(String userName, String userPass) {
		return userDao.login(userName,userPass);
	}
	@Override
	public void findUserByPageLike(Page<User> page) {
		int totalCount = userDao.findTotalCountByLike(page);
		page.setPageProperties(totalCount);
		List<User> list = userDao.findUserByPageLike(page);
		page.setList(list);
	}
	@Override
	public boolean addUser(User user) {		
		return userDao.addUser(user);
	}
	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}
	@Override
	public boolean updateSelfInfo(User user) {
		return userDao.updateSelfInfo(user);
	}
	@Override
	public boolean updateUserInfo(User user) {
		return userDao.updateUserInfo(user);
	}
	@Override
	public User findUserById(int userId) {
		return userDao.findUserById(userId);
	}
	@Override
	public boolean deleteUserInfoById(User user) {	
		return userDao.deleteUserInfoById(user);
	}
	@Override
	public boolean updateSelfPass(User user) {
		return userDao.updateSelfPass(user);
	}
	@Override
	public User findNameById(int id) {
		// TODO Auto-generated method stub
		return userDao.findNameById(id);
	}	

}
