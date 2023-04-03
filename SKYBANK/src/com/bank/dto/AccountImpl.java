package com.bank.dto;

public class AccountImpl implements Account{
	
	private String balance;
	private String accountNumber;
	private String accountType;
	private int duration = 0;
	private String nomineeName ;
	private String nomineeRelation;
	
	public AccountImpl() {}
	
	public AccountImpl(String balance, String accountNumber,
			String accountType, int duration,String nomineeName, String nomineeRelation) {
		super();
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.duration = duration;
		this.nomineeName = nomineeName;
		this.nomineeRelation = nomineeRelation;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeRelation() {
		return nomineeRelation;
	}

	public void setNomineeRelation(String nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}
	
	

	@Override
	public String toString() {
		return "AccountNumber= " + accountNumber +  ", AccountType= " + accountType + ", Balance= " + balance
				+ ", NomineeName= " + nomineeName + ", NomineeRelation= " + nomineeRelation + "\n";
	}

	
	
	
	
}
