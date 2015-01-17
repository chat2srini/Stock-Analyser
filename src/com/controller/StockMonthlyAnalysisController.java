package com.controller;
import com.model. *;
import com.model.StockMonthlyAnalysisModel.KeyValue;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StockMonthlyAnalysisController
 */
public class StockMonthlyAnalysisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String Company = request.getParameter("D1");
		//char comp = Company.charAt(Company.indexOf("-"));
		String [] comp = Company.split("-");
		String compe = comp[0];
		String Month = request.getParameter("D2");
		String [] months = Month.split("-");
		String monthname = months[0];
		String year = request.getParameter("D3");
		StockMonthlyAnalysisModel cm = new StockMonthlyAnalysisModel();
		try {
			cm.getDataset(compe, monthname,year);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List <KeyValue> lineChartData = new ArrayList <KeyValue>();
		String [] maximumprice = cm.maxprice(monthname, compe);
		String [] openprice = cm.openprice(compe, monthname);
		lineChartData = cm.getList();
		request.setAttribute("LineChartDataSet", lineChartData);
		request.setAttribute("maximumprice", maximumprice);
		request.setAttribute("openprice", openprice);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/StockMonthlyAnalysisOutput.jsp");
		requestDispatcher.forward(request, response);
		
		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		//for(KeyValue i : lineChartData){
			//out.println(i);
			
		//}
		//out.println(compe);
		//out.println(monthname);
		
		
	}

}
