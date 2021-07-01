package com.ztkj.exam.exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.exam.entity.ExamRule;
import com.ztkj.exam.entity.StudentExam;
import com.ztkj.exam.entity.Subject;
import com.ztkj.exam.exam.dao.IStudentExamDao;
import com.ztkj.exam.utils.Page;

import com.ztkj.utils.DBUtils;

public  class StudentExamDaoImpl implements IStudentExamDao {

	@Override
	public boolean addStudentExam(StudentExam studentExam) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try{
			String sql = "insert into t_studentExam values(seq_t_studentExam.nextval,?,?,?,0,sysdate,'0',0,0)";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, studentExam.getUserId());
			ps.setInt(2, studentExam.getExamRuleId());
			ps.setInt(3, studentExam.getExamScore());
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
	public boolean deleteStudentExam(int studentExamId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		
		try{
			String sql = "delete from t_studentExam where studentExam_id=?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, studentExamId);
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
			String sql = "select studentExam_id from t_studentExam where create_time = (select max(create_time) from t_studentExam)";
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
	public boolean jiaFen(int id,int score) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag =false;
		try{
			String sql = "update t_studentExam set user_score2=?,status ='1' where studentExam_id = ?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,score);
			ps.setInt(2, id);
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
	public boolean jiaFen2(int id,int score) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag =false;
		try{
			String sql = "update t_studentExam set user_score=? where studentExam_id = ?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,score);
			ps.setInt(2, id);
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
	public int findTotalCount(String userName, String examName, int subjectId, String dateOne, String dateTwo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			StringBuffer sb = new StringBuffer("select count(1) from t_studentExam where 1=1 and status='1'");
			if (userName!=null&&!"".equals(userName)) {
				sb.append(" and user_id in(select user_id from t_user where user_name like '%"+userName+"%')");
			}if (examName!=null&&!"".equals(examName)) {
				sb.append("and examRule_id in(select exam_id from t_examRule where exam_name like '%"+examName+"%')");
			}if (subjectId!=0) {
				sb.append(" and examRule_id in(select exam_id from t_examRule where subject_id="+subjectId+")");
			}if (dateOne!=null&&!"".equals(dateOne)){
				sb.append("and create_time >=to_date('"+dateOne+"','yyyy-mm-dd')");
			}if (dateTwo!=null&&!"".equals(dateTwo)) {
				sb.append("and create_time <=to_date('"+dateTwo+"','yyyy-mm-dd')");
			}
			conn = DBUtils.getConn();
			
			ps=conn.prepareStatement(sb.toString());
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
	public List<StudentExam> findAll(Page page, String userName, String examrName, int subjectId, String dateOne,
			String dateTwo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StudentExam> list = new ArrayList<StudentExam>();
		try{
			StringBuffer sb = new StringBuffer("select*from(select t.*,rownum rn from t_studentExam t where 1=1  ");
			if (userName!=null&&!"".equals(userName)) {
				sb.append(" and user_id in(select user_id from t_user where user_name like '%"+userName+"%') ");
			}if (examrName!=null&&!"".equals(examrName)) {
				sb.append(" and examRule_id in(select exam_id from t_examRule where exam_name like '%"+examrName+"%')");
			}if (subjectId!=0) {
				sb.append(" and examRule_id in(select exam_id from t_examRule where subject_id="+subjectId+")");
			}if (dateOne!=null&&!"".equals(dateOne)) {
				sb.append(" and create_time >=to_date('"+dateOne+"','yyyy-mm-dd') ");
			}if (dateTwo!=null&&!"".equals(dateTwo)) {
				sb.append(" and create_time <=to_date('"+dateTwo+"','yyyy-mm-dd')");
			}
			sb.append(" and status='1')where rn between ? and ?");
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sb.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getEndIndex());
			rs=ps.executeQuery();
			while(rs.next()){
				StudentExam studentExam = new StudentExam();
				studentExam.setStudentExamId(rs.getInt("studentExam_id"));
				studentExam.setUserId(rs.getInt("user_id"));
				studentExam.setExamRuleId(rs.getInt("examRule_id"));
				studentExam.setExamScore(rs.getInt("exam_score"));
				studentExam.setCreateDate(rs.getDate("create_time"));
				studentExam.setStatus(rs.getString("status"));
				studentExam.setUserScore2(rs.getInt("user_score2"));
				studentExam.setUserScore(rs.getInt("user_score"));
				studentExam.setCredit(rs.getInt("credit"));
				list.add(studentExam);
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
	public Subject findSubjectName(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Subject subject = null;
		try{
			String sql = "select subject_name from t_subject where subject_id=(select subject_id from t_examRule where exam_id =(select examRule_id from t_studentExam where studentExam_id = ?))";
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if (rs.next()) {
				subject = new Subject();
				subject.setSubjectName(rs.getString("subject_name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return subject;
	}

	@Override
	public ExamRule findExamName(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ExamRule examRule = null;
		try{
			String sql = "select exam_name from t_examRule where exam_id = ?";
			conn=DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if (rs.next()) {
				examRule = new ExamRule();
				examRule.setExamName(rs.getString("exam_name"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return examRule;
	}

	@Override
	public int findTotalCount(String userName, String examName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try{
			conn=DBUtils.getConn();
			StringBuffer sb = new StringBuffer("select count(1) from t_studentExam where 1=1 and status='0'" );
			if (userName!=null&&!"".equals(userName)) {
				sb.append(" and user_id in(select user_id from t_user where user_name like '%"+userName+"%')");
			}if (examName!=null&&!"".equals(examName)) {
				sb.append("and examRule_id in(select exam_id from t_examRule where exam_name like '%"+examName+"%')");
			}
			ps=conn.prepareStatement(sb.toString());
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
	public List<StudentExam> findAll(Page page, String userName,String examName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StudentExam> list = new ArrayList<StudentExam>();
		try{
			conn =DBUtils.getConn();
			StringBuffer sb = new StringBuffer("select*from(select t.*,rownum rn from t_studentExam t where 1=1  and status='0'");
			if (userName!=null&&!"".equals(userName)) {
				sb.append(" and user_id in(select user_id from t_user where user_name like '%"+userName+"%') ");
			}if (examName!=null&&!"".equals(examName)) {
				sb.append(" and examRule_id in(select exam_id from t_examRule where exam_name like '%"+examName+"%')");
			}
			sb.append(")where rn between ? and ?");
			ps=conn.prepareStatement(sb.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getEndIndex());
			rs=ps.executeQuery();
			while(rs.next()){
				StudentExam studentExam = new StudentExam();
				studentExam.setStudentExamId(rs.getInt("studentExam_id"));
				studentExam.setUserId(rs.getInt("user_id"));
				studentExam.setExamRuleId(rs.getInt("examRule_id"));
				studentExam.setExamScore(rs.getInt("exam_score"));
				studentExam.setUserScore(rs.getInt("user_score"));
				studentExam.setCreateDate(rs.getDate("create_time"));
				studentExam.setUserScore2(rs.getInt("user_score2"));
				studentExam.setCredit(rs.getInt("credit"));
				list.add(studentExam);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return list;
	}

	@Override
	public StudentExam findStudentExamById(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StudentExam studentExam = null;
		try{
			conn =DBUtils.getConn();
			String sql = "select * from t_studentExam where studentExam_id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				studentExam = new StudentExam();
				studentExam.setStudentExamId(rs.getInt("studentExam_id"));
				studentExam.setUserId(rs.getInt("user_id"));
				studentExam.setExamRuleId(rs.getInt("examRule_id"));
				studentExam.setExamScore(rs.getInt("exam_score"));
				studentExam.setUserScore(rs.getInt("user_score"));
				studentExam.setCreateDate(rs.getDate("create_time"));
				studentExam.setUserScore2(rs.getInt("user_score2"));
				studentExam.setCredit(rs.getInt("credit"));
				}
			}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return studentExam;
	}

	@Override
	public int findQuestionScore(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		try{
			String sql="select score from t_examRuleDetail where examRule_id=(select examRule_id from t_studentExam where studentExam_id=?) "+
		"and questionType_id=5";
			conn =DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
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
	public boolean jiaCredit(int id, int credit) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag =false;
		try{
			String sql = "update t_studentExam set credit=? where studentExam_id = ?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,credit);
			ps.setInt(2, id);
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
	public StudentExam findStudentCredit(int userId, int examRuleId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StudentExam studentExam = null;
		try{
			conn =DBUtils.getConn();
			String sql = "select * from t_studentExam where user_id = ? and examRule_id=? and status='1'";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, examRuleId);
			rs=ps.executeQuery();
			if(rs.next()){
				studentExam = new StudentExam();
				studentExam.setStudentExamId(rs.getInt("studentExam_id"));
				studentExam.setUserId(rs.getInt("user_id"));
				studentExam.setExamRuleId(rs.getInt("examRule_id"));
				studentExam.setExamScore(rs.getInt("exam_score"));
				studentExam.setUserScore(rs.getInt("user_score"));
				studentExam.setCreateDate(rs.getDate("create_time"));
				studentExam.setUserScore2(rs.getInt("user_score2"));
				studentExam.setCredit(rs.getInt("credit"));
				
				}
			}catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, ps, conn);
		}
		return studentExam;
	}

	@Override
	public boolean addUserCredit(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag =false;
		try{
			String sql = "update t_user set user_exam_integral =(select sum(credit)  from t_studentExam where user_id=? and status='1')where user_id=?";
			conn = DBUtils.getConn();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,userId);
			ps.setInt(2, userId);
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
