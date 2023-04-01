package com.bank.ui;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

import com.bank.dao.AccountDAO;
import com.bank.dao.AccountDAOImpl;
import com.bank.dao.TransactionDAO;
import com.bank.dao.TransactionDAOImpl;
import com.bank.dto.Transaction;
import com.bank.dto.TransactionImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

public class TransactionUI {
	
	private TransactionDAO transactionDAO;
	private AccountDAO accountDAO;
	private Scanner scanner;
	private static final String transactionTypeDebit = "Debit";
	private static final String transactionTypeCredit = "Credit";
	
	
	public TransactionUI(Scanner scanner) {
		this.transactionDAO = new TransactionDAOImpl();
		this.scanner = scanner;
	}

	private int transactionAmount;
	private String transactionType;
	private LocalDate timeOfTransaction;
	private String remark;
	private String accountNumber;
	private String balance;
	private String transactionID;
	
	public void quickTransfer() {
		System.out.print("Enter debit account number: ");
		String debitAccountNumber = scanner.next();
		System.out.print("Enter beneficiary account number: ");
		String beneficiaryAccount = scanner.next();
		System.out.print("Re-enter beneficiary account number: ");
		String beneficiaryAccountAgain = scanner.next();
		System.out.print("Enter the beneficiary bank's IFScode: ");
		String bank = scanner.next();
		System.out.print("Enter amount: ");
		int transactionAmount = scanner.nextInt();
		String transactionType = transactionTypeDebit;
		LocalDateTime timeOfTransaction = LocalDateTime.now();
		String balance = "";
		String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        String transactionID = generateUUIDNo.substring( generateUUIDNo.length() - 10);
        
        Transaction transaction = new TransactionImpl(transactionAmount,transactionType,timeOfTransaction, "To Transfer "+beneficiaryAccount,debitAccountNumber,"",transactionID);
		try {
			int result = transactionDAO.debit(transaction);
			
		} catch (SomethingWentWrongException | UserRelatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
