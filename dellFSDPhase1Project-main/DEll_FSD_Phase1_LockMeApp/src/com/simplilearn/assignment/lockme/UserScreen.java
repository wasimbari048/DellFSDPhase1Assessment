package com.simplilearn.assignment.lockme;

// added import files to the Project
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.simplilearn.assignmnet.dependencyModel.UserCredentials;
import com.simplilearn.assignmnet.dependencyModel.Users;

public class UserScreen {
	//to Input data in the program from the user
	private static Scanner keyboard;
	private static Scanner input;
	private static Scanner lockerInput;
	//to share requested output data to the user
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	//function to call when to store data.
	private static Users users;
	private static UserCredentials userCredentials;
	
	
	public static void main(String[] args) {
		filecheck();
		welcomeScreen();
		signInOptions();

	}
	public static void signInOptions() {
		System.out.println("1 . New User Registration ");
		System.out.println("2 . Login exisitng user");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				loginUser();
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		keyboard.close();
		input.close();
	}
	
	public static void lockerOptions(String inpUsername) {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS FROM THEAPP ");
		System.out.println("2 . STORE NEW SET OF CREDENTIALS ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
				fetchCredentials(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			default :
				System.out.println("Please select either 1 or 2 as options");
				break;
		}
		lockerInput.close();
	}
	
	public static void registerUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   HELLO USER, WELCOME TO THE REGISTRATION PAGE	*");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		users.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		users.setPassword(password);
		
		output.println(users.getUsername());
		output.println(users.getPassword());
		
		System.out.println("User Registration Successful !");
		output.close();
		
	}
	public static void loginUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO Lock ME APP USER PAGE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		System.out.println("Enter the Username :");
		String inpUsername = keyboard.next();
		boolean found = false;
		while(input.hasNext() && !found) {
			if(input.next().equals(inpUsername)) {
				System.out.println("Enter your Password :");
				String inpPassword = keyboard.next();
				if(input.next().equals(inpPassword)) {
					System.out.println("Login has been successful !");
					found = true;
					lockerOptions(inpUsername);
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found : Login Failure : 404");
		}
		
	}
	
	public static void welcomeScreen() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   hello User, Welcome To LockMe.com		*");
		System.out.println("*   Your Personal Digital Locker by Lockme.com	*");
		System.out.println("*					*");
		System.out.println("==========================================");
		
	}
	//store credentails
	public static void storeCredentials(String loggedInUser) {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER ! STORE YOUR CREDENTIALS HERE	! *");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		userCredentials.setLoggedInUser(loggedInUser);
		
		System.out.println("Enter Site/Domain Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		userCredentials.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		userCredentials.setPassword(password);
		
		lockerOutput.println(userCredentials.getLoggedInUser());
		lockerOutput.println(userCredentials.getSiteName());
		lockerOutput.println(userCredentials.getUsername());
		lockerOutput.println(userCredentials.getPassword());
		
		System.out.println("YOUR CREDS ARE STORED AND SECURED!");
		lockerOutput.close();		
	}
	
	//fetch credentials
	public static void fetchCredentials(String inpUsername) {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
		System.out.println("*   YOUR CREDENTIALS ARE 	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		System.out.println(inpUsername);
		
		
		while(lockerInput.hasNext()) {
//			System.out.println(lockerInput.hasNext());
			if(lockerInput.next().equals(inpUsername)) {
				System.out.println("Site Name: "+lockerInput.next());
				System.out.println("User Name: "+lockerInput.next());
				System.out.println("User Password: "+lockerInput.next());
			}
		}
		
	}
	
	public static void filecheck() {

		File  dbFile = new File("database.txt");
		File  lockerFile = new File("locker-file.txt");
		
		try {
			//read data from db file
			input = new Scanner(dbFile);
			
			//red data from locker file
			lockerInput = new Scanner(lockerFile);
			
			//read data from keyboard
			keyboard = new Scanner(System.in);
			
			//out put 
			output = new PrintWriter( new FileWriter(dbFile,true));
			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));
			
			users = new Users();
			userCredentials  = new UserCredentials();
			
			
		} catch (IOException e) {
			System.out.println("404 : File Not Found Exception ");
		}
		
	}

}

