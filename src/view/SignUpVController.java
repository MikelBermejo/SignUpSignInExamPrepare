/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import datatransferobject.Model;
import datatransferobject.User;
import datatransferobject.UserPrivilege;
import datatransferobject.UserStatus;
import java.util.function.UnaryOperator;
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
import exceptions.InvalidUserValueException;
import exceptions.InvalidUserException;
import exceptions.InvalidPasswordValueException;
import exceptions.InvalidConfirmPasswordValueException;
import exceptions.InvalidEmailValueException;
import exceptions.ConnectionErrorException;
import exceptions.MaxConnectionExceededException;
import exceptions.UserExistException;
import exceptions.TimeOutException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;
import model.ModelFactory;

/**
 * Escenario de registro
 * @author Mikel
 */
public class SignUpVController{
    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger("SignUpVController.class");
    
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    
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
    /**
     * Este metodo te inicializa el escenario de registro. Además les implementa acciones a los diferentes campos que contiene.
     * @param root 
     */
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
        textFieldUsername.setOnKeyTyped(this::textChanged);
        textFieldPassword.setOnKeyTyped(this::textChanged);
        passwordField.setOnKeyTyped(this::textChanged);
        passwordField.setOnKeyReleased(this::textChangedPressed);
        textFieldPassword.setOnKeyReleased(this::textChangedPressed);
        passwordFieldConfirm.setOnKeyReleased(this::textChangedPressed);
        textFieldConfirmPassword.setOnKeyReleased(this::textChangedPressed);
        textFieldEmail.setOnKeyTyped(this::textChangedEmail);
        textFieldName.setOnKeyTyped(this::textChangedName);
        //
        //Focus lost
        textFieldPassword.focusedProperty().addListener(this::focusedPropertyChangedPassword);
        passwordField.focusedProperty().addListener(this::focusedPropertyChangedPassword);
        textFieldEmail.focusedProperty().addListener(this::focusedPropertyChangedEmail);
        textFieldUsername.focusedProperty().addListener(this::focusedPropertyChanged);
        textFieldConfirmPassword.focusedProperty().addListener(this::focusedPropertyChangedPasswordConfirm);
        passwordFieldConfirm.focusedProperty().addListener(this::focusedPropertyChangedPasswordConfirm);
        //
        //Button Actions
        buttonSignIn.setOnAction(this::signIn);
        ButtonShowHide.setOnAction(this::showHide);
        ButtonShowHideConfirm.setOnAction(this::showHideConfirm);
        //
        // CLOSE //
        stage.setOnCloseRequest(this::handleExitAction);
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
    /**
     * Evento que permite controlar que el maximo de carteres del textfield sea 25.
     * @param event 
     */
    private void textChanged(KeyEvent event) {
        if (((TextField) event.getSource()).getText().length() >= 25) {
            event.consume();
            ((TextField) event.getSource()).setText(((TextField) event.getSource()).getText().substring(0, 25));
        }
    }
    /**
     * Evento que permite controlar que el maximo de carteres del email sea 35
     * @param event 
     */
    private void textChangedEmail(KeyEvent event) {
        if (((TextField) event.getSource()).getText().length() >= 35) {
            event.consume();
            ((TextField) event.getSource()).setText(((TextField) event.getSource()).getText().substring(0, 35));
        }
    }
    /**
     * Evento que permite controlar que el maximo de carteres del nombre sea 50
     * @param event 
     */
    private void textChangedName(KeyEvent event) {
        if (((TextField) event.getSource()).getText().length() >= 50) {
            event.consume();
            ((TextField) event.getSource()).setText(((TextField) event.getSource()).getText().substring(0, 50));
        }
    }
    /**
     * Este metodo comprueba si el nombre está vacio o no.
     */
    private void nameIsEmptyOrNo() {
        if(!textFieldName.isFocused()){
            try{
                if(textFieldName.getText().isEmpty()) throw new InvalidUserValueException("Name is empty");
                imageViewName.setImage(new Image(getClass().getResourceAsStream("/resources/iconFullName.png")));
                lineName.setStroke(Color.GREY);
                labelInvalidName.setText("");

            } catch (InvalidUserValueException e) {
                imageViewName.setImage(new Image(getClass().getResourceAsStream("/resources/iconFullNameIncorrect.png")));
                lineName.setStroke(Color.RED);
                labelInvalidName.setText(e.getMessage());
            }

        }
    }
    
