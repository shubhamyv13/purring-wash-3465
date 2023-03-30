package com.bank.dao;

import java.util.List;

import com.bank.dto.User;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public interface UserDAO {

	//for user side
	void addUser(User user) throws SomethingWentWrongException;
	void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;
	public void logout();
	public List<User> viewProfile() throws SomethingWentWrongException, NoRecordFoundException;
	public void updateUser(User user) throws SomethingWentWrongException;
	
}
