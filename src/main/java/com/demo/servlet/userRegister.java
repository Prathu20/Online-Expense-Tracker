package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.dao.UserDao;
import com.demo.db.HibernateUtil;
import com.demo.entity.User;

/**
 * Servlet implementation class userRegister
 */
public class userRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullName = request.getParameter("fullname");
		String email = request.getParameter("email");
		String passqord = request.getParameter("password");
		String about = request.getParameter("about");
		
		User u = new User(fullName, email, passqord, about);
		
		//System.out.println(u);
		UserDao dao = new UserDao(HibernateUtil.getSessionFactory());
		boolean f = dao.saveuser(u);
		
		HttpSession session = request.getSession();
		
		if(f) {
			session.setAttribute("msg", "Register Succesfully");
			//System.out.println("Register Succesfully");
			response.sendRedirect("register.jsp");
		}
		else {
			session.setAttribute("msg", "Something wrong in server");
			//System.out.println("Something wrong in server");
			response.sendRedirect("register.jsp");
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