    /**
     * Comprueba si el email esta bajo foco y verifica si es valido o no, usando un patron miraremos si corresponde a la estructura correcto.
     * @param value
     * @param oldValue 
     * @param newValue 
     */
    private void focusedPropertyChangedEmail(Observable value, Boolean oldValue, Boolean newValue) {
        if (!textFieldEmail.isFocused()) {
            boolean matchOrNot = false;
            Pattern pattern = Pattern.compile(emailPattern);
            Matcher matcher = pattern.matcher(textFieldEmail.getText());
            if (matcher.find()) {
                matchOrNot = true;
            }
            try {
                if (!matchOrNot) {
                    throw new InvalidEmailValueException("Invalid format of email (*@*.*)");
                } else {
                    imageViewEmail.setImage(new Image(getClass().getResourceAsStream("/resources/iconEmail.png")));
                    lineEmail.setStroke(Color.GREY);
                    labelInvalidEmail.setText("");
                }
            } catch (InvalidEmailValueException e) {
                imageViewEmail.setImage(new Image(getClass().getResourceAsStream("/resources/iconEmailIncorrect.png")));
                lineEmail.setStroke(Color.RED);
                labelInvalidEmail.setText(e.getMessage());
            }

        }
    }
    
    /**
     * Comprueba si el nombre de ususario esta bajo foco y valida si es correcto, que no contenga espacios en blanco y que no sea vacio.
     * @param value
     * @param oldValue
     * @param newValue 
     */
    private void focusedPropertyChanged(Observable value, Boolean oldValue, Boolean newValue) {
        if(oldValue){
            if(!textFieldUsername.isFocused()){
                try{
                    if(textFieldUsername.getText().contains(" ") || textFieldUsername.getText().isEmpty()) throw new InvalidUserValueException("Username can't be empty nor contain an empty space.");
                    imageViewUsername.setImage(new Image(getClass().getResourceAsStream("/resources/iconUser.png")));
                    lineUsername.setStroke(Color.GREY);
                    labelInvalidUser.setText("");
                } catch (InvalidUserValueException e) {
                    imageViewUsername.setImage(new Image(getClass().getResourceAsStream("/resources/iconUserInconrrect.png")));
                    lineUsername.setStroke(Color.RED);
                    labelInvalidUser.setText(e.getMessage());
                }
            }
        }
    }
    
    /**
     * Comprueba si el confirmacion de la contraseña esta bajo foco y valida si es correcto, que sea igual que la contraseña.
     * @param value
     * @param oldValue
     * @param newValue 
     */
     private void focusedPropertyChangedPasswordConfirm(Observable value, Boolean oldValue, Boolean newValue){
        if(oldValue){
            if(!passwordFieldConfirm.isFocused() && !textFieldConfirmPassword.isFocused()){
                try{
                    if(!passwordFieldConfirm.getText().toString().equalsIgnoreCase(passwordField.getText().toString())) throw new InvalidConfirmPasswordValueException("These passwords didn’t match");
                    imageViewConfirmPassword.setImage(new Image(getClass().getResourceAsStream("/resources/iconPassword.png")));
                    lineConfirmPassword.setStroke(Color.GREY);
                    labelInvalidConfirmPassword.setText("");
                } catch (InvalidConfirmPasswordValueException e) {
                    imageViewConfirmPassword.setImage(new Image(getClass().getResourceAsStream("/resources/iconPasswordRedIncorrect.png")));
                    lineConfirmPassword.setStroke(Color.RED);
                    labelInvalidConfirmPassword.setText(e.getMessage());
                }
            }
        }
    }
    
