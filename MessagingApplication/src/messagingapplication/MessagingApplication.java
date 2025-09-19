/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package messagingapplication;
import java.util.Scanner;
/**
 *
 * @author Zain
 */



public class MessagingApplication {

    public static void main(String[] args) {
        Login loginSystem = new Login();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Messaging Application ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = getIntInput(scanner);
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    handleRegistration(loginSystem, scanner);
                    break;
                case 2:
                    handleLogin(loginSystem, scanner);
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    private static void handleRegistration(Login loginSystem, Scanner scanner) {
        boolean registrationSuccessful = false;
        
        while (!registrationSuccessful) {
            System.out.println("\n=== Registration ===");
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            
            String username = getValidInput(loginSystem, scanner, "username", 
                "Username must contain '_' and be ≤5 characters! Try again: ",
                (login, input) -> login.checkUserName(input));
            
            String password = getValidInput(loginSystem, scanner, "password", 
                "Password must be ≥8 chars with capital, number, and special character! Try again: ",
                (login, input) -> login.checkPasswordComplexity(input));
            
            String cellphone = getValidInput(loginSystem, scanner, "cellphone", 
                "Cellphone must start with '+27' and have 9 digits! Try again: ",
                (login, input) -> login.checkCellPhoneNumber(input));
            
            String regMessage = loginSystem.registerUser(firstName, lastName, username, password, cellphone);
            System.out.println(regMessage);
            
            if (regMessage.equals("User registered successfully!")) {
                registrationSuccessful = true;
                System.out.println("Registration completed successfully!");
            } else {
                System.out.println("Let's try registration again...");
            }
        }
    }
    
    @FunctionalInterface
    interface ValidationFunction {
        boolean validate(Login login, String input);
    }
    
    private static String getValidInput(Login loginSystem, Scanner scanner, String fieldName, 
                                      String errorMessage, ValidationFunction validator) {
        while (true) {
            System.out.print("Enter " + fieldName + ": ");
            String input = scanner.nextLine();
            if (validator.validate(loginSystem, input)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }
    
    private static void handleLogin(Login loginSystem, Scanner scanner) {
        boolean loginSuccessful = false;
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (!loginSuccessful && attempts < MAX_ATTEMPTS) {
            System.out.println("\n=== Login ===");
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();
            
            String loginMessage = loginSystem.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginMessage);
            
            if (loginMessage.contains("Welcome")) {
                loginSuccessful = true;
                System.out.println("Login successful! You can now use the chat app.");
            } else {
                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    System.out.println("Attempt " + attempts + " of " + MAX_ATTEMPTS + " - Try again!");
                } else {
                    System.out.println("Too many failed attempts. Returning to main menu.");
                }
            }
        }
    }
}
