package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.loginbean.*;
import com.db.DbConnection;
public class LoginModel {
public LoginBean dologincheck(LoginBean b){
		
		int count;
		try {
			DbConnection d = new DbConnection();
			Statement ps = null;
			ResultSet rs=null;
			ps=d.DbConnection1();
			
			String user_name=b.getUsername();
			String pass_word=b.getPassword();
			
			String sql = "select username,password,firstname,lastname from login where username = '"+user_name+"'and password = '"+pass_word+"'";
			rs=ps.executeQuery(sql);
			
			count = 0;
			while(rs.next()){
				count = count + 1;
				b.setFirstname(rs.getString("firstname"));
				b.setLastname(rs.getString("lastname"));
			}
			if(count == 1){
		     b.setValid(true);
		     
		     
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
public LoginBean RegisterUser(LoginBean a){
	try {
		DbConnection d = new DbConnection();
		Statement ps = null;
		ps=d.DbConnection1();
		String username = a.getUsername();
		String password = a.getPassword();
		String fname= a.getFirstname();
		String lname=a.getLastname();
		String email=a.getEmail();
		
		String sql="insert into login values('"+username+"','"+password+"','"+fname+"','"+lname+"','"+email+"')";
		ps.executeUpdate(sql);
		
		a.setValid(true);	
		ps.close();
		
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return a;
}
}
