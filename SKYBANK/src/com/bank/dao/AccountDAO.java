package com.bank.dao;

import java.util.List;

import com.bank.dto.Account;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

public interface AccountDAO {

	//for user side
	void addAccount(Account account,String customerNumber) throws SomethingWentWrongException, NoRecordFoundException;
	void addAccountFD(Account account) throws SomethingWentWrongException ;
    public List<Account> accountSummary() throws SomethingWentWrongException, NoRecordFoundException;
	int eStatementSubscription(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	int closeAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	List<Account> viewAccountFDRDData() throws SomethingWentWrongException, NoRecordFoundException;
	int partialCloseFD(String fdAccountNumber, String savingAccountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	List<Account> viewAccountFDData() throws SomethingWentWrongException, NoRecordFoundException;
	List<Account> viewAccountRDData() throws SomethingWentWrongException, NoRecordFoundException;
	Account viewAccountRDDataParticular(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	Account viewAccountFDDataParticular(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	int partialCloseRD(String rdAccountNumber, String savingAccountNumber)throws SomethingWentWrongException, NoRecordFoundException;
	int enableUPI(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	int disableUPI(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	void updateAccountBalance(String accountNumber, int amount) throws SomethingWentWrongException, NoRecordFoundException, UserRelatedException;
	Account getAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	int getAccountId(String accountNumber) throws SomethingWentWrongException,NoRecordFoundException;
	
	//for accountant side
	
	public List<Account> viewAllAccounts() throws SomethingWentWrongException, NoRecordFoundException;
	public List<Account> viewParticularAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	public List<Account> viewAllClosedAccounts() throws SomethingWentWrongException, NoRecordFoundException ;
	public List<Account> viewAllInoperativeAccounts() throws SomethingWentWrongException, NoRecordFoundException;
	public void makeAccountInoperative(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
}
