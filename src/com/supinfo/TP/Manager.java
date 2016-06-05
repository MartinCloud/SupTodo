package com.supinfo.TP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager extends User {
	public String Name;
	public String Password;
	public TodoList todo;
	
	public Manager(String name,String password)
	{
		this.Name=name;
		this.Password=password;
	}
	
	public boolean login() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		String url ="jdbc:mysql://127.0.0.1:3306/suptodo";
		
		Connection con=DriverManager.getConnection(url,"root","");
		
	    PreparedStatement pstmt = con.prepareStatement(
			"SELECT Id FROM manager WHERE Name = ? AND Password = ?");
		pstmt.setString(1, this.Name);
		pstmt.setString(2, this.Password);

        ResultSet id=pstmt.executeQuery();
		if(id.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void Add_Todo(String todo,String date)
	{	
		this.todo= new TodoList(111111,todo,date);
		try {
			this.todo.Add_Todo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
