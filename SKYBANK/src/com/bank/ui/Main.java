package com.bank.ui;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.bank.consoleColors.ConsoleColors;
import com.bank.exception.InvalidInputException;

public class Main {
	
	private static UserUI userUI;
	private static AccountUI accountUI;
	private static BeneficiaryUI beneficiaryUI;
	private static TransactionUI transactionUI;
	
	
	
	//private static UserUi userUI;
	public static void displayAtmServices(Scanner sc) {
		
		System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+======================================================+"+"\n"
				+"|  Fixed Deposit                                       |"+"\n"
				+"+======================================================+"+"\n"
				+"|                                                      |"+"\n"
				+"|  1. Block ATM Card                                   |"+"\n"
				+"|  2. ATM Pin Generation                               |"+"\n"
				+"|  3. Request Debit Card                               |"+"\n"
				+"|  4. ATM Card Limit                                   |"+"\n"
				+"|  5. View Linked ATM cards                            |"+"\n"
				+"|  6. Enable/Disable Debit Card for Different Channels |"+"\n"
				+"|  7. Exit                                             |"+"\n"
				+"|                                                      |"+"\n"
				+"+======================================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		int choice = 0;
		do {
			choice = sc.nextInt();
			switch(choice) {
			   case 1:
				   //displaymyAccountAndProfile(sc);
				   break;
			   case 2:
				   //paymentsAndTransfer(sc);
				   break;
			   case 3:
				   //fixedDeposit(sc);
				   break;
			   case 4:
				   //eservices(sc);
				   break;
			   case 5:
				   //logout(sc);
				   break;
			   case 6:
				   //logout(sc);
				   break;	   
			   case 7:
				   return;
			   default:
				   System.out.println("Invalid selection, try again.");
			}
		}while( choice != 0);
	}
	
	public static void displayEServices(Scanner sc) {
		
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+===============================================+"+"\n"
				+"|  e-Services                                   |"+"\n"
				+"+===============================================+"+"\n"
				+"|                                               |"+"\n"
				+"|  1. Pan Registration                          |"+"\n"
				+"|  2. Aadhar Registration                       |"+"\n"
				+"|  3. Enable Account for UPI                    |"+"\n"
				+"|  4. Disable Account for UPI                   |"+"\n"
				+"|  5. Exit                                      |"+"\n"
				+"|                                               |"+"\n"
				+"+===============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
		String choice = "";
		do {
			choice = sc.next();
			
		if(choice.equals("1")) {
			userUI.panRegistration();
		}
		else if(choice.equals("2")) {
			userUI.aadharRegistration();
		}
		else if(choice.equals("3")) {
			accountUI.enableUPI();
		}
		else if(choice.equals("4")) {
			 accountUI.disableUPI();
		}
		else if(choice.equals("5")) {
			break;
		}
		else {
			System.out.println("Invalid Selection, try again");
		}
		}while(choice.equals("0") == false);
	}
	
	public static void displayFixedDeposit(Scanner sc) {
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+================================================+"+"\n"
				+"|  Fixed Deposit                                 |"+"\n"
				+"+================================================+"+"\n"
				+"|                                                |"+"\n"
				+"|  1.  New Fixed Deposit                         |"+"\n"
				+"|  2.  New Recurring Deposit                     |"+"\n"
				+"|  3.  View all FD/RD                            |"+"\n"
				+"|  4.  View all FD                               |"+"\n"
				+"|  5.  View all RD                               |"+"\n"
				+"|  6.  View FD using account number              |"+"\n"
				+"|  7.  View RD using account number              |"+"\n"
				+"|  8.  Partial Closure FD                        |"+"\n"
				+"|  9.  Partial Closure RD                        |"+"\n"
				+"|  10. Exit                                      |"+"\n"
				+"|                                                |"+"\n"
				+"+================================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
		String choice = "";
		do {
			choice = sc.next();
			
		if(choice.equals("1")) {
			accountUI.addAccountFD();
		}
		else if(choice.equals("2")) {
			accountUI.addAccountRD();
		}
		else if(choice.equals("3")) {
			accountUI.viewAccountFDRD();
		}
		else if(choice.equals("4")) {
			accountUI.viewAccountFD();
		}
		else if(choice.equals("5")) {
			accountUI.viewAccountRD();
		}
		else if(choice.equals("6")) {
			accountUI.viewAccountFDAccount();
		}
		else if(choice.equals("7")) {
			accountUI.viewAccountRDAccount();
		}
		else if(choice.equals("8")) {
			accountUI.partialCloseFD();  
		}
		else if(choice.equals("9")) {
			accountUI.partialCloseRD();   
		}
		else if(choice.equals("10")) {
			break;
		}
		else {
			System.out.println("Invalid Selection, try again");
		}
		}while(choice.equals("0") == false);
		
	}
	
