package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.RoomDao;

/**
 * Servlet implementation class RoomtAddServlet
 */
@WebServlet("/RoomtAddServlet")
public class RoomtAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomtAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type_id = Integer.parseInt(request.getParameter("type_id"));
		String type = request.getParameter("type");
		int price = Integer.parseInt(request.getParameter("price"));
		int sum = Integer.parseInt(request.getParameter("sum"));
		int used = Integer.parseInt(request.getParameter("used"));
		
		RoomDao rd = new RoomDao();
		int num = rd.addRoomt(type_id, type, price, sum, used);
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
