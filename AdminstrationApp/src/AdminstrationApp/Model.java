/*
 * TODO:
 * WritePeopleToDatabse method needs to be implemented
 * readGroupsFromDatabase method needs to be implemented
 * writeGroupsToDatabase method needs to be implemented
 */
package AdminstrationApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.beans.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    public final String PUT_USERS_URL = "http://emoji-survey.me/auth/users";

    public final String POST_SCHEDULE_URL = "http://emoji-survey.me/schedule/";

    public final String TOKEN = "d9ed1ecac123cae16e6e1a0b565762786bef301f";

    private static final String USER_AGENT = "Mozilla/5.0";

    public Model() throws IOException {
        propertySupport = new PropertyChangeSupport(this);
        people = new ArrayList<Person>();
        getPeopleFromDatabase();
        //ReadGroupsFromDatabase();
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
    public void getPeopleFromDatabase() throws IOException {
        URL obj = new URL(GET_USERS_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String auth = "Token " + TOKEN;
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", auth);
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
            for (int i = 0; i < response.length(); i++) {
                if (response.charAt(i) == '[') {
                    hitBracket = true;
                    x = i + 1;
                }
                if (!hitBracket) {
                    continue;
                }
                if (response.length() - 1 != i && response.charAt(i) == ',' && response.charAt(i - 1) == '}' && response.charAt(i + 1) == '{') {
                    a.add(response.substring(x, i));
                    x = i + 1;
                }
                if (response.charAt(i) == ']') {
                    a.add(response.substring(x, i));
                    break;
                }
            }
            Gson g = new Gson();
            for (int i = 0; i < a.size(); i++) {
                PersonGSON p = g.fromJson(a.get(i), PersonGSON.class);
                people.add(new Person(p.first_name, p.last_name, p.phone_number, p.email, p.birth_date, p.id));
            }

        } else {
            System.out.println("GET request not worked");
        }

    }

    //This method writes all people with changed elements to the database
    public void writePeopleToDatabase(ArrayList<Person> p) throws IOException {

        String auth = "Token " + TOKEN;
        for (Person pers : p) {
            URL obj = new URL(PUT_USERS_URL + "/" + pers.getUserID());
            System.out.println(PUT_USERS_URL + "/" + pers.getUserID());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Authorization", auth);
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write("{\"last_name\" : \"nameTest\"}");
           //System.out.println("{\"last_name\" : \"nameTest\", \"birth_date\" : \"05/05/1998\", \"first_name\" : \"firstname\", \"birth_date\" : \"05/05/1998\","
            //+ " \"last_name\" : \"nameTest\", \"birth_date\" : \"05/05/1998\"}");
            out.close();
            System.out.println(con.getResponseCode());
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response);
            } else {
                System.out.println("PUT request not worked");
            }
        }
    }

    //This method goes to our local database and reads in all existing groups
    private void ReadGroupsFromDatabase() {

    }

    //This method writes changes to groups to the local database
    private void writeGroupsToDatabase() {

    }

    //This method uses the CSVUtils class to write response data to a CSV file.
    //For example on usage of CSVUtils class, see Sprint1/Tester/Research how data 
    //will be exported to CSV in the project github.
    public void writeResponseToCSV() throws IOException {
        URL obj = new URL(GET_RESPONSES_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String auth = "Token " + TOKEN;
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", auth);
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
            for (int i = 0; i < response.length(); i++) {
                if (response.charAt(i) == '[') {
                    hitBracket = true;
                    x = i + 1;
                }
                if (!hitBracket) {
                    continue;
                }
                if (response.length() - 1 != i && response.charAt(i) == ',' && response.charAt(i - 1) == '}' && response.charAt(i + 1) == '{') {
                    a.add(response.substring(x, i));
                    x = i + 1;
                }
                if (response.charAt(i) == ']') {
                    a.add(response.substring(x, i));
                    break;
                }
            }

            Gson g = new Gson();
            ArrayList<Response> responses = new ArrayList<Response>();
            for (int i = 0; i < a.size(); i++) {
                Response p = g.fromJson(a.get(i), Response.class);
                responses.add(p);
            }

            for (Response p : responses) {
                System.out.println(p);
            }

            String localDir = System.getProperty("user.dir");
            BufferedWriter writer = new BufferedWriter(new FileWriter(localDir + "\\src\\Resources\\responses.csv"));
            writer.write("emoji, timestamp");
            writer.newLine();
            for (int i = 0; i < responses.size(); i++) {
                writer.write(responses.get(i) + "");
                writer.newLine();
            }
            writer.flush();

        } else {
            System.out.println("GET request not worked");
        }

    }

    public ArrayList<Person> getPeople() {
        return people;
    }

//show detailed log for http connection debugging
//static {
//        ConsoleHandler handler = new ConsoleHandler();
//        handler.setLevel(Level.ALL);
//        Logger log = LogManager.getLogManager().getLogger("");
//        log.addHandler(handler);
//        log.setLevel(Level.ALL);
//    }
    
//This method takes a schedule as input and writes it to the database
    public void writeScheduleToDatabase(Schedule schedule) throws IOException {

        URL url = new URL(POST_SCHEDULE_URL);

        //postConn, url obj to POST schedule
        HttpURLConnection postConn = (HttpURLConnection) url.openConnection();
        String auth = "Token " + TOKEN;
        postConn.setRequestMethod("POST"); //implicitly declared when setDoOutput(true)
        postConn.setRequestProperty("Authorization", auth);
        postConn.setRequestProperty("Content-Type", "application/json");
        postConn.setRequestProperty("Accept", "application/json");
        postConn.setDoOutput(true);
        postConn.setDoInput(true);

        
        Gson gson = new GsonBuilder().create();
        String scheduleJson = gson.toJson(schedule);
//        System.out.println(scheduleJson);

        /**
         * POSTing *
         */
        OutputStream output = postConn.getOutputStream();
        OutputStreamWriter outWriter = new OutputStreamWriter(output, "UTF-8");
        postConn.connect();

//            outWriter.write("{  \"send_at\": \"2018-10-24T17:12:43.640Z\",  \"message\": 1,  \"users\": [1]}");
        outWriter.write(scheduleJson);
        outWriter.flush();

        int status = postConn.getResponseCode();//this cannot be invoked before data stream is ready when performing HTTP POST

        //reading in response from endpoint
        if (status == 201) {
            BufferedReader br = new BufferedReader(new InputStreamReader(postConn.getInputStream()));
            String content = br.readLine();
            System.out.println(content);

        } else {
            System.out.println("Invalid HTTP response status "
                    + "code " + status + " from web service server.");
        }

    }

    //test for writing schedule to database, need to modify to alter time/message based on controllers
    public void scheduleUsers(ArrayList<Integer> people, String time) throws IOException {

        Model test = new Model();
        Schedule schedule = new Schedule(time, 1, people);
        test.writeScheduleToDatabase(schedule);

    }

    ArrayList<Integer> inactiveUsers = new ArrayList<Integer>();
    public void makeUsersInactive(ArrayList<Integer> people) {
        for(Integer p : people)
        {
            inactiveUsers.add(p);
        }
    }
    public ArrayList<Integer> getInactiveUsers()
    {
        return inactiveUsers;
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
