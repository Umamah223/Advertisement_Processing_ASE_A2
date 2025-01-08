/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ase_a2_part.pkg4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author hp
 */
public class AdvertiserTest {
    
    public AdvertiserTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

      
    @org.junit.Test
    public void testConstructor() {
        Advertiser instance = new Advertiser(1, "Test Advertiser", "test@example.com");
        assertEquals(1, instance.getAdvertiserID());
        assertEquals("Test Advertiser", instance.getAdvertiserName());
        assertEquals("test@example.com", instance.getContactInfo());
}
    
    /**
     * Test of getAdvertiserID method, of class Advertiser.
     */
    @org.junit.Test
    public void testGetAdvertiserID() {
        Advertiser instance = new Advertiser(1, "Test Advertiser", "test@example.com");
        int expResult = 1;
        int result = instance.getAdvertiserID();
    assertEquals(expResult, result);
}

    /**
     * Test of getAdvertiserName method, of class Advertiser.
     */
    @org.junit.Test
    public void testGetAdvertiserName() {
        Advertiser instance = new Advertiser(1, "Test Advertiser", "test@example.com");
        String expResult = "Test Advertiser";
        String result = instance.getAdvertiserName();
        assertEquals(expResult, result);
}

    /**
     * Test of getContactInfo method, of class Advertiser.
     */
    @org.junit.Test
    public void testGetContactInfo() {
         Advertiser instance = new Advertiser(1, "Test Advertiser", "test@example.com");
         String expResult = "test@example.com";
         String result = instance.getContactInfo();
         assertEquals(expResult, result);
}


    @org.junit.Test
    public void testSubmitAdvertValid() {
        
         Advertiser instance = new Advertiser(1, "Kevin", "kevin@advertiser.com");
         Advertisement advert = new Advertisement(102, 253, "Top Left", "50$ Four Installments", "Submitted", "Advertiser : Kevin");
         MarketingDeptEmployee marketingEmployee = new MarketingDeptEmployee(11, "Nick Jonas", 1,"nick@wired.com");
         
          instance.submitAdvert(advert, marketingEmployee);
    
        // Check if the advertisement was successfully added
            assertTrue(instance.hasAdvertisements());
            assertEquals("Submitted", advert.getStatus());
    }
    
    
//Testing the submitAdvert Method with null values
  @org.junit.Test
public void testSubmitAdvertInvalid() {
    Advertiser instance = new Advertiser(1, "Kate", "kate@advertiser.com");
    Advertisement advert = new Advertisement(103, 395, " ", "50$ Four Installments", "Submitted", "Advertiser : Kevin");
    MarketingDeptEmployee marketingEmployee = new MarketingDeptEmployee(11, "Nick Jonas", 1, "nick@wired.com");
    
    // This will cause the test to fail if the exception is not thrown
    instance.submitAdvert(advert, marketingEmployee);
}

}

