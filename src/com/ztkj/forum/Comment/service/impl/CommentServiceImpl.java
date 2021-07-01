package com.ztkj.forum.Comment.service.impl;

import java.util.List;

import com.ztkj.forum.Comment.dao.ICommentDao;
import com.ztkj.forum.Comment.dao.impl.CommentDaoImpl;
import com.ztkj.forum.Comment.service.dao.ICommentService;
import com.ztkj.forum.entity.Comment;

public class CommentServiceImpl implements ICommentService {

	ICommentDao cd = new CommentDaoImpl();
	@Override
	public boolean addComment(Comment reply) {
		
		return cd.addComment(reply);
	}

	@Override
	public List<Comment> findAllComment() {
		
		return cd.findAllComment();
	}

}
