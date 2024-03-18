package depaul.edu;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderProcessingTest {

	@Test
    public void testPlaceOrder() {
        MockPaymentProcessor paymentProcessor = new MockPaymentProcessor();
        ShoppingCart shoppingCart = SingletonShoppingCart.getInstance();
        shoppingCart.addItem(new ConcreteProduct("Test Product", 10.0));
        OrderProcessing orderProcessing = new OrderProcessing(shoppingCart, paymentProcessor);
        assertTrue(orderProcessing.placeOrder());
    }

}
