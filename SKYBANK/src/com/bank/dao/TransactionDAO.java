package com.bank.dao;

import java.time.LocalDate;
import java.util.List;

import com.bank.dto.Transaction;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

public interface TransactionDAO {

	int debit(Transaction transaction) throws SomethingWentWrongException, UserRelatedException,NoRecordFoundException;

	int credit(Transaction transaction) throws SomethingWentWrongException, UserRelatedException, NoRecordFoundException;

	List<Transaction> checkTransactionHistory(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException;
	
	//Accountant methods starts here
	
	List<Transaction> transactionsDayRange(LocalDate startDate, LocalDate endDate) throws SomethingWentWrongException, NoRecordFoundException;
	
	List<Transaction> highMagnitudeTransaction(int amount) throws SomethingWentWrongException, NoRecordFoundException;
} 
