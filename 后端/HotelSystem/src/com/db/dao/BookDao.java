package com.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.entity.Book;

public class BookDao {
	
	DataBase db=new DataBase();
	
	public int addBook(String name,String tel, String id, String type_id, String date){
		
		String[] arr = date.split(",");
		
		String start_date = arr[0];
		String end_date = arr[1];
		
		String sql="insert into book(name, tel, id, type_id, start_date, end_date) values(?,?,?,?,?,?)";
		return db.doExe(sql, name, tel, id, type_id, start_date, end_date);
	}
	
	public List<Book> getBooklist() {

		String sql = "select * from book";

		ResultSet rs = db.getInfo(sql);

		List<Book> booklist = new ArrayList<>();

		try {
			while(rs.next()) {
				
				Book book = new Book();
				
				book.setBook_id(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setTel(rs.getString("tel"));
				book.setId(rs.getString("id"));
				
				int type_id = rs.getInt("type_id");
				String sql1 = "select type from room where type_id=?";
				ResultSet rs1 = db.getInfo(sql1, type_id);
				rs1.next();
				book.setType_id(type_id);
				book.setType(rs1.getString("type"));
				
				book.setStart_date(rs.getString("start_date"));
				book.setEnd_date(rs.getString("end_date"));
				
				booklist.add(book);
				
			}
			return booklist;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return booklist;
	}
	
	public int delBook(int book_id) {
		String sql = "delete from book where book_id = ?";
		
		return db.doExe(sql, book_id);
	}
	
	public int updateBook(int book_id, String name,String tel, String id, String type_id, String date) {
		String[] arr = date.split(",");
		
		String start_date = arr[0];
		String end_date = arr[1];
		
		String sql="update book set name=?, tel=?, id=?, type_id=?, start_date=?, end_date=? where book_id=?";
		return db.doExe(sql, name, tel, id, type_id, start_date, end_date, book_id);
	}
}
