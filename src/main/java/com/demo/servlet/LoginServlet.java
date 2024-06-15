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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passqord = request.getParameter("password");
		
		UserDao dao = new UserDao(HibernateUtil.getSessionFactory());
		User u = dao.login(email, passqord);
		
		
		HttpSession session = request.getSession();
		
		if(u == null) {
			session.setAttribute("msg", "invalid email and password");
			response.sendRedirect("login.jsp");
		}
		else {
			session.setAttribute("loginUser", u);
			//System.out.println("Something wrong in server");
			response.sendRedirect("user/home.jsp");
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
