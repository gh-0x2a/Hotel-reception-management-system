package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.entity.His;

public class HisDao {

	DataBase db=new DataBase();
	
	public int addhis(int c_id, String name,String tel, String id, int type_id, String date, int roomnum){
		
		String sql1="insert into history(c_id, name, tel, id, type_id, roomnum, start_date, end_date, price) values(?,?,?,?,?,?,?,?,?)";
		
		String[] arr = date.split(",");
		String start_date = arr[0];
		String end_date = arr[1];
		
		int price = 0;
		String sql2 = "select price from room where type_id=?";
		ResultSet rs = db.getInfo(sql2, type_id);
		try {
			rs.next();
			price = rs.getInt("price");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int sy = Integer.parseInt(start_date.substring(0, 4));
		int sm = Integer.parseInt(start_date.substring(5, 7));
		int sd = Integer.parseInt(start_date.substring(8));
		
		int ey = Integer.parseInt(end_date.substring(0, 4));
		int em = Integer.parseInt(end_date.substring(5, 7));
		int ed = Integer.parseInt(end_date.substring(8));
		
		int cnt = 0;
		int flag = 0;
		for(int i = sy; i <= ey; i++) {
			if((i % 4 == 0 && i % 100 != 0) || i % 400 == 0)
				month[2] = 29;
			int j = 1;
			if(i == sy)
				j = sm;
			for(; j <= 12; j++) {
				int k = 1;
				if(i == sy && j == sm)
					k = sd;
				for(; k <= month[j]; k++) {
					if(i == ey && j == em && k == ed) {
						flag = 1;
						break;
					}
					cnt++;
				}
				if(flag == 1) break;
			}
			month[2] = 28;
			if(flag == 1) break;
		}
		
		price = price * cnt;
		
		return db.doExe(sql1, c_id, name, tel, id, type_id, roomnum, start_date, end_date, price);
	}

	public List<His> getHislist() {

		String sql = "select * from history";

		ResultSet rs = db.getInfo(sql);

		List<His> hislist = new ArrayList<>();

		try {
			while(rs.next()) {
				
				His his = new His();
				
				his.setC_id(rs.getInt("c_id"));
				his.setName(rs.getString("name"));
				his.setTel(rs.getString("tel"));
				his.setId(rs.getString("id"));
				
				int type_id = rs.getInt("type_id");
				String sql1 = "select type from room where type_id=?";
				ResultSet rs1 = db.getInfo(sql1, type_id);
				rs1.next();
				his.setType_id(type_id);
				his.setType(rs1.getString("type"));
				
				his.setStart_date(rs.getString("start_date"));
				his.setEnd_date(rs.getString("end_date"));
				his.setRoomnum(rs.getInt("roomnum"));
				his.setPrice(rs.getInt("price"));
				
				hislist.add(his);
				
			}
			return hislist;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return hislist;
	}
	
	public int updateHis(int c_id, String name, String tel, String id, String type_id, String date, int roomnum, int price) {
		String[] arr = date.split(",");
		
		String start_date = arr[0];
		String end_date = arr[1];
		
		String sql="update history set name=?, tel=?, id=?, type_id=?, start_date=?, end_date=?, roomnum=?, price=? where c_id=?";
		return db.doExe(sql, name, tel, id, type_id, start_date, end_date, roomnum, price, c_id);
	}
	
	public int delHis(int c_id) {
		String sql = "delete from history where c_id = ?";
		
		return db.doExe(sql, c_id);

	}
}
