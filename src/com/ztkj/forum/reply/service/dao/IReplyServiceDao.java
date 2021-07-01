package com.ztkj.forum.reply.service.dao;

import java.util.List;

import com.ztkj.forum.entity.Reply;

public interface IReplyServiceDao {

	boolean addReply(Reply reply);
	
	List<Reply> findAllReply();
}
