package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bank.dto.Account;
import com.bank.dto.AccountImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;
import com.bank.exception.UserRelatedException;

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
	
	public Account getAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		Account account = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE accountNumber = ? AND is_delete = ? AND customer_Id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setString(1, accountNumber);
			ps.setInt(2, 0);
			ps.setInt(3, LoggedINUser.loggedInUserId);
			
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
			account.setNomineeName(resultSet.getString("nomineeName"));
			account.setNomineeRelation(resultSet.getString("nomineeRelation"));
			
			
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
	
	
	public int getAccountId(String accountNumber) throws SomethingWentWrongException,NoRecordFoundException{
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
	public void addAccount(Account account, String customerNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int id = UserDAOImpl.getCustomerId(customerNumber);
			
			//prepare the query
			String INSERT_QUERY = "INSERT INTO account (accountNumber,availableBalance,accountType,nomineeName, nomineeRelation,customer_Id,duration) VALUES (?,?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, account.getAccountNumber());
			ps.setString(2, account.getBalance());
			ps.setString(3, account.getAccountType());
			ps.setString(4, account.getNomineeName());
			ps.setString(5, account.getNomineeRelation());
			ps.setInt(6, id);
			ps.setInt(7, 0);
				
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
			account.setNomineeName(resultSet.getString("nomineeName"));
			account.setNomineeRelation(resultSet.getString("nomineeRelation"));
			list.add(account);
		}
		return list;
	}
	
	private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException{
		Account account = new AccountImpl();
			account.setAccountNumber(resultSet.getString("accountNumber"));
			account.setBalance(resultSet.getString("availableBalance"));
			account.setAccountType(resultSet.getString("accountType"));		
			account.setNomineeName(resultSet.getString("nomineeName"));
			account.setNomineeRelation(resultSet.getString("nomineeRelation"));
		return account;
	}

	@Override
	public List<Account> accountSummary() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE customer_Id = ? AND is_delete=?";
			
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
	
	private int getIsUpi(String accountNumber) throws NoRecordFoundException, SomethingWentWrongException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT is_upi FROM account WHERE accountNumber = ? AND is_delete = ? AND customer_Id =? AND accountType = ?";
			
			//get the prepared statement object
			PreparedStatement pr = connection.prepareStatement(SELECT_QUERY);
			
            pr.setString(1, accountNumber);
            pr.setInt(2, 0);
            pr.setInt(3, LoggedINUser.loggedInUserId);
            pr.setString(4, "Saving");
			
			//execute query
			ResultSet resultSet = pr.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No such account Found");
			}
			resultSet.next();
			result = resultSet.getInt("is_upi");
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
		return result;
	}
	
	@Override
	public int enableUPI(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			int isUpi = getIsUpi(accountNumber);
		    
			if(isUpi == 1){
				return result;
			}else {
				String UPDATE_QUERY = "UPDATE account SET is_upi=? WHERE accountNumber = ? AND customer_Id = ? AND is_delete = ?";
					
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
	public int disableUPI(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			int isUpi = getIsUpi(accountNumber);
				
			if(isUpi == 0){
				return result;
			}else {
				String UPDATE_QUERY = "UPDATE account SET is_upi=? WHERE accountNumber = ? AND customer_Id = ? AND is_delete = ?";
					
				//get the prepared statement object
				PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
					
			    ps.setInt(1, 0);
				ps.setString(2, accountNumber);
				ps.setInt(3, LoggedINUser.loggedInUserId);
				ps.setInt(4, 0);
	
				//execute query
				result = ps.executeUpdate();
				return result;
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
					
			//prepare the query
			String UPDATE_QUERY = "UPDATE account SET is_delete = ? WHERE customer_Id = ? AND accountNumber = ? AND is_delete = ?";
				
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
				
			//stuff the data in the query
			ps.setInt(1, 1);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setString(3, accountNumber);
			ps.setInt(4, 0);
				
			//execute query
			result = ps.executeUpdate();
			return result;
			
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
			
			//prepare the query
			String INSERT_QUERY = "INSERT INTO account (accountNumber,availableBalance,accountType,nomineeName, nomineeRelation,customer_Id) VALUES (?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, account.getAccountNumber());
			ps.setString(2, account.getBalance());
			ps.setString(3, account.getAccountType());
			ps.setString(4, account.getNomineeName());
			ps.setString(5, account.getNomineeRelation());
			ps.setInt(6, LoggedINUser.loggedInUserId);
				
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
		
	}
	
	@Override
	public List<Account> viewAccountFDRDData() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE customer_Id = ? AND is_delete=? AND (accountType = ? OR accountType = ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			ps.setInt(2, 0);
			ps.setString(3, "Fixed_Deposit");
			ps.setString(4, "Recurring_Deposit");
			
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
	public List<Account> viewAccountFDData() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE customer_Id = ? AND is_delete=? AND accountType = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			ps.setInt(2, 0);
			ps.setString(3, "Fixed_Deposit");
			
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
	public List<Account> viewAccountRDData() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE customer_Id = ? AND is_delete=? AND accountType = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			ps.setInt(2, 0);
			ps.setString(3, "Recurring_Deposit");
			
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
	public Account viewAccountRDDataParticular(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
	    Account account = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE customer_Id = ? AND is_delete=? AND accountType = ? AND accountNumber = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			ps.setInt(2, 0);
			ps.setString(3, "Recurring_Deposit");
			ps.setString(4, accountNumber);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No account Found");
			}
			resultSet.next();
			
			account = new AccountImpl();
			account = getAccountFromResultSet(resultSet);
			
			
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
	
	@Override
	public Account viewAccountFDDataParticular(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
	    Account account = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE customer_Id = ? AND is_delete=? AND accountType = ? AND accountNumber = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			ps.setInt(2, 0);
			ps.setString(3, "Fixed_Deposit");
			ps.setString(4, accountNumber);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No account Found");
			}
			resultSet.next();
			
			account = new AccountImpl();
			account.setAccountNumber(resultSet.getString("accountNumber"));
			account.setBalance(resultSet.getString("availableBalance"));
			account.setAccountType(resultSet.getString("accountType"));		
			account.setNomineeName(resultSet.getString("nomineeName"));
			account.setNomineeRelation(resultSet.getString("nomineeRelation"));
			
			
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
	
	@Override
	public int partialCloseFD(String fdAccountNumber, String savingAccountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//getting the accountId to which the payee account is connected to check if the entered account number is a valid one
			int accountId = getAccountId(fdAccountNumber);
			
			//call here the method getBalance and deposit method to given account number from fd account
			
			if(accountId == 0) {
				//If no such payee account is linked with the logged in customer
				result = accountId;
				return result;
			}
			else {			
				//prepare the query
				String UPDATE_QUERY = "UPDATE account SET is_delete = ? WHERE id = ? AND accountType = ?";
				
				//get the prepared statement object
				PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
				
				//stuff the data in the query
				ps.setInt(1, 1);
				ps.setInt(2, accountId);
				ps.setString(3, "Fixed_Deposit");
				
				//execute query
				result = ps.executeUpdate();
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
	public int partialCloseRD(String rdAccountNumber, String savingAccountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//getting the accountId to which the payee account is connected to check if the entered account number is a valid one
			int accountId = getAccountId(rdAccountNumber);
			
			//call here the method getBalance and deposit method to given account number from fd account
			
			if(accountId == 0) {
				//If no such payee account is linked with the logged in customer
				result = accountId;
				return result;
			}
			else {			
				//prepare the query
				String UPDATE_QUERY = "UPDATE account SET is_delete = ? WHERE id = ? AND accountType = ?";
				
				//get the prepared statement object
				PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
				
				//stuff the data in the query
				ps.setInt(1, 1);
				ps.setInt(2, accountId);
				ps.setString(3, "Recurring_Deposit");
				
				//execute query
				result = ps.executeUpdate();
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
	public void updateAccountBalance(String accountNumber, int amount) throws SomethingWentWrongException, NoRecordFoundException, UserRelatedException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
					
			//prepare the query
			String UPDATE_QUERY = "UPDATE account SET availableBalance = ? WHERE customer_Id = ? AND accountNumber = ? AND is_delete = ?";
				
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			String amountReal = ""+ amount;
				
			//stuff the data in the query
			ps.setString(1, amountReal);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setString(3, accountNumber);
			ps.setInt(4, 0);
				
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
	
	@Override
	public List<Account> viewAllAccounts() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
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
	public List<Account> viewAllClosedAccounts() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, 1);
			
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
	
	private Account getAccountForInoperative(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		Account account = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE accountNumber = ? ";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setString(1, accountNumber);			
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
			account.setNomineeName(resultSet.getString("nomineeName"));
			account.setNomineeRelation(resultSet.getString("nomineeRelation"));
			
			
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
	
	@Override
	public List<Account> viewAllInoperativeAccounts() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber FROM transaction t INNER JOIN account a on t.account_id = a.id GROUP BY accountNumber HAVING MAX(TIMESTAMP(timeOfTransaction)) < ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			Date currentDate = new Date();
	        long milliseconds = (long) 2*365 * 24 * 60 * 60 * 1000;
	        Date twoYearBefore = new Date(currentDate.getTime() - milliseconds);
	     
			ps.setDate(1,new java.sql.Date(twoYearBefore.getTime()));
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No account Found");
			}
			
			list = new ArrayList<>();
			while(resultSet.next()) {
				list.add(getAccountForInoperative(resultSet.getString("accountNumber")));
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
		return list;
	}
	
	@Override
	public List<Account> viewParticularAccount(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Account> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT accountNumber, availableBalance, accountType, nomineeName, nomineeRelation FROM account WHERE accountNumber = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setString(1, accountNumber);
			
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
	public void makeAccountInoperative(String accountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query

			String UPDATE_QUERY = "UPDATE account SET is_inoperative = ? WHERE accountNumber = ?";
					
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
					
			ps.setInt(1, 1);
			ps.setString(2, accountNumber);
			
			//execute query
			ps.executeUpdate();
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
