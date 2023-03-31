package com.bank.dto;

public interface Account {

	public String getAadhar();

	public void setAadhar(String aadhar);
	public String getPan();
	public void setPan(String pan) ;
	public String getBalance();
	public void setBalance(String balance);
	public String getCustomerId();
	public void setCustomerId(String customerId);
	public String getAccountNumber();
	public void setAccountNumber(String accountNumber);
	public String getAccountType();
	public void setAccountType(String accountType);
	public int getDuration();
	public void setDuration(int duration);
}
