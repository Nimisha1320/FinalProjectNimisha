package depaul.edu;

public class ConcreteProductFactory implements ProductFactory{
	@Override
    public Product createProduct(String name, double price) {
        return new ConcreteProduct(name, price);
    }

}
