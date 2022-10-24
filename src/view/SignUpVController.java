/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class SignUpVController{
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initStage(Parent root) {
        //Create a scene associted to the node graph root.
        Scene scene = new Scene(root);
        //Associate scene to primaryStage(Window
        stage.setScene(scene);
        //Set window properties
        stage.setTitle("SignUp");
        stage.setResizable(false);
        //Show primary window
        stage.show();
    }
    
    @FXML
    public void handleButtonSignUpAction(ActionEvent event) {
        // Comprueba que los campos están informados y que el usuario, el email, el nombre completo y la contraseña son válidos (cumplen los requisitos especificados en sus propios eventos).
        // En caso de que un campo (o varios) esté vacío cambiar el color de su icono (imageUser, imageEmail, etc.) y la línea inferior (lineUser, lineEmail, etc.) a rojo. Cambiar los mensajes de campo invalido (labelInvalidUser, labelInvalidEmail, etc.) a “Enter [campo]”
        // (Cambiar campo por el dato a introducir en cuestión. 
        // Ej.: Enter an email)
        // Si los datos se validan correctamente, se ejecuta el método doSignUp() enviándole un user con los datos introducidos, y devuelve una excepción en caso de error o una respuesta OK si todo va bien.
        // Si no devuelve ninguna excepción abre la ventana SignIn y cierra la actual.
        // Si devuelve una excepción se muestra una ventana emergente que muestra el error.

    }
}
