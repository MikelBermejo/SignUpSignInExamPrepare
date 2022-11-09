/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.concurrent.TimeoutException;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import main.ApplicationFX;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import static org.junit.Assert.*;

/**
 * Test class for the sign in window
 * @author sendoa
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInVControllerTest extends ApplicationTest {

    private TextField textFieldUsername;
    private PasswordField passwordField;
    private TextField textFieldPassword;
    private Label labelInvalidUser;
    private Label labelInvalidPassword;
    private Pane paneSignIn;

    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationFX.class);
    }

    /**
     * Test of the initial stage of the login view
     */
    @Test
    public void test1_InitialStage() {
        verifyThat("#textFieldUsername", hasText(""));
        verifyThat("#passwordField", hasText(""));
        verifyThat("#textFieldPassword", hasText(""));
        verifyThat("#buttonSignIn", isEnabled());
        verifyThat("#buttonSignUp", isEnabled());
    }

    /**
     * Test if the labels appear when both fields are empty and the SignIn
     * button is pressed
     */
    @Test
    public void test2_BothEmpty() {
        labelInvalidUser = lookup("#labelInvalidUser").query();
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        push(KeyCode.ENTER);
        //verifyThat("#labelInvalidUser", hasText("Enter a username"));
        assertEquals("Enter a username", labelInvalidUser.getText());
        //verifyThat("#labelInvalidPassword", hasText("Enter a password"));
        assertEquals("Enter a password", labelInvalidPassword.getText());
        clickOn("#buttonSignIn");
        assertEquals("Enter a username", labelInvalidUser.getText());
        assertEquals("Enter a password", labelInvalidPassword.getText());
    }

    /**
     * Tests for the username TextField
     */
    @Test
    public void test3_UsernameIsInvalid() {
        textFieldUsername = lookup("#textFieldUsername").query();
        labelInvalidUser = lookup("#labelInvalidUser").query();
        // First test if the label appears when there is a space in the textField //
        clickOn("#textFieldUsername");
        write("test test");
        clickOn("#passwordField");
        assertEquals("Username can't contain an empty space", labelInvalidUser.getText());
        // Then test if the text field can contain more than 25 characters //
        clickOn("#textFieldUsername");
        eraseText(9);
        write("123456789012345678901234567890");
        assertTrue(textFieldUsername.getText().length() <= 25);
        // Finaly test if the username is correct the label doesn't appear //
        doubleClickOn("#textFieldUsername").push(KeyCode.DELETE);
        write("test1");
        clickOn("#passwordField");
        assertEquals("", labelInvalidUser.getText());
    }

    /**
     * Tests for the passwordField
     */
    @Test
    public void test4_passwordFieldIsInvalid() {
        passwordField = lookup("#passwordField").query();
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        // First test if the password is more than 8 characters long //
        clickOn("#passwordField");
        write("test");
        clickOn("#textFieldUsername");
        assertEquals("Password must be at least 8 characters long and must not contain blank spaces", labelInvalidPassword.getText());
        // Now we test when it is more than 8 characters long but contins spaces //
        clickOn("#passwordField");
        eraseText(4);
        write("test test");
        clickOn("#textFieldUsername");
        assertEquals("Password must be at least 8 characters long and must not contain blank spaces", labelInvalidPassword.getText());
        // Test if it can contain more than 25 characters //
        clickOn("#passwordField");
        eraseText(9);
        write("123456789012345678901234567890");
        assertTrue(passwordField.getText().length() <= 25);
        // Test when the password is correct //
        doubleClickOn("#passwordField").push(KeyCode.DELETE);
        write("testtest");
        clickOn("#textFieldUsername");
        assertEquals("", labelInvalidPassword.getText());
    }

    /**
     * Test if both of the password fields share the same text
     */
    @Test
    public void test5_showHideSameText() {
        passwordField = lookup("#passwordField").query();
        textFieldPassword = lookup("#textFieldPassword").query();
        clickOn("#buttonShowHide");
        assertEquals(passwordField.getText(), textFieldPassword.getText());
    }

    /**
     * Test for the password textField
     */
    @Test
    public void test6_textFieldPasswordIsInvalid() {
        textFieldPassword = lookup("#textFieldPassword").query();
        labelInvalidPassword = lookup("#labelInvalidPassword").query();
        // First test if the password is more than 8 characters long //
        clickOn("#textFieldPassword");
        eraseText(8);
        write("test");
        clickOn("#textFieldUsername");
        assertEquals("Password must be at least 8 characters long and must not contain blank spaces", labelInvalidPassword.getText());
        // Now we test when it is more than 8 characters long but contins spaces //
        clickOn("#textFieldPassword");
        eraseText(4);
        write("test test");
        clickOn("#textFieldUsername");
        assertEquals("Password must be at least 8 characters long and must not contain blank spaces", labelInvalidPassword.getText());
        // Test if it can contain more than 25 characters //
        clickOn("#textFieldPassword");
        eraseText(9);
        write("123456789012345678901234567890");
        assertTrue(textFieldPassword.getText().length() <= 25);
        // Test when the password is correct //
        doubleClickOn("#textFieldPassword").push(KeyCode.DELETE);
        write("testtest1");
        clickOn("#textFieldUsername");
        assertEquals("", labelInvalidPassword.getText());
    }

    /**
     * Test to see if a user can log in
     */
    @Test
    public void test7_signInTest() {
        paneSignIn = lookup("#SignIn").query();
        clickOn("#buttonSignIn");
        verifyThat("Incorrect username or password.", isVisible());
        clickOn("#textFieldUsername");
        eraseText(1);
        clickOn("#textFieldPassword");
        eraseText(1);
        clickOn("#buttonSignIn");
        assertThat(paneSignIn, isInvisible());
    }
}
