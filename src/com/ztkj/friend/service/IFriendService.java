package com.ztkj.friend.service;

import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.friend.entity.Apply;
import com.ztkj.friend.entity.Chat;
import com.ztkj.friend.entity.Friend;
import com.ztkj.utils.Page;
import com.ztkj.friend.vo.ApplyInfo;
import com.ztkj.friend.vo.FriendInfo;
import com.ztkj.friend.vo.MsgInfo;

public interface IFriendService {
	List<User> findUser(String userName, String userNickName, int userId);

	void findApplyList(Page<ApplyInfo> page);

	boolean friendApply(Apply apply, String type);

	boolean addFriend(Friend friend, String type);

	void findAllFriendListLike(Page<FriendInfo> page);

	boolean friendBlackList(Friend friend, String type);

	void findAllFriendBlackList(Page<FriendInfo> page);

	boolean deleteFriend(Friend friend, String type);

	boolean ifFriend(int userId, int  friendId);

	boolean addChatMsg(Chat chat);

	List<Chat> findChatMsg(int userId, int friendId);

	void findChatNoReadMsgUserList(Page<MsgInfo> page);

	List<Chat> findChatNoReadMsgByUser(int userId, int friendId);

	void findAllChatRecordListLike(Page<MsgInfo> page);

	boolean ignoreMsg(Chat chat);
}
