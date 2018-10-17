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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
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
    
    

    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        m = new Model();
        c = new Controller();
        
        Button btn = new Button();
        btn.setText("Placeholder");
        
        Button btnAddUsers = new Button();
        btnAddUsers.setText("Add Users");
        btnAddUsers.setOnAction(e1 -> c.btnAddUsersAction());
        
        Button btnEditGroups = new Button();
        btnEditGroups.setText("Edit Groups");
        btnEditGroups.setOnAction(e2 -> c.btnEditGroupsAction());
        
        Button btnApplyChanges = new Button();
        btnApplyChanges.setText("Apply Changes");
        btnApplyChanges.setOnAction(e3 -> c.btnApplyChangesAction());
        
        Button btnRevertChanges = new Button();
        btnRevertChanges.setText("Revert Changes");
        btnRevertChanges.setOnAction(e4 -> c.btnRevertChangesAction());
        
        Button btnExportCSV = new Button();
        btnExportCSV.setText("Export Data");
        btnExportCSV.setOnAction(e5 -> {
            try {
                c.btnExportDataAction();
            } catch (IOException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        TableView table = new TableView();
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        firstNameCol.setPrefWidth(150);
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        lastNameCol.setPrefWidth(150);
        TableColumn phoneNumCol = new TableColumn("Phone Number");
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        phoneNumCol.setPrefWidth(150);
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        emailCol.setPrefWidth(150);
        TableColumn scheduleCol = new TableColumn("Schedule");
        scheduleCol.setCellValueFactory(new PropertyValueFactory<>("Schedule"));
        scheduleCol.setPrefWidth(150);
        
        table.getColumns().addAll(firstNameCol, lastNameCol, phoneNumCol, emailCol, scheduleCol);
        //table.getItems().add(m.getPeople());
        
        for(Person p : m.getPeople())
        {
            table.getItems().add(p);
        }
        
        
        BorderPane border = new BorderPane();
        table.setEditable(true);
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
        
        
        
        
        
        
        border.setCenter(table);
        
        btn.setMinWidth(130);
        border.setLeft(btn);
        
        
        VBox vboxRight = new VBox();
        vboxRight.setPrefWidth(130);
        
        Button[] options = {btnAddUsers, btnEditGroups, btnApplyChanges, btnRevertChanges, btnExportCSV};
        for (int i=0; i<options.length; i++) {
           options[i].setMinWidth(vboxRight.getPrefWidth());
           vboxRight.setMargin(options[i], new Insets(0, 0, 0, 0));
           vboxRight.getChildren().add(options[i]);
    }
        
               
        border.setRight(vboxRight);
        //border.setRight(border.getRight(),btnEditUsers);
        //root.getChildren().add(table);
        
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
    
}
