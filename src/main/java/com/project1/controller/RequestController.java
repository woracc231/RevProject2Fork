package com.project1.controller;

import java.sql.SQLException;

import com.project1.dao.AuthenticationDAO;
import com.project1.dao.RequestDAO;
import com.project1.service.RequestService;

import io.javalin.http.Context;

public class RequestController implements RequestService{
	
	public RequestController() {
		super();
	}
	//-----------------------------------login
	public void login(Context ctx) {
		AuthenticationDAO authDao = new AuthenticationDAO();
	
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
			if(authDao.authenticateUser(username, password)) {
				ctx.sessionAttribute("username", username);
				ctx.sessionAttribute("password", password);
				ctx.status(201);
				} else {
					ctx.status(403);
				}
	}
	//-----------------------------------admin
	public void getAllRequests(Context ctx) throws SQLException{
		
		RequestDAO reqDao = new RequestDAO();
		reqDao.getAllRequests(ctx);
		ctx.status(200);
	}
	
	public void requestApproval(Context ctx) throws SQLException {
		
		String user = ctx.formParam("username");
		RequestDAO req = new RequestDAO();
		req.approveRequest(ctx, user);
		ctx.status(201);
	}
	
	public void requestDenial(Context ctx) throws SQLException {
		
		String user = ctx.formParam("username");
		RequestDAO req = new RequestDAO();
		req.denyRequest(ctx, user);
		ctx.status(201);
	}
	
	//-----------------------------------------------employee
	public void getPending(Context ctx) throws SQLException{
		
		String user = ctx.formParam("username");
		String check = ctx.cachedSessionAttribute("username").toString(); 
		
		if(user.equalsIgnoreCase(check)) {

			 RequestDAO req = new RequestDAO();
			 
			 req.getRequests(ctx, check);
			 ctx.status(200);
		}
	}
	
	public void getHistory(Context ctx) throws SQLException{
		
		String user = ctx.formParam("username");
		String check = ctx.cachedSessionAttribute("username").toString(); 
		
		if(user.equalsIgnoreCase(check)) {

			 RequestDAO req = new RequestDAO();
			 
			 req.getRequestHistory(ctx, check);
			 ctx.status(200);
		}
	}
	
	public void postRequest(Context ctx) throws SQLException{
		
		RequestDAO req = new RequestDAO();
		
		req.postRequest(ctx);
		ctx.status(201);
	}
	
}
