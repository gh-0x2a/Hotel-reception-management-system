package com.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {

	Connection conn;
	
	public void getconn(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbhotel?useUnicode=true&characterEncoding=UTF-8", "root","1234");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public int doExe(String sql,Object... obj){
		
		getconn();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i+1, obj[i]);
			}
			
			return pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	

	public ResultSet getInfo(String sql,Object... obj){
		
		getconn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i+1, obj[i]);
			}
			
			return pstmt.executeQuery();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	} 
	
}
