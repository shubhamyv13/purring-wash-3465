package com.bank.dto;

import java.time.LocalDate;

public interface User {

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getMobileNumber();

	public void setMobileNumber(String mobileNumber);

	public String geteMail();

	public void seteMail(String eMail);

	public String getDateOfBirth();

	public void setDateOfBirth(String dateOfBirth);

	public String getAddress();

	public void setAddress(String address);

	public String getSecurityQuestion();

	public void setSecurityQuestion(String securityQuestion);

	public LocalDate getRegistrationDate();

	public void setRegistrationDate(LocalDate registrationDate);

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public String getCustomerId();

	public void setCustomerId(String customerId);
	
	public String getAadhar();

	public void setAadhar(String aadhar);

	public String getPan();

	public void setPan(String pan);
}
