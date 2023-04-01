package com.bank.dto;


import java.time.LocalDateTime;

public interface Transaction {

	public int getTransactionAmount();

	public void setTransactionAmount(int transactionAmount);

	public String getTransactionType();

	public void setTransactionType(String transactionType);

	public LocalDateTime getTimeOfTransaction();

	public void setTimeOfTransaction(LocalDateTime timeOfTransaction);

	public String getRemark();

	public void setRemark(String remark);

	public String getAccountNumber();

	public void setAccountNumber(String accountNumber);
	
	public String getBalance();

	public void setBalance(String balance);
	
	public String getTransactionID();
	public void setTransactionID(String transactionID);
}
