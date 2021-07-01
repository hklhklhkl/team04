package com.ztkj.friend.dao;

import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.friend.entity.Apply;
import com.ztkj.friend.entity.Chat;
import com.ztkj.friend.entity.Friend;
import com.ztkj.utils.Page;
import com.ztkj.friend.vo.ApplyInfo;
import com.ztkj.friend.vo.FriendInfo;
import com.ztkj.friend.vo.MsgInfo;

public interface IFriendDao {
	List<User> findUser(String userName, String userNickName, int userId);

	List<ApplyInfo> findApplyList(Page<ApplyInfo> page);

	int findApplyListTotalCount(Page<ApplyInfo> page);

	boolean friendApply(Apply apply, String type);

	boolean addFriend(Friend friend, String type);

	List<FriendInfo> findAllFriendListLike(Page<FriendInfo> page);

	int findAllFriendListTotalCountLike(Page<FriendInfo> page);

	boolean friendBlackList(Friend friend, String type);

	List<FriendInfo> findAllFriendBlackList(Page<FriendInfo> page);

	int findAllFriendBlackListTotalCount(Page<FriendInfo> page);

	boolean deleteFriend(Friend friend, String type);

	boolean ifFriend(int userId, int  friendId);

	boolean addChatMsg(Chat chat);

	List<Chat> findChatMsg(int userId, int friendId);

	List<MsgInfo> findChatNoReadMsgUserList(Page<MsgInfo> page);

	List<Chat> findChatNoReadMsgByUser(int userId, int friendId);

	int findChatNoReadMsgUserListTotalCount(Page<MsgInfo> page);

	List<MsgInfo> findAllChatRecordListLike(Page<MsgInfo> page);

	int findAllChatRecordListTotalCount(Page<MsgInfo> page);

	boolean ignoreMsg(Chat chat);
}
