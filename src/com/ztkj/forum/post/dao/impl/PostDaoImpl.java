package com.ztkj.forum.post.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.forum.entity.Block;
import com.ztkj.forum.entity.Post;
import com.ztkj.forum.post.dao.IPostDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class PostDaoImpl implements IPostDao {

	@Override
	public List<Post> findAllPost(Page<Post> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_post t where 1 = 1");
			/*String sql = "select * from (select t.*,rownum rn from emp t ) where rn between ? and ?";*/
			if(page.getEntity().getPostContent() != null && !"".equals(page.getEntity().getPostContent())){
				sb.append(" and post_content like'%" + page.getEntity().getPostContent()+"%'");
			}
			if(page.getEntity().getPostState() > 0  && !"".equals(page.getEntity().getPostState())){
				sb.append(" and post_state =" + page.getEntity().getPostState());
			}
			if(page.getEntity().getBlockId() > 0  && !"".equals(page.getEntity().getBlockId())){
				sb.append(" and block_id =" + page.getEntity().getBlockId());
			}
			if(page.getEntity().getUserId() > 0  && !"".equals(page.getEntity().getUserId())){
				sb.append(" and user_id =" + page.getEntity().getUserId());
			}
			sb.append(") where rn between ? and ?");
			
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setBlockId(rs.getInt("block_id"));
				post.setPostComment(rs.getString("post_comment"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostDate(rs.getDate("post_date"));
				post.setPostGood(rs.getInt("post_good"));
				post.setPostId(rs.getInt("post_id"));
				post.setPostNow(rs.getDate("post_now"));
				post.setPostNowId(rs.getInt("post_nowid"));
				post.setPostState(rs.getInt("post_state"));
				post.setPostUp(rs.getInt("post_up"));
				post.setUserId(rs.getInt("user_id"));
				post.setPostSaw(rs.getString("post_saw"));
				list.add(post); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public Post findPostById(int postId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Post post = null;
		try {
			String sql = "select t1.*,t2.block_name,t3.user_name from t_post t1,t_block t2,t_user t3 where t1.block_id = t2.block_id and t1.user_id = t3.user_id and post_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, postId);
			rs = pstm.executeQuery();
			if(rs.next()){
				post = new Post();
				post.setBlockId(rs.getInt("block_id"));
				post.setPostComment(rs.getString("post_comment"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostDate(rs.getDate("post_date"));
				post.setPostGood(rs.getInt("post_good"));
				post.setPostId(rs.getInt("post_id"));
				post.setPostNow(rs.getDate("post_now"));
				post.setPostNowId(rs.getInt("post_nowid"));
				post.setPostState(rs.getInt("post_state"));
				post.setPostUp(rs.getInt("post_up"));
				post.setUserId(rs.getInt("user_id"));
				post.setPostSaw(rs.getString("post_saw"));
				post.setBlockName(rs.getString("block_name"));
				post.setUserName(rs.getString("user_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return post;
	}

	@Override
	public boolean deletePost(int postId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "delete from t_post where post_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, postId);
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
	public boolean addPost(Post post) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_post values(seq_t_post.nextval,?,?,?,sysdate,0,0,?,sysdate,0,0,null)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, post.getPostContent());
			pstm.setInt(2, post.getBlockId());
			pstm.setInt(3, post.getUserId());
			pstm.setString(4, post.getPostComment());
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag ;
	}

	@Override
	public boolean updatePost(Post post) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_post set post_date = sysdate,post_state = ?,post_now = sysdate,post_saw=? where post_id = ?";
			
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, post.getPostState());
			pstm.setString(2, post.getPostSaw());
			pstm.setInt(3, post.getPostId());
			int result = pstm.executeUpdate();
			System.out.println(post.getPostId());
			System.out.println(sql);
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag ;
	}

	@Override
	public int findTotalCountByLike(Page<Post> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_post where 1 = 1");
			/*String sql = "select count(1) from emp where 1 = 1";*/
			if(page.getEntity().getPostContent() != null && !"".equals(page.getEntity().getPostContent())){
				sb.append(" and post_content like'%" + page.getEntity().getPostContent()+"%'");
			}
			if(page.getEntity().getPostState()  > 0  && !"".equals(page.getEntity().getPostState())){
				sb.append(" and post_state =" + page.getEntity().getPostState());
			}
			if(page.getEntity().getBlockId() > 0  && !"".equals(page.getEntity().getBlockId())){
				sb.append(" and block_id =" + page.getEntity().getBlockId());
			}
			if(page.getEntity().getUserId() > 0 && !"".equals(page.getEntity().getUserId())){
				sb.append(" and user_id =" + page.getEntity().getUserId());
			}
			
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			rs = pstm.executeQuery();
			if(rs.next()){
				
				//getint方法为重载方法，可以根据结果集中的字段名字来获取值，也可以根据字段名为第几列来获取该字段的值
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public List<Post> findNameByBlockId() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		try {
			String sql = "select distinct t2.block_name,t1.block_id from t_post t1,t_block t2 where t1.block_id = t2.block_id";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setBlockId(rs.getInt("block_id"));
				post.setBlockName(rs.getString("block_name"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Post> findNameByUserId() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		try {
			String sql = "select distinct t2.user_name,t1.user_id from t_post t1,t_user t2 where t1.user_id = t2.user_id";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setUserId(rs.getInt("user_id"));
				post.setUserName(rs.getString("user_name"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Post> findNameByState() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		try {
			String sql = "select distinct post_state from t_post";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setPostState(rs.getInt("post_state"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public boolean updateGood(int postId,int postGood) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_post set post_good = ? where post_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, postGood);
			pstm.setInt(2, postId);
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
	public boolean updateUp(int postId,int postUp) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_post set post_up = ? where post_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, postUp);
			pstm.setInt(2, postId);
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
	public boolean updateState(int postState, int postId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_post set post_state = ? where post_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, postState);
			pstm.setInt(2, postId);
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
	public List<Post> findPostList(int blockId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		try {
			String sql = "select t3.user_name,t2.* from t_block t1,t_post t2,t_user t3 where t1.block_id = t2.block_id and t3.user_id = t2.user_id and t1.block_id=?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, blockId);
			rs = pstm.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setBlockId(rs.getInt("block_id"));
				post.setPostComment(rs.getString("post_comment"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostDate(rs.getDate("post_date"));
				post.setPostGood(rs.getInt("post_good"));
				post.setPostId(rs.getInt("post_id"));
				post.setPostNow(rs.getDate("post_now"));
				post.setPostNowId(rs.getInt("post_nowid"));
				post.setPostState(rs.getInt("post_state"));
				post.setPostUp(rs.getInt("post_up"));
				post.setUserId(rs.getInt("user_id"));
				post.setPostSaw(rs.getString("post_saw"));
				post.setUserName(rs.getString("user_name"));
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}
}
