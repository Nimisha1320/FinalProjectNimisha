package depaul.edu;

import java.util.ArrayList;
import java.util.List;

public class SingletonShoppingCart implements ShoppingCart{
	private static SingletonShoppingCart instance;
    private List<Product> items;

    SingletonShoppingCart() {
        this.items = new ArrayList<>();
    }

    public static SingletonShoppingCart getInstance() {
        if (instance == null) {
            instance = new SingletonShoppingCart();
        }
        return instance;
    }

    @Override
    public void addItem(Product item) {
        items.add(item);
    }

    @Override
    public List<Product> getItems() {
        return items;
    }

    @Override
    public double calculateTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

}
