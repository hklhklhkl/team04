package com.ztkj.front.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.entity.Pic;
import com.ztkj.entity.User;
import com.ztkj.forum.entity.Post;
import com.ztkj.front.user.dao.IUserDao;
import com.ztkj.front.vo.GoodPost;
import com.ztkj.front.vo.HotPost;
import com.ztkj.front.vo.LunTan;
import com.ztkj.sys.entity.Role;
import com.ztkj.utils.DBUtils;

public class UserDaoImpl implements IUserDao {

	@Override
	public User front_login(String userName, String userPass) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;
		try {
			String sql = "select * from t_user where user_name = ? and user_pass = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, userPass);
			rs = pstm.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserNickname(rs.getString("user_nickname"));
				user.setUserName(rs.getString("user_Name"));
				user.setUserPass(rs.getString("user_pass"));
				user.setUserSex(rs.getString("user_sex"));
				user.setUserBirth(rs.getDate("user_birth"));
				user.setUserUri(rs.getString("user_uri"));
				user.setRoleId(rs.getInt("role_id"));
				user.setUserState(rs.getInt("user_state"));
				user.setUserForumIntegral(rs.getInt("user_forum_integral"));
				user.setUserExamIntegral(rs.getInt("user_exam_integral"));							
				user.setUserCreateDate(rs.getDate("user_createDate"));
				user.setUserCreatorId(rs.getInt("user_creatorId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean add_user(User user) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			String sql = "insert into t_user values(seq_t_user.nextval,?,?,?,?,sysdate,'e169c064-7fef-48b8-be73-4280d05cf565.png',5,0,0,sysdate,1,1)";
			System.out.println(sql);
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserNickname());
			pstm.setString(2, user.getUserName());
			pstm.setString(3, user.getUserPass());
			pstm.setString(4, user.getUserSex());
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean updatePic(User user) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			String sql ="update t_user set user_uri = ? where user_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUserUri());
			pstm.setInt(2, user.getUserId());
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public List<User> findUserName() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			String sql = "select user_name,user_exam_integral from t_user order by user_exam_integral desc";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserName(rs.getString("user_name"));
				user.setUserExamIntegral(rs.getInt("user_exam_integral"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<HotPost> findHotPost() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<HotPost> list  = new ArrayList<HotPost>();
		try {
			String sql = "select * from(select t.*, rownum rn from (select block_id, block_name,post_date,count(1) count from (select t1.block_name,t2.* from t_block t1,t_post t2 where t1.block_id=t2.block_id)group by block_id, block_name,post_date order by count desc )t )where rn between 1 and 6";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs= pstm.executeQuery();
			while(rs.next()){
				HotPost hotpost = new HotPost();
				hotpost.setBlock_id(rs.getInt("block_id"));
				hotpost.setBlock_name(rs.getString("block_name"));
				hotpost.setTime(rs.getDate("post_date"));
				hotpost.setCount(rs.getInt("count"));
				hotpost.setHot(rs.getInt("rn"));
				list.add(hotpost);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public String findRoleName(int userId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String str  = "";
		try {
			String sql = "select role_name from t_role where role_id=(select role_id from t_user where user_id = ?) ";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			rs= pstm.executeQuery();
			if(rs.next()){
				str = rs.getString("role_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return str;
	}

	@Override
	public List<LunTan> findLunTanName() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<LunTan> list  = new ArrayList<LunTan>();
		try {
			String sql = "select tt1.*,tt2.count from (select t2.block_id,t2.block_name,t2.block_photo from t_user t1,t_block t2 where t1.user_id = t2.user_id) tt1,(select t1.block_id,count(1) count from t_block t1,t_post t2 where t1.block_id = t2.block_id group by t1.block_id) tt2 where tt1.block_id = tt2.block_id(+) order by count desc";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				LunTan luntan = new LunTan();
				luntan.setBlockId(rs.getInt("block_id"));
				luntan.setBlockName(rs.getString("block_name"));
				luntan.setCount(rs.getInt("count"));
				list.add(luntan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Pic> findPicture() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Pic> list = new ArrayList<Pic>();
		try {
			String sql = "select * from t_img";
			conn =DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Pic pic  = new Pic();
				pic.setImgId(rs.getInt("img_id"));
				pic.setImgPic(rs.getString("img_pic"));
				pic.setImgPlace(rs.getInt("img_place"));
				list.add(pic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<GoodPost> findGoodPost() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<GoodPost> list = new ArrayList<GoodPost>();
		try {
			String sql = "select * from(select t.*, rownum rn from(select t3.post_content,t2.block_name,t1.user_name,post_date from t_user t1,t_block t2,t_post t3 where t1.user_id=t2.user_id and t2.block_id=t3.block_id and post_good = 1) t) where rn between 1 and 5";
			conn =DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				GoodPost goodPost = new GoodPost();
				goodPost.setUserName(rs.getString("user_name"));
				goodPost.setBlockName(rs.getString("block_name"));
				goodPost.setPostDate(rs.getDate("post_date"));
				goodPost.setPostContent(rs.getString("post_content"));
				goodPost.setRn(rs.getInt("rn"));
				list.add(goodPost);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findPsostTotal(int userId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int str = 0;
		try {
			String sql = "select count(1) from t_post where post_state > 0  and user_id = ? ";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			rs= pstm.executeQuery();
			if(rs.next()){
				str = rs.getInt("count(1)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return str;
	}
	

}
