/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ase_a2_part.pkg4;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for SystemClass
 */
public class SystemClassTest {

    private SystemClass systemClass;

    @Before
    public void setUp() {
        systemClass = new SystemClass(); // Initialize the system class before each test
    }
    
    @BeforeEach
public void resetAdvertisementIDs() {
    Advertisement.resetAdvertIDs();
}

    @Test
    public void testValidateAdvertDetailsWithNegativeSize() {
        Advertisement advert = new Advertisement(132, -376, "Center", "3 Installments", "Validated", "Advertiser B advertiser2@gmail.com");
       try {
        // Attempt to validate the advertisement
        systemClass.validateAdvertDetails(advert);

        fail("Expected IllegalArgumentException for negative advertisement size, but none was thrown.");
    } catch (IllegalArgumentException e) {
        // Verify that the exception message matches the expected error message
        assertEquals("Invalid",e.getMessage());
    }
    }
       
    @Test
    public void testValidateAdvertDetailsWithPositiveSize() {
         Advertisement.resetAdvertIDs(); // Clear IDs from previous tests
         Advertisement advert = new Advertisement(132, 376, "Center", "3 Installments", "Validated", "Advertiser B advertiser2@gmail.com");

        // Assert that no exception is thrown and validation returns true
        boolean result = systemClass.validateAdvertDetails(advert);
        assertTrue(result);
        assertEquals("Validated", advert.getStatus());
    }
}
