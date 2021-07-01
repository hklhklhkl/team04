package com.ztkj.exam.examRuleDetail.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.exam.entity.StudentExamDetail;
import com.ztkj.exam.examRuleDetail.dao.IStudentExamDetailDao;
import com.ztkj.utils.DBUtils;


public class StudentExamDetailDaoImpl implements IStudentExamDetailDao {

	@Override
	public boolean addStudentExamDetail(StudentExamDetail sed) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String sql = "insert into t_studentExamDetail values(seq_t_studentExamDetail.nextval,?,?,?,?,?)";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, sed.getStudentExamId());
			ps.setInt(2, sed.getQuestionId());
			ps.setString(3, sed.getQuestionAnswer());
			ps.setString(4, sed.getUserAnswer());
			ps.setInt(5, sed.getUserScore());
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
	public boolean deleteStudentExamDetail(int studentExamId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String sql = "delete from t_studentExamDetail where studentExam_id=?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, studentExamId);
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
	public List<StudentExamDetail> findQuestion(int studentExamId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StudentExamDetail> list = new ArrayList<StudentExamDetail>();
		try{
			String sql = "select * from t_studentExamDetail where studentExam_id =? order by studentExamDetail_id";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, studentExamId);
			rs=ps.executeQuery();
			while(rs.next()){
				StudentExamDetail sed = new StudentExamDetail();
				sed.setStudentExamDetailId(rs.getInt("studentExamDetail_id"));
				sed.setStudentExamId(rs.getInt("studentExam_id"));
				sed.setQuestionId(rs.getInt("question_id"));
				sed.setQuestionAnswer(rs.getString("question_answer"));
				sed.setUserAnswer(rs.getString("user_answer"));
				sed.setUserScore(rs.getInt("user_score"));
				
				list.add(sed);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public boolean updateAnswer(int id,String answer,int score) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String sql = "update t_studentExamDetail set user_answer = ?, user_score=? where studentExamDetail_id=?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setString(1, answer);
			ps.setInt(2, score);
			ps.setInt(3, id);
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
	public int chaFen(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		try{
			String sql="select sum(user_score) from t_studentExamDetail where studentExam_id=?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if (rs.next()) {
				count=rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return count;
	}

	

}
