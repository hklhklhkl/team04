package com.ztkj.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtils {
	
	//��ȡ���ݿ�����
	public static Connection getConn(){
		Connection conn = null;
		try {
			//��ʼ�����ӳ�
			Context ct = new InitialContext();
			//��ȡ����Դ    java:comp/envΪ�̶�д��
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/local");
			//������Դ�л�ȡ����
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//�ر����ݿ�����
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
