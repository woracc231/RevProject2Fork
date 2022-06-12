package com.pancakecontrol.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ZelleDAO{
	//This getAcct function gets a bit crazy. it notifies you of a pending transfer when you log in by checking a table 
	// called tuser for a positive balance. that table is set up just to hold pending transfers and it's zeroed out after
	//the transfer is complete. I probably would have done a lot of this in SQL with functions and triggers, but I had
	//so many weird problems with dbeaver that I decided to do more of the work in java
	
	static CustomerDAO cust = new CustomerDAO();
	public void getAcct(String username, Connection con) {
		
	try {
			Scanner input = new Scanner(System.in);
			DecimalFormat df = new DecimalFormat("#.00");
			float balance2;
			String SQLquery2 = "SELECT balance FROM tusers WHERE usercolumn = "+"'"+username+"'";
			Statement stmt2 = con.createStatement();
			ResultSet acct2 = stmt2.executeQuery(SQLquery2);
				while(acct2.next()) {
						balance2 = acct2.getFloat("balance");
					if (balance2 > 0) {
						System.out.println("\nYou have a pending transaction of "+df.format(balance2)+"\nPress Y to accept, or N to decline");
						String choice = input.next();
							switch(choice) {
							case "Y":
								String SQLQuery3 = "SELECT balance FROM users WHERE usercolumn = "+"'"+username+"'";
								Statement stmt3 = con.createStatement();
								ResultSet acct3 = stmt3.executeQuery(SQLQuery3);
							
								while(acct3.next()) 
								{
								    float balance4;
									float balance3 = acct3.getFloat("balance");
									balance4 = balance2 + balance3;
									Statement stmt4 = con.createStatement();
									stmt4.execute("UPDATE users SET balance = "+balance4+" WHERE usercolumn = "+"'"+username+"'");
									System.out.println("Your updated balance is: "+balance4+"\n");
									Statement stmt5 = con.createStatement();
									stmt5.execute("UPDATE tusers SET balance = 0 WHERE usercolumn ="+"'"+username+"'");
								}
							
								
								break;
							case "N":
								System.out.println("Thank you!");
								cust.menuOptions(username, con);
								
								break;
							
							default:
								System.out.println("Please choose Y or N");
								break;
							}
							}
					}
	} catch (SQLException e) {				 
		e.printStackTrace();
	}
	
	Scanner input2 = new Scanner(System.in);
	System.out.println("\nWelcome to the Zelle menu");
	System.out.println("You don't have any pending transactions. Would you like to make a transfer? Y/N");
	String choice2 = input2.next();
	//The next chunk of this was a copy/paste from my CustomerImpl.transfer function with some modifications to update the tuser table.
	boolean x = true; //if the account balance is positive after the withdrawal, the transfer will continue
	//First, withdraw from this account
	switch (choice2) {
	case "N":
		cust.menuOptions(username, con);
		break;
	default:
		cust.menuOptions(username, con);
		break;
	case "Y":
	Scanner input3 = new Scanner(System.in);
	System.out.println("How much would you like to transfer? ");
	double amt = Double.parseDouble(input3.next());
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
					   cust.menuOptions(username, con);
					   }
				if (x == true) {
				Statement stmt2 = con.createStatement();
				stmt2.execute("UPDATE users SET balance = "+balance+" WHERE usercolumn = "+"'"+username+"'");
				System.out.println("Great! Your new balance is: "+df.format(balance));
				} }
				} catch (SQLException e) {
					e.printStackTrace();
				}
	
	if (x == true) {
	//Second, deposit in another account
	try {
		Scanner input4 = new Scanner(System.in);
		System.out.println("Enter the account number of the recipient: ");
		int acctNum = Integer.parseInt(input4.next());
		
		String SQLquery = "SELECT balance FROM tusers WHERE accountnum = "+"'"+acctNum+"'";
		Statement stmt2 = con.createStatement();
		ResultSet acct2 = stmt2.executeQuery(SQLquery);
		
		
		double balance;
		DecimalFormat df = new DecimalFormat("#.00");		
				while (acct2.next())
				{
				balance = acct2.getDouble("balance");
				balance += amt;
				Statement stmt3 = con.createStatement();
				stmt3.execute("UPDATE tusers SET balance = "+balance+" WHERE accountnum = "+"'"+acctNum+"'");				
				System.out.println("The transfer is now pending the approval of the recipient");
				System.out.println("***Revealed for testing purposes***\nThe balance in the target (pending) acount is: "+df.format(balance));
				} 
				
					
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}
	}	
}