package com.ztkj.exam.questionType.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.exam.entity.QuestionType;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.questionType.dao.IQuestionTypeDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.exam.utils.Page;

public class QuestionTypeDaoImpl implements IQuestionTypeDao{

	@Override
	public boolean addQuestionType(QuestionType questionType) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "insert into t_questionType values(seq_t_questionType.nextval,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,questionType.getQuestionTypeName());
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
	public boolean deleteQuestionType(int questionTypeId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			conn = DBUtils.getConn();
			String sql = "delete from t_questionType where questionType_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,questionTypeId);
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
	public int findTotalCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			String sql = "select count(1) from t_questionType";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				//getInt为重载的方法可以根据结果集中的字段名获取值也可以根据字段的列数获取值
				count=rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return count;
	}

	@Override
	public List<QuestionType> findAllQuestionType(Page page) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<QuestionType> list = new ArrayList<QuestionType>();
		try{
			conn = DBUtils.getConn();
			String sql = "select * from (select t.*,rownum rn from t_questionType t) where rn between ? and ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getEndIndex());
			rs = ps.executeQuery();
			while(rs.next()){
				QuestionType qt = new QuestionType();
				qt.setQuestionTypeId(rs.getInt("questionType_id"));
				qt.setQuestionTypeName(rs.getString("questionType_name"));
				list.add(qt);
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
	public boolean updatedeleteQuestionType(Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<QuestionType> findAllQuestionType() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<QuestionType> list = new ArrayList<QuestionType>();
		try{
			conn = DBUtils.getConn();
			String sql = "select * from  t_questionType";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				QuestionType qt = new QuestionType();
				qt.setQuestionTypeId(rs.getInt("questionType_id"));
				qt.setQuestionTypeName(rs.getString("questionType_name"));
				list.add(qt);
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
