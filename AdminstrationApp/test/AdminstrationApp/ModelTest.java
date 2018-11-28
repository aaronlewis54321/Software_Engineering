/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.beans.PropertyChangeListener;
import java.io.IOException;
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
public class ModelTest {
    
    public ModelTest() {
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
     * Test of getSampleProperty method, of class Model.
     */
    @Test
    public void testGetSampleProperty() throws IOException {
        System.out.println("getSampleProperty");
        Model instance = new Model();
        String expResult = "";
        String result = instance.getSampleProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSampleProperty method, of class Model.
     */
    @Test
    public void testSetSampleProperty() throws IOException {
        System.out.println("setSampleProperty");
        String value = "";
        Model instance = new Model();
        instance.setSampleProperty(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPropertyChangeListener method, of class Model.
     */
    @Test
    public void testAddPropertyChangeListener() throws IOException {
        System.out.println("addPropertyChangeListener");
        PropertyChangeListener listener = null;
        Model instance = new Model();
        instance.addPropertyChangeListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePropertyChangeListener method, of class Model.
     */
    @Test
    public void testRemovePropertyChangeListener() throws IOException {
        System.out.println("removePropertyChangeListener");
        PropertyChangeListener listener = null;
        Model instance = new Model();
        instance.removePropertyChangeListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeopleFromDatabase method, of class Model.
     */
    @Test
    public void testGetPeopleFromDatabase() throws Exception {
        System.out.println("getPeopleFromDatabase");
        Model instance = new Model();
        instance.getPeopleFromDatabase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writePeopleToDatabase method, of class Model.
     */
    @Test
    public void testWritePeopleToDatabase() throws Exception {
        System.out.println("writePeopleToDatabase");
        ArrayList<Person> p = null;
        Model instance = new Model();
        instance.writePeopleToDatabase(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeResponseToCSV method, of class Model.
     */
    @Test
    public void testWriteResponseToCSV() throws Exception {
        System.out.println("writeResponseToCSV");
        Model instance = new Model();
        instance.writeResponseToCSV();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeople method, of class Model.
     */
    @Test
    public void testGetPeople() throws IOException {
        System.out.println("getPeople");
        Model instance = new Model();
        ArrayList<Person> expResult = null;
        ArrayList<Person> result = instance.getPeople();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeScheduleToDatabase method, of class Model.
     */
    @Test
    public void testWriteScheduleToDatabase() throws IOException {
        System.out.println("writeScheduleToDatabase");
        Schedule schedule = null;
        Model instance = new Model();
        instance.writeScheduleToDatabase(schedule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scheduleUsers method, of class Model.
     */
    @Test
    public void testScheduleUsers() throws Exception {
        System.out.println("scheduleUsers");
        String time = "";
        ArrayList<Integer> people = null;
        Model instance = new Model();
        instance.scheduleUsers(time, people);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostBoolean method, of class Model.
     */
    @Test
    public void testGetPostBoolean() {
        System.out.println("getPostBoolean");
        boolean expResult = false;
        boolean result = Model.getPostBoolean();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPostBool method, of class Model.
     */
    @Test
    public void testResetPostBool() throws IOException {
        System.out.println("resetPostBool");
        Model instance = new Model();
        instance.resetPostBool();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeUsersInactive method, of class Model.
     */
    @Test
    public void testMakeUsersInactive() throws IOException {
        System.out.println("makeUsersInactive");
        ArrayList<Integer> people = null;
        String schedule = "";
        Model instance = new Model();
        instance.makeUsersInactive(people, schedule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInactiveUsers method, of class Model.
     */
    @Test
    public void testGetInactiveUsers() throws IOException {
        System.out.println("getInactiveUsers");
        Model instance = new Model();
        ArrayList<InactiveUser> expResult = null;
        ArrayList<InactiveUser> result = instance.getInactiveUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeUsersActive method, of class Model.
     */
    @Test
    public void testMakeUsersActive() throws IOException {
        System.out.println("makeUsersActive");
        Model instance = new Model();
        instance.makeUsersActive();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