	public static void displayPaymentsAndTransfer(Scanner sc) {
		
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+===============================================+"+"\n"
				+"|  Payments & Transfers                         |"+"\n"
				+"+===============================================+"+"\n"
				+"|                                               |"+"\n"
				+"|  1. Quick Transfer(Without Adding Beneficiary)|"+"\n"
				+"|  2. Transfer (To Added Beneficiary)           |"+"\n"
				+"|  3. Deposit Money                             |"+"\n"
				+"|  4. Add Beneficiary                           |"+"\n"
				+"|  5. Delete Beneficiary (Particular Account)   |"+"\n"
				+"|  6. Exit                                      |"+"\n"
				+"|                                               |"+"\n"
				+"+===============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
		
		String choice = "";
		do {
			choice = sc.next();
			
		if(choice.equals("1")) {
			transactionUI.quickTransfer();
		}
		else if(choice.equals("2")) {
			transactionUI.transferToAddedBeneficiary();
		}
		else if(choice.equals("3")) {
			transactionUI.depositMoneyToAccount();
		}
		else if(choice.equals("4")) {
			beneficiaryUI.addBeneficiary();
		}
		else if(choice.equals("5")) {
			beneficiaryUI.deleteBeneficiary();
		}
		else if(choice.equals("6")) {
			break;
		}
		else {
			System.out.println("Invalid Selection, try again");
		}
	   }while(choice.equals("0") == false);
	}
	
	public static void displaymyAccountAndProfile(Scanner sc) {
		
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+=============================================+"+"\n"
				+"|  My Account & Profile                       |"+"\n"
				+"+=============================================+"+"\n"
				+"|                                             |"+"\n"
				+"|  1.  View Profile                           |"+"\n"
				+"|  2.  Update Profile                         |"+"\n"
				+"|  3.  Account Summary                        |"+"\n"
				+"|  4.  Register for e-statement               |"+"\n"
				+"|  5.  Transaction History                    |"+"\n"
				+"|  6.  Change Password                        |"+"\n"
				+"|  7.  Add Beneficiary                        |"+"\n"
				+"|  8.  Delete Beneficiary (particular account)|"+"\n"
				+"|  9.  View Beneficiary (particular account)  |"+"\n"
				+"|  10. Exit                                   |"+"\n"
				+"|                                             |"+"\n"
				+"+=============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
		String choice = "";
		
		do {
			choice = sc.next();
			
		if(choice.equals("1")) {
			userUI.viewProfile();
		}
		else if(choice.equals("2")) {
			userUI.updateProfile();
		}
		else if(choice.equals("3")) {
			accountUI.accountSummary();
		}
		else if(choice.equals("4")) {
			accountUI.eStatement();
		}
		else if(choice.equals("5")) {
			transactionUI.checkTransactionHistory();
		}
		else if(choice.equals("6")) {
			userUI.changePassword();
		}
		else if(choice.equals("7")) {
			beneficiaryUI.addBeneficiary();
		}
		else if(choice.equals("8")) {
			 beneficiaryUI.deleteBeneficiary();   
		}
		else if(choice.equals("9")) {
			beneficiaryUI.viewBeneficiary();	   
		}
		else if(choice.equals("10")) {
			break;   
		}
		else {
			System.out.println("Invalid Selection, try again");
		}
		}while(choice.equals("0") == false);
	}
	
