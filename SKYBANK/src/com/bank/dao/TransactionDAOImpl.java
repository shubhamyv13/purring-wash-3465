package com.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bank.dto.Beneficiary;
import com.bank.dto.BeneficiaryImpl;
import com.bank.dto.Transaction;
import com.bank.dto.TransactionImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

public class TransactionDAOImpl implements TransactionDAO{
	
	public static AccountDAO accountDAO;
	
	public TransactionDAOImpl() {
		this.accountDAO = new AccountDAOImpl();
	}
	

	@Override
	public int debit(Transaction transaction) throws SomethingWentWrongException, UserRelatedException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int account_id = accountDAO.getAccountId(transaction.getAccountNumber());			
			String accountType = accountDAO.getAccount(transaction.getAccountNumber()).getAccountType();
			
			if(accountType.equals("Fixed_Deposit") || accountType.equals("Recurring_Deposit")) {
				result = -1;
				return result;
			}
			
			int balance = Integer.parseInt(accountDAO.getAccount(transaction.getAccountNumber()).getBalance());
			
			if(balance < transaction.getTransactionAmount()) {
				result = -2;
				return result;
			}
			
			int balanceNow = balance - transaction.getTransactionAmount();
			
			//prepare the query
			String INSERT_QUERY = "INSERT INTO transaction (transactionAmount,transactionType,timeOfTransaction,account_id, remark,transactionID,balance) VALUES (?,?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, transaction.getTransactionAmount());
			ps.setString(2, transaction.getTransactionType());
			ps.setTimestamp(3, Timestamp.valueOf(transaction.getTimeOfTransaction()));
			ps.setInt(4, account_id);
			ps.setString(5, transaction.getRemark());
			ps.setString(6, transaction.getTransactionID());
			ps.setString(7, ""+balanceNow);
				
			//execute query
			result = ps.executeUpdate();
			accountDAO.updateAccountBalance(transaction.getAccountNumber(), balanceNow);
			
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
		
		return result;
	}
	
	@Override
	public int credit(Transaction transaction) throws SomethingWentWrongException, UserRelatedException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int account_id = accountDAO.getAccountId(transaction.getAccountNumber());			
			String accountType = accountDAO.getAccount(transaction.getAccountNumber()).getAccountType();
			
			if(accountType.equals("Fixed_Deposit") || accountType.equals("Recurring_Deposit")) {
				result = -1;
				return result;
			}
			
			int balance = Integer.parseInt(accountDAO.getAccount(transaction.getAccountNumber()).getBalance());
			
			int balanceNow = balance + transaction.getTransactionAmount();
			
			//prepare the query
			String INSERT_QUERY = "INSERT INTO transaction (transactionAmount,transactionType,timeOfTransaction,account_id, remark,transactionID,balance) VALUES (?,?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, transaction.getTransactionAmount());
			ps.setString(2, transaction.getTransactionType());
			ps.setTimestamp(3, Timestamp.valueOf(transaction.getTimeOfTransaction()));
			ps.setInt(4, account_id);
			ps.setString(5, transaction.getRemark());
			ps.setString(6, transaction.getTransactionID());
			ps.setString(7, ""+balanceNow);
				
			//execute query
			result = ps.executeUpdate();
			accountDAO.updateAccountBalance(transaction.getAccountNumber(), balanceNow);
			
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
		
		return result;
	}
	
	private List<Transaction> viewTransactionFromResultSet(ResultSet resultSet, String accountNumber) throws SQLException{
		List<Transaction> list = new ArrayList<>();
		while(resultSet.next()) {
			
			Transaction transaction = new TransactionImpl();
			transaction.setTransactionAmount(resultSet.getInt("transactionAmount"));
			transaction.setTransactionType(resultSet.getString("transactionType"));
			transaction.setTimeOfTransaction(resultSet.getTimestamp("timeOfTransaction").toLocalDateTime());
			transaction.setRemark(resultSet.getString("remark"));
			transaction.setBalance(resultSet.getString("balance"));
			transaction.setTransactionID(resultSet.getString("transactionID"));
			transaction.setAccountNumber(accountNumber);
			
			
			list.add(transaction);
		}
		
		return list;
	}
	
	private List<Transaction> viewAllTransactionFromResultSet(ResultSet resultSet) throws SQLException{
		List<Transaction> list = new ArrayList<>();
		while(resultSet.next()) {
			
			Transaction transaction = new TransactionImpl();
			transaction.setTransactionAmount(resultSet.getInt("transactionAmount"));
			transaction.setTransactionType(resultSet.getString("transactionType"));
			transaction.setTimeOfTransaction(resultSet.getTimestamp("timeOfTransaction").toLocalDateTime());
			transaction.setRemark(resultSet.getString("remark"));
			transaction.setBalance(resultSet.getString("balance"));
			transaction.setTransactionID(resultSet.getString("transactionID"));	
			
			list.add(transaction);
		}
		
		return list;
	}

	@Override
	public List<Transaction> checkTransactionHistory(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Transaction> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int account_id = accountDAO.getAccountId(accountNumber);
			
			//prepare the query
			String SELECT_QUERY = "SELECT transactionAmount,transactionType,timeOfTransaction, remark,transactionID, t.balance FROM transaction t INNER JOIN account a ON t.account_id = a.id WHERE t.account_id = ? AND a.customer_Id = ? AND a.is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, account_id);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setInt(3, 0);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No transaction found.");
			}
			
			list = viewTransactionFromResultSet(resultSet,accountNumber);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException();
			}
		}
		return list;
	}
	
	@Override
	public List<Transaction> transactionsDayRange(LocalDate startDate, LocalDate endDate) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Transaction> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String SELECT_QUERY = "SELECT transactionAmount,transactionType,timeOfTransaction, remark,transactionID, balance FROM transaction WHERE DATE(timeOfTransaction) BETWEEN ? AND ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setDate(1, Date.valueOf(startDate));
			ps.setDate(2, Date.valueOf(endDate));
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No such transaction found.");
			}
			
			list = viewAllTransactionFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException();
			}
		}
		return list;
	}
	
	public List<Transaction> highMagnitudeTransaction(int amount) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Transaction> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//prepare the query
			String SELECT_QUERY = "SELECT transactionAmount,transactionType,timeOfTransaction, remark,transactionID, balance FROM transaction WHERE transactionAmount > ? AND (transactionType = ? OR transactionType = ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, amount);
			ps.setString(2, "Debit");
			ps.setString(3, "Credit");
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No such transaction found.");
			}
			
			list = viewAllTransactionFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException();
		}finally {
			try {
				//close the exception
				DBUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException();
			}
		}
		return list;
	}
	
	
}
