public class Main {

	public static void main(String[] args) {
		StorageManager storeManager = new StorageManager();
		StoreCapable storeInterface = new PersistentStore();
		storeManager.addStorage(storeInterface);
		storeManager.addCDProduct("MC Hawer �s Tekkn�", 1800, 18);
		storeManager.addBookProduct("Sz�razf�ldi eml�s�k II", 1000, 200);
		storeManager.addBookProduct("404 Kiskutya", 40404, 404);
		storeManager.addCDProduct("vindosz 95", 1995, 9005);
        System.out.println(storeManager.listProducts());
        System.out.println(storeManager.getTotalProductPrice()+" Ft");
	}

}
