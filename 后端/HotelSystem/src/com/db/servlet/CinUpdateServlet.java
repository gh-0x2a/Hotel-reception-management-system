package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.CheckinDao;
import com.db.dao.RoomDao;

/**
 * Servlet implementation class CinUpdateServlet
 */
@WebServlet("/CinUpdateServlet")
public class CinUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CinUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String tel = request.getParameter("tel");
		String date = request.getParameter("date");
		String type_id = request.getParameter("type_id");
		int roomnum = Integer.parseInt(request.getParameter("roomnum"));
		
		CheckinDao cind = new CheckinDao();
		

		RoomDao roomd = new RoomDao();
		
		boolean bol = roomd.checkRoomnum(Integer.parseInt(type_id), roomnum);
		if(bol == false) {
			response.getWriter().append("type-roomnumwrong");
			return;
		}
		
		boolean bol1 = roomd.checkRoom(roomnum);
		if(bol1 == false) {
			response.getWriter().append("roomused");
			return;
		}
		
		int oldroomnum = roomd.findroomnum(c_id);
		int oldtype_id = roomd.findtype_id(c_id);
		
		int num=cind.updateCin(c_id, name, tel, id, type_id, date, roomnum);
		
		int num1 = roomd.changeRoom_out(oldtype_id, oldroomnum);
		
		String[] arr = date.split(",");
		String end_date = arr[1];
		int num2 = roomd.changeRoom(Integer.parseInt(type_id), roomnum, end_date);
		
		
		if (num>0 && num1>0 && num2>0) {
			response.getWriter().append("success");
		} else {
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
