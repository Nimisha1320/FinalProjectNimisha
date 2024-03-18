package depaul.edu;

import org.junit.Test;
import static org.junit.Assert.*;



public class ProductFactoryTest {

	@Test
    public void testCreateProduct() {
        ProductFactory factory = new ConcreteProductFactory();
        Product product = factory.createProduct("Test Product", 10.0);
        assertEquals("Test Product", product.getName());
        assertEquals(10.0, product.getPrice(), 0.001);
    }

}
