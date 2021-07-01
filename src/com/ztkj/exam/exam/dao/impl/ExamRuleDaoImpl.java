package com.ztkj.exam.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.exam.dao.IExamRuleDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.exam.utils.Page;

public class ExamRuleDaoImpl implements IExamRuleDao {

	@Override
	public List<ExamRule> findAllExam(Page page, String examName, int subjectId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ExamRule> examList = new ArrayList<ExamRule>();
		try{
			StringBuffer sb = new StringBuffer("select * from"+
					" (select t.*,rownum rn from t_examRule t where 1=1 ");
			if(!"".equals(examName)&& examName!=null) {
				sb.append(" and exam_name like '%"+examName+"%'");
			}if (subjectId!=0) {
				sb.append(" and subject_id ="+subjectId);
			}
			sb.append(") where rn between ? and ?");
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sb.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getEndIndex());
			rs=ps.executeQuery();
			while(rs.next()){
				ExamRule exam = new ExamRule();
				exam.setExamId(rs.getInt("exam_id"));
				exam.setSubjectId(rs.getInt("subject_id"));
				exam.setExamName(rs.getString("exam_name"));
				exam.setStatus(rs.getString("exam_status"));
				exam.setExamScore(rs.getInt("exam_score"));
				exam.setExamAuthor(rs.getString("exam_author"));
				exam.setExamDate(rs.getDate("exam_date"));
				exam.setExamTime(rs.getInt("exam_time"));
				exam.setExamCredit(rs.getInt("exam_credit"));
				examList.add(exam);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return examList;
	}

	@Override
	public int findTotalCount(String examName, int subjectId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			StringBuffer sb = new StringBuffer("select count(1) from t_examRule where 1=1");
			if(!"".equals(examName)&& examName!=null) {
				sb.append(" and exam_name like '%"+examName+"%'");
			}if (subjectId!=0) {
				sb.append(" and subject_id ="+subjectId);
			}
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sb.toString());
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
	public boolean deleteExam(int examId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "delete from t_examRule  where exam_id = ? and exam_status='0'";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, examId);
			int result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}

	@Override
	public boolean updateExamStatus(ExamRule exam) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String status = null;
			conn = DBUtils.getConn();
			String sql = "update t_examRule set exam_status=? where exam_id = ?";
			ps = conn.prepareStatement(sql);
			status = exam.getStatus();
			if (status.equals("1")) {
				status="0";
			}else{
				status="1";
			}
			ps.setString(1, status);
			ps.setInt(2, exam.getExamId());
			int result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}

	@Override
	public ExamRule findExamById(int examId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ExamRule exam = null;
		try{
			String sql = "select * from t_examRule where exam_id=?";
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, examId);
			rs=ps.executeQuery();
			if (rs.next()) {
				exam = new ExamRule();
				exam.setExamId(rs.getInt("exam_id"));
				exam.setSubjectId(rs.getInt("subject_id"));
				exam.setExamName(rs.getString("exam_name"));
				exam.setStatus(rs.getString("exam_status"));
				exam.setExamScore(rs.getInt("exam_score"));
				exam.setExamAuthor(rs.getString("exam_author"));
				exam.setExamDate(rs.getDate("exam_date"));
				exam.setExamTime(rs.getInt("exam_time"));
				exam.setExamCredit(rs.getInt("exam_credit"));
			}		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return exam;
	}

	@Override
	public boolean addExam(ExamRule exam) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String sql = "insert into t_examRule values(seq_t_examRule.nextval,?,?,'0',?,?,sysdate,?,?)";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, exam.getSubjectId());
			ps.setString(2, exam.getExamName());
			ps.setInt(3, exam.getExamScore());
			ps.setString(4, exam.getExamAuthor());
			ps.setInt(5, exam.getExamTime());
			ps.setInt(6, exam.getExamCredit());
			int result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}

	@Override
	public int findSeq() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id=0;
		try{
			String sql = "select exam_id from t_examRule where exam_date = (select max(exam_date) from t_examRule)";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				//getInt为重载的方法可以根据结果集中的字段名获取值也可以根据字段的列数获取值
				id=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return id;
	}

	@Override
	public boolean updateExam(ExamRule exam) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		int result = 0;
		try{
			conn = DBUtils.getConn();
			String sql = "update t_examRule set exam_name=?,exam_score=?,exam_time=?,exam_credit=? where exam_id=? and exam_status='0' ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, exam.getExamName());
			ps.setInt(2, exam.getExamScore());
			ps.setInt(3, exam.getExamTime());
			ps.setInt(4, exam.getExamCredit());
			ps.setInt(5, exam.getExamId());
			result = ps.executeUpdate();
			if (result !=0) {
				flag=true;
			}else flag=false;
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return flag;
	}

}
