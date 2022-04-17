package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.entity.Checkin;

public class CheckinDao {
	
	DataBase db=new DataBase();
	
	public int addcin(String name,String tel, String id, int type_id, String start_date, String end_date, int roomnum){
		
		String sql="insert into checkin(name, tel, id, type_id, roomnum, start_date, end_date) values(?,?,?,?,?,?,?)";
		return db.doExe(sql, name, tel, id, type_id, roomnum, start_date, end_date);
	}

	public List<Checkin> getCinlist() {

		String sql = "select * from checkin";

		ResultSet rs = db.getInfo(sql);

		List<Checkin> cinlist = new ArrayList<>();

		try {
			while(rs.next()) {
				
				Checkin cin = new Checkin();
				
				cin.setC_id(rs.getInt("c_id"));
				cin.setName(rs.getString("name"));
				cin.setTel(rs.getString("tel"));
				cin.setId(rs.getString("id"));
				
				int type_id = rs.getInt("type_id");
				String sql1 = "select type from room where type_id=?";
				ResultSet rs1 = db.getInfo(sql1, type_id);
				rs1.next();
				cin.setType_id(type_id);
				cin.setType(rs1.getString("type"));
				
				cin.setStart_date(rs.getString("start_date"));
				cin.setEnd_date(rs.getString("end_date"));
				cin.setRoomnum(rs.getInt("roomnum"));
				
				cinlist.add(cin);
				
			}
			return cinlist;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cinlist;
	}
	
	public int updateCin(int c_id, String name, String tel, String id, String type_id, String date, int roomnum) {
		String[] arr = date.split(",");
		
		String start_date = arr[0];
		String end_date = arr[1];
		
		String sql="update checkin set name=?, tel=?, id=?, type_id=?, start_date=?, end_date=?, roomnum=? where c_id=?";
		return db.doExe(sql, name, tel, id, type_id, start_date, end_date, roomnum, c_id);
	}
	
	public int delCin(int c_id) {
		String sql = "delete from checkin where c_id = ?";

		return db.doExe(sql, c_id);
	}
}