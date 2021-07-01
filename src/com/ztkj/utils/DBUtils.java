package com.ztkj.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtils {
	
	//获取数据库连接
	public static Connection getConn(){
		Connection conn = null;
		try {
			//初始化连接池
			Context ct = new InitialContext();
			//获取数据源    java:comp/env为固定写法
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/local");
			//从数据源中获取连接
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭数据库连接
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
