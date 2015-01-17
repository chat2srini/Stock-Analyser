package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.StockMonthlyAnalysisModel.KeyValue;

public class StockMonthlyAnalysisModel {
	
List <KeyValue> lineChartData = new ArrayList <KeyValue>();
String [] maximumprice = new String[2];
String [] openprice = new String [2];
	
 	
	public void getDataset(String Company, String Month, String year )throws ClassNotFoundException{
		int monthno = getmonthno(Month);
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("Select Date, diefference from "+Company+ " where month(Date)="+monthno+" and year(Date)="+year );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						lineChartData.add(new KeyValue(rs.getString("Date"),rs.getString("diefference")));
					}
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	public  List <KeyValue> getList(){
		return lineChartData;
	}
	public String [] maxprice(String Month, String Company){
		int monthno = getmonthno(Month);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("Select date, Close from "+Company+ " where close = (select max(close) from " + Company+" where month(date) ="+monthno+ ")" );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						System.out.println(rs.getString("Date"));
						System.out.println(rs.getString("Close"));
						maximumprice[0] = rs.getString("Date");
						maximumprice[1] = rs.getString("Close");
					}
					
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return maximumprice;
		
	}
	public String[]openprice(String Company, String Month){
int monthno = getmonthno(Month);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("Select date, Open from "+Company+ " where month(date) ="+monthno+" limit 1" );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						openprice[0] = rs.getString("Date");
						openprice[1] = rs.getString("Open");
					}
					
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return openprice;
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
