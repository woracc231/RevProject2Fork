package com.pancakecontrol.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.pancakecontrol.controller.UserMap;
import com.pancakecontrol.dao.CustomerDAO;
import com.pancakecontrol.dao.EmployeeDAO;
import com.pancakecontrol.dao.ZelleDAO;
import com.pancakecontrol.service.Authentication;


public class ConnectionFactory implements Authentication {

	final String URL = System.getenv("db_url");
	final String USERNAME = System.getenv("db_login");
	final String PASSWORD = System.getenv("db_pass");
	
	static CustomerDAO cust = new CustomerDAO();
	static EmployeeDAO emp = new EmployeeDAO();
	static ZelleDAO zell = new ZelleDAO();
	static UserMap usr = new UserMap();
	
	public ConnectionFactory(){
		super();
	}
	
	public Connection connectUser() {
				try {
				Connection connection;
				
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("!----Connected----!\n Please enter your \n      username\t   \n");
				
		
				Statement statement = connection.createStatement();
				Scanner input = new Scanner(System.in);
				String username;
				String password;
				System.out.println("Username: ");
				username = input.next();
				System.out.println("Password: ");
				password = input.next();
				
				
				String UserSQL = "select * from users where usercolumn = " + "'"+username+"'" + " and passwordcol = " + "'"+password+"'";
				ResultSet rs = statement.executeQuery(UserSQL);
				    boolean access = false;
					while(rs.next()) {
						String b = rs.getString("usercolumn");
						String c = rs.getString("passwordcol");
						access = true;
						}
				
					if (access == true) {
			
						cust.getAcct(username, connection);//********************
						
						} else { System.out.println("denied"); }
					
					} catch (SQLException e) {
						e.printStackTrace();
					}
				return null;
				
				}
			
	public Connection zelleUser() {
		try {
		Connection connection;
		
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("!----Connected----!\n Please enter your \n      username\t   \n");
		

		Statement statement = connection.createStatement();
		Scanner input = new Scanner(System.in);
		String username;
		String password;
		System.out.println("Username: ");
		username = input.next();
		System.out.println("Password: ");
		password = input.next();
		
		
		String UserSQL = "select * from users where usercolumn = " + "'"+username+"'" + " and passwordcol = " + "'"+password+"'";
		ResultSet rs = statement.executeQuery(UserSQL);
		    boolean access = false;
			while(rs.next()) {
				String b = rs.getString("usercolumn");
				String c = rs.getString("passwordcol");
				access = true;
				}
		
			if (access == true) {
	
				zell.getAcct(username, connection);//********************
				
				} else { System.out.println("denied"); }
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
		
		}
	
	public Connection userUser() {
		try {
		Connection connection;
		
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("!----Connected----!\n Please enter your \n      username\t   \n");
		

		Statement statement = connection.createStatement();
		Scanner input = new Scanner(System.in);
		String username;
		String password;
		System.out.println("Username: ");
		username = input.next();
		System.out.println("Password: ");
		password = input.next();
		
		
		String UserSQL = "select * from users where usercolumn = " + "'"+username+"'" + " and passwordcol = " + "'"+password+"'";
		ResultSet rs = statement.executeQuery(UserSQL);
		    boolean access = false;
			while(rs.next()) {
				String b = rs.getString("usercolumn");
				String c = rs.getString("passwordcol");
				access = true;
				}
		
			if (access == true) {
	
				usr.getAcct(username, connection);//********************
				
				} else { System.out.println("denied"); }
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public Connection emplUser() {
		try {
		Connection connection;
		
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("!----Connected----!\n Please enter your \n      username\t   \n");
		

		Statement statement = connection.createStatement();
		Scanner input = new Scanner(System.in);
		String username;
		String password;
		System.out.println("Username: ");
		username = input.next();
		System.out.println("Password: ");
		password = input.next();
		
		
		String UserSQL = "select * from users where usercolumn = " + "'"+username+"'" + " and passwordcol = " + "'"+password+"'";
		ResultSet rs = statement.executeQuery(UserSQL);
		    boolean access = false;
			while(rs.next()) {
				String b = rs.getString("usercolumn");
				String c = rs.getString("passwordcol");
				access = true;
				}
		
			if (access == true) {
	
				emp.employeeMenu(username, connection);
				
				} else { System.out.println("denied"); }
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
}