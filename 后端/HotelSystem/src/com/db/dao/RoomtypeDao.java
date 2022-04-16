package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.entity.Roomtype;


public class RoomtypeDao {
	
	DataBase db = new DataBase();
	
	public List<Roomtype> getType() {

		String sql = "select type,type_id from room";

		ResultSet rs = db.getInfo(sql);

		List<Roomtype> roomtypelist = new ArrayList<>();

		try {
			while(rs.next()) {
				
				Roomtype roomtype = new Roomtype();
				
				roomtype.setId(rs.getInt("type_id"));
				roomtype.setType(rs.getString("type"));
				
				roomtypelist.add(roomtype);
				
			}
			return roomtypelist;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return roomtypelist;
	}
}
	
