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
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aaron and MattyR
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

                    // use comma as separator for ISO 8601 substring parse
                    String scheduledString = line.substring(line.lastIndexOf(",") + 1);
                    Instant scheduledTime = Instant.parse(scheduledString);
                    Instant reactivate = scheduledTime.plusSeconds(600); //10 minutes after scheduled
                    Instant now = Instant.now();

                    //if now is more than 10 mins post schedule reactivate user
                    if (now.isAfter(reactivate)) {
                        noLongerInactive = true;
                    } else {
                        noLongerInactive = false; //else don't
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
