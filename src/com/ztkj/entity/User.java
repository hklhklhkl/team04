package com.ztkj.entity;

import java.util.Date;

public class User {
	
	private int userId;	
	private String userNickname;		//�û�����
	private String userName;			//�˺�
	private String userPass;			//����
	private String userSex;		
	private Date userBirth;
	private String userUri;				//�û�ͷ��
	private int userState;				//�ж��û�״̬����������ע��
	private int roleId;					//Ȩ��id
	private int userForumIntegral;		//��̳����
	private int userExamIntegral;		//���Ի���
	private Date userCreateDate;		//����ʱ��
	private int userCreatorId;			//�����û���Id
	

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
