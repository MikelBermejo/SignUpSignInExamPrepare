/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Sendoa
 */
public class SignInVController {

    private Stage stage;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private Line usernameLine;
    @FXML
    private Line passwordLine;
    @FXML
    private Button buttonSignIn;
    @FXML
    private Button buttonSignUp;
    @FXML
    private Button buttonShowHide;
    @FXML
    private Label labelInvalidUser;
    @FXML
    private Label labelInvalidPassword;
    @FXML
    private ImageView userIcon;
    @FXML
    private ImageView passwordIcon;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("SignIn");
        stage.setResizable(false);

        textFieldUsername.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED));
        textFieldUsername.focusedProperty().addListener((event) -> this.focusPropertyChange());
        passwordField.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED));
        textFieldPassword.textProperty().addListener((event) -> this.textChanged(KeyEvent.KEY_TYPED));
        buttonSignIn.pressedProperty().addListener((event) -> this.signIn(ActionEvent.ACTION));

        stage.show();
    }

    private void textChanged(EventType<KeyEvent> KEY_TYPED) {
        if (textFieldUsername.getText().length() > 25) {
            textFieldUsername.setText(textFieldUsername.getText().substring(0, 25));
        }
        if (passwordField.getText().length() > 25) {
            passwordField.setText(passwordField.getText().substring(0, 25));
        }
        if (textFieldPassword.getText().length() > 25) {
            textFieldPassword.setText(textFieldPassword.getText().substring(0, 25));
        }
    }

    private void signIn(EventType<ActionEvent> ACTION) {
        if (textFieldUsername.getText().isEmpty()) {
            userIcon.setImage(new Image(getClass().getResourceAsStream("/resources/iconUserIncorrect.png")));
            usernameLine.setStroke(Color.RED);
            labelInvalidUser.setText("Username is empty");
            labelInvalidUser.setVisible(true);
        }
        if (passwordField.getText().isEmpty() || textFieldPassword.getText().isEmpty()) {
            passwordIcon.setImage(new Image(getClass().getResourceAsStream("/resources/iconPasswordRedIncorrect.png")));
            passwordLine.setStroke(Color.RED);
            labelInvalidPassword.setText("Password is empty");
            labelInvalidPassword.setVisible(true);
        }
    }

    private void focusPropertyChange() {
        if (textFieldUsername.getText().contains(" ")) {
            userIcon.setImage(new Image(getClass().getResourceAsStream("/resources/iconUserIncorrect.png")));
            usernameLine.setStroke(Color.RED);
            labelInvalidUser.setText("Username can't contain an empty space");
            labelInvalidUser.setVisible(true);
        } else {
            userIcon.setImage(new Image(getClass().getResourceAsStream("/resources/iconUser.png")));
            usernameLine.setStroke(Color.GRAY);
            labelInvalidUser.setVisible(false);
        }
        if (passwordField.getText().contains(" ") || textFieldPassword.getText().contains(" ")) {
            passwordIcon.setImage(new Image(getClass().getResourceAsStream("/resources/iconPasswordRedIncorrect.png")));
            passwordLine.setStroke(Color.RED);
            labelInvalidPassword.setText("Password can't contain an empty space");
            labelInvalidPassword.setVisible(true);
        } else {
            passwordIcon.setImage(new Image(getClass().getResourceAsStream("/resources/iconPassword.png")));
            passwordLine.setStroke(Color.GRAY);
            labelInvalidPassword.setVisible(false);
        }
    }
}
