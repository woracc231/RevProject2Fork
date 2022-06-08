package com.basicapp;



import io.javalin.Javalin;


public class MainDriver {
	
	public static void main(String...args) {
		
		Javalin app = Javalin.create().start(7070);
		
		app.get("/", ctx-> {
			
		ctx.result("Hello from This.Team!");
		});
		
	}

}
