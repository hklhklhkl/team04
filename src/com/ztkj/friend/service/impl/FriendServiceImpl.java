package com.ztkj.friend.service.impl;

import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.friend.dao.IFriendDao;
import com.ztkj.friend.dao.impl.FriendDaoImpl;
import com.ztkj.friend.entity.Apply;
import com.ztkj.friend.entity.Chat;
import com.ztkj.friend.entity.Friend;
import com.ztkj.friend.service.IFriendService;
import com.ztkj.utils.Page;
import com.ztkj.friend.vo.ApplyInfo;
import com.ztkj.friend.vo.FriendInfo;
import com.ztkj.friend.vo.MsgInfo;

public class FriendServiceImpl implements IFriendService {
	IFriendDao friendDao = new FriendDaoImpl();

	@Override
	public List<User> findUser(String userName, String userNickName, int userId) {
		return friendDao.findUser(userName, userNickName, userId);
	}

	@Override
	public void findApplyList(Page<ApplyInfo> page) {
		page.setPageProperties(friendDao.findApplyListTotalCount(page));
		page.setList(friendDao.findApplyList(page));
	}

	@Override
	public boolean friendApply(Apply apply, String type) {
		return friendDao.friendApply(apply,type);
	}

	@Override
	public boolean addFriend(Friend friend, String type) {
		return friendDao.addFriend(friend,type);
	}

	@Override
	public void findAllFriendListLike(Page<FriendInfo> page) {
		page.setPageProperties(friendDao.findAllFriendListTotalCountLike(page));
		page.setList(friendDao.findAllFriendListLike(page));
	}

	@Override
	public boolean friendBlackList(Friend friend, String type) {
		return friendDao.friendBlackList(friend,type);
	}

	@Override
	public void findAllFriendBlackList(Page<FriendInfo> page) {
		page.setPageProperties(friendDao.findAllFriendBlackListTotalCount(page));
		page.setList(friendDao.findAllFriendBlackList(page));
	}

	@Override
	public boolean deleteFriend(Friend friend, String type) {
		return friendDao.deleteFriend(friend,type);
	}

	@Override
	public boolean ifFriend(int userId, int friendId) {
		return friendDao.ifFriend(userId,friendId);
	}

	@Override
	public boolean addChatMsg(Chat chat) {
		return friendDao.addChatMsg(chat);
	}

	@Override
	public List<Chat> findChatMsg(int userId, int friendId) {
		return friendDao.findChatMsg(userId, friendId);
	}

	@Override
	public void findChatNoReadMsgUserList(Page<MsgInfo> page) {
		page.setPageProperties(friendDao.findChatNoReadMsgUserListTotalCount(page));
		page.setList(friendDao.findChatNoReadMsgUserList(page));
	}

	@Override
	public List<Chat> findChatNoReadMsgByUser(int userId, int friendId) {
		return friendDao.findChatNoReadMsgByUser(userId,friendId);
	}

	@Override
	public void findAllChatRecordListLike(Page<MsgInfo> page) {
		page.setPageProperties(friendDao.findAllChatRecordListTotalCount(page));
		page.setList(friendDao.findAllChatRecordListLike(page));
	}

	@Override
	public boolean ignoreMsg(Chat chat) {
		return friendDao.ignoreMsg(chat);
	}
}
