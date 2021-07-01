package com.ztkj.forum.block.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ztkj.forum.block.dao.IBlockDao;
import com.ztkj.forum.entity.Block;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class BlockDaoImpl implements IBlockDao {

	@Override
	public List<Block> findAllBlock(Page<Block> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Block> list = new ArrayList<Block>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t1.*,rownum rn from "
					+ "(select t1.*,t2.user_name from t_block t1,t_user t2 where t1.user_id=t2.user_id) t1"
					+ " where 1=1");
			/*String sql = "select * from (select t.*,rownum rn from emp t ) where rn between ? and ?";*/
			if(page.getEntity().getBlockName() != null && !"".equals(page.getEntity().getBlockName())){
				sb.append(" and block_name like'%" + page.getEntity().getBlockName()+"%'");
			}
			if(page.getEntity().getBlockBiref() != null && !"".equals(page.getEntity().getBlockBiref())){
				sb.append(" and block_biref like'%" + page.getEntity().getBlockBiref()+"%'");
			}
			if(page.getEntity().getBlockBuff() > 0  && !"".equals(page.getEntity().getBlockBuff())){
				sb.append(" and block_buff =" + page.getEntity().getBlockBuff());
			}
			if(page.getEntity().getUserId() > 0  && !"".equals(page.getEntity().getUserId())){
				sb.append(" and user_id =" + page.getEntity().getUserId());
			}
			sb.append(") where rn between ? and ?");
			
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();
			
			while(rs.next()){
				Block block = new Block();
				block.setBlockBiref(rs.getString("block_biref"));
				block.setBlockBuff(rs.getInt("block_buff"));
				block.setBlockDate(rs.getDate("block_date"));
				block.setBlockId(rs.getInt("block_id"));
				block.setBlockName(rs.getString("block_name"));
				block.setBlockPhoto(rs.getString("block_photo"));
				block.setUserId(rs.getInt("user_id"));
				block.setUserName(rs.getString("user_name"));
				list.add(block);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public Block findBlockById(int blockId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Block block = null;
		try {
			String sql = "select * from t_block where block_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, blockId);
			rs = pstm.executeQuery();
			if(rs.next()){
				block = new Block();
				block.setBlockBiref(rs.getString("block_biref"));
				block.setBlockBuff(rs.getInt("block_buff"));
				block.setBlockDate(rs.getDate("block_date"));
				block.setBlockId(rs.getInt("block_id"));
				block.setBlockName(rs.getString("block_name"));
				block.setBlockPhoto(rs.getString("block_photo"));
				block.setUserId(rs.getInt("user_id"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return block;
	}

	@Override
	public boolean addBlock(Block block) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_block values(seq_t_block.nextval,?,?,?,?,1,sysdate)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(4, block.getBlockBiref());
			pstm.setString(1, block.getBlockName());
			pstm.setString(3, block.getBlockPhoto());
			
			pstm.setInt(2, block.getUserId());
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean deleteBlock(int blockId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "delete from t_block where block_id=?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, blockId);
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean updateBlock(Block block) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_block set block_biref = ?,block_name = ?,block_photo = ?,block_date = sysdate,user_id = ? where block_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, block.getBlockBiref());
			pstm.setString(2, block.getBlockName());
			pstm.setString(3, block.getBlockPhoto());
			pstm.setInt(4, block.getUserId());
			pstm.setInt(5, block.getBlockId());
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}



	@Override
	public int findTotalCountByLike(Page<Block> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_block where 1 = 1");
			/*String sql = "select count(1) from emp where 1 = 1";*/
			if(page.getEntity().getBlockName() != null && !"".equals(page.getEntity().getBlockName())){
				sb.append(" and block_name like'%" + page.getEntity().getBlockName()+"%'");
			}
			if(page.getEntity().getBlockBiref() != null && !"".equals(page.getEntity().getBlockBiref())){
				sb.append(" and block_biref like'%" + page.getEntity().getBlockBiref()+"%'");
			}
			if(page.getEntity().getBlockBuff() > 0  && !"".equals(page.getEntity().getBlockBuff()) && page.getEntity().getBlockBuff() <= 1){
				sb.append(" and block_buff =" + page.getEntity().getBlockBuff());
			}
			if(page.getEntity().getUserId() != 0){
				sb.append(" and user_id =" + page.getEntity().getUserId());
			}
			System.out.println(sb.toString());
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			rs = pstm.executeQuery();
			if(rs.next()){
				
				//getint方法为重载方法，可以根据结果集中的字段名字来获取值，也可以根据字段名为第几列来获取该字段的值
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public List<Block> findTotalConut() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Block> list = new ArrayList<Block>();
		try {
			//String sql = "select count(1) cc from emp";
			String sql = "select t2.post_id,count(1),t2.post_state,t2.post_content,t2.block_id,t1.block_name,t3.user_name,t1.block_biref from t_block t1,t_post t2,t_user t3 where t1.block_id = t2.block_id and t2.user_id = t3.user_id group by t2.block_id,t1.block_name,t3.user_name,t1.block_biref,t2.post_content,t2.post_state,t2.post_id";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				//getInt方法为重载的方法,可以根据结果集中的字段名来获取值,也可以根据字段名为第几列来获取该字段的值
				//count = rs.getInt("cc");
				Block block = new Block();
				block.setCount(rs.getInt("count(1)"));
				block.setBlockId(rs.getInt("block_id"));
				block.setBlockName(rs.getString("block_name"));
				block.setUserName(rs.getString("user_name"));
				block.setBlockBiref(rs.getString("block_biref"));
				block.setPostContent(rs.getString("post_content"));
				block.setPostState(rs.getInt("post_state"));
				block.setPostId(rs.getInt("post_id"));
				list.add(block);
				System.out.println(block.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Block> findNameById() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Block> list = new ArrayList<Block>();
		try {
			String sql = "select distinct t1.user_name,t1.user_id from t_user t1,t_block t2 where t1.user_id = t2.user_id and t1.user_id = t2.user_id";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Block block = new Block();
				block.setUserId(rs.getInt("user_id"));
				block.setUserName(rs.getString("user_name"));
				list.add(block);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public boolean buff(int blockId, int blockBuff) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_block set block_buff = ? where block_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, blockBuff);
			pstm.setInt(2, blockId);
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	

	
	

}
