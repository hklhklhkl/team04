package com.ztkj.resourcetype.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.entity.Study;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.resourcetype.dao.IResourceTypeDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class ResourceTypeDaoImpl implements IResourceTypeDao{

	@Override
	public boolean addResourceType(ResourceType resourceType) {
		Connection conn = null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "insert  into t_explore_type values(seq_t_explore_type.nextval,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,resourceType.getExploreTypeName());
			pstm.setString(2,resourceType.getExploreTypeMan());
			pstm.setDate(3, new Date(resourceType.getExploreTypeDate().getTime()));
			pstm.setInt(4,resourceType.getExploreTypeState());
			int result = pstm.executeUpdate();
			if(result !=0){
				flag = true;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public List<ResourceType> findResourceTypeByPageLike(Page<ResourceType> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ResourceType> list = new ArrayList<ResourceType>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_explore_type t where 1=1 ");
			conn = DBUtils.getConn();
			/*String sql ="select * from (select t.*,rownum rn from emp t where ename like '%%') where rn between ? and ?";*/
			if(page.getEntity().getExploreTypeName() != null && !"".equals(page.getEntity().getExploreTypeName())){
				sb.append(" and  explore_type_name like '%"+page.getEntity().getExploreTypeName() +"%'");
			}
			if( page.getEntity().getExploreTypeState() == 4){
				sb.append(" and 1 = 1 ");
			}else{
				
				if(!"".equals(page.getEntity().getExploreTypeState())){
					sb.append(" and  explore_type_State like '%"+page.getEntity().getExploreTypeState()+"%'");
					}
			}
			sb.append(") where rn between ? and ?");
			System.out.println(sb.toString());
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();
			while(rs.next()){
				ResourceType resourceType = new ResourceType();
				resourceType.setExploreTypeId(rs.getInt("explore_type_id"));
				resourceType.setExploreTypeName(rs.getString("explore_type_name"));
				resourceType.setExploreTypeMan(rs.getString("explore_type_man"));
				resourceType.setExploreTypeDate(rs.getDate("explore_type_Date"));
				resourceType.setExploreTypeState(rs.getInt("explore_type_State"));
				  list.add(resourceType);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findTotalCountByLike(Page<ResourceType> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<Study> list= new ArrayList<Study>();
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_explore_type where 1=1 ");
			/*String sql ="select count(1) from emp";*/
			if(page.getEntity().getExploreTypeName() != null && !"".equals(page.getEntity().getExploreTypeName())){
				sb.append(" and  explore_type_name like '%"+page.getEntity().getExploreTypeName()+"%'");
			}
			if( page.getEntity().getExploreTypeState() == 4){
				sb.append(" and 1 = 1 ");
			}else{
				
				if(!"".equals(page.getEntity().getExploreTypeState())){
					sb.append(" and  explore_type_State like '%"+page.getEntity().getExploreTypeState()+"%'");
					}
			}
			System.out.println(sb.toString());
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			rs =pstm.executeQuery();
			while(rs.next()){
				//getInt方法为重载方法，可以根据集中的字段名来获取值，也可以根据字段名为第几列来获取该字段的值
				count =rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public ResourceType findResourceTypeById(int exploreTypeId) {
		Connection conn =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResourceType resourceType = null;
		try {
			conn = DBUtils.getConn();
			String sql = "select * from t_explore_type where explore_type_id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, exploreTypeId);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				resourceType = new ResourceType();
				resourceType.setExploreTypeId(rs.getInt("explore_type_id"));
				resourceType.setExploreTypeName(rs.getString("explore_type_name"));
				resourceType.setExploreTypeMan(rs.getString("explore_type_man"));
				resourceType.setExploreTypeDate(rs.getDate("explore_type_Date"));
				resourceType.setExploreTypeState(rs.getInt("explore_type_state"));
				  
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			DBUtils.closeAll(rs, pstm, conn);
	}
		return resourceType;
	}

	@Override
	public boolean updateResourceType(ResourceType resourceType) {
		Connection conn=null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "update t_explore_type set explore_type_name=?,explore_type_man=?,explore_type_Date=?,explore_type_state=? where explore_type_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,resourceType.getExploreTypeName() );
			pstm.setString(2,resourceType.getExploreTypeMan());
			pstm.setDate(3, new Date(resourceType.getExploreTypeDate().getTime()));
			pstm.setInt(4, resourceType.getExploreTypeState());
			pstm.setInt(5, resourceType.getExploreTypeId());
			int result = pstm.executeUpdate();
			if(result !=0)
				flag = true;
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				//关闭数据库连接
				DBUtils.closeAll(null, pstm, conn);
		}
			return flag;
	}

	@Override
	public boolean deleteResourceTypeById(int exploreTypeId) {
		Connection conn =null;
		PreparedStatement pstm = null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "delete from t_explore_type where explore_type_id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,exploreTypeId);
			int result =pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			DBUtils.closeAll(null, pstm,conn);
		}
		
		return flag;
	}

	@Override
	public boolean updateResourceState(ResourceType resourceType) {
		Connection conn=null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "update t_explore_type set explore_type_state=2 where explore_type_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,resourceType.getExploreTypeId());
			int result = pstm.executeUpdate();
			if(result !=0)
				flag = true;
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				//关闭数据库连接
				DBUtils.closeAll(null, pstm, conn);
		}
		
		return flag;
	}

	@Override
	public boolean updateResourceStateTwo(ResourceType resourceType) {
		Connection conn=null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "update t_explore_type set explore_type_state=1 where explore_type_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,resourceType.getExploreTypeId());
			int result = pstm.executeUpdate();
			if(result !=0)
				flag = true;
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				//关闭数据库连接
				DBUtils.closeAll(null, pstm, conn);
		}
		
		return flag;
	}

}
