/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package messagingapplication;

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



public class UserNGTest {
    
    @Test
    public void testUserCreation() {
        User user = new User("John", "Doe", "j_doe", "Passw0rd!", "+27821234567");
        
        assertEquals(user.getFirstName(), "John");
        assertEquals(user.getLastName(), "Doe");
        assertEquals(user.getUsername(), "j_doe");
        assertEquals(user.getPassword(), "Passw0rd!");
        assertEquals(user.getCellphone(), "+27821234567");
    }
    
    @Test
    public void testUserGetters() {
        User user = new User("Alice", "Smith", "a_smi", "Alice1!", "+27823334455");
        
        assertNotNull(user.getFirstName());
        assertNotNull(user.getLastName());
        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertNotNull(user.getCellphone());
    }
}
