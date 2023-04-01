package com.bank.dto;

public interface Account {

	public String getBalance();
	public void setBalance(String balance);
	public String getAccountNumber();
	public void setAccountNumber(String accountNumber);
	public String getAccountType();
	public void setAccountType(String accountType);
	public int getDuration();
	public void setDuration(int duration);
	public String getNomineeName();
	public void setNomineeName(String nomineeName);
	public String getNomineeRelation();
	public void setNomineeRelation(String nomineeRelation);
}
