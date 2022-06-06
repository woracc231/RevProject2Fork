package com.project1.controller;
import com.project1.dao.AuthenticationImpl;
import com.project1.dao.RequestServiceImpl;
import io.javalin.Javalin;

public class RequestMapping {

	private static AuthenticationImpl authDao = new AuthenticationImpl(); 
	
	public static void configureRoutes(Javalin app) {
		
			//login	------------------------------------------------------------------------------------------
			app.post("/login", ctx -> {	//still need to put all this in the right layer
			
			String username = ctx.formParam("username");
			String password = ctx.formParam("password");
				if(authDao.authenticateUser(username, password)) {
					ctx.sessionAttribute("username", username);
					ctx.sessionAttribute("password", password);
					ctx.status(201);
					System.out.println("created"); //testing
					} else {
						ctx.status(403);
						System.out.println("forbidden"); //testing
					}
			
				});
			//-----------------------------------------------------------------------------------------------
			//management endpoints
			app.before("/finance/*", ctx -> {  //redirects the user in case of unauthorized access
				
				if(!authDao.check()) {
				ctx.redirect("http://localhost:7070/");
				}
				
				});
				
			app.get("/finance/viewrequests", ctx -> {  //returns all requests from all employees
				
				RequestServiceImpl req = new RequestServiceImpl();
					req.getAllRequests(ctx);
					ctx.status(200);
				
				});
			
			app.post("/finance/requestapproval", ctx -> { //approve requests here
				String user = ctx.formParam("username");
				RequestServiceImpl req = new RequestServiceImpl();
				req.approveRequest(ctx, user);
				ctx.status(201);
				
			
				});
			
			app.post("/finance/requestdenial", ctx -> { //deny requests here
				String user = ctx.formParam("username");
				RequestServiceImpl req = new RequestServiceImpl();
				req.denyRequest(ctx, user);
				ctx.status(201);
			
				});
			
			
			//employee endpoints
			//-------------------------------------------------------------------------------------------
			app.get("/employee/pending", ctx -> {  //pending requests only
				String user = ctx.formParam("username");
				String check = ctx.cachedSessionAttribute("username").toString(); 
				
				if(user.equalsIgnoreCase(check)) {
		
					 RequestServiceImpl req = new RequestServiceImpl();
					 
					 req.getRequests(ctx, check);
					 ctx.status(200);
				}

			});
			
			app.get("/employee/history", ctx -> { //request history
				String user = ctx.formParam("username");
				String check = ctx.cachedSessionAttribute("username").toString(); 
				
				if(user.equalsIgnoreCase(check)) {
		
					 RequestServiceImpl req = new RequestServiceImpl();
					 
					 req.getRequestHistory(ctx, check);
					 ctx.status(200);
				}
			});
			
			app.post("/employee/requestform", ctx ->{
				
				RequestServiceImpl req = new RequestServiceImpl();
				
				req.postRequest(ctx);
				ctx.status(201);
				
					
			});
			
			
			
			//--------------------------------------------------------------------------------------------
			//endpoint for logging out
			app.post("/logout", ctx -> {
				
				ctx.consumeSessionAttribute("username");
				ctx.status(200);
			});
			
			
	}
	
	
}
