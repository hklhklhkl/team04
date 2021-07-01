package com.ztkj.exam.examRuleDetail.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.entity.ExamRuleDetail;
import com.ztkj.exam.examRuleDetail.dao.IExamRuleDetailDao;
import com.ztkj.utils.DBUtils;

public class ExamRuleDetailDaoImpl implements IExamRuleDetailDao {

	@Override
	public List<ExamRuleDetail> findAllExamRuleDetailById(int examId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ExamRuleDetail> list =  new ArrayList<ExamRuleDetail>();
		try{
			conn = DBUtils.getConn();
			String sql = "select * from t_examRuleDetail where examRule_id=? order by examRule_id,questionType_id";
			ps= conn.prepareStatement(sql);
			ps.setInt(1, examId);
			rs=ps.executeQuery();
			while(rs.next()){
				ExamRuleDetail erd = new ExamRuleDetail();
				erd.setExamRuleDetailId(rs.getInt("examRuleDetail_id"));
				erd.setExamRuleId(rs.getInt("examRule_id"));
				erd.setQuestionNum(rs.getInt("questionNum"));
				erd.setQuestionTypeId(rs.getInt("questionType_id"));
				erd.setScore(rs.getInt("score"));
				list.add(erd);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return list;
	}

	@Override
	public boolean deleteExamRuleDetail(int examId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "delete from t_examRuleDetail  where examRule_id = ? ";
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
	public List<ExamRuleDetail> findAllExamRuleDetail() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ExamRuleDetail> list =  new ArrayList<ExamRuleDetail>();
		try{
			conn = DBUtils.getConn();
			String sql = "select * from t_examRuleDetail order by examRule_id,questionType_id";
			ps= conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				ExamRuleDetail erd = new ExamRuleDetail();
				erd.setExamRuleDetailId(rs.getInt("examRuleDetail_id"));
				erd.setExamRuleId(rs.getInt("examRule_id"));
				erd.setQuestionNum(rs.getInt("questionNum"));
				erd.setQuestionTypeId(rs.getInt("questionType_id"));
				erd.setScore(rs.getInt("score"));
				list.add(erd);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(null, ps, conn);
		}
		return list;
	}

	@Override
	public boolean addExamRuleDetail(ExamRuleDetail examRuleDetail) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String sql = "insert into t_examRuleDetail values(seq_t_examRuleDetail.nextval,?,?,?,? )";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, examRuleDetail.getExamRuleId());
			ps.setInt(2, examRuleDetail.getQuestionTypeId());
			ps.setInt(3, examRuleDetail.getQuestionNum());
			ps.setInt(4,examRuleDetail.getScore());
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

}
