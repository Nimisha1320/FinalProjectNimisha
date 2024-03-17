package depaul.edu;

import java.util.List;

public interface ShoppingCart {
	void addItem(Product item);

    List<Product> getItems();

    double calculateTotal();

}
