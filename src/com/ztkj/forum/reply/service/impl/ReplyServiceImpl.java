package com.ztkj.forum.reply.service.impl;

import java.util.List;

import com.ztkj.forum.entity.Reply;
import com.ztkj.forum.reply.dao.IReplyDao;
import com.ztkj.forum.reply.dao.impl.ReplyDaoImpl;
import com.ztkj.forum.reply.service.dao.IReplyServiceDao;

public class ReplyServiceImpl implements IReplyServiceDao {
	IReplyDao replyDao = new ReplyDaoImpl();
	@Override
	public boolean addReply(Reply reply) {
		
		return replyDao.addReply(reply);
	}

	@Override
	public List<Reply> findAllReply() {
		
		return replyDao.findAllReply();
	}

}
