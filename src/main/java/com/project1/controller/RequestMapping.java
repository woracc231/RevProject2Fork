package com.project1.controller;

import com.project1.dao.AuthenticationImpl;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

public class RequestMapping {

	private static AuthenticationImpl authDao = new AuthenticationImpl(); //DAO dependency
	
	public static void configureRoutes(Javalin app) {
		
		app.post("/login", ctx -> {
			
			String username = ctx.formParam("username");
			String password = ctx.formParam("password");
				if(authDao.authenticateUser(username, password)) {
					ctx.sessionAttribute(username, app);
					ctx.status(HttpCode.CREATED);
					System.out.println("created"); //testing
					} else {
						ctx.status(HttpCode.FORBIDDEN);
						System.out.println("forbidden"); //testing
					}
			
		});
	}
	
}
