package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDao {

	DataBase db=new DataBase();
	public boolean getLogin(String uname,String upwd){
		
		String sql="select * from admin where uname=? and upwd=?";
		ResultSet rs=db.getInfo(sql,uname,upwd);

		try {
			boolean bl=rs.next();
			if(bl){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
