/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ase_a2_part.pkg4;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class MarketingDeptEmployeeTest {
     private MarketingDeptEmployee marketingEmployee;
    
    public MarketingDeptEmployeeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
     @Before
    public void setUp() {
        // Create a MarketingDeptEmployee instance before each test
        marketingEmployee = new MarketingDeptEmployee(201, "Nick Jonas", 2, "nick.jonas@marketing.com");
    }

    
     @After
    public void tearDown() {
        // Clean up after each test (if necessary)
        marketingEmployee = null;
    }
    
     @Test
    public void testConstructorAndGetters() {
        // Verify that the constructor correctly initializes both inherited and specific properties
        assertEquals(101, marketingEmployee.getEmployeeID());  // Check employee ID
        assertEquals("Nick Jonas", marketingEmployee.getName()); // Check name
        assertEquals(2, marketingEmployee.getDeptID());        // Check department ID
        assertEquals("nick.jonas@marketing.com", marketingEmployee.getContactInfo()); // Check contact info
    }
    
     @Test
    public void testEnterAdvertisementDetails() {
        // Create an Advertisement instance to pass to the method
        Advertisement advert = new Advertisement(103, 500, "New Ad", "50% off on items", "Submitted", "Advertiser: Nick Jonas");
        marketingEmployee.enterAdvertisementDetails(advert); 
    }
}
    
    
