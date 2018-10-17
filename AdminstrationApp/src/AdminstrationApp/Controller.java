/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import java.io.IOException;
import static javafx.application.Application.launch;

/**
 *
 * @author alewis91
 */
public class Controller {
    
    Model m;
    
    
    public Controller() throws IOException
    {
        m = new Model();
    }
    
    
    
    //Funcitonality needs to be implemented
    public void btnAddUsersAction(){
        System.out.println("AddUsers");
    }
    
    public void btnEditGroupsAction(){
        System.out.println("EditGroups");
    }
    
    
    //Functionality needs to be implemented
    public void btnApplyChangesAction() {
        System.out.println("ApplyChanges");
    }
    
    
    //Functionality needs to be implemented
    public void btnRevertChangesAction() {
        System.out.println("RevertChanges");
    }
    
    public void btnExportDataAction() throws IOException {
        m.writeResponseToCSV();
    }    

    
}
