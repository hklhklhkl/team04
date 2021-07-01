package com.ztkj.sys.img.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ztkj.sys.entity.Img;
import com.ztkj.sys.entity.Module;
import com.ztkj.sys.img.dao.IImgDao;
import com.ztkj.utils.DBUtils;
import com.ztkj.utils.Page;

public class ImgDaoImpl implements IImgDao {

	@Override
	public List<Img> findImgByPage(Page<Img> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Img> list = new ArrayList<Img>();
		try {
			StringBuffer sb = new StringBuffer("select * from (select t.*,rownum rn from t_img t where 1 = 1 ");
			if(page.getEntity().getImgPic() != null && !"".equals(page.getEntity().getImgPic())){
				sb.append(" and img_pic like '%"+page.getEntity().getImgPic()+"%'");
			}
			if(page.getEntity().getImgState() != -1){
				sb.append(" and img_state = '"+page.getEntity().getImgState()+"'");
			}
			sb.append(" order by img_id desc) where rn between ? and ? ");
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			pstm.setInt(1, page.getStartIndex());
			pstm.setInt(2, page.getEndIndex());
			rs = pstm.executeQuery();
			while(rs.next()){
				Img img = new Img();
				img.setImgId(rs.getInt("img_id"));
				img.setImgPic(rs.getString("img_pic"));
				img.setImgPlace(rs.getInt("img_place"));
				img.setImgCreatorId(rs.getInt("img_creatorId"));
				img.setImgCreateDate(rs.getDate("img_createDate"));
				img.setImgState(rs.getInt("img_state"));
				list.add(img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int findTotalCount(Page<Img> page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			StringBuffer sb = new StringBuffer("select count(1) from t_img where 1 = 1");
			if(page.getEntity().getImgPic() != null && !"".equals(page.getEntity().getImgPic())){
				sb.append(" and img_pic like '%"+page.getEntity().getImgPic()+"%'");
			}
			if(page.getEntity().getImgState() != -1){
				sb.append(" and img_state = '"+page.getEntity().getImgState()+"'");
			}
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sb.toString());
			rs = pstm.executeQuery();			
			while(rs.next()){
				count = rs.getInt(1);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public boolean addImg(Img img) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "insert into t_img values(seq_t_img.nextval,?,?,?,?,1)";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, img.getImgPic());
			pstm.setInt(2, img.getImgPlace());
			pstm.setInt(3, img.getImgCreatorId());			
			pstm.setDate(4, new java.sql.Date(new Date().getTime()));
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean updateImg(Img img) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_img set img_pic = ?,img_place = ? where img_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, img.getImgPic());
			pstm.setInt(2, img.getImgPlace());
			pstm.setInt(3, img.getImgId());			
			int result = pstm.executeUpdate();
			if(result != 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public boolean deleteImg(Img img) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = false;
		try {
			String sql = "update t_img set img_state = ? where img_id = ?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);
			if(img.getImgState()==0){
				pstm.setInt(1, 1);
			}else{
				pstm.setInt(1, 0);
			}
			pstm.setInt(2, img.getImgId());
			int result = pstm.executeUpdate();
			if(result!=0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return flag;
	}

	@Override
	public Img findImgById(int imgId) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Img img = new Img();
		try {
			String sql = "select * from t_img where img_id=?";
			conn = DBUtils.getConn();
			pstm = conn.prepareStatement(sql);	
			pstm.setInt(1, imgId);
			rs = pstm.executeQuery();
			if(rs.next()){					
				img.setImgId(rs.getInt("img_id"));
				img.setImgPic(rs.getString("img_pic"));
				img.setImgPlace(rs.getInt("img_place"));
				img.setImgCreatorId(rs.getInt("img_creatorId"));
				img.setImgCreateDate(rs.getDate("img_createDate"));
				img.setImgState(rs.getInt("img_state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtils.closeAll(null, pstm, conn);
		}
		return img;
	}

}
