package com.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loginbean.*;
import com.model.*;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
			LoginBean b = new LoginBean();
			LoginModel a = new LoginModel();
			String user_name=request.getParameter("username");
			String pass_word=request.getParameter("password");
			if("".equals(user_name) || "".equals(pass_word)){
				response.sendRedirect("login.jsp");
			}
			
			else{
				b.setUsername(user_name);
				b.setPassword(pass_word);
   
			
				b=a.dologincheck(b);
				if(b.isValid()){
					HttpSession session = request.getSession(true);
					session.setAttribute("currentUserSession",b);
					request.getRequestDispatcher("Success.jsp").forward(request, response);
				}
				else{
					String status = "Invalid Login Credentials";
					request.setAttribute("Invalid Login Credentials", status);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
