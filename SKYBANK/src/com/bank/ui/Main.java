package com.bank.ui;
import java.util.Scanner;

import com.bank.consoleColors.ConsoleColors;

public class Main {
	
	private static UserUI userUI;
	private static AccountUI accountUI;
	private static BeneficiaryUI beneficiaryUI;
	
	
	
	//private static UserUi userUI;
	public static void displayAtmServices(Scanner sc) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
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
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+===============================================+"+"\n"
				+"|  Fixed Deposit                                |"+"\n"
				+"+===============================================+"+"\n"
				+"|                                               |"+"\n"
				+"|  1. ATM Card Services                         |"+"\n"
				+"|  2. Loans                                     |"+"\n"
				+"|  3. Pan Registration                          |"+"\n"
				+"|  4. Aadhar Registration                       |"+"\n"
				+"|  5. Enable Account for UPI                    |"+"\n"
				+"|  6. Disable Account for UPI                   |"+"\n"
				+"|  7. Exit                                      |"+"\n"
				+"|                                               |"+"\n"
				+"+===============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		int choice = 0;
		do {
			choice = sc.nextInt();
			switch(choice) {
			   case 1:
				   displayAtmServices(sc);
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
	
	public static void displayFixedDeposit(Scanner sc) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD
				+"+===============================================+"+"\n"
				+"|  Fixed Deposit                                |"+"\n"
				+"+===============================================+"+"\n"
				+"|                                               |"+"\n"
				+"|  1. New Fixed Deposit                         |"+"\n"
				+"|  2. View All FD                               |"+"\n"
				+"|  3. Recurring FD                              |"+"\n"
				+"|  4. Partial Closure FD                        |"+"\n"
				+"|  5. Exit                                      |"+"\n"
				+"|                                               |"+"\n"
				+"+===============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		int choice = 0;
		do {
			choice = sc.nextInt();
			switch(choice) {
			   case 1:
				   accountUI.addAccountFD();
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
				   return;
			   default:
				   System.out.println("Invalid selection, try again.");
			}
		}while( choice != 0);
	}
	
	public static void displayPaymentsAndTransfer(Scanner sc) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
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
				+"|  5. Delete Beneficiary                        |"+"\n"
				+"|  6. Exit                                      |"+"\n"
				+"|                                               |"+"\n"
				+"+===============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
		int choice = 0;
		do {
			choice = sc.nextInt();
			switch(choice) {
			   case 1:
				   //accountUI.addAccountFD();
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
				   return;
			   default:
				   System.out.println("Invalid selection, try again.");
			}
		}while( choice != 0);
	}
	
	public static void displaymyAccountAndProfile(Scanner sc) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
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
		int choice = 0;
		do {
			choice = sc.nextInt();
			switch(choice) {
			   case 1:
				   userUI.viewProfile();
				   break;
			   case 2:
				   userUI.updateProfile();
				   break;
			   case 3:
				   accountUI.accountSummary();
				   break;
			   case 4:
				   accountUI.eStatement();
				   break;
			   case 5:
				   //logout(sc);
				   break;
			   case 6:
				   userUI.changePassword();
				   break;
			   case 7:
				   beneficiaryUI.addBeneficiary();
				   break;	
			   case 8:
				   beneficiaryUI.deleteBeneficiary();
				   break;	 
			   case 9:
				   beneficiaryUI.viewBeneficiary();
				   break;	   
			   case 10:
				   return;
			   default:
				   System.out.println("Invalid selection, try again.");
			}
		}while( choice != 0);
	}
	
	public static void displayCustomerMenu() {
		System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
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
				+"|  8. Exit                                   |"+"\n"
				+"|                                            |"+"\n"
				+"+============================================+"+"\n"
				+ConsoleColors.RESET);
		
		System.out.println();
	}
	
	public static void existingCustomer(Scanner sc) {
		if(!userUI.login())
		   return;
		
		int choice = 0;
		do {
			displayCustomerMenu();
			choice = sc.nextInt();
			switch(choice) {
			   case 1:
				   displaymyAccountAndProfile(sc);
				   break;
			   case 2:
				   displayPaymentsAndTransfer(sc);
				   break;
			   case 3:
				   displayFixedDeposit(sc);
				   break;
			   case 4:
				   displayEServices(sc);
				   break;
			   case 5:
				   accountUI.closeAccount();
				   break;
			   case 6:
				   userUI.deleteUser();
				   try{
						Thread.sleep(2000);
						userUI.logout();
						customerPortal(sc);
					}catch(InterruptedException ex) {
						
					}
					//no break statement here i.e. after deletion of user account, logout will also take place
			   case 7:
				   userUI.logout();
				   break;	   
			   case 8:
				   return;
			   default:
				   System.out.println("Invalid selection, try again.");
			}
		}while( choice != 0);
		
	}
	
	public static void customerPortal(Scanner sc) {
		int choice = 0;
		
		do {
			System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
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
			choice = sc.nextInt();
			switch(choice) {
			   case 0:
				   System.out.println("Thank you, Visit again");
				   break;
			   case 1:
				   existingCustomer(sc);
				   break;
			   case 2:
				   int result = userUI.addUser();
				   //the below function will run even if above does not complete due to some errors
				   if(result==0) displayAfterRegistrationMenu(sc);
				   break;
			   default:
				   System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		
		sc.close();
	}
	public static void displayAfterRegistrationMenu(Scanner sc) {
		int choice = 0;
			System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option to create one of the following accounts to use our services ***"+ConsoleColors.RESET);
			System.out.println();
			System.out.println(ConsoleColors.BLUE_BOLD
					+"+============================================+"+"\n"
					+"|                  SKY BANK                  |"+"\n"
					+"+============================================+"+"\n"
					+"|                                            |"+"\n"
					+"|  1. Saving Account                         |"+"\n"
					+"|  2. Fixed Deposit Account                  |"+"\n"
					+"|  0. Exit                                   |"+"\n"
					+"|                                            |"+"\n"
					+"+============================================+"+"\n"
					+ConsoleColors.RESET);
			
			System.out.println();
			choice = sc.nextInt();
			switch(choice) {
			   case 0:
				   System.out.println("Thank you, Visit again");
				   break;
			   case 1:
				   accountUI.addAccountSaving();
				   userUI.logout();
				   customerPortal(sc);
				   break;
			   case 2:
				   //userUI.addUser();
				   break;
			   default:
				   System.out.println("Invalid Selection, try again");
			}
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		userUI = new UserUI(sc);
		accountUI = new AccountUI(sc);
		beneficiaryUI = new BeneficiaryUI(sc);
		int choice = 0;
		
		do {
			System.out.println(ConsoleColors.GREEN_BOLD+"*** Please choose Option ***"+ConsoleColors.RESET);
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
			choice = sc.nextInt();
			switch(choice) {
			   case 0:
				   System.out.println("Thank you, Visit again");
				   break;
			   case 1:
				   displayAfterRegistrationMenu(sc);
				   break;
			   case 2:
				   customerPortal(sc);
				   break;
			   default:
				   System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		
		sc.close();
	}
}
