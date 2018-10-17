/*
 * TODO:
 * WritePeopleToDatabse method needs to be implemented
 * readGroupsFromDatabase method needs to be implemented
 * writeGroupsToDatabase method needs to be implemented
 */
package AdminstrationApp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.beans.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author alewis91
 */

public class Model implements Serializable {
    
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public ArrayList<Person> people;
    
    public ArrayList<Group> groups;
    
    public final String GET_USERS_URL = "http://emoji-survey.me/auth/users";
    
    public final String GET_RESPONSES_URL = "http://emoji-survey.me/responses";
    
    public final String TOKEN = "d9ed1ecac123cae16e6e1a0b565762786bef301f";
    
    private static final String USER_AGENT = "Mozilla/5.0";
    
    public Model() throws IOException {
        propertySupport = new PropertyChangeSupport(this);
        people = new ArrayList<Person>();
        getPeopleFromDatabase();
        ReadGroupsFromDatabase();
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    
    //This method is where the code that reads the people from the database and
    //loads each one into people objects that are stored as an arraylist.
    public void getPeopleFromDatabase() throws IOException{
        URL obj = new URL(GET_USERS_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String auth = "Token " + TOKEN;
        con.setRequestMethod("GET");
        con.setRequestProperty ("Authorization", auth);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", "application/json");
        int responseCode = con.getResponseCode();
	//System.out.println("GET Response Code :: " + responseCode);
	     if (responseCode == HttpURLConnection.HTTP_OK) { // success
		BufferedReader in = new BufferedReader(new InputStreamReader(
		con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		    while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		    }
		in.close();
               
                
                String m = response.toString();
                
                
                ArrayList<String> a = new ArrayList<String>();
                int x = 0;
                boolean hitBracket = false;
                for(int i = 0; i <response.length();i++)
                {
                    if(response.charAt(i) == '[')
                    {
                        hitBracket = true;
                        x = i+1;
                    }
                    if(!hitBracket)
                    {
                        continue;
                    }
                    if(response.length() -1 != i && response.charAt(i) == ',' && response.charAt(i-1) == '}' && response.charAt(i+1) == '{' )
                    {
                        a.add(response.substring(x, i));
                        x=i+1;
                    }
                    if(response.charAt(i) == ']')
                    {
                        a.add(response.substring(x, i));
                        break;
                    }
                }
                
                
                Gson g = new Gson();
                for(int i = 0; i < a.size(); i++)
                {
                    Person p = g.fromJson(a.get(i), Person.class);
                    people.add(p);
                }
                
		} else {
			System.out.println("GET request not worked");
		}
            
        
    }
    
    //This method writes all people with changed elements to the database
    public void writePeopleToDatabase() {
        
    }

    //This method goes to our local database and reads in all existing groups
    private void ReadGroupsFromDatabase() {
        
    }
    
    //This method writes changes to groups to the local database
    private void writeGroupsToDatabase(){
        
    }
    
    //This method uses the CSVUtils class to write response data to a CSV file.
    //For example on usage of CSVUtils class, see Sprint1/Tester/Research how data 
    //will be exported to CSV in the project github.
    public void writeResponseToCSV() throws IOException{
        URL obj = new URL(GET_RESPONSES_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String auth = "Token " + TOKEN;
        con.setRequestMethod("GET");
        con.setRequestProperty ("Authorization", auth);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", "application/json");
        int responseCode = con.getResponseCode();
	//System.out.println("GET Response Code :: " + responseCode);
	     if (responseCode == HttpURLConnection.HTTP_OK) { // success
		BufferedReader in = new BufferedReader(new InputStreamReader(
		con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		    while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		    }
		in.close();
               
                
                String m = response.toString();
                
                System.out.println(m);
                
                ArrayList<String> a = new ArrayList<String>();
                int x = 0;
                boolean hitBracket = false;
                for(int i = 0; i <response.length();i++)
                {
                    if(response.charAt(i) == '[')
                    {
                        hitBracket = true;
                        x = i+1;
                    }
                    if(!hitBracket)
                    {
                        continue;
                    }
                    if(response.length() -1 != i && response.charAt(i) == ',' && response.charAt(i-1) == '}' && response.charAt(i+1) == '{' )
                    {
                        a.add(response.substring(x, i));
                        x=i+1;
                    }
                    if(response.charAt(i) == ']')
                    {
                        a.add(response.substring(x, i));
                        break;
                    }
                }
                
                
                Gson g = new Gson();
                ArrayList<Response> responses = new ArrayList<Response>();
                for(int i = 0; i < a.size(); i++)
                {
                    Response p = g.fromJson(a.get(i), Response.class);
                    responses.add(p);
                }
                
                for(Response p : responses)
                {
                    System.out.println(p);
                }
                
                
                
                
                String localDir = System.getProperty("user.dir");
                BufferedWriter writer = new BufferedWriter(new FileWriter(localDir + "\\src\\Resources\\responses.csv"));
                writer.write("emoji, timestamp");
                writer.newLine();
                for(int i = 0; i< responses.size(); i++)
                {
                   writer.write(responses.get(i)+"");
                   writer.newLine();
                }
                writer.flush();
	       
                } 
             
             
                else {
			System.out.println("GET request not worked");
		}
            
        
    }
    
    public ArrayList getPeople()
    {
        return people;
    }
    
    
 /*   
    public void testGetUsers() throws IOException
    {
        
        String token = "d9ed1ecac123cae16e6e1a0b565762786bef301f";
        URL obj = new URL(GET_USERS_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String auth = "Token " + token;
        con.setRequestMethod("GET");
        con.setRequestProperty ("Authorization", auth);
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", "application/json");
        int responseCode = con.getResponseCode();
	System.out.println("GET Response Code :: " + responseCode);
	     if (responseCode == HttpURLConnection.HTTP_OK) { // success
		BufferedReader in = new BufferedReader(new InputStreamReader(
		con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		    while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		    }
		in.close();

	        // print result
	        //System.out.println(response.toString());
                
                
                String m = response.toString();
                
                
                ArrayList<String> a = new ArrayList<String>();
                int x = 0;
                boolean hitBracket = false;
                for(int i = 0; i <response.length();i++)
                {
                    if(response.charAt(i) == '[')
                    {
                        hitBracket = true;
                        x = i+1;
                    }
                    if(!hitBracket)
                    {
                        continue;
                    }
                    if(response.length() -1 != i && response.charAt(i) == ',' && response.charAt(i-1) == '}' && response.charAt(i+1) == '{' )
                    {
                        a.add(response.substring(x, i));
                        x=i+1;
                    }
                }
                
                
                Gson g = new Gson();
                ArrayList<Person> people = new ArrayList<Person>();
                for(int i = 0; i < a.size(); i++)
                {
                    System.out.println(a.get(i)+"\n");
                    Person p = g.fromJson(a.get(i), Person.class);
                    people.add(p);
                }
                
                
                for(Person p : people)
                {
                    System.out.println(p);
                }
                
                
                //Type type = new TypeToken<List<Person>>() {}.getType();
                //ArrayList<Person> p = (ArrayList<Person>) g.fromJson(m, type);
                
                
                //ArrayList<Person> p = g.fromJson(response, ArrayList<Person>.class);
                //JSONParser parser = new JSONParser(); 
                //JSONObject json = (JSONObject) parser.parse(stringToParse);
                
		} else {
			System.out.println("GET request not worked");
		}
            
    } 
*/
}
