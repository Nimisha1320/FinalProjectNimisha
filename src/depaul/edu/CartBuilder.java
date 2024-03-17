package depaul.edu;

public interface CartBuilder {
	CartBuilder addItem(Product item);

    ShoppingCart build();

}
