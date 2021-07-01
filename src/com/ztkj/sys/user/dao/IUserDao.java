package com.ztkj.sys.user.dao;

import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.utils.Page;

public interface IUserDao {

	User login(String userName,String userPass);
	
	List<User> findUserByPageLike(Page<User> page);;
	
	//查询总记录数
	int findTotalCountByLike(Page<User> page);
	
	boolean addUser(User user);

	List<User> findAllUser();
	
	boolean updateSelfInfo(User user);
	boolean updateSelfPass(User user);
	
	boolean updateUserInfo(User user);
	
	User findUserById(int userId);
	
	boolean deleteUserInfoById(User user);
	User findNameById(int id);
	
}