     /**
      * Comprueba si la contraseña esta bajo foco y valida si es correcto, si su longitud es superior o igual a 8 y que no contenga espacios.
      * @param value
      * @param oldValue
      * @param newValue 
      */
    private void focusedPropertyChangedPassword(Observable value, Boolean oldValue, Boolean newValue){
        if(oldValue){
            if(!passwordField.isFocused() && !textFieldPassword.isFocused()){
                try{
                    if(passwordField.getText().contains(" ") || passwordField.getText().length()<8 || passwordField.getText().isEmpty()) throw new InvalidPasswordValueException("Password can't be empty nor contain an empty space or his lenght is less than 8.");
                    imageViewPassword.setImage(new Image(getClass().getResourceAsStream("/resources/iconPassword.png")));
                    linePassword.setStroke(Color.GREY);
                    labelInvalidPassword.setText("");
                } catch(InvalidPasswordValueException e) {
                    imageViewPassword.setImage(new Image(getClass().getResourceAsStream("/resources/iconPasswordRedIncorrect.png")));
                    linePassword.setStroke(Color.RED);
                    labelInvalidPassword.setText(e.getMessage());
                }
            }
        }
    }
    
    /**
     * El metodo que loguea y abre la ventana signIn.
     * @param event 
     */
    private void signIn(ActionEvent event) {
        try {
            stage.close();
            LOGGER.info("SignUp window closed");
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/SignInView.fxml"));
            Parent root = (Parent) loader.load();
            SignInVController controller = ((SignInVController) loader.getController());
            controller.setStage(new Stage());
            controller.initStage(root);
            LOGGER.info("SignIn window opened");
        } catch (IOException ex) {
            Logger.getLogger(SignInVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * El metodo de registro.
     * @param event 
     */
    @FXML
    private void signUp(ActionEvent event) {
        nameIsEmptyOrNo();
        Model model = ModelFactory.getModel();
        User user = new User(textFieldUsername.getText(), textFieldEmail.getText(),textFieldName.getText(),UserStatus.ENABLED,UserPrivilege.USER,textFieldPassword.getText(),new Timestamp(System.currentTimeMillis()));
        try {
            model.doSignUp(user);
            signIn(event);
        } catch (UserExistException | ConnectionErrorException | TimeOutException | MaxConnectionExceededException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.info(ex.getMessage());
        }
    }
    /**
     * Metodo que hace visible y no visible la contraseña
     * @param event 
     */
    private void showHide(ActionEvent event) {
        if (ButtonShowHide.isSelected()) {
            IconEye.setImage(new Image(getClass().getResourceAsStream("/resources/iconEye2.png")));
            passwordField.setVisible(false);
            textFieldPassword.setVisible(true);
        } else {
            IconEye.setImage(new Image(getClass().getResourceAsStream("/resources/iconEye.png")));
            passwordField.setVisible(true);
            textFieldPassword.setVisible(false);
        }
    }
    
    /**
     * Metodo que hace visible y no visible la confirmacion de contraseña.
     * @param event 
     */
    private void showHideConfirm(ActionEvent event) {
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
    
    /**
     * 
     * @param KEY_RELEASED 
     */
    private void textChangedPressed(KeyEvent KEY_RELEASED) {
        if (passwordField.isVisible()){
            textFieldPassword.setText(passwordField.getText());
        } else if (textFieldPassword.isVisible()){
            passwordField.setText(textFieldPassword.getText());
        }
        if (passwordFieldConfirm.isVisible()){
            textFieldConfirmPassword.setText(passwordFieldConfirm.getText());
        } else if(textFieldConfirmPassword.isVisible()){
            passwordFieldConfirm.setText(textFieldConfirmPassword.getText());
        }
    } 

    /**
     * Metodo que te hace salir de la aplicacion pasando por un alert
     * @param event 
     */
    private void handleExitAction(WindowEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit? This will close the app.");
        a.showAndWait();
        try {
            if (a.getResult().equals(ButtonType.CANCEL)) {
                event.consume();
            } else {
                Platform.exit();
            }
        } catch (Exception e) {
            String msg = "Error closing the app: " + e.getMessage();
            Alert alert = new Alert(Alert.AlertType.ERROR, msg);
            alert.show();
            LOGGER.log(Level.SEVERE, msg);
        }
    }
}
