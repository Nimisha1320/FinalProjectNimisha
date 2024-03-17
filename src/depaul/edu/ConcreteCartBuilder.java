package depaul.edu;

public class ConcreteCartBuilder implements CartBuilder{
	private ShoppingCart cart;

    public ConcreteCartBuilder() {
        this.cart = new SingletonShoppingCart();
    }

    @Override
    public CartBuilder addItem(Product item) {
        cart.addItem(item);
        return this;
    }

    @Override
    public ShoppingCart build() {
        return cart;
    }

}
