/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ase_a2_part.pkg4;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hp
 */
public class ProcessingCenterTest {
    
    public ProcessingCenterTest() {
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
public void testStoreAdvertisement() {
    // Arrange
    ProcessingCenter processingCenter = new ProcessingCenter();
    Advertisement advert = new Advertisement(101, 500, "Top Banner", "2 Installments", "Accepted", "Advertiser A advertiser@example.com");
    
    // Act
    processingCenter.storeAdvertisement(advert);
    
    // Assert
    List<Advertisement> storedAdvertisements = processingCenter.getStoredAdvertisements();
    assertNotNull(storedAdvertisements); // Ensure the list is not null
    assertEquals(1, storedAdvertisements.size()); // Ensure the list contains one advertisement
    assertEquals(advert, storedAdvertisements.get(0)); // Ensure the advertisement is stored correctly
}

@org.junit.Test
public void testStoreMultipleAdvertisements() {
    // Arrange
    ProcessingCenter processingCenter = new ProcessingCenter();
    Advertisement advert1 = new Advertisement(101, 500, "Top Banner", "2 Installments", "Accepted", "Advertiser A advertiser@example.com");
    Advertisement advert2 = new Advertisement(102, 600, "Sidebar", "3 Installments", "Accepted", "Advertiser B advertiser2@example.com");
    
    // Act
    processingCenter.storeAdvertisement(advert1);
    processingCenter.storeAdvertisement(advert2);
    
    // Assert
    List<Advertisement> storedAdvertisements = processingCenter.getStoredAdvertisements();
    assertNotNull(storedAdvertisements); // Ensure the list is not null
    assertEquals(2, storedAdvertisements.size()); // Ensure both advertisements are stored
    assertTrue(storedAdvertisements.contains(advert1)); // Ensure advert1 is in the list
    assertTrue(storedAdvertisements.contains(advert2)); // Ensure advert2 is in the list
}
}
