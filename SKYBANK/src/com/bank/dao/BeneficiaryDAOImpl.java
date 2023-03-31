package com.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dto.Beneficiary;
import com.bank.dto.BeneficiaryImpl;
import com.bank.dto.User;
import com.bank.dto.UserImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public class BeneficiaryDAOImpl implements BeneficiaryDAO{
	
	//a helper function to get the beneficiaryId
	private int getBeneficiaryId(String beneficiaryAccountNumber, String payeeAccountNumber) throws NoRecordFoundException, SomethingWentWrongException {
		Connection connection = null;
		int result = 0;
        try {
        	//connect to database
			connection = DBUtils.connectToDatabase();
			//getting the account_Id for the given payee account number
			int acc_Id = AccountDAOImpl.getAccountId(payeeAccountNumber);
			
			//prepare the query
			String INSERT_QUERY = "SELECT id FROM beneficiary WHERE accountNum = ? AND acc_Id = ? AND is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, beneficiaryAccountNumber);
			ps.setInt(2, acc_Id);
			ps.setInt(3, 0);
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			if(DBUtils.isResultSetEmpty(resultSet)) {
				return result;
			}
			//Data extraction from the result set
			resultSet.next();
			result = resultSet.getInt(1);
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
		return result;
	}

	@Override
	public int addBeneficiary(Beneficiary beneficiary) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//getting the beneficiary id to check if the beneficiary already exist or not
			int beneficiaryId = getBeneficiaryId(beneficiary.getAccountNumber(),beneficiary.getAccount().getAccountNumber());
			
			//getting the payee account type (as FD account cannot have beneficiaries)
			String payeeAccountType = AccountDAOImpl.getAccount(beneficiary.getAccount().getAccountNumber()).getAccountType();
			
			//getting the accountId to which the payee account is connected to check if the entered account number is a valid one
			int accountId = AccountDAOImpl.getAccountId(beneficiary.getAccount().getAccountNumber());
			System.out.println(accountId);
			
			if(payeeAccountType.equals("Fixed_Deposit")) {
				result = -1;
				return result;
			}else if(accountId == 0) {
				//If no such account account is linked with the logged in customer
				result = -2;
				return result;
			}
			else if(beneficiaryId > 0) {
				result = beneficiaryId;
				return result;
			}	
			else {
				int id = AccountDAOImpl.getAccountId(beneficiary.getAccount().getAccountNumber());
				
				//prepare the query
				String INSERT_QUERY = "INSERT INTO beneficiary (fname,lname,accountNum,bank,branch,limitOfTransaction,acc_Id) VALUES (?,?,?,?,?,?,?)";
				
				//get the prepared statement object
				PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
				
				//stuff the data in the query
				ps.setString(1, beneficiary.getFisrtName());
				ps.setString(2, beneficiary.getLastName());
				ps.setString(3, beneficiary.getAccountNumber());
				ps.setString(4, beneficiary.getBank());
				ps.setString(5, beneficiary.getBranch());
				ps.setString(6, beneficiary.getLimitOfTransaction());
				ps.setInt(7, id);
				
				//execute query
				ps.executeUpdate();
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
	public int deleteBeneficiary(String beneficiaryAccountNumber, String payeeAccountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		int result = 0;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			//getting the beneficiary id to check if the beneficiary already exist or not
			int beneficiaryId = getBeneficiaryId(beneficiaryAccountNumber,payeeAccountNumber);
			
			//getting the payee account type (as FD account cannot have beneficiaries)
			String payeeAccountType = AccountDAOImpl.getAccount(payeeAccountNumber).getAccountType();
			
			//getting the accountId to which the payee account is connected to check if the entered account number is a valid one
			int accountId = AccountDAOImpl.getAccountId(payeeAccountNumber);
			
			if(payeeAccountType.equals("Fixed_Deposit")) {
				result = -1;
				return result;
			}else if(accountId == 0) {
				//If no such payee account is linked with the logged in customer
				result = -2;
				return result;
			}
			else if(beneficiaryId == 0) {
				result = beneficiaryId;
				return result;
			}	
			else {
				int id = AccountDAOImpl.getAccountId(payeeAccountNumber);
				
				//prepare the query
				String UPDATE_QUERY = "UPDATE beneficiary SET is_delete = ? WHERE accountNum= ? AND acc_Id = ?";
				
				//get the prepared statement object
				PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
				
				//stuff the data in the query
				ps.setInt(1, 1);
				ps.setString(2, beneficiaryAccountNumber);
				ps.setInt(3, id);
				
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
	
	private List<Beneficiary> viewBeneficiaryFromResultSet(ResultSet resultSet) throws SQLException{
		List<Beneficiary> list = new ArrayList<>();
		while(resultSet.next()) {
			Beneficiary beneficiary = new BeneficiaryImpl();
			beneficiary.setFisrtName(resultSet.getString("fname"));
			beneficiary.setLastName(resultSet.getString("lname"));
			beneficiary.setAccountNumber(resultSet.getString("accountNum"));
			beneficiary.setBank(resultSet.getString("bank"));
			beneficiary.setBranch(resultSet.getString("branch"));;
			beneficiary.setLimitOfTransaction(resultSet.getString("limitOfTransaction"));
			
			list.add(beneficiary);
		}
		
		return list;
	}

	@Override
	public List<Beneficiary> viewBeneficiary(String payeeAccountNumber) throws SomethingWentWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Beneficiary> list = null;
		try {
			//connect to database
			connection = DBUtils.connectToDatabase();
			
			int account_id = AccountDAOImpl.getAccountId(payeeAccountNumber);
			
			//prepare the query
			String SELECT_QUERY = "SELECT fname, lname, accountNum, bank, branch, limitOfTransaction FROM beneficiary b INNER JOIN account a ON b.acc_Id = a.id WHERE b.acc_Id = ? AND a.customer_Id = ? AND b.is_delete = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			ps.setInt(1, account_id);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			ps.setInt(3, 0);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("There is no beneficiary account found linked to provided account.");
			}
			
			list = viewBeneficiaryFromResultSet(resultSet);
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
