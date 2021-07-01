package com.ztkj.resource.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.entity.Study;
import com.ztkj.resource.dao.IResourseDao;
import com.ztkj.resource.entity.Resource;
import com.ztkj.resource.entity.ResourceType;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class ResourceDaoImpl implements IResourseDao {

	@Override
	public boolean addResource(Resource resource) {
		Connection conn = null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "insert  into t_explore values(seq_t_explore.nextval,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,resource.getExploreName());
			pstm.setString(2,resource.getExploreDes());
			pstm.setDate(3, new Date(resource.getExploreDate().getTime()));
			pstm.setString(4,resource.getExploreUri());
			pstm.setInt(5, resource.getExploreTypeId());
			pstm.setInt(6, resource.getExploreState());
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
	public boolean deleteResourceById(int exploreId) {
		Connection conn =null;
		PreparedStatement pstm = null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "delete from t_explore where explore_id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, exploreId);
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
	public List<Resource> findResourceByPageLike(Page<Resource> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Resource> list = new ArrayList<Resource>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_explore t where 1=1");
			conn = DBUtils.getConn();
			/*String sql ="select * from (select t.*,rownum rn from emp t where ename like '%%') where rn between ? and ?";*/
			if(page.getEntity().getExploreName() != null && !"".equals(page.getEntity().getExploreName())){
				sb.append(" and  explore_name like '%"+page.getEntity().getExploreName() +"%'");
			}
			if(page.getEntity().getStartTime()!= null && !"".equals(page.getEntity().getStartTime())){
				sb.append("and  explore_Date >= to_date('"+page.getEntity().getStartTime()+"','yyyy-mm-dd')");
			}
			if(page.getEntity().getEndTime()!= null && !"".equals(page.getEntity().getEndTime())){
				sb.append("and  explore_Date <= to_date('"+page.getEntity().getEndTime()+"','yyyy-mm-dd')");
			}
			sb.append(") where rn between ? and ?");
			System.out.println(sb.toString());
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();
			while(rs.next()){
				Resource resource = new Resource();
				resource.setExploreId(rs.getInt("explore_id"));
				resource.setExploreName(rs.getString("explore_name"));
				resource.setExploreDes(rs.getString("explore_desc"));
				resource.setExploreDate(rs.getDate("explore_date"));
				resource.setExploreUri(rs.getString("explore_uri"));
				resource.setExploreTypeId(rs.getInt("explore_type_id"));
				resource.setExploreState(rs.getInt("explore_state"));
				  list.add(resource);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findTotalCountByLike(Page<Resource> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<Resource> list= new ArrayList<Resource>();
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_explore where 1=1");
			/*String sql ="select count(1) from emp";*/
			if(page.getEntity().getExploreName() != null && !"".equals(page.getEntity().getExploreName())){
				sb.append(" and  explore_name like '%"+page.getEntity().getExploreName()+"%'");
			}
			if(page.getEntity().getExploreDate() != null && !"".equals(page.getEntity().getExploreDate())){
				sb.append("and  note_Date like '%"+page.getEntity().getExploreDate()+"%'");
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
	public List<ResourceType> findAllResourceType() {
		Connection conn =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ResourceType> list=new ArrayList<ResourceType>();
		try {
			conn = DBUtils.getConn();
			String sql = "select * from t_explore_type";
			pstm = conn.prepareStatement(sql);
			
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
	public Resource findResourceById(int exploreId) {
		Connection conn =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Resource resource = null;
		try {
			conn = DBUtils.getConn();
			String sql = "select * from t_explore where explore_id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, exploreId);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				 resource = new Resource();
				resource.setExploreId(rs.getInt("explore_id"));
				resource.setExploreName(rs.getString("explore_name"));
				resource.setExploreDes(rs.getString("explore_desc"));
				resource.setExploreDate(rs.getDate("explore_date"));
				resource.setExploreUri(rs.getString("explore_uri"));
				resource.setExploreTypeId(rs.getInt("explore_type_id"));
				resource.setExploreState(rs.getInt("explore_state"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//关闭数据库连接
				DBUtils.closeAll(rs, pstm, conn);
		}
			return resource;
	}

	@Override
	public boolean updateResource(Resource resource) {
		Connection conn=null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "update t_explore set explore_name=?,explore_desc=?,explore_uri=?,explore_Date=? ,explore_type_id=?,explore_state=? where explore_id=?";
					
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,resource.getExploreName());
			pstm.setString(2, resource.getExploreDes());
			pstm.setString(3,resource.getExploreUri());
			pstm.setDate(4, new Date(resource.getExploreDate().getTime()));
			pstm.setInt(5, resource.getExploreTypeId());
			pstm.setInt(6, resource.getExploreState());
			pstm.setInt(7, resource.getExploreId());
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
	public List<Resource> findsourceByPageLike(Page<Resource> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Resource> list = new ArrayList<Resource>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_explore t where 1=1");
			conn = DBUtils.getConn();
			/*String sql ="select * from (select t.*,rownum rn from emp t where ename like '%%') where rn between ? and ?";*/
			if(page.getEntity().getExploreName() != null && !"".equals(page.getEntity().getExploreName())){
				sb.append(" and  explore_name like '%"+page.getEntity().getExploreName() +"%'");
			}
			if( page.getEntity().getExploreState() == 4){
				sb.append(" and 1 = 1 ");
			}else{
				if(!"".equals(page.getEntity().getExploreState())){
				sb.append(" and  explore_state like '%"+page.getEntity().getExploreState() +"%'");
				}
			}
			if( page.getEntity().getExploreTypeId() == 4){
				sb.append(" and 1 = 1 ");
			}else{
				if(!"".equals(page.getEntity().getExploreState())){
				sb.append(" and  explore_state like '%"+page.getEntity().getExploreTypeId() +"%'");
				}
			}
			
			if(page.getEntity().getStartTime()!= null && !"".equals(page.getEntity().getStartTime())){
				sb.append("and  explore_Date >= to_date('"+page.getEntity().getStartTime()+"','yyyy-mm-dd')");
			}
			if(page.getEntity().getEndTime()!= null && !"".equals(page.getEntity().getEndTime())){
				sb.append("and  explore_Date <= to_date('"+page.getEntity().getEndTime()+"','yyyy-mm-dd')");
			}
			sb.append(") where rn between ? and ?");
			System.out.println(sb.toString());
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();
			while(rs.next()){
				Resource resource = new Resource();
				resource.setExploreId(rs.getInt("explore_id"));
				resource.setExploreName(rs.getString("explore_name"));
				resource.setExploreDes(rs.getString("explore_desc"));
				resource.setExploreDate(rs.getDate("explore_date"));
				resource.setExploreUri(rs.getString("explore_uri"));
				resource.setExploreTypeId(rs.getInt("explore_type_id"));
				resource.setExploreState(rs.getInt("explore_state"));
				  list.add(resource);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findTotalCountsByLike(Page<Resource> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<Resource> list= new ArrayList<Resource>();
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_explore where 1=1");
			/*String sql ="select count(1) from emp";*/
			if(page.getEntity().getExploreName() != null && !"".equals(page.getEntity().getExploreName())){
				sb.append(" and  explore_name like '%"+page.getEntity().getExploreName()+"%'");
			}
			if( page.getEntity().getExploreState() == 4){
				sb.append(" and 1 = 1 ");
			}else{
				if(!"".equals(page.getEntity().getExploreState())){
				sb.append(" and  explore_state like '%"+page.getEntity().getExploreState() +"%'");
				}
			}
			if( page.getEntity().getExploreTypeId() == 4){
				sb.append(" and 1 = 1 ");
			}else{
				if(!"".equals(page.getEntity().getExploreState())){
				sb.append(" and  explore_state like '%"+page.getEntity().getExploreTypeId() +"%'");
				}
			}
			
			if(page.getEntity().getStartTime()!= null && !"".equals(page.getEntity().getStartTime())){
				sb.append("and  explore_Date >= to_date('"+page.getEntity().getStartTime()+"','yyyy-mm-dd')");
			}
			if(page.getEntity().getEndTime()!= null && !"".equals(page.getEntity().getEndTime())){
				sb.append("and  explore_Date <= to_date('"+page.getEntity().getEndTime()+"','yyyy-mm-dd')");
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

}
