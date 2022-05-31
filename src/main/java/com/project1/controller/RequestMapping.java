package com.project1.controller;

import io.javalin.Javalin;

public class RequestMapping {


	public static void configureRoutes(Javalin app) {
		
		app.post("/employee/login", ctx -> {
			//testing endpoint
			System.out.println("good");
		});
	}
	
}
