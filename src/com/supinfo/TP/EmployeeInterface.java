package com.supinfo.TP;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;


import java.awt.Checkbox;

import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;

public class EmployeeInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	/*
	 the variable outside to control the circle.
	 */
	int i=1,j=1,k=1;
	public EmployeeInterface(Employee emp) throws SQLException {
		
		
		int[] IdList=new int[20];                      //store the todo ID
		JButton[] allbutton=new JButton[10];           //store all the JButton
		JTextArea[] alltext=new JTextArea[10];           //store all the TextArea
		JTextArea[] allcomment=new JTextArea[10];      //store all the JTextArea
		Checkbox[] allbox=new Checkbox[10];            //store all the CheckBox
		//ArrayList<JButton> allbutton=new ArrayList<JButton>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();                      
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// create a tabbedPane to store the components
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(14, 13, 554, 427);
		contentPane.add(tabbedPane);
		
		//to link to the database to find the result.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		String url ="jdbc:mysql://127.0.0.1:3306/suptodo";
		
		Connection con=DriverManager.getConnection(url,"root","");
		//con.setAutoCommit(false);
		PreparedStatement pstmt = con.prepareStatement(
				"SELECT * FROM todo");
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			//if the todo is not checked. then show it
			if(rs.getString("CHECKED").equals("false"))
			{
				IdList[i]=Integer.parseInt(rs.getString("Id"));
				JPanel panel = new JPanel();
				tabbedPane.addTab("Task"+i, panel);
				panel.setLayout(null);
		
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setBounds(10, 41, 529, 203);
				textArea.setRows(10);
				textArea.setColumns(30);
				textArea.setFont(new Font("Serif",Font.PLAIN,19));
				
				panel.add(textArea);
				textArea.setText(rs.getString("message"));
				alltext[i]=textArea;
			
				Checkbox checkbox = new Checkbox("Mark as done");
				checkbox.setBounds(10, 262, 118, 30);
				panel.add(checkbox);
				allbox[i]=checkbox;
		
				JButton btnNewButton = new JButton("Save");
				btnNewButton.setBounds(10, 355, 529, 27);
				panel.add(btnNewButton);
				allbutton[i]=btnNewButton;
			
				JTextArea txtrWriteAComment = new JTextArea();
				txtrWriteAComment.setFont(new Font("Serif", Font.PLAIN, 19));
				txtrWriteAComment.setText("Write a comment");
				txtrWriteAComment.setBounds(10, 300, 529, 42);
				txtrWriteAComment.setLineWrap(true);
				txtrWriteAComment.setWrapStyleWord(true);
				
				panel.add(txtrWriteAComment);
				allcomment[i]=txtrWriteAComment;
				
				
				i++;
			}
		}
		for(j=1;j<i;j++)
		 {
			 allbutton[j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for(k=1;k<i;k++)
						{
							//to check which button is selected.
							if(e.getSource()==allbutton[k])
							{
								// if the check box is selected and comment is not null .
								if(allbox[k].getState()&&!(allcomment[k].getText().equals("Write a comment")))
								{
									try {
										PreparedStatement update = con.prepareStatement(
										"UPDATE todo SET MESSAGE=?,CHECKED=? WHERE ID=?");
										update.setString(1, alltext[k].getText()+allcomment[k].getText());
										update.setString(2, "true");
										update.setInt(3, IdList[k]);
										update.executeUpdate();
										allcomment[k].setText("Write a comment");
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
								// if the check box is selected and comment is null .
								else if(allbox[k].getState()&&allcomment[k].getText().equals("Write a comment"))
								{
									JOptionPane.showMessageDialog(null, "You must Comment");
								}
								// if the comment is not null .
								else if(!(allcomment[k].getText().equals("Write a comment")))
								{
									SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									try {
										PreparedStatement addComm = con.prepareStatement(
										"UPDATE todo SET MESSAGE=? WHERE ID=?");
										addComm.setString(1, alltext[k].getText()+"\r\n\nComment by "+
										emp.Name +" :"+allcomment[k].getText()+" "+df.format(new Date()).toString());
										addComm.setInt(2, IdList[k]);
										addComm.executeUpdate();
										allcomment[k].setText("Write a comment");
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
						}
						
					}
				});
		 }
		
	}
}
