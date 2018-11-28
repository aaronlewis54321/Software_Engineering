package AdminstrationApp;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matt
 */
public class Schedule {
    
    private String send_at;
    private int message;  
    private ArrayList<Integer> users;
//    transient boolean sent;
    
    //POJO for JSON
    public Schedule(String time, int x, ArrayList<Integer> y){
        send_at = time;
        message = x;
        users = y;
    }
    
    @Override
    public String toString(){
        return "Send User: " + getUsers() + ", Message: " + getMessage() + ", at time: " + getSend_at();
    }

    /**
     * @return the send_at
     */
    public String getSend_at() {
        return send_at;
    }

    /**
     * @param send_at the send_at to set
     */
    public void setSend_at(String send_at) {
        this.send_at = send_at;
    }

    /**
     * @return the message
     */
    public int getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     * Seemingly unnecessary at this point as we only have 1 message...
     */
    public void setMessage(int message) {
        this.message = message;
    }

    /**
     * @return the users
     */
    public ArrayList<Integer> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(ArrayList<Integer> users) {
        this.users = users;
    }
    
    
}