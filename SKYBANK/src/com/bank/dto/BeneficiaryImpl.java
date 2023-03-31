package com.bank.dto;

public class BeneficiaryImpl implements Beneficiary {

	private String firstName;
	private String lastName;
	private String accountNumber;
	private String bank;
	private String branch;
	private String limitOfTransaction;
	private Account account;
	
	public BeneficiaryImpl() {}
	
	public BeneficiaryImpl(String firstName, String lastName, String accountNumber, String bank, String branch,
			String limitOfTransaction, Account account) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.bank = bank;
		this.branch = branch;
		this.limitOfTransaction = limitOfTransaction;
		this.account = account;
	}

	public String getFisrtName() {
		return firstName;
	}

	public void setFisrtName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getLimitOfTransaction() {
		return limitOfTransaction;
	}

	public void setLimitOfTransaction(String limitOfTransaction) {
		this.limitOfTransaction = limitOfTransaction;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Name " + firstName+" " +lastName + ", AccountNumber=" + accountNumber
				+ ", Bank=" + bank + ", Branch=" + branch + ", LimitOfTransaction=" + limitOfTransaction  + "\n";
	}
}
