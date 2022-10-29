/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class SignUp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        // Carga el documento xml y obtiene un objeto Parent
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/SignUp.fxml"));
        Parent root = (Parent) loader.load();
        
        SignUpVController controller = 
                ((SignUpVController)loader.getController());
        controller.setStage(stage);
        
        controller.initStage(root);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
