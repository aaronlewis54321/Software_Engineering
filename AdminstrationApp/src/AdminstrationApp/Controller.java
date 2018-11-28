/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminstrationApp;

import AdminstrationApp.DateTimePicker;
import java.io.IOException;
import static javafx.application.Application.launch;

/**
 *
 * @author alewis91
 */
public class Controller {

    Model m;

    public Controller(Model model) throws IOException {
        m = model;
    }

    
    public void btnExportDataAction() throws IOException {
        m.writeResponseToCSV();
    }

    void btnSubmitAction(View v) throws IOException {
        v.determineScheduledUsers();
        m.scheduleUsers(v.getSchedule(), v.getScheduledUsers());
        m.makeUsersInactive(v.getScheduledUsers(), v.getSchedule());
        v.clearScheduledUsers();
        btnRefreshAction(v);

        //System.out.println(v.table.getSelectionModel().getSelectedItems());
    }

    void btnRefreshAction(View v) {
        v.refreshTable();
    }

    void btnReactivateAllAction(View v){
        m.makeAllUsersActive();
        v.refreshTable();
    }

}
