package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.ExpenseDao;
import com.demo.db.HibernateUtil;
import com.demo.entity.Expense;
import com.demo.entity.User;

/**
 * Servlet implementation class UpdateExpenseServlet
 */
public class UpdateExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExpenseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		
		Expense exp = new Expense(title, date, time, description, price, user);
		exp.setId(id);
		ExpenseDao dao = new ExpenseDao(HibernateUtil.getSessionFactory());
		 boolean f = dao.updateExpense(exp);
		 
		 if(f) {
				session.setAttribute("msg", "Expense Updated Succesfully");
				//System.out.println("Register Succesfully");
				response.sendRedirect("user/view_expense.jsp");
			}
			else {
				session.setAttribute("msg", "Something wrong in server");
				//System.out.println("Something wrong in server");
				response.sendRedirect("user/view_expense.jsp");
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
