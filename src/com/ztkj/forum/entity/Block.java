package com.ztkj.forum.entity;

import java.sql.Date;

public class Block {

	private int blockId;
	private String blockName;
	private int userId;
	private String blockBiref;
	private String blockPhoto;
	private int blockBuff;
	private Date blockDate;
	private String userName;
	private int count;
	private String postContent;
	private int postState;
	private int postId;
	
	@Override
	public String toString() {
		return "Block [blockId=" + blockId + ", blockName=" + blockName + ", userId=" + userId + ", blockBiref="
				+ blockBiref + ", blockPhoto=" + blockPhoto + ", blockBuff=" + blockBuff + ", blockDate=" + blockDate
				+ ", userName=" + userName + ", count=" + count + ", postContent=" + postContent + ", postState="
				+ postState + ", postId=" + postId + "]";
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getPostState() {
		return postState;
	}
	public void setPostState(int postState) {
		this.postState = postState;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int i) {
		this.count = i;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBlockDate() {
		return blockDate;
	}
	public void setBlockDate(Date date) {
		this.blockDate = date;
	}
	public int getBlockBuff() {
		return blockBuff;
	}
	public void setBlockBuff(int blockBuff) {
		this.blockBuff = blockBuff;
	}
	public String getBlockBiref() {
		return blockBiref;
	}
	public void setBlockBiref(String blockBiref) {
		this.blockBiref = blockBiref;
	}
	public String getBlockPhoto() {
		return blockPhoto;
	}
	public void setBlockPhoto(String blockPhoto) {
		this.blockPhoto = blockPhoto;
	}
	public int getBlockId() {
		return blockId;
	}
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
