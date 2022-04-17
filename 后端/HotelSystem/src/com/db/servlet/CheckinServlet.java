package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.BookDao;
import com.db.dao.CheckinDao;
import com.db.dao.RoomDao;

/**
 * Servlet implementation class CheckinServlet
 */
@WebServlet("/CheckinServlet")
public class CheckinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String tel = request.getParameter("tel");
		String date = request.getParameter("date");
		String type_id = request.getParameter("type_id");
		String roomnum = request.getParameter("roomnum");
		int room = Integer.parseInt(roomnum);
		int t_id = Integer.parseInt(type_id);
		
		int num2 = 1;
		if(book_id != -1) {
			BookDao bd = new BookDao();
			num2 = bd.delBook(book_id);
		}
		
		String[] arr = date.split(",");
		
		String start_date = arr[0];
		String end_date = arr[1];
		
		CheckinDao checkind = new CheckinDao();
		RoomDao roomd = new RoomDao();
		
		//检查房间类型与房间号是否匹配
		boolean bol = roomd.checkRoomnum(t_id, room);
		if(bol == false) {
			response.getWriter().append("type-roomnumwrong");
			return;
		}
		
		//检查房间是否正在使用
		boolean bol1 = roomd.checkRoom(room);
		if(bol1 == false) {
			response.getWriter().append("roomused");
			return;
		}
		//写入入住信息
		int num=checkind.addcin(name, tel, id, t_id, start_date, end_date, room);
		//更改房间状态
		int num1 = roomd.changeRoom(t_id, room, end_date);
		
		if (num>0 && num1>0 && num2>0) {
			response.getWriter().append("success");
		} else{
			response.getWriter().append("fail");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
