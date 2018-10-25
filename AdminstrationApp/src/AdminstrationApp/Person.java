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
    String first_name, last_name, phone_number, email, birth_date, id;
    boolean isActive;
    
    public Person (String f, String l, String p, String e, String b, String i)
    {
        first_name = f;
        last_name = l;
        phone_number = p;
        email = e;
        birth_date = b;
        id = i;
    }
    
    public String toString()
    {
        return "Name: " + first_name + " " + last_name + ", Phone Number: " + phone_number + ", Email: " + email + ", Birthdate: " + birth_date;
    }
    
    
    public void setFirstName(String s)
    {
        first_name = s;
    }
    public String getFirstName()
    {
        return first_name;
    }
    public void setLastName(String s)
    {
        last_name = s;
    }
    public String getLastName()
    {
        return last_name;
    }
    public void setPhoneNumber(String s)
    {
        phone_number = s;
    }
    public String getPhoneNumber()
    {
        return phone_number;
    }
    public void setEmail(String s)
    {
        email = s;
    }
    public String getEmail()
    {
        return email;
    }
    public void setBirthDate(String s)
    {
        birth_date = s;
    }
    public String getBirthDate()
    {
        return birth_date;
    }
    public String getUserID()
    {
        return id;
    }
}
