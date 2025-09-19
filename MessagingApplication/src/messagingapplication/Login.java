/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package messagingapplication;
import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 *
 * @author Zain
 */


public class Login {
    private ArrayList<User> users;
    
    public Login() {
        this.users = new ArrayList<>();
    }
    
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasNumber = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber(String cellphone) {
        return cellphone.matches("^\\+27[0-9]{9}$");
    }
    
    public String registerUser(String firstName, String lastName, String username, String password, String cellphone) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellphone)) {
            return "Cellphone number is not correctly formatted. Please ensure it starts with '+27' and has 9 additional digits.";
        }
        
        User newUser = new User(firstName, lastName, username, password, cellphone);
        users.add(newUser);
        return "User registered successfully!";
    }
    
    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public String returnLoginStatus(String username, String password) {
        boolean success = loginUser(username, password);
        if (success) {
            User user = getUserByUsername(username);
            if (user != null) {
                return "Welcome " + user.getFirstName() + ", " + user.getLastName() + " it is great to see you again.";
            }
        }
        return "Username or password incorrect, please try again.";
    }
    
    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }
}
