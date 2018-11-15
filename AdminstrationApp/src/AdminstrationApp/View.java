/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.io.IOException;
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
    Model m;
    Controller c;
    TableView table;
    BorderPane border;
    ArrayList<Person> people;
    private ArrayList<Integer> scheduledUsers;
    TextField txtSchedule;

    @Override
    public void start(Stage primaryStage) throws IOException {
        m = new Model();
        c = new Controller(m);
        people = new ArrayList<Person>();
        scheduledUsers = new ArrayList<Integer>();

        Person l = new Person("", "", "", "", "", "4");
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(l);
         //m.writePeopleToDatabase(list);

        //Button btn = new Button();
        //btn.setText("Placeholder");
        //Button btnAddUsers = new Button();
        //btnAddUsers.setText("Add Users");
        //btnAddUsers.setOnAction(e1 -> c.btnAddUsersAction());
        //Button btnEditGroups = new Button();
        //btnEditGroups.setText("Edit Groups");
        //btnEditGroups.setOnAction(e2 -> c.btnEditGroupsAction());
        //Button btnApplyChanges = new Button();
        //btnApplyChanges.setText("Apply Changes");
        //btnApplyChanges.setOnAction(e3 -> c.btnApplyChangesAction());
        //Button btnRevertChanges = new Button();
        //btnRevertChanges.setText("Revert Changes");
        //btnRevertChanges.setOnAction(e4 -> c.btnRevertChangesAction());
        Button btnRefresh = new Button();
        btnRefresh.setText("Refresh");
        btnRefresh.setOnAction(e -> c.btnRefreshAction(this));
        Button btnHelp = new Button("Help");
        btnHelp.setOnAction(e -> c.btnHelpAction());

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
            for(InactiveUser i : m.getInactiveUsers())
            {
                if (i.getUserId() == Integer.parseInt(p.getUserID()))
                {
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

        Button[] options = {btnExportCSV, btnRefresh, btnHelp};  //{btnAddUsers, btnEditGroups, btnApplyChanges, btnRevertChanges, btnExportCSV};
        for (int i = 0; i < options.length; i++) {
            options[i].setMinWidth(vboxLeft.getPrefWidth());
            vboxLeft.setMargin(options[i], new Insets(0, 0, 0, 0));
            vboxLeft.getChildren().add(options[i]);
        }

        border.setLeft(vboxLeft);
        //border.setRight(border.getRight(),btnEditUsers);
        //root.getChildren().add(table);

        //border.setLeft(vboxLeft);
        HBox submitArea = new HBox(10);
        submitArea.setPadding(new Insets(0, 0, 0, 130));
        //submitArea.setHgap(100);
        Button btnSubmit = new Button("Submit");
        btnSubmit.setPrefWidth(100);
        btnSubmit.setOnAction(e -> {
            try {
                c.btnSubmitAction(this);
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        txtSchedule = new TextField();
        txtSchedule.setPrefWidth(300);
        
        ComboBox year = new ComboBox();
        year.setPrefWidth(100);
        ObservableList<String> s = FXCollections.observableArrayList();
        s.add("2018");s.add("2019");s.add("2020");s.add("2021");s.add("2022");s.add("2023");
        year.setItems(s);
        ComboBox month = new ComboBox();
        month.setPrefWidth(100);
        ObservableList<String> q = FXCollections.observableArrayList();
        q.add("JAN");q.add("FEB");q.add("MAR");q.add("APR");q.add("MAY");q.add("JUN");q.add("JUL");
        q.add("AUG");q.add("SEP");q.add("OCT");q.add("NOV");q.add("DEC");
        month.setItems(q);
        ComboBox day = new ComboBox();
        ObservableList<String> w = FXCollections.observableArrayList();
        w.add("1");w.add("2");w.add("3");w.add("4");w.add("5");w.add("6");w.add("7");
        w.add("8");w.add("9");w.add("10");w.add("11");w.add("12");
        w.add("13");w.add("14");w.add("15");w.add("16");w.add("17");w.add("18");w.add("19");
        w.add("20");w.add("21");w.add("22");w.add("23");w.add("24");
        w.add("25");w.add("26");w.add("27");w.add("28");w.add("29");w.add("30");w.add("31");
        day.setItems(w);
        day.setPrefWidth(100);
        TextField hour = new TextField();
        hour.setPrefWidth(100);
        TextField minute = new TextField();
        minute.setPrefWidth(100);
        
        
        Label lblSchedule = new Label("Schedule: ");
        lblSchedule.setPrefHeight(30);
        submitArea.getChildren().addAll(lblSchedule, year, month, day, hour, minute, btnSubmit);

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
            for(InactiveUser i : m.getInactiveUsers())
            {
                if (i.getUserId() == Integer.parseInt(p.getUserID()))
                {
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
    

    void determineScheduledUsers() {
        //scheduledUsers.add(Integer.parseInt(((Person)table.getSelectionModel().getSelectedItems()).getUserID()));
        //scheduledUsers.add(1);
        //scheduledUsers.add(2);

        for (Person p : people) {
            if (p.getCheckBox().isSelected()) {
                scheduledUsers.add(Integer.parseInt(p.getUserID()));
            }
        }

    }

    String getScheduleTime() {
        return txtSchedule.getText();
    }

}