	public static void displayCustomerMenu() {
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+============================================+"+"\n"
				+"|  SKY BANK - Customer Dashboard             |"+"\n"
				+"+============================================+"+"\n"
				+"|                                            |"+"\n"
				+"|  1. My Account & Profile                   |"+"\n"
				+"|  2. Payments & Transfers                   |"+"\n"
				+"|  3. Fixed Deposit                          |"+"\n"
				+"|  4. e-services                             |"+"\n"
				+"|  5. Close Account                          |"+"\n"
				+"|  6. Delete Account                         |"+"\n"
				+"|  7. Logout                                 |"+"\n"
				+"|                                            |"+"\n"
				+"+============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
	}
	
	public static void existingCustomer(Scanner sc) {
		if(!userUI.login())
		   return;
		
		String choice = "";
		do {
			displayCustomerMenu();
			choice = sc.next();
			
		if(choice.equals("1")) {
			displaymyAccountAndProfile(sc);
		}
		else if(choice.equals("2")) {
			displayPaymentsAndTransfer(sc);
		}
		else if(choice.equals("3")) {
			displayFixedDeposit(sc);
		}
		else if(choice.equals("4")) {
			displayEServices(sc);
		}
		else if(choice.equals("5")) {
			accountUI.closeAccount();
		}
		else if(choice.equals("6")) {
			userUI.deleteUser();
			   try{
					Thread.sleep(2000);
					userUI.logout();
					customerPortal(sc);
				}catch(InterruptedException ex) {
					
				}
				//no break statement here i.e. after deletion of user account, logout will also take place
		}
		else if(choice.equals("7")) {
			userUI.logout();
			customerPortal(sc);
		}
		else {
			System.out.println("Invalid Selection, try again");
		}
	   }while(choice.equals("0") == false);
		
	}
	
	public static void displayAccountantMenu() {
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+============================================+"+"\n"
				+"|  SKY BANK - Accountant Dashboard           |"+"\n"
				+"+============================================+"+"\n"
				+"|                                            |"+"\n"
				+"|  1. View all customers                     |"+"\n"
				+"|  2. View particular customer               |"+"\n"
				+"|  3. View all accounts                      |"+"\n"
				+"|  4. View particular account detail         |"+"\n"
				+"|  5. Inoperative accounts                   |"+"\n"
				+"|  6. View all inoperative accounts          |"+"\n"
				+"|  7. View all closed accounts               |"+"\n"
				+"|  8. Transaction report for a day           |"+"\n"
				+"|  9. High magnitude transaction data        |"+"\n"
				+"|  10. Logout                                |"+"\n"
				+"|                                            |"+"\n"
				+"+============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
	}
	
	public static void accountantMenu(Scanner sc) {
		String choice = "";
		do {
			displayAccountantMenu();
			choice = sc.next();
			System.out.println();
			
		if(choice.equals("1")) {
			userUI.viewAllCustomers();
		}
		else if(choice.equals("2")) {
			userUI.viewParticularCustomer();
		}
		else if(choice.equals("3")) {
			accountUI.viewAllAccounts();
		}
		else if(choice.equals("4")) {
			accountUI.viewParticularAccount();
		}
		else if(choice.equals("5")) {
			accountUI.makeInoperative();
		}
		else if(choice.equals("6")) {
			  accountUI.viewAllInoperativeAccounts();
		}
		else if(choice.equals("7")) {
			accountUI.viewAllClosedAccounts();
		}
		else if(choice.equals("8")) {
			transactionUI.transactionsDayRange();	   
		}
		else if(choice.equals("9")) {
			transactionUI.highMagnitudeTransaction();	   
		}
		else if(choice.equals("10")) {
			displayMainMenu(sc);	   
		}
		else {
			System.out.println("Invalid Selection, try again");
		}

	  }while(choice.equals("0") == false);
		
	}
	
