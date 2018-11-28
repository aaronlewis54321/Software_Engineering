/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.util.ArrayList;
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
public class ScheduleTest {
    
    public ScheduleTest() {
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
     * Test of toString method, of class Schedule.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Schedule instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSend_at method, of class Schedule.
     */
    @Test
    public void testGetSend_at() {
        System.out.println("getSend_at");
        Schedule instance = null;
        String expResult = "";
        String result = instance.getSend_at();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSend_at method, of class Schedule.
     */
    @Test
    public void testSetSend_at() {
        System.out.println("setSend_at");
        String send_at = "";
        Schedule instance = null;
        instance.setSend_at(send_at);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class Schedule.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        Schedule instance = null;
        int expResult = 0;
        int result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class Schedule.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        int message = 0;
        Schedule instance = null;
        instance.setMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Schedule.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Schedule instance = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsers method, of class Schedule.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        ArrayList<Integer> users = null;
        Schedule instance = null;
        instance.setUsers(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
