package com.ztkj.forum.reply.dao;

import java.util.List;

import com.ztkj.forum.entity.Reply;

public interface IReplyDao {

	boolean addReply(Reply reply);
	
	List<Reply> findAllReply();
}
