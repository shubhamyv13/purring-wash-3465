package com.bank.ui;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.bank.dao.AccountDAO;
import com.bank.dao.AccountDAOImpl;
import com.bank.dao.BeneficiaryDAO;
import com.bank.dao.BeneficiaryDAOImpl;
import com.bank.dao.TransactionDAO;
import com.bank.dao.TransactionDAOImpl;
import com.bank.dto.Beneficiary;
import com.bank.dto.Transaction;
import com.bank.dto.TransactionImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

public class TransactionUI {
	
	private TransactionDAO transactionDAO;
	private BeneficiaryDAO beneficiaryDAO;
	private AccountDAO accountDAO;
	private Scanner scanner;
	private static final String transactionTypeDebit = "Debit";
	private static final String transactionTypeCredit = "Credit";
	
	
	public TransactionUI(Scanner scanner) {
		this.transactionDAO = new TransactionDAOImpl();
		this.beneficiaryDAO = new BeneficiaryDAOImpl();
		this.accountDAO = new AccountDAOImpl();
		this.scanner = scanner;
	}
	
	public void quickTransfer() {
		try {
			
			System.out.print("Enter debit account number: ");
			String debitAccountNumber = scanner.next();
			
			int account_id = accountDAO.getAccountId(debitAccountNumber);
			
			if(account_id == 0) {
				throw new NoRecordFoundException("No such account linked to your customer Id. Please, enter a valid account.");
			}
			
			System.out.print("Enter beneficiary account number: ");
			String beneficiaryAccount = scanner.next();
			System.out.print("Re-enter beneficiary account number: ");
			String beneficiaryAccountAgain = scanner.next();
			
			if(beneficiaryAccount.equals(beneficiaryAccountAgain)) {
				
				System.out.print("Enter the beneficiary bank's IFScode: ");
				String bank = scanner.next();
				System.out.print("Enter amount: ");
				int transactionAmount = scanner.nextInt();
				String transactionType = transactionTypeDebit;
				LocalDateTime timeOfTransaction = LocalDateTime.now();
				String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
		        String transactionID = generateUUIDNo.substring( generateUUIDNo.length() - 10);
			        
			        Transaction transaction = new TransactionImpl(transactionAmount,transactionType,timeOfTransaction, "To Transfer "+beneficiaryAccount,debitAccountNumber,"",transactionID);
					
					int result = transactionDAO.debit(transaction);
		
					if(result == -1) System.out.println("Transfer failed. No such saving account exists. You entered one of your FD/RD account.");
					else if(result == -2) System.out.println("Insufficient balance.");
					else System.out.println("Transaction successfull. Amount "+ transactionAmount+ " is transfered to the account number "+beneficiaryAccount);
			}else {
				System.out.println("Beneficiary account number and re-entered beneficiary account number do not match.");
			}
		} catch (SomethingWentWrongException | UserRelatedException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public void transferToAddedBeneficiary() {		
		try {
			
			System.out.print("Enter debit account number: ");
			String debitAccountNumber = scanner.next();
			
			int account_id = accountDAO.getAccountId(debitAccountNumber);
			
			if(account_id == 0) {
				throw new NoRecordFoundException("No such account linked to your customer Id. Please, enter a valid account.");
			}
			
			System.out.print("Enter beneficiary account number: ");
			String beneficiaryAccount = scanner.next();
			System.out.print("Re-enter beneficiary account number: ");
			String beneficiaryAccountAgain = scanner.next();
			System.out.println();
			
			if(beneficiaryAccount.equals(beneficiaryAccountAgain)) {
				int id = beneficiaryDAO.getBeneficiaryId(beneficiaryAccountAgain, debitAccountNumber);
				
				if(id > 0) {
					Beneficiary beneficiary = beneficiaryDAO.getBeneficiary(beneficiaryAccountAgain, debitAccountNumber);
					
					System.out.println("Account found!");
					System.out.println(beneficiary);
					System.out.println();
					
					System.out.print("Enter the amount: ");
					int transactionAmount = scanner.nextInt();
					
					if(transactionAmount > Integer.parseInt(beneficiary.getLimitOfTransaction())) {
						throw new UserRelatedException("Entered amount excedes the transaction limit set for the given account.");
					}
					
					String transactionType = transactionTypeDebit;
					LocalDateTime timeOfTransaction = LocalDateTime.now();
					String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
			        String transactionID = generateUUIDNo.substring( generateUUIDNo.length() - 10);
			        
			        Transaction transaction = new TransactionImpl(transactionAmount,transactionType,timeOfTransaction, "Transfer to "+beneficiaryAccount,debitAccountNumber,"",transactionID);
					
					int result = transactionDAO.debit(transaction);
					
					if(result == -1) System.out.println("Transfer failed. No such saving account exists. You entered one of your FD/RD account.");
					else if(result == -2) System.out.println("Insufficient balance.");
					else System.out.println("Transaction successfull. Amount "+ transactionAmount+ " is transfered to the account number "+beneficiaryAccount);
				}else {
					System.out.println("Transaction failed! No such beneficiary found in your beneficiary list. Add, before transfer.");
				}
				
			}else {
				System.out.println("Beneficiary account number and re-entered beneficiary account number do not match.");
			}
		} catch (SomethingWentWrongException | UserRelatedException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	public void depositMoneyToAccount() {		
		try {
			
			System.out.print("Enter the account number in which you want to deposit: ");
			String accountNumber = scanner.next();
			
			int account_id = accountDAO.getAccountId(accountNumber);
			
			if(account_id == 0) {
				throw new NoRecordFoundException("No such account linked to your customer Id. Please, enter a valid account.");
			}
					
			System.out.print("Enter the amount: ");
			int transactionAmount = scanner.nextInt();
			String transactionType = transactionTypeCredit;
			LocalDateTime timeOfTransaction = LocalDateTime.now();
			String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
	        String transactionID = generateUUIDNo.substring( generateUUIDNo.length() - 10);
	        
	        Transaction transaction = new TransactionImpl(transactionAmount,transactionType,timeOfTransaction, "Cash deposit ",accountNumber,"",transactionID);
			
			int result = transactionDAO.credit(transaction);		
			if(result == -1) System.out.println("Transfer failed. No such saving account exists. You entered one of your FD/RD account.");
			else System.out.println("Transaction successfull. Amount "+ transactionAmount+ " is transfered to the account number "+accountNumber);		
					
		} catch (SomethingWentWrongException | UserRelatedException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
    public void checkTransactionHistory() {
		
		System.out.print("Enter the account number: ");
		String accountNumber = scanner.next();
		
		try {
			List<Transaction> listTransactionData = transactionDAO.checkTransactionHistory(accountNumber);
			listTransactionData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
    
    //Accountant Methods starts here
    
    public void transactionsDayRange() {
		
		System.out.print("Enter start date: ");
		LocalDate startDate = LocalDate.parse(scanner.next());
		
		System.out.print("Enter end date: ");
		LocalDate endDate = LocalDate.parse(scanner.next());
		
		try {
			List<Transaction> listTransactionData = transactionDAO.transactionsDayRange(startDate, endDate);
			listTransactionData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
    
    public void highMagnitudeTransaction() {
    	
    	System.out.print("Enter minimum amount of transaction: ");
		int amount = scanner.nextInt();
		
		try {
			List<Transaction> listTransactionData = transactionDAO.highMagnitudeTransaction(amount);
			listTransactionData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
    
}
