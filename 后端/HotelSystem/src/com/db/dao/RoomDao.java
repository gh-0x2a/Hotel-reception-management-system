package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.entity.Room;
import com.db.entity.Rooms;

public class RoomDao {
	
	DataBase db = new DataBase();
	
	public List<Room> getRoomAll() {

		String sql = "select * from room";

		ResultSet rs = db.getInfo(sql);

		List<Room> roomlist = new ArrayList<>();

		try {
			while(rs.next()) {
				
				Room room = new Room();
				
				room.setType_id(rs.getInt("type_id"));
				room.setType(rs.getString("type"));
				room.setPrice(rs.getInt("price"));
				room.setSum(rs.getInt("sum"));
				room.setUsed(rs.getInt("used"));
				room.setRemain(room.getSum() - room.getUsed());
				
				roomlist.add(room);
				
			}
			return roomlist;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return roomlist;
	}

	public boolean checkRoomnum(int type_id, int roomnum) {
		
		String sql = "select * from allroom where roomnum=? and type_id=?";
		
		ResultSet rs = db.getInfo(sql, roomnum, type_id);
		
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
	
	public boolean checkRoom(int roomnum) {
		String sql = "select * from allroom where roomnum=? and used=false";
		
		ResultSet rs = db.getInfo(sql, roomnum);
		
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
	
	//更改allroom房间状态和room总览状态
	public int changeRoom(int type_id, int roomnum, String end_date) {
		
		String sql = "update allroom set used=true,end_date=? where roomnum=?";
		String sql1 = "update room set used=used+1 where type_id=?";

		int res1 = db.doExe(sql, end_date, roomnum);
		int res2 = db.doExe(sql1, type_id);
		
		if(res1>0 && res2>0)
			return 1;
		return 0;
	}
	
	public int changeRoom_out(int type_id, int roomnum) {
		
		String sql = "update allroom set used=false,end_date=0 where roomnum=?";
		String sql1 = "update room set used=used-1 where type_id=?";
		

		int res1 = db.doExe(sql, roomnum);
		int res2 = db.doExe(sql1, type_id);
		
		if(res1>0 && res2>0)
			return 1;
		return 0;
	}
	
	public int findPrice(int type_id) {
		String sql = "select price from room where type_id=?";
		ResultSet rs = db.getInfo(sql, type_id);
		int res = 0;
		try {
			rs.next();
			res = rs.getInt("price");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int findroomnum(int c_id) {
		String sql = "select roomnum from checkin where c_id=?";
		ResultSet rs = db.getInfo(sql, c_id);
		int res = 0;
		try {
			rs.next();
			res = rs.getInt("roomnum");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int findtype_id(int c_id) {
		String sql = "select type_id from checkin where c_id=?";
		ResultSet rs = db.getInfo(sql, c_id);
		int res = 0;
		try {
			rs.next();
			res = rs.getInt("type_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int finddate(int c_id) {
		String sql = "select date from checkin where c_id=?";
		ResultSet rs = db.getInfo(sql, c_id);
		int res = 0;
		try {
			rs.next();
			res = rs.getInt("date");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Rooms> getRoomlist() {

		String sql = "select * from allroom";

		ResultSet rs = db.getInfo(sql);

		List<Rooms> roomlist = new ArrayList<>();

		try {
			while(rs.next()) {
				
				Rooms rooms = new Rooms();
				
				rooms.setType_id(rs.getInt("type_id"));
				int type_id = rs.getInt("type_id");
				String sql1 = "select type from room where type_id=?";
				ResultSet rs1 = db.getInfo(sql1, type_id);
				rs1.next();
				rooms.setType_id(type_id);
				rooms.setType(rs1.getString("type"));
				
				rooms.setRoomnum(rs.getInt("roomnum"));
				int t = rs.getInt("used");
				if(t == 1)
					rooms.setUsed("正在使用");
				else
					rooms.setUsed("待使用");
				
				rooms.setEnd_date(rs.getString("end_date"));
				
				roomlist.add(rooms);
				
			}
			return roomlist;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return roomlist;
	}
	
	public int addRoomt(int type_id, String type, int price, int sum, int used) {
		String sql = "insert into room(type_id, type, price, sum, used) values(?,?,?,?,?)";
		return db.doExe(sql, type_id, type, price, sum, used);
	}
	
	public int updateRoomt(int type_id, String type, int price, int sum, int used) {
		String sql = "update room set type=?, price=?, sum=?, used=? where type_id=?";
		return db.doExe(sql, type, price, sum, used, type_id);
	}
	
	public int delRoomt(int type_id) {
		String sql = "delete from room where type_id=?";
		return db.doExe(sql, type_id);
	}
	
	public int addRoom(int roomnum, int type_id, int used, String end_date) {
		String sql = "insert into allroom(roomnum, type_id, used, end_date) values(?,?,?,?)";
		return db.doExe(sql, roomnum, type_id, used, end_date);
	}
	
	public int updateRoom(int roomnum, int type_id, int used, String end_date) {
		String sql = "update allroom set type_id=?, used=?, end_date=? where roomnum=?";
		return db.doExe(sql, type_id, used, end_date, roomnum);
	}
	
	public int delRoom(int roomnum) {
		String sql = "delete from allroom where roomnum=?";
		return db.doExe(sql, roomnum);
	}
}
