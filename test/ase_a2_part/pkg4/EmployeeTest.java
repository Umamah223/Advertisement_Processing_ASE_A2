/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ase_a2_part.pkg4;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;


/**
 *
 * @author hp
 */
public class EmployeeTest {
    
    public EmployeeTest() {
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
    public void testEmployeeConstructorAndGetters() {
    // Create an Employee instance
     Employee employee = new Employee(101, "James Dashner", 5);

    // Verify that the fields are correctly initialized
    assertEquals(101, employee.getEmployeeID());  // Check employee ID
    assertEquals("James Dashner", employee.getName()); // Check name
    assertEquals(5, employee.getDeptID());        // Check department ID
}
}
