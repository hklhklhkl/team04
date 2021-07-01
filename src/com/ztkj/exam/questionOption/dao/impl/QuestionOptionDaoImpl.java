package com.ztkj.exam.questionOption.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.exam.entity.QuestionOption;
import com.ztkj.exam.questionOption.dao.IQuestionOptionDao;
import com.ztkj.utils.DBUtils;

public class QuestionOptionDaoImpl implements IQuestionOptionDao{

	@Override
	public boolean addQuestionOption(QuestionOption questionOption) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn=DBUtils.getConn();
			String sql = "insert into t_questionOption values(seq_t_questionOption.nextval,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, questionOption.getQuestionId());
			ps.setString(2, questionOption.getQuestionOptionContent());
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
	public boolean deleteQuestionOption(int questionId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn=DBUtils.getConn();
			String sql = "delete from t_questionOption where question_id=?";
			ps=conn.prepareStatement(sql);
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
	public boolean updateQuestionOption(QuestionOption questionOption) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn=DBUtils.getConn();
			String sql = "update t_questionOption set questionOption_content=? where question_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, questionOption.getQuestionOptionContent());
			ps.setInt(2, questionOption.getQuestionOptionId());
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
	public List<QuestionOption> findQuestionOption(int questionId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<QuestionOption> list = new ArrayList<QuestionOption>();
		try{
			conn=DBUtils.getConn();
			String sql="select * from t_questionOption where question_id=? order  by questionOption_id";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, questionId);
			rs = ps.executeQuery();
			while(rs.next()){
				QuestionOption qo = new QuestionOption();
				qo.setQuestionId(rs.getInt("question_id"));
				qo.setQuestionOptionContent(rs.getString("questionOption_content"));
				qo.setQuestionOptionId(rs.getInt("questionOption_id"));
				list.add(qo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}

}
