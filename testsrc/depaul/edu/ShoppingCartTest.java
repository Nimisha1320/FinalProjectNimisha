package depaul.edu;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ShoppingCartTest {
	private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = SingletonShoppingCart.getInstance();
    }

    @Test
    public void testAddItem() {
        Product product = new ConcreteProduct("Test Product", 10.0);
        shoppingCart.addItem(product);
        assertEquals(1, shoppingCart.getItems().size());
        assertEquals(product, shoppingCart.getItems().get(0));
    }

    @Test
    public void testCalculateTotal() {
        Product product1 = new ConcreteProduct("Product 1", 20.0);
        Product product2 = new ConcreteProduct("Product 2", 30.0);
        shoppingCart.addItem(product1);
        shoppingCart.addItem(product2);
        assertEquals(60.0, shoppingCart.calculateTotal(), 0.001);
    }


}
