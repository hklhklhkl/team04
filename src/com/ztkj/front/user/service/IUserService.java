package com.ztkj.front.user.service;

import java.util.List;

import com.ztkj.entity.Pic;
import com.ztkj.entity.User;
import com.ztkj.front.vo.GoodPost;
import com.ztkj.front.vo.HotPost;
import com.ztkj.front.vo.LunTan;
import com.ztkj.sys.entity.Role;

public interface IUserService {
	
	User front_login(String userName,String userPass);

	boolean add_user(User user);

	boolean updatePic(User user);

	List<User> findUserName();
	
	String findRoleName(int userId);
	
	int findPsostTotal(int userId);
	
	List<Pic> findPicture();
	
	List<LunTan> findLunTanName();
	
	List<GoodPost> findGoodPost();
	
	List<HotPost> findHotPost();
}
