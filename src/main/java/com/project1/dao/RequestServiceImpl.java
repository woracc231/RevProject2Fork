package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project1.models.Employee;
import com.project1.util.ConnectionFactory;

import io.javalin.http.Context;

public class RequestServiceImpl {

	public RequestServiceImpl() {
		super();	
	}
	
	public void getRequests(Context ctx) throws SQLException{
			
		
			String sql = "SELECT * FROM admins";
			Connection connection = ConnectionFactory.connectUser();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<Employee> a = new ArrayList<>();
			while(rs.next()) {
						Employee e = new Employee(rs.getString("username"),
						rs.getString("newrequests"), rs.getBoolean("approve"));
						a.add(e);
						}
			for (Employee x: a)
				ctx.json(a.toString());
			
	}
	
	
}
