package com.ztkj.front.user.service.impl;

import java.util.List;

import com.ztkj.entity.Pic;
import com.ztkj.entity.User;
import com.ztkj.front.user.dao.IUserDao;
import com.ztkj.front.user.dao.impl.UserDaoImpl;
import com.ztkj.front.user.service.IUserService;
import com.ztkj.front.vo.GoodPost;
import com.ztkj.front.vo.HotPost;
import com.ztkj.front.vo.LunTan;
import com.ztkj.sys.entity.Role;

public class UserServiceImpl implements IUserService{

	IUserDao userdao = new UserDaoImpl();
	@Override
	public User front_login(String userName, String userPass) {
		return userdao.front_login(userName, userPass);
	}
	@Override
	public boolean add_user(User user) {
		return userdao.add_user(user);
	}
	@Override
	public boolean updatePic(User user) {
		return userdao.updatePic(user);
	}
	@Override
	public List<User> findUserName() {
		return userdao.findUserName();
	}
	
	@Override
	public List<LunTan> findLunTanName() {
		return userdao.findLunTanName();
	}
	@Override
	public String findRoleName(int userId) {
		return userdao.findRoleName(userId);
	}
	@Override
	public List<Pic> findPicture() {
		return userdao.findPicture();
	}
	@Override
	public List<HotPost> findHotPost() {
		return userdao.findHotPost();
	}
	@Override
	public List<GoodPost> findGoodPost() {
		return userdao.findGoodPost();
	}
	@Override
	public int findPsostTotal(int userId) {
		return userdao.findPsostTotal(userId);
	}

	

}
