package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dto.Account;
import com.bank.dto.AccountImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public class AccountDAOImpl implements AccountDAO{
	
//	public static int getCustomerId(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
//		Connection connection = null;
//		int customerId = 0;
//		try {
//			//connect to database
//			connection = DBUtils.connectToDatabase();
//			//prepare the query
//			String SELECT_QUERY = "SELECT customer_Id FROM account WHERE accountNumber = ?";
//			
//			//get the prepared statement object
//			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
//			
//			ps.setString(1, accountNumber);
//			
//			//execute query
//			ResultSet resultSet = ps.executeQuery();
//			
//			//check if result set is empty
//			if(DBUtils.isResultSetEmpty(resultSet)) {
//				return customerId;
//			}
//			resultSet.next();
//			customerId = resultSet.getInt("customer_Id");		
//		}catch(SQLException sqlEx) {
//			//code to log the error in the file
//			throw new SomethingWentWrongException();
//		}finally {
//			try {
//				//close the exception
//				DBUtils.closeConnection(connection);				
//			}catch(SQLException sqlEX) {
//				throw new SomethingWentWrongException();
//			}
//		}
//		return customerId;
//	}
	
	public static Account getAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		Account account = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType FROM account WHERE accountNumber = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setString(1, accountNumber);
			ps.setInt(2, 0);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No suc account found linked to your customerId.");
			}
			resultSet.next();
			
			account = new AccountImpl();
			account.setAccountNumber(resultSet.getString("accountNumber"));
			account.setBalance(resultSet.getString("availableBalance"));
			account.setAccountType(resultSet.getString("accountType"));	
			
			
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
		return account;
	}
	
	
	public static int getAccountId(String accountNumber) throws SomethingWentWrongException,NoRecordFoundException{
		Connection connection = null;
		int checkResult = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			String SELECT_QUEry = "SELECT id FROM account WHERE accountNumber = ? AND customer_Id = ? AND is_delete = ?";
			
			//prepare the query
			PreparedStatement pr = connection.prepareStatement(SELECT_QUEry);
			
			pr.setString(1, accountNumber);
			pr.setInt(2, LoggedINUser.loggedInUserId);
			pr.setInt(3, 0);
			
			//execute query
			ResultSet resultSet = pr.executeQuery();
			
			if(DBUtils.isResultSetEmpty(resultSet)) {
				return checkResult;
			}
			//Data extraction from the result set
			resultSet.next();
			checkResult = resultSet.getInt(1);
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
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
		return checkResult;
	}

	@Override
	public void addAccount(Account account) throws SomethingWentWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int id = UserDAOImpl.getCustomerId(account.getCustomerId());
			
			//prepare the query
			String INSERT_QUERY = "INSERT INTO account (accountNumber,availableBalance,aadhar,pan,accountType,customer_Id) VALUES (?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, account.getAccountNumber());
			ps.setString(2, account.getBalance());
			ps.setString(3, account.getAadhar());
			ps.setString(4, account.getPan());
			ps.setString(5, account.getAccountType());
			ps.setInt(6, id);
			
			System.out.println(ps);
				
			//execute query
			ps.executeUpdate();
		}catch(SQLException | NoRecordFoundException ex) {
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
		
	}
	
	private List<Account> viewAccountResultSet(ResultSet resultSet) throws SQLException{
		List<Account> list = new ArrayList<>();
		while(resultSet.next()) {
			Account account = new AccountImpl();
			account.setAccountNumber(resultSet.getString("accountNumber"));
			account.setBalance(resultSet.getString("availableBalance"));
			account.setAccountType(resultSet.getString("accountType"));			
			list.add(account);
		}
		return list;
	}

	@Override
	public List<Account> accountSummary() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType FROM account WHERE customer_Id = ? AND is_delete=?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			ps.setInt(2, 0);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No account Found");
			}
			
			list = viewAccountResultSet(resultSet);
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
	public int eStatementSubscription(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT customer_Id, eStatement FROM account WHERE accountNumber = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement pr = connection.prepareStatement(SELECT_QUERY);
			
            pr.setString(1, accountNumber);
            pr.setInt(2, 0);
			
			//execute query
			ResultSet resultSet = pr.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No such account Found");
			}else{
				resultSet.next();
				int id = resultSet.getInt("customer_Id");
				int eStatementCheck = resultSet.getInt("eStatement");
				
				if(id != LoggedINUser.loggedInUserId) {
					throw new NoRecordFoundException("No such account for your customerId.");
				}else if(eStatementCheck == 1){
					return result;
				}else {
					String UPDATE_QUERY = "UPDATE account SET eStatement=? WHERE accountNumber = ? AND customer_Id = ? AND is_delete = ?";
					
					//get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
					
					ps.setInt(1, 1);
					ps.setString(2, accountNumber);
					ps.setInt(3, LoggedINUser.loggedInUserId);
					ps.setInt(4, 0);
					
					result = 1;
					
					//execute query
					ps.executeUpdate();
					return result;
				}
			} 
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
	}
	
	@Override
	public int closeAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//getting the accountId to which the payee account is connected to check if the entered account number is a valid one
			int accountId = AccountDAOImpl.getAccountId(accountNumber);
			
			if(accountId == 0) {
				//If no such payee account is linked with the logged in customer
				result = accountId;
				return result;
			}
			else {			
				//prepare the query
				String UPDATE_QUERY = "UPDATE account SET is_delete = ? WHERE id = ?";
				
				//get the prepared statement object
				PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
				
				//stuff the data in the query
				ps.setInt(1, 1);
				ps.setInt(2, accountId);
				
				//execute query
				ps.executeUpdate();
				result = 1;
				return result;
			}
			
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
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
		
	}

	@Override
	public void addAccountFD(Account account) throws SomethingWentWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int id = UserDAOImpl.getCustomerId(account.getCustomerId());
			
			//prepare the query
			String INSERT_QUERY = "INSERT INTO account (accountNumber,availableBalance,aadhar,pan,accountType,customer_Id,duration) VALUES (?,?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, account.getAccountNumber());
			ps.setString(2, account.getBalance());
			ps.setString(3, account.getAadhar());
			ps.setString(4, account.getPan());
			ps.setString(5, account.getAccountType());
			ps.setInt(6, id);
			ps.setInt(7, account.getDuration());
			
			System.out.println(ps);
				
			//execute query
			ps.executeUpdate();
		}catch(SQLException | NoRecordFoundException ex) {
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
		
	}
	
}
