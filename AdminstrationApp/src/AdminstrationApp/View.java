/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import javafx.geometry.Insets;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
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
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
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
        
        Scene scene = new Scene(border, 700, 400);
        
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
