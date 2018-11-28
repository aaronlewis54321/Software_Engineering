/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matt
 */
public class InactiveUserTest {
    
    public InactiveUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSchedule method, of class InactiveUser.
     */
    @Test
    public void testGetSchedule() {
        System.out.println("getSchedule");
        InactiveUser instance = null;
        String expResult = "";
        String result = instance.getSchedule();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserId method, of class InactiveUser.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        InactiveUser instance = null;
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class InactiveUser.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        InactiveUser instance = null;
        String expResult = "";
        String result = instance.print();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of noLongerInactive method, of class InactiveUser.
     */
    @Test
    public void testNoLongerInactive() {
        System.out.println("noLongerInactive");
        InactiveUser instance = null;
        boolean expResult = false;
        boolean result = instance.noLongerInactive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
