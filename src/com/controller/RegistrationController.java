package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loginbean.LoginBean;
import com.model.LoginModel;

/**
 * Servlet implementation class RegistrationController
 */
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String status = "unsuccessful";
			LoginBean b = new LoginBean();
			LoginModel a = new LoginModel();
			b.setUsername(request.getParameter("username"));
			b.setPassword(request.getParameter("password"));
			b.setFirstname(request.getParameter("firstname"));
			b.setLastname(request.getParameter("lastname"));
			b.setEmail(request.getParameter("email"));
			b = a.RegisterUser(b);
			
			if(b.isValid()){
				System.out.println("inside register successful");
				status = "successful";
				request.setAttribute("status", status);
				request.getRequestDispatcher("create-account.jsp").forward(request, response);
				
				
				
				//logger.log(Level.INFO, "Author Loggedin");
			}
			else{
				//logger.log(Level.INFO, "Author Loggedin Failed");
				status = "unsuccessful";
				request.setAttribute("status", status);
				request.getRequestDispatcher("create-account.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
