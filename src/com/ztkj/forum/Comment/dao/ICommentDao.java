package com.ztkj.forum.Comment.dao;

import java.util.List;

import com.ztkj.forum.entity.Comment;

public interface ICommentDao {

	boolean addComment(Comment reply);
	
	List<Comment> findAllComment();
}
