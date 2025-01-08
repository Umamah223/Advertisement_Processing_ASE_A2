/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ase_a2_part.pkg4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author hp
 */
public class EditorTest {
    private Editor editor;
    private Advertisement advert;
    private SystemClass systemClass;

    
    public EditorTest() {
    }
    
   @BeforeClass
    public static void setUpClass() {
        // Initialize static resources before all tests
    }

    @Before
    public void setUp() {
        // Initialize objects before each test
        editor = new Editor(301, "Alina Starkov", 3, "alina.starkov@example.com");
        advert = new Advertisement(132, 376, "Center", "3 Installments", "Validated", "Advertiser B advertiser2@gmail.com");
        systemClass = new SystemClass();
        
        Advertisement.resetAdvertIDs(); // Reset the advert IDs to avoid conflicts
    }
    
     // Testing with the Advertisement Status set to "Submitted" instead of "Validated"
    @Test
    public void testReviewAdvertisement_NotValidated() {
  
        advert.setStatus("Submitted"); // Status is "Submitted" instead of "Validated"
        
        // Call the reviewAdvertisement method
        try {
            editor.reviewAdvertisement(advert, systemClass);
            fail("Expected IllegalArgumentException due to invalid advertisement status");
        } catch (IllegalArgumentException e) {
            assertEquals("Advertisement must be validated by the System before being reviewed by the Editor", e.getMessage());
        }
    }

    // Testing with the Validated Advertisement Status
    @Test
    public void testReviewAdvertisement_Validated() {
        advert.setStatus("Validated"); // Status is set to "Validated"

        // Review Advertisement
        try {
            editor.reviewAdvertisement(advert, systemClass);
        } catch (IllegalArgumentException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    }
  
   
   

