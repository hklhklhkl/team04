package com.ztkj.forum.entity;

public class Reply {

	private int replyId;
	private int replyUserId;
	private int repliedUserId;
	private String replyContent;
	private int commentId;
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(int replyUserId) {
		this.replyUserId = replyUserId;
	}
	public int getRepliedUserId() {
		return repliedUserId;
	}
	public void setRepliedUserId(int repliedUserId) {
		this.repliedUserId = repliedUserId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
}
