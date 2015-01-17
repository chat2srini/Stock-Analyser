package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	public Statement DbConnection1() {
		Statement ps = null;
		try {
            Connection con = null;          
			
            Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url="jdbc:mysql://127.0.0.1:3306/stockanalyzer";
			String user="root";
			String pass="";
			con = DriverManager.getConnection(url,user,pass);
			ps = con.createStatement();			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;	
	}
	
		
}