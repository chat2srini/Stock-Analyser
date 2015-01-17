package com.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.ProfitCalculatorModel.KeyValue;


public class ProfitCalculatorModel {
	List <KeyValue> lineChartData = new ArrayList <KeyValue>();
	float openprice;
	float closeprice;
	public void getDataset(String Company, String Startdate, String Enddate )throws ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("select date, Close from "+Company+" where date between '"+Startdate+"' and '"+Enddate+"'" );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						lineChartData.add(new KeyValue(rs.getString("Date"),rs.getString("Close")));
					}
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	public List<KeyValue> getLineChartData(){
		return lineChartData;
	}
	public float getopenprice(String Startdate, String Company) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("select Open from "+Company +" where date= '"+Startdate+"'" );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						openprice = rs.getFloat("Open");
					}
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return openprice;
		
	}
	public float getcloseprice(String Enddate, String Company) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stockanalyzer",
					"root","");
					ResultSet rs = null;
					Statement st = con.createStatement();
					String sql = ("select Close from "+Company +" where date= '"+Enddate+"'" );
					System.out.println(sql);
					rs = st.executeQuery(sql);
					while(rs.next()){
						closeprice = rs.getFloat("Close");
					}
					
						
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return closeprice;
		
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
