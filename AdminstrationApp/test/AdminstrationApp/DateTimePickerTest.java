/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
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
public class DateTimePickerTest {
    
    public DateTimePickerTest() {
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
     * Test of alignColumnCountWithFormat method, of class DateTimePicker.
     */
    @Test
    public void testAlignColumnCountWithFormat() {
        System.out.println("alignColumnCountWithFormat");
        DateTimePicker instance = new DateTimePicker();
        instance.alignColumnCountWithFormat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateTimeValue method, of class DateTimePicker.
     */
    @Test
    public void testGetDateTimeValue() {
        System.out.println("getDateTimeValue");
        DateTimePicker instance = new DateTimePicker();
        LocalDateTime expResult = null;
        LocalDateTime result = instance.getDateTimeValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateTimeValue method, of class DateTimePicker.
     */
    @Test
    public void testSetDateTimeValue() {
        System.out.println("setDateTimeValue");
        LocalDateTime dateTimeValue = null;
        DateTimePicker instance = new DateTimePicker();
        instance.setDateTimeValue(dateTimeValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dateTimeValueProperty method, of class DateTimePicker.
     */
    @Test
    public void testDateTimeValueProperty() {
        System.out.println("dateTimeValueProperty");
        DateTimePicker instance = new DateTimePicker();
        ObjectProperty<LocalDateTime> expResult = null;
        ObjectProperty<LocalDateTime> result = instance.dateTimeValueProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFormat method, of class DateTimePicker.
     */
    @Test
    public void testGetFormat() {
        System.out.println("getFormat");
        DateTimePicker instance = new DateTimePicker();
        String expResult = "";
        String result = instance.getFormat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatProperty method, of class DateTimePicker.
     */
    @Test
    public void testFormatProperty() {
        System.out.println("formatProperty");
        DateTimePicker instance = new DateTimePicker();
        ObjectProperty<String> expResult = null;
        ObjectProperty<String> result = instance.formatProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFormat method, of class DateTimePicker.
     */
    @Test
    public void testSetFormat() {
        System.out.println("setFormat");
        String format = "";
        DateTimePicker instance = new DateTimePicker();
        instance.setFormat(format);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
