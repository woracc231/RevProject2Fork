package com.pancakecontrol;

import java.util.Scanner;

import com.pacakecontrol.util.ConnectionFactory;

public class MainDriver {
	
public static void main(String[] args) {
		
		System.out.println("\033[38;5;15;48;5;88m"); //this changes the colors in the console
		 
		boolean x = true;
		
		String choice;
		do { //loop menu
			Scanner input = new Scanner(System.in);
			
		//login menu
		System.out.println("Banking Application\n\tMenu       ");
		System.out.println("-------------------");
		System.out.println("Please Choose from \n   the following   \n      options      ");
		System.out.println("-------------------");
		System.out.println("<1>Customer Login  \n<2>User Login      \n<3>Employee Login  \n[S]end money with Zelle");
		
		choice = input.next();
		ConnectionFactory user = new ConnectionFactory();
		
		
		switch(choice) {
		case "1":
			user.connectUser();
			x = false;
			break;
		case "2":
			user.userUser();
			x = false;
			break;
		case "3":
			user.emplUser();
			x = false;
			break;
		case "S":
			 user.zelleUser();
			 break;
		default:
			System.out.println("Please choose 1, 2, 3, or S\n");
			break;
		}
	
		} while (x);
		
		
		
		
		
		
		
		
	}

}
