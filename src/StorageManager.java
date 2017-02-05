import java.util.ArrayList;

public class StorageManager{
	private StoreCapable StoreInterface;


	public void addStorage(StoreCapable storage){
		this.StoreInterface = storage;
	}
	public void addCDProduct(String name, int price, int tracks){
		StoreInterface.storeCDProduct(name, price, tracks);
	}
	public void addBookProduct(String name, int price, int size){
		StoreInterface.storeBookProduct(name, price, size);
	}
	public String listProducts(){
		Store storage = (Store) StoreInterface;
		String list = "";
		for(Product product : storage.loadProducts()){
			list += product.getName() + " - " + product.getPrice() + "\n";
		}
		return list;
	}
	
	
	public int getTotalProductPrice(){
		Store storage = (Store) StoreInterface;
		int totalPrice = 0;
		for(Product product : storage.loadProducts()){
			totalPrice += product.getPrice();
		}
		return totalPrice;
	}
}
