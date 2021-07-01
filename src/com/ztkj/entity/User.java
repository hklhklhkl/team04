package com.ztkj.entity;

import java.util.Date;

public class User {
	
	private int userId;	
	private String userNickname;		//用户名称
	private String userName;			//账号
	private String userPass;			//密码
	private String userSex;		
	private Date userBirth;
	private String userUri;				//用户头像
	private int userState;				//判断用户状态是正常还是注销
	private int roleId;					//权限id
	private int userForumIntegral;		//论坛积分
	private int userExamIntegral;		//考试积分
	private Date userCreateDate;		//创建时间
	private int userCreatorId;			//创建用户的Id
	

	public int getUserState() {
		return userState;
	}
	public void setUserState(int userState) {
		this.userState = userState;
	}
	public Date getUserCreateDate() {
		return userCreateDate;
	}
	public void setUserCreateDate(Date userCreateDate) {
		this.userCreateDate = userCreateDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserUri() {
		return userUri;
	}
	public void setUserUri(String userUri) {
		this.userUri = userUri;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUserForumIntegral() {
		return userForumIntegral;
	}
	public void setUserForumIntegral(int userForumIntegral) {
		this.userForumIntegral = userForumIntegral;
	}
	public int getUserExamIntegral() {
		return userExamIntegral;
	}
	public void setUserExamIntegral(int userExamIntegral) {
		this.userExamIntegral = userExamIntegral;
	}
	public int getUserCreatorId() {
		return userCreatorId;
	}
	public void setUserCreatorId(int userCreatorId) {
		this.userCreatorId = userCreatorId;
	}
	
}
