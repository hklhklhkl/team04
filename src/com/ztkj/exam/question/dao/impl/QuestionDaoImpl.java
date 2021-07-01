package com.ztkj.exam.question.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.ztkj.exam.entity.Question;
import com.ztkj.exam.question.dao.IQuestionDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.exam.utils.Page;

public class QuestionDaoImpl implements IQuestionDao{

	@Override
	public boolean addQuestion(Question question) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "insert into t_question values(seq_t_question.nextval,?,?,?,?,'0',?,sysdate)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, question.getSubjectId());
			ps.setInt(2, question.getQuestionTypeId());
			ps.setString(3, question.getQuestionName());
			ps.setString(4, question.getQuestionAnswer());
			ps.setString(5, question.getQuestionAuthor());
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
	public boolean updateQuestion(Question question) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "update t_question set subject_id=?,questionType_id=?, question_name = ? ,question_answer = ? where question_id = ? and question_status='0'";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, question.getSubjectId());
			ps.setInt(2, question.getQuestionTypeId());
			ps.setString(3, question.getQuestionName());
			ps.setString(4, question.getQuestionAnswer());
			ps.setInt(5, question.getQuestionId());
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
	public boolean deleteQuestion(int questionId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "delete from t_question  where question_id = ? and question_status='0'";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, questionId);
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
	public List<Question> findAllQuestion(Page page,String questionName,int questionTypeId,int subjectId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Question> list = new ArrayList<Question>();
		try{
			StringBuffer sb = new StringBuffer("select * from "+
					"(select t.*,rownum rn from t_question t where 1=1 "
					);
			if (!"".equals(questionName)&&questionName!=null) {
				sb.append(" and question_name like '%"+questionName+"%'");
			}if (questionTypeId!=0) {
				sb.append(" and questionType_id="+questionTypeId);
			}if (subjectId!=0) {
				sb.append(" and subject_id="+subjectId);
			}
			sb.append(") where rn between ? and ?");
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sb.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getEndIndex());
			rs=ps.executeQuery();
			while(rs.next()){
				Question q = new Question();
				q.setQuestionId(rs.getInt("question_id"));
				q.setSubjectId(rs.getInt("subject_id"));
				q.setQuestionTypeId(rs.getInt("questionType_id"));
				q.setQuestionName(rs.getString("question_name"));
				q.setQuestionAnswer(rs.getString("question_answer"));
				q.setQuestionAuthor(rs.getString("question_author"));
				q.setQuestionDate(rs.getDate("question_date"));
				q.setQuestionStatus(rs.getString("question_status"));
				list.add(q);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}
	

	@Override
	public boolean updateQuestionStatus(Question question) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String status = null;
			conn = DBUtils.getConn();
			String sql = "update t_question set question_status=? where question_id = ?";
			ps = conn.prepareStatement(sql);
			status = question.getQuestionStatus();
			if (status.equals("1")) {
				status="0";
			}else{
				status="1";
			}
			ps.setString(1, status);
			ps.setInt(2, question.getQuestionId());
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
	public int findTotalCount(String questionName, int questionTypeId, int subjectId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			StringBuffer sb = new StringBuffer("select count(1) from t_question where 1=1");
			if (!"".equals(questionName)&& questionName!=null) {
				sb.append(" and question_name like '%"+questionName+"%'");
			}if(questionTypeId!=0){
				sb.append(" and questionType_id ="+questionTypeId);
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
		
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return count;
	}

	@Override
	public Question findQuestionById(int questionId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Question q = null;
		try{
			String sql = "select * from t_question where question_id = ?";
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, questionId);
			rs=ps.executeQuery();
			if(rs.next()){
				q = new Question();
				q.setQuestionId(rs.getInt("question_id"));
				q.setSubjectId(rs.getInt("subject_id"));
				q.setQuestionTypeId(rs.getInt("questionType_id"));
				q.setQuestionName(rs.getString("question_name"));
				q.setQuestionAnswer(rs.getString("question_answer"));
				q.setQuestionAuthor(rs.getString("question_author"));
				q.setQuestionDate(rs.getDate("question_date"));
				q.setQuestionStatus(rs.getString("question_status"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return q;
	}

	@Override
	public int findQuestionId() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			String sql = "select seq_t_question.currval from dual";
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if (rs.next()) {
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
	public int findCountBySubjectId(int subjectId,int questionTypeId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			String sql = "select count(1) from t_question where subject_id=? and questionType_id=?";
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, subjectId);
			ps.setInt(2, questionTypeId);
			rs=ps.executeQuery();
			if (rs.next()) {
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
	public List<Question> findQuestionBySubjectIdAndType(int subjectId, int questionTypeId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Question> list = new ArrayList<Question>();
		try{
			String sql = "select * from t_question where subject_id=? and questionType_id=?";
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, subjectId);
			ps.setInt(2, questionTypeId);
			rs=ps.executeQuery();
			while(rs.next()){
				Question q = new Question();
				q.setQuestionId(rs.getInt("question_id"));
				q.setSubjectId(rs.getInt("subject_id"));
				q.setQuestionTypeId(rs.getInt("questionType_id"));
				q.setQuestionName(rs.getString("question_name"));
				q.setQuestionAnswer(rs.getString("question_answer"));
				q.setQuestionAuthor(rs.getString("question_author"));
				q.setQuestionDate(rs.getDate("question_date"));
				q.setQuestionStatus(rs.getString("question_status"));
				list.add(q);
			}	
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}

}
