package com.db.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.dao.BookDao;

/**
 * Servlet implementation class BookUpdateServlet
 */
@WebServlet("/BookUpdateServlet")
public class BookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookUpdateServlet() {
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
		
		BookDao bookd = new BookDao();
		
		int num=bookd.updateBook(book_id, name, tel, id, type_id, date);

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
