package model;

import datatransferobject.MessageEnum;
import datatransferobject.User;
import datatransferobject.Package;
import datatransferobject.UserPrivilege;
import datatransferobject.UserStatus;
import exceptions.ConnectionErrorException;
import exceptions.InvalidUserException;
import exceptions.MaxConnectionExceededException;
import exceptions.TimeOutException;
import exceptions.UserExistException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Mikel
 */
public class ModelImplementationTest extends TestCase {
    
    public ModelImplementationTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of doSignIn method, of class ModelImplementation, it tries a signIn with correct values.
     */
    @Test
    public void testDoSignIn() {
        System.out.println("doSignIn");
        User user = new User("userTest", "userTest@gmail.com", "userTest", UserStatus.ENABLED, UserPrivilege.USER, "abcd*1234", new Timestamp(System.currentTimeMillis()));
        ModelImplementation instance = new ModelImplementation();
        User result=null;
        try {
            result = instance.doSignIn(user);
        } catch (InvalidUserException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MaxConnectionExceededException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionErrorException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeOutException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(user, result);
    }
    /**
     * Test of doSignIn method, of class ModelImplementation, it trys signIn with a non-registered user.
     */
    @Test(expected=InvalidUserException.class)
    public void testDoSignInError() throws InvalidUserException {
        System.out.println("doSignIn");
        User user = new User("aaaaaa", "aaaa@aaaaa.aaa", "aaa aaaa", UserStatus.ENABLED, UserPrivilege.USER, "aaaaaa", new Timestamp(System.currentTimeMillis()));
        ModelImplementation instance = new ModelImplementation();
        User result=null;
        try {
            result = instance.doSignIn(user);
        } catch (MaxConnectionExceededException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionErrorException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeOutException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(user, result);
    }

    /**
     * Test of doSignUp method, of class ModelImplementation, it tries to do a signUp with correct values.
     */
    @Test
    public void testDoSignUp() {
        System.out.println("doSignUp");
        User user =  new User("aaaaaa", "aaaa@aaaaa.aaa", "aaa aaaa", UserStatus.ENABLED, UserPrivilege.USER, "aaaaaa", new Timestamp(System.currentTimeMillis()));
        ModelImplementation instance = new ModelImplementation();
        try {
            instance.doSignUp(user);
        } catch (MaxConnectionExceededException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionErrorException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserExistException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeOutException ex) {
            Logger.getLogger(ModelImplementationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
