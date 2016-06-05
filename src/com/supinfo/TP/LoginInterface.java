package com.supinfo.TP;


import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
public class LoginInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField Password;


	/**
	 * Create the frame.
	 */
	public LoginInterface() {
		setTitle("SUPTODO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{562};
		gbl_contentPane.rowHeights = new int[]{37, 139, 27, 16, 38, 21, 27, 0};
		gbl_contentPane.columnWeights = new double[]{1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel titleLabel = new JLabel("Login to SUPtodo");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.fill = GridBagConstraints.VERTICAL;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		contentPane.add(titleLabel, gbc_titleLabel);
		
		TextField Usename = new TextField();
		Usename.setColumns(20);
		Usename.setFont(new Font("Bell MT", Font.PLAIN, 20));
		Usename.setText("Username ...");
		GridBagConstraints gbc_Usename = new GridBagConstraints();
		gbc_Usename.fill = GridBagConstraints.BOTH;
		gbc_Usename.insets = new Insets(0, 0, 5, 0);
		gbc_Usename.gridx = 0;
		gbc_Usename.gridy = 2;
		contentPane.add(Usename, gbc_Usename);
		
		Password = new JPasswordField();
		Password.setEchoChar('0');
		Password.setColumns(16);
		GridBagConstraints gbc_Password = new GridBagConstraints();
		gbc_Password.fill = GridBagConstraints.BOTH;
		gbc_Password.insets = new Insets(0, 0, 5, 0);
		gbc_Password.gridx = 0;
		gbc_Password.gridy = 4;
		contentPane.add(Password, gbc_Password);
		
		JButton LoginButton = new JButton("login");
		LoginButton.setFont(new Font("Cambria", Font.BOLD, 18));
		GridBagConstraints gbc_LoginButton = new GridBagConstraints();
		gbc_LoginButton.fill = GridBagConstraints.BOTH;
		gbc_LoginButton.gridx = 0;
		gbc_LoginButton.gridy = 6;
		contentPane.add(LoginButton, gbc_LoginButton);
		
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Manager manager =new Manager(Usename.getText(),Password.getText());
				Employee employee = new Employee(Usename.getText(),Password.getText());
				try {
					if(!manager.login()&&!employee.login())
					{
						JOptionPane.showMessageDialog(null,"Username or Password error!");
					}
					else if(manager.login())
					{
						ManagerInterface frame1=new ManagerInterface(manager);
						frame1.setVisible(true);
					}
					else if(employee.login())
					{
						EmployeeInterface frame2=new EmployeeInterface(employee);
						frame2.setVisible(true);
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}
