package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.RoomDao;

/**
 * Servlet implementation class RoomUpdateServlet
 */
@WebServlet("/RoomUpdateServlet")
public class RoomUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type_id = Integer.parseInt(request.getParameter("type_id"));
		int roomnum = Integer.parseInt(request.getParameter("roomnum"));
		String used = request.getParameter("used");
		String end_date = request.getParameter("end_date");
		
		int u = 0;
		String s = "正在使用";
		if(used.equals(s)) {
			u = 1;
		}
		
		RoomDao rd = new RoomDao();
		int num = rd.updateRoom(roomnum, type_id, u, end_date);
		if (num>0) {
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
