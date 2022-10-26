/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Mikel
 */
public class SignUpVController{
    private Stage stage;
    
    @FXML
    private TextField textFieldUsername;
    @FXML
    private ImageView imageViewUsername;
    @FXML
    private ImageView imageViewEmail;
    @FXML
    private ImageView imageViewName;
    @FXML
    private ImageView imageViewPassword;
    @FXML
    private ImageView imageViewConfirmPassword;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldConfirm;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private TextField textFieldConfirmPassword;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Button buttonSignUp;
    @FXML
    private Line lineUsername;
    @FXML
    private Line lineEmail;
    @FXML
    private Line lineName;
    @FXML
    private Line linePassword;
    @FXML
    private Line lineConfirmPassword;
    @FXML
    private ToggleButton ButtonShowHide;
    @FXML
    private ToggleButton ButtonShowHideConfirm;
    @FXML
    private Text labelInvalidUser;
    @FXML
    private Text labelInvalidEmail;
    @FXML
    private Text labelInvalidName;
    @FXML
    private Text labelInvalidPassword;
    @FXML
    private Text labelInvalidConfirmPassword;
    @FXML
    private ImageView IconEye;
    @FXML
    private ImageView IconEye2;
    
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
        //Add Listeners
        //Writing, key typed
        textFieldUsername.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED));
        textFieldPassword.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED, KeyEvent.KEY_PRESSED));
        passwordField.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED, KeyEvent.KEY_PRESSED));
        textFieldPassword.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_PRESSED));
        passwordField.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_PRESSED));
        textFieldEmail.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED));
        textFieldName.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED));
        //
        //Focus lost
        passwordField.focusedProperty().addListener((event) -> this.focusedPropertyChanged());
        textFieldUsername.focusedProperty().addListener((event) -> this.focusedPropertyChanged());
        textFieldPassword.focusedProperty().addListener((event) -> this.focusedPropertyChanged());
        //
        //Button Actions
        buttonSignIn.pressedProperty().addListener((event) -> this.signIn(ActionEvent.ACTION));
        buttonSignUp.pressedProperty().addListener((event) -> this.signUp(ActionEvent.ACTION));
        ButtonShowHide.pressedProperty().addListener((event) -> this.ShowHide(ActionEvent.ACTION));
        ButtonShowHideConfirm.pressedProperty().addListener((event) -> this.ShowHide(ActionEvent.ACTION));
        //
        //Show primary window
        stage.show();
    }
    
    // Comprueba que los campos están informados y que el usuario, el email, el nombre completo y la contraseña son válidos (cumplen los requisitos especificados en sus propios eventos).
        // En caso de que un campo (o varios) esté vacío cambiar el color de su icono (imageUser, imageEmail, etc.) y la línea inferior (lineUser, lineEmail, etc.) a rojo. Cambiar los mensajes de campo invalido (labelInvalidUser, labelInvalidEmail, etc.) a “Enter [campo]”
        // (Cambiar campo por el dato a introducir en cuestión. 
        // Ej.: Enter an email)
        // Si los datos se validan correctamente, se ejecuta el método doSignUp() enviándole un user con los datos introducidos, y devuelve una excepción en caso de error o una respuesta OK si todo va bien.
        // Si no devuelve ninguna excepción abre la ventana SignIn y cierra la actual.
        // Si devuelve una excepción se muestra una ventana emergente que muestra el error.

    private void textChanged(EventType<KeyEvent> KEY_TYPED) {
        if(textFieldUsername.getText().length()>25){
            textFieldUsername.setText(textFieldUsername.getText().substring(0,25));
        }
        if(textFieldPassword.getText().length()>25){
            textFieldPassword.setText(textFieldPassword.getText().substring(0,25));
        }
        if(passwordField.getText().length()>25){
            passwordField.setText(passwordField.getText().substring(0,25));
        }
        if(textFieldEmail.getText().length()>35){
            textFieldEmail.setText(textFieldEmail.getText().substring(0,35));
        }
        if(passwordField.getText().length()>50){
            textFieldName.setText(textFieldName.getText().substring(0,50));
        }
    }
    
    private void focusedPropertyChanged() {
        if(!passwordField.isFocused() && !textFieldPassword.isFocused()){
            if(passwordField.getText().contains(" ")){
                imageViewPassword.setImage(new Image(getClass().getResourceAsStream("/resources/iconPasswordRedIncorrect.png")));
                linePassword.setStroke(Color.RED);
                labelInvalidPassword.setText("Password can't contain an empty space.");
                labelInvalidPassword.setVisible(true);
            } else {
                imageViewPassword.setImage(new Image(getClass().getResourceAsStream("/resources/iconPassword.png")));
                linePassword.setStroke(Color.GREY);
                labelInvalidPassword.setVisible(false);
            }
        }
        if(!textFieldUsername.isFocused()){
            if(textFieldUsername.getText().contains(" ")){
                imageViewUsername.setImage(new Image(getClass().getResourceAsStream("/resources/iconUserInconrrect.png")));
                lineUsername.setStroke(Color.RED);
                labelInvalidUser.setText("Username can't contain an empty space.");
                labelInvalidUser.setVisible(true);
            } else {
                imageViewUsername.setImage(new Image(getClass().getResourceAsStream("/resources/iconUser.png")));
                lineUsername.setStroke(Color.GREY);
                labelInvalidUser.setVisible(false);
            }
        }
    }

    private void signIn(EventType<ActionEvent> ACTION) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void signUp(EventType<ActionEvent> ACTION) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ShowHide(EventType<ActionEvent> ACTION) {
        if (ButtonShowHide.isSelected()) {
            IconEye.setImage(new Image(getClass().getResourceAsStream("/resources/iconEye2.png")));
            passwordField.setVisible(false);
            textFieldPassword.setVisible(true);
        } else {
            IconEye.setImage(new Image(getClass().getResourceAsStream("/resources/iconEye.png")));
            passwordField.setVisible(true);
            textFieldPassword.setVisible(false);
        }
        if (ButtonShowHideConfirm.isSelected()) {
            IconEye2.setImage(new Image(getClass().getResourceAsStream("/resources/iconEye2.png")));
            passwordFieldConfirm.setVisible(false);
            textFieldConfirmPassword.setVisible(true);
        } else {
            IconEye2.setImage(new Image(getClass().getResourceAsStream("/resources/iconEye.png")));
            passwordFieldConfirm.setVisible(true);
            textFieldConfirmPassword.setVisible(false);
        }
    }

    private void textChanged(EventType<KeyEvent> KEY_TYPED, EventType<KeyEvent> KEY_PRESSED) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   

}
