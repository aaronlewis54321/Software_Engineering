/*
 * TODO:
 * getPeopleFromDatabase method needs to be implemented
 * WritePeopleToDatabse method needs to be implemented
 * readGroupsFromDatabase method needs to be implemented
 * writeGroupsToDatabase method needs to be implemented
 * writeResponseToCSV method needs to be implemented
 */
package AdminstrationApp;

import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;

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
    
    public Model() {
        propertySupport = new PropertyChangeSupport(this);
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
    public void getPeopleFromDatabase() {
        
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
    public void writeResponseToCSV() {
        
    }
    
    public ArrayList getPeople()
    {
        return people;
    }
    
}
