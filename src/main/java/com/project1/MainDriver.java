package com.project1;

import com.project1.controller.RequestMapping;
import com.project1.util.ConnectionFactory;

import io.javalin.Javalin;


public class MainDriver {
	
	static Javalin app = Javalin.create().start(7070);
	

	public static void main(String...args) {
	
	 ConnectionFactory conn = new ConnectionFactory();
	 RequestMapping.configureRoutes(app);
	 
	 //testing connection
	 conn.connectUser();
	}
	

}
