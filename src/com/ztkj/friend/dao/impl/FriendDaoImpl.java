package com.ztkj.friend.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.friend.dao.IFriendDao;
import com.ztkj.friend.entity.Apply;
import com.ztkj.friend.entity.Chat;
import com.ztkj.friend.entity.Friend;
import com.ztkj.friend.vo.ApplyInfo;
import com.ztkj.friend.vo.FriendInfo;
import com.ztkj.friend.vo.MsgInfo;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class FriendDaoImpl implements IFriendDao {

	@Override
	public List<User> findUser(String userName, String userNickName, int userId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		User user = null;
		try {
			StringBuffer sql = new StringBuffer(
					"select user_id, user_name, user_nickname, user_uri from t_user where user_id != ?");
			if (userName != null && !"".equals(userName)) {
				sql.append(" and user_name like '%").append(userName).append("%'");
			}
			if (userNickName != null && !"".equals(userNickName)) {
				sql.append(" and user_nickname like '%").append(userNickName).append("%'");
			}
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setInt(1, userId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserNickname(rs.getString("user_nickname"));
				user.setUserUri(rs.getString("user_uri"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<ApplyInfo> findApplyList(Page<ApplyInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ApplyInfo> list = new ArrayList<ApplyInfo>();
		try {
			String sql = "select * from (select t1.*, t2.user_name, t2.user_nickname, t2.user_uri, rownum rn from t_apply t1, t_user t2 where t1.friend_id = ? and t1.status = 0 and t1.user_id = t2.user_id) where rn between ? and ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			pstm.setInt(2, page.getStartIndex());
			pstm.setInt(3, page.getEndIndex());
			rs = pstm.executeQuery();
			while (rs.next()) {
				ApplyInfo applyInfo = new ApplyInfo();
				applyInfo.setUserId(rs.getInt("user_id"));
				applyInfo.setFriendId(rs.getInt("friend_id"));
				applyInfo.setStatus(rs.getInt("status"));
				applyInfo.setUserName(rs.getString("user_name"));
				applyInfo.setUserNickName(rs.getString("user_nickname"));
				applyInfo.setUserUri(rs.getString("user_uri"));
				list.add(applyInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findApplyListTotalCount(Page<ApplyInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(1) from t_apply t1, t_user t2 where t1.friend_id = ? and t1.status = 0 and t1.friend_id = t2.user_id";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public boolean friendApply(Apply apply, String type) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "";
			if("if".equals(type)){
				sql = "select * from t_apply where user_id = ? and friend_id = ?";
			}else if ("add".equals(type)) {
				sql = "insert into t_apply values (?,?,0)";
			} else if ("agree".equals(type)) {
				sql = "update t_apply set status = 1 where user_id = ? and friend_id = ?";
			} else if ("refuse".equals(type)) {
				sql = "delete from t_apply where user_id = ? and friend_id = ?";
			}
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, apply.getUserId());
			pstm.setInt(2, apply.getFriendId());
			int result = pstm.executeUpdate();
			if (result != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean addFriend(Friend friend, String type) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "";
			if ("one".equals(type)) {
				sql = "insert into t_friend values (?,?,1)";
			} else if ("two".equals(type)) {
				sql = "insert into t_friend values (?,?,1)";
			}

			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			if ("one".equals(type)) {
				pstm.setInt(1, friend.getUserId());
				pstm.setInt(2, friend.getFriendId());
			} else if ("two".equals(type)) {
				pstm.setInt(1, friend.getFriendId());
				pstm.setInt(2, friend.getUserId());
			}
			int result = pstm.executeUpdate();
			if (result != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public List<FriendInfo> findAllFriendListLike(Page<FriendInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<FriendInfo> list = new ArrayList<FriendInfo>();
		try {
			StringBuffer sql = new StringBuffer(
					"select * from (select t2.user_id, t2.user_name, t2.user_nickname, t2.user_uri, t2.USER_CREATEDATE, t2.USER_FORUM_INTEGRAL, t2.USER_EXAM_INTEGRAL, rownum rn from t_friend t1, t_user t2 where t1.friend_id = t2.user_id and t1.user_id = ? and t1.status = 1");
			if (page.getEntity().getUserName() != null && !"".equals(page.getEntity().getUserName())) {
				sql.append(" and t2.user_name like '%").append(page.getEntity().getUserName()).append("%'");
			}
			if (page.getEntity().getUserNickName() != null && !"".equals(page.getEntity().getUserNickName())) {
				sql.append(" and t2.user_nickname like '%").append(page.getEntity().getUserNickName()).append("%'");
			}
			sql.append(" ) where rn between ? and ?");
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setInt(1, page.getEntity().getUserId());
			pstm.setInt(2, page.getStartIndex());
			pstm.setInt(3, page.getEndIndex());
			rs = pstm.executeQuery();
			while (rs.next()) {
				FriendInfo friendInfo = new FriendInfo();
				friendInfo.setFriendId(rs.getInt("user_id"));
				friendInfo.setUserName(rs.getString("user_name"));
				friendInfo.setUserNickName(rs.getString("user_nickname"));
				friendInfo.setUserUri(rs.getString("user_uri"));
				friendInfo.setUserCreatDate(rs.getDate("USER_CREATEDATE"));
				friendInfo.setUserForumIntegral(rs.getInt("USER_FORUM_INTEGRAL"));
				friendInfo.setUserExamIntegral(rs.getInt("USER_EXAM_INTEGRAL"));
				list.add(friendInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findAllFriendListTotalCountLike(Page<FriendInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			StringBuffer sql = new StringBuffer(
					"select count(1) from t_friend t1, t_user t2 where t1.friend_id = t2.user_id and t1.user_id = ? and t1.status = 1");
			if (page.getEntity().getUserName() != null && !"".equals(page.getEntity().getUserName())) {
				sql.append(" and t2.user_name like '%").append(page.getEntity().getUserName()).append("%'");
			}
			if (page.getEntity().getUserNickName() != null && !"".equals(page.getEntity().getUserNickName())) {
				sql.append(" and t2.user_nickname like '%").append(page.getEntity().getUserNickName()).append("%'");
			}

			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql.toString());
			pstm.setInt(1, page.getEntity().getUserId());
			rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public boolean friendBlackList(Friend friend, String type) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "";
			if ("add".equals(type)) {
				sql = "update t_friend set status = 0 where user_id = ? and friend_id = ?";
			} else if ("remove".equals(type)) {
				sql = "update t_friend set status = 1 where user_id = ? and friend_id = ?";
			}
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, friend.getUserId());
			pstm.setInt(2, friend.getFriendId());
			int result = pstm.executeUpdate();
			if (result != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public List<FriendInfo> findAllFriendBlackList(Page<FriendInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<FriendInfo> list = new ArrayList<FriendInfo>();
		try {
			String sql = "select * from (select t2.user_id, t2.user_name, t2.user_nickname, t2.user_uri, rownum rn from t_friend t1, t_user t2 where t1.friend_id = t2.user_id and t1.user_id = ? and t1.status = 0) where rn between ? and ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			pstm.setInt(2, page.getStartIndex());
			pstm.setInt(3, page.getEndIndex());
			rs = pstm.executeQuery();
			while (rs.next()) {
				FriendInfo friendInfo = new FriendInfo();
				friendInfo.setFriendId(rs.getInt("user_id"));
				friendInfo.setUserName(rs.getString("user_name"));
				friendInfo.setUserNickName(rs.getString("user_nickname"));
				friendInfo.setUserUri(rs.getString("user_uri"));
				list.add(friendInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findAllFriendBlackListTotalCount(Page<FriendInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(1) from t_friend t1, t_user t2 where t1.friend_id = t2.user_id and t1.user_id = ? and t1.status = 0";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public boolean deleteFriend(Friend friend, String type) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "";
			if ("fromFriend".equals(type)) {
				sql = "delete from t_friend where user_id = ? and friend_id = ?";
			} else if ("fromChat".equals(type)) {
				sql = "delete from t_chat where user_id = ? and friend_id = ?";
			} else if ("fromApply".equals(type)) {
				sql = "delete from t_apply where user_id = ? and friend_id = ?";
			}
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			if ("fromChat".equals(type)) {
				pstm.setInt(1, friend.getFriendId());
				pstm.setInt(2, friend.getUserId());
			} else {
				pstm.setInt(1, friend.getUserId());
				pstm.setInt(2, friend.getFriendId());
			}
			int result = pstm.executeUpdate();
			if (result != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean ifFriend(int userId, int friendId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			String sql = "select * from t_friend where user_id = ? and friend_id = ? and status = 1";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, friendId);
			pstm.setInt(2, userId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean addChatMsg(Chat chat) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_chat values (seq_t_chat.nextval,?,?,?,?,0)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, chat.getUserId());
			pstm.setInt(2, chat.getFriendId());
			pstm.setTimestamp(3, new java.sql.Timestamp(chat.getWhen().getTime()));
			pstm.setString(4, chat.getContent());
			int result = pstm.executeUpdate();
			if (result != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public List<Chat> findChatMsg(int userId, int friendId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Chat> list = new ArrayList<Chat>();
		try {
			String sql = "SELECT * FROM T_CHAT WHERE USER_ID = ? AND FRIEND_ID = ? or USER_ID = ? AND FRIEND_ID = ? ORDER BY WHEN";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			pstm.setInt(2, friendId);
			pstm.setInt(3, friendId);
			pstm.setInt(4, userId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setUserId(rs.getInt("user_id"));
				chat.setFriendId(rs.getInt("friend_id"));
				chat.setWhen(rs.getTimestamp("when"));
				chat.setContent(rs.getString("content"));
				chat.setStatus(rs.getInt("status"));
				list.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<MsgInfo> findChatNoReadMsgUserList(Page<MsgInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MsgInfo> list = new ArrayList<MsgInfo>();
		try {
			String sql = "select * from (select user_id, user_name, user_nickname, user_uri, rownum rn from t_user where user_id in (select distinct user_id from t_chat where friend_id = ? and status = 0)) t1, (select count(1) count,user_id from t_chat where friend_id = ? and status = 0 group by user_id) t2 where t1.user_id = t2.user_id and rn between ? and ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			pstm.setInt(2, page.getEntity().getUserId());
			pstm.setInt(3, page.getStartIndex());
			pstm.setInt(4, page.getEndIndex());
			rs = pstm.executeQuery();
			while (rs.next()) {
				MsgInfo msgInfo = new MsgInfo();
				msgInfo.setUserId(rs.getInt("user_id"));
				msgInfo.setUserName(rs.getString("user_name"));
				msgInfo.setUserNickName(rs.getString("user_nickname"));
				msgInfo.setUserUri(rs.getString("user_uri"));
				msgInfo.setCount(rs.getInt("count"));
				list.add(msgInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Chat> findChatNoReadMsgByUser(int userId, int friendId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Chat> list = new ArrayList<Chat>();
		try {
			String sql = "select * from t_chat where status = 0 and friend_id = ? and user_id = ? order by when";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			pstm.setInt(2, friendId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setUserId(rs.getInt("user_id"));
				chat.setFriendId(rs.getInt("friend_id"));
				chat.setWhen(rs.getTimestamp("when"));
				chat.setContent(rs.getString("content"));
				list.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findChatNoReadMsgUserListTotalCount(Page<MsgInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(1) from (select count(1) from t_chat where friend_id = ? and status = 0 group by user_id)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public List<MsgInfo> findAllChatRecordListLike(Page<MsgInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MsgInfo> list = new ArrayList<MsgInfo>();
		try {
			String sql = "SELECT * FROM (SELECT t1.*,rownum rn  FROM T_CHAT t1 WHERE USER_ID = ? AND FRIEND_ID = ? or USER_ID = ? AND FRIEND_ID = ? ORDER BY WHEN) WHERE RN BETWEEN ? AND ?";
			/*
			 * if (page.getStartTime() != null && page.getEndTime() != null) {
			 * sql.append(" and when between ").append(page.getStartTime()).append(" and ").
			 * append(page.getEndTime()); }
			 */
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			pstm.setInt(2, page.getEntity().getFriendId());
			pstm.setInt(3, page.getEntity().getFriendId());
			pstm.setInt(4, page.getEntity().getUserId());
			pstm.setInt(5, page.getStartIndex());
			pstm.setInt(6, page.getEndIndex());
			rs = pstm.executeQuery();
			while (rs.next()) {
				MsgInfo msgInfo = new MsgInfo();
				msgInfo.setUserId(rs.getInt("user_id"));
				msgInfo.setFriendId(rs.getInt("friend_id"));
				msgInfo.setWhen(rs.getTimestamp("when"));
				msgInfo.setContent(rs.getString("content"));
				msgInfo.setStatus(rs.getInt("status"));
				list.add(msgInfo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findAllChatRecordListTotalCount(Page<MsgInfo> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "SELECT count(1) FROM T_CHAT t1 WHERE USER_ID = ? AND FRIEND_ID = ? or USER_ID = ? AND FRIEND_ID = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, page.getEntity().getUserId());
			pstm.setInt(2, page.getEntity().getFriendId());
			pstm.setInt(3, page.getEntity().getFriendId());
			pstm.setInt(4, page.getEntity().getUserId());
			rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public boolean ignoreMsg(Chat chat) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_chat set status = 1 where user_id = ? and friend_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, chat.getFriendId());
			pstm.setInt(2, chat.getUserId());
			int result = pstm.executeUpdate();
			if (result != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}
}
