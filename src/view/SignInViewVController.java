/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 *
 * @author 2dam
 */
public class SignInViewVController {
    
    private Stage stage;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordText;
    @FXML 
    private Button buttonSignIn;
    @FXML
    private Button buttonSignUp;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.setTitle("SignIn");
        stage.setResizable(false);
        
        stage.show();
    }
}
