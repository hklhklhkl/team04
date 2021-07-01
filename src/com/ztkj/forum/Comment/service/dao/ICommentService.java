package com.ztkj.forum.Comment.service.dao;

import java.util.List;

import com.ztkj.forum.entity.Comment;

public interface ICommentService {

	boolean addComment(Comment reply);
	
	List<Comment> findAllComment();
}
