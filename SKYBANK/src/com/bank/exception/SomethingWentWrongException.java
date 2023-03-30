package com.bank.exception;

public class SomethingWentWrongException extends Exception {

	@Override
	public String toString() {
		return "Some thing went wrong, try again later";
	}

	
}
