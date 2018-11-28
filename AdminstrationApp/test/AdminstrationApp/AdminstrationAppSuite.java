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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Matt
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AdminstrationApp.InactiveUserTest.class, AdminstrationApp.ControllerTest.class, AdminstrationApp.ResponseTest.class, AdminstrationApp.ScheduleTest.class, AdminstrationApp.GroupTest.class, AdminstrationApp.ScheduleTestTest.class, AdminstrationApp.ModelTest.class, AdminstrationApp.PersonTest.class, AdminstrationApp.DateTimePickerTest.class, AdminstrationApp.ViewTest.class, AdminstrationApp.PersonGSONTest.class})
public class AdminstrationAppSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
