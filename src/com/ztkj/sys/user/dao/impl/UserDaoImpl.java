package com.ztkj.sys.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.sys.user.dao.IUserDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

	public class UserDaoImpl implements IUserDao {

		@Override
		public User login(String userName, String userPass) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			User user = null;
			try {
				String sql = "select * from t_user where user_name=? and user_pass=? and user_state = 1";
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
		public List<User> findUserByPageLike(Page<User> page) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<User> list = new ArrayList<User>();
			try {
				StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_user t where 1 = 1 ");
				if(page.getEntity().getUserNickname() != null && !"".equals(page.getEntity().getUserNickname())){
					sb.append(" and user_nickname like '%"+page.getEntity().getUserNickname()+"%'");
				}
				if(page.getEntity().getUserName() != null && !"".equals(page.getEntity().getUserName())){
					sb.append(" and user_name like '%"+page.getEntity().getUserName()+"%'");
				}
				if(page.getEntity().getRoleId() != 0){
					sb.append(" and role_id = '"+page.getEntity().getRoleId()+"'");
				}
				if(page.getEntity().getUserState() == 0 ||page.getEntity().getUserState() == 1){
					sb.append(" and user_state = '"+page.getEntity().getUserState()+"'");
				}
				sb.append("order by user_id desc) where rn between ? and ? ");
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sb.toString());
				pstm.setInt(1, page.getStartIndex());
				pstm.setInt(2, page.getEndIndex());
				rs = pstm.executeQuery();
				/*System.out.println(sb.toString());*/
				while(rs.next()){
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					user.setUserNickname(rs.getString("user_nickname"));
					user.setUserName(rs.getString("user_name"));
					user.setUserPass(rs.getString("user_pass"));
					user.setUserSex(rs.getString("user_sex"));
					user.setUserBirth(rs.getDate("user_birth"));
					user.setUserUri(rs.getString("user_uri"));
					user.setRoleId(rs.getInt("role_id"));
					user.setUserForumIntegral(rs.getInt("user_forum_integral"));
					user.setUserExamIntegral(rs.getInt("user_Exam_integral"));
					user.setUserCreateDate(rs.getDate("user_createDate"));
					user.setUserCreatorId(rs.getInt("user_creatorId"));
					user.setUserState(rs.getInt("user_state"));
					list.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(rs, pstm, conn);
			}
			return list;
		}

		@Override
		public int findTotalCountByLike(Page<User> page) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int count = 0;
			try {
				StringBuffer sb = new StringBuffer("select count(1) from t_user where 1=1 ");
				if(page.getEntity().getUserNickname() != null && !"".equals(page.getEntity().getUserNickname())){
					sb.append(" and user_nickname like '%"+page.getEntity().getUserNickname()+"%'");
				}
				if(page.getEntity().getUserName() != null && !"".equals(page.getEntity().getUserName())){
					sb.append(" and user_name like '%"+page.getEntity().getUserName()+"%'");
				}
				if(page.getEntity().getRoleId() != 0){
					sb.append(" and role_id = '"+page.getEntity().getRoleId()+"'");
				}
				if(page.getEntity().getUserState() != -1 ){
					sb.append(" and user_state = '"+page.getEntity().getUserState()+"'");
				}
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sb.toString());
				rs = pstm.executeQuery();
				while(rs.next()){
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(rs, pstm, conn);
			}
			return count;
		}

		@Override
		public boolean addUser(User user) {
			Connection conn = null;
			PreparedStatement pstm = null;
			boolean flag = false;
			try {
				String sql = "insert into t_user values(seq_t_user.nextval,?,?,?,?,?,?,?,0,0,?,?,1)";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, user.getUserNickname());
				pstm.setString(2, user.getUserName());
				pstm.setString(3, user.getUserPass());
				pstm.setString(4, user.getUserSex());
				pstm.setDate(5, new java.sql.Date(user.getUserBirth().getTime()));
				pstm.setString(6, user.getUserUri());
				pstm.setInt(7, user.getRoleId());
				pstm.setDate(8, new java.sql.Date(new Date().getTime()));
				pstm.setInt(9, user.getUserCreatorId());
				System.out.println(sql);
				int result = pstm.executeUpdate();
				if(result != 0){
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(null, pstm, conn);
			}
			return flag;
		}

		@Override
		public List<User> findAllUser() {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<User> list = new ArrayList<User>();
			try {
				String sql = "select * from t_user";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while(rs.next()){
					User user = new User();
					user.setUserId(rs.getInt("user_id"));
					user.setUserNickname(rs.getString("user_nickname"));
					user.setUserName(rs.getString("user_name"));
					user.setUserPass(rs.getString("user_pass"));
					user.setUserSex(rs.getString("user_sex"));
					user.setUserBirth(rs.getDate("user_birth"));
					user.setUserUri(rs.getString("user_uri"));
					user.setRoleId(rs.getInt("role_id"));
					user.setUserForumIntegral(rs.getInt("user_forum_integral"));
					user.setUserExamIntegral(rs.getInt("user_Exam_integral"));
					user.setUserCreateDate(rs.getDate("user_createDate"));
					user.setUserCreatorId(rs.getInt("user_creatorId"));
					user.setUserState(rs.getInt("user_state"));
					list.add(user);	
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(rs, pstm, conn);
			}
			return list;
		}

		@Override
		public boolean updateSelfInfo(User user) {
			Connection conn = null;
			PreparedStatement pstm = null;
			boolean flag = false;
			try {
				String sql = "update t_user set user_nickname = ?£¬user_name = ?,user_sex = ?£¬user_birth=?,user_uri=? where user_id = ?";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, user.getUserNickname());
				pstm.setString(2, user.getUserName());
				pstm.setString(3, user.getUserSex());
				pstm.setDate(4, new java.sql.Date(user.getUserBirth().getTime()));
				pstm.setString(5, user.getUserUri());
				pstm.setInt(6, user.getUserId());
				int result = pstm.executeUpdate();
				System.out.println(sql);
				if(result != 0){
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(null, pstm, conn);
			}
			return flag;
		}

		@Override
		public boolean updateUserInfo(User user) {
			Connection conn = null;
			PreparedStatement pstm = null;
			boolean flag = false;
			try {
				String sql = "update t_user set user_nickname = ?,user_name = ?,user_sex = ?£¬user_birth = ?,user_uri=?,role_id=? where user_id = ?";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, user.getUserNickname());
				pstm.setString(2, user.getUserName());
				pstm.setString(3, user.getUserSex());
				pstm.setDate(4, new java.sql.Date(user.getUserBirth().getTime()));
				pstm.setString(5, user.getUserUri());
				pstm.setInt(6, user.getRoleId());
				pstm.setInt(7, user.getUserId());
				int result = pstm.executeUpdate();
				//System.out.println(sql);
				if(result != 0){
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(null, pstm, conn);
			}
			return flag;
		}

		@Override
		public boolean deleteUserInfoById(User user) {
			Connection conn = null;
			PreparedStatement pstm = null;
			boolean flag = false;
			try {
				String sql = "update t_user set user_state = ? where user_id = ?";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);
				if(user.getUserState()==0){
					pstm.setInt(1, 1);
				}else{
					pstm.setInt(1, 0);
				}
				pstm.setInt(2, user.getUserId());
				int result = pstm.executeUpdate();
				if(result!=0){
					flag = true;
				}
				System.out.println(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(null, pstm, conn);
			}
			return flag;
		}

		@Override
		public User findUserById(int userId) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			User user = new User();
			try {
				String sql = "select * from t_user where user_id=?";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);	
				pstm.setInt(1, userId);
				rs = pstm.executeQuery();
				if(rs.next()){					
					user.setUserId(rs.getInt("user_id"));
					user.setUserNickname(rs.getString("user_nickname"));
					user.setUserName(rs.getString("user_name"));
					user.setUserPass(rs.getString("user_pass"));
					user.setUserSex(rs.getString("user_sex"));
					user.setUserBirth(rs.getDate("user_birth"));
					user.setUserUri(rs.getString("user_uri"));
					user.setRoleId(rs.getInt("role_id"));
					user.setUserForumIntegral(rs.getInt("user_forum_integral"));
					user.setUserExamIntegral(rs.getInt("user_Exam_integral"));
					user.setUserCreateDate(rs.getDate("user_createDate"));
					user.setUserCreatorId(rs.getInt("user_creatorId"));
					user.setUserState(rs.getInt("user_state"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(null, pstm, conn);
			}
			return user;
		}

		@Override
		public boolean updateSelfPass(User user) {
			Connection conn = null;
			PreparedStatement pstm = null;
			boolean flag = false;
			try {
				String sql = "update t_user set user_pass = ? where user_id = ?";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, user.getUserPass());
				pstm.setInt(2, user.getUserId());
				int result = pstm.executeUpdate();
				if(result!=0){
					flag = true;
				}
				System.out.println(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(null, pstm, conn);
			}
			return flag;
		}

		@Override
		public User findNameById(int id) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			User user=null;
			try{
				String sql = " select user_name from t_user where user_id=?";
				conn = DBUtils.getConn();
				pstm = conn.prepareStatement(sql);	
				pstm.setInt(1, id);
				rs = pstm.executeQuery();
				if (rs.next()) {
					user=new User();
					user.setUserName(rs.getString("user_name"));
					}
			}catch (Exception e) {
				e.printStackTrace();
			} finally{
				DBUtils.closeAll(rs, pstm, conn);
			}
			return user;
		}
}
