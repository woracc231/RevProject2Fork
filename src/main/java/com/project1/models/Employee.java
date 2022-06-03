package com.project1.models;

import java.io.Serializable;
import java.util.Objects;

//import java.time.LocalTime;

public class Employee {
	
	private String username;
	private String newrequest;
	private boolean approve;
//	LocalTime thisTime;
	public Employee() {
		super();
	}
	
	public Employee(String username, String newrequest, boolean approve) {
		
		this.username = username;
		this.newrequest = newrequest;
		this.approve = approve;
	}
	
//	public void employeeRequest(String username, String request, double amount) {
//		this.username = username;
//		this.request = request;
//		this.amount = amount;
//		this.thisTime = thisTime.now();
//	}

	
	public String toString() {
		return "Employee [username=" + username + ", request=" + newrequest + ", approve=" + approve + "]";
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(amount, request, thisTime, username);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Employee other = (Employee) obj;
//		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
//				&& Objects.equals(request, other.request) && Objects.equals(thisTime, other.thisTime)
//				&& Objects.equals(username, other.username);
//	}


	
	
	
	}
