package com.bank.ui;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.bank.dao.AccountDAO;
import com.bank.dao.AccountDAOImpl;
import com.bank.dao.UserDAO;
import com.bank.dao.UserDAOImpl;
import com.bank.dto.Account;
import com.bank.dto.AccountImpl;
import com.bank.dto.User;
import com.bank.dto.UserImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public class AccountUI {

	private AccountDAO accountDAO;
	private Scanner scanner;
	private static final String accountType = "Saving";
	
	public AccountUI(Scanner scanner) {
		this.accountDAO = new AccountDAOImpl();
		this.scanner = scanner;
	}
	
	public void addAccountSaving() {
		//code to take input of the new user
		System.out.print("Enter Aadhar number: ");
		String aadhar = scanner.next();
		System.out.print("Enter Pancard number: ");
		String pan = scanner.next();
		System.out.print("Enter an amount (enter 0 for zero balance account): ");
		String balance = scanner.next();
		System.out.print("Enter Customer Id: ");
		String customerId = scanner.next();
		String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String accountNumber = generateUUIDNo.substring( generateUUIDNo.length() - 10);
        
        //create object for user with all details
        System.out.println(aadhar + " " + pan + " " + balance + " " + customerId + " " + accountNumber + " " +accountType);
        
        Account account = new AccountImpl(aadhar, pan, balance, customerId, accountNumber,accountType);
		
        try {
        	accountDAO.addAccount(account);
        	System.out.println("Account created successfully.");
        }catch(SomethingWentWrongException ex) {
        	System.err.println(ex);
        }
			
	}
	
	public void accountSummary() {
		try {
			List<Account> listUserData = accountDAO.accountSummary();
			listUserData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void eStatement() {
		//code to take input product details
		System.out.print("Enter account number: ");
		String accountNumber = scanner.next();
		
		try {
			accountDAO.eStatementSubscription(accountNumber);
			System.out.println("Successfully subscribed for e-statement.");
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
}
