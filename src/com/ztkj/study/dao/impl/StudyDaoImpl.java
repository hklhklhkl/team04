package com.ztkj.study.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import com.ztkj.entity.Study;
import com.ztkj.study.dao.IStudyDao;

import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class StudyDaoImpl implements IStudyDao {

	@Override
	public boolean addStudy(Study study) {
		Connection conn = null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "insert  into t_note values(seq_t_note.nextval,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, study.getNoteTitle());
			pstm.setString(2, study.getNoteContent());
			pstm.setDate(3, new Date(study.getNoteDate().getTime()));
			pstm.setInt(4, study.getUserId());
			pstm.setString(5, study.getNoteType());
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
	public List<Study> findAllStudy(){
		Connection conn =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Study> list=new ArrayList<Study>();
		try {
			conn = DBUtils.getConn();
			String sql = "select * from t_note ";
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()){
				Study study = new Study();
				 study.setNoteId(rs.getInt("note_id"));
				  study.setNoteTitle(rs.getString("note_title"));
				  study.setNoteType(rs.getString("note_type"));
				  study.setNoteContent(rs.getString("note_Content"));
				  study.setNoteDate(rs.getDate("note_date"));
				  study.setUserId(rs.getInt("user_id"));
				  list.add(study);
				  	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
	}
		return list;
}

	

	@Override
	public boolean deleteStudyById(int noteId) {
		Connection conn =null;
		PreparedStatement pstm = null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "delete from t_note where note_id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noteId);
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
	public Study findStudyById(int noteId) {
		Connection conn =null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Study study = null;
		try {
			conn = DBUtils.getConn();
			String sql = "select * from t_note where note_id= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noteId);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				  study = new Study();
				 study.setNoteId(rs.getInt("note_id"));
				 study.setNoteTitle(rs.getString("note_title"));
				 study.setNoteType(rs.getString("note_type"));
				 study.setNoteContent(rs.getString("note_Content"));
				 study.setUserId(rs.getInt("user_id"));
				 study.setNoteDate(rs.getDate("note_Date"));
				  
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			DBUtils.closeAll(rs, pstm, conn);
	}
		return study;
	}


	@Override
	public boolean updateStudy(Study study) {
		Connection conn=null;
		PreparedStatement pstm =null;
		boolean flag =false;
		try {
			conn = DBUtils.getConn();
			String sql = "update t_note set note_title=?,note_type=?,note_Content=?,note_Date=? where note_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, study.getNoteTitle());
			pstm.setString(2, study.getNoteType());
			pstm.setString(3, study.getNoteContent());
			pstm.setDate(4, new Date(study.getNoteDate().getTime()));
			pstm.setInt(5, study.getNoteId());
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
	public List<Study> findEmpByPageLike(Page<Study> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Study> list = new ArrayList<Study>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_note t where 1=1");
			conn = DBUtils.getConn();
			/*String sql ="select * from (select t.*,rownum rn from emp t where ename like '%%') where rn between ? and ?";*/
			if(page.getEntity().getNoteTitle() != null && !"".equals(page.getEntity().getNoteTitle())){
				sb.append(" and  note_title like '%"+page.getEntity().getNoteTitle() +"%'");
			}
			if(page.getEntity().getStartTime()!= null && !"".equals(page.getEntity().getStartTime())){
				sb.append("and  note_Date >= to_date('"+page.getEntity().getStartTime()+"','yyyy-mm-dd')");
			}
			if(page.getEntity().getEndTime()!= null && !"".equals(page.getEntity().getEndTime())){
				sb.append("and  note_Date <= to_date('"+page.getEntity().getEndTime()+"','yyyy-mm-dd')");
			}
			sb.append(") where rn between ? and ?");
			System.out.println(sb.toString());
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();
			while(rs.next()){
				Study study = new Study();
				study.setNoteId(rs.getInt("note_id"));
				  study.setNoteTitle(rs.getString("note_title"));
				  study.setNoteType(rs.getString("note_type"));
				  study.setNoteContent(rs.getString("note_Content"));
				  study.setNoteDate(rs.getDate("note_date"));
				  study.setUserId(rs.getInt("user_id"));
				  list.add(study);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findTotalCountByLike(Page<Study> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs=null;
		List<Study> list= new ArrayList<Study>();
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_note where 1=1");
			/*String sql ="select count(1) from emp";*/
			if(page.getEntity().getNoteTitle() != null && !"".equals(page.getEntity().getNoteTitle())){
				sb.append(" and  note_title like '%"+page.getEntity().getNoteTitle()+"%'");
			}
			if(page.getEntity().getNoteDate() != null && !"".equals(page.getEntity().getNoteDate())){
				sb.append("and  note_Date like '%"+page.getEntity().getNoteDate()+"%'");
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


