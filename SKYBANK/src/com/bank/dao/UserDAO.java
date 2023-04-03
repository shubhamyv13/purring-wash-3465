package com.bank.dao;

import java.util.List;

import com.bank.dto.User;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public interface UserDAO {

	//for user side
	public void addUser(User user) throws SomethingWentWrongException;
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;
	public void logout();
	public List<User> viewProfile() throws SomethingWentWrongException, NoRecordFoundException;
	public void updateUser(User user) throws SomethingWentWrongException;
	public void changePassword(String oldPassword, String newPassword)throws SomethingWentWrongException, NoRecordFoundException;
	public void deleteUser() throws SomethingWentWrongException, NoRecordFoundException;
	void updateUserPan(String pan) throws SomethingWentWrongException;
	void updateUserAadhar(String aadhar) throws SomethingWentWrongException;
	
	
	//for accountant side
	
	public List<User> viewAllCustomers() throws SomethingWentWrongException, NoRecordFoundException;
	public List<User> viewParticularCustomer(String customerNumber) throws SomethingWentWrongException, NoRecordFoundException;
	
}
