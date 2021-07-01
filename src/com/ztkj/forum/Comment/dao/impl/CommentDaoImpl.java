package com.ztkj.forum.Comment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.forum.Comment.dao.ICommentDao;
import com.ztkj.forum.entity.Comment;
import com.ztkj.utils.DBUtils;

public class CommentDaoImpl implements ICommentDao {

	@Override
	public boolean addComment(Comment reply) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_comment values(seq_t_comment.nextval,?,?,?,sysdate)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, reply.getCommentContent());
			pstm.setInt(2, reply.getPostId());
			pstm.setInt(3, reply.getUserId());
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
	public List<Comment> findAllComment() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<Comment>();
		try {
			String sql = "select * from t_comment";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Comment comment = new Comment();
				comment.setCommentContent(rs.getString("comment_content"));
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setPostId(rs.getInt("post_id"));
				comment.setUserId(rs.getInt("user_id"));
				comment.setCommentDate(rs.getDate("comment_date"));
				list.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

}
