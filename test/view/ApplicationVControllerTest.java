/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import main.ApplicationFXMessageVTest;
import java.util.concurrent.TimeoutException;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.junit.Assert.*;

/**
 * 
 * @author sendoa
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationVControllerTest extends ApplicationTest {
    
    Pane paneSignIn;
    
    @BeforeClass
    public static void setUpClass() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(ApplicationFXMessageVTest.class);
    }
    
    @Test
    public void test1_LogOutTest() {
        clickOn("#buttonLogOut");
        verifyThat("Are you sure you want to Log Out?", isVisible());
        push(KeyCode.ENTER);
        paneSignIn = lookup("#SignIn").query();
        assertThat(paneSignIn, isVisible());
    }
    
}
