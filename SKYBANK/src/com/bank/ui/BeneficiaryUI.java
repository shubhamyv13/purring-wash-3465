package com.bank.ui;

import java.util.List;
import java.util.Scanner;
import com.bank.dao.BeneficiaryDAO;
import com.bank.dao.BeneficiaryDAOImpl;
import com.bank.dto.Account;
import com.bank.dto.AccountImpl;
import com.bank.dto.Beneficiary;
import com.bank.dto.BeneficiaryImpl;
import com.bank.exception.NoRecordFoundException;
import com.bank.exception.SomethingWentWrongException;

public class BeneficiaryUI {
	
	private BeneficiaryDAO beneficiaryDAO;
	private Scanner scanner;
	
	public BeneficiaryUI(Scanner scanner) {
		this.beneficiaryDAO = new BeneficiaryDAOImpl();
		this.scanner = scanner;
	}

	public void addBeneficiary() {
		//code to take input of the new user
		System.out.print("Enter first name: ");
		String fname = scanner.next();
		System.out.print("Enter last name: ");
		String lname = scanner.next();
		System.out.print("Enter beneficiary account number: ");
		String accountNumber = scanner.next();
		System.out.print("Re-enter beneficiary's account number: ");
		String accountNumberAgain = scanner.next();
		System.out.print("Enter beneficiary's bank: ");
		String bank = scanner.next();
		System.out.print("Enter beneficiary's branch: ");
		String branch = scanner.next();
		System.out.print("Enter limit of transaction: ");
		String limitOfTransaction = scanner.next();
		System.out.print("Enter your account number: ");
		String accountNum = scanner.next();
        
		
		if(!accountNumber.equals(accountNumberAgain) ) {
			System.out.println("Account number and re-entered account number does not matches.");
		}else {
			//create object for user with all details
	        
	        Beneficiary beneficiary = new BeneficiaryImpl(fname,lname, accountNumber, bank,branch, limitOfTransaction, new AccountImpl(null, null,null,null,accountNum,null,0));
			
	        try {
	        	int result = beneficiaryDAO.addBeneficiary(beneficiary);
	        	if(result == 0 )System.out.println("User registered successfully.");
	        	else if(result == -1) System.out.println("FD account cannon have beneficiaries.");
	        	else if(result == -2) System.out.println("No such account exists.");
	        	else System.out.println("Beneficiary already registered to this accout number");
	        	
	        }catch(SomethingWentWrongException | NoRecordFoundException ex) {
	        	System.err.println(ex);
	        }
		}
	}
	
	public void deleteBeneficiary() {
		//code to take input of the new user
		
		System.out.print("Enter beneficiary account number: ");
		String beneficiaryAccountNumber = scanner.next();
		System.out.print("Enter payee account number: ");
		String payeeAccountNum = scanner.next();
			
	    try {
	       int result = beneficiaryDAO.deleteBeneficiary(beneficiaryAccountNumber,payeeAccountNum);
	       if(result == -1) System.out.println("FD account cannot have beneficiaries.");
	       else if(result == -2) System.out.println("No such account exists.");
	       else if(result == 0 )System.out.println("No such beneficiary account exists.");
	       else System.out.println("Beneficiary account deleted successfully.");
	        	
	    }catch(SomethingWentWrongException | NoRecordFoundException ex) {
	        	System.err.println(ex);
	    }
	}
	
	public void viewBeneficiary() {
		
		System.out.println("Enter the account number: ");
		String payeeAccountNumber = scanner.next();
		
		try {
			List<Beneficiary> listBeneficiaryData = beneficiaryDAO.viewBeneficiary(payeeAccountNumber);
			listBeneficiaryData.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}
}
