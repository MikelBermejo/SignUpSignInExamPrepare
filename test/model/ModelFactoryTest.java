package model;

import datatransferobject.Model;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author haize
 */
public class ModelFactoryTest {
    /**
     * Test of getModel method, of class ModelFactory.
     */
    @Test
    public void testGetModel() {
        Model result = ModelFactory.getModel();
        assertNotNull("Null model", result);
        assertTrue("Instance of model is not ModelImplementation", result instanceof ModelImplementation);
    }
    
}
