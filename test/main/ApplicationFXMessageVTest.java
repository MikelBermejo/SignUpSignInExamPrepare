/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import datatransferobject.User;
import datatransferobject.UserPrivilege;
import datatransferobject.UserStatus;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import view.ApplicationVController;
import view.SignUpVController;

/**
 *
 * @author 2dam
 */
public class ApplicationFXMessageVTest extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ApplicationView.fxml"));
        Parent root = (Parent) loader.load();
        
        ApplicationVController controller = ((ApplicationVController) loader.getController());
        
        controller.setStage(stage);
        
        controller.setUser(new User("Test", "test@gmail.com", "Test", UserStatus.ENABLED, UserPrivilege.USER, "testtest", Timestamp.valueOf(LocalDateTime.MIN)));
        controller.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
