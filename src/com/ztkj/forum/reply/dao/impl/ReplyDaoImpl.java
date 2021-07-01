package com.ztkj.forum.reply.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.forum.entity.Reply;
import com.ztkj.forum.reply.dao.IReplyDao;
import com.ztkj.utils.DBUtils;

public class ReplyDaoImpl implements IReplyDao {

	@Override
	public boolean addReply(Reply reply) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_reply values(seq_t_post.nextval,?,?,?,?)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, reply.getReplyUserId());
			pstm.setInt(2, reply.getRepliedUserId());
			pstm.setString(3, reply.getReplyContent());
			pstm.setInt(4, reply.getCommentId());
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public List<Reply> findAllReply() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Reply> list = new ArrayList<Reply>();
		try {
			String sql = "select * from t_reply";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Reply reply = new Reply();
				reply.setCommentId(rs.getInt("comment_id"));
				reply.setRepliedUserId(rs.getInt("replied_user_id"));
				reply.setReplyContent(rs.getString("reply_content"));
				reply.setReplyId(rs.getInt("reply_id"));
				reply.setReplyUserId(rs.getInt("reply_user_id"));
				list.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

}
