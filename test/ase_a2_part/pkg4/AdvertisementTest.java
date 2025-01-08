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
public class AdvertisementTest {

    // Testing the Advertisement Class with valid inputs
    @Test
    public void testAdvertisementConstructor() {
        Advertisement ad = new Advertisement(101, 200, "Sidebar", "Prepaid", "Validated", "Advertiser B advertiser2@gmail.com");
        assertEquals(101, ad.getAdvertID());
        assertEquals(200, ad.getSize());
        assertEquals("Sidebar", ad.getPlacementPreferences());
        assertEquals("Prepaid", ad.getPaymentTerms());
        assertEquals("Validated", ad.getStatus());
        assertEquals("Advertiser B advertiser2@gmail.com", ad.getAdvertiserInfo());
    }

}

