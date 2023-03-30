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

	@Override
	public void addAccount(Account account) throws SomethingWentWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			String SELECT_QUEry = "SELECT id FROM user WHERE customerId = ?";
			
			//prepare the query
			PreparedStatement pr = connection.prepareStatement(SELECT_QUEry);
			
			pr.setString(1, account.getCustomerId());
			
			//execute query
			ResultSet rs = pr.executeQuery();
			//Data extraction from the result set
			rs.next();
			int id = rs.getInt(1);
			
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
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType FROM account WHERE customer_Id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			
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
	public void eStatementSubscription(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT id FROM account WHERE accountNumber = ?";
			
			//get the prepared statement object
			PreparedStatement pr = connection.prepareStatement(SELECT_QUERY);
			
            pr.setString(1, accountNumber);
			
			//execute query
			ResultSet resultSet = pr.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No such account Found");
			}else {
				String UPDATE_QUERY = "UPDATE account SET eStatement=? WHERE accountNumber = ? AND customer_Id = ?";
				
				//get the prepared statement object
				PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
				
				ps.setInt(1, 0);
				ps.setString(2, accountNumber);
				ps.setInt(3, LoggedINUser.loggedInUserId);
				
				//execute query
				ps.executeUpdate();
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

	
}
