/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

/**
 *
 * @author alewis91
 */
public class Person {
    String firstName, lastName, phoneNumber, email;
    boolean isActive;
    
    public Person (String f, String l, String p, String e, boolean isAct)
    {
        firstName = f;
        lastName = l;
        phoneNumber = p;
        email = e;
        isActive=isAct;
    }
    public void setFirstName(String s)
    {
        firstName = s;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setLastName(String s)
    {
        lastName = s;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setPhoneNumber(String s)
    {
        phoneNumber = s;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setEmail(String s)
    {
        email = s;
    }
    public String getEmail()
    {
        return email;
    }
}
