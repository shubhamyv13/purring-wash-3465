package com.bank.dto;

import java.time.LocalDate;

public class UserImpl implements User{

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String eMail;
	private String dateOfBirth;
	private String address;
	private String securityQuestion;
	private LocalDate registrationDate;
	private String username;
	private String password;
	private String customerId;
	private String aadhar = "";
	private String pan = "";
	
	public UserImpl() {}
	
	public UserImpl(String firstName, String lastName, String mobileNumber, String eMail, String dateOfBirth,
			String address, String securityQuestion, LocalDate registrationDate, String username, String password,
			String customerId, String aadhar, String pan) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.eMail = eMail;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.securityQuestion = securityQuestion;
		this.registrationDate = registrationDate;
		this.username = username;
		this.password = password;
		this.customerId = customerId;
		this.aadhar = aadhar;
		this.pan = pan;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	@Override
	public String toString() {
		return "FirstName= " + firstName + ", LastName= " + lastName + ", MobileNumber= " + mobileNumber + ", eMail= "
				+ eMail + ", DateOfBirth= " + dateOfBirth + ", Address= " + address + ", SecurityQuestion= "
				+ securityQuestion + ", CustomerId= " + customerId + "\n";
	}
	
}
