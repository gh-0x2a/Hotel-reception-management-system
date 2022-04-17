package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.RegDao;
import com.db.util.MD5Util;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String upwd = MD5Util.getMD5Str(request.getParameter("upwd"));
		String name = request.getParameter("name");
	
		RegDao rDao=new RegDao();
		
		int num=rDao.addUser(uname, upwd, name);

		if (num>0) {
			response.getWriter().append("success");
		} else if(num == -1) {
			response.getWriter().append("unamerepeat");
		} else {
			response.getWriter().append("fail");
		}
	}

}
