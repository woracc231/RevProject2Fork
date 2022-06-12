package com.pancakecontrol.service;

import java.sql.Connection;

public interface User {
	
	public void getAcct(String username, Connection con);

}
