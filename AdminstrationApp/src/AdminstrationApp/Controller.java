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
    
    
    public Controller(Model model) throws IOException
    {
        m = model;
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

    void btnSubmitAction(View v) throws IOException {
        v.determineScheduledUsers();
        m.scheduleUsers(v.getScheduleTime(),v.getScheduledUsers());
        m.makeUsersInactive(v.getScheduledUsers(), v.getScheduleTime());
        v.clearScheduledUsers();
        btnRefreshAction(v);
        
        //System.out.println(v.table.getSelectionModel().getSelectedItems());
    }

    void btnRefreshAction(View v) {
        v.refreshTable();
    }

    void btnHelpAction() {
        
    }

    
}
