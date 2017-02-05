import java.util.ArrayList;

public interface StoreCapable {
	public ArrayList<Product> getallProduct();
	public void storeCDProduct(String name, int price, int size);
	public void storeBookProduct(String name, int price, int size);
}
