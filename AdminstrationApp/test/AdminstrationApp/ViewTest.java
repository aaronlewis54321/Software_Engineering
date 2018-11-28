/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.util.ArrayList;
import javafx.stage.Stage;
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
public class ViewTest {
    
    public ViewTest() {
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
     * Test of start method, of class View.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        Stage primaryStage = null;
        View instance = new View();
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class View.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        View.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refreshTable method, of class View.
     */
    @Test
    public void testRefreshTable() {
        System.out.println("refreshTable");
        View instance = new View();
        instance.refreshTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScheduledUsers method, of class View.
     */
    @Test
    public void testGetScheduledUsers() {
        System.out.println("getScheduledUsers");
        View instance = new View();
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getScheduledUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearScheduledUsers method, of class View.
     */
    @Test
    public void testClearScheduledUsers() {
        System.out.println("clearScheduledUsers");
        View instance = new View();
        instance.clearScheduledUsers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSchedule method, of class View.
     */
    @Test
    public void testGetSchedule() {
        System.out.println("getSchedule");
        View instance = new View();
        String expResult = "";
        String result = instance.getSchedule();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determineScheduledUsers method, of class View.
     */
    @Test
    public void testDetermineScheduledUsers() {
        System.out.println("determineScheduledUsers");
        View instance = new View();
        instance.determineScheduledUsers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
