package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.CheckinDao;
import com.db.dao.HisDao;
import com.db.dao.RoomDao;

/**
 * Servlet implementation class CinDelServlet
 */
@WebServlet("/CinDelServlet")
public class CinDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CinDelServlet() {
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
		int type_id = Integer.parseInt(request.getParameter("type_id"));
		int roomnum = Integer.parseInt(request.getParameter("roomnum"));
		
		CheckinDao cind = new CheckinDao();
		HisDao hisd = new HisDao();
		RoomDao roomd = new RoomDao();
		
		int num = cind.delCin(c_id);
		
		int num1 = hisd.addhis(c_id, name, tel, id, type_id, date, roomnum);
		
		int num2 = roomd.changeRoom_out(type_id, roomnum);
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
