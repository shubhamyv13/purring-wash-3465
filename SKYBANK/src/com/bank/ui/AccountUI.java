package com.bank.ui;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.InputMismatchException;
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
	private static final String accountTypeSaving = "Saving";
	private static final String accountTypeFD = "Fixed_Deposit";
	private static final String accountTypeRD = "Recurring_Deposit";
	
	
	public AccountUI(Scanner scanner) {
		this.accountDAO = new AccountDAOImpl();
		this.scanner = scanner;
	}
	
	public void addAccountSaving() throws InputMismatchException{
		//code to take input of the new user
		try {
		System.out.print("Enter an amount (enter 0 for zero balance account): ");
		String balance = scanner.next();
		scanner.nextLine();
		System.out.print("Enter Nominee name: ");
		String nomineeName = scanner.nextLine();
		System.out.print("Enter Nominee relation: ");
		String nomineeRelation = scanner.nextLine();
		System.out.println("Enter your customer Number: ");
		String customerNumber = scanner.next();
		String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String accountNumber = generateUUIDNo.substring( generateUUIDNo.length() - 10);
        
        //create object for user with all details
        Account account = new AccountImpl(balance, accountNumber,accountTypeSaving,0,nomineeName,nomineeRelation);
		
        
        	accountDAO.addAccount(account,customerNumber);
        	System.out.println("Account created successfully. \nYou can now login with your username and password to use our banking services.");
        }catch(SomethingWentWrongException | InputMismatchException | NoRecordFoundException ex) {
        	System.err.println(ex);
        }
			
	}
	
	public void accountSummary() {
		try {
			List<Account> listAccountData = accountDAO.accountSummary();
			listAccountData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void eStatement() throws InputMismatchException{
		//code to take input product details
		try {
		System.out.print("Enter account number: ");
		String accountNumber = scanner.next();
		
			int check = accountDAO.eStatementSubscription(accountNumber);
			
			if(check == 1) System.out.println("Successfully subscribed for e-statement.");
			else System.out.println("You are already subscribed for e-Statement");
		}catch(SomethingWentWrongException | NoRecordFoundException |  InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void closeAccount() throws InputMismatchException{
		//code to take input of the new user
		try {
		System.out.print("Enter the account number you want to close: ");
		String accountNumber = scanner.next();
			
	    
	       int result = accountDAO.closeAccount(accountNumber);
	       if(result == 0) System.out.println("No such account exists.");
	       else System.out.println("Account closed successfully. You will receive a check for the remaining funds.");
	        	
	    }catch(SomethingWentWrongException | NoRecordFoundException |InputMismatchException ex) {
	        	System.err.println(ex);
	    }
	}
	
	public void addAccountFD() throws InputMismatchException{
		//code to take input of the new user
		try {
		System.out.print("Enter an amount : ");
		String balance = scanner.next();
		scanner.nextLine();
		System.out.print("Enter Nominee name: ");
		String nomineeName = scanner.nextLine();
		
		System.out.print("Enter Nominee relation: ");
		String nomineeRelation = scanner.nextLine();
	    int duration = 1;
		String generateUUIDNo = String.format("%110d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String accountNumber = generateUUIDNo.substring( generateUUIDNo.length() - 10);
        
        //create object for user with all details
        Account account = new AccountImpl(balance, accountNumber,accountTypeFD,duration,nomineeName,nomineeRelation);
		
        
        	accountDAO.addAccountFD(account);
        	System.out.println("FD created successfully for one year duration.");
        }catch(SomethingWentWrongException | InputMismatchException ex) {
        	System.err.println(ex);
        }	
	}
	
	public void viewAccountFDRD() {
		try {
			List<Account> listAccountFDData = accountDAO.viewAccountFDRDData();
			listAccountFDData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAccountFD() {
		try {
			List<Account> listAccountFDData = accountDAO.viewAccountFDData();
			listAccountFDData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAccountFDAccount() throws InputMismatchException{
		try {
		System.out.print("Enter the FD account number: ");
		String accountNumber = scanner.next();
		
		  Account accountFDData = accountDAO.viewAccountFDDataParticular(accountNumber);
		  System.out.println(accountFDData);
		}catch(SomethingWentWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAccountRD() {
		try {
			List<Account> listAccountFDData = accountDAO.viewAccountRDData();
			listAccountFDData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAccountRDAccount() throws InputMismatchException {
		try {
		System.out.print("Enter the RD account number: ");
		String accountNumber = scanner.next();
		  Account accountRDData = accountDAO.viewAccountRDDataParticular(accountNumber);
		  System.out.println(accountRDData);
		}catch(SomethingWentWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void partialCloseFD() throws InputMismatchException{
		//code to take input of the new user
		try {
		System.out.print("Enter the FD account number you want to close: ");
		String fdAccountNumber = scanner.next();
		System.out.println("Enter the saving account number to get the FD account balance: ");
		String savingAccountNumber = scanner.next();
	
	       int result = accountDAO.partialCloseFD(fdAccountNumber,savingAccountNumber);
	       if(result == 0) System.out.println("No such account exists.");
	       else System.out.println("FD account closed successfully.\nMoney from the FD account is succefully transfered into given account.");
	        	
	    }catch(SomethingWentWrongException | NoRecordFoundException | InputMismatchException ex) {
	        	System.err.println(ex);
	    }
	}
	
	public void partialCloseRD() throws InputMismatchException {
		//code to take input of the new user
		try {
		System.out.print("Enter the RD account number you want to close: ");
		String rdAccountNumber = scanner.next();
		System.out.println("Enter the saving account number to get the RD account balance: ");
		String savingAccountNumber = scanner.next();
			
	       int result = accountDAO.partialCloseRD(rdAccountNumber,savingAccountNumber);
	       if(result == 0) System.out.println("No such account exists.");
	       else System.out.println("RD account closed successfully.\nMoney from the RD account is succefully transfered into given account.");
	        	
	    }catch(SomethingWentWrongException | NoRecordFoundException | InputMismatchException ex) {
	        	System.err.println(ex);
	    }
	}
	
	public void addAccountRD() throws InputMismatchException {
		//code to take input of the new user
		try {
			
		System.out.print("Enter the goal amount (Enter an amount multiple of 1000) : ");
		String balanceYearly = scanner.next();
		String balanceMonthly = "" + Integer.parseInt(balanceYearly)/12;
		scanner.nextLine();
		System.out.print("Enter Nominee name: ");
		String nomineeName = scanner.nextLine();
		System.out.print("Enter Nominee relation: ");
		String nomineeRelation = scanner.nextLine();
	    int duration = 1;
		String generateUUIDNo = String.format("%110d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String accountNumber = generateUUIDNo.substring( generateUUIDNo.length() - 10);
        
        //create object for user with all details
        Account account = new AccountImpl(balanceMonthly, accountNumber,accountTypeRD,duration,nomineeName,nomineeRelation);
		
        	accountDAO.addAccountFD(account);
        	System.out.println("RD created successfully for one year duration.\nCredit money "+balanceMonthly+ " to this RD account every month.");
        }catch(SomethingWentWrongException | InputMismatchException  ex) {
        	System.err.println(ex);
        }	
	}
	
	public void enableUPI() throws InputMismatchException{
		//code to take input product details
		try {
		System.out.print("Enter account number: ");
		String accountNumber = scanner.next();
	
			int check = accountDAO.enableUPI(accountNumber);
			
			if(check == 0) System.out.println("UPI is already enabled.");
			else System.out.println("UPI has been successfully enabled.");
		}catch(SomethingWentWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	public void disableUPI() throws InputMismatchException{
		//code to take input product details
		try {
		System.out.print("Enter account number: ");
		String accountNumber = scanner.next();
		
			int check = accountDAO.disableUPI(accountNumber);
			
			if(check == 0) System.out.println("UPI is already disabled.");
			else System.out.println("UPI has been successfully disabled.");
		}catch(SomethingWentWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
	}
	
	//Accountant methods start here
	
	public void viewAllAccounts() {
		try {
			List<Account> listAccountData = accountDAO.viewAllAccounts();
			listAccountData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAllClosedAccounts() {
		try {
			List<Account> listAccountData = accountDAO.viewAllClosedAccounts();
			listAccountData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewAllInoperativeAccounts() {
		try {
			List<Account> listAccountData = accountDAO.viewAllInoperativeAccounts();
			listAccountData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
	public void viewParticularAccount() throws InputMismatchException{
		try {
		System.out.print("Enter the account number: ");
		String accountNumber  = scanner.next();
			List<Account> listAccountData = accountDAO.viewParticularAccount(accountNumber);
			listAccountData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException | InputMismatchException ex) {
			System.out.println(ex);
		}
   }
	
	public void makeInoperative() {
		try {
			List<Account> listAccountData = accountDAO.viewAllInoperativeAccounts();
			int count = 0;
			for(int i=0; i<listAccountData.size(); i++) {
				System.out.println(listAccountData.get(i));
				accountDAO.makeAccountInoperative(listAccountData.get(i).getAccountNumber());
				count++;
			}
			if(count != 0) System.out.println(count + " accounts have been made inoperative.");
			else System.out.println("No inoperative account found.");
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
	
}
