package com.project1.controller;
import com.project1.dao.AuthenticationImpl;
import com.project1.dao.RequestServiceImpl;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

public class RequestMapping {

	private static AuthenticationImpl authDao = new AuthenticationImpl(); 
	
	public static void configureRoutes(Javalin app) {
		
			app.post("/login", ctx -> {	//still need to put all this in the right layer
			
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
		
			app.before("/finance", ctx -> { 
				
				if(!authDao.check()) {
				ctx.redirect("http://localhost:7070/");
				}
				
				});
			
			app.get("/finance", ctx -> {
				System.out.println("working"); //testing
			
				});
			
			app.get("/test", ctx -> {
				
				RequestServiceImpl req = new RequestServiceImpl();
					req.getRequests(ctx);
				
			});
	}
	
}
