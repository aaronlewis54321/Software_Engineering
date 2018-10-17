/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

/**
 *
 * @author aaron
 */


public class Response {
    String emoji, ts;
    
    
    public Response(String e, String t){
        emoji = e;
        ts = t;
        
    }
    
    public String toString(){
        return emoji + ", " + ts;
    }
    
}
