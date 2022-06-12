package com.pancakecontrol.service;

import java.sql.Connection;

public interface Employee {
	
	public void notify(String name, String acct, Connection con);
	public void employeeMenu(String username, Connection con);

}
