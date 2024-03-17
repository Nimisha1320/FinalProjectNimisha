package depaul.edu;

public class MockPaymentProcessor {
	public boolean processPayment(double amount) {
        // Simulate payment processing, return true if successful
        return Math.random() < 0.9; // 90% success rate for simplicity
    }

}
