package depaul.edu;


public class OrderProcessing {
	private ShoppingCart cart;
    private MockPaymentProcessor paymentProcessor;

    public OrderProcessing(ShoppingCart cart, MockPaymentProcessor paymentProcessor) {
        this.cart = cart;
        this.paymentProcessor = paymentProcessor;
    }

    public boolean placeOrder() {
        double totalAmount = cart.calculateTotal();
        boolean paymentSuccess = paymentProcessor.processPayment(totalAmount);

        if (paymentSuccess) {
            Logger.log("Order placed. Total Amount: $" + totalAmount);
            cart.getItems().clear(); // Clear the shopping cart after successful payment
            return true;
        } else {
            Logger.log("Payment failed. Order not placed.");
            return false;
        }
    }

}
