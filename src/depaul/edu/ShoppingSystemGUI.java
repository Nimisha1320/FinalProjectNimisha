package depaul.edu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingSystemGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextArea productCatalogArea;
    private JTextArea cartArea;
    private JLabel totalPriceLabel;

    private UserAuthentication userAuthentication;
    private ProductFactory productFactory;
    private CartBuilder cartBuilder;
    private MockPaymentProcessor paymentProcessor;
    private ShoppingCart shoppingCart;

    public ShoppingSystemGUI() {
        // Initialize components
        userAuthentication = new UserAuthentication();
        productFactory = new ConcreteProductFactory();
        cartBuilder = new ConcreteCartBuilder();
        paymentProcessor = new MockPaymentProcessor();

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        productCatalogArea = new JTextArea(10, 30);
        cartArea = new JTextArea(10, 30);
        totalPriceLabel = new JLabel("Total Price: $0.00");

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.add(new JLabel("Product Catalog:"), BorderLayout.NORTH);
        productPanel.add(new JScrollPane(productCatalogArea), BorderLayout.CENTER);

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(new JLabel("Cart:"), BorderLayout.NORTH);
        cartPanel.add(new JScrollPane(cartArea), BorderLayout.CENTER);
        cartPanel.add(totalPriceLabel, BorderLayout.SOUTH);

        // Add "Buy" button for placing orders
        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });
        cartPanel.add(buyButton, BorderLayout.EAST);

        // Set layout
        setLayout(new BorderLayout());
        JPanel loginPanel = new JPanel(new FlowLayout());
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.NORTH);
        add(productPanel, BorderLayout.WEST);
        add(cartPanel, BorderLayout.CENTER);

        // Set frame properties
        setTitle("Shopping System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
        
        // Set background color for components
        getContentPane().setBackground(Color.WHITE);
        loginPanel.setBackground(Color.LIGHT_GRAY);
        productPanel.setBackground(Color.GRAY);
        cartPanel.setBackground(Color.DARK_GRAY);
        productCatalogArea.setBackground(Color.WHITE);
        cartArea.setBackground(Color.WHITE);
        totalPriceLabel.setForeground(Color.WHITE);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userAuthentication.authenticateUser(username, password)) {
            displayProductCatalog();
            shoppingCart = cartBuilder.build(); // Initialize the shopping cart after login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayProductCatalog() {
        // For simplicity, adding hardcoded products to the catalog
        Product product1 = productFactory.createProduct("Product 1", 29.99);
        Product product2 = productFactory.createProduct("Product 2", 39.99);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        // Add a button for each product in the catalog
        JButton addToCartButton1 = new JButton("Add to Cart");
        addToCartButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCart.addItem(product1);
                updateCartDisplay();
            }
        });
        productPanel.add(new JLabel("Product 1 - $29.99"));
        productPanel.add(addToCartButton1);

        JButton addToCartButton2 = new JButton("Add to Cart");
        addToCartButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCart.addItem(product2);
                updateCartDisplay();
            }
        });
        productPanel.add(new JLabel("Product 2 - $39.99"));
        productPanel.add(addToCartButton2);

        productCatalogArea.setLayout(new BorderLayout());
        productCatalogArea.add(new JScrollPane(productPanel), BorderLayout.CENTER);
    }

    private void updateCartDisplay() {
        List<Product> items = shoppingCart.getItems();
        StringBuilder cartText = new StringBuilder();
        double total = 0.0;
        for (int i = 0; i < items.size(); i++) {
            Product product = items.get(i);
            cartText.append(i + 1).append(". ").append(product.getName()).append(" - $").append(product.getPrice()).append("\n");
            total += product.getPrice();
        }
        cartArea.setText(cartText.toString());
        totalPriceLabel.setText("Total Price: $" + total);
    }

    private void placeOrder() {
        if (shoppingCart == null || shoppingCart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in the cart", "Order Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalAmount = shoppingCart.calculateTotal();
        boolean paymentSuccess = paymentProcessor.processPayment(totalAmount);

        if (paymentSuccess) {
            JOptionPane.showMessageDialog(this, "Order placed successfully. Total Amount: $" + totalAmount, "Order Placed", JOptionPane.INFORMATION_MESSAGE);
            shoppingCart.getItems().clear(); // Clear the cart after successful order placement
            updateCartDisplay(); // Update cart display after clearing
        } else {
            JOptionPane.showMessageDialog(this, "Payment failed. Order not placed.", "Payment Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingSystemGUI();
            }
        });
    }
}