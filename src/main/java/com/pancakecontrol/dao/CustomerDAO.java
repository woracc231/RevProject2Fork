package com.pancakecontrol.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;



public abstract class CustomerDAO {
	
	
	
	public static void getAcct(String username, Connection con) {
		
		try {
			String SQLquery = "SELECT * FROM users WHERE usercolumn = "+"'"+username+"'";
			Statement stmt = con.createStatement();
			ResultSet acct = stmt.executeQuery(SQLquery);
			
			
			System.out.println("\n------------------------");
			System.out.print(" Here is your account   \n  information, "+username+".   \n");
			System.out.println("------------------------");
			while (acct.next())
			{
				String user = acct.getString("usercolumn");
				int acctnum = acct.getInt("accountnum");
				float balance = acct.getFloat("balance");
				DecimalFormat df = new DecimalFormat("#.00");
				
				
				System.out.println("USER:"+" "+user+"\t "+"ACCOUNTNUMBER: "+acctnum+"\t "+"   BALANCE: "+df.format(balance));
			}
			
			menuOptions(username, con);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		
}



	





	private static void deposit(String username, Connection con, double deposit) {
			
		try {
			String SQLquery = "SELECT balance FROM users WHERE usercolumn = "+"'"+username+"'";
			Statement stmt = con.createStatement();
			ResultSet acct = stmt.executeQuery(SQLquery);
			
			
			double balance;
			DecimalFormat df = new DecimalFormat("#.00");		
					while (acct.next())
					{
					balance = acct.getDouble("balance");
					balance += deposit;
					Statement stmt2 = con.createStatement();
					stmt2.execute("UPDATE users SET balance = "+balance+" WHERE usercolumn = "+"'"+username+"'");
					Statement stmt3 = con.createStatement();
					stmt3.execute("UPDATE transact SET balance = "+balance+" WHERE auser = "+"'"+username+"'"); //updates employee transact table
					System.out.println("Your new balance is: "+df.format(balance));
					} 
							
					} catch (SQLException e) {
						e.printStackTrace();
					}
}
	
	
	
	private static void view(String username, Connection con) {
		String SQLquery = "SELECT balance FROM users WHERE usercolumn = "+"'"+username+"'";
		try {
		Statement stmt = con.createStatement();
		ResultSet acct = stmt.executeQuery(SQLquery);
		String balance;
			
			while (acct.next()) {
				balance = acct.getString("balance");
				System.out.println("Your current balance is: "+balance);
				}
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
		
}
	
	
	
	private static void withdraw(String username, Connection con, double withdraw) {
		
		try {
			String SQLquery = "SELECT balance FROM users WHERE usercolumn = "+"'"+username+"'";
			Statement stmt = con.createStatement();
			ResultSet acct = stmt.executeQuery(SQLquery);
			
			
			double balance;
			DecimalFormat df = new DecimalFormat("#.00");		
					while (acct.next())
					{
					balance = acct.getDouble("balance");
					balance -= withdraw;
					Statement stmt2 = con.createStatement();
					stmt2.execute("UPDATE users SET balance = "+balance+" WHERE usercolumn = "+"'"+username+"'");
					Statement stmt3 = con.createStatement();
					stmt3.execute("UPDATE transact SET balance = "+balance+" WHERE auser = "+"'"+username+"'");//updates transact table
					System.out.println("Your new balance is: "+df.format(balance));
					} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
	
	
	
	public static void transfer(String username, Connection con, double amt, int acctNum) {
		boolean x = true; //if the account balance is positive after the withdrawal, the transfer will continue
		//First, withdraw from this account
		try {
			String SQLquery = "SELECT balance FROM users WHERE usercolumn = "+"'"+username+"'";
			Statement stmt = con.createStatement();
			ResultSet acct = stmt.executeQuery(SQLquery);
			
			
			double balance;
			DecimalFormat df = new DecimalFormat("#.00");		
					while (acct.next())
					{
					balance = acct.getDouble("balance");
					if (!(balance - amt < 0))            //check for negative account balance
					   {
						balance -= amt;
					   } else { 
						   x = false;
						   System.out.println("Insufficient funds");
						   menuOptions(username, con);
						   }
					if (x == true) {
					Statement stmt2 = con.createStatement();
					stmt2.execute("UPDATE users SET balance = "+balance+" WHERE usercolumn = "+"'"+username+"'");
					Statement stmt5 = con.createStatement();
					stmt5.execute("UPDATE transact SET balance = "+balance+" WHERE accountnum = "+"'"+acctNum+"'");
					System.out.println("Your new balance is: "+df.format(balance));
					} }
					} catch (SQLException e) {
						e.printStackTrace();
					}
		
		if (x == true) {
		//Second, deposit in another account
		try {
			String SQLquery = "SELECT balance FROM users WHERE accountnum = "+"'"+acctNum+"'";
			Statement stmt2 = con.createStatement();
			ResultSet acct2 = stmt2.executeQuery(SQLquery);
			
			
			double balance;
			DecimalFormat df = new DecimalFormat("#.00");		
					while (acct2.next())
					{
					balance = acct2.getDouble("balance");
					balance += amt;
					Statement stmt3 = con.createStatement();
					stmt3.execute("UPDATE users SET balance = "+balance+" WHERE accountnum = "+"'"+acctNum+"'");
					Statement stmt4 = con.createStatement();
					stmt4.execute("UPDATE transact SET balance = "+balance+" WHERE accountnum = "+"'"+acctNum+"'");
					System.out.println("The transfer was successful");
					System.out.println("***Revealed for testing purposes***\n The balance in the target acount is: "+df.format(balance));
					} 
					
					
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
	
	
	
	public static void menuOptions(String username, Connection con) {
		Scanner input = new Scanner(System.in);
		System.out.println("\nHere are your options:\n----------------------");
		System.out.println("[M]ake a deposit\t[W]ithdraw\t[T]ransfer money\t[V]iew balance\n[N]ew account \t     [Q]uit");
		
		String choice = input.next();
		switch(choice)
		{
		
		case "M":
			Scanner in = new Scanner(System.in);
			System.out.println("How much would you like to deposit? ");
			double amt = Double.parseDouble(in.next());
			 deposit(username, con, amt);
			 System.out.println("Thank you!");
			 menuOptions(username, con);
			 break;
			 
		case "V":
			System.out.println("Getting your account information...");
			view(username, con);
			menuOptions(username, con);
			break;
			
		case "W":
			Scanner in2 = new Scanner(System.in);
			System.out.println("How much would you like to withdraw? ");
			double amt2 = Double.parseDouble(in2.next());
				withdraw(username, con, amt2);
			System.out.println("Thank you.");
			menuOptions(username, con);
			break;
			
		case "T":
			Scanner in3 = new Scanner(System.in);
			System.out.println("How much would you like to transfer? ");
			double amt3 = Double.parseDouble(in3.next());
			System.out.println("Send to which account number? ");
			int acctNum = Integer.parseInt(in3.next());
			transfer(username, con, amt3, acctNum);
			menuOptions(username, con);
			break;
		
		case "Q":
			System.out.println("Thank you and have a nice day!");
			break;
			
		case "N":
			try {
				UserDAO.getAcct(username, con);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
			
		}
		
	}
}