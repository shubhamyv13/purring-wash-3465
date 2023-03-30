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
			String SELECT_QUERY = "SELECT fname, lname, mobile, email, dateOfBirth, address, securityQuestion,customerId FROM user WHERE id = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, LoggedINUser.loggedInUserId);
			
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
			String LOGIN_QUERY = "SELECT id FROM user WHERE username= ? AND password = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
			
			//stuff the data in the query
			ps.setString(1, username);
			ps.setString(2, password);
			
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
			String UPDATE_QUERY = "UPDATE user SET fname =?,lname = ?,mobile = ?,email = ?,dateOfBirth = ?,address = ?,securityQuestion = ? WHERE id = ?";
			
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

}
