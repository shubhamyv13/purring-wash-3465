package com.bank.ui;

import java.util.Scanner;

import com.bank.consoleColors.ConsoleColors;

public class Main {
	
	
	
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
				+"+============================================+"+"\n"
				+"|  My Account & Profile                      |"+"\n"
				+"+============================================+"+"\n"
				+"|                                            |"+"\n"
				+"|  1. View Profile                           |"+"\n"
				+"|  2. Update Profile                         |"+"\n"
				+"|  3. Account Summary                        |"+"\n"
				+"|  4. Register for e-statement               |"+"\n"
				+"|  5. Transaction History                    |"+"\n"
				+"|  6. Change Password                        |"+"\n"
				+"|  7. Add & Manage Beneficiary               |"+"\n"
				+"|  8. Exit                                   |"+"\n"
				+"|                                            |"+"\n"
				+"+============================================+"+"\n"
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
			   case 8:
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
//		if(!userUi.login())
//		   return;
		
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
				   //logout(sc);
				   break;
			   case 6:
				   //logout(sc);
				   break;
			   case 7:
				   //logout(sc);
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
				   //existingCustomer(sc);
				   break;
			   default:
				   System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		
		sc.close();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
				   //accountantLogin(sc);
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
