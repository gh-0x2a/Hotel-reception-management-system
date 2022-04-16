package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.HisDao;

/**
 * Servlet implementation class HisUpdateServlet
 */
@WebServlet("/HisUpdateServlet")
public class HisUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HisUpdateServlet() {
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
		int price = Integer.parseInt(request.getParameter("price"));
		
		HisDao hd = new HisDao();
		
		int num = hd.updateHis(c_id, name, tel, id, type_id, date, roomnum, price);
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
