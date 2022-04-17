package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.entity.User;
import com.db.util.MD5Util;

public class UserDao {
	DataBase db = new DataBase();
	
	public List<User> getUserlist() {

		String sql = "select * from user";

		ResultSet rs = db.getInfo(sql);

		List<User> userlist = new ArrayList<>();

		try {
			while(rs.next()) {
				
				User u = new User();
				
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setUpwd(rs.getString("upwd"));
				u.setName(rs.getString("name"));

				userlist.add(u);
				
			}
			return userlist;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userlist;
	}
	
	public int ResetUser(int uid) {
		String s = "select uname from user where uid=?";
		ResultSet rs = db.getInfo(s, uid);
		String uname="";
		try {
			rs.next();
			uname = MD5Util.getMD5Str(rs.getString("uname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "update user set upwd=? where uid = ?";
		
		return db.doExe(sql, uname, uid);
	}
	
	public int DelUser(int uid) {
		String sql = "delete from user where uid = ?";
		
		return db.doExe(sql, uid);
	}
}
