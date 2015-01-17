package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.StockComparatorModel;
import com.model.StockComparatorModel.KeyValue;

/**
 * Servlet implementation class StockComparatorController
 */
public class StockComparatorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List <KeyValue> lineChartData1 = new ArrayList <KeyValue>();
	List <KeyValue> lineChartData2 = new ArrayList <KeyValue>();

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
		String Company1 = request.getParameter("D1");
		String Company2 = request.getParameter("D2");
		String Month = request.getParameter("D3");
		String Year = request.getParameter("D4");
		StockComparatorModel cm = new StockComparatorModel();
		try {
			cm.getDataset1(Company1, Month, Year);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cm.getDataset2(Company2, Month, Year);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lineChartData1 = cm.getList1();
		lineChartData2 = cm.getList2();
		request.setAttribute("LineChartDataSet1", lineChartData1);
		request.setAttribute("LineChartDataSet2", lineChartData2);
		System.out.println(Company1);
		System.out.println(Company2);
		request.setAttribute("Company1", Company1);
		request.setAttribute("Company2", Company2);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/StockComparatorOutput.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
