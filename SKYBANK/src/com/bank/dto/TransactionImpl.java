package com.bank.dto;

import java.time.LocalDateTime;

public class TransactionImpl implements Transaction{

	private int transactionAmount;
	private String transactionType;
	private LocalDateTime timeOfTransaction;
	private String remark;
	private String accountNumber;
	private String balance;
	private String transactionID;
	
	public TransactionImpl() {}
	
	public TransactionImpl(int transactionAmount, String transactionType, LocalDateTime timeOfTransaction, String remark,
			String accountNumber,String balance,String transactionID) {
		super();
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.timeOfTransaction = timeOfTransaction;
		this.remark = remark;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.transactionID = transactionID;
 	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getTimeOfTransaction() {
		return timeOfTransaction;
	}

	public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return  "TimeOfTransaction= " + timeOfTransaction.toLocalDate() + ", Remark= " + remark + ", TransactionAmount= " + transactionAmount + ", TransactionType= " + transactionType
				+  ", Balance= "+ balance +"\n";
	}
	
	
	
}
