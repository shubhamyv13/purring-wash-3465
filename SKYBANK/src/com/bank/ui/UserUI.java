package com.bank.ui;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.bank.dao.UserDAO;
import com.bank.dao.UserDAOImpl;
import com.bank.dto.User;
import com.bank.dto.UserImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public class UserUI {

	private UserDAO userDAO;
	private Scanner scanner;
	
	public UserUI(Scanner scanner) {
		this.userDAO = new UserDAOImpl();
		this.scanner = scanner;
	}
	
	public int addUser() {
		//code to take input of the new user
		System.out.print("Enter first name: ");
		String fname = scanner.next();
		System.out.print("Enter last name: ");
		String lname = scanner.next();
		System.out.print("Enter mobile number: ");
		String mobile = scanner.next();
		System.out.print("Enter email: ");
		String email = scanner.next();
		System.out.print("Enter DOB in yyyy-mm-dd format");
		String dob = scanner.next();
		System.out.print("Enter address: ");
		String address = scanner.next();
		System.out.print("In what city were you born?: ");
		String city = scanner.next();
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.println("Enter password: ");
		String password = scanner.next();
		LocalDate regDate = LocalDate.now();
		String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String customerId = generateUUIDNo.substring( generateUUIDNo.length() - 10);
        
        //create object for user with all details
        
        User user = new UserImpl(fname,lname, mobile, email,dob, address, city,regDate, username, password,customerId);
		
        try {
        	userDAO.addUser(user);
        	System.out.println("User registered successfully.");
        	return 0;
        	
        }catch(SomethingWentWrongException ex) {
        	System.err.println(ex);
        	return 1;
        }
	}
	
	public boolean login() {
		boolean loginSuccessful = false;
		//code to take input username and password 
		System.out.print("Enter username: ");
		String username = scanner.next();
		System.out.print("Enter password: ");
		String password = scanner.next();
		
		try {
			userDAO.login(username, password);
			loginSuccessful = true;
		}catch(SomethingWentWrongException |  NoRecordFoundException ex) {
			System.out.println(ex);
		}
		return loginSuccessful;		
	}
	
	public void viewProfile() {
		try {
			List<User> listUserData = userDAO.viewProfile();
			listUserData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void updateProfile() {
	   //code to take input of the new user
		  System.out.print("Enter first name: ");
		  String fname = scanner.next();
		  System.out.print("Enter last name: ");
		  String lname = scanner.next();
		  System.out.print("Enter mobile number: ");
		  String mobile = scanner.next();
		  System.out.print("Enter email: ");
		  String email = scanner.next();
		  System.out.print("Enter DOB in yyyy-mm-dd format");
		  String dob = scanner.next();
		  System.out.print("Enter address: ");
		  String address = scanner.next();
		  System.out.print("In what city were you born?: ");
		  String city = scanner.next();
		  		        
		  //create object for user with all details
		        
		  User user = new UserImpl(fname,lname, mobile, email,dob, address, city,null, null, null,null);
				
		  try {
		     	userDAO.updateUser(user);
		        System.out.println("User details updated successfully.");
		  }catch(SomethingWentWrongException ex) {
		        System.err.println(ex);
	
		  }
	}
	
	public void changePassword() {
		//code to take input new name
		System.out.print("Enter old password: ");
		String oldPassword = scanner.next();
		
		System.out.print("Enter new password: ");
		String newPassword = scanner.next();
		
		System.out.print("Re-Enter new password: ");
		String newPasswordAgain = scanner.next();
		
		if(newPassword.equals(newPasswordAgain)) {
			try {
				userDAO.changePassword(oldPassword, newPassword);
				System.out.println("Password updated successfully.");
			}catch(SomethingWentWrongException | NoRecordFoundException ex) {
				System.err.println(ex);
			}
		}else {
			System.out.println("New password does not match with re-entered password");
		}
	}
	
	public void deleteUser() {
	    try {
	       userDAO.deleteUser();
	       System.out.println("You account has been deleted successfully.\nYou are Logged out");
	        	
	    }catch(SomethingWentWrongException | NoRecordFoundException ex) {
	        	System.err.println(ex);
	    }
	}
	
	public void logout() {
		userDAO.logout();
	}
	
}
