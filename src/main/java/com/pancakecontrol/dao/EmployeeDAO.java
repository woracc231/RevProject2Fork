package com.pancakecontrol.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.pancakecontrol.service.Employee;

public class EmployeeDAO implements Employee {

	public void notify(String name, String acct, Connection con) {
			try {
		String SQLquery = "SELECT sav_accounts FROM pending";
			Statement stmt = con.createStatement();
			ResultSet acct1 = stmt.executeQuery(SQLquery);
				while (acct1.next()) 
						{
					Statement stmt2 = con.createStatement();	
					stmt2.executeUpdate("INSERT INTO pending ("+acct+") VALUES ('"+name+"')");
							System.out.println("\nThank you, "+name+", you will be notified on approval\nLogging out...");
							break;
							
						}
				} catch (SQLException e) {
							e.printStackTrace();
							
						}
			
			
						
				}
	
	
	
	public void employeeMenu(String username, Connection con) {
		
		System.out.println("\nThe Employee Menu");
		System.out.println("-----------------");
		System.out.println(" Here are your   \n    options      ");
		System.out.println("-----------------");
		
		System.out.println("[V]iew applications\n[R]eject applications\n[S]ee transaction log\n[Q]uit");
		
		Scanner input = new Scanner(System.in);
		boolean x = true;
		String choice = input.next();
		try {
		switch(choice) {
		case "V": 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM pending");
			while (rs.next()) {
			String saveuser = rs.getString("sav_accounts");
			String chkuser = rs.getString("chk_accounts");
			String bothuser = rs.getString("both_accounts");
			System.out.println("|Savings|   |Checking|   |Both|");
			System.out.println(saveuser+"           "+chkuser+"        "+bothuser+" ");
			}
			employeeMenu(username, con);
			break;
			
		case "S": //I could improve the functionality here. it just returns a view of the balances of associated accounts
			Statement stmt2 = con.createStatement();
			ResultSet rs1 = stmt2.executeQuery("SELECT * FROM transact");
			while (rs1.next()) {
				String account = rs1.getString("accountnum");
				double balance = rs1.getDouble("balance");
				System.out.println("---------------");
				System.out.println(account+"\t"+balance);
					}
			employeeMenu(username, con);
			break;
		case "R": //rejects accounts by entering the name and the account type - sav_accounts, chk_accounts, both_accounts
			Scanner input3 = new Scanner(System.in);
			System.out.println("Enter the name of the applicant to reject: ");
			String user = input3.next();
			Scanner input2 = new Scanner(System.in);
			System.out.println("Enter the account type: ");
			String acct = input2.next();
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate("UPDATE pending SET "+acct+" = null WHERE "+acct+" = '"+user+"'");
			break;
					
		case "Q":
			System.out.println("Logging off!");
			break;
		
		}
	}catch (SQLException e) {
				e.printStackTrace();
	}
	}
}