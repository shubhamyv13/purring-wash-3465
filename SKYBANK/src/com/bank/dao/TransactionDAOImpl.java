package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bank.dto.Transaction;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

public class TransactionDAOImpl implements TransactionDAO{

	@Override
	public int debit(Transaction transaction) throws SomethingWentWrongException, UserRelatedException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int account_id = AccountDAOImpl.getAccountId(transaction.getAccountNumber());
			
			if(account_id == 0) {
				return result;
			}
			
			int balance = Integer.parseInt(AccountDAOImpl.getAccount(transaction.getAccountNumber()).getBalance());
			
			if(balance < transaction.getTransactionAmount()) {
				return -1;
			}
			
			//prepare the query
			String INSERT_QUERY = "INSERT INTO account (accountNumber,availableBalance,accountType,nomineeName, nomineeRelation,customer_Id) VALUES (?,?,?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
//			ps.setString(1, account.getAccountNumber());
//			ps.setString(2, account.getBalance());
//			ps.setString(5, account.getAccountType());
//			ps.setString(6, account.getNomineeName());
//			ps.setString(7, account.getNomineeRelation());
			ps.setInt(8, LoggedINUser.loggedInUserId);
				
			//execute query
			ps.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
			//code to log the error in the file
			throw new SomethingWentWrongException();
		}finally {
			try {
				//close the exception 
				DBUtils.closeConnection(connection);
			}catch(SQLException sqlEx) {
				throw new SomethingWentWrongException();
			}
		}
		
		return 0;
	}

}
