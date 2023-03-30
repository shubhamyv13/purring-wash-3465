package com.bank.dto;

public class AccountImpl implements Account{

	private String aadhar;
	private String pan;
	private String balance;
	private String customerId;
	private String accountNumber;
	private String accountType;
	
	public AccountImpl() {}
	
	public AccountImpl(String aadhar, String pan, String balance, String customerId, String accountNumber,
			String accountType) {
		super();
		this.aadhar = aadhar;
		this.pan = pan;
		this.balance = balance;
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return  "AccountNumber= " + accountNumber + ", AccountType= " + accountType + ", Balance= " + balance + "\n";
	}
	
	
	
}
