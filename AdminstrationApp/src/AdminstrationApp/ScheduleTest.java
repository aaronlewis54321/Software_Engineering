/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class ScheduleTest {
    
    public static void main(String args[]) throws IOException{
     
     
     Model test = new Model();
     ArrayList<Integer> users = new ArrayList<Integer>();
     users.add(1);users.add(2);
     Schedule schedule = new Schedule("2018-10-24T17:12:43.640Z",1,users);
     test.writeScheduleToDatabase(schedule);
    
    }
    
}
