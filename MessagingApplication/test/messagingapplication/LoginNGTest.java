/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package messagingapplication;

import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Zain
 */


public class LoginNGTest {
    
    private Login login;
    
    @BeforeMethod
    public void setUp() {
        login = new Login();
    }
    
    @Test
    public void testCheckUserNameValid() {
        assertTrue(login.checkUserName("a_b"));
        assertTrue(login.checkUserName("user_"));
    }
    
    @Test
    public void testCheckUserNameInvalid() {
        assertFalse(login.checkUserName("username"));
        assertFalse(login.checkUserName("user"));
    }
    
    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(login.checkPasswordComplexity("Passw0rd!"));
        assertTrue(login.checkPasswordComplexity("A1@bcdefg"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(login.checkPasswordComplexity("weak"));
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue(login.checkCellPhoneNumber("+27821234567"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        assertFalse(login.checkCellPhoneNumber("0821234567"));
        assertFalse(login.checkCellPhoneNumber("+27123"));
    }
    
    @Test
    public void testRegisterUserSuccess() {
        String result = login.registerUser("John", "Doe", "j_doe", "Passw0rd!", "+27821234567");
        assertEquals(result, "User registered successfully!");
    }
    
    @Test
    public void testRegisterUserBadUsername() {
        String result = login.registerUser("John", "Doe", "badname", "Passw0rd!", "+27821234567");
        assertTrue(result.contains("Username is not correctly formatted"));
    }
    
    @Test
    public void testLoginUserSuccess() {
        login.registerUser("Test", "User", "t_usr", "Test123!", "+27829876543");
        assertTrue(login.loginUser("t_usr", "Test123!"));
    }
    
    @Test
    public void testLoginUserFailure() {
        login.registerUser("Test", "User", "t_usr", "Test123!", "+27829876543");
        assertFalse(login.loginUser("t_usr", "wrongpass"));
    }
}

 
