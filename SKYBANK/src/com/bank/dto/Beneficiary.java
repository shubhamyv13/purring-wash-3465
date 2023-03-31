package com.bank.dto;

public interface Beneficiary {

	public String getFisrtName();

	public void setFisrtName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getAccountNumber();

	public void setAccountNumber(String accountNumber);
	public String getBank();

	public void setBank(String bank);

	public String getBranch();

	public void setBranch(String branch);

	public String getLimitOfTransaction();

	public void setLimitOfTransaction(String limitOfTransaction);

	public Account getAccount();

	public void setAccount(Account account);
}
