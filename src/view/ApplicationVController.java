/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import datatransferobject.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Haizea and Julen
 */
public class ApplicationVController {

    private User user;
    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger("view");

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Label labelMessage;

    public void initStage(Parent root) {
        try {
            // Crea una escena asociada al root
            Scene scene = new Scene(root);
            // Asocia una escena al escenario
            stage.setScene(scene);
            // La ventana tiene el título “Application”
            stage.setTitle("Application");
            // La ventana no es redimensionable
            stage.setResizable(false);
            // La ventana recogerá un objeto User del cual cogerá el Username y lo asignará al labelMessage (“Hello [usuario]!!”)
            labelMessage.setText("Hello " + user.getLogin() + "!!");
            
            // Confirmar el cierre de la aplicación
            stage.setOnCloseRequest(this::handleExitAction);
            
            // Enseña la ventana principal
            stage.show();
            LOGGER.info("Application window initialized");
        } catch (Exception e) {
            String msg = "Error opening the window: " + e.getMessage();
            Alert alert = new Alert(AlertType.ERROR,msg);
            LOGGER.log(Level.SEVERE, msg);
        }
    }
    
    private void handleExitAction(WindowEvent event) {
        try {
            if (new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit? This will close the app").getResult().equals(ButtonType.YES)) {
                Platform.exit();
            }
        } catch(Exception e) {
            String msg = "Error closing the app: " + e.getMessage();
            Alert alert = new Alert(AlertType.ERROR,msg);
            LOGGER.log(Level.SEVERE, msg);
        }
    }
    
    @FXML
    private void handleButtonLogOutAction(ActionEvent event) {
        try {
            if (new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to LogOut?").getResult().equals(ButtonType.YES)) {
                // Carga el documento FXML y obtiene un objeto Parent
                Parent root = FXMLLoader.load(getClass().getResource("view/SignInView.fxml"));
                // Crea una escena a partir del Parent
                Scene scene = new Scene(root);
                // Establece la escena en el escenario (Stage) y la muestra
                Stage stageSI = new Stage();
                stageSI.setScene(scene);
                stageSI.show();
                stage.close();
                LOGGER.info("Application window closed");
            }
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR, ex.getMessage());
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

}
