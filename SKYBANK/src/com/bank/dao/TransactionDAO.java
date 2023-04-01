package com.bank.dao;

import com.bank.dto.Transaction;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

public interface TransactionDAO {

	int debit(Transaction transaction) throws SomethingWentWrongException, UserRelatedException,NoRecordFoundException;
}
