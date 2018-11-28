/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

/**
 *
 * @author alewis91 and mRinoldo
 * View got a little messier with a bit more logic than intended but
 * it works so. Yeah. Hopefully helpful comments
 */
public class View extends Application {

    Model m;
    Controller c;
    TableView table;
    BorderPane border;
    ArrayList<Person> people;
    private ArrayList<Integer> scheduledUsers;
    private String asIsoDateTime;

    @Override
    public void start(Stage primaryStage) throws IOException {
        m = new Model();
        c = new Controller(m);
        people = new ArrayList<Person>();
        scheduledUsers = new ArrayList<Integer>();

        Person l = new Person("", "", "", "", "", "4");
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(l);

        //sexy icon
        String localDir = System.getProperty("user.dir");
        primaryStage.getIcons().add(new Image("file:taskbar_image.png"));

        // buttons that do self explanatory things
        Button btnRefresh = new Button();
        btnRefresh.setText("Refresh");
        btnRefresh.setOnAction(e -> c.btnRefreshAction(this));
        btnRefresh.setTooltip(new Tooltip("Refresh Table View"));

        Button btnExportCSV = new Button();
        btnExportCSV.setText("Export Data");
        btnExportCSV.setTooltip(new Tooltip("Export Response Data To CSV"));

        btnExportCSV.setOnAction(e5 -> {
            try {
                c.btnExportDataAction();
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Just in case we need to bring some people back into view
        // We can't delete the actual schedule but we CAN POST a new one
        Button btnReactivateAll = new Button();
        btnReactivateAll.setText("Reactivate all users");
        btnReactivateAll.setOnAction(event -> c.btnReactivateAllAction(this));
        btnReactivateAll.setTooltip(new Tooltip("Reactivate Missing Users"));

        //table creation with columns 
        table = new TableView();
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        firstNameCol.setPrefWidth(176);
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        lastNameCol.setPrefWidth(176);
        TableColumn phoneNumCol = new TableColumn("Phone Number");
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        phoneNumCol.setPrefWidth(176);
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        emailCol.setPrefWidth(176);
        TableColumn scheduleCol = new TableColumn("Schedule");
        //scheduleCol.setCellFactory( tc -> new CheckBoxTableCell<>());
        scheduleCol.setCellValueFactory(new PropertyValueFactory<>("CheckBox"));
        scheduleCol.setStyle("-fx-alignment: CENTER;");
        scheduleCol.setPrefWidth(176);

        table.getColumns().addAll(firstNameCol, lastNameCol, phoneNumCol, emailCol, scheduleCol);

        //Populate the tableview with users from the database
        m.makeUsersActive();
        outerLoop1:
        for (Person p : m.getPeople()) {
            for (InactiveUser i : m.getInactiveUsers()) {
                if (i.getUserId() == Integer.parseInt(p.getUserID())) {
                    continue outerLoop1;
                }
            }
            table.getItems().add(p);
            people.add(p);
        }

        //sweet border
        border = new BorderPane();
        table.setEditable(true);
        /*
         table.getSelectionModel().cellSelectionEnabledProperty().set(true);
         table.setOnKeyPressed(event -> {
         if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
         //This code should determine the current table position, and edit that position with input
         System.out.println("test");
         }
         else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB)
         {
         table.getSelectionModel().selectNext();
         event.consume();
         }
         else if (event.getCode() == KeyCode.LEFT) {
         // work around due to
         // TableView.getSelectionModel().selectPrevious() due to a bug
         // stopping it from working on
         // the first column in the last row of the table
         }
         });
        
         */

        border.setCenter(table);
        VBox vboxLeft = new VBox();
        vboxLeft.setPrefWidth(130);

        //Reduced options buttons to only 3, less is more
        Button[] options = {btnExportCSV, btnRefresh, btnReactivateAll};
        for (int i = 0; i < options.length; i++) {
            options[i].setMinWidth(vboxLeft.getPrefWidth());
            vboxLeft.setMargin(options[i], new Insets(0, 0, 0, 0));
            vboxLeft.getChildren().add(options[i]);
        }

        border.setLeft(vboxLeft);
  
        //Selector for Date/Time scheduling, not designed from scratch
        DateTimePicker schedPicker = new DateTimePicker();
        schedPicker.setDateTimeValue(LocalDateTime.now());  //Default to today

        HBox submitArea = new HBox(10);
        submitArea.setPadding(new Insets(5, 0, 0, 130));
        Button btnSubmit = new Button("Submit");
        btnSubmit.setTooltip(new Tooltip("Send Schedule For Selected Users"));
        btnSubmit.setPrefWidth(100);

        //This logic shouldn't be here, should be in controller/model
        btnSubmit.setOnAction(e -> {
            try {
                //create LDT instance(no timezones), use that to create zoned instance,
                //offset to UTC, format into string for posting
                LocalDateTime ldt = schedPicker.getDateTimeValue();
                ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
                ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneOffset.UTC);
                asIsoDateTime = utcZoned.format(DateTimeFormatter.ISO_DATE_TIME);
                
                //do all the other stuff button is supposed to through controller
                c.btnSubmitAction(this);
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
<<<<<<< HEAD
<<<<<<< HEAD
        txtSchedule = new TextField();
        txtSchedule.setPrefWidth(300);
        
        year = new ComboBox();
        year.setPrefWidth(100);
        ObservableList<String> s = FXCollections.observableArrayList();
        s.add("2018");s.add("2019");s.add("2020");s.add("2021");s.add("2022");s.add("2023");
        year.setItems(s);
        month = new ComboBox();
        month.setPrefWidth(100);
        ObservableList<String> q = FXCollections.observableArrayList();
        q.add("JAN");q.add("FEB");q.add("MAR");q.add("APR");q.add("MAY");q.add("JUN");q.add("JUL");
        q.add("AUG");q.add("SEP");q.add("OCT");q.add("NOV");q.add("DEC");
        month.setItems(q);
        day = new ComboBox();
        ObservableList<String> w = FXCollections.observableArrayList();
        w.add("01");w.add("02");w.add("03");w.add("04");w.add("05");w.add("06");w.add("07");
        w.add("08");w.add("09");w.add("10");w.add("11");w.add("12");
        w.add("13");w.add("14");w.add("15");w.add("16");w.add("17");w.add("18");w.add("19");
        w.add("20");w.add("21");w.add("22");w.add("23");w.add("24");
        w.add("25");w.add("26");w.add("27");w.add("28");w.add("29");w.add("30");w.add("31");
        day.setItems(w);
        day.setPrefWidth(100);
        hour = new TextField();
        hour.setPrefWidth(100);
        minute = new TextField();
        minute.setPrefWidth(100);
        
        
=======
=======
        
>>>>>>> dateTimePicker
        StackPane root = new StackPane(schedPicker);
>>>>>>> dateTimePicker
        Label lblSchedule = new Label("Schedule: ");
        lblSchedule.setPrefHeight(30);
        submitArea.getChildren().addAll(lblSchedule, root, btnSubmit);
        border.setBottom(submitArea);

        Scene scene = new Scene(border, 1013, 600);

        primaryStage.setTitle("Emoji Administration");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    //refresh table settings/columns/values
    void refreshTable() {
        table = new TableView();
        people = new ArrayList<Person>();
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        firstNameCol.setPrefWidth(176);
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        lastNameCol.setPrefWidth(176);
        TableColumn phoneNumCol = new TableColumn("Phone Number");
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        phoneNumCol.setPrefWidth(176);
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        emailCol.setPrefWidth(176);
        TableColumn scheduleCol = new TableColumn("Schedule");
        scheduleCol.setCellValueFactory(new PropertyValueFactory<>("CheckBox"));
        scheduleCol.setStyle("-fx-alignment: CENTER;");
        scheduleCol.setPrefWidth(176);

        table.getColumns().addAll(firstNameCol, lastNameCol, phoneNumCol, emailCol, scheduleCol);
        //table.getItems().add(m.getPeople());

        //repopulate table with users who are not scheduled
        m.makeUsersActive();
        outerLoop:
        for (Person p : m.getPeople()) {
            for (InactiveUser i : m.getInactiveUsers()) {
                if (i.getUserId() == Integer.parseInt(p.getUserID())) {
                    continue outerLoop;
                }
            }
            table.getItems().add(p);
            people.add(p);
            /*
             if (!m.getInactiveUsers().contains(Integer.parseInt(p.getUserID()))) {
             table.getItems().add(p);
             people.add(p);
             }
             */

        }
        border.setCenter(table);
    }

    ArrayList<Integer> getScheduledUsers() {
        return scheduledUsers;
    }

    //more B Logic that probably shouldn't be here
    //clear scheduled users and reset successful post boolean
    void clearScheduledUsers() {
        scheduledUsers.clear();
        m.resetPostBool();
    }

    public String getSchedule() {
        return asIsoDateTime;
    }

    //determine who be selected, who we tryin to schedule
    void determineScheduledUsers() {
        for (Person p : people) {
            if (p.getCheckBox().isSelected()) {
                scheduledUsers.add(Integer.parseInt(p.getUserID()));
            }
        }

    }
}
