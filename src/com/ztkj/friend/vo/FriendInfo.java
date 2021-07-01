package com.ztkj.friend.vo;

import java.util.Date;

public class FriendInfo {
	private int userId;
	private int friendId;
	private String userName;
	private String userNickName;
	private String userUri;
	private Date userCreatDate;
	private int userExamIntegral;
	private int userForumIntegral;

	public Date getUserCreatDate() {
		return userCreatDate;
	}

	public void setUserCreatDate(Date userCreatDate) {
		this.userCreatDate = userCreatDate;
	}

	public int getUserExamIntegral() {
		return userExamIntegral;
	}

	public void setUserExamIntegral(int userExamIntegral) {
		this.userExamIntegral = userExamIntegral;
	}

	public int getUserForumIntegral() {
		return userForumIntegral;
	}

	public void setUserForumIntegral(int userForumIntegral) {
		this.userForumIntegral = userForumIntegral;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserUri() {
		return userUri;
	}

	public void setUserUri(String userUri) {
		this.userUri = userUri;
	}

}
