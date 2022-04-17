package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegDao {

	DataBase db=new DataBase();
	
	public int addUser(String uname,String upwd, String name){
		String s = "select * from user where uname=?";
		ResultSet rs = db.getInfo(s, uname);
		try {
			boolean bl=rs.next();
			if(bl){
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="insert into user(uname, upwd, name) values(?,?,?)";
		return db.doExe(sql, uname, upwd, name);
	}
	
}
