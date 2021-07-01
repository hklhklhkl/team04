package com.ztkj.sys.user.service;

import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.utils.Page;

public interface IUserService {

	User login(String userName,String userPass);
	
	void findUserByPageLike(Page<User> page);
	
	boolean addUser(User user);
	
	List<User> findAllUser();
	
	boolean updateSelfInfo(User user);
	boolean updateSelfPass(User user);
	
	boolean updateUserInfo(User user);
	
	User findUserById(int userId);
	User findNameById(int id);
	boolean deleteUserInfoById(User user);
}
