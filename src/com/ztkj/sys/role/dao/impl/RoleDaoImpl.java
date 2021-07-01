package com.ztkj.sys.role.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ztkj.entity.User;
import com.ztkj.sys.entity.Role;
import com.ztkj.sys.role.dao.IRoleDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class RoleDaoImpl implements IRoleDao {

	@Override
	public List<Role> findAllRole() {		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Role> list = new ArrayList<Role>();
		try {
			String sql = "select * from t_role";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Role role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				role.setRoleState(rs.getInt("role_state"));
				role.setRoleCreatorId(rs.getInt("role_creatorId"));
				role.setRoleCreateDate(rs.getDate("role_createDate"));
				list.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Role> findRoleByPageLike(Page<Role> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Role> list = new ArrayList<Role>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_role t where 1 = 1 ");
			if(page.getEntity().getRoleName() != null && !"".equals(page.getEntity().getRoleName())){
				sb.append(" and role_name like '%"+page.getEntity().getRoleName()+"%'");
			}
			if(page.getEntity().getRoleState() != -1){
				sb.append(" and role_state = '"+page.getEntity().getRoleState()+"'");
			}
			sb.append(" order by role_id desc) where rn between ? and ? ");
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();			
			while(rs.next()){
				Role role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				role.setRoleState(rs.getInt("role_state"));
				role.setRoleCreatorId(rs.getInt("role_creatorId"));
				role.setRoleCreateDate(rs.getDate("role_createDate"));
				list.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findTotalCountByLike(Page<Role> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_role where 1=1 ");
			if(page.getEntity().getRoleName() != null && !"".equals(page.getEntity().getRoleName())){
				sb.append(" and role_name like '%"+page.getEntity().getRoleName()+"%'");
			}
			if(page.getEntity().getRoleState() != -1){
				sb.append(" and role_state = '"+page.getEntity().getRoleState()+"'");
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
	public boolean addRole(Role role) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_role values(seq_t_role.nextval,?,1,?,?)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, role.getRoleName());
			pstm.setInt(2, role.getRoleCreatorId());
			pstm.setDate(3, new java.sql.Date(new Date().getTime()));
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
	public Role findRoleById(int roleId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Role role = new Role();
		try {
			String sql = "select * from t_role where role_id=?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);	
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			if(rs.next()){					
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				role.setRoleState(rs.getInt("role_state"));
				role.setRoleCreatorId(rs.getInt("role_creatorId"));
				role.setRoleCreateDate(rs.getDate("role_createDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return role;
	}

	@Override
	public boolean updateRoleInfo(Role role) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_role set role_name = ? where role_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, role.getRoleName());
			pstm.setInt(2, role.getRoleId());
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
	public boolean deleteRoleById(Role role) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_role set role_state = ? where role_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			if(role.getRoleState()==0){
				pstm.setInt(1, 1);
			}else{
				pstm.setInt(1, 0);
			}
			pstm.setInt(2, role.getRoleId());
			int result = pstm.executeUpdate();
			if(result!=0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}	

}
