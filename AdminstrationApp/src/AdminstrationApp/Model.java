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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author alewis91 and mrinoldodododod
 */
public class Model implements Serializable {

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private String sampleProperty;

    private PropertyChangeSupport propertySupport;

    public ArrayList<Person> people;

    public static boolean postSuccessful;

    public ArrayList<Group> groups;

    public final String GET_USERS_URL = "http://emoji-survey.me/auth/users";

    public final String GET_RESPONSES_URL = "http://emoji-survey.me/responses";

    public final String PUT_USERS_URL = "http://emoji-survey.me/auth/users";

    public final String POST_SCHEDULE_URL = "http://emoji-survey.me/schedule/";

    public final String TOKEN = "d9ed1ecac123cae16e6e1a0b565762786bef301f";

    private static final String USER_AGENT = "Mozilla/5.0";

    boolean getPostBoolean;

    ArrayList<InactiveUser> inactiveUsers = new ArrayList<InactiveUser>();

    public Model() throws IOException {
        propertySupport = new PropertyChangeSupport(this);
        people = new ArrayList<Person>();
        getPeopleFromDatabase();
        readInactiveUsersFromFile();
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
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("HTTP Connection Error");
            alert.setContentText("GET request failed");
        }

    }

    //This method writes all people with changed elements to the database
    public void writePeopleToDatabase(ArrayList<Person> p) throws IOException {

        String auth = "Token " + TOKEN;
        for (Person pers : p) {
            URL obj = new URL(PUT_USERS_URL + "/" + pers.getUserID());
//            System.out.println(PUT_USERS_URL + "/" + pers.getUserID());
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
//            System.out.println(con.getResponseCode());
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

//                System.out.println(response);
            } else {
                System.out.println("PUT request not worked");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("HTTP Error");
                alert.setContentText("PUT request failed to modify database.");
            }
        }
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
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("HTTP Connection Error");
            alert.setContentText("GET request Failed");
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
    public void writeScheduleToDatabase(Schedule schedule) {
        postSuccessful = false;

        try {

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
                postSuccessful = true;
                BufferedReader br = new BufferedReader(new InputStreamReader(postConn.getInputStream()));
                String content = br.readLine();
                System.out.println(content);

            } else {
                System.out.println("Invalid HTTP response status "
                        + "code " + status + " from web service server.");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("HTTP Error");
                alert.setHeaderText("Connection Error");
                alert.setContentText("Post failed: status code " + status + " from web service");

                alert.showAndWait();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //test for writing schedule to database, need to modify to alter time/message based on controllers
    public void scheduleUsers(String time, ArrayList<Integer> people) throws IOException {
        Schedule schedule = new Schedule(time, 1, people);
        writeScheduleToDatabase(schedule);

    }

    public static boolean getPostBoolean() {
        return postSuccessful;
    }

    public void resetPostBool() {
        postSuccessful = false;
    }

    //If schedule is accidentally sent, and we want to reactivate all users
    //clears inactiveUsers.txt
    public void makeAllUsersActive() {
        try {
            inactiveUsers.clear();
            String localDir = System.getProperty("user.dir");
            PrintWriter pw = new PrintWriter(localDir + "\\src\\Resources\\inactiveUsers.txt");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void makeUsersInactive(ArrayList<Integer> people, String schedule) {
        if (postSuccessful) {
            for (Integer p : people) {
                inactiveUsers.add(new InactiveUser(p, schedule));
            }

            writeInactiveUsersToFile();

        } else {
            System.out.println("Please enter a valid schedule in ISO 8601 format");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Scheduling Error");
            alert.setContentText("Please select a valid date and time");
        }
    }

    public ArrayList<InactiveUser> getInactiveUsers() {
        return inactiveUsers;
    }

    private void readInactiveUsersFromFile() {
        BufferedReader br = null;
        try {
            String localDir = System.getProperty("user.dir");
            br = new BufferedReader(new FileReader(localDir + "\\src\\Resources\\inactiveUsers.txt"));
            String line;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] inactiveUsersFromFile = line.split(",");

                InactiveUser t = new InactiveUser(Integer.parseInt(inactiveUsersFromFile[0]), inactiveUsersFromFile[1]);
                inactiveUsers.add(t);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeInactiveUsersToFile() {
        try {

            // Open given file in append mode. 
            String localDir = System.getProperty("user.dir");
            BufferedWriter out = new BufferedWriter(new FileWriter(localDir + "\\src\\Resources\\inactiveUsers.txt"));
            for (InactiveUser i : inactiveUsers) {
                out.write(i.print());
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Missing In Action");
            alert.setContentText("Could not find file inactiveUsers.txt!");

            Exception ex = new FileNotFoundException("Could not find file inactiveUsers.txt");

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        }
    }

    public void makeUsersActive() {
        Iterator<InactiveUser> i = inactiveUsers.iterator();
        while (i.hasNext()) {
            InactiveUser s = i.next();
            if (s.noLongerInactive()) {
                i.remove();
                writeInactiveUsersToFile();
            }
        }

    }

}
