package com.supinfo.TP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Employee extends User {
	public String Name;
	public String Password;
	
	public Employee(String name,String password)
	{
		this.Name=name;
		this.Password=password;
	}
	
	public boolean login()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Conn't find the database Drive");
			e.printStackTrace();
		}
		
        String url ="jdbc:mysql://127.0.0.1:3306/suptodo";
		try
		{
			Connection con=DriverManager.getConnection(url,"root","");
			
			PreparedStatement pstmt = con.prepareStatement(
				"SELECT id FROM employee "+ "WHERE Name = ? AND Password = ?");
			pstmt.setString(1, this.Name);
			pstmt.setString(2, this.Password);

			ResultSet id = pstmt.executeQuery();
		
			if(id.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<String> get_Todo() throws SQLException
	{
		ArrayList<String> myMessage=new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		String url ="jdbc:mysql://127.0.0.1:3306/mysql";
		
		Connection con=DriverManager.getConnection(url,"root","");
		con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(
				"SELECT message FROM todo");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			myMessage.add(rs.getString("message"));
		}
		return myMessage;
	}

}
