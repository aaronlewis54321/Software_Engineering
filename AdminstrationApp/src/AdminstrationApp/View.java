/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Clock;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alewis91
 */
public class View extends Application {

    Button btn;
    Button btnAddUsers;
    Button btnEditGroups;
    Button btnApplyChanges;
    Button btnRevertChanges;
    Button btnExportCSV;
    ComboBox year;
    ComboBox month;
    ComboBox day;
    TextField hour;
    TextField minute;
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

        String localDir = System.getProperty("user.dir");
        primaryStage.getIcons().add(new Image("file:taskbar_image.png"));

        Button btnRefresh = new Button();
        btnRefresh.setText("Refresh");
        btnRefresh.setOnAction(e -> c.btnRefreshAction(this));

        Button btnExportCSV = new Button();
        btnExportCSV.setText("Export Data");
        btnExportCSV.setOnAction(e5 -> {
            try {
                c.btnExportDataAction();
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

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
        //table.getItems().add(m.getPeople());
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

        //btn.setMinWidth(130);
        //border.setLeft(btn);
        VBox vboxLeft = new VBox();
        vboxLeft.setPrefWidth(130);

        Button[] options = {btnExportCSV, btnRefresh};  //{btnHelp, btnAddUsers, btnEditGroups, btnApplyChanges, btnRevertChanges, btnExportCSV};
        for (int i = 0; i < options.length; i++) {
            options[i].setMinWidth(vboxLeft.getPrefWidth());
            vboxLeft.setMargin(options[i], new Insets(0, 0, 0, 0));
            vboxLeft.getChildren().add(options[i]);
        }

        border.setLeft(vboxLeft);
        //border.setRight(border.getRight(),btnEditUsers);
        //root.getChildren().add(table);

        //border.setLeft(vboxLeft);
        border.setLeft(vboxLeft);
        //border.setRight(border.getRight(),btnEditUsers);
        //root.getChildren().add(table);

        //border.setLeft(vboxLeft);
        DateTimePicker schedPicker = new DateTimePicker();
        schedPicker.setDateTimeValue(LocalDateTime.now());

        HBox submitArea = new HBox(10);
        submitArea.setPadding(new Insets(0, 0, 0, 130));
        //submitArea.setHgap(100);
        Button btnSubmit = new Button("Submit");
//        btnSubmit.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        btnSubmit.setPrefWidth(100);
        btnSubmit.setOnAction(e -> {
            try {

                LocalDateTime ldt = schedPicker.getDateTimeValue();
                ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
                ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneOffset.UTC);
                asIsoDateTime = utcZoned.format(DateTimeFormatter.ISO_DATE_TIME);

                c.btnSubmitAction(this);
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        StackPane root = new StackPane(schedPicker);
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

        //System.out.println(m.getInactiveUsers().contains(Integer.parseInt("1")));
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

    void clearScheduledUsers() {
        scheduledUsers.clear();
        m.resetPostBool();
    }

    public String getSchedule() {
        System.out.println(asIsoDateTime);
        return asIsoDateTime;
    }

    void determineScheduledUsers() {
        for (Person p : people) {
            if (p.getCheckBox().isSelected()) {
                scheduledUsers.add(Integer.parseInt(p.getUserID()));
            }
        }

    }
}
