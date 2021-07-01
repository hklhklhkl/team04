package com.ztkj.forum.entity;

import java.util.Date;

public class Post {

	private int postId;
	private String postContent;
	private int blockId;
	private int userId;
    private Date postDate;
    private int postState;
    private int postUp;
    private String postComment;
    private Date postNow;
    private int postNowId;
    private String blockName;
    private String userName;
    private int postGood;
    private String postSaw;
	public String getPostSaw() {
		return postSaw;
	}
	public void setPostSaw(String postSaw) {
		this.postSaw = postSaw;
	}
	
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPostComment() {
		return postComment;
	}
	public void setPostComment(String postComment) {
		this.postComment = postComment;
	}
	public Date getPostNow() {
		return postNow;
	}
	public void setPostNow(Date postNow) {
		this.postNow = postNow;
	}
	
	public int getPostNowId() {
		return postNowId;
	}
	public void setPostNowId(int postNowId) {
		this.postNowId = postNowId;
	}
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public int getBlockId() {
		return blockId;
	}
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getPostState() {
		return postState;
	}
	public void setPostState(int postState) {
		this.postState = postState;
	}
	public int getPostUp() {
		return postUp;
	}
	public void setPostUp(int postUp) {
		this.postUp = postUp;
	}
	public int getPostGood() {
		return postGood;
	}
	public void setPostGood(int postGood) {
		this.postGood = postGood;
	}
	
	
}
