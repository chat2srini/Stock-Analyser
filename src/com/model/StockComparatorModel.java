package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.model.ProfitCalculatorModel.KeyValue;

public class StockComparatorModel {
	List <KeyValue> lineChartData1 = new ArrayList <KeyValue>();
	List <KeyValue> lineChartData2 = new ArrayList <KeyValue>();
	public void getDataset1(String Company, String Month, String Year )throws ClassNotFoundException{
		int monthno = getmonthno(Month);
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("Select date, close from "+Company+ " where month(Date)="+monthno+" and year(date) ="+Year  );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						lineChartData1.add(new KeyValue(rs.getString("Date"),rs.getString("close")));
					}
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	public void getDataset2(String Company, String Month, String Year )throws ClassNotFoundException{
		int monthno = getmonthno(Month);
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("Select date, close from "+Company+ " where month(Date)="+monthno+" and year(date) ="+Year  );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						lineChartData2.add(new KeyValue(rs.getString("Date"),rs.getString("close")));
					}
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	public  List <KeyValue> getList1(){
		return lineChartData1;
	}
	public  List <KeyValue> getList2(){
		return lineChartData2;
	}
	
	private int getmonthno(String Month){
		
		
		switch (Month){
		case "January":
			return 1;
			//break;
		case "Feburary":
			return 2;
			//break;
		case "March":
			return 3;
			//break;
		case "April":
			return 4;
			//break;
		case "May":
			return 5;
			//break;
		case "June":
			return 6;
			//break;
		case "July":
			return 7;
			//break;
		case "August":
			return 8;
			//break;
		case "September":
			return 9;
			//break;
		case "October":
			return 10;
			//break;
		case "November":
			return 11;
			//break;
		case "December":
			return 12;
			//break;
			default:
				return 0;
				//break;
				
		}
		
	
	}

	
	public static class KeyValue{
		String Key, Value;
		public KeyValue(String Key, String Value){
			this.Key =Key;
			this.Value = Value;
						
		}
		public String getKey(){
			return Key;
		}
		public void setKey(String Key){
			this.Key = Key;
		}
		public String getValue(){
			return Value;
		}
		public void setValue(String Value){
			this.Value = Value;
		}
	}
}
