package com.bank.dao;

import java.util.List;

import com.bank.dto.Account;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public interface AccountDAO {

	//for user side
	void addAccount(Account account) throws SomethingWentWrongException;
	void addAccountFD(Account account) throws SomethingWentWrongException ;
    public List<Account> accountSummary() throws SomethingWentWrongException, NoRecordFoundException;
	int eStatementSubscription(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	int closeAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
}
