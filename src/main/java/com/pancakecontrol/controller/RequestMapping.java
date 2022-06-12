package com.pancakecontrol.controller;
import io.javalin.Javalin;

public class RequestMapping {
	
	public void configureRoutes(Javalin app) {
		
		//the following is just an idea for the endpoints we'll use in the project. let me know what you think 
		
		app.post("/guest", null); //we'll allow account registration here
		app.post("/login", null); //button
		
		app.before("/customer/*", null); //check session attributes - this keeps any future endpoints safe that we might add under /customer
		app.before("/customer/{username}", null); //check session attributes first
		
		app.get("/customer/{username}", null); //we can put all of our options for withdrawals, transfers, new accounts here
		
		app.post("/guest", null); //we'll allow account registration here
		
		app.before("/admin/*", null); //check session attributes - this keeps any future endpoints safe that we might add under /admin
		app.before("/admin/{username}", null);//we check the session attributes first
		
		app.get("/admin/{username}", null); //employee endpoint we'll use for most, if not all, admin options
		
	}
}
