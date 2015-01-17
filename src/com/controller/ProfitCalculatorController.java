package com.controller;
import com.model.*;
import com.model.ProfitCalculatorModel.KeyValue;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProfitCalculatorController
 */
public class ProfitCalculatorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	float openprice;
	float closeprice;
	List <KeyValue> lineChartData = new ArrayList <KeyValue>();

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
		// TODO Auto-generated method stub
		String Company = request.getParameter("D1");
		String [] comp = Company.split("-");
		String compe = comp[0];
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		int volume = Integer.parseInt(request.getParameter("volume"));
		ProfitCalculatorModel cm = new ProfitCalculatorModel();
		try {
			cm.getDataset(compe, startdate, enddate);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			openprice = cm.getopenprice(startdate, compe);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			closeprice = cm.getcloseprice(enddate, compe);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int investment = (int) (openprice * volume);
		 int returns = (int) (closeprice * volume);
		 int profit = returns - investment;
		lineChartData = cm.getLineChartData();
		request.setAttribute("LineChartDataSet", lineChartData);
		request.setAttribute("closeprice", closeprice);
		request.setAttribute("openprice", openprice);
		request.setAttribute("investment", investment);
		request.setAttribute("returns", returns);
		request.setAttribute("profit", profit);
		request.setAttribute("volume", volume);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ProfitCalculatorOutput.jsp");
		requestDispatcher.forward(request, response);
		
		 
	}

}
