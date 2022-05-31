package com.project1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	final String USERNAME = System.getenv("db_user");
	final String PASSWORD = System.getenv("db_pass");
	final String URL = System.getenv("db_url");
	
	public ConnectionFactory() {
		super();
	}
	
	public void connectUser() {
		
		
			Connection connection;
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	
	
}
