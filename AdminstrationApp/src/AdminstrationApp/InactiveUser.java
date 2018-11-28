/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author aaron
 */
public class InactiveUser {
    int userId;
    String schedule;
    public InactiveUser(int p, String s) {
        userId = p;
        schedule = s;
    }
    
    public String getSchedule()
    {
        return schedule;
    }
    
    public int getUserId()
    {
        return userId;
    }
    
    public String print()
    {
        return userId + "," + schedule;
    }
    
    public Boolean noLongerInactive()
    {
        GregorianCalendar sched = new GregorianCalendar();  //Need to add timezone stuff
        Scanner scan = new Scanner(schedule);
        String year = "" + schedule.charAt(0) + schedule.charAt(1) + schedule.charAt(2) + schedule.charAt(3);
        
        String month = "" + schedule.charAt(5) + schedule.charAt(6);
        
        String day = "" + schedule.charAt(8) + schedule.charAt(9);
       
        String hour = "" + schedule.charAt(11) + schedule.charAt(12);
       
        String minute = "" + schedule.charAt(14) + schedule.charAt(15);
       
        String second = "" + schedule.charAt(17) + schedule.charAt(18);
       if(Integer.parseInt(minute) < 50)
       {
        sched.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute)+10, Integer.parseInt(second));
       }
       else {
           sched.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hour)+1, Integer.parseInt(minute)-50, Integer.parseInt(second));
       }
        GregorianCalendar now = new GregorianCalendar();
        
        if (now.after(sched))
        {
            return true;
        }
        return false;
    }
}
