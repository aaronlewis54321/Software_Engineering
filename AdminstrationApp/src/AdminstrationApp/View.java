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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        //btnAddUsers.setOnAction(e -> c.doStuff);
        
        Button btnEditGroups = new Button();
        btnEditGroups.setText("Edit Groups");
        
        Button btnApplyChanges = new Button();
        btnApplyChanges.setText("Apply Changes");
        
        Button btnRevertChanges = new Button();
        btnRevertChanges.setText("Revert Changes");
        
        Button btnExportCSV = new Button();
        btnExportCSV.setText("Export Data");
        
        
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
        border.setCenter(table);
        
        btn.setMinWidth(100);
        border.setLeft(btn);
        
        
        VBox vboxRight = new VBox();
        vboxRight.setPrefWidth(100);
        
        Button[] options = {btnAddUsers, btnEditGroups, btnApplyChanges, btnRevertChanges, btnExportCSV};
        for (int i=0; i<options.length; i++) {
           options[i].setMinWidth(vboxRight.getPrefWidth());
           vboxRight.setMargin(options[i], new Insets(0, 0, 0, 0));
           vboxRight.getChildren().add(options[i]);
    }
        
               
        border.setRight(vboxRight);
        //border.setRight(border.getRight(),btnEditUsers);
        //root.getChildren().add(table);
        
        Scene scene = new Scene(border, 950, 400);
        
        primaryStage.setTitle("Emoji Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        m.writeResponseToCSV();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
