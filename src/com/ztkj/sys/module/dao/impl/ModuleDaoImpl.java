package com.ztkj.sys.module.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ztkj.sys.entity.Auth;
import com.ztkj.sys.entity.Module;
import com.ztkj.sys.module.dao.IModuleDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class ModuleDaoImpl implements IModuleDao {

	@Override
	public List<Module> findModuleByUserId(int userId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Module> list = new ArrayList<Module>();
		try {
			String sql = "select t4.* "
					+ "from t_user t1,t_role t2,t_role_module t3,t_module t4 "
					+ "where t1.role_id = t2.role_id "
					+ "and t2.role_id = t3.role_id "
					+ "and t3.module_id = t4.module_id "
					+ "and t1.user_id = ? "
					+ "and t4.model_state = 1";
					//+ "and t4.model_state = 1";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			rs = pstm.executeQuery();
			while(rs.next()){
				Module module = new Module();
				module.setModuleId(rs.getInt("module_id"));
				module.setModuleName(rs.getString("module_name"));
				module.setModuleParentId(rs.getInt("module_ParentId"));
				module.setModuleUrl(rs.getString("module_url"));
				module.setModuleState(rs.getInt("model_state"));
				module.setModuleCreatorId(rs.getInt("model_creatorId"));
				module.setModuleCreateDate(rs.getDate("model_createDate"));
				list.add(module);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Module> findAllModule() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Module> list = new ArrayList<Module>();
		try {
			String sql = "select * from t_module";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Module module = new Module();
				module.setModuleId(rs.getInt("module_id"));
				module.setModuleName(rs.getString("module_name"));
				module.setModuleParentId(rs.getInt("module_ParentId"));
				module.setModuleUrl(rs.getString("module_url"));
				module.setModuleState(rs.getInt("model_state"));
				module.setModuleCreatorId(rs.getInt("model_creatorId"));
				module.setModuleCreateDate(rs.getDate("model_createDate"));
				list.add(module);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Auth> findAuthByRoleId(int roleId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Auth> list = new ArrayList<Auth>(); 
		try {
			String sql = "select * from t_role_module where role_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			while(rs.next()){
				Auth auth = new Auth();
				auth.setRoleId(rs.getInt("role_id"));
				auth.setModuleId(rs.getInt("module_id"));
				list.add(auth);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public boolean deleteAuthByRoleId(int roleId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "delete from t_role_module where role_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
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
	public boolean addAuth(Auth auth) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_role_module values(?,?)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, auth.getRoleId());
			pstm.setInt(2, auth.getModuleId());
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
	public List<Module> findModuleByPageLike(Page<Module> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Module> list = new ArrayList<Module>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_module t where 1 = 1 ");
			if(page.getEntity().getModuleName() != null && !"".equals(page.getEntity().getModuleName())){
				sb.append(" and module_name like '%"+page.getEntity().getModuleName()+"%'");
			}
			if(page.getEntity().getModuleState() != -1){
				sb.append(" and model_state = '"+page.getEntity().getModuleState()+"'");
			}
			sb.append(" order by module_id desc) where rn between ? and ? ");
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();			
			while(rs.next()){
				Module module = new Module();
				module.setModuleId(rs.getInt("module_id"));
				module.setModuleName(rs.getString("module_name"));
				module.setModuleParentId(rs.getInt("module_parentId"));
				module.setModuleUrl(rs.getString("module_url"));
				module.setModuleState(rs.getInt("model_state"));
				module.setModuleCreatorId(rs.getInt("model_creatorId"));
				module.setModuleCreateDate(rs.getDate("model_createDate"));
				list.add(module);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findTotalCountByLike(Page<Module> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_module where 1=1");
			if(page.getEntity().getModuleName() != null && !"".equals(page.getEntity().getModuleName())){
				sb.append(" and module_name like '%"+page.getEntity().getModuleName()+"%'");
			}
			if(page.getEntity().getModuleState() != -1){
				sb.append(" and model_state = '"+page.getEntity().getModuleState()+"'");
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
	public boolean addModule(Module module) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_module values(seq_t_module.nextval,?,?,?,1,?,?)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, module.getModuleName());
			pstm.setInt(2, module.getModuleParentId());
			pstm.setString(3, module.getModuleUrl());
			pstm.setInt(4, module.getModuleCreatorId());
			pstm.setDate(5, new java.sql.Date(new Date().getTime()));
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
	public boolean updateModule(Module module) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_module set module_name = ?,module_parentId = ?,module_url = ? where module_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, module.getModuleName());
			pstm.setInt(2, module.getModuleParentId());
			pstm.setString(3, module.getModuleUrl());
			pstm.setInt(4, module.getModuleId());
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
	public boolean deleteModule(Module module) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_module set model_state = ? where module_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			if(module.getModuleState()==0){
				pstm.setInt(1, 1);
			}else{
				pstm.setInt(1, 0);
			}
			pstm.setInt(2, module.getModuleId());
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

	@Override
	public Module findModuleById(int moduleId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Module module = new Module();
		try {
			String sql = "select * from t_module where module_id=?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);	
			pstm.setInt(1, moduleId);
			rs = pstm.executeQuery();
			if(rs.next()){					
				module.setModuleId(rs.getInt("module_id"));
				module.setModuleName(rs.getString("module_name"));
				module.setModuleParentId(rs.getInt("module_parentId"));
				module.setModuleUrl(rs.getString("module_url"));
				module.setModuleState(rs.getInt("model_state"));
				module.setModuleCreatorId(rs.getInt("model_creatorId"));
				module.setModuleCreateDate(rs.getDate("model_createDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return module;
	}
}