	public static void customerPortal(Scanner sc) {
		String choice = "";
		
		do {
			System.out.println();
			System.out.println(ConsoleColors.BLUE_BOLD
					+"+============================================+"+"\n"
					+"|                  SKY BANK                  |"+"\n"
					+"+============================================+"+"\n"
					+"|                                            |"+"\n"
					+"|  1. Existing Customer Login                |"+"\n"
					+"|  2. New Customer Registration              |"+"\n"
					+"|  0. Exit                                   |"+"\n"
					+"|                                            |"+"\n"
					+"+============================================+"+"\n"
					+ConsoleColors.RESET);
			
			System.out.println();
			System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
			choice = sc.next();
			if(choice.equals("0")) {
				System.out.println("Thank you, Visit again");
				break;
			}
			else if(choice.equals("1")) {
				existingCustomer(sc);
			}
			else if(choice.equals("2")) {
				int result = userUI.addUser();
				//the below function will run even if above does not complete due to some errors
				if(result==0) displayAfterRegistrationMenu(sc);
			}
			else {
				System.out.println("Invalid Selection, try again");
			}
		}while(choice.equals("0") == false);
		
		sc.close();
	}
	public static void displayAfterRegistrationMenu(Scanner sc) {
		String choice = "";
			System.out.println();
			System.out.println(ConsoleColors.BLUE_BOLD
					+"+============================================+"+"\n"
					+"|                  SKY BANK                  |"+"\n"
					+"+============================================+"+"\n"
					+"|                                            |"+"\n"
					+"|  1. Saving Account                         |"+"\n"
					+"|  2. Fixed Deposit Account                  |"+"\n"
					+"|  3. Recurring Deposit Account              |"+"\n"
					+"|  0. Exit                                   |"+"\n"
					+"|                                            |"+"\n"
					+"+============================================+"+"\n"
					+ConsoleColors.RESET);
			
			System.out.println();
			System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
			choice = sc.next();
			if(choice.equals("0")) {
				System.out.println("Thank you, Visit again");
			}
			else if(choice.equals("1")) {
				accountUI.addAccountSaving();
				userUI.logout();
				customerPortal(sc);
			}
			else if(choice.equals("2")) {
				  accountUI.addAccountFD();
				  userUI.logout();
				  customerPortal(sc);
			}
			else if(choice.equals("3")) {
				   accountUI.addAccountRD();
				   userUI.logout();
				   customerPortal(sc);
			}
			else {
				System.out.println("Invalid Selection, try again");
			}
			
	}
	
	static void accountantLogin(Scanner sc) {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		System.out.println();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			accountantMenu(sc);
		}else {
			System.out.println("Invalid Username and Password");
		}
	}
	
	public static void displayMainMenu(Scanner sc){
			String choice = "";
			
			do {
				System.out.println();
				System.out.println(ConsoleColors.BLUE_BOLD
						+"+============================================+"+"\n"
						+"|                  SKY BANK                  |"+"\n"
						+"+============================================+"+"\n"
						+"|                                            |"+"\n"
						+"|  1. Accountant portal                      |"+"\n"
						+"|  2. Customer portal                        |"+"\n"
						+"|  0. Exit                                   |"+"\n"
						+"|                                            |"+"\n"
						+"+============================================+"+"\n"
						+ConsoleColors.RESET);
				
				System.out.println();
				System.out.print(ConsoleColors.GREEN_BOLD+"Please enter your choice: "+ConsoleColors.RESET);
				choice = sc.next();
				System.out.println();
				if(choice.equals("0")) {
					 System.out.println("Thank you, Visit again");
				}
				else if(choice.equals("1")) {
					accountantLogin(sc);
				}
				else if(choice.equals("2")) {
					customerPortal(sc);
				}else {
					System.out.println("Invalid Selection, try again");
				}
			}while(choice.equals("0") == false);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		userUI = new UserUI(sc);
		accountUI = new AccountUI(sc);
		beneficiaryUI = new BeneficiaryUI(sc);
		transactionUI = new TransactionUI(sc);
		
		displayMainMenu(sc);
		
		sc.close();
	}
}
