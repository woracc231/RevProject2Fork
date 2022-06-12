package com.pancakecontrol.service;

import java.sql.Connection;

public interface Customer {
	
	public void getAcct(String username, Connection con);
	public void deposit(String username, Connection con, double deposit);
	public void view(String username, Connection con);
	public void withdraw(String username, Connection con, double withdraw);
	public void transfer(String username, Connection con, double amt, int acctNum);
	public void menuOptions(String username, Connection con);

}
