/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.concurrent.TimeoutException;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.ApplicationFX;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import static org.junit.Assert.*;

/**
 *
 * @author sendoa
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInVControllerTest extends ApplicationTest {
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationFX.class);
    }

    @Test
    public void test1_InitialStage() {
        verifyThat("#textFieldUsername", hasText(""));
        verifyThat("#passwordField", hasText(""));
        verifyThat("#textFieldPassword", hasText(""));
        verifyThat("#buttonSignIn", isEnabled());
        verifyThat("#buttonSignUp", isEnabled());
    }
    
    @Test
    public void test2_BothEmpty() {
        push(KeyCode.ENTER);
        verifyThat("#labelInvalidUser", isVisible());
        verifyThat("#labelInvalidPassword", isVisible());
        clickOn("#buttonSignIn");
        verifyThat("#labelInvalidUser", isVisible());
        verifyThat("#labelInvalidPassword", isVisible());
    }
    
    @Test
    public void test3_Username(){
        clickOn("#textFieldUsername");
        write("test test");
        clickOn("#passwordField");
        verifyThat("#labelInvalidUser", isVisible());
        clickOn("#textFieldUsername");
        eraseText(10);
        write("test");
        clickOn("#passwordField");
        verifyThat("#labelInvalidUser", isVisible());
        clickOn("#textFieldUsername");
        eraseText(4);
        write("123456789012345678901234567890");
        // Texto mayor que 25 //
    }
    
    @Test
    public void test4_passwordField(){
        clickOn("#passwordField");
        write("test");
        clickOn("#textFieldUsername");
        verifyThat("#labelInvalidPassword", isVisible());
        clickOn("#passwordField");
        eraseText(4);
        write("test test");
        clickOn("#textFieldUsername");
        verifyThat("#labelInvalidPassword", isVisible());
        clickOn("#passwordField");
        eraseText(9);
        write("testtest");
        clickOn("#textFieldUsername");
        verifyThat("#labelInvalidPassword", isVisible());
        // Texto mayor que 25 //
    }
    
    @Test
    public void test5_showHide(){
        clickOn("#buttonShowHide");
        verifyThat("#textFieldPassword", isVisible());
        /**
         * Mirar que el texto es el mismo en ambos 
         */
    }
    
    @Test 
    public void test6_textFieldPassword(){
        clickOn("#textFieldPassword");
        eraseText(8);
        write("test");
        clickOn("#textFieldUsername");
        verifyThat("#labelInvalidPassword", isVisible());
        clickOn("#textFieldPassword");
        eraseText(4);
        write("test test");
        clickOn("#textFieldUsername");
        verifyThat("#labelInvalidPassword", isVisible());
        clickOn("#textFieldPassword");
        eraseText(9);
        write("testtest");
        clickOn("#textFieldUsername");
        verifyThat("#labelInvalidPassword", isVisible());
    }
}
