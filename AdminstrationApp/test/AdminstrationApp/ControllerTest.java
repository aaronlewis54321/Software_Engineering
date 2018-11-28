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
public class ControllerTest {
    
    public ControllerTest() {
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
     * Test of btnExportDataAction method, of class Controller.
     */
    @Test
    public void testBtnExportDataAction() throws Exception {
        System.out.println("btnExportDataAction");
        Controller instance = null;
        instance.btnExportDataAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of btnSubmitAction method, of class Controller.
     */
    @Test
    public void testBtnSubmitAction() throws Exception {
        System.out.println("btnSubmitAction");
        View v = null;
        Controller instance = null;
        instance.btnSubmitAction(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of btnRefreshAction method, of class Controller.
     */
    @Test
    public void testBtnRefreshAction() {
        System.out.println("btnRefreshAction");
        View v = null;
        Controller instance = null;
        instance.btnRefreshAction(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of btnHelpAction method, of class Controller.
     */
    @Test
    public void testBtnHelpAction() {
        System.out.println("btnHelpAction");
        Controller instance = null;
        instance.btnHelpAction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
