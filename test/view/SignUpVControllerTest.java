/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.ApplicationFXSignUpTest;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author Julen
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpVControllerTest extends ApplicationTest {

    private Text labelInvalidUser;
    private Text labelInvalidEmail;
    private Text labelInvalidName;
    private Text labelInvalidPassword;
    private Text labelInvalidConfirmPassword;
    private TextField textFieldUsername;
    private TextField textFieldEmail;
    private TextField textFieldName;
    private PasswordField passwordField;
    private PasswordField passwordFieldConfirm;
    private TextField textFieldPassword;
    private TextField textFieldConfirmPassword;
    private Button buttonSignIn;
    private Pane paneMessage;
    private Pane paneSignIn;
    private Pane paneSignUp;

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationFXSignUpTest.class);
    }

    /**
     * Test of the initial stage of the signup view
     */
    @Test
    public void testA_InitialStage() {
        verifyThat("#passwordField", isEnabled());
        verifyThat("#passwordFieldConfirm", isEnabled());
        verifyThat("#textFieldUsername", hasText(""));
        verifyThat("#textFieldEmail", hasText(""));
        verifyThat("#textFieldName", hasText(""));
        verifyThat("#passwordField", hasText(""));
        verifyThat("#passwordFieldConfirm", hasText(""));
        verifyThat("#textFieldPassword", hasText(""));
        verifyThat("#textFieldConfirmPassword", hasText(""));
        verifyThat("#buttonSignUp", isEnabled());
        verifyThat("#buttonSignIn", isEnabled());

    }

    /**
     * Test if the labels appear when all fields are empty and the SignUp button
     * is pressed
     */
    @Test
    public void testB_FieldsEmpty() {
        labelInvalidUser = lookup("#labelInvalidUser").query();
        labelInvalidEmail = lookup("#labelInvalidEmail").query();
        labelInvalidName = lookup("#labelInvalidName").query();
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        clickOn("#buttonSignUp");
        push(KeyCode.ENTER);
        assertEquals("Username can't be empty nor contain an empty space.", labelInvalidUser.getText());
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());
        assertEquals("Name is empty", labelInvalidName.getText());
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());
    }

    /**
     * Tests for the username TextField
     */
    @Test
    public void testC_UsernameIsInvalid() {
        labelInvalidUser = lookup("#labelInvalidUser").query();
        textFieldUsername = lookup("#textFieldUsername").query();
        clickOn("#textFieldUsername");
        write("test test");
        clickOn("#textFieldEmail");
        assertEquals("Username can't be empty nor contain an empty space.", labelInvalidUser.getText());

        clickOn("#textFieldUsername");
        eraseText(9);
        write("0123456789012345678901234");
        clickOn("#textFieldEmail");
        assertTrue(textFieldUsername.getText().length() <= 25);

        doubleClickOn("#textFieldUsername").push(KeyCode.DELETE);
        write("Test1");
        assertEquals("", labelInvalidUser.getText());
    }

    /**
     * Tests for the textFieldEmail
     */
    @Test
    public void testD_EmailIsInvalid() {
        labelInvalidEmail = lookup("#labelInvalidEmail").query();
        textFieldEmail = lookup("#textFieldEmail").query();
        
        clickOn("#textFieldEmail");
        write("01234567890123456789012345678901234");
        clickOn("#textFieldName");
        assertTrue(textFieldEmail.getText().length() <= 35);
        doubleClickOn("#textFieldEmail");
        push(KeyCode.DELETE);
        write("test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());

        clickOn("#textFieldEmail");
        eraseText(4);
        write("test@test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());

        clickOn("#textFieldEmail");
        eraseText(9);
        write("test.test");
        clickOn("#textFieldName");
        assertEquals("Invalid format of email (*@*.*)", labelInvalidEmail.getText());

        clickOn("#textFieldEmail");
        eraseText(9);
        write("test@test.test");
        clickOn("#textFieldName");
        assertEquals("", labelInvalidEmail.getText());
    }
    
    
    /**
     * Tests for the textFieldName
     */
    @Test
    public void testE_NameisValid() {
        labelInvalidName = lookup("#labelInvalidName").query();
        textFieldName = lookup("#textFieldName").query();
        
        clickOn("#textFieldName");
        write("012345678901234567890123456789012345678901234");
        clickOn("#passwordField");
        assertTrue(textFieldName.getText().length() <= 50);
        doubleClickOn("#textFieldName");
        push(KeyCode.DELETE);
        write("test");
        clickOn("#passwordField");
        assertEquals("", labelInvalidName.getText());
    }

    /**
     * Tests for the passwordField
     */
    @Test
    public void testF_PasswordIsInvalid() {
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        passwordField = lookup("#passwordField").query();
        
        clickOn("#passwordField");
        write("0123456789012345678901234");
        clickOn("#passwordFieldConfirm");
        assertTrue(passwordField.getText().length() <= 25);
        
        doubleClickOn("#passwordField");
        push(KeyCode.DELETE);
        write("test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#passwordField");
        eraseText(4);
        write("test test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#passwordField");
        eraseText(9);
        write("abcd*1234");
        clickOn("#passwordFieldConfirm");
        assertEquals("", labelInvalidPassword.getText());
    }

    /**
     * Test if both of the password fields share the same text
     */
    @Test
    public void testG_ConfirmPasswordIsInvalid() {
        labelInvalidConfirmPassword = lookup("#labelInvalidConfirmPassword").query();
        passwordField = lookup("#passwordField").query();
        passwordFieldConfirm = lookup("#passwordFieldConfirm").query();
        
        write("0123456789012345678901234");
        clickOn("#passwordField");
        assertTrue(passwordFieldConfirm.getText().length() <= 25);
        
        doubleClickOn("#passwordFieldConfirm");
        push(KeyCode.DELETE);
        write("test");
        clickOn("#passwordField");
        assertEquals("These passwords didn’t match", labelInvalidConfirmPassword.getText());

        clickOn("#passwordFieldConfirm");
        eraseText(4);
        write(passwordField.getText());
        clickOn("#passwordField");
        assertEquals("", labelInvalidConfirmPassword.getText());
        eraseText(9);
        clickOn("#passwordFieldConfirm");
        eraseText(9);
    }

    /**
     * Test if ButtonShowHide makes visible textFieldPassword
     */
    @Test
    public void testh_showHide() {
        doubleClickOn("#ButtonShowHide");
        verifyThat("#textFieldPassword", isVisible());
    }

    /**
     * Tests for the textFieldPassword
     */
    @Test
    public void testi_textFieldPassword() {
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        textFieldPassword = lookup("#textFieldPassword").query();
        
        clickOn("#textFieldPassword");
        write("0123456789012345678901234");
        clickOn("#passwordFieldConfirm");
        assertTrue(textFieldPassword.getText().length() <= 25);
        doubleClickOn("#textFieldPassword");
        push(KeyCode.DELETE);
        write("test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#textFieldPassword");
        eraseText(4);
        write("test test");
        clickOn("#passwordFieldConfirm");
        assertEquals("Password can't be empty nor contain an empty space or his lenght is less than 8.", labelInvalidPassword.getText());

        clickOn("#textFieldPassword");
        eraseText(9);
        write("abcd*1234");
        clickOn("#passwordFieldConfirm");
        assertEquals("", labelInvalidPassword.getText());
    }

    /**
     * Test if ButtonShowHideConfirm makes visible textFieldConfirmPassword
     */
    @Test
    public void testj_showHideConfirm() {
        doubleClickOn("#ButtonShowHideConfirm");
        verifyThat("#textFieldConfirmPassword", isVisible());
    }

    /**
     * Tests for the textFieldConfirmPassword
     */
    @Test
    public void testk_textFieldConfirmPassword() {
        labelInvalidConfirmPassword = lookup("#labelInvalidConfirmPassword").query();
        textFieldPassword = lookup("#textFieldPassword").query();
        textFieldConfirmPassword = lookup("#textFieldConfirmPassword").query();
        
        clickOn("#textFieldConfirmPassword");
        write("0123456789012345678901234");
        clickOn("#textFieldPassword");
        assertTrue(textFieldConfirmPassword.getText().length() <= 25);
        doubleClickOn("#textFieldConfirmPassword");
        push(KeyCode.DELETE);
        write("test");
        clickOn("#textFieldPassword");
        assertEquals("These passwords didn’t match", labelInvalidConfirmPassword.getText());

        clickOn("#textFieldConfirmPassword");
        eraseText(4);
        write(textFieldPassword.getText());
        clickOn("#textFieldPassword");
        assertEquals("", labelInvalidConfirmPassword.getText());
    }

    /**
     * Test to see if a user can go to the sign in view
     */
    @Test
    public void testl_goSignIn() {
        clickOn("#buttonSignIn");
        paneSignIn = lookup("#singInPane").query();
        assertThat(paneSignIn, isVisible());
    }

    /**
     * Test if it goes back to the SignUp view
     */
    @Test
    public void testn_goBackToSignUp() {
        clickOn("#buttonSignUp");
        paneSignUp = lookup("#singUpPane").query();
        assertThat(paneSignUp, isVisible());
    }

    /**
     * Test to see if a user can register
     */
    @Test
    public void testm_signUpTest() {
        clickOn("#buttonSignUp");
        verifyThat("Username can't be empty nor contain an empty space.", isVisible());
        verifyThat("Invalid format of email (*@*.*)", isVisible());
        verifyThat("Name is empty", isVisible());
        verifyThat("Password can't be empty nor contain an empty space or his lenght is less than 8.", isVisible());
        clickOn("#textFieldUsername");
        write("userTest4");
        clickOn("#textFieldEmail");
        write("userTest4@gmail.com");
        clickOn("#textFieldName");
        write("userTest4");
        clickOn("#passwordField");
        write("abcd*1234");
        clickOn("#passwordFieldConfirm");
        write("abcd*1234");
        verifyThat("These passwords didn’t match", isInvisible());
        clickOn("#buttonSignUp");
        paneMessage = lookup("#applicationPane").query();
        assertThat(paneMessage, isVisible());
    }   
}