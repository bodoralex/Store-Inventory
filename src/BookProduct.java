public class BookProduct extends Product {

	private int pageSize;

	public BookProduct(String name, int price, int pageSize) {
		super(name, price);
		this.setPageSize(pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
