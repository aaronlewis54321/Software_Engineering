/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron
 */
public class InactiveUser {

    int userId;
    String schedule;
    public boolean noLongerInactive;

    public InactiveUser(int p, String s) {
        userId = p;
        schedule = s;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getUserId() {
        return userId;
    }

    public String print() {
        return userId + "," + schedule;
    }

    public boolean noLongerInactive() {
        try {
            BufferedReader br = null;

            String localDir = System.getProperty("user.dir");
            br = new BufferedReader(new FileReader(localDir + "\\src\\Resources\\inactiveUsers.txt"));
            String line;
            try {
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String scheduledString = line.substring(line.lastIndexOf(",") + 1);
                    System.out.println("scheduled time: "+ scheduledString);
                    
                    Instant scheduledTime = Instant.parse(scheduledString);
                    System.out.println("scheduled time: "+ scheduledTime);

                    Instant reactivate = scheduledTime.plusSeconds(600);
                    System.out.println("reactivate time: "+ reactivate);

                    Instant now = Instant.now();
                    System.out.println("now time: "+ now);

                    if (now.isAfter(reactivate)) {
                        noLongerInactive = true;
                    } else {
                        noLongerInactive = false;
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(InactiveUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InactiveUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return noLongerInactive;
    }
}
