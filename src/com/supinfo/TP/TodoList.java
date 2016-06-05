package com.supinfo.TP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TodoList {
	public String Messagee;
	public String Date;
	public int UserId;
	public TodoList(int id,String msg,String date )
	{
		this.UserId=id;
		this.Messagee=msg;
		this.Date=date;
	}
	public void Add_Todo() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		String url ="jdbc:mysql://127.0.0.1:3306/suptodo";
		
		Connection con=DriverManager.getConnection(url,"root","");
		
		PreparedStatement pstmt = con.prepareStatement(
				"INSERT INTO todo(MESSAGE,UserID,CHECKED,AddDate) values(?,?,?,?)");
		pstmt.setString(1, this.Messagee);
		pstmt.setInt(2, this.UserId);
		pstmt.setString(3, "false");
		pstmt.setString(4, this.Date);
		pstmt.executeUpdate();
	}

}
