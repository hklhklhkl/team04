package com.ztkj.exam.subjects.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.subjects.dao.ISubjectDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.exam.utils.Page;


public class SubjectDaoImpl implements ISubjectDao{

	@Override
	public boolean addSubject(Subject subjects) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "insert into t_subject values(seq_t_subject.nextval,?,?,sysdate,'0')";
			ps = conn.prepareStatement(sql);
			ps.setString(1,subjects.getSubjectName());
			ps.setString(2,subjects.getSubjectAuthor());
			int result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}

	@Override
	public boolean updateSubject(Subject subject) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String status = null;
			conn = DBUtils.getConn();
			String sql = "update t_subject set subject_status=? where subject_id = ?";
			ps = conn.prepareStatement(sql);
			status = subject.getSubjectStatus();
			if (status.equals("1")) {
				status="0";
			}else{
				status="1";
			}
			ps.setString(1, status);
			ps.setInt(2, subject.getSubjectId());
			int result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}
	public boolean updateSubjectName(Subject subject) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "update t_subject set subject_name=? where subject_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, subject.getSubjectName());
			ps.setInt(2, subject.getSubjectId());
			int result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}

	@Override
	public boolean deleteSubject(int subjectId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "delete from t_subject where subject_id = ? and subject_status='0'";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, subjectId);
			int result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}

	@Override
	public List<Subject> findSubjectByPage(Page page,String subjectName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Subject subject = null;
		List<Subject> list = new ArrayList<Subject>();
		try{
			conn = DBUtils.getConn();
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_subject t where 1=1 ");
			if(!"".equals(subjectName) && subjectName!=null ){
				sb.append(" and subject_name like '%"+subjectName+"%'");
			}
			sb.append(") where rn between ? and ?");
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getEndIndex());
			rs = ps.executeQuery();
			while(rs.next()){
				subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectAuthor(rs.getString("subject_author"));
				subject.setSubjectDate(rs.getDate("subject_date"));
				subject.setSubjectStatus(rs.getString("subject_status"));
				list.add(subject);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<Subject> findAllSubject(Page page) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Subject> list = new ArrayList<Subject>();
		try{
			conn = DBUtils.getConn();
			String sql = "select * from (select t.*,rownum rn from t_subject t) where rn between ? and ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getEndIndex());
			rs = ps.executeQuery();
			while(rs.next()){
				Subject subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectAuthor(rs.getString("subject_author"));
				subject.setSubjectDate(rs.getDate("subject_date"));
				subject.setSubjectStatus(rs.getString("subject_status"));
				list.add(subject);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public int findTotalCount(String subjectName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try{
			StringBuffer sb = new StringBuffer("select count(1) from t_subject where 1=1");
			if (subjectName!=null&& !"".equals(subjectName)) {
				sb.append(" and subject_name like '%"+subjectName+"%'");
				
			}
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				//getInt为重载的方法可以根据结果集中的字段名获取值也可以根据字段的列数获取值
				count=rs.getInt(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return count;
	}

	@Override
	public int findTotalCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			String sql = "select count(1) from t_subject";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				//getInt为重载的方法可以根据结果集中的字段名获取值也可以根据字段的列数获取值
				count=rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return count;
	}

	@Override
	public Subject findSubjectById(int subjectId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Subject subject = null;
		try{
			String sql = "select * from t_subject where subject_id = ?";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, subjectId);
			rs = ps.executeQuery();
			if(rs.next()){
				subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectAuthor(rs.getString("subject_author"));
				subject.setSubjectDate(rs.getDate("subject_date"));
				subject.setSubjectStatus(rs.getString("subject_status"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return subject;
	}

	@Override
	public List<Subject> findAllSubject() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Subject> list = new ArrayList<Subject>();
		try{
			conn = DBUtils.getConn();
			String sql = "select * from  t_subject ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Subject subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectAuthor(rs.getString("subject_author"));
				subject.setSubjectDate(rs.getDate("subject_date"));
				subject.setSubjectStatus(rs.getString("subject_status"));
				list.add(subject);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}

	

}
