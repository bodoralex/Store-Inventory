import java.util.ArrayList;

public class PersistentStore extends Store {
	
	private ArrayList<Product> products = new ArrayList<Product>();

	@Override
	public void storeProduct(Product product) {
		products.add(product);
	}

	@Override
	public ArrayList<Product> getallProduct() {
		return products;
	}

}
