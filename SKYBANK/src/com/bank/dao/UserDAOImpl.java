package com.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bank.dto.User;
import com.bank.dto.UserImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public class UserDAOImpl implements UserDAO {
	
	public static int getCustomerId(String customerNumber) throws SomethingWentWrongException,NoRecordFoundException{
		Connection connection = null;
		int checkResult = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			String SELECT_QUEry = "SELECT id FROM user WHERE customerId = ? AND is_delete = ?";
			
			//prepare the query
			PreparedStatement pr = connection.prepareStatement(SELECT_QUEry);
			
			pr.setString(1, customerNumber);
			pr.setInt(2, 0);
			
			//execute query
			ResultSet resultSet = pr.executeQuery();
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No such customer Id found");
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
	
	public static int getAnyCustomerId(String customerNumber) throws SomethingWentWrongException,NoRecordFoundException{
		Connection connection = null;
		int checkResult = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			String SELECT_QUEry = "SELECT id FROM user WHERE customerId = ?";
			
			//prepare the query
			PreparedStatement pr = connection.prepareStatement(SELECT_QUEry);
			
			pr.setString(1, customerNumber);
			
			//execute query
			ResultSet resultSet = pr.executeQuery();
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No such customer Id found");
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
	public void addUser(User user) throws SomethingWentWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO user (fname,lname,mobile,email,dateOfBirth,address,securityQuestion,registrationDate,username,password, customerId) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getMobileNumber());
			ps.setString(4, user.geteMail());
			ps.setString(5, user.getDateOfBirth());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getSecurityQuestion());
			ps.setDate(8, Date.valueOf(user.getRegistrationDate()));
			ps.setString(9, user.getUsername());
			ps.setString(10, user.getPassword());
			ps.setString(11, user.getCustomerId());
			
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
	
	private List<User> viewProfileFromResultSet(ResultSet resultSet) throws SQLException{
		List<User> list = new ArrayList<>();
		while(resultSet.next()) {
			User user = new UserImpl();
			user.setFirstName(resultSet.getString("fname"));
			user.setLastName(resultSet.getString("lname"));
			user.setMobileNumber(resultSet.getString("mobile"));
			user.seteMail(resultSet.getString("email"));
			user.setDateOfBirth(resultSet.getString("dateOfBirth"));;
			user.setAddress(resultSet.getString("address"));
			user.setSecurityQuestion(resultSet.getString("securityQuestion"));
			user.setCustomerId(resultSet.getString("customerId"));
			
			list.add(user);
		}
		
		return list;
	}
	
	public List<User> viewProfile() throws SomethingWentWrongException, NoRecordFoundException{
		Connection connection = null;
		List<User> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT fname, lname, mobile, email, dateOfBirth, address, securityQuestion,customerId FROM user WHERE id = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			ps.setInt(2, 0);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No user Found");
			}
			
			list = viewProfileFromResultSet(resultSet);
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
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String LOGIN_QUERY = "SELECT id FROM user WHERE username= ? AND password = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
			
			//stuff the data in the query
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, 0);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("Invalid Username and Password");
			}
			
			//you are here means username and password combination is correct
			resultSet.next();
			LoggedINUser.loggedInUserId = resultSet.getInt("id");
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException();
		}finally {
			try {
				//close the connection
				DBUtils.closeConnection(connection);
			}catch(SQLException sqlEx) {
				throw new SomethingWentWrongException();
			}
		}
		
	}
	
	@Override
	public void logout() {
		LoggedINUser.loggedInUserId = 0;
	}

	@Override
	public void updateUser(User user) throws SomethingWentWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE user SET fname =?,lname = ?,mobile = ?,email = ?,dateOfBirth = ?,address = ?,securityQuestion = ? WHERE id = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getMobileNumber());
			ps.setString(4, user.geteMail());
			ps.setString(5, user.getDateOfBirth());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getSecurityQuestion());
			ps.setInt(8, LoggedINUser.loggedInUserId);
			ps.setInt(9, 0);
			
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
	
	private boolean isOldPasswordCorrect(String oldPassword) throws SomethingWentWrongException{
		Connection connection = null;
		boolean isPasswordValid = false;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String CHECK_PASSSWORD_QUERY = "SELECT count(*) as count FROM user WHERE  password = ? AND id = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(CHECK_PASSSWORD_QUERY);
			
			//stuff the data in the query
			ps.setString(1, oldPassword);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setInt(3, 0);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			
			isPasswordValid = resultSet.getInt("count") == 1;
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
		return isPasswordValid;
	}
	
	@Override
	public void changePassword(String oldPassword, String newPassword)throws SomethingWentWrongException, NoRecordFoundException{
		if(!isOldPasswordCorrect(oldPassword)) {
			throw new NoRecordFoundException("Invalid old passowrd!");
		}
		
		//you are here means old password matched
		Connection connection = null;
		
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE user SET password = ? WHERE id = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, newPassword);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setInt(3, 0);
			
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
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException();
			}
		}
	}

	@Override
	public void deleteUser() throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
						
			//prepare the query
			String UPDATE_QUERY = "UPDATE user SET is_delete = ? WHERE id = ?";
				
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
				
			//stuff the data in the query
			ps.setInt(1, 1);
			ps.setInt(2, LoggedINUser.loggedInUserId);
				
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
	public void updateUserPan(String pan) throws SomethingWentWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE user SET pan = ? WHERE id = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, pan);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setInt(3, 0);
			
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
	public void updateUserAadhar(String aadhar) throws SomethingWentWrongException {
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE user SET aadhar = ? WHERE id = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, aadhar);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setInt(3, 0);
			
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
	
	
	//Accountant methods start here --
	
	public List<User> viewAllCustomers() throws SomethingWentWrongException, NoRecordFoundException{
		Connection connection = null;
		List<User> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT fname, lname, mobile, email, dateOfBirth, address, securityQuestion,customerId FROM user";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No user Found");
			}
			
			list = viewProfileFromResultSet(resultSet);
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
	
	public List<User> viewParticularCustomer(String customerNumber) throws SomethingWentWrongException, NoRecordFoundException{
		Connection connection = null;
		List<User> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int customerId = getAnyCustomerId(customerNumber);
			
			//prepare the query
			String SELECT_QUERY = "SELECT fname, lname, mobile, email, dateOfBirth, address, securityQuestion,customerId FROM user WHERE id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, customerId);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No user Found");
			}
			
			list = viewProfileFromResultSet(resultSet);
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
